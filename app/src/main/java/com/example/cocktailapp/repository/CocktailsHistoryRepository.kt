package com.example.cocktailapp.repository

import com.example.cocktailapp.cocktailModule.model.CocktailResponse

interface CocktailsHistoryRepository {
    suspend fun getCocktailsHistory() : CocktailResponse
}