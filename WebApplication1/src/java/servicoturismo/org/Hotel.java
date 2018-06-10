/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicoturismo.org;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author allan
 */
public class Hotel implements Serializable{
    
    private String cidade;
    private String nomeHotel;
    private int quartos;
    private List<Reserva> reservas;
    private List<DiaReserva> vagasReservadas;
    double valorPorPessoa;

    public List<Reserva> getReservas() {
        return reservas;
    }

    public double getValorPorPessoa() {
        return valorPorPessoa;
    }

    public int getQuartos() {
        return quartos;
    }
    synchronized public boolean reservaQuartos(int quantidade, int cartao, int parcelamento, Date entrada, Date saida) throws ErroDiaException{
        List<DiaReserva> reservar = new ArrayList();
        if (entrada.compareTo(saida) >= 0)
            return false;
        if (this.quartos>=quantidade){            
            Date percorer = entrada;
            while (percorer.compareTo(saida)<=0){
                DiaReserva atual = new DiaReserva(percorer, quartos);
                boolean flag = false;
                for (DiaReserva dia : vagasReservadas)
                {
                    if(dia.getDia().compareTo(percorer)==0){
                        atual = dia;
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                   vagasReservadas.add(atual);
                }
                if(!atual.disponivel(quantidade)){
                    return false;
                }
                reservar.add(atual);
                //amenta um dia na data
                Calendar c = Calendar.getInstance();
                c.setTime(percorer);
                c.add(Calendar.DATE, +1);
                percorer = c.getTime();                
            }
            for (DiaReserva dia : reservar){
                if (!dia.reservar(quantidade)){
                    throw new ErroDiaException("Erro ao adicionar os quartos");
                }
            }
            this.reservas.add(new Reserva(reservas.size(), cartao, quantidade, entrada, saida, parcelamento));
            return true;
        }
        return false;
    }

    public List<DiaReserva> getVagasReservadas() {
        return vagasReservadas;
    }
    public boolean consultaDisponivilidade(int quantidade, Date entrada, Date saida){
        List<DiaReserva> reservar = new ArrayList();
        if (entrada.compareTo(saida) >= 0)
            return false;
        if (this.quartos>=quantidade){
            Date percorer = entrada;
            while (percorer.compareTo(saida)<=0){
                DiaReserva atual = new DiaReserva(percorer, quartos);
                boolean flag = false;
                for (DiaReserva dia : vagasReservadas)
                {
                    if(dia.getDia().compareTo(percorer)==0){
                        atual = dia;
                        flag = true;
                        break;
                    }
                }
                if(!atual.disponivel(quantidade)){
                    return false;
                }
                //amenta um dia na data
                Calendar c = Calendar.getInstance();
                c.setTime(percorer);
                c.add(Calendar.DATE, +1);
                percorer = c.getTime();                
            }
            return true;
        }
        return false;
    }
    public Hotel(String cidade, String nomeHotel, int dispQuartos, double valorPorPessoa) {
        this.cidade = cidade;
        this.nomeHotel = nomeHotel;
        this.quartos = dispQuartos;
        this.valorPorPessoa = valorPorPessoa;
        this.reservas = new ArrayList();  
        this.vagasReservadas = new ArrayList();
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
    public int vagasPorPeriodo(Date entrada, Date saida){
        int vagas = quartos;
        for (Reserva r : reservas){
        }
        return vagas;
    }
    @Override
    public String toString(){
        return  "Hotel: " + this.nomeHotel + "\tCidade: " + this.cidade + " - " + this.quartos;
    }
    public String toString(int id){
        return  id + " - " + this.nomeHotel + " - " + this.cidade + " - " + this.quartos;
    }
}
