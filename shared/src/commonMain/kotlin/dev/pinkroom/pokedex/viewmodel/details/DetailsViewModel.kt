package dev.pinkroom.pokedex.viewmodel.details

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import dev.pinkroom.pokedex.data.repository.DetailsRepository
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailsViewModel : KMMViewModel(), KoinComponent {

    private val detailsRepository by inject<DetailsRepository>()

    private val _state = MutableStateFlow(viewModelScope, DetailsState())

    @NativeCoroutinesState
    val state = _state.asStateFlow()

    fun getDetails(name: String) = viewModelScope.coroutineScope.launch {
        _state.value = _state.value.copy(loading = true)
        detailsRepository.getDetails(name)
            .onSuccess { _state.value = _state.value.copy(loading = false, pokemon = it) }
            .onFailure { _state.value = _state.value.copy(loading = false, error = it.message) }
    }
}