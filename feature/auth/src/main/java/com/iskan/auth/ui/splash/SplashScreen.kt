package com.iskan.auth.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iskan.ui.theme.BackgroundSplash
import com.iskan.ui.theme.PureYellow
import com.iskan.ui.theme.StrongBlue

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onNavigateToAuth: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // Handle navigation based on UI state
    LaunchedEffect(uiState) {
        when (uiState) {
            is SplashUiState.NavigateToAuth -> onNavigateToAuth()
            is SplashUiState.NavigateToHome -> onNavigateToHome()
            is SplashUiState.Loading -> {
                // Tetap di splash screen
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundSplash),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(250.dp)
                .offset(x = 100.dp, y = (-100).dp)
                .clip(RoundedCornerShape(100.dp))
                .background(StrongBlue)
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(250.dp)
                .offset(x = (-100).dp, y = 100.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(StrongBlue)
                .align(Alignment.BottomStart)
        )

        Box(
            modifier = Modifier
                .size(width = 140.dp, height = 60.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 20.dp,
                        bottomStart = 20.dp,
                        bottomEnd = 0.dp
                    )
                )
                .background(PureYellow),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Jello.",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
