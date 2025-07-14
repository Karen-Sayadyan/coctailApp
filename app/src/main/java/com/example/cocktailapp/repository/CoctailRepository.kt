package com.example.cocktailapp.repository

import com.example.cocktailapp.cocktailModule.model.CocktailResponse

interface CocktailRepository {
    suspend fun getCocktail(): CocktailResponse
}
