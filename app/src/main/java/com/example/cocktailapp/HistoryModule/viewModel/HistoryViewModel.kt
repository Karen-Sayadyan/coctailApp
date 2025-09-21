package com.example.cocktailapp.HistoryModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.cocktailModule.model.CocktailResponse
import com.example.cocktailapp.HistoryModule.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val cocktailRepository: HistoryRepository,
) : ViewModel() {

    sealed class CocktailState {
        object Loading : CocktailState()
        object Empty : CocktailState()
        data class Success(val cocktails: CocktailResponse) : CocktailState()
    }

    private val _cocktailState = MutableStateFlow<CocktailState>(CocktailState.Loading)
    val cocktailState = _cocktailState.asStateFlow()

    fun loadHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000) // Задержка для демонстрации
            val history = cocktailRepository.getCocktailsHistory()
            _cocktailState.value = CocktailState.Success(history)
        }
    }

}
