package br.com.dataeasy.visualizador.negocio.service;

import org.junit.Assert;
import org.junit.Test;

import br.com.dataeasy.visualizador.mock.InjectedMock;
import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.validacao.visualizador.ValidadorAberturaDocumento;

/**
 * <b>Description:</b>Teste para VisualizacaoDocumentoService.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 *    Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11 de abr de 2016
 */
public class VisualizacaoDocumentoServiceTest extends AbstractServiceTest<VisualizacaoDocumentoService> {

    @InjectedMock
    private ValidadorAberturaDocumento validadorAberturaDocumento ;

    @Test
    public void testComBinarioNulo() {
        Binario binario = null;

        validadorAberturaDocumento.validar(binario);
        replayAll();

        instancia.ajustarEValidarAbertura(binario);
        verifyAll();
    }

    @Test
    public void testComBinarioComCaminhoNaoNulo() {
        Binario binario = new Binario();

        validadorAberturaDocumento.validar(binario);
        replayAll();

        instancia.ajustarEValidarAbertura(binario);
        verifyAll();
    }

    @Test
    public void testComBinarioComCaminhoComEspaços() {
        String caminhoComEspacos = " /var/test/arquivo.bin ";
        Binario binario = new Binario(caminhoComEspacos, "application/pdf");

        validadorAberturaDocumento.validar(binario);
        replayAll();

        instancia.ajustarEValidarAbertura(binario);
        replayAll();

        Assert.assertEquals(caminhoComEspacos.trim(), binario.getCaminhoCompleto());
    }

    @Test
    public void testComBinarioMimeTypeMaiusculas() {
        String mimeType = " APPLICATION/PDF ";
        Binario binario = new Binario(" /var/test/arquivo.bin ", mimeType);

        validadorAberturaDocumento.validar(binario);
        replayAll();

        instancia.ajustarEValidarAbertura(binario);
        replayAll();

        Assert.assertEquals(mimeType.trim().toLowerCase(), binario.getMimeType());
    }

}
