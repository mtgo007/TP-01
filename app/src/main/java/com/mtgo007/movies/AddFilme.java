package com.mtgo007.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFilme extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filme);
        //Dados do Bundle
        Bundle args = this.getIntent().getExtras();
        final String name = args.getString("User");

        //inicia db
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //dados do formulário
        Button btn = findViewById(R.id.add);
        final EditText nome = findViewById(R.id.nome);
        final EditText genero = findViewById(R.id.genero);
        final EditText diretor = findViewById(R.id.diretor);
        final EditText faixa = findViewById(R.id.faixaEtaria);
        final EditText ano = findViewById(R.id.ano);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filme filme = new Filme(nome.getText().toString(), genero.getText().toString(), diretor.getText().toString(), faixa.getText().toString(),Integer.parseInt(ano.getText().toString()));
                mDatabase.child("users").child(name).child("Filmes").child(filme.getNome()).setValue(filme);
                Toast.makeText(AddFilme.this, "Filme Adicionado com sucesso", Toast.LENGTH_SHORT);
                Log.i("AddFilme","Adicionado");
                AddFilme.this.finish();
            }
        });
    }
}