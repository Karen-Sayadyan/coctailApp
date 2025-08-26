package com.example.cocktailapp.repository
import com.example.cocktailapp.cocktailModule.model.CocktailResponse
import com.example.cocktailapp.data.CocktailItemDao
import com.example.cocktailapp.utils.mapFromBd
import jakarta.inject.Inject

class FavoriteRepositoryImpl @Inject constructor (
    private val dao: CocktailItemDao
) : FavoriteRepository  {
    override suspend fun addToFavorite(id: Int): Boolean {
        return dao.adToFavorite(id) > 0
    }

    override suspend fun removeFromFavorite(id: Int): Boolean {
        return dao.removeFromFavorite(id) > 0
    }

    override suspend fun getAllFavorites():  CocktailResponse {
        val cocktails = dao.getAllFavorites()
        val responseCocktails = cocktails.map { it?.mapFromBd() }.mapNotNull { it }
        return CocktailResponse(responseCocktails)
    }
}