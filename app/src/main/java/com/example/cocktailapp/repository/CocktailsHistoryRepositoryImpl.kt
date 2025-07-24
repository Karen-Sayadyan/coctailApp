package com.example.cocktailapp.repository

import com.example.cocktailapp.cocktailModule.model.CocktailResponse
import com.example.cocktailapp.data.CocktailItemDao
import com.example.cocktailapp.utils.mapFromBd
import jakarta.inject.Inject

class CocktailsHistoryRepositoryImpl @Inject constructor (
    private val dao: CocktailItemDao
) : CocktailsHistoryRepository {
    override suspend fun getCocktailsHistory(): CocktailResponse {
        val cocktails = dao.getAllCocktailItems()
        val responseCocktails = cocktails.map { it.mapFromBd() }
        return CocktailResponse(responseCocktails)
    }
}