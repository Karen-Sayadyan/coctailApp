package com.example.cocktailapp.landingModule.component

import com.arkivanov.decompose.ComponentContext
import com.example.cocktailapp.landingModule.events.LandingEvents

class LandingComponent(
    componentContext: ComponentContext,
    private val onCocktailNavigation: () -> Unit

) : ComponentContext by componentContext {
    fun toCocktailScreen(event: LandingEvents.GoToCocktail) {
        when (event) {
            is LandingEvents.GoToCocktail -> {
                onCocktailNavigation()
            }
        }
    }
}