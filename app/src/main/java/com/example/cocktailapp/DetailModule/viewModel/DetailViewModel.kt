package com.example.cocktailapp.DetailModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailapp.cocktailModule.model.Cocktail
import com.example.cocktailapp.cocktailModule.repository.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {

    private val _cocktailState = MutableStateFlow<Cocktail?>(null)
    val cocktailState = _cocktailState.asStateFlow()

    fun getCocktailById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
           val cocktail = cocktailRepository.getCocktailById(id.toInt())
            _cocktailState.value = cocktail
        }
    }
}


