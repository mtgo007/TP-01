package com.mtgo007.movies;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ListaFilmes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        Bundle args = this.getIntent().getExtras();
        final String name = args.getString("User");
        FilmesAdapter fadaper = new FilmesAdapter(this, name);

        ListView filmes = findViewById(R.id.filmesView);
        filmes.setAdapter(fadaper);
        fadaper.notifyDataSetChanged();
    }
}
