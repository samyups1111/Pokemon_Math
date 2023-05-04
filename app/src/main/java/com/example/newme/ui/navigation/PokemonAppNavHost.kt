package com.example.newme.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newme.ui.component.PokemonBattleRoute

@Composable
fun PokemonAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onNavigateToDestination: (PokemonAppNavigationDestination, String) -> Unit = { _, _ -> },
    onBackClick: () -> Unit = {},
    startDestination: String = PokemonBattleDestination.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        composable(
            route = startDestination,
        ) {
            PokemonBattleRoute()
        }
    }
}