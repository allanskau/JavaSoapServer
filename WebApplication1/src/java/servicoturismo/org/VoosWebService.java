/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicoturismo.org;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author allan
 */
@WebService(serviceName = "ServicoTurismo")
public class VoosWebService {

    private final List<Voo> lista = new ArrayList<>();
    public VoosWebService() {
        
        for (int i = 15; i<30; i++){
            for(int j = 5; j<8; j++)
            {
            lista.add(new Voo("Curitiba", "Salvador", 350.28, new Date(2018-1900, j, i),20));
            lista.add(new Voo("Curitiba", "São Paulo", 220.48, new Date(2018-1900, j, i), 5));
            lista.add(new Voo("Curitiba", "Rio de Janeiro", 250.37, new Date(2018-1900, j, i), 10));
            lista.add(new Voo("Curitiba", "Brasilia", 327.99, new Date(2018-1900, j, i), 20));
            lista.add(new Voo("São Paulo", "Salvador", 230.28, new Date(2018-1900, j, i), 40));
            lista.add(new Voo("São Paulo", "Curitiba", 220.48, new Date(2018-1900, j, i), 5));
            lista.add(new Voo("São Paulo", "Rio de Janeiro", 150.28, new Date(2018-1900, j, i), 2));
            lista.add(new Voo("São Paulo", "Brasilia", 180.28, new Date(2018-1900, j, i), 2));
            lista.add(new Voo("Salvador", "Curitiba", 329.28, new Date(2018-1900, j, i), 6));
            lista.add(new Voo("Salvador", "São Paulo", 230.28, new Date(2018-1900, j, i), 3));
            lista.add(new Voo("Salvador", "Rio de Janeiro", 180.28, new Date(2018-1900, j, i), 8));
            lista.add(new Voo("Salvador", "Brasilia", 180.28, new Date(2018-1900, j, i), 5));
            lista.add(new Voo("Brasilia", "Curitiba", 249.37, new Date(2018-1900, j, i), 0));
            lista.add(new Voo("Brasilia", "São Paulo", 150.28, new Date(2018-1900, j, i), 20));
            lista.add(new Voo("Brasilia", "Rio de Janeiro", 140.28, new Date(2018-1900, j, i), 10));
            lista.add(new Voo("Brasilia", "Salvador", 290.28, new Date(2018-1900, j, i), 4));
            lista.add(new Voo("Rio de Janeiro", "Curitiba", 249.37, new Date(2018-1900, j, i), 8));
            lista.add(new Voo("Rio de Janeiro", "São Paulo", 150.28, new Date(2018-1900, j, i), 10));
            lista.add(new Voo("Rio de Janeiro", "Brasilia", 140.28, new Date(2018-1900, j, i), 5));
            lista.add(new Voo("Rio de Janeiro", "Salvador", 290.28, new Date(2018-1900, j, i), 6));
            }
        }
        
    }

    /**
     * This is a sample web service operation
     *
     * @param novoVoo
     */
    //@WebMethod(operationName = "inserirVoo")
    public void inserirVoo(String novoVoo) {
        //             0       1        2        3        4
        // novoVoo = data - origem - destino - valor - nVagas
        String[] msgDividida = novoVoo.split("-");
        String dt = msgDividida[0];
        Date data = null;
        try {
            data = new SimpleDateFormat("dd/MM/yyyy").parse(dt);
        } catch (ParseException ex) {
            System.out.println("Erro ao converter String para data");
        }
        String origem = msgDividida[2];
        Double valor = Double.parseDouble(msgDividida[3]);
        String destino = msgDividida[4];
        int nVagas = Integer.parseInt(msgDividida[4]);
        Voo novo = new Voo(origem, destino, valor, data, nVagas);
        lista.add(novo);
    }

    /**
     * This is a sample web service operation
     *
     * @return String - lista de voos em uma String concatenada
     */
    @WebMethod(operationName = "listarVoos")
    public String listarVoos() {
        if (lista.size() > 0) {
            Voo v = null;
            String s = "\nVoos disponiveis:\n";
            s += "Companhia - Data - Origem - Destino - Assentos \n";
            for (int i = 0; i < lista.size(); i++) {
                v = lista.get(i);                
                SimpleDateFormat dataBr = new SimpleDateFormat("dd/MM/yyyy");
                s += " - " + dataBr.format(v.getData());
                //s += " - " + String.valueOf(v.getData());
                s += " - " + v.getOrigem();
                s += " - " + v.getDestino();
                s += " - " + v.getVagas();
                s += "\n";
            }
            return s;
        } else {
            return "Nao existem voos cadastrados!";
        }
    }

