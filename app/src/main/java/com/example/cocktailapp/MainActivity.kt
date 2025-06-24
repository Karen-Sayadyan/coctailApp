package com.example.cocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.cocktailapp.screens.mainScreen.CocktailScreen
import com.example.cocktailapp.screens.mainScreen.LandingScreen
import com.example.cocktailapp.ui.theme.CocktailAppTheme
import com.example.cocktailapp.viewModels.CocktailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: CocktailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CocktailAppTheme {
                Scaffold(
                    content = { padding ->
                        // Определяем текущий экран на основе состояния ViewModel
                        val showLandings by viewModel.showLandings.collectAsState()

                        if (showLandings) {
                            LandingScreen(
                                onStartClick = { viewModel.loadCocktail() },
                                modifier = Modifier.padding(padding),
                                viewModel = viewModel
                            )
                        } else {
                            CocktailScreen(
                                viewModel = viewModel,
                                onBackClick = { viewModel.showLandings() }, // Переключаем обратно на лендинги
                                modifier = Modifier.padding(padding)
                            )
                        }
                    }
                )
            }
        }
    }
}