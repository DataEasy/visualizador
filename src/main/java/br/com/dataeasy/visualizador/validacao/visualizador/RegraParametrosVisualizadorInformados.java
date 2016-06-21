package br.com.dataeasy.visualizador.validacao.visualizador;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.dataeasy.visualizador.negocio.mensagens.MensagemValidacao;
import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.negocio.validacao.ContextoValidacao;
import br.com.dataeasy.visualizador.util.Labels;
import br.com.dataeasy.visualizador.validacao.regra.AbstractRegraSimples;

/**
 * <b>Description:</b>Valida se parâmetros para o visualizador de documentos foram informados.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 *    Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11 de abr de 2016
 */
@Component
public class RegraParametrosVisualizadorInformados extends AbstractRegraSimples<Binario> {

    protected void validarCampoInformado(boolean condicao, ContextoValidacao contexto, String labelAtributo) {
        verificarCondicao(condicao, contexto, getMensagemFormatada(MensagemValidacao.ERRO_ATRIBUTO_NAO_FORNECIDO, labelAtributo));
    }

    @Override
    public void validar(Binario objeto, ContextoValidacao contexto) {
        validarCampoInformado(StringUtils.isBlank(objeto.getCaminhoCompleto()), contexto, Labels.CAMINHO_DO_ARQUIVO);
        validarCampoInformado(StringUtils.isBlank(objeto.getMimeType()), contexto, Labels.MIME_TYPE);
        validarCampoInformado(StringUtils.isBlank(objeto.getToken()), contexto, Labels.TOKEN_DE_AUTENTICACAO);
    }

}
