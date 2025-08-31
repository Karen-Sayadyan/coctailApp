package com.example.cocktailapp.cocktailModule.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
       viewModel.isCocktailFavorite()
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
                            cocktail = cocktail,
                            onClick = { id ->
                                id?.let {viewModel.addToFavorite(it) }
                            }
                        )
                    }
                }
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
        enabled = !isLoading
    ) {
        Text(
            text = if (isLoading) "Загрузка..." else "Получить случайный коктейль",
            style = MaterialTheme.typography.labelLarge
        )
    }
}