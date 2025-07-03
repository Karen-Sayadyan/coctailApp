package com.example.cocktailapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.model.Cocktail
import com.example.cocktailapp.model.LandingItem
import com.example.cocktailapp.model.LandingScreenRepository
import com.example.cocktailapp.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CocktailViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {

    sealed class CocktailState {
        data object Idle : CocktailState() // Новое состояние - не загружаем коктейль сразу
        data object Loading : CocktailState()
        data class Error(val message: String) : CocktailState()
        data class Success(val cocktail: Cocktail) : CocktailState()
    }

    sealed class LandingState {
        data class Success(val landings: List<LandingItem>) : LandingState()
    }

    private val _cocktailState = MutableStateFlow<CocktailState>(CocktailState.Idle) //
    val cocktailState = _cocktailState.asStateFlow()

    private val _landingState = MutableStateFlow<LandingState>(LandingState.Success(
        LandingScreenRepository.landingScreens))
    val landingState = _landingState.asStateFlow()

    private val _showLandings = MutableStateFlow(true) // Флаг для переключения между экранами
    val showLandings = _showLandings.asStateFlow()

    fun loadCocktail() {
        _showLandings.value = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _cocktailState.value = CocktailState.Loading
                delay(1000) // Задержка 1 сек для демонстрации
                val response = cocktailRepository.getCocktail()
                val cocktail = response.drinks?.firstOrNull() ?: throw Exception("No cocktails found")
                _cocktailState.value = CocktailState.Success(cocktail)
            } catch (e: Exception) {
                _cocktailState.value = CocktailState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun showLandings() {
        _showLandings.value = true
        _cocktailState.value = CocktailState.Idle //
    }
}