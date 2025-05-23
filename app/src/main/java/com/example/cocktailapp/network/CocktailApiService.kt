package com.example.cocktailapp.network

import com.example.cocktailapp.model.DrinkResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "https://thecocktaildb.com/api/json/v1/1/"
private val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY // Установите уровень логирования
}

private val client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

// Создаем экземпляр Retrofit
private val retrofit = Retrofit.Builder()
    .client(client) // Используем созданный клиент
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface CocktailApiService {
    @GET("random.php")
    suspend fun getRandomDrink(): DrinkResponse
}

object CocktailApi {
    //val api: Any
    val retrofitService: CocktailApiService by lazy {
        retrofit.create(CocktailApiService::class.java)
    }
}
