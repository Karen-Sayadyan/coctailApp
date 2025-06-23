package com.example.cocktailapp.screens.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.cocktailapp.model.Cocktail

@Composable
fun CardView (
    cocktail: Cocktail?,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface,
            contentColor = colorScheme.onSurface,

        )
    ) {
        if (cocktail == null) {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        } else {
            Column(
                modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    modifier = Modifier.padding(5.dp),
                    text = cocktail.strDrink.orEmpty(),
                    style = MyAppTypography.displayMedium,
                    textAlign = TextAlign.Center
                )

                AsyncImage(
                    model = "https://lastfm.freetls.fastly.net/i/u/ar0/980a08d3d882bacd05b0b25951221e34.png",
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
}