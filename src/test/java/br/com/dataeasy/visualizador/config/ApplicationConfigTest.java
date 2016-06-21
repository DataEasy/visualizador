package br.com.dataeasy.visualizador.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.easymock.EasyMock;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.dataeasy.visualizador.VisualizadorInfraException;

/**
 * <b>Description:</b>Teste da ApplicationConfig.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 16/07/2015
 */
@RunWith(PowerMockRunner.class)
public class ApplicationConfigTest {

    @Rule
    public ExpectedException expected = ExpectedException .none();

    private ApplicationConfig applicationConfig;

    @Before
    public void setUp() throws IOException {
        // limpando diretório padrão do visualizador para testes a cada execução de método testado
        limparDiretorioBasePadrao();
        applicationConfig = new ApplicationConfig();
    }

    private void limparDiretorioBasePadrao() throws IOException {
        Path caminho = Paths.get(System.getProperty("java.io.tmpdir"), "visualizador-base");
        if (Files.exists(caminho)) {
            FileUtils.deleteDirectory(caminho.toFile());
        }
    }

    @Test
    public void testRecuperarBasePathNuloComVisualizadorBaseInexistente() throws IOException {
        testRecuperarBasePathNuloComVisualizadorBaseInexistenteOuNulo(null);
    }

    @Test
    public void testRecuperarBasePathNuloComVisualizadorBaseVazio() throws IOException {
        testRecuperarBasePathNuloComVisualizadorBaseInexistenteOuNulo("");
    }

    private void testRecuperarBasePathNuloComVisualizadorBaseInexistenteOuNulo(String valorASetar) throws IOException {
        applicationConfig.setBasePath(valorASetar);

        Path basePathGerado = Paths.get(System.getProperty("java.io.tmpdir"), "visualizador-base");
        String esperado = basePathGerado.toString() + File.separator;

        String basePath = applicationConfig.getBasePath();
        Assert.assertEquals(esperado, basePath);
        Assert.assertTrue(Files.exists(basePathGerado));
        Assert.assertTrue(Files.isDirectory(basePathGerado));
    }

    @Test
    public void testRecuperarBasePathTerminandoComFileSeparator() {
        String basePathDefinido = Paths.get(System.getProperty("java.io.tmpdir")).toString() + File.separator;
        applicationConfig.setBasePath(basePathDefinido);
        Assert.assertEquals(basePathDefinido, applicationConfig.getBasePath());
    }

    @Test
    public void testRecuperarCaminhoBaseComCaminhoExistente() throws IOException {
        Path caminho = Paths.get(System.getProperty("java.io.tmpdir"), "visualizador-base");
        if (Files.notExists(caminho)) {
            Files.createDirectory(caminho);
        }

        applicationConfig.setBasePath(null);
        Assert.assertEquals(caminho + File.separator, applicationConfig.getBasePath());
    }

    @Test
    @PrepareForTest({ApplicationConfig.class, Files.class})
    public void testCriarCaminhoBaseComIOException() throws IOException {
        expected.expect(VisualizadorInfraException.class);
        expected.expectCause(Is.isA(IOException.class));

        PowerMock.mockStatic(Files.class);
        EasyMock.expect(Files.exists(EasyMock.<Path>anyObject())).andReturn(false);
        EasyMock.expect(Files.createDirectory(EasyMock.<Path>anyObject())).andThrow(new IOException("blah"));
        PowerMock.replayAll(Files.class);

        applicationConfig.getBasePath();
        PowerMock.verifyAll();
    }

    @Test
    public void testRecuperarCaminhoCachePadraoNaoInicializado() throws IOException {
        Path basePath = Paths.get(System.getProperty("java.io.tmpdir"), "visualizador-base");
        Path cachePath = basePath.resolve("cache");

        Assert.assertEquals(cachePath.toString(), applicationConfig.getCachePath());
    }

    @Test
    public void testRecuperarCaminhoCachePadraoInicializado() throws IOException {
        Path basePath = Paths.get(System.getProperty("java.io.tmpdir"), "blah");
        Path cachePath = basePath.resolve("cache");
        if (Files.notExists(cachePath)) {
            Files.createDirectories(cachePath);
        }

        applicationConfig.setBasePath(basePath.toString());
        Assert.assertEquals(cachePath.toString(), applicationConfig.getCachePath());
    }

    @Test
    @PrepareForTest({ApplicationConfig.class, Files.class})
    public void testRecuperarCaminhoCacheComIOException() throws IOException {
        expected.expect(VisualizadorInfraException.class);
        expected.expectCause(Is.isA(IOException.class));

        PowerMock.mockStatic(Files.class);
        EasyMock.expect(Files.exists(EasyMock.<Path>anyObject())).andReturn(true);
        EasyMock.expect(Files.exists(EasyMock.<Path>anyObject())).andReturn(false);
        EasyMock.expect(Files.createDirectory(EasyMock.<Path>anyObject())).andThrow(new IOException("blah"));
        PowerMock.replayAll(Files.class);

        applicationConfig.getCachePath();
        PowerMock.verifyAll();
    }

}
