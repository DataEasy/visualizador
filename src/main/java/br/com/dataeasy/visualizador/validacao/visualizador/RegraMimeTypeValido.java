package br.com.dataeasy.visualizador.validacao.visualizador;

import static br.com.dataeasy.visualizador.negocio.mensagens.MensagemValidacao.ERRO_VISUALIZADOR_MIME_TYPE_INVALIDO;
import static br.com.dataeasy.visualizador.util.Constantes.MIME_TYPE_PDF;
import static br.com.dataeasy.visualizador.util.Constantes.MIME_TYPE_TIFF;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.negocio.validacao.ContextoValidacao;
import br.com.dataeasy.visualizador.validacao.regra.AbstractRegraSimples;

/**
 * <b>Description:</b>Valida se mime type fornecido é inválido.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11 de abr de 2016
 */
@Component
public class RegraMimeTypeValido extends AbstractRegraSimples<Binario> {

    private static final String[] MIME_TYPES = { MIME_TYPE_PDF, MIME_TYPE_TIFF };

    @Override
    public void validar(Binario objeto, ContextoValidacao contexto) {
        String mimeType = objeto.getMimeType();
        if (mimeType != null) {
            verificarCondicao(Arrays.binarySearch(MIME_TYPES, mimeType) < 0, contexto, ERRO_VISUALIZADOR_MIME_TYPE_INVALIDO, mimeType);
        }
    }

}
