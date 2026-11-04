package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.MovieCreation
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ProjectRepository
import com.example.data.ScriptEngine
import com.example.model.GeneratedShortsScript
import com.example.model.ScriptDuration
import com.example.model.ScriptNiche
import com.example.model.ScriptScene
import com.example.model.ScriptTone
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.NeonPurpleLight
import com.example.ui.theme.ShortsRed
import com.example.ui.theme.StudioBackground
import com.example.ui.theme.StudioCardBorder
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.StudioSurfaceVariant
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GeneratorScreen(
  onSendToTeleprompter: (GeneratedShortsScript) -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val clipboard = LocalClipboardManager.current
  val coroutineScope = rememberCoroutineScope()

  var selectedNiche by remember { mutableStateOf(ScriptNiche.SPACE) }
  var selectedDuration by remember { mutableStateOf(ScriptDuration.THIRTY) }
  var selectedTone by remember { mutableStateOf(ScriptTone.DRAMATIC) }
  var customTopicInput by remember { mutableStateOf("") }
  var isGenerating by remember { mutableStateOf(false) }

  // Initial generated script
  var currentScript by remember {
    mutableStateOf(
      ScriptEngine.generateScript(
        niche = ScriptNiche.SPACE,
        duration = ScriptDuration.THIRTY,
        tone = ScriptTone.DRAMATIC
      )
    )
  }

  Box(modifier = modifier.fillMaxSize().background(StudioBackground)) {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      item {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = "AI SCRIPT & STORYBOARD STUDIO",
          color = Color(0xFF94A3B8),
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Text(
          text = "Generate High-Retention Shorts",
          color = Color.White,
          fontSize = 22.sp,
          fontWeight = FontWeight.Bold
        )
      }

      // NICHE SELECTOR
      item {
        Column {
          Text(
            text = "1. SELECT HIGH-RETENTION NICHE",
            color = Color(0xFF94A3B8),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(8.dp))
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            ScriptNiche.entries.forEach { niche ->
              val isSelected = selectedNiche == niche
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .background(if (isSelected) ShortsRed else StudioSurface)
                  .border(
                    1.dp,
                    if (isSelected) ShortsRed else StudioCardBorder,
                    RoundedCornerShape(12.dp)
                  )
                  .clickable { selectedNiche = niche }
                  .padding(horizontal = 14.dp, vertical = 10.dp)
              ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(text = niche.emoji, fontSize = 16.sp)
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(
                    text = niche.label,
                    color = Color.White,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    fontSize = 13.sp
                  )
                }
              }
            }
          }
        }
      }

      // DURATION & TONE ROW
      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          // Duration
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = "2. DURATION",
              color = Color(0xFF94A3B8),
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              ScriptDuration.entries.forEach { duration ->
                val isSelected = selectedDuration == duration
                Box(
                  modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (isSelected) NeonPurple else StudioSurface)
                    .border(1.dp, if (isSelected) NeonPurple else StudioCardBorder, RoundedCornerShape(10.dp))
                    .clickable { selectedDuration = duration }
                    .padding(vertical = 8.dp),
                  contentAlignment = Alignment.Center
                ) {
                  Text(
                    text = "${duration.seconds}s",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                  )
                }
              }
            }
          }

          // Tone
          Column(modifier = Modifier.weight(1.3f)) {
            Text(
              text = "3. TONE & PACING",
              color = Color(0xFF94A3B8),
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
              modifier = Modifier.horizontalScroll(rememberScrollState()),
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              ScriptTone.entries.forEach { tone ->
                val isSelected = selectedTone == tone
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (isSelected) Color(0xFF3B2F5C) else StudioSurface)
                    .border(1.dp, if (isSelected) NeonPurple else StudioCardBorder, RoundedCornerShape(10.dp))
                    .clickable { selectedTone = tone }
                    .padding(horizontal = 10.dp, vertical = 8.dp)
                ) {
                  Text(
                    text = "${tone.emoji} ${tone.label.split(" ").first()}",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                  )
                }
              }
            }
          }
        }
      }

      // CUSTOM TOPIC (OPTIONAL)
      item {
        Column {
          Text(
            text = "4. TOPIC OR ANGLE (OPTIONAL)",
            color = Color(0xFF94A3B8),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(6.dp))
          OutlinedTextField(
            value = customTopicInput,
            onValueChange = { customTopicInput = it },
            placeholder = {
              Text(
                text = "e.g. Deep sea creature that glows, or leave empty for AI trend",
                color = Color(0xFF64748B),
                fontSize = 13.sp
              )
            },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("custom_topic_input"),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedContainerColor = StudioSurface,
              unfocusedContainerColor = StudioSurface,
              focusedBorderColor = ShortsRed,
              unfocusedBorderColor = StudioCardBorder,
              focusedTextColor = Color.White,
              unfocusedTextColor = Color.White
            ),
            singleLine = true
          )
        }
      }

      // GENERATE BUTTON
      item {
        Button(
          onClick = {
            isGenerating = true
            coroutineScope.launch {
              delay(400) // Brief simulation of AI structuring
              currentScript = ScriptEngine.generateScript(
                niche = selectedNiche,
                duration = selectedDuration,
                tone = selectedTone,
                customTopic = customTopicInput
              )
              isGenerating = false
              Toast.makeText(context, "Generated 9:16 viral script & visual storyboard!", Toast.LENGTH_SHORT).show()
            }
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .testTag("generate_script_button"),
          shape = RoundedCornerShape(14.dp),
          colors = ButtonDefaults.buttonColors(containerColor = ShortsRed),
          enabled = !isGenerating
        ) {
          if (isGenerating) {
            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Engineering Hooks & Visuals...", fontWeight = FontWeight.Bold)
          } else {
            Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Generate Viral Shorts Script", fontWeight = FontWeight.Bold, fontSize = 15.sp)
          }
        }
      }

      // RESULT HEADER & ACTIONS
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .border(1.dp, StudioCardBorder, RoundedCornerShape(18.dp)),
          colors = CardDefaults.cardColors(containerColor = StudioSurface)
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(8.dp))
                  .background(NeonGreen.copy(alpha = 0.15f))
                  .border(1.dp, NeonGreen.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(
                  text = "Viral Score: ${currentScript.viralScore}/100",
                  color = NeonGreen,
                  fontWeight = FontWeight.Bold,
                  fontSize = 12.sp
                )
              }

              Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // Save Project
                IconButton(
                  onClick = {
                    ProjectRepository.saveProject(currentScript)
                    Toast.makeText(context, "Saved to My Projects!", Toast.LENGTH_SHORT).show()
                  },
                  modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(StudioSurfaceVariant)
                ) {
                  Icon(imageVector = Icons.Default.BookmarkAdd, contentDescription = "Save Project", tint = Color.White, modifier = Modifier.size(18.dp))
                }

                // Copy Full Script
                IconButton(
                  onClick = {
                    val fullText = buildString {
                      appendLine("TITLE: ${currentScript.title}")
                      appendLine("HOOK (0-3s): ${currentScript.hookSpoken}")
                      appendLine()
                      currentScript.scenes.forEach { s ->
                        appendLine("[${s.timecode}] ${s.voiceover}")
                        appendLine("AI PROMPT: ${s.visualPrompt}")
                        appendLine()
                      }
                      appendLine("LOOP: ${currentScript.loopEnding}")
                      appendLine("TAGS: ${currentScript.hashtags.joinToString(" ")}")
                    }
                    clipboard.setText(AnnotatedString(fullText))
                    Toast.makeText(context, "Copied full script + visual prompts!", Toast.LENGTH_SHORT).show()
                  },
                  modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(StudioSurfaceVariant)
                ) {
                  Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy All", tint = Color.White, modifier = Modifier.size(18.dp))
                }
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
              text = currentScript.title,
              color = Color.White,
              fontWeight = FontWeight.Bold,
              fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = currentScript.viralReason,
              color = Color(0xFF94A3B8),
              fontSize = 12.sp,
              lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Teleprompter quick launcher
            Button(
              onClick = { onSendToTeleprompter(currentScript) },
              colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier.fillMaxWidth().testTag("open_teleprompter_button")
            ) {
              Icon(imageVector = Icons.Default.RecordVoiceOver, contentDescription = null, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(text = "Practice in Studio Teleprompter", fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
          }
        }
      }

      // HOOK BLOCK
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.5.dp, ShortsRed, RoundedCornerShape(16.dp)),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1F1118))
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(6.dp))
                  .background(ShortsRed)
                  .padding(horizontal = 8.dp, vertical = 2.dp)
              ) {
                Text(
                  text = "0:00 - 0:03 THE HOOK",
                  color = Color.White,
                  fontWeight = FontWeight.Black,
                  fontSize = 10.sp
                )
              }
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "Must Stop The Swipe!",
                color = Color(0xFFFFB3BA),
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
              )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
              text = "\"${currentScript.hookSpoken}\"",
              color = Color.White,
              fontWeight = FontWeight.Bold,
              fontSize = 15.sp,
              lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(verticalAlignment = Alignment.Top) {
              Icon(imageVector = Icons.Default.Videocam, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(14.dp).padding(top = 2.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Visual Cue: ${currentScript.hookVisual}",
                color = Color(0xFFCBD5E1),
                fontSize = 12.sp
              )
            }
          }
        }
      }

      // SCENE BY SCENE STORYBOARD
      item {
        Text(
          text = "SCENE-BY-SCENE STORYBOARD & AI PROMPTS",
          color = Color(0xFF94A3B8),
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp
        )
      }

      items(currentScript.scenes) { scene ->
        SceneCard(scene = scene)
      }

      // SEAMLESS LOOP BLOCK
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, NeonGreen.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
          colors = CardDefaults.cardColors(containerColor = Color(0xFF0F1E19))
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(imageVector = Icons.Default.Repeat, contentDescription = null, tint = NeonGreen, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "THE SEAMLESS LOOP TRICK",
                color = NeonGreen,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp
              )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = currentScript.loopEnding,
              color = Color.White,
              fontWeight = FontWeight.SemiBold,
              fontSize = 13.sp,
              lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "Notice how the final word bridges directly back into the opening hook without any pause, tricking viewers into watching twice for >100% retention!",
              color = Color(0xFF94A3B8),
              fontSize = 11.sp
            )
          }
        }
      }

      // VIRAL TAGS
      item {
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(6.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          currentScript.hashtags.forEach { tag ->
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(StudioSurfaceVariant)
                .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
              Text(text = tag, color = NeonCyan, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
          }
        }
      }

      item {
        Spacer(modifier = Modifier.height(24.dp))
      }
    }
  }
}

