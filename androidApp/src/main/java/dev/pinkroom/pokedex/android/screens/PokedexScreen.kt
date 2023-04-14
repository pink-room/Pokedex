package dev.pinkroom.pokedex.android.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import dev.pinkroom.pokedex.android.common.launchEffect
import dev.pinkroom.pokedex.android.navigation.Screens
import dev.pinkroom.pokedex.data.models.Pokemon
import dev.pinkroom.pokedex.viewmodel.pokedex.PokedexEffect
import dev.pinkroom.pokedex.viewmodel.pokedex.PokedexViewModel

@Composable
fun PokedexScreen(navController: NavController, viewModel: PokedexViewModel) {
    val pokemons: LazyPagingItems<Pokemon> = viewModel.pokemons.collectAsLazyPagingItems()
    PokedexContent(viewModel, pokemons)
    HandleEffects(navController, viewModel)
}

@Composable
private fun PokedexContent(viewModel: PokedexViewModel, pokemons: LazyPagingItems<Pokemon>) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (pokemons.itemCount == 0) CircularProgressIndicator()
        else Pokedex(viewModel, pokemons)
    }
}

@Composable
private fun Pokedex(viewModel: PokedexViewModel, pokemons: LazyPagingItems<Pokemon>) {
    TopAppBarUI()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) { items(pokemons) { it?.let { PokedexRow(viewModel, it) } } }
}

@Composable
fun TopAppBarUI() {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                color = Color.Black,
                text = "Pokemons"
            )
        }
    )
}

@Composable
private fun PokedexRow(viewModel: PokedexViewModel, pokemon: Pokemon) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.pokemonSelected(pokemon.name) }
            .padding(horizontal = 4.dp)
            .padding(bottom = 4.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CoilImage(
                modifier = Modifier.size(80.dp),
                imageModel = { pokemon.getImageUrl() },
                imageOptions = ImageOptions(
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Fit,
                ),
                component = rememberImageComponent {
                    +ShimmerPlugin(
                        baseColor = MaterialTheme.colors.background,
                        highlightColor = Color.LightGray,
                        durationMillis = 350,
                        dropOff = 0.65f,
                        tilt = 20f
                    )
                },
            )
            Text(
                pokemon.getNameCapitalized(),
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun HandleEffects(navController: NavController, viewModel: PokedexViewModel) {
    viewModel.effect.launchEffect { effect ->
        when (effect) {
            is PokedexEffect.NavigateToDetails -> navController.navigate(
                Screens.Details.buildRoute(effect.name)
            )
        }
    }
}