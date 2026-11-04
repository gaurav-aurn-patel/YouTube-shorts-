package com.example.ui.screens

import android.widget.Toast
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.GuideData
import com.example.data.ScriptEngine
import com.example.model.HookTemplate
import com.example.model.TitleAnalysisResult
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HookLabScreen(modifier: Modifier = Modifier) {
  val context = LocalContext.current
  val clipboard = LocalClipboardManager.current

  var testTitleInput by remember { mutableStateOf("Why scientists are terrified of the ocean floor") }
  var analysisResult by remember { mutableStateOf<TitleAnalysisResult?>(ScriptEngine.analyzeTitle(testTitleInput)) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(StudioBackground)
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item {
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = "HOOK & TITLE LAB",
        color = Color(0xFF94A3B8),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Text(
        text = "Analyze & Optimize For Clicks",
        color = Color.White,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
      )
    }

    // TITLE ANALYZER INPUT
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .border(1.dp, StudioCardBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = StudioSurface)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "EVALUATE YOUR SHORTS TITLE",
            color = NeonCyan,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(8.dp))

          OutlinedTextField(
            value = testTitleInput,
            onValueChange = {
              testTitleInput = it
              if (it.isNotBlank()) {
                analysisResult = ScriptEngine.analyzeTitle(it)
              }
            },
            placeholder = { Text("Enter your title idea...", color = Color(0xFF64748B)) },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("title_evaluator_input"),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedContainerColor = StudioSurfaceVariant,
              unfocusedContainerColor = StudioSurfaceVariant,
              focusedBorderColor = ShortsRed,
              unfocusedBorderColor = StudioCardBorder,
              focusedTextColor = Color.White,
              unfocusedTextColor = Color.White
            )
          )

          Spacer(modifier = Modifier.height(10.dp))

          analysisResult?.let { res ->
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(8.dp))
                  .background(
                    if (res.viralScore >= 80) NeonGreen.copy(alpha = 0.2f)
                    else NeonAmber.copy(alpha = 0.2f)
                  )
                  .padding(horizontal = 10.dp, vertical = 5.dp)
              ) {
                Text(
                  text = "Viral Score: ${res.viralScore}/100",
                  color = if (res.viralScore >= 80) NeonGreen else NeonAmber,
                  fontWeight = FontWeight.Bold,
                  fontSize = 12.sp
                )
              }

              Text(
                text = "${res.originalTitle.length}/50 chars",
                color = if (res.originalTitle.length <= 48) NeonCyan else ShortsRed,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = "Length: ${res.lengthQuality}",
              color = Color(0xFF94A3B8),
              fontSize = 11.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
              text = "Emotional Triggers Detected:",
              color = Color(0xFFCBD5E1),
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            FlowRow(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              res.emotionalTriggers.forEach { trigger ->
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFF28233C))
                    .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                  Text(text = "🔥 $trigger", color = Color.White, fontSize = 11.sp)
                }
              }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
              text = "AI-OPTIMIZED VARIATIONS",
              color = NeonAmber,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            res.suggestions.forEach { altTitle ->
              Surface(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 3.dp)
                  .clip(RoundedCornerShape(8.dp))
                  .clickable {
                    testTitleInput = altTitle
                    analysisResult = ScriptEngine.analyzeTitle(altTitle)
                  },
                color = Color(0xFF161522),
                shape = RoundedCornerShape(8.dp)
              ) {
                Row(
                  modifier = Modifier.padding(10.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, tint = NeonAmber, modifier = Modifier.size(14.dp))
                  Spacer(modifier = Modifier.width(8.dp))
                  Text(
                    text = altTitle,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f)
                  )
                  IconButton(
                    onClick = {
                      clipboard.setText(AnnotatedString(altTitle))
                      Toast.makeText(context, "Copied title!", Toast.LENGTH_SHORT).show()
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
      }
    }

    // PROVEN VIRAL HOOK BLUEPRINTS
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "PROVEN VIRAL HOOK FORMULAS",
          color = Color(0xFF94A3B8),
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp
        )
        Text(
          text = "Tested on 10M+ Views",
          color = NeonGreen,
          fontSize = 11.sp,
          fontWeight = FontWeight.SemiBold
        )
      }
    }

    items(GuideData.viralHooks) { hook ->
      HookCard(hook = hook)
    }

    item {
      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

@Composable
private fun HookCard(hook: HookTemplate) {
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
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(NeonPurple.copy(alpha = 0.2f))
            .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
          Text(
            text = hook.category.uppercase(),
            color = NeonPurpleLight,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
          )
        }

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(NeonGreen.copy(alpha = 0.15f))
            .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
          Text(
            text = hook.retentionRate,
            color = NeonGreen,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = "Formula: ${hook.formula}",
        color = Color(0xFFE2E8F0),
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 18.sp
      )

      Spacer(modifier = Modifier.height(6.dp))

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(Color(0xFF13111C))
          .padding(8.dp)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "Example: \"${hook.example}\"",
            color = Color(0xFFFFD54F),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
          )
          IconButton(
            onClick = {
              clipboard.setText(AnnotatedString(hook.example))
              Toast.makeText(context, "Copied hook example!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.size(28.dp)
          ) {
            Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy Hook", tint = NeonCyan, modifier = Modifier.size(15.dp))
          }
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Default.Psychology, contentDescription = null, tint = Color(0xFF94A3B8), modifier = Modifier.size(13.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Trigger: ${hook.psychologicalTrigger}",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp
        )
      }
    }
  }
}
