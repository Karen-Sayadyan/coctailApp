package com.example.cocktailapp.utils

import com.example.cocktailapp.cocktailModule.model.Cocktail
import com.example.cocktailapp.data.CocktailItem

fun Cocktail.mapToBd(): CocktailItem {
    return CocktailItem(
        idDrink = this.idDrink?.toInt() ?: 1,
        strDrink = this.strDrink,
        strAlcoholic = this.strAlcoholic,
        strInstructions = this.strInstructions,
        image = this.strDrinkThumb,
        isFavorite = false
    )
}

fun CocktailItem.mapFromBd() : Cocktail {
    return Cocktail(
        idDrink = this.idDrink.toString(),
        strDrink = this.strDrink,
        strAlcoholic = this.strAlcoholic,
        strInstructions = this.strInstructions,
        strDrinkThumb = this.image,
        isFavorite = this.isFavorite
    )
}