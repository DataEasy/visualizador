package br.com.dataeasy.visualizador;

/**
 * <b>Description:</b>Exceção de infraestrutura ao utilizar o Visualizador.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 16/06/2015
 */
public class VisualizadorInfraException extends RuntimeException {

    private static final long serialVersionUID = 8528033505955931062L;

    public VisualizadorInfraException() {
        super();
    }

    /**
     * @param message mensagem de erro
     * @param cause o Throwable causa
     * @param enableSuppression habilitar supressão de exceção
     * @param writableStackTrace define se o Stacktrace deve ser 'escrevível'
     */
    public VisualizadorInfraException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @param message a mensagem de erro
     * @param cause o Throwable causa do erro
     */
    public VisualizadorInfraException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message a mensagem de erro
     */
    public VisualizadorInfraException(String message) {
        super(message);
    }

    /**
     * @param cause o Throwable causa do erro
     */
    public VisualizadorInfraException(Throwable cause) {
        super(cause);
    }
}
