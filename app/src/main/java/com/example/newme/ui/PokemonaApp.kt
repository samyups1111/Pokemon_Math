package com.example.newme.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.newme.ui.component.PokemonBattlePage
import com.example.newme.ui.model.PokemonBattlePageUiState
import com.example.newme.ui.navigation.PokemonAppNavHost
import com.example.newme.ui.navigation.PokemonBattleDestination

@Composable
fun PokemonApp(
    appState: PokemonAppState = rememberPokemonAppState()
) {
    PokemonAppNavHost(
        navController = appState.navController,
        onBackClick = appState::onBackClick,
        startDestination = PokemonBattleDestination.route,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokemonBattlePage(
        uiState = PokemonBattlePageUiState()
    )
}