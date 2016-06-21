package br.com.dataeasy.visualizador.config;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.TimeZone;

import org.easymock.EasyMock;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.dataeasy.visualizador.VisualizadorInfraException;
import br.com.dataeasy.visualizador.negocio.excecoes.VisualizadorNegocioException;
import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.util.Constantes;

import com.groupdocs.annotation.exception.AnnotationException;
import com.groupdocs.annotation.handler.AnnotationHandler;
import com.groupdocs.annotation.localization.ILocalization;
import com.groupdocs.viewer.config.ServiceConfiguration;
import com.groupdocs.viewer.domain.path.EncodedPath;

/**
 * <b>Description:</b>Testes unitários do VisualizadorConfig.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 24/08/2015
 */
@RunWith(PowerMockRunner.class)
public class VisualizadorConfigTest {

    @Rule
    public ExpectedException   expected = ExpectedException.none();
    private VisualizadorConfig visualizadorConfig;
    private AnnotationHandler  annotationHandler;
    private ApplicationConfig  applicationConfig;

    @Before
    public void setUp() {
        PowerMock.resetAll();

        visualizadorConfig = new VisualizadorConfig();
        applicationConfig = new ApplicationConfig();

        Whitebox.setInternalState(visualizadorConfig, ApplicationConfig.class, applicationConfig);
    }

    @After
    public void tearDown() {
        PowerMock.resetAll();
    }

    private void definirAnnotationHandler() {
        annotationHandler = EasyMock.createNiceMock(AnnotationHandler.class);
        Whitebox.setInternalState(visualizadorConfig, AnnotationHandler.class, annotationHandler);
    }

    @Test
    public void testGetInformacoesBasicas() {
        Boolean showThumbnails = true;
        applicationConfig.setShowThumbnails(showThumbnails);
        PowerMock.replayAll();

        InformacoesGroupDocs informacoesBasicas = visualizadorConfig.getInformacoesBasicas();
        Assert.assertEquals(showThumbnails, informacoesBasicas.isShowThumbnails());
        PowerMock.verifyAll();
    }

    @Test
    @PrepareForTest(VisualizadorConfig.class)
    public void testGetInformacoesParaVisualizacao() throws Exception {
        String fileId = "path_blah_blah";
        String userName = "user.name";
        String userId = "56115.44";
        EncodedPath encodedPath = EasyMock.createNiceMock(EncodedPath.class);

        Binario binario = PowerMock.createPartialMock(Binario.class, "getCaminhoCompleto");
        long idBinario = 156156L;

        String caminhoArmazem = "c:/teste";
        binario.setCaminhoCompleto("");
        binario.setMimeType("application/pdf");

        EasyMock.expect(binario.getCaminhoCompleto()).andReturn(caminhoArmazem + idBinario);

        definirAnnotationHandler();

        PowerMock.expectNew(EncodedPath.class, EasyMock.<String> anyObject(), EasyMock.<ServiceConfiguration> anyObject()).andReturn(encodedPath);
        EasyMock.expect(encodedPath.getPath()).andReturn(fileId);
        EasyMock.expect(annotationHandler.getUserGuid(userName)).andReturn(userId);

        PowerMock.replayAll(EncodedPath.class, annotationHandler, encodedPath, binario);

        InformacoesGroupDocs informacoesBasicas = visualizadorConfig.getInformacoesParaVisualizacao(binario, userName);
        Assert.assertEquals(informacoesBasicas.getFileId(), fileId);
        Assert.assertEquals(informacoesBasicas.getUserName(), userName);
        Assert.assertEquals(informacoesBasicas.getUserId(), userId);

        PowerMock.verifyAll();
    }

