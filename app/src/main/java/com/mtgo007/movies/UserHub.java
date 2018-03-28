package com.mtgo007.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserHub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hub);

        //Recuperando nome do usuario
        Bundle args = this.getIntent().getExtras();
        final String name = args.getString("User");

        Button add = findViewById(R.id.addFilmes);
        Button listar = findViewById(R.id.listarFilmes);
        TextView hello = findViewById(R.id.userHello);

        hello.setText("Bem Vindo "+ name);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putCharSequence("User",name);
                Intent intent = new Intent(UserHub.this, AddFilme.class);
                intent.putExtras(args);
                startActivity(intent);
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putCharSequence("User",name);
                Intent intent = new Intent(UserHub.this, ListaFilmes.class);
                intent.putExtras(args);
                startActivity(intent);
            }
        });
    }
}
