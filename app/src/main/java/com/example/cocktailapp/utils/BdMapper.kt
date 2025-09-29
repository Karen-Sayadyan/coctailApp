package com.example.cocktailapp.utils

import com.example.cocktailapp.cocktailModule.model.Cocktail
import com.example.cocktailapp.data.entity.CocktailItem

fun Cocktail.mapToBd(): CocktailItem {
    return CocktailItem(
        idDrink = this.idDrink?.toInt() ?: 1,
        strDrink = this.strDrink,
        strAlcoholic = this.strAlcoholic,
        strInstructions = this.strInstructions,
        image = this.strDrinkThumb,
        isFavorite = false,
        strCategory = this.strCategory,
        strGlass = this.strGlass
    )
}

fun CocktailItem.mapFromBd() : Cocktail {
    return Cocktail(
        idDrink = this.idDrink.toString(),
        strDrink = this.strDrink,
        strAlcoholic = this.strAlcoholic,
        strInstructions = this.strInstructions,
        strDrinkThumb = this.image,
        isFavorite = this.isFavorite,
        strCategory = this.strCategory,
        strGlass = this.strGlass
    )
}