    @Test
    @PrepareForTest({ TimeZone.class, Locale.class, AnnotationHandler.class, ServiceConfiguration.class, VisualizadorConfig.class })
    public void testInit() throws Exception {
        PowerMock.mockStatic(TimeZone.class);
        PowerMock.mockStatic(Locale.class);

        TimeZone.setDefault(Constantes.TIMEZONE_PADRAO);
        PowerMock.expectLastCall();

        ServiceConfiguration serviceConfiguration = EasyMock.createNiceMock(ServiceConfiguration.class);
        PowerMock.expectNew(ServiceConfiguration.class, applicationConfig).andReturn(serviceConfiguration);

        Locale.setDefault(Constantes.LOCALE_PADRAO);
        PowerMock.expectLastCall();

        AnnotationHandler annotationHandler = EasyMock.createNiceMock(AnnotationHandler.class);
        PowerMock.expectNew(AnnotationHandler.class, serviceConfiguration, null).andReturn(annotationHandler);
        ILocalization iLocalization = EasyMock.createNiceMock(ILocalization.class);
        EasyMock.expect(annotationHandler.getLocalization()).andReturn(iLocalization);

        applicationConfig.setWidgetId(Constantes.DIV_VISUALIZADOR);
        PowerMock.replayAll(TimeZone.class, Locale.class, AnnotationHandler.class, serviceConfiguration, iLocalization);

        visualizadorConfig.init();

        PowerMock.verifyAll();
    }

    @Test
    public void testInitQuandoAnnotationHandlerJaExiste() {
        definirAnnotationHandler();
        PowerMock.replayAll();

        visualizadorConfig.init();
        PowerMock.verifyAll();
    }

    @Test
    @PrepareForTest({ TimeZone.class, VisualizadorConfig.class })
    public void testInitComQualquerExcecao() throws Exception {
        expected.expect(VisualizadorInfraException.class);
        expected.expectCause(Is.isA(NullPointerException.class));
        expected.expectMessage("Problema ao inicializar infra do Visualizador.");

        PowerMock.mockStatic(TimeZone.class);

        TimeZone.setDefault(Constantes.TIMEZONE_PADRAO);
        PowerMock.expectLastCall().andThrow(new NullPointerException("testando"));

        PowerMock.replayAll(TimeZone.class);

        visualizadorConfig.init();
        PowerMock.verifyAll();
    }

    @Test
    @PrepareForTest({ TimeZone.class, Locale.class, AnnotationHandler.class, ServiceConfiguration.class, VisualizadorConfig.class })
    public void testInitComExcecaoAoCriarAnnnotaitonHandler() throws Exception {
        expected.expect(VisualizadorInfraException.class);
        expected.expectCause(Is.isA(NullPointerException.class));
        expected.expectMessage("Problema ao criar AnnotationHandler. O ApplicationConfig do GroupDocs foi carregado corretamente?");

        PowerMock.mockStatic(TimeZone.class);
        PowerMock.mockStatic(Locale.class);

        TimeZone.setDefault(Constantes.TIMEZONE_PADRAO);
        PowerMock.expectLastCall();

        ServiceConfiguration serviceConfiguration = EasyMock.createNiceMock(ServiceConfiguration.class);
        PowerMock.expectNew(ServiceConfiguration.class, applicationConfig).andReturn(serviceConfiguration);

        Locale.setDefault(Constantes.LOCALE_PADRAO);
        PowerMock.expectLastCall();

        PowerMock.expectNew(AnnotationHandler.class, serviceConfiguration, null).andThrow(new NullPointerException("testando2"));
        PowerMock.replayAll(TimeZone.class, Locale.class, AnnotationHandler.class, serviceConfiguration);

        visualizadorConfig.init();

        PowerMock.verifyAll();
    }

    @Test
    public void testGetIdUsuario() throws Exception {
        final String USER_GUID = "userGuid";
        final String RETORNO = "12";

        definirAnnotationHandler();

        EasyMock.expect(annotationHandler.getUserGuid(USER_GUID)).andReturn(RETORNO);
        PowerMock.replayAll(annotationHandler);

        Assert.assertEquals(visualizadorConfig.getIdUsuario(USER_GUID), RETORNO);
        PowerMock.verifyAll();
    }

