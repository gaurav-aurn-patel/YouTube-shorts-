package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.GeneratedShortsScript
import com.example.ui.components.PacingGauge
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.ShortsRed
import com.example.ui.theme.StudioBackground
import com.example.ui.theme.StudioCardBorder
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.StudioSurfaceVariant
import kotlinx.coroutines.delay

@Composable
fun TeleprompterScreen(
  script: GeneratedShortsScript?,
  modifier: Modifier = Modifier
) {
  var isPlaying by remember { mutableStateOf(false) }
  var elapsedSeconds by remember { mutableFloatStateOf(0f) }
  var targetWpm by remember { mutableFloatStateOf(160f) }
  var currentSceneIndex by remember { mutableIntStateOf(0) }

  // Extract all voiceover text
  val voiceoverLines = remember(script) {
    if (script != null) {
      listOf("HOOK: ${script.hookSpoken}") + script.scenes.map { "[${it.timecode}] ${it.voiceover}" } + listOf("LOOP: ${script.loopEnding}")
    } else {
      listOf(
        "HOOK: Your brain is lying to you every single second about this...",
        "[0:00 - 0:04] It actually processes visual data eighty milliseconds in the past.",
        "[0:04 - 0:11] When you look at a ticking clock and the second hand seems frozen, that is called chronostasis.",
        "[0:11 - 0:20] Your brain retroactively pastes in the new image to fool your consciousness.",
        "LOOP: ...which is why your brain is lying to you every single second about this!"
      )
    }
  }

  val totalWords = remember(voiceoverLines) {
    voiceoverLines.joinToString(" ").split("\\s+".toRegex()).size
  }

  // Calculate live WPM
  val currentWpm = remember(elapsedSeconds, totalWords) {
    if (elapsedSeconds > 2f) {
      val minutes = elapsedSeconds / 60f
      // Estimate based on portion completed or target
      (targetWpm.toInt()).coerceIn(110, 210)
    } else {
      targetWpm.toInt()
    }
  }

  // Timer loop
  LaunchedEffect(isPlaying) {
    while (isPlaying) {
      delay(100)
      elapsedSeconds += 0.1f
      if (elapsedSeconds >= (script?.duration?.seconds?.toFloat() ?: 30f)) {
        isPlaying = false
      }
    }
  }

  // Metronome pulse animation
  val infiniteTransition = rememberInfiniteTransition(label = "beat")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.95f,
    targetValue = 1.15f,
    animationSpec = infiniteRepeatable(
      animation = tween((60000 / targetWpm.toInt()).coerceAtLeast(200)),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse"
  )

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(StudioBackground)
      .padding(horizontal = 16.dp)
  ) {
    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = "STUDIO TELEPROMPTER & PACING COACH",
      color = Color(0xFF94A3B8),
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp
    )
    Text(
      text = script?.title ?: "Shorts Voiceover Coach",
      color = Color.White,
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
      maxLines = 1
    )

    Spacer(modifier = Modifier.height(12.dp))

    // LIVE TIMER & PACING HUD
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .border(1.dp, StudioCardBorder, RoundedCornerShape(16.dp)),
      colors = CardDefaults.cardColors(containerColor = StudioSurface)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Timer
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Timer, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = String.format("%.1fs / %ds", elapsedSeconds, script?.duration?.seconds ?: 30),
              color = Color.White,
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp
            )
          }

          // Visual Beat Pulse
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .scale(if (isPlaying) pulseScale else 1f)
                .size(12.dp)
                .clip(CircleShape)
                .background(if (isPlaying) ShortsRed else Color.Gray)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "${targetWpm.toInt()} WPM BEAT",
              color = if (isPlaying) ShortsRed else Color.Gray,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Pacing Gauge
        PacingGauge(currentWpm = currentWpm)

        Spacer(modifier = Modifier.height(10.dp))

        // Slider for target speed
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = "Target Speed: ${targetWpm.toInt()} WPM",
            color = Color(0xFFCBD5E1),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
          )
          Text(
            text = "Total Words: $totalWords",
            color = Color(0xFF94A3B8),
            fontSize = 11.sp
          )
        }

        Slider(
          value = targetWpm,
          onValueChange = { targetWpm = it },
          valueRange = 130f..190f,
          colors = SliderDefaults.colors(
            thumbColor = NeonPurple,
            activeTrackColor = NeonPurple,
            inactiveTrackColor = Color(0xFF28253D)
          ),
          modifier = Modifier.fillMaxWidth()
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // TELEPROMPTER SCRIPT DISPLAY
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
        .clip(RoundedCornerShape(16.dp))
        .border(1.5.dp, if (isPlaying) ShortsRed else StudioCardBorder, RoundedCornerShape(16.dp)),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF0D0C14))
    ) {
      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        items(voiceoverLines.size) { index ->
          val line = voiceoverLines[index]
          val isCurrent = isPlaying && ((elapsedSeconds / ((script?.duration?.seconds?.toFloat() ?: 30f) / voiceoverLines.size)).toInt() == index)

          Surface(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(10.dp))
              .border(
                1.dp,
                if (isCurrent) NeonGreen else Color.Transparent,
                RoundedCornerShape(10.dp)
              ),
            color = if (isCurrent) Color(0xFF1B2E24) else Color(0xFF141320),
            shape = RoundedCornerShape(10.dp)
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Text(
                text = line,
                color = if (isCurrent) Color(0xFFF0FDF4) else Color(0xFFCBD5E1),
                fontSize = if (isCurrent) 17.sp else 15.sp,
                fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                lineHeight = 24.sp
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // PLAYBACK CONTROLS
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      Button(
        onClick = { isPlaying = !isPlaying },
        modifier = Modifier
          .weight(1f)
          .height(52.dp)
          .testTag("teleprompter_play_button"),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = if (isPlaying) NeonAmber else ShortsRed
        )
      ) {
        Icon(
          imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
          contentDescription = null,
          modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = if (isPlaying) "Pause Recording" else "Start Teleprompter",
          fontWeight = FontWeight.Bold,
          fontSize = 15.sp
        )
      }

      Button(
        onClick = {
          isPlaying = false
          elapsedSeconds = 0f
        },
        modifier = Modifier
          .height(52.dp)
          .testTag("teleprompter_reset_button"),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = StudioSurfaceVariant)
      ) {
        Icon(imageVector = Icons.Default.Refresh, contentDescription = "Reset", tint = Color.White)
      }
    }
  }
}
