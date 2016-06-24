package br.com.dataeasy.visualizador.wicket.pages;

import static br.com.dataeasy.visualizador.util.Constantes.MIME_TYPE_PDF;
import static org.easymock.EasyMock.and;
import static org.easymock.EasyMock.isA;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;
import org.powermock.reflect.Whitebox;

import br.com.dataeasy.visualizador.config.ApplicationConfig;
import br.com.dataeasy.visualizador.config.InformacoesGroupDocs;
import br.com.dataeasy.visualizador.config.VisualizadorConfig;
import br.com.dataeasy.visualizador.mock.InjectedMock;
import br.com.dataeasy.visualizador.negocio.excecoes.NegocioException;
import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.negocio.service.VisualizacaoDocumentoService;
import br.com.dataeasy.visualizador.wicket.WicketTestPageBase;

import com.groupdocs.annotation.handler.AnnotationHandler;

/**
 * <b>Description:</b> <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 7 de abr de 2016
 */
public class VisualizadorPageTest extends WicketTestPageBase<VisualizadorPage> {

    private static final String          TOKEN_TESTE = "98a7dsf87a0sdf0a9dsf";

    @InjectedMock
    private VisualizadorConfig           visualizadorConfig;

    @InjectedMock
    private ApplicationConfig            applicationConfig;

    @InjectedMock
    private VisualizacaoDocumentoService visualizacaoDocumentoService;

    private Capture<Binario>             binarioCapturado;
    private Capture<String>              usernameCapturado;

    private String                       caminho;

    @Override
    protected void criarMocksParaTesteQueRenderizaPagina() {
        binarioCapturado = new Capture<Binario>();
        usernameCapturado = new Capture<String>();

        visualizacaoDocumentoService.ajustarEValidarAbertura(and(isA(Binario.class), EasyMock.capture(binarioCapturado)));

        EasyMock.expect(
                visualizadorConfig.getInformacoesParaVisualizacao(and(isA(Binario.class), EasyMock.capture(binarioCapturado)),
                        EasyMock.capture(usernameCapturado))).andReturn(new InformacoesGroupDocs());

        visualizadorConfig.configurar(EasyMock.<Binario>anyObject());
    }

    @Override
    protected void iniciarPagina() {
        tester.startPage(VisualizadorPage.class);
    }

    private void definirParametrosRequestParaBinario() {
        caminho = getClass().getResource("/exemplo.pdf").getFile();
        tester.getRequest().addParameter(VisualizadorPage.PARAM_CAMINHO, caminho);
        tester.getRequest().addParameter(VisualizadorPage.PARAM_MIME_TYPE, MIME_TYPE_PDF);
        tester.getRequest().addParameter(VisualizadorPage.PARAM_TOKEN, TOKEN_TESTE);
    }

    @Test
    @Override
    public void testQueRenderizaPagina() {
        definirParametrosRequestParaBinario();

        super.testQueRenderizaPagina();

        Assert.assertEquals(AnnotationHandler.ANONYMOUS_USERNAME, usernameCapturado.getValue());
        Assert.assertEquals(caminho, binarioCapturado.getValue().getCaminhoCompleto());
        Assert.assertEquals(MIME_TYPE_PDF, binarioCapturado.getValue().getMimeType());
        Assert.assertEquals(TOKEN_TESTE, binarioCapturado.getValue().getToken());
    }

    @Test
    public void testQueRenderizaPaginaComParametrosVisualizadorVazio() {
        definirParametrosRequestParaBinario();
        String parametrosVisualizador = "";
        tester.getRequest().addParameter(VisualizadorPage.PARAM_PARAMETROS, parametrosVisualizador);

        super.testQueRenderizaPagina();

        Assert.assertEquals(AnnotationHandler.ANONYMOUS_USERNAME, usernameCapturado.getValue());
        Assert.assertEquals(caminho, binarioCapturado.getValue().getCaminhoCompleto());
        Assert.assertEquals(MIME_TYPE_PDF, binarioCapturado.getValue().getMimeType());
        Assert.assertEquals(TOKEN_TESTE, binarioCapturado.getValue().getToken());
    }