    @Test
    public void testGetIdUsuarioComVisualizadorInfraException() throws AnnotationException {
        expected.expect(VisualizadorInfraException.class);
        expected.expectCause(Is.isA(AnnotationException.class));
        expected.expectMessage("Problema ao requisitar ID de usuário no GroupDocs.");
        final String USER_GUID = "blah";

        definirAnnotationHandler();
        EasyMock.expect(annotationHandler.getUserGuid(USER_GUID)).andThrow(new AnnotationException("ane"));
        PowerMock.replayAll(annotationHandler);

        visualizadorConfig.getIdUsuario(USER_GUID);
        PowerMock.verifyAll();
    }

    @Test
    public void testGetIdArquivoComContentTypeNulo() throws URISyntaxException {
        expected.expect(VisualizadorNegocioException.class);
        expected.expectMessage("Tipo de arquivo (MIME Type) inválido: (nulo)");

        PowerMock.replayAll();

        String caminho = Paths.get(getClass().getResource("/exemplo.pdf").toURI()).toString();
        visualizadorConfig.getIdArquivo(new Binario(caminho, null));
        PowerMock.verifyAll();
    }

    @Test
    @PrepareForTest({ EncodedPath.class, VisualizadorConfig.class })
    public void testGetIdArquivoQuandoExisteLinkSimbolicoErrado() throws Exception {
        definirAnnotationHandler();

        ServiceConfiguration serviceConfiguration = EasyMock.createNiceMock(ServiceConfiguration.class);
        EasyMock.expect(annotationHandler.getConfiguration()).andReturn(serviceConfiguration);

        EncodedPath encodedPath = EasyMock.createNiceMock(EncodedPath.class);
        PowerMock.expectNew(EncodedPath.class, EasyMock.<String> anyObject(), EasyMock.<ServiceConfiguration> anyObject()).andReturn(encodedPath);
        String encodedPathString = "e";
        EasyMock.expect(encodedPath.getPath()).andReturn(encodedPathString);

        String pathDiretorioTemp = System.getProperty("java.io.tmpdir");
        Path linkSimbolico = Paths.get(pathDiretorioTemp, "exemplo.pdf");
        Files.deleteIfExists(linkSimbolico);
        Files.createSymbolicLink(linkSimbolico, Paths.get(getClass().getResource("/group-docs-teste.properties").toURI()));
        Path destino = Files.readSymbolicLink(linkSimbolico);
        Assert.assertNotEquals(Paths.get(getClass().getResource("/exemplo.pdf").toURI()), destino);
        PowerMock.replayAll(annotationHandler, encodedPath);

        Path target = Paths.get(getClass().getResource("/exemplo.pdf").toURI());
        Assert.assertEquals(encodedPathString, visualizadorConfig.getIdArquivo(new Binario(target.toString(), Constantes.MIME_TYPE_PDF)));
        PowerMock.verifyAll();

        destino = Files.readSymbolicLink(linkSimbolico);
        Assert.assertEquals(target, destino);
        Files.deleteIfExists(linkSimbolico);
    }

    @Ignore
    @Test
    public void testGetIdArquivoQuandoExisteLinkSimbolicoErradoComIOException() {
        // testa instanciação e execução do getIdArquivo() quando link simbólico para arquivo já existe, aponta para arquivo errado e ocorre
        // IOException na verificação deste link simbólico
    }

    @Ignore
    @Test
    public void testGetIdArquivoQuandoExisteLinkSimbolicoCorreto() {
        // testa instanciação e execução do getIdArquivo() quando link simbólico para arquivo já existe e aponta para arquivo correto
    }

    @Ignore
    @Test
    public void testGetIdArquivoComVisualizadorInfraException() {
        // testa instanciação e execução do getIdArquivo() quando ocorre IOException e é lançada VisualizadorInfraException
    }

    @Ignore
    @Test
    public void testGetIdArquivo() {
        // testa instanciação e execução do getIdArquivo() com sucesso
    }

    @Ignore
    @Test
    public void testGetIdArquivoComVisualizadorNegocioException() {
        // testa instanciação e execução do getIdArquivo() quando ocorre erro e é lançada VisualizadorNegocioException
    }
}
