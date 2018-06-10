/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicoturismo.org;

/**
 *
 * @author Naiane
 */
public class ErroDiaException extends Exception {
    private static final long serialVersionUID = 1149241039409861914L;
    public ErroDiaException(String msg){
        super(msg);
    }
    public ErroDiaException(String msg, Throwable cause){
        super(msg, cause);
    }
}
