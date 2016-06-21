package br.com.dataeasy.visualizador.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.dataeasy.visualizador.util.Constantes;

/**
 * <b>Description:</b>Teste de integração da ApplicationConfig utilizando Spring para verificação da definição dos valores do
 * {@link ApplicationConfig} a partir do <code>group-docs.properties</code>.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 16/07/2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/springAppContext-visualizador-teste.xml" })
public class ApplicationConfigIntegracaoTest {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Before
    public void setUp() {
        //
    }

    /**
     * Faz o teste de injeção de valores do group-docs.properties para o <code>ApplicationConfig</code> com a utilização do Spring via
     * springAppContext-visualizador-teste.xml e br.com.dataeasy.visualizador.spring.context.SpringAppContext.
     */
    @Test
    public void testaDefinicaoDeValoresDoProperties() {
        Assert.assertEquals("/opt/config/GroupDocs.Annotation.for.Java.lic", applicationConfig.getLicensePath());
        Assert.assertEquals("/opt/aplicacoes/visualizador", applicationConfig.getApplicationPath());
        Assert.assertEquals("nenhum", applicationConfig.getDefaultFileName());
        Assert.assertEquals("null", applicationConfig.getLocalization());
        Assert.assertEquals(true, applicationConfig.isDisableAtmosphere());
        Assert.assertEquals(false, applicationConfig.isCaseSensitive());
        Assert.assertEquals(true, applicationConfig.isSearchForSeparateWords());
        Assert.assertEquals("a", applicationConfig.getEncoding());
        Assert.assertEquals("", applicationConfig.getFontsPath());
        Assert.assertEquals("/upload/", applicationConfig.getUploadPath());
        Assert.assertEquals("default", applicationConfig.getStorageType());
        Assert.assertEquals("few", applicationConfig.getStoreLogic());
        Assert.assertNull(applicationConfig.getStoragePath());

        applicationConfig.setStoragePath("null");
        Assert.assertNull(applicationConfig.getStoragePath());

        Assert.assertEquals("127.0.0.1", applicationConfig.getDbServer());
        Assert.assertEquals((Integer) 3306, applicationConfig.getDbPort());
        Assert.assertEquals("AnnotationDB", applicationConfig.getDbName());
        Assert.assertEquals("root", applicationConfig.getDbUsername());
        Assert.assertEquals("", applicationConfig.getDbPassword());
        Assert.assertEquals(true, applicationConfig.isUseCache());
        Assert.assertEquals(true, applicationConfig.isUseBrowserCache());
        Assert.assertEquals((Long) 200L, applicationConfig.getMaxCacheSize());
        Assert.assertEquals((Integer) 0, applicationConfig.getExpirationDate());
        Assert.assertEquals("null", applicationConfig.getLocalesPath());
        Assert.assertEquals("GroupDocsEncrypt", applicationConfig.getEncryptionKey());
        Assert.assertEquals(false, applicationConfig.isUseAuthorization());
        Assert.assertEquals((Integer) 100, applicationConfig.getQuality());
        Assert.assertEquals((Integer) 0, applicationConfig.getWidth());
        Assert.assertEquals((Integer) 0, applicationConfig.getHeight());
        Assert.assertEquals((Integer) 100, applicationConfig.getInitialZoom());
        Assert.assertEquals(true, applicationConfig.isZoomToFitWidth());
        Assert.assertEquals(true, applicationConfig.isZoomToFitHeight());
        Assert.assertEquals(true, applicationConfig.isShowHeader());
        Assert.assertEquals(false, applicationConfig.isShowPrint());
        Assert.assertEquals(true, applicationConfig.isShowZoom());
        Assert.assertEquals(true, applicationConfig.isShowPaging());
        Assert.assertEquals(true, applicationConfig.isShowThumbnails());
        Assert.assertEquals(true, applicationConfig.isOpenThumbnails());
        Assert.assertEquals(false, applicationConfig.isPrintWithWatermark());
        Assert.assertEquals("", applicationConfig.getJqueryFileDownloadCookieName());
        Assert.assertEquals((Integer) 0, applicationConfig.getWatermarkFontSize());
        Assert.assertEquals("", applicationConfig.getWatermarkPosition());
        Assert.assertEquals("", applicationConfig.getWatermarkText());
        Assert.assertEquals((Integer) 5, applicationConfig.getPreloadPagesCount());
        Assert.assertEquals(false, applicationConfig.isUseEmScaling());
        Assert.assertEquals("", applicationConfig.getFileDisplayName());
        Assert.assertEquals(false, applicationConfig.isConvertWordDocumentsCompletely());
        Assert.assertEquals(false, applicationConfig.isIgnoreDocumentAbsence());
        Assert.assertEquals(true, applicationConfig.isPreloadPagesOnBrowserSide());
        Assert.assertEquals(false, applicationConfig.isSupportPageRotation());
        Assert.assertEquals(false, applicationConfig.isEnableRightClickMenu());
        Assert.assertEquals(false, applicationConfig.isShowFileExplorer());
        Assert.assertEquals(false, applicationConfig.isShowToolbar());
        Assert.assertEquals(false, applicationConfig.isEnableSidePanel());
        Assert.assertEquals(true, applicationConfig.isScrollOnFocus());
        Assert.assertEquals("#00000c", applicationConfig.getStrikeOutColor());
        Assert.assertEquals("#000017", applicationConfig.getHighlightColor());
        Assert.assertEquals("#FF0000", applicationConfig.getUnderlineColor());
        Assert.assertEquals("#990000", applicationConfig.getTextFieldBackgroundColor());
        Assert.assertEquals(false, applicationConfig.isTabNavigationEnabled());
        Assert.assertEquals((Integer) 150, applicationConfig.getMinimumImageWidth());
        Assert.assertEquals((Integer) 1, applicationConfig.getAreaToolOptionsPenWidth());
        Assert.assertEquals("#FF0000", applicationConfig.getAreaToolOptionsPenColor());
        Assert.assertEquals((Integer) 0, applicationConfig.getAreaToolOptionsPenDashStyle());
        Assert.assertEquals("#00FF00", applicationConfig.getAreaToolOptionsBrushColor());
        Assert.assertEquals((Integer) 1, applicationConfig.getPolylineToolOptionsPenWidth());
        Assert.assertEquals("#FF0000", applicationConfig.getPolylineToolOptionsPenColor());
        Assert.assertEquals((Integer) 0, applicationConfig.getPolylineToolOptionsPenDashStyle());
        Assert.assertEquals("#00FF00", applicationConfig.getPolylineToolOptionsBrushColor());
        Assert.assertEquals((Integer) 1, applicationConfig.getArrowToolOptionsPenWidth());
        Assert.assertEquals("#FF0000", applicationConfig.getArrowToolOptionsPenColor());
        Assert.assertEquals((Integer) 0, applicationConfig.getArrowToolOptionsPenDashStyle());
        Assert.assertEquals("#00FF00", applicationConfig.getArrowToolOptionsBrushColor());
        Assert.assertEquals("#0000FF", applicationConfig.getDistanceToolOptionsPenColor());
        Assert.assertEquals("#777777", applicationConfig.getPenColor());
        Assert.assertEquals((Integer) 3, applicationConfig.getPenWidth());
        Assert.assertEquals((Integer) 0, applicationConfig.getPenStyle());
        Assert.assertEquals((Integer) 8191, applicationConfig.getEnabledTools());
        Assert.assertEquals((Integer) 0, applicationConfig.getConnectorPosition());
        Assert.assertEquals(false, applicationConfig.isSaveReplyOnFocusLoss());
        Assert.assertEquals(true, applicationConfig.isClickableAnnotations());
        Assert.assertEquals(false, applicationConfig.isDisconnectUncommented());
        Assert.assertEquals((Integer) 1, applicationConfig.getStrikeoutMode());
        Assert.assertEquals(true, applicationConfig.isAnyToolSelection());
        Assert.assertEquals("div.comments_sidebar_wrapper", applicationConfig.getSidebarContainerSelector());
        Assert.assertEquals(false, applicationConfig.isUsePageNumberInUrlHash());
        Assert.assertEquals(true, applicationConfig.isTextSelectionSynchronousCalculation());
        Assert.assertEquals(true, applicationConfig.isVariableHeightPageSupport());
        Assert.assertEquals(false, applicationConfig.isRightPanelEnabled());
        Assert.assertEquals(true, applicationConfig.isCreateMarkup());
        Assert.assertEquals(true, applicationConfig.isUse_pdf());
        Assert.assertEquals("annotatedDocument", applicationConfig.getMode());
        Assert.assertEquals("[name='selection-content']", applicationConfig.getSelectionContainerSelector());
        Assert.assertEquals(".annotationsContainer", applicationConfig.getGraphicsContainerSelector());
        Assert.assertEquals(Constantes.DIV_VISUALIZADOR, applicationConfig.getWidgetId());
        Assert.assertEquals(true, applicationConfig.isUndoEnabled());
        Assert.assertEquals(true, applicationConfig.isPrintAnnotations());
        Assert.assertEquals(false, applicationConfig.isPdfPrintMarginsEnabled());
    }
}
