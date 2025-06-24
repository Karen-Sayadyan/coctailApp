package com.example.cocktailapp.screens.mainScreen

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cocktailapp.viewModels.CocktailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailScreen(
    viewModel: CocktailViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.cocktailState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        PullToRefreshBox(
            isRefreshing = state is CocktailViewModel.CocktailState.Loading,
            onRefresh = viewModel::loadCocktail,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp)
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Назад")
                }

                when (state) {
                    is CocktailViewModel.CocktailState.Idle -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text("Нажмите кнопку для загрузки коктейля")
                        }
                    }
                    is CocktailViewModel.CocktailState.Loading -> {}
                    is CocktailViewModel.CocktailState.Error -> {
                        CardView(
                            state = state,
                            onRetry = { viewModel.loadCocktail() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                    is CocktailViewModel.CocktailState.Success -> {
                        CardView(
                            state = state,
                            onRetry = { viewModel.loadCocktail() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
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
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun SearchButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = "Получить случайный коктейль",
            style = MaterialTheme.typography.labelLarge
        )
    }
}