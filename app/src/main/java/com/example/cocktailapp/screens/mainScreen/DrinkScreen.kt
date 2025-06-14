package com.example.cocktailapp.screens.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cocktailapp.ui.theme.OrangePrimary
import com.example.cocktailapp.viewModels.CocktailViewModel


@Composable
fun DrinkScreen(viewModel: CocktailViewModel, modifier: Modifier = Modifier) {
    val cocktail = viewModel.cocktail.collectAsState()
    var isInitialLoad by rememberSaveable { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            if (!isInitialLoad) {
                    CardView(
                        cocktail.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 94.dp) // не оч, исрпавить без хардкода
                    )
                }
        }

        // Фиксированная кнопка внизу
        SearchButton(
            onClick = {
                viewModel.getCocktail()
                isInitialLoad = false
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
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 30.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(OrangePrimary)
    ) {
        Text(
            text = "Получить случайный коктейль",
            color = Color.White
        )
    }
}
