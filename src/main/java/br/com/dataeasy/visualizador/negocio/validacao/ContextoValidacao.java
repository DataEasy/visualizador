package br.com.dataeasy.visualizador.negocio.validacao;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * <b>Description:</b>  <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 *    Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author euripedes.evangelist
 * @version Revision: $ Date: 12/06/2015
 */
public class ContextoValidacao {

    private List<String> errosValidacao = Lists.newArrayList();

    public void adicionarErro(String mensagem) {
        if (!errosValidacao.contains(mensagem)) {
            errosValidacao.add(mensagem);
        }
    }

    public List<String> getErrosValidacao() {
        return errosValidacao;
    }

    public boolean temErrosValidacao() {
        return !errosValidacao.isEmpty();
    }
}
