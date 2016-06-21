package br.com.dataeasy.visualizador.validacao.visualizador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.negocio.validacao.ValidadorSimples;

/**
 * <b>Description:</b>Validador que valida informações do binário fornecido para abertura.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11 de abr de 2016
 */
@Component
public class ValidadorAberturaDocumento extends ValidadorSimples<Binario> {

    @Autowired
    private RegraParametrosVisualizadorInformados regraParametrosVisualizadorInformados;

    @Autowired
    private RegraCaminhoDeArquivoValido           regraCaminhoDeArquivoValido;

    @Autowired
    private RegraMimeTypeValido                   regraMimeTypeValido;

    @Autowired
    private RegraTokenPermitido                   regraTokenPermitido;

    @Override
    protected void adicionarRegras() {
        registrarRegra(regraParametrosVisualizadorInformados);
        registrarRegra(regraCaminhoDeArquivoValido);
        registrarRegra(regraMimeTypeValido);
        registrarRegra(regraTokenPermitido);
    }

}
