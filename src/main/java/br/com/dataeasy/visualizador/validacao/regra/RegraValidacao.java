package br.com.dataeasy.visualizador.validacao.regra;

import br.com.dataeasy.visualizador.negocio.validacao.ContextoValidacao;

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
public interface RegraValidacao<T> {

    void validar(T objeto, ContextoValidacao contexto);

}
