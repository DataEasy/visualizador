package br.com.dataeasy.visualizador.validacao.regra;

import br.com.dataeasy.visualizador.negocio.mensagens.Messages;
import br.com.dataeasy.visualizador.negocio.validacao.ContextoValidacao;

/**
 * <b>Description:</b>Regra base para outros tipos de regras simples e compostas.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 *    Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 10/09/2015
 */
public abstract class AbstractRegra {

    protected void verificarCondicao(boolean condicao, ContextoValidacao contextoValidacao, String mensagem, Object...parametros) {
        if (condicao) {
            String mensagemFormatada;
            if (parametros == null) {
                mensagemFormatada = getMensagemFormatada(mensagem);
            } else {
                mensagemFormatada = getMensagemFormatada(mensagem, parametros);
            }

            contextoValidacao.adicionarErro(mensagemFormatada);
        }
    }

    protected String getMensagemFormatada(String conteudoTemplate, Object... parametros) {
        return Messages.get(conteudoTemplate, parametros);
    }
}
