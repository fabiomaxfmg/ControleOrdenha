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
public class Producao {
    private Date data;
    private int producao_litros;
    private int nr_ordenha_uteis;
    private double mediapervaca;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getProducao_litros() {
        return producao_litros;
    }

    public void setProducao_litros(int producao_litros) {
        this.producao_litros = producao_litros;
    }

    public int getNr_ordenha_uteis() {
        return nr_ordenha_uteis;
    }

    public void setNr_ordenha_uteis(int nr_ordenha_uteis) {
        this.nr_ordenha_uteis = nr_ordenha_uteis;
    }

    public double getMediapervaca() {
        return mediapervaca;
    }

    public void setMediapervaca(double mediapervaca) {
        this.mediapervaca = mediapervaca;
    }
    
    
}
