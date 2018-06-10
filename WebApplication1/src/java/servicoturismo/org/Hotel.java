/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicoturismo.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author allan
 */
public class Hotel implements Serializable{
    
    private String cidade;
    private String nomeHotel;
    private int quartos;

    public int getQuartos() {
        return quartos;
    }
    public boolean reservaQuartos(int qtd){
        if (this.quartos>=qtd){
            this.quartos -= qtd;
            return true;
        }
        return false;
    }

    public Hotel(String cidade, String nomeHotel, int dispQuartos) {
        this.cidade = cidade;
        this.nomeHotel = nomeHotel;
        this.quartos = dispQuartos;
    }
    
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNomeHotel() {
        return nomeHotel;
    }

    public void setNomeHotel(String nomeHotel) {
        this.nomeHotel = nomeHotel;
    }
    
    @Override
    public String toString(){
        return  "Hotel: " + this.nomeHotel + "\tCidade: " + this.cidade;
    }
}
