package com.example.starwars.StarWars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.starwars.Models.StarWars;
import com.example.starwars.Models.StarWarsResposta;
import com.example.starwars.R;
import com.example.starwars.StarWarsApi.StarWarsService;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private  static  final String TAG = "STARDEX";

    Retrofit retrofit;
    ListView listViewPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/people/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                */
         retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       // obterDados();



    }

    public void obterDados(View view) {

        StarWarsService service = retrofit.create(StarWarsService.class);
        Call<StarWarsResposta> starWarsRespostaCall = service.obterListaStarWars();
        starWarsRespostaCall.enqueue(new Callback<StarWarsResposta>() {
            @Override
            public void onResponse(Call<StarWarsResposta> call, Response<StarWarsResposta> response) {
                if (response.isSuccessful()){
                    StarWarsResposta starWarsResposta = response.body();
                    List<StarWars> listaStarWars = starWarsResposta.getResults();

                    for (int i = 0; i < listaStarWars.size() ; i++) {
                        StarWars people = listaStarWars.get(i);
                          Log.i(TAG, "Name: " + people.getName());
                          Log.i(TAG, "birth_year: " + people.getBirth_year());
                          Log.i(TAG, "eye_color: " + people.getEye_color());
                          Log.i(TAG, "height: " + people.getHeight());
                          Log.i(TAG, "homeworld: " + people.getHomeworld());




                    }

                } else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<StarWarsResposta> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}