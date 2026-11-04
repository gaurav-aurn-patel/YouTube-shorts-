package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ToolsData
import com.example.model.AIToolInfo
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

@Composable
fun ToolsScreen(modifier: Modifier = Modifier) {
  var selectedCategory by remember { mutableStateOf("All") }
  var showPromptModal by remember { mutableStateOf(false) }
  val context = LocalContext.current
  val clipboard = LocalClipboardManager.current
  val categories = listOf("All", "Video Editor & Captions", "AI Voiceover", "AI Visuals", "AI Video Generation", "Long-to-Shorts Repurposing", "All-In-One Generator")

  val filteredTools = remember(selectedCategory) {
    if (selectedCategory == "All") ToolsData.aiTools
    else ToolsData.aiTools.filter { it.category == selectedCategory }
  }

  val systemPromptText = remember {
    """
    You are the Autonomous YouTube Shorts Directing & Editing Engine (ShortsDirector-v1).
    You transform raw topics into fully orchestrated, production-ready 9:16 vertical video specifications that achieve >75% Viewed Rate (vs. Swiped Away) and >100% Average Percentage Viewed.

    PIPELINE STAGES:
    1. 0:00-0:03 Pattern-Interrupt Hook (Visual + Auditory shocker)
    2. 0:03-0:15 Open Loop (Tension build with withheld climax)
    3. 0:15-0:45 Micro-Payoffs (New visual cut every 2.5-3.5 seconds, 155-175 WPM)
    4. 0:45-0:60 Climax & Seamless Loop Bridge (Last word grammatically fuses into opening hook word)

    ASSET ORCHESTRATION:
    - TTS Voiceover: SSML pause markers, word timestamps enabled, normalized to -1.5dB
    - Visuals: 9:16 vertical stills with deterministic camera motion (zoom_in, pan, speed_ramp)
    - SFX: Whoosh, bass impact, tension risers at -8dB on cuts
    - Background Music: Ducked to -22dB
    - Captions: Center safe zone (35%-60% height), 1-3 words per burst with pop animation

    OUTPUT REQUIREMENT:
    Return ONLY a single valid JSON Edit Decision List (EDL) with video_tracks, sfx_tracks, background_music, and dynamic_captions.
    """.trimIndent()
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
          text = "AI TOOLS DIRECTORY & WORKFLOWS",
          color = Color(0xFF94A3B8),
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Text(
          text = "The Best AI Stack for Shorts",
          color = Color.White,
          fontSize = 22.sp,
          fontWeight = FontWeight.Bold
        )
      }

      // ARCHITECTURAL SYSTEM PROMPT BANNER
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(1.5.dp, NeonPurple, RoundedCornerShape(16.dp))
            .clickable { showPromptModal = true },
          colors = CardDefaults.cardColors(containerColor = Color(0xFF1B1428))
        ) {
          Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(NeonPurple),
              contentAlignment = Alignment.Center
            ) {
              Icon(imageVector = Icons.Default.Terminal, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "AUTOMATION ENGINE SYSTEM PROMPT",
                color = NeonCyan,
                fontSize = 10.sp,
                fontWeight = FontWeight.Black
              )
              Text(
                text = "Autonomous Shorts Engine & JSON EDL",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
              )
              Text(
                text = "Engine prompt for viral hooks, TTS, visual prompts & EDL assembly",
                color = Color(0xFF94A3B8),
                fontSize = 11.sp
              )
            }
            Button(
              onClick = { showPromptModal = true },
              colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
              shape = RoundedCornerShape(8.dp)
            ) {
              Text(text = "View Spec", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
          }
        }
      }

      // 10-MINUTE FACELESS SHORTS WORKFLOW BANNER
      item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(18.dp))
          .border(1.dp, StudioCardBorder, RoundedCornerShape(18.dp)),
        colors = CardDefaults.cardColors(containerColor = StudioSurface)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              Brush.horizontalGradient(
                listOf(
                  Color(0xFF2E1A36),
                  StudioSurface
                )
              )
            )
            .padding(16.dp)
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.ElectricBolt, contentDescription = null, tint = NeonAmber, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "THE 10-MINUTE FACELESS WORKFLOW",
              color = NeonAmber,
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          ToolsData.tenMinuteWorkflowSteps.forEach { step ->
            Row(
              modifier = Modifier.padding(vertical = 3.dp),
              verticalAlignment = Alignment.Top
            ) {
              Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(14.dp).padding(top = 2.dp))
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = step,
                color = Color(0xFFE2E8F0),
                fontSize = 12.sp,
                lineHeight = 17.sp
              )
            }
          }
        }
      }
    }

    // CATEGORY FILTER
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        categories.forEach { cat ->
          val isSelected = selectedCategory == cat
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(10.dp))
              .background(if (isSelected) ShortsRed else StudioSurface)
              .border(1.dp, if (isSelected) ShortsRed else StudioCardBorder, RoundedCornerShape(10.dp))
              .clickable { selectedCategory = cat }
              .padding(horizontal = 12.dp, vertical = 8.dp)
          ) {
            Text(
              text = cat,
              color = Color.White,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
              fontSize = 12.sp
            )
          }
        }
      }
    }

    // AI TOOL CARDS
    items(filteredTools) { tool ->
      ToolCard(tool = tool)
    }

    item {
      Spacer(modifier = Modifier.height(24.dp))
    }
  }

  if (showPromptModal) {
    Dialog(onDismissRequest = { showPromptModal = false }) {
      Surface(
        shape = RoundedCornerShape(20.dp),
        color = StudioSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, StudioCardBorder),
        modifier = Modifier.fillMaxWidth().padding(12.dp)
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(imageVector = Icons.Default.Terminal, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(8.dp))
              Text(text = "Engine System Prompt", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            IconButton(onClick = { showPromptModal = false }, modifier = Modifier.size(28.dp)) {
              Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.White)
            }
          }

          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "Engine prompt for automated 9:16 Shorts with viral hooks, TTS, visual prompts, and JSON Edit Decision List (EDL).",
            color = Color(0xFF94A3B8),
            fontSize = 11.sp
          )

          Spacer(modifier = Modifier.height(10.dp))

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(240.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(Color(0xFF0F0E18))
              .border(1.dp, Color(0xFF28253D), RoundedCornerShape(10.dp))
              .padding(12.dp)
          ) {
            LazyColumn {
              item {
                Text(
                  text = systemPromptText,
                  color = Color(0xFFE2E8F0),
                  fontSize = 11.sp,
                  lineHeight = 16.sp
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          Button(
            onClick = {
              clipboard.setText(AnnotatedString(systemPromptText))
              Toast.makeText(context, "System Prompt copied to clipboard!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = ShortsRed),
            shape = RoundedCornerShape(10.dp)
          ) {
            Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Copy System Prompt", fontWeight = FontWeight.Bold)
          }
        }
      }
    }
  }
}
}

