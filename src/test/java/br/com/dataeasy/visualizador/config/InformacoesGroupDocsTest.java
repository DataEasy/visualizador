package br.com.dataeasy.visualizador.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

/**
 * <b>Description:</b>Teste para InformacoesGroupDocs.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 26/08/2015
 */
@SuppressWarnings("PMD.MagicNumbers")
public class InformacoesGroupDocsTest {

    private ApplicationConfig criarApplicationConfigPopulado() {
        ApplicationConfig appConfig = new ApplicationConfig();
        appConfig.setWidth(50);
        appConfig.setHeight(10);
        appConfig.setQuality(0);
        appConfig.setEnableRightClickMenu(true);
        appConfig.setShowHeader(true);
        appConfig.setShowZoom(true);
        appConfig.setShowPaging(true);
        appConfig.setShowPrint(true);
        appConfig.setShowFileExplorer(true);
        appConfig.setShowThumbnails(true);
        appConfig.setShowToolbar(true);
        appConfig.setOpenThumbnails(true);
        appConfig.setZoomToFitWidth(true);
        appConfig.setZoomToFitHeight(true);
        appConfig.setInitialZoom(332);
        appConfig.setPreloadPagesCount(5);
        appConfig.setEnableSidePanel(true);
        appConfig.setScrollOnFocus(true);
        appConfig.setStrikeOutColor("adsf");
        appConfig.setHighlightColor("asdf");
        appConfig.setUnderlineColor("adsfsd");
        appConfig.setTextFieldBackgroundColor("dsfsda");
        appConfig.setEnabledTools(23);
        appConfig.setConnectorPosition(34);
        appConfig.setSaveReplyOnFocusLoss(true);
        appConfig.setClickableAnnotations(true);
        appConfig.setDisconnectUncommented(true);
        appConfig.setStrikeoutMode(234);
        appConfig.setUndoEnabled(true);
        appConfig.setAnyToolSelection(true);
        appConfig.setTabNavigationEnabled(true);
        appConfig.setMinimumImageWidth(234);
        appConfig.setSidebarContainerSelector("adsf");
        appConfig.setUsePageNumberInUrlHash(true);
        appConfig.setTextSelectionSynchronousCalculation(true);
        appConfig.setVariableHeightPageSupport(true);
        appConfig.setRightPanelEnabled(true);
        appConfig.setCreateMarkup(true);
        appConfig.setUsePdf(true);
        appConfig.setMode("aaa");
        appConfig.setSelectionContainerSelector("aaa");
        appConfig.setGraphicsContainerSelector("adsf");
        return appConfig;
    }

    private Object getValorPropriedade(Object info, String nomePropriedade) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        String sufixo = nomePropriedade.substring(0, 1).toUpperCase() + nomePropriedade.substring(1);
        Method metodo;

        try {
            metodo = info.getClass().getMethod("get" + sufixo);
        } catch (NoSuchMethodException e) {
            metodo = info.getClass().getMethod("is" + sufixo);
        }

        return metodo.invoke(info);
    }

    @Test
    public void testConstrutorComCopiaDeInformacoes() {
        ApplicationConfig appConfig = criarApplicationConfigPopulado();
        InformacoesGroupDocs info = new InformacoesGroupDocs(appConfig);

        for (Method metodo : InformacoesGroupDocs.class.getMethods()) {
            String name = metodo.getName();
            if (!name.startsWith("get") && !name.startsWith("is") || "getClass".equals(name)) {
                continue;
            }

            String nomePropMaiusculo = name.startsWith("is") ? name.substring(2) : name.substring(3);
            String nomePropriedade = nomePropMaiusculo.substring(0, 1).toLowerCase() + nomePropMaiusculo.substring(1);
            try {
                Object valorInfoGroupDocs = getValorPropriedade(info, nomePropriedade);
                Object valorAppConfig = getValorPropriedade(appConfig, nomePropriedade);

//                LOG.info(nomePropriedade + ": " + valorInfoGroupDocs + " X " + valorAppConfig);
                Assert.assertEquals("Valor de propriedade '" + nomePropriedade + "' diferente.", valorAppConfig, valorInfoGroupDocs);
            } catch (Exception e) {
                continue;
            }
        }
    }
}
