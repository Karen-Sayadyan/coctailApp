package com.example.cocktailapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.model.Cocktail
import com.example.cocktailapp.repository.CocktailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CocktailViewModel(private val cocktailRepository: CocktailRepository) : ViewModel() {
    private val _cocktail =
        MutableStateFlow<Cocktail>(Cocktail()) // приватная которую никто не может менять
    val cocktail = _cocktail.asStateFlow() // на это мы подписаны в экране


    fun getCocktail() {
        viewModelScope.launch {
            val cocktail =  cocktailRepository.getCocktail().drinks?.firstOrNull() ?: Cocktail()  // прошу репозиторий отдать коктейль
            _cocktail.value = cocktail  // нашей переменной за которой следим на экране отдаем коктейль который отдал нам репозиторий
        }

    }
}
