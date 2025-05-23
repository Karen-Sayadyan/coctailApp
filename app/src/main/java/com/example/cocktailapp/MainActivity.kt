package com.example.cocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cocktailapp.repository.CocktailRepositoryImpl
import com.example.cocktailapp.viewModels.CocktailViewModel
import com.example.cocktailapp.screens.DrinkScreen


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: CocktailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Создаем экземпляр CocktailRepository
        val cocktailRepository = CocktailRepositoryImpl()

        // Создаем экземпляр CocktailViewModel, передавая в него репозиторий
        viewModel = CocktailViewModel(cocktailRepository)

        setContent {
            DrinkScreen(viewModel)
        }
    }
}
