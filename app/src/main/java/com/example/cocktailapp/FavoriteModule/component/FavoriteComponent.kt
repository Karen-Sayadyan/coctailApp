package com.example.cocktailapp.FavoriteModule.component

import com.arkivanov.decompose.ComponentContext


class FavoriteComponent(
    componentContext: ComponentContext,
    val onClickToDetail: (String?) -> Unit
) : ComponentContext by componentContext {
    fun onItemClicked(id: String) {
        onClickToDetail(id)
    }
}