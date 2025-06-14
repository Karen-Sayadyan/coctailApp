package com.example.cocktailapp.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // Обновил на SingletonComponent т.е один на все
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCocktailRepository(): CocktailRepository {
        return CocktailRepositoryImpl()
    }
}