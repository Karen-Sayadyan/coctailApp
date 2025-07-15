package com.example.cocktailapp.cocktailModule.component

import com.arkivanov.decompose.ComponentContext
import com.example.cocktailapp.cocktailModule.events.CocktailEvents

class CocktailComponent(
    componentContext : ComponentContext,
    private val onGoback: () -> Unit
) : ComponentContext by componentContext {
    fun toLandingScreen(event: CocktailEvents.backToLanding) {
        when (event) {
            is CocktailEvents.backToLanding -> {
                onGoback()
            }
        }
    }
}