package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ScreenSearchDesktop
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.window.Dialog
import com.example.data.GuideData
import com.example.model.GuidePhase
import com.example.ui.components.SafeZoneSimulator
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.ShortsRed
import com.example.ui.theme.ShortsRedDark
import com.example.ui.theme.StudioBackground
import com.example.ui.theme.StudioCardBorder
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.StudioSurfaceVariant

@Composable
fun GuideScreen(
  onNavigateToGenerator: () -> Unit,
  onNavigateToTools: () -> Unit,
  modifier: Modifier = Modifier
) {
  var expandedStepIndex by remember { mutableStateOf<Int?>(0) }
  var showSafeZoneModal by remember { mutableStateOf(false) }

  Box(modifier = modifier.fillMaxSize().background(StudioBackground)) {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      item {
        Spacer(modifier = Modifier.height(8.dp))
        GuideHeroBanner(
          onOpenSafeZones = { showSafeZoneModal = true },
          onStartCreating = onNavigateToGenerator
        )
      }

      item {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "THE 7-STEP AI MASTERCLASS",
            color = Color(0xFF94A3B8),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
          Text(
            text = "Total Workflow: ~45 mins",
            color = NeonCyan,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
          )
        }
      }

      items(GuideData.masterPhases) { phase ->
        val isExpanded = expandedStepIndex == phase.stepNumber - 1
        GuidePhaseCard(
          phase = phase,
          isExpanded = isExpanded,
          onToggle = {
            expandedStepIndex = if (isExpanded) null else phase.stepNumber - 1
          },
          onNavigateToTools = onNavigateToTools
        )
      }

      item {
        Spacer(modifier = Modifier.height(24.dp))
      }
    }

    if (showSafeZoneModal) {
      Dialog(onDismissRequest = { showSafeZoneModal = false }) {
        Surface(
          shape = RoundedCornerShape(24.dp),
          color = StudioSurface,
          border = androidx.compose.foundation.BorderStroke(1.dp, StudioCardBorder),
          modifier = Modifier.padding(8.dp)
        ) {
          Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "YouTube Shorts 9:16 Safe Zones",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
              )
              IconButton(onClick = { showSafeZoneModal = false }) {
                Text(text = "✕", color = Color.White, fontWeight = FontWeight.Bold)
              }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "Never place subtitles or focal points in the red zones! Keep everything in the central green safe zone.",
              color = Color(0xFF94A3B8),
              fontSize = 12.sp,
              modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(14.dp))
            SafeZoneSimulator()
            Spacer(modifier = Modifier.height(12.dp))
            Button(
              onClick = { showSafeZoneModal = false },
              colors = ButtonDefaults.buttonColors(containerColor = ShortsRed),
              modifier = Modifier.fillMaxWidth()
            ) {
              Text(text = "Got It, Back to Guide", fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }
  }
}

