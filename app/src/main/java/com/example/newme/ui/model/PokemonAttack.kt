package com.example.newme.ui.model

data class PokemonAttack(
    var name: String = "None",
    var type: PokemonType = PokemonType.ELECTRIC,
    var level: Int = 1,
)
