package com.mtgo007.movies;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class details extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Filme movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final TextView titulo = findViewById(R.id.detalhe_titulo);
        final TextView diretor = findViewById(R.id.detalhe_diretor);
        final TextView genero = findViewById(R.id.detalhe_genero);
        final TextView ano = findViewById(R.id.detalhe_ano);
        final ImageView faixa = findViewById(R.id.detalhe_faixa);
        final Button deleta = findViewById(R.id.details_remove);
        final Button share = findViewById(R.id.details_share);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Bundle args = this.getIntent().getExtras();
        final String name = args.getString("User");
        final String filme = args.getString("Filme");

        mDatabase.child("users").child(name).child("Filmes").child(filme).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                movie = dataSnapshot.getValue(Filme.class);

                titulo.setText(movie.getNome());
                diretor.setText(movie.getDiretor());
                genero.setText(movie.getGenero());
                ano.setText(""+movie.getAno());
                if(movie.getFaixaEtaria().equals("L")){faixa.setBackgroundResource(R.drawable.livre);}
                if(movie.getFaixaEtaria().equals("10")){faixa.setBackgroundResource(R.drawable.dez);}
                if(movie.getFaixaEtaria().equals("12")){faixa.setBackgroundResource(R.drawable.doze);}
                if(movie.getFaixaEtaria().equals("14")){faixa.setBackgroundResource(R.drawable.quatorze);}
                if(movie.getFaixaEtaria().equals("16")){faixa.setBackgroundResource(R.drawable.dezeseis);}
                if(movie.getFaixaEtaria().equals("18")){faixa.setBackgroundResource(R.drawable.dezoito);}

                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Titulo: "+titulo.getText().toString()+"\nDiretor: "+diretor.getText().toString()+"\nGenêro: "+genero.getText().toString()+"\nFaixa Étaria: "+movie.getFaixaEtaria()+"\nAno: "+ano.getText().toString());
                        sendIntent.setType("text/plain");
                        details.this.startActivity(Intent.createChooser(sendIntent, "Compartilhar"));
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        deleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comfirmação

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(details.this);

                // Define Parâmetros para o Dialog
                alertBuilder.setTitle("Deseja Deletar o Filme "+filme);
                alertBuilder.setMessage("O Filme Será Deletado de Sua Lista");

                // Define o que acontece quando o usuário seleciona a opção positiva
                alertBuilder.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("users").child(name).child("Filmes").child(filme).removeValue();
                        Toast.makeText(details.this, "Filme Deletado", Toast.LENGTH_LONG).show();
                        details.this.finish();
                    }
                });

                // Define o que acontece quando o usuário seleciona a opção negativa
                alertBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(details.this, "Filme Não Deletado", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = alertBuilder.create();
                dialog.show();
            }
        });



    }
}
