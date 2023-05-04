package com.example.newme.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newme.ui.model.Pokemon
import com.example.newme.ui.model.PokemonBattlePageUiState
import com.example.newme.ui.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonBattleViewModel(
    private val repository: PokemonRepository,
): ViewModel() {

    init {
        getUserTeam()
        getEnemyTeam()
    }

    private val _uiState = MutableStateFlow(PokemonBattlePageUiState())

    val uiState: StateFlow<PokemonBattlePageUiState> = _uiState.asStateFlow()

//    private val _battleMenuState = MutableStateFlow(BattleMenu.MAIN)
//
//    val battleMenuState: StateFlow<BattleMenu> = _battleMenuState.asStateFlow()
//
//    private val _pokemon = MutableStateFlow(Pokemon())
//
//    var pokemon: StateFlow<Pokemon> = _pokemon.asStateFlow()
//
//    private val _userTeam = MutableStateFlow(listOf(Pokemon()))
//    val userTeam: StateFlow<List<Pokemon>> = _userTeam.asStateFlow()
//
//    private val _enemyTeam = MutableStateFlow(listOf(Pokemon()))
//    val enemyTeam: StateFlow<List<Pokemon>> = _enemyTeam.asStateFlow()

    fun onFightClicked() {
        _uiState.value.battleMenuState = BattleMenu.ATTACK
    }

    fun attackMenuOnBackClicked() {
        _uiState.value.battleMenuState = BattleMenu.MAIN
    }

    private fun getUserTeam() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.userTeam.collect {
                _uiState.value.userTeam = it
            }
        }
    }
    private fun getEnemyTeam() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.enemyTeam.collect {
                _uiState.value.enemyTeam = it
            }
        }
    }

    fun getPokemonById(id: Int? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonFlow = repository.getPokemonById(id ?: (1..150).random())
            pokemonFlow.collect {

            }
        }
    }

    fun onAttack(
        attackIndex: Int,
        userPokemonIndex: Int,
        enemyPokemonIndex: Int,
    ) {
        viewModelScope.launch {
            var newHealth: Int = _uiState.value.enemyTeam[enemyPokemonIndex].currentHealth - _uiState.value.userTeam[userPokemonIndex].moves[attackIndex].move.attackLevel
            newHealth = if (newHealth < 0) 0 else newHealth

            _uiState.value.enemyTeam[enemyPokemonIndex].currentHealth = newHealth
        }
    }
}

enum class BattleMenu {
    MAIN,
    ATTACK,
}