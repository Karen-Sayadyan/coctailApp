package com.example.cocktailapp.FavoriteModule.repository

import com.example.cocktailapp.cocktailModule.model.CocktailResponse
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun addToFavorite(id: Int): Boolean
    suspend fun removeFromFavorite(id: Int): Boolean
    fun getAllFavorites(): Flow<CocktailResponse>
}