@Composable
private fun GuideHeroBanner(
  onOpenSafeZones: () -> Unit,
  onStartCreating: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(20.dp))
      .border(1.dp, StudioCardBorder, RoundedCornerShape(20.dp)),
    colors = CardDefaults.cardColors(containerColor = StudioSurface)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          Brush.horizontalGradient(
            listOf(
              ShortsRedDark.copy(alpha = 0.35f),
              NeonPurple.copy(alpha = 0.25f),
              StudioSurface
            )
          )
        )
        .padding(18.dp)
    ) {
      Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(ShortsRed)
              .padding(horizontal = 8.dp, vertical = 3.dp)
          ) {
            Text(
              text = "AI BLUEPRINT",
              color = Color.White,
              fontWeight = FontWeight.Black,
              fontSize = 10.sp,
              letterSpacing = 0.5.sp
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Algorithm Secrets Included",
            color = NeonCyan,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
          text = "How to Create Viral YouTube Shorts with AI",
          color = Color.White,
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          lineHeight = 26.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
          text = "From 3-second psychological hooks to 9:16 photorealistic visuals and seamless loop retention tricks.",
          color = Color(0xFFCBD5E1),
          fontSize = 13.sp,
          lineHeight = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Button(
            onClick = onStartCreating,
            modifier = Modifier
              .weight(1f)
              .testTag("guide_start_generating_button"),
            colors = ButtonDefaults.buttonColors(containerColor = ShortsRed),
            shape = RoundedCornerShape(12.dp)
          ) {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Script Studio", fontWeight = FontWeight.Bold, fontSize = 13.sp)
          }

          Button(
            onClick = onOpenSafeZones,
            modifier = Modifier
              .weight(1f)
              .testTag("guide_safe_zones_button"),
            colors = ButtonDefaults.buttonColors(containerColor = StudioSurfaceVariant),
            shape = RoundedCornerShape(12.dp)
          ) {
            Icon(imageVector = Icons.Default.Shield, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Safe Zones", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GuidePhaseCard(
  phase: GuidePhase,
  isExpanded: Boolean,
  onToggle: () -> Unit,
  onNavigateToTools: () -> Unit
) {
  val context = LocalContext.current
  val clipboard = LocalClipboardManager.current

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .border(
        1.dp,
        if (isExpanded) ShortsRed.copy(alpha = 0.6f) else StudioCardBorder,
        RoundedCornerShape(16.dp)
      )
      .clickable(onClick = onToggle),
    colors = CardDefaults.cardColors(
      containerColor = if (isExpanded) StudioSurfaceVariant else StudioSurface
    )
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(if (isExpanded) ShortsRed else Color(0xFF262436)),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "${phase.stepNumber}",
              color = Color.White,
              fontWeight = FontWeight.Bold,
              fontSize = 14.sp
            )
          }
          Spacer(modifier = Modifier.width(12.dp))
          Column {
            Text(
              text = phase.title,
              color = Color.White,
              fontWeight = FontWeight.Bold,
              fontSize = 15.sp
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = phase.tag,
                color = NeonCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
              )
              Text(
                text = " • ${phase.timeEstimate}",
                color = Color(0xFF94A3B8),
                fontSize = 11.sp
              )
            }
          }
        }

        Icon(
          imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
          contentDescription = null,
          tint = Color(0xFF94A3B8)
        )
      }

      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = phase.summary,
        color = Color(0xFFCBD5E1),
        fontSize = 13.sp,
        lineHeight = 18.sp
      )

      AnimatedVisibility(
        visible = isExpanded,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
      ) {
        Column(modifier = Modifier.padding(top = 14.dp)) {
          // KEY FORMULA BANNER
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(10.dp))
              .background(Color(0xFF0F0E18))
              .border(1.dp, NeonPurple.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
              .padding(10.dp)
          ) {
            Row(verticalAlignment = Alignment.Top) {
              Icon(
                imageVector = Icons.Default.Lightbulb,
                contentDescription = null,
                tint = NeonAmber,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Column {
                Text(
                  text = "CORE RETENTION FORMULA",
                  color = NeonAmber,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Black
                )
                Text(
                  text = phase.keyFormula,
                  color = Color.White,
                  fontSize = 12.sp,
                  fontWeight = FontWeight.SemiBold
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          // DEEP DIVE EXPLANATION
          Text(
            text = "HOW TO EXECUTE",
            color = Color(0xFF94A3B8),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = phase.detailedExplanation,
            color = Color(0xFFE2E8F0),
            fontSize = 13.sp,
            lineHeight = 19.sp
          )

          Spacer(modifier = Modifier.height(14.dp))

          // COPYABLE AI PROMPTS
          if (phase.aiPrompts.isNotEmpty()) {
            Text(
              text = "READY-TO-USE AI PROMPTS",
              color = Color(0xFF94A3B8),
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(6.dp))

            phase.aiPrompts.forEach { promptText ->
              Surface(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 4.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF0D0C14),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF232135))
              ) {
                Row(
                  modifier = Modifier.padding(10.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = promptText,
                    color = Color(0xFFE0E7FF),
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier.weight(1f)
                  )
                  IconButton(
                    onClick = {
                      clipboard.setText(AnnotatedString(promptText))
                      Toast.makeText(context, "Prompt copied to clipboard!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.size(32.dp)
                  ) {
                    Icon(
                      imageVector = Icons.Default.ContentCopy,
                      contentDescription = "Copy Prompt",
                      tint = NeonCyan,
                      modifier = Modifier.size(16.dp)
                    )
                  }
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // RECOMMENDED TOOLS TAGS
          Text(
            text = "TOP RECOMMENDED TOOLS",
            color = Color(0xFF94A3B8),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
          )
          Spacer(modifier = Modifier.height(6.dp))
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            phase.recommendedTools.forEach { tool ->
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(6.dp))
                  .background(Color(0xFF1B1927))
                  .border(1.dp, Color(0xFF332F4A), RoundedCornerShape(6.dp))
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(text = tool, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
              }
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // COMMON MISTAKES TO AVOID
          Text(
            text = "FATAL MISTAKES TO AVOID",
            color = ShortsRed,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
          )
          Spacer(modifier = Modifier.height(6.dp))
          phase.commonMistakes.forEach { mistake ->
            Row(
              modifier = Modifier.padding(vertical = 2.dp),
              verticalAlignment = Alignment.Top
            ) {
              Icon(
                imageVector = Icons.Default.ErrorOutline,
                contentDescription = null,
                tint = ShortsRed,
                modifier = Modifier.size(14.dp).padding(top = 2.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = mistake,
                color = Color(0xFFFCA5A5),
                fontSize = 12.sp,
                lineHeight = 16.sp
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          // ACTIONABLE CHECKLIST INDICATOR
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
              .fillMaxWidth()
              .background(NeonGreen.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
              .padding(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.CheckCircle,
              contentDescription = null,
              tint = NeonGreen,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Milestone: ${phase.checklistItem}",
              color = NeonGreen,
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold
            )
          }
        }
      }
    }
  }
}
