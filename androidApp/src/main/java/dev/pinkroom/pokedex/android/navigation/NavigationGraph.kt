package dev.pinkroom.pokedex.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.pinkroom.pokedex.android.screens.DetailsScreen
import dev.pinkroom.pokedex.android.screens.PokedexScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavHost(navController: NavHostController) =
    NavHost(navController = navController, startDestination = Screens.Pokedex.route) {
        pokedex(navController)
        details(navController)
    }

private fun NavGraphBuilder.pokedex(navController: NavHostController) {
    composable(Screens.Pokedex.route) { PokedexScreen(navController, getViewModel()) }
}

private fun NavGraphBuilder.details(navController: NavHostController) {
    composable(
        Screens.Details.route,
        arguments = listOf(navArgument("name") { type = NavType.StringType }),
        content = {
            DetailsScreen(
                navController,
                getViewModel(),
                it.arguments?.getString("name")!!
            )
        }
    )
}

sealed class Screens(val route: String) {
    object Pokedex : Screens("pokedex")
    object Details : Screens("details/{name}") {
        fun buildRoute(name: String) = "details/$name"
    }
}