package com.example.cocktailapp.di.data

import android.content.Context
import androidx.room.Room
import com.example.cocktailapp.data.CocktailItemDao
import com.example.cocktailapp.data.CocktailDatabase
import com.example.cocktailapp.data.migration.MIGRATION_1_2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideCocktailDatabase(@ApplicationContext context: Context): CocktailDatabase {
        return Room.databaseBuilder(
            context,
            CocktailDatabase::class.java,
            "cocktail_database"
        ).addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideCocktailDao(database: CocktailDatabase): CocktailItemDao {
        return database.сockltailItemDao()
    }
}