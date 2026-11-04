package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.ShortsRed
import com.example.ui.theme.StudioSurfaceVariant

@Composable
fun SafeZoneSimulator(
  modifier: Modifier = Modifier,
  sampleCaption: String = "SHOCKING SECRET REVEALED"
) {
  // A 9:16 aspect ratio smartphone simulator showing YouTube's native UI overlay zones
  Surface(
    modifier = modifier
      .width(260.dp)
      .height(460.dp)
      .clip(RoundedCornerShape(20.dp))
      .border(2.dp, Color(0xFF332F4A), RoundedCornerShape(20.dp)),
    color = Color(0xFF100F17)
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      // Background gradient representing video frame
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              listOf(
                Color(0xFF1E1B2E),
                Color(0xFF2A1B3D),
                Color(0xFF171324)
              )
            )
          )
      )

      // TOP DANGER ZONE (YouTube top bar / sound selector)
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(55.dp)
          .background(Color(0x55FF0033))
          .border(1.dp, Color(0x88FF0033))
          .padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "⚠️ TOP DEAD ZONE (Search & Audio Bar)",
          color = Color(0xFFFFB3BA),
          fontSize = 9.sp,
          fontWeight = FontWeight.Bold,
          textAlign = TextAlign.Center
        )
      }

      // CENTER SAFE ZONE (Ideal for Subtitles & Key Visual Focus)
      Box(
        modifier = Modifier
          .align(Alignment.Center)
          .fillMaxWidth(0.78f)
          .height(130.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(Color(0x2210B981))
          .border(1.5.dp, NeonGreen, RoundedCornerShape(12.dp))
          .padding(8.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = "✅ 100% SAFE ZONE",
            color = NeonGreen,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Place All Captions Here",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 10.sp
          )
          Spacer(modifier = Modifier.height(8.dp))
          // Mock animated subtitle
          Box(
            modifier = Modifier
              .background(Color.Black.copy(alpha = 0.85f), RoundedCornerShape(6.dp))
              .padding(horizontal = 10.dp, vertical = 4.dp)
          ) {
            Text(
              text = sampleCaption,
              color = Color(0xFFFFEB3B),
              fontWeight = FontWeight.Black,
              fontSize = 12.sp,
              textAlign = TextAlign.Center
            )
          }
        }
      }

      // RIGHT SIDE BUTTON OVERLAYS (YouTube Actions)
      Column(
        modifier = Modifier
          .align(Alignment.CenterEnd)
          .padding(end = 6.dp, bottom = 80.dp)
          .width(42.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        MockYouTubeAction(icon = Icons.Default.ThumbUp, label = "24K")
        MockYouTubeAction(icon = Icons.Default.ThumbDown, label = "Dislike")
        MockYouTubeAction(icon = Icons.Default.Comment, label = "482")
        MockYouTubeAction(icon = Icons.Default.Share, label = "Share")
        MockYouTubeAction(icon = Icons.Default.Bookmark, label = "Save")
        // Sound disc
        Box(
          modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(Color.Black)
            .border(1.dp, Color.White, CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.MusicNote,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(14.dp)
          )
        }
      }

      // BOTTOM DANGER ZONE (Title, Channel, Subscribe button)
      Box(
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .fillMaxWidth()
          .height(105.dp)
          .background(Color(0x66FF0033))
          .border(1.dp, Color(0x88FF0033))
          .padding(8.dp)
      ) {
        Column(modifier = Modifier.fillMaxWidth()) {
          Text(
            text = "⚠️ BOTTOM OVERLAY (Title & Channel Details)",
            color = Color(0xFFFFB3BA),
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(4.dp))
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(ShortsRed)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "@CreatorStudio",
              color = Color.White,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(6.dp))
            Box(
              modifier = Modifier
                .background(Color.White, RoundedCornerShape(10.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
              Text(text = "Subscribe", color = Color.Black, fontSize = 9.sp, fontWeight = FontWeight.Bold)
            }
          }
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Scientists made this discovery in 2026... #Shorts #AI",
            color = Color.White.copy(alpha = 0.9f),
            fontSize = 10.sp,
            maxLines = 2
          )
        }
      }
    }
  }
}

@Composable
private fun MockYouTubeAction(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Icon(
      imageVector = icon,
      contentDescription = label,
      tint = Color.White,
      modifier = Modifier.size(20.dp)
    )
    Text(
      text = label,
      color = Color.White,
      fontSize = 8.sp,
      fontWeight = FontWeight.Medium
    )
  }
}
