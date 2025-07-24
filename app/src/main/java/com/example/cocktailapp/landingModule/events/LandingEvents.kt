package com.example.cocktailapp.landingModule.events

sealed interface LandingEvents {
    data object GoToCocktailHistory: LandingEvents
}