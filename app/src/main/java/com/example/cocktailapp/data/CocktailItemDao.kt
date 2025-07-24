package com.example.cocktailapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface CocktailItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cocktailItem: CocktailItem)

    @Update
    suspend fun update(cocktailItem: CocktailItem)

    @Delete
    suspend fun delete(cocktailItem: CocktailItem)

    @Query("SELECT * from cocktails WHERE idDrink = :id")
    fun getCocktailById(id: Int): CocktailItem

    @Query("SELECT * from cocktails")
    fun getAllCocktailItems(): List<CocktailItem>
}
