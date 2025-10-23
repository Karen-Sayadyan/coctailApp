package com.example.cocktailapp.HistoryModule.repository

import com.example.cocktailapp.cocktailModule.model.CocktailResponse

interface HistoryRepository {
    suspend fun getCocktailsHistory() : CocktailResponse
}