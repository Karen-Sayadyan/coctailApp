package com.example.cocktailapp.HistoryModule.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cocktailapp.HistoryModule.component.HistoryComponent
import com.example.cocktailapp.HistoryModule.events.HistoryEvents
import com.example.cocktailapp.HistoryModule.viewModel.HistoryViewModel
import com.example.cocktailapp.cocktailModule.screens.ErrorCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    component: HistoryComponent,
    viewModel: HistoryViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewModel.cocktailState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadHistory()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            onClick = { component.toLandingScreen(event = HistoryEvents.BackToLanding) },
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(end = 8.dp)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }

                        Text(
                            text = "History",
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                when(state) {
                    is HistoryViewModel.CocktailState.Loading -> {
                        LoadingScreen()
                    }
                    is HistoryViewModel.CocktailState.Empty -> {
                        ErrorCard()
                    }
                    is HistoryViewModel.CocktailState.Success -> {
                        val cocktails = (state as HistoryViewModel.CocktailState.Success).cocktails.drinks
                        HistoryListScreen(
                            cocktails = cocktails,
                            modifier = Modifier
                                .weight(1f)
                                .padding(bottom = 16.dp)
                        )
                    }
                }
                SearchButton(
                    onClick = { component.toCocktailScreen(event = HistoryEvents.GoToCocktail) },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}


