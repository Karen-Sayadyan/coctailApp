package com.example.cocktailapp.cocktailModule.events

sealed interface CocktailEvents {
    data object BackToLanding : CocktailEvents
}