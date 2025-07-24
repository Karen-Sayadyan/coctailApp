package com.example.cocktailapp.cocktailModule.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cocktailapp.cocktailModule.events.CocktailEvents
import com.example.cocktailapp.cocktailModule.component.CocktailComponent
import com.example.cocktailapp.cocktailModule.viewModel.CocktailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailScreen(
    viewModel: CocktailViewModel,
    component: CocktailComponent,
    modifier: Modifier = Modifier
) {
    val state by viewModel.cocktailState.collectAsState()
    var isSwiping by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadCocktail()
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        PullToRefreshBox(
            isRefreshing = state is CocktailViewModel.CocktailState.LoadingPullToRefresh,
            onRefresh = {
                isSwiping = true
                viewModel.loadCocktailFromPullToRefresh()
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp)
            ) {
                IconButton(
                    onClick = {
                        component.toLandingScreen(event = CocktailEvents.BackToLanding)
                    },
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }

                when (state) {

                    is CocktailViewModel.CocktailState.LoadingButton -> {
                        LoadingCardButton()
                    }

                    is CocktailViewModel.CocktailState.LoadingPullToRefresh -> {
                        LoadingCardPullToRefresh()
                    }

                    is CocktailViewModel.CocktailState.Error -> {
                        ErrorCard()
                    }

                    is CocktailViewModel.CocktailState.Success -> {
                        val cocktail = (state as CocktailViewModel.CocktailState.Success).cocktail
                        SuccessCard(
                            cocktail = cocktail
                        )
                    }
                }

//                 Сброс isSwiping после завершения обновления
                LaunchedEffect(state) {
                    if (state !is CocktailViewModel.CocktailState.LoadingPullToRefresh) {
                        isSwiping = false
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        ) {
            SearchButton(
                onClick = { viewModel.loadCocktail() },
                isLoading = state is CocktailViewModel.CocktailState.LoadingButton || isSwiping,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun SearchButton(
    onClick: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isLoading) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            else MaterialTheme.colorScheme.primary,
            contentColor = if (isLoading) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            else MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(12.dp),
        enabled = !isLoading
    ) {
        Text(
            text = if (isLoading) "Загрузка..." else "Получить случайный коктейль",
            style = MaterialTheme.typography.labelLarge
        )
    }
}