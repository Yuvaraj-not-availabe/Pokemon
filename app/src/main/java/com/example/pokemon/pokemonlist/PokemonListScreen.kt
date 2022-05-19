package com.example.pokemon.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pokemon.R
import com.example.pokemon.models.PokemonListEntry
import com.example.pokemon.viewmodel.PokemonListViewModel

@Composable
fun PokemonListScreen(navController: NavController) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
                contentDescription = "Pokemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            SearchBar(hint = "Search...",
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {

            }
        }
    }
}

@Composable
fun SearchBar(hint: String, modifier: Modifier = Modifier, onSearch: (String) -> Unit = {}) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    Box() {
        BasicTextField(value = text, onValueChange = {
            text = it
            onSearch(it)
        },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(Color.Black),
            modifier = modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(
                    Color.White,
                    CircleShape
                )
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 24.dp)
            )
        }
    }
}

@Composable
fun PokemonListEntry(
    entry: PokemonListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
      val defaultDominantColor= MaterialTheme.colors.surface
      val dominantColor by remember{ mutableStateOf(defaultDominantColor)}
      Box(modifier = modifier.shadow(5.dp, RoundedCornerShape(10.dp))
          .clip(RoundedCornerShape(10.dp))
          .aspectRatio(1f)
          .background(
              Brush.verticalGradient(listOf(dominantColor,defaultDominantColor))
          )
      )
}