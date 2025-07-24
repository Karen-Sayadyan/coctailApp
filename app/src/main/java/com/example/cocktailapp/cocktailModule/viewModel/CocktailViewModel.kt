package com.example.cocktailapp.cocktailModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.cocktailModule.model.Cocktail
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
    private val cocktailRepository: CocktailRepository,
) : ViewModel() {

    sealed class CocktailState {
        object LoadingButton : CocktailState() // Состояние для загрузки по кнопке
        object LoadingPullToRefresh : CocktailState() // Состояние для загрузки от Pull to Refresh
        data class Error(val message: String) : CocktailState()
        data class Success(val cocktail: Cocktail) : CocktailState()
    }

    private val _cocktailState = MutableStateFlow<CocktailState>(CocktailState.LoadingButton)
    val cocktailState = _cocktailState.asStateFlow()

    fun loadCocktail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _cocktailState.value = CocktailState.LoadingButton
                delay(1000) // Задержка для демонстрации
                val response = cocktailRepository.getCocktail()
                val cocktail =
                    response ?: throw Exception("No cocktails found")
                _cocktailState.value = CocktailState.Success(cocktail)
            } catch (e: Exception) {
                _cocktailState.value = CocktailState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun loadCocktailFromPullToRefresh() {
        if (_cocktailState.value is CocktailState.LoadingPullToRefresh) return
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _cocktailState.value = CocktailState.LoadingPullToRefresh
                delay(1000) // Задержка для демонстрации
                val response = cocktailRepository.getCocktail()
                val cocktail =
                    response ?: throw Exception("No cocktails found")
                _cocktailState.value = CocktailState.Success(cocktail)
            } catch (e: Exception) {
                _cocktailState.value = CocktailState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}