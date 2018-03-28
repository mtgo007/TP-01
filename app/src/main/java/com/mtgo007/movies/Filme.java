package com.mtgo007.movies;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by mtgo0 on 27/03/2018.
 */

public class Filme {
    private String nome;
    private String genero;
    private String diretor;
    private String faixaEtaria;
    private int ano;

    public Filme(String nome, String genero, String diretor, String faixa, int Ano){
        super();
        this.nome = nome;
        this.genero = genero;
        this.diretor = diretor;
        this.faixaEtaria = faixa;
        this.ano = Ano;
    }
    public Filme(){

    }

    public void deleteMovie(DatabaseReference ref){
        ref.removeValue();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
