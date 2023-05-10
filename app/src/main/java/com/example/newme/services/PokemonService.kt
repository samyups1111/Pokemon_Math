package com.example.newme.services

import com.example.newme.model.Pokemon
import com.example.newme.model.PokemonFromNetwork
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemons(): List<Pokemon>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonFromNetwork

    @GET("pokemon/{id}")
    suspend fun getPokemonFromNetwork(@Path("id") id: Int): PokemonFromNetwork
}

class PokemonService @Inject constructor() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val pokemonApi = retrofit.create(PokemonApi::class.java)

    suspend fun getPokemons(): List<Pokemon> {
        return pokemonApi.getPokemons()
    }

    suspend fun getPokemonFromNetwork(): PokemonFromNetwork {
        return pokemonApi.getPokemonFromNetwork(132)
    }

    suspend fun getPokemonById(id: Int): PokemonFromNetwork {
        return pokemonApi.getPokemonById(id = id)
    }

    suspend fun getUserTeam(): List<PokemonFromNetwork> {

        val list = mutableListOf<PokemonFromNetwork>()
        repeat(6) {
            val randomPokemon = getPokemonById((1..150).random())
            list.add(randomPokemon)
        }

        return list
    }
}