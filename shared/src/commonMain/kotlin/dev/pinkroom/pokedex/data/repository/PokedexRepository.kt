package dev.pinkroom.pokedex.data.repository

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingData
import com.kuuurt.paging.multiplatform.PagingResult
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import dev.pinkroom.pokedex.data.models.Pokemon
import dev.pinkroom.pokedex.data.service.PokedexService
import dev.pinkroom.pokedex.local.PokemonDao
import dev.pinkroom.pokedex.utils.CommonFlow
import dev.pinkroom.pokedex.utils.asCommonFlow
import dev.pinkroom.pokedex.utils.toPokemon
import dev.pinkroom.pokedex.utils.toPokemonModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.MainScope
import org.koin.core.component.KoinComponent

class PokedexRepository(
    private val pokedexService: PokedexService,
    private val pokemonDao: PokemonDao,
) : KoinComponent {

    companion object {
        private const val PAGING_SIZE = 20L
    }

    var scope: CoroutineScope = MainScope()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pokedexPagingData: CommonFlow<PagingData<Pokemon>>
        get() = pager.pagingData.cachedIn(scope).asCommonFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pager = Pager(
        clientScope = scope,
        config = PagingConfig(pageSize = PAGING_SIZE.toInt(), enablePlaceholders = false),
        initialKey = 0L,
        getItems = { key, _ ->
            PagingResult(
                items = getPokemons(key),
                currentKey = key,
                prevKey = { null },
                nextKey = { key + 1 }
            )
        }
    )

    private suspend fun getPokemons(key: Long): List<Pokemon> {
        val local = pokemonDao.get(PAGING_SIZE, key * PAGING_SIZE)
        return if (local.isEmpty()) getPokemonsRemote(key) else local.map { it.toPokemon() }
    }

    private suspend fun getPokemonsRemote(key: Long): List<Pokemon> = try {
        val result = pokedexService.getPokemons(PAGING_SIZE, key * PAGING_SIZE)
        if (key == 0L) pokemonDao.deleteAll()
        pokemonDao.insert(result.results.map { it.toPokemonModel() })
        result.results
    } catch (e: Exception) {
        emptyList()
    }
}