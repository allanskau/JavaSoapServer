/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicoturismo.org;

import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author Naiane
 */
@WebService(serviceName = "HospedagemWebService")
public class HospedagemWebService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public void hello() {
        
    }
}
