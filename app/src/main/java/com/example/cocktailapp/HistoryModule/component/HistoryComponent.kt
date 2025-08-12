package com.example.cocktailapp.HistoryModule.component

import com.arkivanov.decompose.ComponentContext
import com.example.cocktailapp.HistoryModule.events.HistoryEvents

class HistoryComponent(
    componentContext: ComponentContext,
    private val onCocktailNavigation: () -> Unit,
    private val onGoback: () -> Unit

) : ComponentContext by componentContext {
    fun toCocktailScreen(event: HistoryEvents.GoToCocktail) {
        when (event) {
            is HistoryEvents.GoToCocktail -> {
                onCocktailNavigation()
            }
        }
    }

    fun toLandingScreen(event: HistoryEvents.BackToLanding) {
        when (event) {
            is HistoryEvents.BackToLanding -> {
                onGoback()
            }
        }
    }
}