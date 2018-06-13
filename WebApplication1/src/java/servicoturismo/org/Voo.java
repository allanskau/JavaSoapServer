package servicoturismo.org;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<Reserva> reservas;

    public Voo(String origem, String destino, double valor, Date data, int nVagas) {    
        this.data = data;
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.vagas = nVagas;
        this.reservas = new ArrayList();
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
    synchronized public boolean consumirVagas(int quantidade, int cartao, int parcelamento){
        if (this.vagas>=quantidade)
        {
            this.vagas -= quantidade;
            this.reservas.add(new Reserva(reservas.size(), cartao, quantidade, parcelamento));
            return true;
        }
        return false;
    }

    public double getValor() {
        return valor;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
    
    @Override
    public String toString(){
        return origem + "-" + destino + "-" + data.toLocaleString().split(" ")[0] + "-" + valor +"-" + vagas;
    }
    public String toString(int id){
        return id +"-" + origem + "-" + destino + "-" + data.toLocaleString().split(" ")[0] + "-" + valor +"-" + vagas;
    }
    
}
