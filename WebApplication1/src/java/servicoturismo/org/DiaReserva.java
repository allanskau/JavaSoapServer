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
public class DiaReserva {
    private final Date dia;    
    private int reservados;
    private int limite;

    public Date getDia() {
        return dia;
    }
    
    public int getReservados() {
        return reservados;
    }

    public boolean reservar(int reservar) {
        if (this.limite-this.reservados>=reservar)
        {
            this.reservados += reservar;
            return true;
        }        
        return false;
    }
    public boolean disponivel(int vagas) {
        return this.limite>=vagas+reservados;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
    
    public boolean liberar(int liberar){
        if (this.reservados>=liberar)
        {
            this.reservados -= liberar;
            return true;
        }
                
        return false;
    }
    public DiaReserva(Date dia, int limite) {
        this.dia = dia;
        this.limite = limite;
    }
    @Override
    public String toString(){
        return dia.toLocaleString() + " " + reservados;
    }
}
