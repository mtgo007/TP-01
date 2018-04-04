package com.mtgo007.movies;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filme);
        //Dados do Bundle
        Bundle args = this.getIntent().getExtras();
        final String name = args.getString("User");

        //select
        final Spinner spinner = (Spinner) findViewById(R.id.faixaEtaria);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.faixaEtaria, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //inicia db
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //dados do formulário
        Button btn = findViewById(R.id.add);
        final EditText nome = findViewById(R.id.nome);
        final EditText genero = findViewById(R.id.genero);
        final EditText diretor = findViewById(R.id.diretor);
        final EditText ano = findViewById(R.id.ano);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String faixa = spinner.getSelectedItem().toString();
                System.out.println(faixa);
                Filme filme = new Filme(nome.getText().toString(), genero.getText().toString(), diretor.getText().toString(), faixa ,Integer.parseInt(ano.getText().toString()));
                mDatabase.child("users").child(name).child("Filmes").child(filme.getNome()).setValue(filme);
                Toast.makeText(AddFilme.this, "Filme Adicionado com sucesso", Toast.LENGTH_SHORT);
                Log.i("AddFilme","Adicionado");
                AddFilme.this.finish();
            }
        });
    }
}
