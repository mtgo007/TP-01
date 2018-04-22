package com.mtgo007.movies;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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

    public FilmesAdapter(Context c, ArrayList<Filme> movies, String user){
        this.context = c;
        this.filmes = movies;
        this.user =user;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Filme filme = this.filmes.get(position);
        final View newView = LayoutInflater.from(this.context).inflate(R.layout.filme_layout, parent, false);

        final TextView titulo = newView.findViewById(R.id.Filme_titulo);
        final TextView Genero = newView.findViewById(R.id.Filme_genero);
        final TextView diretor = newView.findViewById(R.id.Filme_Diretor);
        final TextView ano = newView.findViewById(R.id.Filme_Ano);


        final ImageView faixa = newView.findViewById(R.id.Filme_Faixa);



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

        newView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putCharSequence("User",user);
                args.putCharSequence("Filme",filme.getNome());
                Intent intent = new Intent(context, details.class);
                intent.putExtras(args);
                context.startActivity(intent);
            }
        });

        return newView;
    }
}
