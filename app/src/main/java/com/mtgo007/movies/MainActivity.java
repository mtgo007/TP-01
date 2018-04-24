package com.mtgo007.movies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase Reference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Button btnLogar = findViewById(R.id.logar);
        Button btnCadastar = findViewById(R.id.cadastrar);
        final EditText usuario = findViewById(R.id.usuario);
        final EditText senha = findViewById(R.id.senha);

        btnCadastar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastar.class);
                startActivity(intent);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Usuario user = new Usuario();
                user.setUsername(usuario.getText().toString());
                user.setSenha(senha.getText().toString());

                if(!usuario.getText().toString().equals("")){
                    mDatabase.child("users").child(user.getUsername()).child("senha").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            try {
                                if (snapshot.getValue() != null) {
                                    try {
                                        Log.e("TAG", "senha" + snapshot.getValue());
                                        if(user.getSenha().equals(snapshot.getValue().toString())){
                                            // your name values you will get here
                                            Toast.makeText(MainActivity.this, getString(R.string.bemvindo), Toast.LENGTH_SHORT).show();
                                            //start Movies Intent
                                            Bundle args = new Bundle();
                                            args.putCharSequence("User",user.getUsername());
                                            Intent intent = new Intent(MainActivity.this, ListaFilmes.class);
                                            intent.putExtras(args);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(MainActivity.this, getString(R.string.senha_invalida), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, getString(R.string.usuario_invalido), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError firebaseError) {
                            Log.e("onCancelled", " cancelled");
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this, getString(R.string.campos_nulos),Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
