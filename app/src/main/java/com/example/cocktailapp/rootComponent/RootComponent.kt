package com.example.cocktailapp.rootComponent

import DetailComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.example.cocktailapp.FavoriteModule.component.FavoriteComponent
import com.example.cocktailapp.HistoryModule.component.HistoryComponent
import com.example.cocktailapp.cocktailModule.component.CocktailComponent
import com.example.cocktailapp.rootComponent.RootComponent.Child.*
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()
    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.CocktailScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    init {
        // Явно инициализируем первый экран
        navigation.bringToFront(Configuration.CocktailScreen)
    }
    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when (config) {
            Configuration.FavoriteScreen -> FavoriteScreen(
                component = FavoriteComponent(
                    context,
                    onClickToDetail = {
                        it?.let { detailString -> navigation.bringToFront(Configuration.DetailsScreen(detailString)) }
                    }
                )
            )

            Configuration.CocktailScreen -> CocktailScreen(
                component = CocktailComponent(
                    context,
                    onGoback = {
                        navigation.pop()
                    }
                )
            )

            Configuration.HistoryScreen -> HistoryScreen(
                component = HistoryComponent(
                    context,
                    onGoback = {
                        navigation.pop()
                    },
                    onCocktailNavigation = {
                        navigation.pushNew(Configuration.CocktailScreen)
                    }
                )
            )

            is Configuration.DetailsScreen -> DetailsScreen(
                component = DetailComponent(
                    componentContext = context,
                    cocktailId = config.id
                )
            )
        }
    }

    fun onTabSelected(config: Configuration) {
        navigation.bringToFront(config)
    }

    sealed class Child {
        class CocktailScreen(val component: CocktailComponent) : Child()
        class HistoryScreen(val component: HistoryComponent) : Child()

        class FavoriteScreen(val component: FavoriteComponent) : Child()

        class DetailsScreen(val component: DetailComponent) : Child()


    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object CocktailScreen : Configuration()

        @Serializable
        data object HistoryScreen : Configuration()

        @Serializable
        data object FavoriteScreen : Configuration()

        @Serializable
        data class DetailsScreen(val id: String) : Configuration()

    }

}




