package com.example.cocktailapp.repository

import com.example.cocktailapp.cocktailModule.model.CocktailResponse

interface FavoriteRepository {
    suspend fun addToFavorite(id: Int): Boolean
    suspend fun removeFromFavorite(id: Int): Boolean
    suspend fun getAllFavorites(): CocktailResponse
}
