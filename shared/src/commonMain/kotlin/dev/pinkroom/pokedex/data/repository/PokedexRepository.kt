package dev.pinkroom.pokedex.data.repository

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingData
import com.kuuurt.paging.multiplatform.PagingResult
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import dev.pinkroom.pokedex.data.models.Pokemon
import dev.pinkroom.pokedex.data.service.PokedexService
import dev.pinkroom.pokedex.utils.CommonFlow
import dev.pinkroom.pokedex.utils.asCommonFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

class PokedexRepository(private val pokedexService: PokedexService) : KoinComponent {

    companion object {
        private const val PAGING_SIZE = 20L
    }

    var scope: CoroutineScope = MainScope() // TODO can I inject this?

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
                nextKey = { key + 1 })
        }
    )

    private suspend fun getPokemons(key: Long): List<Pokemon> =
        pokedexService.getPokemons(PAGING_SIZE, key * PAGING_SIZE).results
}