package com.example.newme.ui.repository

import com.example.newme.model.toPokemon
import com.example.newme.services.PokemonService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    public val pokemonService: PokemonService,
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