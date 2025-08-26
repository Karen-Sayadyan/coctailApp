package com.example.cocktailapp.FavoriteModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.cocktailModule.model.CocktailResponse
import com.example.cocktailapp.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {

    sealed class CocktailState {
        object Loading : CocktailState()
        object Empty : CocktailState()
        data class Success(val cocktails: CocktailResponse) : CocktailState()
    }

    private val _cocktailState = MutableStateFlow<CocktailState>(CocktailState.Loading)
    val cocktailState = _cocktailState.asStateFlow()

    fun loadFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = favoriteRepository.getAllFavorites()
            _cocktailState.value = FavoriteViewModel.CocktailState.Success(favorites)
        }
    }

    fun deleteCocktailFromFavorites(cocktailId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            cocktailId?.toInt()?.let {
                favoriteRepository.removeFromFavorite(it)
            }
            val favorite = favoriteRepository.getAllFavorites()
            _cocktailState.value = CocktailState.Success(favorite)
        }
    }
}