package com.example.newme.model

data class PokemonAttack(
    var name: String = "None",
    var type: PokemonType = PokemonType.ELECTRIC,
    var level: Int = 1,
)
