package com.example.cocktailapp.mainModule.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.cocktailapp.cocktailModule.screens.CocktailScreen
import com.example.cocktailapp.landingModule.screens.LandingScreen
import com.example.cocktailapp.rootComponent.RootComponent
import com.example.cocktailapp.appMaterialTheme.CocktailAppTheme
import com.example.cocktailapp.cocktailModule.viewModel.CocktailViewModel


@Composable
fun MainAppScreen(root: RootComponent, viewModel: CocktailViewModel,) {
    val childStack by root.childStack.subscribeAsState()
    CocktailAppTheme {
        Scaffold(
            content = { padding ->
                Children(
                    stack = childStack,
                ) { child ->
                    when (val instance = child.instance) {
                        is RootComponent.Child.LandingScreen -> LandingScreen(
                            modifier = Modifier.padding(padding),
                            component = instance.component
                        )

                        is RootComponent.Child.CocktailScreen -> CocktailScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(padding),
                            component = instance.component
                        )
                    }
                }
            }
        )
    }
}