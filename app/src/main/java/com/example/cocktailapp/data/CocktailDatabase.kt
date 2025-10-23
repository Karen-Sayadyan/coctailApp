package com.example.cocktailapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktailapp.data.entity.CocktailItem

@Database(entities = [CocktailItem::class], version = 4, exportSchema = true)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun сockltailItemDao() : CocktailItemDao
}


