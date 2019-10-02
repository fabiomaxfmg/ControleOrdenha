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
public class Inseminacao {
    private int codigo;
    private Date data;
    private int situacao;
    private String observacao;
    private int brinco_vaca;
    private int cod_inseminador;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getBrinco_vaca() {
        return brinco_vaca;
    }

    public void setBrinco_vaca(int brinco_vaca) {
        this.brinco_vaca = brinco_vaca;
    }

    public int getCod_inseminador() {
        return cod_inseminador;
    }

    public void setCod_inseminador(int cod_inseminador) {
        this.cod_inseminador = cod_inseminador;
    }
    
}
