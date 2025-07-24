package com.example.cocktailapp.landingModule.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cocktailapp.landingModule.events.LandingEvents
import com.example.cocktailapp.landingModule.model.LandingScreenRepository
import com.example.cocktailapp.landingModule.component.LandingComponent


@Composable
fun LandingScreen(
    component: LandingComponent,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { LandingScreenRepository.landingScreens.size }) // вот это нужно убрать во вьюмодель
                                                                                                    // эта логика должна быть во вьюмодели)
    var currentPage by remember { mutableIntStateOf(0) }
    val isDarkTheme = isSystemInDarkTheme() // Определяем текущую тему
    val indicatorColor = if (isDarkTheme) Color.White else Color.Black
    val buttonBackgroundColor = if (isDarkTheme) Color.White else Color.Black
    val buttonTextColor = if (isDarkTheme) Color.Black else Color.White

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            currentPage = page
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            key = { page -> LandingScreenRepository.landingScreens[page].imageRes }
        ) { page ->
            Image(
                painter = painterResource(id = LandingScreenRepository.landingScreens[page].imageRes),
                contentDescription = "Landing image $page",
                modifier = Modifier
                    .fillMaxSize(),
               contentScale = ContentScale.Fit,
            )
        }

        // Индикатор страниц с адаптивным цветом
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(LandingScreenRepository.landingScreens.size) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (currentPage == index)
                                indicatorColor
                            else
                                indicatorColor.copy(alpha = 0.5f)
                        )
                )
            }
        }

        // Кнопка перехода к коктейлям
        Button(
            onClick = { component.toCocktailScreen(event = LandingEvents.GoToCocktailHistory) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonBackgroundColor,
                contentColor = buttonTextColor
            )
        ) {
            Text("Начать", style = MaterialTheme.typography.labelLarge)
        }
    }
}