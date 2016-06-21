package br.com.dataeasy.visualizador.validacao.visualizador;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import br.com.dataeasy.visualizador.negocio.mensagens.MensagemValidacao;
import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.negocio.validacao.ContextoValidacao;
import br.com.dataeasy.visualizador.validacao.regra.AbstractRegraSimples;

/**
 * <b>Description:</b>Valida se caminho de arquivo fornecido é inválido.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11 de abr de 2016
 */
@Component
public class RegraCaminhoDeArquivoValido extends AbstractRegraSimples<Binario> {

    @Override
    public void validar(Binario objeto, ContextoValidacao contexto) {
        String caminho = objeto.getCaminhoCompleto();
        if (caminho != null) {
            verificarCondicao(Files.notExists(Paths.get(caminho)), contexto, MensagemValidacao.ERRO_VISUALIZADOR_ARQUIVO_NAO_ENCONTRADO, caminho);
        }
    }

}
