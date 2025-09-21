package com.example.cocktailapp.mainRepository

import com.example.cocktailapp.FavoriteModule.repository.FavoriteRepository
import com.example.cocktailapp.FavoriteModule.repository.FavoriteRepositoryImpl
import com.example.cocktailapp.HistoryModule.repository.HistoryRepository
import com.example.cocktailapp.HistoryModule.repository.HistoryRepositoryImpl
import com.example.cocktailapp.cocktailModule.repository.CocktailRepository
import com.example.cocktailapp.cocktailModule.repository.CocktailRepositoryImpl
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
