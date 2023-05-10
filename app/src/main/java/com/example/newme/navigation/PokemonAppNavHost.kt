package com.example.newme.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newme.ui.pages.PokemonBattlePage

@Composable
fun PokemonAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "pokemon_battle_route",
) {
    Log.d("Sammy", "PokemonAppNavHost()")

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        composable(
            route = startDestination,
        ) {
            PokemonBattlePage()
        }
    }
}

