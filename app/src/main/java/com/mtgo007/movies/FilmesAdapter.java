package com.mtgo007.movies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mtgo0 on 28/03/2018.
 */

public class FilmesAdapter extends BaseAdapter {

    private ArrayList<Filme> filmes;
    private Context context;
    private String user;
    private DatabaseReference mDatabase;

    public FilmesAdapter(Context c, String user){
        this.context = c;
        this.filmes = new ArrayList<>();
        this.user = user;

        //data from database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(user).child("Filmes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.getValue() != null) {
                        try {
                            for (DataSnapshot child: dataSnapshot.getChildren()) {
                                filmes.add(child.getValue(Filme.class));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("Filmes Adapter", "Sem FIlmes");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Lista Filmes Adapter", "Cancelado");
            }
        });


    }

    @Override
    public int getCount() {
        return this.filmes.size();
    }

    @Override
    public Object getItem(int position) {
        return filmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Filme filme = this.filmes.get(position);
        View newView = LayoutInflater.from(this.context).inflate(R.layout.filme_layout, parent, false);

        TextView titulo = newView.findViewById(R.id.Filme_titulo);
        TextView Genero = newView.findViewById(R.id.Filme_genero);
        TextView diretor = newView.findViewById(R.id.Filme_Diretor);
        TextView ano = newView.findViewById(R.id.Filme_Ano);
        ImageView faixa = newView.findViewById(R.id.Filme_Faixa);


        titulo.setText(filme.getNome());
        Genero.setText(filme.getGenero());
        diretor.setText(filme.getDiretor());
        ano.setText(String.valueOf(filme.getAno()));

        if(filme.getFaixaEtaria().equals("L")){faixa.setBackgroundResource(R.drawable.livre);}
        if(filme.getFaixaEtaria().equals("10")){faixa.setBackgroundResource(R.drawable.dez);}
        if(filme.getFaixaEtaria().equals("12")){faixa.setBackgroundResource(R.drawable.doze);}
        if(filme.getFaixaEtaria().equals("14")){faixa.setBackgroundResource(R.drawable.quatorze);}
        if(filme.getFaixaEtaria().equals("16")){faixa.setBackgroundResource(R.drawable.dezeseis);}
        if(filme.getFaixaEtaria().equals("18")){faixa.setBackgroundResource(R.drawable.dezoito);}

        return newView;
    }
}
