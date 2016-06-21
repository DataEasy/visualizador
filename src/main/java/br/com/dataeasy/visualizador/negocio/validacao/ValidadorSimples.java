package br.com.dataeasy.visualizador.negocio.validacao;

import java.util.ArrayList;
import java.util.List;

import br.com.dataeasy.visualizador.validacao.regra.RegraValidacao;

/**
 * <b>Description:</b>Classe base para validador simples.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 *    Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author euripedes.evangelist
 * @version Revision: $ Date: 12/06/2015
 */
public abstract class ValidadorSimples<T> extends Validador<T> {

    private List<RegraValidacao<T>> regras = new ArrayList<RegraValidacao<T>>();

    public void validar(T objetoValidado) {
        ContextoValidacao contexto = new ContextoValidacao();
        adicionarErrosAoContexto(objetoValidado, contexto);
        lancarExcecaoSeHouverErros(contexto);
    }

    protected void registrarRegra(RegraValidacao<T> regra) {
        regras.add(regra);
    }

    private void adicionarErrosAoContexto(T objetoValidado, ContextoValidacao contexto) {
        for (RegraValidacao<T> regra : regras) {
            regra.validar(objetoValidado, contexto);
        }
    }

    public List<RegraValidacao<T>> getRegras() {
        return regras;
    }

}
