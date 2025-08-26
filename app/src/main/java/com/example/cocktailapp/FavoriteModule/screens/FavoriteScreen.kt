package com.example.cocktailapp.FavoriteModule.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    val state by viewModel.cocktailState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFavorite()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Favorites",
                style = MaterialTheme.typography.displayMedium,
            )
            when (state) {
                is FavoriteViewModel.CocktailState.Loading -> {
                    LoadingScreen()
                }

                is FavoriteViewModel.CocktailState.Empty -> {
                    ErrorCard()
                }

                is FavoriteViewModel.CocktailState.Success -> {
                    val cocktails =
                        (state as FavoriteViewModel.CocktailState.Success).cocktails.drinks
                    FavoritesListScreen(
                        cocktails = cocktails,
                        onRemove = {
                            viewModel.deleteCocktailFromFavorites(it)},
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 16.dp)
                    )
                }
            }
        }
    }
}