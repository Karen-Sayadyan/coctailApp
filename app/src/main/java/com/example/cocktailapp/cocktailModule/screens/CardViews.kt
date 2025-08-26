package com.example.cocktailapp.cocktailModule.screens

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
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
import com.example.cocktailapp.appMaterialTheme.MyAppTypography
import com.example.cocktailapp.cocktailModule.model.Cocktail


@Composable
fun LoadingCardButton() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier,
            color = colorScheme.primary
        )
    }
}

@Composable
fun LoadingCardPullToRefresh() {
}

@Composable
fun ErrorCard() {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ошибка загрузки коктейля. Попробуйте еще раз.",
            color = colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun SuccessCard(
    cocktail: Cocktail,
    modifier: Modifier = Modifier,
    onClick: (String?) -> Unit
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
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
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
                Icon( // иконка умеет отрисовываться если isFavorite
                    imageVector = if (cocktail.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                        .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .padding(4.dp)
                        .clickable {
                            onClick(cocktail.idDrink)
                        }
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