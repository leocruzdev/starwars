package com.dacruz.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dacruz.home.presentation.model.Category
import com.dacruz.theme.StarWarsTheme
import org.koin.androidx.compose.getViewModel

@Preview(showBackground = true)
@Composable
fun SwapiScreen() {
    StarWarsTheme() {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = getViewModel()
    val homeState by viewModel.loadCategories().collectAsState(initial = HomeState.Loading)

    HomeScreenContent(homeState)
}

@Composable
fun HomeScreenContent(homeState: HomeState) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (homeState) {
            is HomeState.Loading -> {
                CircularProgressIndicator()
            }
            is HomeState.Success -> {
                GridLayout(homeState.data)
            }
            is HomeState.Error -> {
                homeState.message.localizedMessage?.let { Text(text = it) }
            }
            is HomeState.Empty -> {
                Text(text = "Nenhum item encontrado")
            }
        }
    }
}

@Composable
fun GridLayout(category: Category) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(category.categories) { category ->
                CategoryCard(categoryName = category.first, url = category.second)
            }
        }
    }
}

@Composable
fun CategoryCard(categoryName: String, url: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = categoryName,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}