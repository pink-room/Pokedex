package dev.pinkroom.pokedex.android.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import dev.pinkroom.pokedex.android.R

@Composable
fun TopBar(
    navController: NavController,
    @StringRes titleRes: Int,
    onBackClicked: () -> Unit = { navController.popBackStack() },
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
) = TopAppBar(
    title = { Text(text = stringResource(titleRes)) },
    navigationIcon = {
        IconButton(onClick = { onBackClicked() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
            )
        }
    },
    elevation = elevation,
)