package com.example.cocktailapp.mainModule.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.cocktailapp.FavoriteModule.screens.FavoriteScreen
import com.example.cocktailapp.FavoriteModule.viewModel.FavoriteViewModel
import com.example.cocktailapp.HistoryModule.screens.HistoryScreen
import com.example.cocktailapp.HistoryModule.viewModel.HistoryViewModel
import com.example.cocktailapp.cocktailModule.screens.CocktailScreen
import com.example.cocktailapp.cocktailModule.viewModel.CocktailViewModel
import com.example.cocktailapp.rootComponent.RootComponent


@Composable
fun MainAppScreen(root: RootComponent, viewModel: CocktailViewModel, historyViewModel: HistoryViewModel, favoriteViewModel: FavoriteViewModel) {
    val childStack by root.childStack.subscribeAsState()
    val currentChild = remember(childStack) {
        childStack.items.lastOrNull() ?: run {
            root.onTabSelected(RootComponent.Configuration.CocktailScreen)
            childStack.items.last()
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
// Табы
                NavigationBarItem(
                    selected = currentChild.configuration == RootComponent.Configuration.CocktailScreen,
                    onClick = { root.onTabSelected(RootComponent.Configuration.CocktailScreen) },
                    icon = { Icon(Icons.Default.Home, "Cocktails") },
                    label = { Text("Cocktails") }
                )
                NavigationBarItem(
                    selected = currentChild.configuration == RootComponent.Configuration.HistoryScreen,
                    onClick = { root.onTabSelected(RootComponent.Configuration.HistoryScreen) },
                    icon = { Icon(Icons.Default.List, "History") },
                    label = { Text("History") }
                )
                NavigationBarItem(
                    selected = currentChild.configuration == RootComponent.Configuration.FavoriteScreen,
                    onClick = { root.onTabSelected(RootComponent.Configuration.FavoriteScreen) },
                    icon = { Icon(Icons.Default.Favorite, "Favorites") },
                    label = { Text("Favorites") }
                )
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            when (val child = currentChild.instance) {
                is RootComponent.Child.CocktailScreen ->
                    CocktailScreen(viewModel, child.component)
                is RootComponent.Child.HistoryScreen ->
                    HistoryScreen(child.component, historyViewModel)
                is RootComponent.Child.FavoriteScreen ->
                    FavoriteScreen(child.component, viewModel = favoriteViewModel)
                else -> {
// Fallback на случай ошибки
                    root.onTabSelected(RootComponent.Configuration.CocktailScreen)
                }
            }
        }
    }
}
