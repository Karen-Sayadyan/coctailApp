package com.example.cocktailapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cocktailapp.ui.theme.Pink40
import com.example.cocktailapp.viewModels.CocktailViewModel

@Composable
fun DrinkScreen(viewModel: CocktailViewModel) {
    val cocktail = viewModel.cocktail.collectAsState() // подписались на обновление текста
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier = Modifier.padding(5.dp),
            text = cocktail.value?.strDrink ?: "", // Используем текст из ViewModel
            color = Color.Black,
        )
        Text(modifier = Modifier.padding(5.dp),
            text = cocktail.value?.strAlcoholic ?: "", // Используем текст из ViewModel
            color = Color.Black,
        )
        Text(modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 5.dp),
            text = cocktail.value?.strInstructions ?: "", // Используем текст из ViewModel
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(16.dp))
        SearchButton(onClick = { viewModel.getCocktail() }) // при нажатии просим вьюмодель изменить текст
    }
}

@Composable
fun SearchButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Pink40)
    ) {
        Text(
            text = "Получить случайный коктейль",
            color = Color.White
        )
    }
}
