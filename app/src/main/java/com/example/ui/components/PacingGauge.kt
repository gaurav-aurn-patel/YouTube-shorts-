package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.ShortsRed
import com.example.ui.theme.StudioSurfaceVariant

@Composable
fun PacingGauge(
  currentWpm: Int,
  modifier: Modifier = Modifier
) {
  // Shorts speaking speed zones:
  // < 140 WPM: Too Slow (Viewer swiping away)
  // 155 - 175 WPM: Golden Shorts Sweet Spot (High Retention)
  // > 185 WPM: Rush / Unintelligible
  val (zoneColor, zoneLabel, advice) = when {
    currentWpm < 140 -> Triple(
      NeonAmber,
      "Too Slow ($currentWpm WPM)",
      "Viewers will swipe! Aim for 155-170 WPM by eliminating pauses."
    )
    currentWpm in 140..154 -> Triple(
      NeonCyan,
      "Moderate Pace ($currentWpm WPM)",
      "Good for deep mysteries, but pick up speed for tech/facts."
    )
    currentWpm in 155..175 -> Triple(
      NeonGreen,
      "Viral Sweet Spot ($currentWpm WPM)",
      "Perfect cadence! Matches top retention channels like Kurzgesagt and Veritasium."
    )
    else -> Triple(
      ShortsRed,
      "Too Fast ($currentWpm WPM)",
      "Words might blur together; slow down slightly for clarity."
    )
  }

  val animatedZoneColor by animateColorAsState(targetValue = zoneColor, label = "pacingColor")
  val progress = ((currentWpm.coerceIn(100, 220) - 100) / 120f).coerceIn(0f, 1f)

  Column(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .background(StudioSurfaceVariant)
      .border(1.dp, animatedZoneColor.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
      .padding(14.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(animatedZoneColor)
        )
        Text(
          text = "  $zoneLabel",
          color = animatedZoneColor,
          fontWeight = FontWeight.Bold,
          fontSize = 13.sp
        )
      }
      Text(
        text = "Goal: 160 WPM",
        color = Color(0xFF94A3B8),
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    LinearProgressIndicator(
      progress = { progress },
      modifier = Modifier
        .fillMaxWidth()
        .height(8.dp)
        .clip(RoundedCornerShape(4.dp)),
      color = animatedZoneColor,
      trackColor = Color(0xFF161522),
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
      text = advice,
      color = Color(0xFFCBD5E1),
      fontSize = 12.sp,
      lineHeight = 16.sp
    )
  }
}
