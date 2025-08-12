package com.example.cocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.arkivanov.decompose.retainedComponent
import com.example.cocktailapp.HistoryModule.viewModel.HistoryViewModel
import com.example.cocktailapp.rootComponent.RootComponent
import com.example.cocktailapp.mainModule.screens.MainAppScreen
import com.example.cocktailapp.cocktailModule.viewModel.CocktailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: CocktailViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val root = retainedComponent {
            RootComponent(it)
        }
        setContent {
            MainAppScreen(root = root, viewModel = viewModel, historyViewModel = historyViewModel)
        }
    }
}
