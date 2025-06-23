package com.example.cocktailapp.repository

import com.example.cocktailapp.model.DrinkResponse

interface CocktailRepository {
    suspend fun getCocktail(): DrinkResponse
}
