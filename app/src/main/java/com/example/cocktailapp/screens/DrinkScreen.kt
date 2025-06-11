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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.cocktailapp.ui.theme.MyAppTypography
import com.example.cocktailapp.ui.theme.Pink40
import com.example.cocktailapp.viewModels.CocktailViewModel


@Composable
fun DrinkScreen(viewModel: CocktailViewModel) {
    val cocktail = viewModel.cocktail.collectAsState()
    var isInitialLoad by remember { mutableStateOf(true) } // Добавил флаг первой загрузки

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isInitialLoad) { // Показываем карточку только если не первая загрузка

            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = cocktail.value.strDrink.orEmpty(),
                        color = Color.Black,
                        style = MyAppTypography.displayMedium,
                        textAlign = TextAlign.Center
                    )
                    AsyncImage(
                        model = cocktail.value.strDrinkThumb,
                        contentDescription = null,
                        modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop,
                        placeholder = ColorPainter(Color.LightGray),
                        error = ColorPainter(Color.Red.copy(alpha = 0.2f))
                    )
                    Text(
                        modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 5.dp),
                        text = cocktail.value.strInstructions.orEmpty(),
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = cocktail.value.strAlcoholic.orEmpty(),
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = cocktail.value.strGlass.orEmpty(),
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        SearchButton(onClick = {
            viewModel.getCocktail()
            isInitialLoad = false // После первого нажатия сбрасываем флаг
        })
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
