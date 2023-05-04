package com.example.newme.ui.repository

import com.example.newme.ui.model.toPokemon
import com.example.newme.ui.services.PokemonService
import kotlinx.coroutines.flow.flow

class PokemonRepository(
    private val pokemonService: PokemonService,
) {
    val pokemons = flow {
        emit(pokemonService.getPokemons())
    }

    val pokemonFromNetwork = flow {
        emit(pokemonService.getPokemonFromNetwork().toPokemon())
    }

    fun getPokemonById(id: Int) = flow {
        emit(pokemonService.getPokemonById(id).toPokemon())
    }

    val userTeam = flow {
        emit(pokemonService.getUserTeam().toPokemon())
    }

    val enemyTeam = flow {
        emit(pokemonService.getUserTeam().toPokemon())
    }
}