package dev.pinkroom.pokedex.android.common

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
@SuppressLint("ComposableNaming")
inline fun <T> SharedFlow<T>.launchEffect(
    key: Any? = "effect",
    crossinline action: suspend (value: T) -> Unit
) {
    LaunchedEffect(key) {
        onEach { action(it) }.launchIn(this)
    }
}