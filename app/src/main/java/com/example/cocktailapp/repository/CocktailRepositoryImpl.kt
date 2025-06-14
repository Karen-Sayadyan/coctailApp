package com.example.cocktailapp.repository

import com.example.cocktailapp.model.DrinkResponse
import com.example.cocktailapp.network.CocktailApi

class CocktailRepositoryImpl: CocktailRepository {
    private val networkManager = CocktailApi.retrofitService

    override suspend fun getCocktail(): DrinkResponse {
        return networkManager.getRandomDrink() // делаем запрос, в результате получаем DrinkResponse
    }
}