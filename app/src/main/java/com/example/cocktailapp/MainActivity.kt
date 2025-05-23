package com.example.cocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cocktailapp.screens.CocktailViewModel
import com.example.cocktailapp.screens.DrinkScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = CocktailViewModel()
        setContent {
            DrinkScreen(viewModel)
        }
    }
}



