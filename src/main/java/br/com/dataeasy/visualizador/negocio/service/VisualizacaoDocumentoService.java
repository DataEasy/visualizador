package br.com.dataeasy.visualizador.negocio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.validacao.visualizador.ValidadorAberturaDocumento;

/**
 * <b>Description:</b>Service para abertura de documento pelo visualizador.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11 de abr de 2016
 */
@Component
public class VisualizacaoDocumentoService {

    @Autowired
    private ValidadorAberturaDocumento validador;

    public void ajustarEValidarAbertura(Binario binario) {
        ajustarAtributos(binario);
        validador.validar(binario);
    }

    /**
     * Faz ajustes simples nas propriedades do binário, como mudança para 'todas minúsculas' no mime type.
     *
     * @param binario o Binário a ser visualizador
     */
    private void ajustarAtributos(Binario binario) {
        if (binario != null) {
            String caminho = binario.getCaminhoCompleto();
            if (caminho != null) {
                binario.setCaminhoCompleto(caminho.trim());
            }

            String mimeType = binario.getMimeType();
            if (mimeType != null) {
                binario.setMimeType(mimeType.trim().toLowerCase());
            }
        }
    }
}
