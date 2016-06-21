package br.com.dataeasy.visualizador.negocio.configuracao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.easymock.EasyMock;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.dataeasy.visualizador.negocio.excecoes.NegocioException;

/**
 * <b>Description:</b>Testes para classe {@Tokens}.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 04/05/2016
 */
@RunWith(PowerMockRunner.class)
public class TokensTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void testGet() {
        Assert.assertEquals("JzNqRSZ7w6Ka6tPaIWFQ", Tokens.get("password"));
        Assert.assertEquals("br.com.dataeasy.comum.chronus.visualizador.VisualizacaoAutenticadaBemTokenizada", Tokens.get("chronus"));
    }

    @Test
    @PrepareForTest({ Tokens.class, Properties.class })
    public void testIOExceptionAoRecuperarProperties() throws Exception {
        String mensagemIOException = "blah";
        expected.expect(NegocioException.class);
        expected.expectCause(Is.isA(IOException.class));
        expected.expectMessage("Problema ao recuperar informações de configuração.");

        Properties props = PowerMock.createNiceMock(Properties.class);
        Whitebox.setInternalState(Tokens.class, Properties.class, (Properties) null);

        PowerMock.expectNew(Properties.class).andReturn(props);
        props.load(EasyMock.<InputStream>anyObject());
        PowerMock.expectLastCall().andThrow(new IOException(mensagemIOException));

        PowerMock.replayAll(Tokens.class, props);
        Tokens.get("chaveTesteInexistente");
        PowerMock.replayAll();
    }

}
