package br.com.dataeasy.visualizador.validacao.visualizador;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Properties;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.dataeasy.commons.criptografia.Criptografia;
import br.com.dataeasy.visualizador.negocio.configuracao.Tokens;
import br.com.dataeasy.visualizador.negocio.excecoes.NegocioException;
import br.com.dataeasy.visualizador.negocio.mensagens.MensagemValidacao;
import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.negocio.validacao.AbstractValidadorSimplesTest;
import br.com.dataeasy.visualizador.util.Constantes;
import br.com.dataeasy.visualizador.util.Labels;

/**
 * <b>Description:</b>Teste para ValidadorAberturaDocumento.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11 de abr de 2016
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.crypto.*")
public class ValidadorAberturaDocumentoTest extends AbstractValidadorSimplesTest<ValidadorAberturaDocumento, Binario> {

    private String getCaminhoArquivo(String caminhoRelativo) {
        try {
            return Paths.get(getClass().getResource(caminhoRelativo).toURI()).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testParametrosNaoPreenchidos() {
        Binario binario = new Binario();

        try {
            validar(binario);
        } catch (NegocioException e) {
            assertMensagemExistente(e, MensagemValidacao.ERRO_ATRIBUTO_NAO_FORNECIDO, Labels.CAMINHO_DO_ARQUIVO);
            assertMensagemExistente(e, MensagemValidacao.ERRO_ATRIBUTO_NAO_FORNECIDO, Labels.MIME_TYPE);
            assertMensagemExistente(e, MensagemValidacao.ERRO_ATRIBUTO_NAO_FORNECIDO, Labels.TOKEN_DE_AUTENTICACAO);
        }
    }

    @Test
    public void testCaminhoDeArquivoInvalido() {
        String caminho = "/teste/blah";
        Binario binario = new Binario(caminho, "application/pdf", getTokenValido());

        try {
            validar(binario);
        } catch (NegocioException e) {
            assertMensagemExistente(e, MensagemValidacao.ERRO_VISUALIZADOR_ARQUIVO_NAO_ENCONTRADO, caminho);
        }
    }

    private String getTokenValido() {
        String tokenChronus = Tokens.get("chronus");
        String senha = Tokens.get("password");
        return new Criptografia(senha).encriptar(tokenChronus);
    }

    @Test
    public void testMimeTypeInvalido() {
        String caminho = getCaminhoArquivo("/exemplo.pdf");
        String mimeType = "application/octet-stream";
        Binario binario = new Binario(caminho, mimeType, getTokenValido());

        try {
            validar(binario);
        } catch (NegocioException e) {
            assertMensagemExistente(e, MensagemValidacao.ERRO_VISUALIZADOR_MIME_TYPE_INVALIDO, mimeType);
        }
    }

    @Test
    public void testTokenVazio() {
        String caminho = getCaminhoArquivo("/exemplo.pdf");
        String mimeType = "application/octet-stream";
        Binario binario = new Binario(caminho, mimeType, "");

        try {
            validar(binario);
        } catch (NegocioException e) {
            assertMensagemExistente(e, MensagemValidacao.ERRO_ATRIBUTO_NAO_FORNECIDO, Labels.TOKEN_DE_AUTENTICACAO);
        }
    }

    @Test
    public void testTokenNaoPermitido() {
        String caminho = getCaminhoArquivo("/exemplo.pdf");
        String mimeType = "application/octet-stream";
        Binario binario = new Binario(caminho, mimeType, "TOKENNAOPERMITIDO8239749");

        try {
            validar(binario);
        } catch (NegocioException e) {
            assertMensagemExistente(e, MensagemValidacao.ERRO_VISUALIZADOR_TOKEN_NAO_PERMITIDO);
        }
    }

    @Test
    @PrepareForTest({ RegraTokenPermitido.class, Properties.class })
    public void testIOExceptionAoAutenticarToken() throws Exception {
        String caminho = getCaminhoArquivo("/exemplo.pdf");
        String mimeType = "application/octet-stream";
        String chaveErrada = new Criptografia(Tokens.get("password")).encriptar("Chave inexistente para autenticação.");
        Binario binario = new Binario(caminho, mimeType, chaveErrada);

        Properties props = PowerMock.createNiceMock(Properties.class);
        PowerMock.expectNew(Properties.class).andReturn(props);
        props.load(EasyMock.<InputStream> anyObject());
        PowerMock.expectLastCall().andThrow(new IOException("blah blah"));

        PowerMock.replay(RegraTokenPermitido.class, props);

        try {
            validar(binario);
        } catch (NegocioException e) {
            Assert.assertTrue(e.getCause().getClass().isAssignableFrom(IOException.class));
            Assert.assertEquals("Problema ao tentar autenticar cliente do Visualizador por token.", e.getMensagem());
        }
    }

    @Test
    public void testSucesso() {
        String caminho = getCaminhoArquivo("/exemplo.pdf");
        String mimeType = "application/pdf";
        String token = getTokenValido();

        Binario binario = new Binario(caminho, mimeType, token);
        validar(binario);

        // agora, utilizando mime types conforme Constantes
        binario.setMimeType(Constantes.MIME_TYPE_PDF);
        validar(binario);

        binario.setMimeType(Constantes.MIME_TYPE_TIFF);
        validar(binario);
    }

}
