package com.example.cocktailapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CocktailItem::class], version = 1, exportSchema = false)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun сockltailItemDao() : CocktailItemDao
}


