package com.mtgo007.movies;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaFilmes extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ArrayList<Filme> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        movies = new ArrayList();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Bundle args = this.getIntent().getExtras();
        final String name = args.getString("User");

        final FilmesAdapter fadaper = new FilmesAdapter(this, movies, name);

        mDatabase.child("users").child(name).child("Filmes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                movies.removeAll(movies);
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    movies.add(data.getValue(Filme.class));
                }
                fadaper.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ListView filmes = findViewById(R.id.filmesView);
        filmes.setAdapter(fadaper);
    }
}
