package com.example.newme.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newme.model.Pokemon
import com.example.newme.model.PokemonBattlePageUiState
import com.example.newme.ui.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonBattleViewModel @Inject constructor(
    public val repository: PokemonRepository,
): ViewModel() {

    init {
        getUserTeam()
        getEnemyTeam()
    }

    private val _uiState = MutableStateFlow(PokemonBattlePageUiState())

    val uiState: StateFlow<PokemonBattlePageUiState> = _uiState.asStateFlow()

    fun onFightClicked() {
        _uiState.value.battleMenuState = BattleMenu.ATTACK
    }

    fun attackMenuOnBackClicked() {
        _uiState.value.battleMenuState = BattleMenu.MAIN
    }

    private fun getUserTeam() {
        viewModelScope.launch(Dispatchers.IO) {


            repository.userTeam.collect {
                _uiState.update { currentState ->
                    currentState.copy(
                        userTeam = it
                    )
                }
                Log.d("Sammy", "pokemon: $it")
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