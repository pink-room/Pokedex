package dev.pinkroom.pokedex.android.di

import dev.pinkroom.pokedex.viewmodel.details.DetailsViewModel
import dev.pinkroom.pokedex.viewmodel.pokedex.PokedexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { PokedexViewModel() }
    viewModel { DetailsViewModel() }
}