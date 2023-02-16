package dev.pinkroom.pokedex.viewmodel.pokedex

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import dev.pinkroom.pokedex.data.repository.PokedexRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokedexViewModel : KMMViewModel(), KoinComponent {

    private val pokedexRepository by inject<PokedexRepository>()
    private val _effect = MutableSharedFlow<PokedexEffect>()

    @NativeCoroutinesState
    val effect = _effect.asSharedFlow()

    init {
        pokedexRepository.scope = viewModelScope.coroutineScope
    }

    val pokemons = pokedexRepository.pokedexPagingData

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pager = pokedexRepository.pager

    fun pokemonSelected(name: String) = viewModelScope.coroutineScope.launch {
        _effect.emit(PokedexEffect.NavigateToDetails(name))
    }
}