    @Test
    public void testQueRenderizaPaginaComParametrosVisualizadorValidos() throws UnsupportedEncodingException {
        definirParametrosRequestParaBinario();
        Map<String, Object> parametrosTeste = new HashMap<String, Object>();
        parametrosTeste.put("param1", "teste");
        parametrosTeste.put("param.booleano", "true");
        parametrosTeste.put("param.numerico", "150");
        parametrosTeste.put("groupdocs.annotation.isCaseSensitive", "false");

        String parametrosVisualizador = "";
        Iterator<Entry<String, Object>> iter = parametrosTeste.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Object> entry = iter.next();
            parametrosVisualizador += entry.getKey() + "=" + entry.getValue();
            if (iter.hasNext()) {
                parametrosVisualizador += "&";
            }
        }

        tester.getRequest().addParameter(VisualizadorPage.PARAM_PARAMETROS, parametrosVisualizador);

        super.testQueRenderizaPagina();

        Assert.assertEquals(AnnotationHandler.ANONYMOUS_USERNAME, usernameCapturado.getValue());
        Assert.assertEquals(caminho, binarioCapturado.getValue().getCaminhoCompleto());
        Assert.assertEquals(MIME_TYPE_PDF, binarioCapturado.getValue().getMimeType());
        Assert.assertEquals(TOKEN_TESTE, binarioCapturado.getValue().getToken());

        // verificando se foi criado mapa de parâmetros de acordo com parâmetro da requisição de nome 'parametros' correspondente aos parâmetros
        // fornecidos
        VisualizadorPage page = (VisualizadorPage) tester.getLastRenderedPage();
        Map<String, Object> parametrosRecuperado = Whitebox.getInternalState(page, "configuracoes");

        Assert.assertEquals(parametrosTeste.size(), parametrosRecuperado.size());
        for (String chave : parametrosTeste.keySet()) {
            Assert.assertEquals(parametrosTeste.get(chave), parametrosRecuperado.get(chave));
        }
    }

    @Test
    public void testErroAoCarregarVisualizadorPanelMensagemNaoNula() {
        String mensagemErro = "Mensagem de erro1!\nMensagem de erro 2!";
        testErroAoCarregarVisualizadorPanel(mensagemErro);
    }

    @Test
    public void testErroAoCarregarVisualizadorPanelMensagemNula() {
        String mensagemErro = null;
        testErroAoCarregarVisualizadorPanel(mensagemErro);
    }

    private void testErroAoCarregarVisualizadorPanel(String mensagemErro) {
        definirParametrosRequestParaBinario();

        visualizacaoDocumentoService.ajustarEValidarAbertura(EasyMock.<Binario> anyObject());

        EasyMock.expect(visualizadorConfig.getInformacoesParaVisualizacao(EasyMock.<Binario> anyObject(), EasyMock.<String> anyObject())).andThrow(
                new NegocioException(mensagemErro));

        InformacoesGroupDocs infoBasicas = new InformacoesGroupDocs();
        EasyMock.expect(visualizadorConfig.getInformacoesBasicas()).andReturn(infoBasicas);
        visualizadorConfig.configurar(EasyMock.<Binario>anyObject());

        PowerMock.replayAll();
        iniciarPagina();
        tester.assertRenderedPage(getClassePaginaTestada());
        PowerMock.verifyAll();

        Assert.assertNotNull(infoBasicas.getMensagemErro());
        String mensagemEsperada = "'" + (mensagemErro == null ? "null" : mensagemErro.replaceAll("\n", "<br>")) + "'";
        Assert.assertEquals(mensagemEsperada, infoBasicas.getMensagemErro());
        Assert.assertEquals(AnnotationHandler.ANONYMOUS_USERNAME, infoBasicas.getUserName());
    }

}
