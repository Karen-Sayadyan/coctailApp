package com.example.cocktailapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cocktailapp.data.entity.CocktailItem
import kotlinx.coroutines.flow.Flow


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

    @Query("UPDATE cocktails SET isFavorite = 1 WHERE idDrink = :id")
    suspend fun adToFavorite(id: Int) : Int

    @Query("UPDATE cocktails SET isFavorite = 0 WHERE idDrink = :id")
    suspend fun removeFromFavorite(id: Int) : Int

    @Query("SELECT * from cocktails WHERE isFavorite = 1 ")
    fun getAllFavorites(): Flow<List<CocktailItem>>
}