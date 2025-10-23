package com.example.cocktailapp.FavoriteModule.repository

import com.example.cocktailapp.cocktailModule.model.CocktailResponse
import com.example.cocktailapp.data.CocktailItemDao
import com.example.cocktailapp.utils.mapFromBd
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: CocktailItemDao
) : FavoriteRepository {
    override suspend fun addToFavorite(id: Int): Boolean {
        val timestamp = System.currentTimeMillis()
        return dao.adToFavorite(id, timestamp) > 0
    }

    override suspend fun removeFromFavorite(id: Int): Boolean {
        return dao.removeFromFavorite(id) > 0
    }

    override fun getAllFavorites(): Flow<CocktailResponse> {
        return dao.getAllFavorites().map { listFromDb ->
            val responseCocktails = listFromDb.map { it.mapFromBd() }
            CocktailResponse(responseCocktails)
        }
    }
}