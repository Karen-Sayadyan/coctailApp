package com.example.cocktailapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails")
data class CocktailItem(
    @PrimaryKey val idDrink: Int,
    val strDrink: String?,
    val strAlcoholic: String?,
    val strInstructions: String?,
    val image: String?,
    val isFavorite: Boolean,
    val strCategory: String?
)