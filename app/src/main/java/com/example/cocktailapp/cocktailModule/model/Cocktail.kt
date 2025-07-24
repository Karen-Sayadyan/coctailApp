package com.example.cocktailapp.cocktailModule.model

data class CocktailResponse(
    val drinks: List<Cocktail>
)

data class Cocktail(
    val idDrink: String? = null,
    val strDrink: String? = null,
    val strDrinkAlternate: String? = null,
    val strTags: String? = null,
    val strVideo: String? = null,
    val strCategory: String? = null,
    val strIBA: String? = null,
    val strAlcoholic: String? = null,
    val strGlass: String? = null,
    val strInstructions: String? = null,
    val strInstructionsES: String? = null,
    val strInstructionsDE: String? = null,
    val strInstructionsFR: String? = null,
    val strInstructionsIT: String? = null,
    val strInstructionsZH_HANS: String? = null,
    val strInstructionsZH_HANT: String? = null,
    val strDrinkThumb: String? = null,
    val ingredients: List<Ingredient>? = null
)

data class Ingredient(
    val strIngredient: String?,
    val strMeasure: String?
)