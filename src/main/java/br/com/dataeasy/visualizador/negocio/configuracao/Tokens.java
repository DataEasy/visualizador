package br.com.dataeasy.visualizador.negocio.configuracao;

import static br.com.dataeasy.visualizador.negocio.mensagens.MensagemValidacao.ERRO_VISUALIZADOR_PROBLEMA_RECUPERANDO_CONFIGS;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.dataeasy.visualizador.negocio.excecoes.NegocioException;
import br.com.dataeasy.visualizador.negocio.mensagens.Messages;

/**
 * <b>Description:</b>Classe que recupera informações do <code>tokens.properties</code>.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 04/05/2016
 */
public final class Tokens {

    private static Properties properties;

    private Tokens() {
        //
    }

    public static String get(String chave) {
        if (properties == null) {
            inicializarProperties();
        }

        return (String) properties.get(chave);
    }

    private static void inicializarProperties() {
        synchronized(Tokens.class) {
            try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("tokens.properties")) {
                properties = new Properties();
                properties.load(is);
            } catch (IOException e) {
                throw new NegocioException(Messages.get(ERRO_VISUALIZADOR_PROBLEMA_RECUPERANDO_CONFIGS), e);
            }
        }
    }
}
