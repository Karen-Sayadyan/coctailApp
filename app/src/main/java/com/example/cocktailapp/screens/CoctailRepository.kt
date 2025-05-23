package com.example.cocktailapp.screens

import com.example.cocktailapp.model.DrinkResponse
import com.example.cocktailapp.network.CocktailApi

class CoctailRepository {  // репозиторием в андроид принято называть место откуда мы что то берем
    // без ращницы из сети, из бд откуда угодно
    val networkManager = CocktailApi.retrofitService

    suspend fun getCocktail() : DrinkResponse {
        return networkManager.getRandomDrink() // делаем запрос в котором отдаем DrinkResponse
    }
}