@Composable
private fun ToolCard(tool: AIToolInfo) {
  val context = LocalContext.current
  val clipboard = LocalClipboardManager.current

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .border(1.dp, StudioCardBorder, RoundedCornerShape(16.dp)),
    colors = CardDefaults.cardColors(containerColor = StudioSurface)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = tool.name,
              color = Color.White,
              fontWeight = FontWeight.Bold,
              fontSize = 17.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(NeonPurple.copy(alpha = 0.2f))
                .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
              Text(
                text = tool.badge,
                color = NeonPurpleLight,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
              )
            }
          }
          Text(
            text = tool.category,
            color = Color(0xFF94A3B8),
            fontSize = 12.sp
          )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          if (tool.isFreeTierAvailable) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(NeonGreen.copy(alpha = 0.15f))
                .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
              Text(text = "Free Tier", color = NeonGreen, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(6.dp))
          }
          Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = NeonAmber, modifier = Modifier.size(15.dp))
          Text(text = "${tool.rating}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = tool.description,
        color = Color(0xFFCBD5E1),
        fontSize = 13.sp,
        lineHeight = 18.sp
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Workflow Box
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(10.dp))
          .background(Color(0xFF13121F))
          .border(1.dp, Color(0xFF28253D), RoundedCornerShape(10.dp))
          .padding(10.dp)
      ) {
        Column {
          Text(
            text = "HOW TO USE IN YOUR SHORTS PIPELINE",
            color = NeonCyan,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = tool.recommendedWorkflow,
            color = Color(0xFFE2E8F0),
            fontSize = 12.sp,
            lineHeight = 16.sp
          )
          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "Pro Tip: ${tool.proTip}",
            color = Color(0xFFFFD54F),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Sample Setting / Preset with copy
      Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFF0F0E18),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF252335))
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = tool.samplePromptOrSetting,
            color = Color(0xFF94A3B8),
            fontSize = 11.sp,
            modifier = Modifier.weight(1f)
          )
          IconButton(
            onClick = {
              clipboard.setText(AnnotatedString(tool.samplePromptOrSetting))
              Toast.makeText(context, "Copied preset / prompt!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.size(24.dp)
          ) {
            Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy", tint = NeonCyan, modifier = Modifier.size(14.dp))
          }
        }
      }
    }
  }
}
