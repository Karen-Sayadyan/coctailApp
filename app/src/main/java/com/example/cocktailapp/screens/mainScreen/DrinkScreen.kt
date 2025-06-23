package com.example.cocktailapp.screens.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cocktailapp.model.Cocktail
import com.example.cocktailapp.viewModels.CocktailViewModel

@Composable
fun DrinkScreen(viewModel: CocktailViewModel, modifier: Modifier = Modifier) {
    val cocktail = viewModel.cocktail.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()  // Подписка на сообщения об ошибках
    val isInitialLoad by viewModel.isInitialLoad.collectAsState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            if (errorMessage.value != null) {
                Text(
                    text = errorMessage.value ?: "",
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            if (!isInitialLoad && cocktail.value != Cocktail()) {
                CardView(
                    cocktail.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 94.dp) // избавляемся от хардкода
                )
            }
        }

        // Фиксированная кнопка внизу
        SearchButton(
            onClick = {
                viewModel.getCocktail()
                viewModel.setInitialLoad(false)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )
    }
}


@Composable
fun SearchButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text("Получить случайный коктейль")
    }
}