package com.example.cocktailapp.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindCocktailRepository(impl: CocktailRepositoryImpl): CocktailRepository

    @Binds
    @Singleton
    fun bindCocktailsHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository

    @Binds
    @Singleton
    fun bindFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository
}