@Composable
private fun SceneCard(scene: ScriptScene) {
  val context = LocalContext.current
  val clipboard = LocalClipboardManager.current

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .border(1.dp, StudioCardBorder, RoundedCornerShape(14.dp)),
    colors = CardDefaults.cardColors(containerColor = StudioSurface)
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(NeonPurple.copy(alpha = 0.2f))
              .padding(horizontal = 8.dp, vertical = 2.dp)
          ) {
            Text(
              text = "SCENE ${scene.sceneNumber}",
              color = NeonPurpleLight,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = scene.timecode,
            color = Color(0xFF94A3B8),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
          )
        }

        // SFX Tag
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(imageVector = Icons.Default.GraphicEq, contentDescription = null, tint = NeonAmber, modifier = Modifier.size(13.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text(text = scene.soundEffect, color = NeonAmber, fontSize = 10.sp, maxLines = 1)
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Voiceover dialogue
      Text(
        text = "Voiceover:",
        color = Color(0xFF94A3B8),
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold
      )
      Text(
        text = scene.voiceover,
        color = Color.White,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
      )

      Spacer(modifier = Modifier.height(10.dp))

      // AI Image/Video Prompt Box
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(Color(0xFF0F0E18))
          .border(1.dp, Color(0xFF28253B), RoundedCornerShape(8.dp))
          .padding(10.dp)
      ) {
        Column {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(imageVector = Icons.Default.MovieCreation, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(14.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Midjourney / Flux / Runway Prompt",
                color = NeonCyan,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
              )
            }

            IconButton(
              onClick = {
                clipboard.setText(AnnotatedString(scene.visualPrompt))
                Toast.makeText(context, "Copied AI visual prompt!", Toast.LENGTH_SHORT).show()
              },
              modifier = Modifier.size(24.dp)
            ) {
              Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy Prompt", tint = Color.White, modifier = Modifier.size(14.dp))
            }
          }

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = scene.visualPrompt,
            color = Color(0xFFE2E8F0),
            fontSize = 11.sp,
            lineHeight = 15.sp
          )

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = "Camera Movement: ${scene.visualMotion}",
            color = Color(0xFF94A3B8),
            fontSize = 10.sp
          )
        }
      }
    }
  }
}
