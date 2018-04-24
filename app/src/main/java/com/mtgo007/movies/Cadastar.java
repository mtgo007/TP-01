package com.mtgo007.movies;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastar extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastar);

        Button btn = findViewById(R.id.cad);
        final EditText user = findViewById(R.id.Username);
        final EditText senha = findViewById(R.id.Senha);
        final EditText cSenha = findViewById(R.id.ConfirmaSenha);

        //init DB
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!user.getText().toString().equals("")&&!senha.getText().toString().equals("")&&!cSenha.getText().toString().equals("")){
                    if(senha.getText().toString().equals(cSenha.getText().toString())){
                        Usuario newUser = new Usuario();
                        newUser.setUsername(user.getText().toString());
                        newUser.setSenha(senha.getText().toString());
                        mDatabase.child("users").child(user.getText().toString()).setValue(newUser);
                        Toast.makeText(Cadastar.this, R.string.cadastro_sucesso, Toast.LENGTH_SHORT).show();
                        Cadastar.this.finish();
                    }else{
                        Toast.makeText(Cadastar.this,R.string.Senhas_diferentes, Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(Cadastar.this, R.string.campos_nulos, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
