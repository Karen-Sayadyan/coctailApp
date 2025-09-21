package com.example.cocktailapp.FavoriteModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.cocktailModule.model.CocktailResponse
import com.example.cocktailapp.FavoriteModule.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {

    sealed class CocktailState {
        object Loading : CocktailState()
        object Empty : CocktailState()
        data class Success(val cocktails: CocktailResponse) : CocktailState()
        data class Error(val message: String) : CocktailState() // Добавим состояние ошибки
    }

    private val _cocktailState = MutableStateFlow<CocktailState>(CocktailState.Loading)
    val cocktailState = _cocktailState.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        favoriteRepository.getAllFavorites()
            .onStart {
                _cocktailState.value = CocktailState.Loading
            }
            .onEach { cocktailResponse ->
                if (cocktailResponse.drinks.isEmpty()) {
                    _cocktailState.value = CocktailState.Empty
                } else {
                    _cocktailState.value = CocktailState.Success(cocktailResponse)
                }
            }
            .catch { exception ->
                _cocktailState.value = CocktailState.Error("Ошибка загрузки: ${exception.message}")
            }
            .launchIn(viewModelScope)
    }

    fun deleteCocktailFromFavorites(cocktailId: String?) {
        viewModelScope.launch {
            try {
                cocktailId?.toInt()?.let { id ->
                    favoriteRepository.removeFromFavorite(id)
                }
            } catch (e: Exception) {
                _cocktailState.value = CocktailState.Error("Ошибка удаления: ${e.message}")
            }
        }
    }
}