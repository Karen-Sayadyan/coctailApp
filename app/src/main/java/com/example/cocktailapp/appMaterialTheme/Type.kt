package com.example.cocktailapp.appMaterialTheme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cocktailapp.R

val Savate = FontFamily(
    Font(R.font.savate_regular, FontWeight.Black)
)


val MyAppTypography = Typography(
    displayMedium = TextStyle(
        fontFamily = Savate,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Savate,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )

)


