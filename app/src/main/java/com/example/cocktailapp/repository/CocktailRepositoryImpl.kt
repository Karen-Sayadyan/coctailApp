package com.example.cocktailapp.repository

import com.example.cocktailapp.cocktailModule.model.Cocktail
import com.example.cocktailapp.data.CocktailItemDao
import com.example.cocktailapp.di.network.NetworkModule
import com.example.cocktailapp.utils.mapToBd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CocktailRepositoryImpl @Inject constructor(
    private val networkManager: NetworkModule.CocktailApiService,
    private val dao: CocktailItemDao
) : CocktailRepository {
    override suspend fun getCocktail(): Cocktail? = withContext(Dispatchers.IO) {
        val cocktail = networkManager.getRandomCocktail().drinks?.firstOrNull()
        cocktail?.let { dao.insert(it.mapToBd()) }
        return@withContext cocktail
    }
}