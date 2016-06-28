package br.com.dataeasy.visualizador.validacao.visualizador;

import static br.com.dataeasy.visualizador.negocio.mensagens.MensagemValidacao.ERRO_VISUALIZADOR_TOKEN_NAO_PERMITIDO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import br.com.dataeasy.commons.criptografia.Criptografia;
import br.com.dataeasy.commons.criptografia.CriptografiaException;
import br.com.dataeasy.visualizador.negocio.configuracao.Tokens;
import br.com.dataeasy.visualizador.negocio.excecoes.NegocioException;
import br.com.dataeasy.visualizador.negocio.mensagens.MensagemValidacao;
import br.com.dataeasy.visualizador.negocio.mensagens.Messages;
import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.negocio.validacao.ContextoValidacao;
import br.com.dataeasy.visualizador.validacao.regra.AbstractRegraSimples;

/**
 * <b>Description:</b>Valida se o Token informado pelo cliente faz parte dos tokens autenticados.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 04 de mai de 2016
 */
@Component
public class RegraTokenPermitido extends AbstractRegraSimples<Binario> {

    @Override
    public void validar(Binario objeto, ContextoValidacao contexto) {
        String tokenEncriptado = objeto.getToken();
        if (StringUtils.isNotBlank(tokenEncriptado)) {
            String password = Tokens.get("password");

            String token;
            try {
                token = new Criptografia(password).decriptar(tokenEncriptado);
            } catch (CriptografiaException e) {
                token = null;
            }

            boolean autenticado = token == null ? false : isTokenPermitido(token);
            verificarCondicao(!autenticado, contexto, ERRO_VISUALIZADOR_TOKEN_NAO_PERMITIDO);
        }
    }

    private boolean isTokenPermitido(String tokenDesencriptado) {
        Properties props = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("tokens.properties");
        try {
            props.load(is);
        } catch (IOException e) {
            throw new NegocioException(Messages.get(MensagemValidacao.ERRO_VISUALIZADOR_AUTENTICACAO_CLIENTE), e);
        }
        return props.entrySet().stream().filter(entry -> entry.getValue().toString().equals(tokenDesencriptado)).count() > 0L;
    }

}
