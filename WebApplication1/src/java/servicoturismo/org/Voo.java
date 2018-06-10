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
    
    
    private final Date data;
    private final String origem;
    private final String destino;
    private final double valor;
    private int vagas;

    public Voo(String origem, String destino, double valor, Date data, int nVagas) {    
        this.data = data;
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.vagas = nVagas;
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

    public int getVagas() {
        return vagas;
    }
    public boolean consumirVagas(int quantidade){
        if (this.vagas>=quantidade)
        {
            this.vagas -= quantidade;
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return origem + "-" + destino + "-" + data.toLocaleString().split(" ")[0] + "-" + valor +"-" + vagas;
    }
    public String toString(int id){
        return id +"-" + origem + "-" + destino + "-" + data.toLocaleString().split(" ")[0] + "-" + valor +"-" + vagas;
    }
    
}
