package servicoturismo.org;


import java.io.Serializable;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author allan
 */
public class Voo implements Serializable{
    
    private final String companhiaAerea;
    private final Date data;
    private final String origem;
    private final String destino;
    private final int nVagas;

    public Voo(String companhiaAerea, Date data, String origem, String destino, int nVagas) {
        this.companhiaAerea = companhiaAerea;
        this.data = data;
        this.origem = origem;
        this.destino = destino;
        this.nVagas = nVagas;
    }

    public String getCompanhiaAerea() {
        return companhiaAerea;
    }

    public Date getData() {
        return data;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public int getnVagas() {
        return nVagas;
    }
    
    @Override
    public String toString(){
        return "Companhia: " + companhiaAerea + " Data: " + data + " Origem: " + origem + " destino: " + destino + " Vagas: " + nVagas;
    }
    
}
