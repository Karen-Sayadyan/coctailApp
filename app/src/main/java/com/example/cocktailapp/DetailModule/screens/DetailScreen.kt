package com.example.cocktailapp.DetailModule.screens

import DetailComponent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.cocktailapp.DetailModule.viewModel.DetailViewModel
import com.example.cocktailapp.appMaterialTheme.MyAppTypography
import com.example.cocktailapp.cocktailModule.model.Cocktail


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    component: DetailComponent,
    viewModel: DetailViewModel
) {
    val cocktail by viewModel.cocktailState.collectAsStateWithLifecycle()

    // Загружаем данные при первом показе
    LaunchedEffect(component) {
        viewModel.getCocktailById(component.cocktailId)
    }

    // Показываем загрузку или данные
    when {
        cocktail == null -> {
            // Экран загрузки
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Загрузка...")
            }
        }
        else -> {
            cocktail?.let {
                DetailContent(
                    modifier = modifier,
                    component = component,
                    cocktail = it,
                    goBack = {
                        component.goBackScreen()
                    }
                )
            }
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    component: DetailComponent,
    cocktail: Cocktail,
    goBack: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface,
            contentColor = colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
                //contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Close, "Close",
                    modifier = Modifier.clickable {
                        goBack()
                })
                Text(
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f),
                    text = cocktail.strDrink.orEmpty(),
                    style = MyAppTypography.displayMedium,
                    textAlign = TextAlign.Center
                )
            }

            Box {
                AsyncImage(
                    model = cocktail.strDrinkThumb.orEmpty(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = ColorPainter(Color.LightGray),
                    error = ColorPainter(Color.Red.copy(alpha = 0.2f))
                )
            }

            Text(
                modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 5.dp),
                text = cocktail.strInstructions.orEmpty(),
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.padding(5.dp),
                text = cocktail.strAlcoholic.orEmpty(),
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.padding(5.dp),
                text = cocktail.strGlass.orEmpty(),
                textAlign = TextAlign.Center
            )
        }
    }
}