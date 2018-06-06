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
import javax.jws.WebParam;

/**
 *
 * @author allan
 */
@WebService(serviceName = "ServicoTurismo")
public class ServicoTurismo {

    private final List<Voo> voos = new ArrayList<>();

    /**
     * This is a sample web service operation
     *
     * @param novoVoo
     */
    @WebMethod(operationName = "inserirVoo")
    public void inserirVoo(String novoVoo) {
        //                  0           1       2        3        4
        // novoVoo = companhiaAerea - data - origem - destino - nVagas
        String[] msgDividida = novoVoo.split("-");
        String companhiaAerea = msgDividida[0];
        String dt = msgDividida[1];
        Date data = null;
        try {
            data = new SimpleDateFormat("dd/MM/yyyy").parse(dt);
        } catch (ParseException ex) {
            System.out.println("Erro ao converter String para data");
        }
        String origem = msgDividida[2];
        String destino = msgDividida[3];
        int nVagas = Integer.parseInt(msgDividida[4]);
        Voo novo = new Voo(companhiaAerea, data, origem, destino, nVagas);
        voos.add(novo);
    }

    /**
     * This is a sample web service operation
     *
     * @return String - lista de voos em uma String concatenada
     */
    @WebMethod(operationName = "listarVoos")
    public String listarVoos() {
        if (voos.size() > 0) {
            Voo v = null;
            String s = "\nVoos disponiveis:\n";
            s += "Companhia - Data - Origem - Destino - Assentos \n";
            for (int i = 0; i < voos.size(); i++) {
                v = voos.get(i);
                s += v.getCompanhiaAerea();
                SimpleDateFormat dataBr = new SimpleDateFormat("dd/MM/yyyy");
                s += " - " + dataBr.format(v.getData());
                //s += " - " + String.valueOf(v.getData());
                s += " - " + v.getOrigem();
                s += " - " + v.getDestino();
                s += " - " + v.getnVagas();
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
    public List<Voo> obterVoos() {
        return voos;
    }

    /**
     * This is a sample web service operation
     *
     * @return String - lista de voos em uma String concatenada
     */
    @WebMethod(operationName = "consultarVoosIda")
    public String consultarVoosIda(String origem, String destino, String data, String nPassageiros, List<String> idade) {
        if (voos.size() > 0) {
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
            for (int i = 0; i < voos.size(); i++) {
                v = voos.get(i);
                if ((v.getOrigem().equalsIgnoreCase(origem)) && (v.getDestino().equalsIgnoreCase(destino))
                        && (v.getData().equals(d)) && (v.getnVagas() > passageiros)) {
                    s += v.getCompanhiaAerea();
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
    }

}
