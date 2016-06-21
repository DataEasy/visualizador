package br.com.dataeasy.visualizador.negocio.excecoes;

/**
 * <b>Description:</b>Exceção de negócio relacionada a problemas do Visualizador de Documentos.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 07/04/2016
 */
@SuppressWarnings("serial")
public class VisualizadorNegocioException extends NegocioException {

    public VisualizadorNegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public VisualizadorNegocioException(String message) {
        super(message);
    }
}
