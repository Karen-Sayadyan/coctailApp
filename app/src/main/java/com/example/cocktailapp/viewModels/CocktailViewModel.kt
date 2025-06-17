package com.example.cocktailapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.model.Cocktail
import com.example.cocktailapp.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class CocktailViewModel @Inject constructor(private val cocktailRepository: CocktailRepository) : ViewModel() {
    private val _cocktail = MutableStateFlow<Cocktail>(Cocktail())
    val cocktail = _cocktail.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null) // состояние для сообщений об ошибках
    val errorMessage = _errorMessage.asStateFlow() // публичное наблюдаемое состояние

    private val _isInitialLoad = MutableStateFlow(true)
    val isInitialLoad = _isInitialLoad.asStateFlow()

    fun getCocktail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cocktail = cocktailRepository.getCocktail().drinks?.firstOrNull() ?: Cocktail()
                _cocktail.value = cocktail
                _errorMessage.value = null // сброс сообщения об ошибке при успешном загрузке
                _isInitialLoad.value = false // Устанавливаем флаг после успешной загрузки
            } catch (e: Exception) {
                val errorMsg = when (e) {
                    is IOException -> "Network error. Try again"
                    is HttpException -> "Server error. Try again"
                    else -> "Unknown error: ${e.localizedMessage}"
                }
                _errorMessage.value = errorMsg // установка сообщения об ошибке
            }
        }
    }
    fun setInitialLoad(loadState: Boolean) {
        _isInitialLoad.value = loadState
    }
}

