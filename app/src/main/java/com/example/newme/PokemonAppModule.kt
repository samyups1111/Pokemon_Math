package com.example.newme

import com.example.newme.ui.repository.PokemonRepository
import com.example.newme.services.PokemonService
import com.example.newme.ui.viewmodel.PokemonBattleViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonAppModule {

    @Provides
    @Singleton
    fun providePokemonBattleViewModel(
        pokemonRepository: PokemonRepository,
    ) = PokemonBattleViewModel(pokemonRepository)

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokemonService: PokemonService,
    ) = PokemonRepository(pokemonService)

    @Provides
    @Singleton
    fun providePokemonService() = PokemonService()
}