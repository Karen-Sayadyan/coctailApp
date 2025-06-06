package com.example.cocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cocktailapp.repository.CocktailRepository
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

        // Инициализируем ViewModelProvider
        viewModel = ViewModelProvider(this, CocktailViewModelFactory(cocktailRepository)).get(
            CocktailViewModel::class.java
        )

        setContent {
            DrinkScreen(viewModel)
        }
    }
}

class CocktailViewModelFactory(private val cocktailRepository: CocktailRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CocktailViewModel(cocktailRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}