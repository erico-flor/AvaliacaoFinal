package br.com.senai.avaliacaofinal.Modelo;

import java.io.Serializable;

public class Local implements Serializable {

    private int id;
    private String descricao;
    private String bairro;
    private String cidade;
    private String lotacao;

    public Local(int id, String descricao, String bairro, String cidade, String lotacao) {
        this.id = id;
        this.descricao = descricao;
        this.bairro = bairro;
        this.cidade = cidade;
        this.lotacao = lotacao;
    }

    public Local(int id) {
        this.id = id;
    }

    public Local(String nome) {
        this.descricao = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLotacao() {
        return lotacao;
    }

    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}



