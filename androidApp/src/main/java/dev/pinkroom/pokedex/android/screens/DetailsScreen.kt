package dev.pinkroom.pokedex.android.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import dev.pinkroom.pokedex.android.R
import dev.pinkroom.pokedex.android.components.TopBar
import dev.pinkroom.pokedex.data.models.PokemonDetails
import dev.pinkroom.pokedex.viewmodel.details.DetailsState
import dev.pinkroom.pokedex.viewmodel.details.DetailsViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DetailsScreen(navController: NavController, viewModel: DetailsViewModel, name: String) {
    LaunchedEffect(viewModel) { viewModel.getDetails(name) }
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = { DetailsTopBar(navController) },
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state.loading) CircularProgressIndicator()
            else DetailsContent(state)
        }
    }
}

@Composable
private fun DetailsTopBar(navController: NavController) {
    TopBar(
        navController = navController,
        titleRes = R.string.details,
    )
}

@Composable
private fun DetailsContent(state: DetailsState) {
    if (state.error != null) Text(text = state.error!!)
    else state.pokemon?.let { PokemonDetails(it) }
}

@Composable
private fun PokemonDetails(pokemon: PokemonDetails) {
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
                    baseColor = Color.Transparent,
                    highlightColor = Color.LightGray,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                )
            }
        )
        Text("Name: ${pokemon.getNameCapitalized()}")
        Text("Height: ${pokemon.height}")
        Text("Weight: ${pokemon.weight}")
        Text("Base experience: ${pokemon.experience}")
    }
}