package com.example.cocktailapp.repository

import com.example.cocktailapp.cocktailModule.model.CocktailResponse
import com.example.cocktailapp.network.CocktailApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CocktailRepositoryImpl @Inject constructor(
    private val networkManager: CocktailApiService
) : CocktailRepository {
    override suspend fun getCocktail(): CocktailResponse = withContext(Dispatchers.IO) {
        networkManager.getRandomCocktail()
    }
}