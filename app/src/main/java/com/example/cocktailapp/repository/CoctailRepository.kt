package com.example.cocktailapp.repository

import com.example.cocktailapp.cocktailModule.model.Cocktail

interface CocktailRepository {
    suspend fun getCocktail(): Cocktail?
    suspend fun getCocktailById(id: Int): Cocktail?
}

