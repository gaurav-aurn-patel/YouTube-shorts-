package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.data.ProjectRepository
import com.example.model.GeneratedShortsScript
import com.example.model.ProjectStatus
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.ShortsRed
import com.example.ui.theme.StudioBackground
import com.example.ui.theme.StudioCardBorder
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.StudioSurfaceVariant

@Composable
fun ProjectsScreen(
  onOpenTeleprompter: (GeneratedShortsScript) -> Unit,
  onNavigateToGenerator: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val clipboard = LocalClipboardManager.current
  val projects by ProjectRepository.projects.collectAsState()
  val checklist by ProjectRepository.checklist.collectAsState()

  val checkedCount = checklist.count { it.isChecked }
  val progress = if (checklist.isNotEmpty()) checkedCount.toFloat() / checklist.size.toFloat() else 0f

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
        text = "SHORTS LAUNCHPAD & DRAFTS",
        color = Color(0xFF94A3B8),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Text(
        text = "Projects & Pre-Launch Checklist",
        color = Color.White,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
      )
    }

    // PRE-LAUNCH YOUTUBE CHECKLIST
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
            Column {
              Text(
                text = "PRE-UPLOAD YOUTUBE CHECKLIST",
                color = NeonCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "$checkedCount of ${checklist.size} Verified",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
            }

            IconButton(
              onClick = { ProjectRepository.resetChecklist() },
              modifier = Modifier.size(32.dp)
            ) {
              Icon(imageVector = Icons.Default.Refresh, contentDescription = "Reset Checklist", tint = Color(0xFF94A3B8), modifier = Modifier.size(18.dp))
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
              .fillMaxWidth()
              .height(8.dp)
              .clip(RoundedCornerShape(4.dp)),
            color = if (progress >= 1f) NeonGreen else ShortsRed,
            trackColor = Color(0xFF1B1927),
          )

          Spacer(modifier = Modifier.height(14.dp))

          checklist.forEach { item ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { ProjectRepository.toggleChecklistItem(item.id) }
                .padding(vertical = 2.dp),
              verticalAlignment = Alignment.Top
            ) {
              Checkbox(
                checked = item.isChecked,
                onCheckedChange = { ProjectRepository.toggleChecklistItem(item.id) },
                colors = CheckboxDefaults.colors(
                  checkedColor = NeonGreen,
                  uncheckedColor = Color(0xFF64748B),
                  checkmarkColor = Color.Black
                ),
                modifier = Modifier.size(24.dp).padding(top = 2.dp)
              )
              Spacer(modifier = Modifier.width(10.dp))
              Column {
                Text(
                  text = item.title,
                  color = if (item.isChecked) Color(0xFF94A3B8) else Color.White,
                  fontSize = 13.sp,
                  fontWeight = FontWeight.SemiBold
                )
                Text(
                  text = item.description,
                  color = Color(0xFF64748B),
                  fontSize = 11.sp,
                  lineHeight = 15.sp
                )
              }
            }
          }
        }
      }
    }

    // SAVED SCRIPTS HEADER
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "SAVED SHORTS PIPELINE (${projects.size})",
          color = Color(0xFF94A3B8),
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp
        )

        Button(
          onClick = onNavigateToGenerator,
          colors = ButtonDefaults.buttonColors(containerColor = StudioSurfaceVariant),
          shape = RoundedCornerShape(8.dp)
        ) {
          Text(text = "+ New Script", color = NeonCyan, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
      }
    }

    if (projects.isEmpty()) {
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(StudioSurface),
          contentAlignment = Alignment.Center
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "No saved scripts yet", color = Color(0xFF94A3B8), fontSize = 14.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Button(
              onClick = onNavigateToGenerator,
              colors = ButtonDefaults.buttonColors(containerColor = ShortsRed)
            ) {
              Text(text = "Generate Script", fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }

    items(projects) { project ->
      ProjectCard(
        project = project,
        onOpenTeleprompter = { onOpenTeleprompter(project) },
        onDelete = { ProjectRepository.deleteProject(project.id) },
        onUpdateStatus = { newStatus -> ProjectRepository.updateStatus(project.id, newStatus) }
      )
    }

    item {
      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

@Composable
private fun ProjectCard(
  project: GeneratedShortsScript,
  onOpenTeleprompter: () -> Unit,
  onDelete: () -> Unit,
  onUpdateStatus: (ProjectStatus) -> Unit
) {
  val context = LocalContext.current
  val clipboard = LocalClipboardManager.current

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
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(ShortsRed.copy(alpha = 0.2f))
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text(
              text = "${project.niche.emoji} ${project.niche.label}",
              color = Color(0xFFFFB3BA),
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
          }
          Spacer(modifier = Modifier.width(6.dp))
          Text(text = "${project.duration.seconds}s", color = NeonCyan, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
          IconButton(
            onClick = {
              val fullText = buildString {
                appendLine("TITLE: ${project.title}")
                appendLine("HOOK: ${project.hookSpoken}")
                project.scenes.forEach { s ->
                  appendLine("[${s.timecode}] ${s.voiceover}")
                  appendLine("PROMPT: ${s.visualPrompt}")
                }
                appendLine("LOOP: ${project.loopEnding}")
              }
              clipboard.setText(AnnotatedString(fullText))
              Toast.makeText(context, "Copied project script!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.size(28.dp)
          ) {
            Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy", tint = Color(0xFF94A3B8), modifier = Modifier.size(16.dp))
          }

          IconButton(
            onClick = onDelete,
            modifier = Modifier.size(28.dp)
          ) {
            Icon(imageVector = Icons.Default.DeleteOutline, contentDescription = "Delete", tint = Color(0xFFEF4444), modifier = Modifier.size(16.dp))
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = project.title,
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
      )

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = "Hook: \"${project.hookSpoken}\"",
        color = Color(0xFFCBD5E1),
        fontSize = 12.sp,
        lineHeight = 16.sp,
        maxLines = 2
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Status Pipeline Selector
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        ProjectStatus.entries.forEach { status ->
          val isSelected = project.status == status
          Box(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(6.dp))
              .background(
                if (isSelected) {
                  when (status) {
                    ProjectStatus.IDEA -> NeonAmber
                    ProjectStatus.SCRIPTED -> NeonPurple
                    ProjectStatus.VOICE_DONE -> NeonCyan
                    ProjectStatus.VIDEO_DONE -> ShortsRed
                    ProjectStatus.PUBLISHED -> NeonGreen
                  }
                } else Color(0xFF1A1826)
              )
              .clickable { onUpdateStatus(status) }
              .padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = status.label,
              color = if (isSelected) Color.White else Color(0xFF94A3B8),
              fontSize = 9.sp,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Button(
        onClick = onOpenTeleprompter,
        colors = ButtonDefaults.buttonColors(containerColor = StudioSurfaceVariant),
        modifier = Modifier.fillMaxWidth().height(40.dp),
        shape = RoundedCornerShape(8.dp)
      ) {
        Icon(imageVector = Icons.Default.RecordVoiceOver, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = "Practice Voiceover in Teleprompter", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
      }
    }
  }
}
