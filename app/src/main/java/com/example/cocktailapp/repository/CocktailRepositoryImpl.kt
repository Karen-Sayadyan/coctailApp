package com.example.cocktailapp.repository

import com.example.cocktailapp.model.DrinkResponse
import com.example.cocktailapp.network.CocktailApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CocktailRepositoryImpl @Inject constructor(
    private val networkManager: CocktailApiService
) : CocktailRepository {
    override suspend fun getCocktail(): DrinkResponse = withContext(Dispatchers.IO) {
        networkManager.getRandomDrink()
    }
}