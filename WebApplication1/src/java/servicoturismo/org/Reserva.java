/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicoturismo.org;

import java.util.Date;

/**
 *
 * @author Naiane
 */
public class Reserva {

    public int getId() {
        return id;
    }

    public int getCartao() {
        return cartao;
    }

    public int getQtd() {
        return qtd;
    }

    public Date getEntrada() {
        return entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public boolean isHospedagem() {
        return hospedagem;
    }
    private final int id;
    private final int cartao;
    private final int qtd;
    private Date entrada;
    private Date saida;
    private final boolean hospedagem;
    int parcelamento;

    public Reserva(int id, int cartao, int qtd, int parcelamento) {
        this.id = id;
        this.cartao = cartao;
        this.qtd = qtd;
        this.hospedagem = false;
        this.parcelamento = parcelamento;
        this.entrada = new Date();
        this.saida = new Date();
    }

    public Reserva(int id, int cartao, int qtd, Date Entrada, Date Saida, int parcelamento) {
        this.id = id;
        this.cartao = cartao;
        this.parcelamento = parcelamento;
        this.qtd = qtd;
        this.entrada = Entrada;
        this.saida = Saida;
        this.hospedagem = true;
    }
    
    @Override
    public String toString(){
       if (this.hospedagem)
          return id + " - " + qtd + " - " + entrada.toLocaleString().split(" ")[0] + " - " + saida.toLocaleString().split(" ")[0] + " - " + cartao+ " - " + parcelamento;
       return id + " - " + qtd + " - " + cartao+ " - " + parcelamento;
    }
    
}
