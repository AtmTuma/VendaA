package br.com.venda.util.exception;

/**
 *
 * @author alex
 */
public class ErroSistema extends Exception{

    public ErroSistema(String message) {
        super(message);
    }
    
    public ErroSistema(String message, Throwable cause) {
        super(message, cause);
    }
}
