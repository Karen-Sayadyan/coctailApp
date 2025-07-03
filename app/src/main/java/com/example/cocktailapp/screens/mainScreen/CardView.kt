package com.example.cocktailapp.screens.mainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.cocktailapp.ui.theme.MyAppTypography
import com.example.cocktailapp.viewModels.CocktailViewModel


@Composable
fun CardView(
    state: CocktailViewModel.CocktailState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = if (state is CocktailViewModel.CocktailState.Loading) {
            Modifier.fillMaxWidth()
        } else {
            Modifier.fillMaxWidth()
        },
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = modifier
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(if (state is CocktailViewModel.CocktailState.Loading) 0.dp else 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (state is CocktailViewModel.CocktailState.Loading) Color.Transparent else colorScheme.surface,
                contentColor = colorScheme.onSurface,
            )
        ) {
            when (state) {
                is CocktailViewModel.CocktailState.Idle -> {
                    // Обработка состояния Idle
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            //.height(200.dp),
                            .heightIn(min = 100.dp), // мин высота высота
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Нажмите кнопку для загрузки коктейля",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                is CocktailViewModel.CocktailState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            //.height(200.dp),
                            .heightIn(min = 100.dp), // мин высота высота
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is CocktailViewModel.CocktailState.Error -> {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = state.message,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(16.dp)
                        )
                        Button(
                            onClick = onRetry,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("Повторить попытку")
                        }
                    }
                }

                is CocktailViewModel.CocktailState.Success -> {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = state.cocktail.strDrink.orEmpty(),
                            style = MyAppTypography.displayMedium,
                            textAlign = TextAlign.Center
                        )

                        AsyncImage(
                            model = state.cocktail.strDrinkThumb.orEmpty(),
                            //model = "https://lastfm.freetls.fastly.net/i/u/ar0/980a08d3d882bacd05b0b25951221e34.png",
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                //.height(200.dp)
                                .aspectRatio(1f) // Соотношение сторон 1:1 для изображения
                                .padding(8.dp),
                            contentScale = ContentScale.Crop,
                            placeholder = ColorPainter(Color.LightGray),
                            error = ColorPainter(Color.Red.copy(alpha = 0.2f))
                        )
                        Text(
                            modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 5.dp),
                            text = state.cocktail.strInstructions.orEmpty(),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = state.cocktail.strAlcoholic.orEmpty(),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = state.cocktail.strGlass.orEmpty(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}