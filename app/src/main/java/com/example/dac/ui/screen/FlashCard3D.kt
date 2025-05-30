package com.example.dac.ui.screen

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FlashCard3D(
    isBack: Boolean,
    word: String,
    ipa: String,
    part: String,
    contextEnglish: String,
    level: String,
    meaning: String,
    contextVietnamese: String,
    onFlip: () -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (isBack) 180f else 0f,
        animationSpec = tween(durationMillis = 350),
        label = "rotation"
    )
    val context = LocalContext.current
    val speaker = remember { FlashCardSpeaker(context) }
    DisposableEffect(Unit) {
        onDispose { speaker.shutdown() }
    }

    Box(
        modifier = Modifier
            .width(320.dp)
            .height(200.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12 * density
            }
            .background(Color(0xFFEDE7F6), shape = MaterialTheme.shapes.medium)
            .clickable { onFlip() },
        contentAlignment = Alignment.Center
    ) {
        if (rotation <= 90f) {
            FrontCardContent(
                word = word,
                ipa = ipa,
                part = part,
                contextEnglish = contextEnglish,
                level = level,
                modifier = Modifier.alpha(1f - (rotation / 90f)),
                onSpeak = { speaker.speak(word) },
                onSpeakContext = { speaker.speak(contextEnglish) }
            )
        } else {
            BackCardContent(
                meaning = meaning,
                contextVietnamese = contextVietnamese,
                modifier = Modifier
                    .graphicsLayer { rotationY = 180f }
                    .alpha((rotation - 90f) / 90f)
            )
        }
    }
}

@Composable
private fun FrontCardContent(
    word: String,
    ipa: String,
    part: String,
    contextEnglish: String,
    level: String,
    modifier: Modifier = Modifier,
    onSpeak: () -> Unit,
    onSpeakContext: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(word, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.VolumeUp,
                contentDescription = "Speak word",
                tint = Color(0xFF6A1B9A),
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onSpeak() }
            )
        }
        Text(ipa, color = Color.Gray)
        Text("($part)", color = Color.Gray)
        Spacer(Modifier.height(8.dp))
        Text("Level: $level", color = Color(0xFF6A1B9A))
        Spacer(Modifier.height(8.dp))
        Text("Context (En):", fontWeight = FontWeight.Bold)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(contextEnglish, modifier = Modifier.weight(1f))
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.VolumeUp,
                contentDescription = "Speak context",
                tint = Color(0xFF6A1B9A),
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onSpeakContext() }
            )
        }
    }
}

@Composable
private fun BackCardContent(
    meaning: String,
    contextVietnamese: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(16.dp)
    ) {
        Text("Nghĩa:", fontWeight = FontWeight.Bold)
        Text(meaning)
        Spacer(Modifier.height(8.dp))
        Text("Ngữ cảnh (Việt):", fontWeight = FontWeight.Bold)
        Text(contextVietnamese)
    }
}