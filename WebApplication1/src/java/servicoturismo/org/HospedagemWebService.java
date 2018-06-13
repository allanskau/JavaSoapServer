/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicoturismo.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Naiane
 */
@WebService(serviceName = "HospedagemWebService")
public class HospedagemWebService {
    private final List<Hotel> lista;

    public HospedagemWebService() {
        lista = new ArrayList<>();
        lista.add(new Hotel("Curitiba","Calton",20,200));
        lista.add(new Hotel("Curitiba","Dalton",30,120));
        lista.add(new Hotel("Curitiba","Palace",50,80));
        lista.add(new Hotel("Curitiba","Rilton",5,150));
        lista.add(new Hotel("Curitiba","Cristal",60,180));
        lista.add(new Hotel("Curitiba","Egito",12,60));
        lista.add(new Hotel("Salvador","Calton",20,200));
        lista.add(new Hotel("Salvador","Dalton",30,120));
        lista.add(new Hotel("Salvador","Palace",50,80));
        lista.add(new Hotel("Salvador","Rilton",5,150));
        lista.add(new Hotel("Salvador","Cristal",60,180));
        lista.add(new Hotel("Salvador","Egito",12,60));
    }
    
    /**
     * This is a sample web service operation
     * @return lista de todos os hoteis disponiveis em strings
     */
    @WebMethod(operationName = "ListarHospedagem")
    public List<String> listar(){
        ArrayList<String> listaQuery = new ArrayList();
        int i = 1;
         for (Hotel h : lista){ 
             listaQuery.add(h.toString(i));
             i++;
         }
        
        return listaQuery;
    }

    /**
     * Operação de Web service
     * @param cidade
     * @param dataEntrada
     * @param dataSaida
     * @param pessoas
     * @return 
     */
    @WebMethod(operationName = "consultaPorCidade")
    public List<String> consultaPorCidade(@WebParam(name = "cidade") String cidade, @WebParam(name = "dataEntrada") String dataEntrada, @WebParam(name = "dataSaida") String dataSaida, @WebParam(name = "pessoas") int pessoas) {
        ArrayList<String> listaQuery = new ArrayList();
        String[] p = dataEntrada.split("/");
        Date dataE = new Date(Integer.parseInt(p[2])-1900, Integer.parseInt(p[1])-1, Integer.parseInt(p[0]));
        String[] s = dataSaida.split("/");
        Date dataS = new Date(Integer.parseInt(s[2])-1900, Integer.parseInt(s[1])-1, Integer.parseInt(s[0]));
        //String ret= "";
        int id = 1;
        for (Hotel h : lista){              
            
            if((h.getCidade().equals(cidade.trim()))&&(h.consultaDisponivilidade(pessoas, dataE, dataS))){
                //listaQuery.add(v);
                listaQuery.add(h.toString(id));
            }
            id++;
        }
       return listaQuery;
    }
    /**
     * Operação de Web service
     * @param nome
     * @param dataEntrada
     * @param dataSaida
     * @param pessoas
     * @return 
     */
    @WebMethod(operationName = "consultaPorHotel")
    public List<String> consultaPorHotel(@WebParam(name = "nome") String nome, @WebParam(name = "dataEntrada") String dataEntrada, @WebParam(name = "dataSaida") String dataSaida, @WebParam(name = "pessoas") int pessoas) {
        ArrayList<String> listaQuery = new ArrayList();
        String[] p = dataEntrada.split("/");
        Date dataE = new Date(Integer.parseInt(p[2])-1900, Integer.parseInt(p[1])-1, Integer.parseInt(p[0]));
        String[] s = dataSaida.split("/");
        Date dataS = new Date(Integer.parseInt(s[2])-1900, Integer.parseInt(s[1])-1, Integer.parseInt(s[0]));
        //String ret= "";
        int id = 1;
        for (Hotel h : lista){              
            
            if((h.getNomeHotel().equals(nome.trim()))&&(h.consultaDisponivilidade(pessoas, dataE, dataS))){
                //listaQuery.add(v);
                listaQuery.add(h.toString(id));
            }
            id++;
        }
       return listaQuery;
    }

    /**
     * Operação de Web service
     * @param idHotel Id hotel no sistema
     * @param pessoas quantidade de pessoas cadastradas
     * @param dataEntrada data da entrada
     * @param dataSaida data da saida
     * @param cartao
     * @param parcelamento
     * @return  booleano diz respeito a reserva efetuada ou não
     * @throws servicoturismo.org.ErroDiaException
     */
    @WebMethod(operationName = "reserva")
    public boolean reserva(@WebParam(name = "idHotel") int idHotel, @WebParam(name = "pessoas") int pessoas, @WebParam(name = "dataEntrada") String dataEntrada, @WebParam(name = "dataSaida") String dataSaida, @WebParam(name = "cartao") int cartao, @WebParam(name = "parcelamento") int parcelamento) throws ErroDiaException {
        String[] p = dataEntrada.split("/");
        Date dataE = new Date(Integer.parseInt(p[2])-1900, Integer.parseInt(p[1])-1, Integer.parseInt(p[0]));
        String[] s = dataSaida.split("/");
        Date dataS = new Date(Integer.parseInt(s[2])-1900, Integer.parseInt(s[1])-1, Integer.parseInt(s[0]));
        //String ret= "";
        
        Hotel h = lista.get(idHotel-1);            
        return h.reservaQuartos(pessoas, cartao, parcelamento, dataE, dataS);
        
    }

    /**
     * Operação de Web service
     * @param id
     * @return 
     */
    @WebMethod(operationName = "exibirReservasHotel")
    public List<String> exibirReservasHotel(@WebParam(name = "id") int id) {
        List<String> ret = new ArrayList();
        for (Reserva r : lista.get(id).getReservas())
            ret.add(r.toString());
        
        return ret;
    }

    /**
     * Operação de Web service
     * @return 
     */
    @WebMethod(operationName = "exibirTodasReservas")
    public List<String> exibirTodasReservas() {
        List<String> ret = new ArrayList();
        for(Hotel h : lista){
            for (Reserva r : h.getReservas())
                ret.add(r.toString());
        }
        return ret;
    }
     /**
     * Operação de Web service
     * @param id
     * @return 
     */
    @WebMethod(operationName = "exibirReservasHotelPorDia")
    public List<String> exibirReservasHotelPorDia(@WebParam(name = "id") int id) {
        List<String> ret = new ArrayList();
        for (DiaReserva dr : lista.get(id).getVagasReservadas())
            ret.add(dr.toString());
       
        return ret;
    }

    /**
     * Operação de Web service
     * @return 
     */
    @WebMethod(operationName = "exibirTodasReservasPorDia")
    public List<String> exibirTodasReservasPorDia() {
        List<String> ret = new ArrayList();
        for(Hotel h : lista){
            for (DiaReserva dr : h.getVagasReservadas())
                ret.add(dr.toString());
        }
        return ret;
    }
    
    
    
}
