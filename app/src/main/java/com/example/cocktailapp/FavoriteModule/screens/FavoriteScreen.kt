package com.example.cocktailapp.FavoriteModule.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cocktailapp.FavoriteModule.component.FavoriteComponent
import com.example.cocktailapp.FavoriteModule.viewModel.FavoriteViewModel
import com.example.cocktailapp.HistoryModule.screens.LoadingScreen
import com.example.cocktailapp.cocktailModule.screens.ErrorCard

@Composable
fun FavoriteScreen(
    component: FavoriteComponent,
    viewModel: FavoriteViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.cocktailState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Favorites",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            when (val currentState = state) {
                is FavoriteViewModel.CocktailState.Loading -> {
                    LoadingScreen()
                }

                is FavoriteViewModel.CocktailState.Empty -> {
                    EmptyFavoritesPlaceholder()
                }

                is FavoriteViewModel.CocktailState.Success -> {
                    val cocktails = currentState.cocktails.drinks
                    FavoritesListScreen(
                        cocktails = cocktails,
                        onRemove = { viewModel.deleteCocktailFromFavorites(it) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 16.dp)
                    )
                }

                is FavoriteViewModel.CocktailState.Error -> {
                    ErrorCard()
                }
            }
        }
    }
}

@Composable
fun EmptyFavoritesPlaceholder() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "Empty favorites",
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
        Text(
            text = "Нет избранных коктейлей",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}