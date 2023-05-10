package com.example.newme.model

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName

data class Pokemon(
    var id: Int = 1,
    var name: String = "None",
    var moves: List<MoveTypes> = listOf(MoveTypes(Move("Struggle")), MoveTypes(Move("Empty Slot"))),
    var type: PokemonType = PokemonType.UNKNOWN,
    var sprites: Sprites = Sprites(),
    var level: Int = 1,
    var totalHealth: Int = 1,
    var currentHealth: Int = 1,
) {
    val percentageHealth: Float
        get() = (currentHealth / totalHealth).toFloat()

    val healthBarColor: Color
        get() = when (percentageHealth) {
            in 0.51..1.0 -> Color.Green
            in 0.21..0.5 -> Color.Yellow
            in 0.0..0.20 -> Color.Red
            else -> Color.Black
        }
}

data class PokemonFromNetwork(
    var id: Int,
    var name: String,
    var sprites: Sprites,
    @SerializedName("moves")
    var moves: List<MoveTypes>,
)

fun PokemonFromNetwork.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name.uppercase(),
        sprites = sprites,
        moves = moves,
    )
}

fun List<PokemonFromNetwork>.toPokemon(): List<Pokemon> {
    val pokemons = mutableListOf<Pokemon>()
    for (pokemonFromNetWork in this) {
        pokemons.add((pokemonFromNetWork.toPokemon()))
    }
    return pokemons
}

data class Sprites(
    @SerializedName("back_default")
    var backDefault: String? = null,
    @SerializedName("front_default")
    var frontDefault: String? = null,
)

data class MoveTypes(
    var move: Move,
)

data class Move(
    var name: String = "Struggle",
    var attackLevel: Int = 1,
)