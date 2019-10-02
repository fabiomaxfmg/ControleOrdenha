/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author fabio
 */
public class Vaca {
    private int brinco;
    private String nome;
    private String data_nascimento;
    private int cod_situacao;
    private int brinco_mae;
    private int cod_raca;
    private int cod_touro;

    public int getBrinco() {
        return brinco;
    }

    public void setBrinco(int brinco) {
        this.brinco = brinco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getCod_situacao() {
        return cod_situacao;
    }

    public void setCod_situacao(int cod_situacao) {
        this.cod_situacao = cod_situacao;
    }

    
    public int getBrinco_mae() {
        return brinco_mae;
    }

    public void setBrinco_mae(int brinco_mae) {
        this.brinco_mae = brinco_mae;
    }

    public int getCod_raca() {
        return cod_raca;
    }

    public void setCod_raca(int cod_raca) {
        this.cod_raca = cod_raca;
    }

    public int getCod_touro() {
        return cod_touro;
    }

    public void setCod_touro(int cod_touro) {
        this.cod_touro = cod_touro;
    }

    @Override
    public String toString() {
        return getNome(); //To change body of generated methods, choose Tools | Templates.
    }

    
}
