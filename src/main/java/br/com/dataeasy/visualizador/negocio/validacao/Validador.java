package br.com.dataeasy.visualizador.negocio.validacao;

import javax.annotation.PostConstruct;

import br.com.dataeasy.visualizador.negocio.excecoes.NegocioException;

/**
 * <b>Description:</b>Classe base para qualquer validador de negócio.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 23/09/2015
 */
public abstract class Validador<T> {

    @PostConstruct
    protected abstract void adicionarRegras();

    protected void lancarExcecaoSeHouverErros(ContextoValidacao contexto) {
        if (contexto.temErrosValidacao()) {
            throw new NegocioException(contexto.getErrosValidacao());
        }
    }
}
