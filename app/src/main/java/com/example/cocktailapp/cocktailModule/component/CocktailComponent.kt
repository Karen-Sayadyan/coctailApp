package com.example.cocktailapp.cocktailModule.component

import com.arkivanov.decompose.ComponentContext

class CocktailComponent(
    componentContext : ComponentContext,
    private val onGoback: () -> Unit
) : ComponentContext by componentContext {
}
