package com.example.starwars.StarWarsApi;

import com.example.starwars.Models.StarWarsResposta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StarWarsService {

    @GET("people")
    Call<StarWarsResposta> obterListaStarWars();

}
