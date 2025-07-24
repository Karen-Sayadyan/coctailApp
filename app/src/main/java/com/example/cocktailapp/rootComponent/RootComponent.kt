package com.example.cocktailapp.rootComponent

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.example.cocktailapp.HistoryModule.component.HistoryComponent
import com.example.cocktailapp.cocktailModule.component.CocktailComponent
import com.example.cocktailapp.landingModule.component.LandingComponent
import kotlinx.serialization.Serializable

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()
    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.LandingScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when (config) {
            Configuration.LandingScreen -> Child.LandingScreen(
                component = LandingComponent(
                    context,
                    onCocktailNavigation = {
                        navigation.pushNew(Configuration.HistoryScreen)
                    }
                )
            )

            Configuration.CocktailScreen -> Child.CocktailScreen(
                component = CocktailComponent(
                    context,
                    onGoback = {
                        navigation.pop()
                    }
                )
            )

            Configuration.HistoryScreen -> Child.HistoryScreen(
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
        }
    }

    sealed class Child {
        class LandingScreen(val component: LandingComponent) : Child()
        class CocktailScreen(val component: CocktailComponent) : Child()
        class HistoryScreen(val component: HistoryComponent) : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object LandingScreen : Configuration()

        @Serializable
        data object CocktailScreen : Configuration()

        @Serializable
        data object HistoryScreen : Configuration()
    }

}




