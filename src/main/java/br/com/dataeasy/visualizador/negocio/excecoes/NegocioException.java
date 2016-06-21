package br.com.dataeasy.visualizador.negocio.excecoes;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * <b>Description:</b>Exceção de negócio.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 *    Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 7 de abr de 2016
 */
public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private List<String>      mensagens;

    public NegocioException(String mensagem) {
        super(mensagem);
        this.mensagens = new ArrayList<String>();
        this.mensagens.add(mensagem);
    }

    public NegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
        this.mensagens = new ArrayList<String>();
        this.mensagens.add(mensagem);
    }

    public NegocioException(List<String> mensagens) {
        this.mensagens = mensagens;
    }

    public NegocioException(List<String> mensagens, Throwable causa) {
        super(causa);
        this.mensagens = mensagens;
    }

    public String getMensagem() {
        return getMessage();
    }

    @Override
    public String getMessage() {
        return getMensagensTexto();
    }

    public List<String> getMensagens() {
        if (mensagens == null) {
            mensagens = new ArrayList<String>();
        }
        if (mensagens.isEmpty()) {
            mensagens.add(getMessage());
        }
        return mensagens;
    }

    public String getMensagensTexto() {
        return getMensagensTexto("\n");
    }

    public String getMensagensTexto(String separador) {
        StringBuilder sbMensagem = new StringBuilder();
        if (!getMensagens().isEmpty()) {
            for (String mensagem : getMensagens()) {
                if (StringUtils.isNotBlank(sbMensagem.toString())) {
                    sbMensagem.append(separador);
                }
                sbMensagem.append(mensagem);
            }
        }
        return sbMensagem.toString();
    }

}
