package com.example.cocktailapp.HistoryModule.repository

import com.example.cocktailapp.cocktailModule.model.CocktailResponse
import com.example.cocktailapp.data.CocktailItemDao
import com.example.cocktailapp.utils.mapFromBd
import jakarta.inject.Inject

class HistoryRepositoryImpl @Inject constructor (
    private val dao: CocktailItemDao
) : HistoryRepository {
    override suspend fun getCocktailsHistory(): CocktailResponse {
        val cocktails = dao.getAllCocktailItems()
        val responseCocktails = cocktails.map { it.mapFromBd() }
        return CocktailResponse(responseCocktails)
    }
}