package br.com.senai.avaliacaofinal.Modelo;

import java.io.Serializable;

public class Evento implements Serializable {

    private int id;
    private String nome;
    private String data;
    private Local local;

    public Evento(int id, String nome, String data, Local local) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.local = local;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
     public int setId(){
        return this.id;
     }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Local getLocal() {
        return this.local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Evento: " + this.nome +
                " \nData: " + this.data +
                " \nLocal: " + this.getLocal() .getDescricao() +
                " \nBairro: " + this.getLocal().getBairro() +
                " \nCidade: " + this.getLocal().getCidade() +
                " \nLotacao: " + this.getLocal().getLotacao();
    }
}
