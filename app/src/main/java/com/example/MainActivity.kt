package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.RecordVoiceOver
import androidx.compose.material.icons.outlined.RocketLaunch
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.GeneratedShortsScript
import com.example.ui.screens.GeneratorScreen
import com.example.ui.screens.GuideScreen
import com.example.ui.screens.HookLabScreen
import com.example.ui.screens.ProjectsScreen
import com.example.ui.screens.TeleprompterScreen
import com.example.ui.screens.ToolsScreen
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.ShortsCraftTheme
import com.example.ui.theme.ShortsRed
import com.example.ui.theme.StudioBackground
import com.example.ui.theme.StudioCardBorder
import com.example.ui.theme.StudioSurface
import com.example.ui.theme.StudioSurfaceVariant

enum class AppTab(
  val title: String,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector
) {
  GUIDE("Masterclass", Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
  GENERATOR("Script Studio", Icons.Filled.AutoAwesome, Icons.Outlined.AutoAwesome),
  HOOK_LAB("Hook Lab", Icons.Filled.Psychology, Icons.Outlined.Psychology),
  TELEPROMPTER("Coach", Icons.Filled.RecordVoiceOver, Icons.Outlined.RecordVoiceOver),
  LAUNCHPAD("Launchpad", Icons.Filled.RocketLaunch, Icons.Outlined.RocketLaunch)
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ShortsCraftTheme {
        MainAppContent()
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent() {
  var currentTab by remember { mutableStateOf(AppTab.GUIDE) }
  var teleprompterScript by remember { mutableStateOf<GeneratedShortsScript?>(null) }
  var launchpadSubTab by remember { mutableIntStateOf(0) } // 0: AI Tools Directory, 1: Projects & Checklist

  // Handle system back navigation to return to Guide
  BackHandler(enabled = currentTab != AppTab.GUIDE) {
    currentTab = AppTab.GUIDE
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    containerColor = StudioBackground,
    contentWindowInsets = WindowInsets.safeDrawing,
    topBar = {
      TopAppBar(
        title = {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(ShortsRed),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "ShortsCraft AI",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
              )
              Text(
                text = "YouTube Shorts Creator Studio",
                color = Color(0xFF94A3B8),
                fontSize = 11.sp
              )
            }
          }
        },
        actions = {
          Box(
            modifier = Modifier
              .padding(end = 12.dp)
              .clip(RoundedCornerShape(12.dp))
              .background(StudioSurfaceVariant)
              .border(1.dp, StudioCardBorder, RoundedCornerShape(12.dp))
              .padding(horizontal = 8.dp, vertical = 4.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(8.dp)
                  .clip(CircleShape)
                  .background(NeonGreen)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "9:16 Ready",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp
              )
            }
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = StudioSurface
        )
      )
    },
    bottomBar = {
      NavigationBar(
        containerColor = StudioSurface,
        contentColor = Color.White
      ) {
        AppTab.entries.forEach { tab ->
          val isSelected = currentTab == tab
          NavigationBarItem(
            selected = isSelected,
            onClick = { currentTab = tab },
            icon = {
              Icon(
                imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                contentDescription = tab.title
              )
            },
            label = {
              Text(
                text = tab.title,
                fontSize = 11.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
              )
            },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = Color.White,
              selectedTextColor = Color.White,
              indicatorColor = ShortsRed,
              unselectedIconColor = Color(0xFF94A3B8),
              unselectedTextColor = Color(0xFF94A3B8)
            ),
            modifier = Modifier.testTag("tab_${tab.name.lowercase()}")
          )
        }
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      Crossfade(targetState = currentTab, label = "tab_transition") { tab ->
        when (tab) {
          AppTab.GUIDE -> {
            GuideScreen(
              onNavigateToGenerator = { currentTab = AppTab.GENERATOR },
              onNavigateToTools = {
                currentTab = AppTab.LAUNCHPAD
                launchpadSubTab = 0
              }
            )
          }

          AppTab.GENERATOR -> {
            GeneratorScreen(
              onSendToTeleprompter = { script ->
                teleprompterScript = script
                currentTab = AppTab.TELEPROMPTER
              }
            )
          }

          AppTab.HOOK_LAB -> {
            HookLabScreen()
          }

          AppTab.TELEPROMPTER -> {
            TeleprompterScreen(script = teleprompterScript)
          }

          AppTab.LAUNCHPAD -> {
            Column(modifier = Modifier.fillMaxSize()) {
              // Sub Tab Switcher between AI Tools Directory and Saved Projects
              Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(StudioSurface)
                    .border(1.dp, StudioCardBorder, RoundedCornerShape(12.dp))
                    .padding(4.dp)
                ) {
                  Box(
                    modifier = Modifier
                      .weight(1f)
                      .clip(RoundedCornerShape(8.dp))
                      .background(if (launchpadSubTab == 0) ShortsRed else Color.Transparent)
                      .clickable { launchpadSubTab = 0 }
                      .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                  ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                      Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null,
                        tint = if (launchpadSubTab == 0) Color.White else Color(0xFF94A3B8),
                        modifier = Modifier.size(14.dp)
                      )
                      Spacer(modifier = Modifier.width(6.dp))
                      Text(
                        text = "AI Tools Directory",
                        color = if (launchpadSubTab == 0) Color.White else Color(0xFF94A3B8),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                      )
                    }
                  }

                  Box(
                    modifier = Modifier
                      .weight(1f)
                      .clip(RoundedCornerShape(8.dp))
                      .background(if (launchpadSubTab == 1) ShortsRed else Color.Transparent)
                      .clickable { launchpadSubTab = 1 }
                      .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                  ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                      Icon(
                        imageVector = Icons.Default.Checklist,
                        contentDescription = null,
                        tint = if (launchpadSubTab == 1) Color.White else Color(0xFF94A3B8),
                        modifier = Modifier.size(14.dp)
                      )
                      Spacer(modifier = Modifier.width(6.dp))
                      Text(
                        text = "Projects & Checklist",
                        color = if (launchpadSubTab == 1) Color.White else Color(0xFF94A3B8),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                      )
                    }
                  }
                }
              }

              if (launchpadSubTab == 0) {
                ToolsScreen()
              } else {
                ProjectsScreen(
                  onOpenTeleprompter = { script ->
                    teleprompterScript = script
                    currentTab = AppTab.TELEPROMPTER
                  },
                  onNavigateToGenerator = { currentTab = AppTab.GENERATOR }
                )
              }
            }
          }
        }
      }
    }
  }
}