    /**
     * This is a sample web service operation
     *
     * @return String - lista de voos em uma String concatenada
     */
    @WebMethod(operationName = "obterVoos")
    public List<String> obterVoos() {
        ArrayList<String> listaQuery = new ArrayList();
        int i = 1;
         for (Voo v : lista){ 
             listaQuery.add(v.toString(i));
             i++;
         }
        
        return listaQuery;
    }

    /**
     * This is a sample web service operation
     *
     * @param origem
     * @param destino
     * @param dataS
     * @param qtdmenores
     * @param qtdmaiores
     * 
     * @return String - lista de voos em uma String concatenada
     */
    @WebMethod(operationName = "consultarVoos")
    public  ArrayList<String> consultarVoos (String origem, String destino, String dataS, int qtdmenores, int qtdmaiores ){
        ArrayList<String> listaQuery = new ArrayList();
        String[] p = dataS.split("/");
        Date data = new Date(Integer.parseInt(p[2])-1900, Integer.parseInt(p[1])-1, Integer.parseInt(p[0]));
        //String ret= "";
        int id = 1;
        for (Voo v : lista){              
            
            if((v.getOrigem().equals(origem))&&(v.getDestino().equals(destino))&&(v.getVagas()>=qtdmaiores+qtdmenores)&&(v.getData().equals(data))){
                //listaQuery.add(v);
                listaQuery.add(v.toString(id));
            }
            id++;
        }
       return listaQuery;
    }
    @WebMethod(operationName = "reservarPassagem")
    public boolean reservarPassagem (int id, int quantidade, int cartao, int parcelamento){              
       return lista.get(id-1).consumirVagas(quantidade, cartao, parcelamento);
    }
    @WebMethod(operationName = "ExibirReservas")
    public List<String> getReservas(int idVoo){
        List<Reserva> reservasVoo = lista.get(idVoo-1).getReservas();
        List<String> ret = new ArrayList();
        for(Reserva r : reservasVoo){
            String nova = r.toString();
            ret.add(nova);
        }
        return ret;
    }
    @WebMethod(operationName = "ExibirTodasReservas")
    public List<String> getTodasReservas(){
        
        List<String> ret = new ArrayList();
        int i = 0;
        for(Voo v : lista){
            i++;
            List<Reserva> reservasVoo = v.getReservas();
            for(Reserva r : reservasVoo){
                String nova = r.toString();
                ret.add(i + " - " + nova);
            }
        }
            
        return ret;
    }
    
    /*public String consultarVoos(String origem, String destino, String data, String nPassageiros, List<String> idade) {
        if (lista.size() > 0) {
            Date d = null;
            try {
                d = new SimpleDateFormat("dd/MM/yyyy").parse(data);
            } catch (ParseException ex) {
                System.out.println("Erro ao converter String para data");
            }
            int passageiros = Integer.parseInt(nPassageiros);
            Voo v = null;
            int count = 0;
            String s = "\nVoos disponiveis:\n";
            s += "Companhia - Data - Origem - Destino - Assentos \n";
            for (int i = 0; i < lista.size(); i++) {
                v = lista.get(i);
                if ((v.getOrigem().equalsIgnoreCase(origem)) && (v.getDestino().equalsIgnoreCase(destino))
                        && (v.getData().equals(d)) && (v.getnVagas() > passageiros)) {
                    SimpleDateFormat dataBr = new SimpleDateFormat("dd/MM/yyyy");
                    s += " - " + dataBr.format(v.getData());
                    //s += " - " + String.valueOf(v.getData());
                    s += " - " + v.getOrigem();
                    s += " - " + v.getDestino();
                    s += " - " + v.getnVagas();
                    s += "\n";
                    count++;
                }
            }
            if (count == 0) {
                return "Nao existem voos disponiveis!";
            } else {
                return s;
            }
        } else {
            return "Nao existem voos disponiveis!";
        }
    }*/

}
