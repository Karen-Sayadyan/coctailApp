package com.example.cocktailapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.model.Cocktail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//
//data class CocktailUiState(
//    val text: String? = null
//)

class CocktailViewModel : ViewModel() {
    private val repo = CoctailRepository()
    private val _cocktail =
        MutableStateFlow<Cocktail?>(Cocktail()) // приватная которую никто не может менять
    val cocktail = _cocktail.asStateFlow() // на это мы подписаны в экране


    fun getCocktail() {
        viewModelScope.launch {
            val cocktail = repo.getCocktail().drinks?.firstOrNull() // прошу репозиторий отдать коктейль
            _cocktail.value = cocktail  // нашей переменной за которой следим на экране отдаем коктейль который отдал нам репозиторий
        }

    }
}
