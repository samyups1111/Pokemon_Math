package com.example.newme.model

import com.example.newme.ui.viewmodel.BattleMenu

data class PokemonBattlePageUiState(
    var userPokemon: Pokemon = Pokemon(),
    var opponentPokemon: Pokemon = Pokemon(),
    var battleMenuState: BattleMenu = BattleMenu.MAIN,
    var userTeam: List<Pokemon> = emptyList(),
    var enemyTeam: List<Pokemon> = emptyList(),
)
