package com.example.cocktailapp.FavoriteModule.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.cocktailapp.appMaterialTheme.MyAppTypography
import com.example.cocktailapp.cocktailModule.model.Cocktail

@Composable
fun FavoritesListScreen(
    cocktails: List<Cocktail>,
    modifier: Modifier = Modifier,
    onRemove: (String?) -> Unit,
    onClick: (String?) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(cocktails) { cocktail ->
            FavoriteItem(cocktail = cocktail,
                onRemove = { onRemove(cocktail.idDrink) },
                onClick = {
                    onClick(cocktail.idDrink)
                }
            )
        }
    }
}


@Composable
fun FavoriteItem(
    cocktail: Cocktail,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
    onClick: (String?) -> Unit
) {
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissDirection ->
            if (dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                onRemove()
            }
            false
        }
    )
    SwipeToDismissBox(
        state = swipeToDismissBoxState,
        modifier = modifier.fillMaxSize().clickable{
            onClick(cocktail.idDrink)
        },
        backgroundContent = {
            when (swipeToDismissBoxState.dismissDirection) {
                SwipeToDismissBoxValue.EndToStart -> {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Удалить задачу",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                lerp(
                                    Color.LightGray,
                                    Color.Red,
                                    swipeToDismissBoxState.progress
                                )
                            )
                            .wrapContentSize(Alignment.CenterEnd)
                            .padding(16.dp),
                        tint = Color.White // белый цвет иконки
                    )
                }
                else -> {}
            }
        }
    ) {
        Card(
            modifier = modifier
                .padding(6.dp),
            elevation = cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorScheme.surface,
                contentColor = colorScheme.onSurface
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = cocktail.strDrinkThumb.orEmpty(),
                    contentDescription = cocktail.strDrink,
                    modifier = Modifier
                        .size(88.dp)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = ColorPainter(Color.LightGray),
                    error = ColorPainter(Color.Red.copy(alpha = 0.2f))
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp, end = 16.dp),
                    text = cocktail.strDrink.orEmpty(),
                    style = MyAppTypography.displaySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}