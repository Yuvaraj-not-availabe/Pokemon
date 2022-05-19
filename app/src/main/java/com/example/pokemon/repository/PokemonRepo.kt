package com.example.pokemon.repository

import com.example.pokemon.data.remote.PokeApi
import com.example.pokemon.data.remote.responses.PokemonList
import com.example.pokemon.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepo @Inject constructor(private val api: PokeApi) {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit = limit, offset)
        } catch (e: Exception) {
            return Resource.Error("Error occurred")
        }
        return Resource.Success(response)
    }
}