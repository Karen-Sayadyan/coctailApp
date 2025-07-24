package com.example.cocktailapp.cocktailModule.component

import com.arkivanov.decompose.ComponentContext
import com.example.cocktailapp.cocktailModule.events.CocktailEvents

class CocktailComponent(
    componentContext : ComponentContext,
    private val onGoback: () -> Unit
) : ComponentContext by componentContext {
    fun toLandingScreen(event: CocktailEvents.BackToLanding) {
        when (event) {
            is CocktailEvents.BackToLanding -> {
                onGoback()
            }
        }
    }
}
