package com.mtgo007.movies;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFilme extends AppCompatActivity {

    private DatabaseReference mDatabase;

    //fontes
//    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filme);

        //Dados do Bundle
        Bundle args = this.getIntent().getExtras();
        final String name = args.getString("User");

        //select
        final Spinner faixa = (Spinner) findViewById(R.id.faixaEtaria);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> faixa_adapter = ArrayAdapter.createFromResource(this, R.array.faixaEtaria, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        faixa_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        faixa.setAdapter(faixa_adapter);

        //select
        final Spinner genero_spinner = (Spinner) findViewById(R.id.genero);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genero_adapter = ArrayAdapter.createFromResource(this, R.array.generos, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        genero_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        genero_spinner.setAdapter(genero_adapter);

        //inicia db
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //dados do formulário
        Button btn = findViewById(R.id.add);
        final EditText nome = findViewById(R.id.nome);
        final EditText diretor = findViewById(R.id.diretor);
        final EditText ano = findViewById(R.id.ano);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String censura = faixa.getSelectedItem().toString();
                String genero = genero_spinner.getSelectedItem().toString();
                Filme filme = new Filme(nome.getText().toString(), genero, diretor.getText().toString(), censura ,Integer.parseInt(ano.getText().toString()));
                mDatabase.child("users").child(name).child("Filmes").child(filme.getNome()).setValue(filme);
                Toast.makeText(AddFilme.this,"Filme Adicionado com Sucesso", Toast.LENGTH_LONG).show();
                AddFilme.this.finish();
            }
        });
    }
}
