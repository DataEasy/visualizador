package br.com.dataeasy.visualizador.config;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.dataeasy.visualizador.negocio.excecoes.VisualizadorNegocioException;
import br.com.dataeasy.visualizador.negocio.mensagens.MensagemValidacao;
import br.com.dataeasy.visualizador.negocio.mensagens.Messages;
import br.com.dataeasy.visualizador.util.ReflexaoUtil;

/**
 * <b>Description:</b> Abstrai configurações do {@link ApplicationConfig} para disponibilizá-las no Javascript como um conjunto de configurações a
 * serem passadas para o plugin GroupDocsAnnotation do jQuery. Estender o <code>ApplicationConfig</code> não é uma opção, pois ocorre problema ao
 * transformá-lo em JSON.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 25/08/2015
 */
// Esta classe representa as entradas de um longo arquivo properties. Devido a isso, a classe irá apresentar indicadores do PMD para 'classe com
// muitos campos' e 'classe com muitos métodos e campos públicos'.
@SuppressWarnings({ "PMD.TooManyFields", "PMD.ExcessivePublicCount", "serial" })
public class InformacoesGroupDocs implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(InformacoesGroupDocs.class);
    private String              fileId;
    private String              userName;
    private String              userId;
    private String              mensagemErro;
    private Map<String, String> localizedStrings;
    private String              thumbsImageBase64Encoded;
    private Integer             width;
    private Integer             height;
    private String              docViewerId;
    private Integer             quality;
    private Boolean             enableRightClickMenu;
    private Boolean             showHeader;
    private Boolean             showZoom;
    private Boolean             showPaging;
    private Boolean             showPrInteger;
    private Boolean             showFileExplorer;
    private Boolean             showThumbnails;
    private Boolean             showToolbar;
    private Boolean             openThumbnails;
    private Boolean             zoomToFitWidth;
    private Boolean             zoomToFitHeight;
    private Integer             initialZoom;
    private Integer             preloadPagesCount;
    private Boolean             enableSidePanel;
    private Boolean             scrollOnFocus;
    private String              strikeOutColor;
    private String              highlightColor;
    private String              underlineColor;
    private String              textFieldBackgroundColor;
    private Integer             enabledTools;
    private Integer             connectorPosition;
    private Boolean             saveReplyOnFocusLoss;
    private Boolean             clickableAnnotations;
    private Boolean             disconnectUncommented;
    private Boolean             enableStandardErrorHandling;
    private Integer             strikeoutMode;
    private Boolean             undoEnabled;
    private Boolean             anyToolSelection;
    private Boolean             tabNavigationEnabled;
    private Integer             minimumImageWidth;
    private String              sidebarContainerSelector;
    private Boolean             usePageNumberInUrlHash;
    private Boolean             textSelectionSynchronousCalculation;
    private Boolean             variableHeightPageSupport;
    private Boolean             useJavaScriptDocumentDescription;
    private Boolean             rightPanelEnabled;
    private Boolean             createMarkup;
    private Boolean             usePdf;
    private String              mode;
    private String              selectionContainerSelector;
    private String              graphicsContainerSelector;

    public InformacoesGroupDocs() {
        super();
    }

    @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
    public InformacoesGroupDocs(ApplicationConfig applicationConfig) {
        super();
        copiarConfiguracoes(applicationConfig);
    }

    /**
     * Copia configurações do ApplicationConfig para esta instância.
     *
     * @param applicationConfig o ApplicationConfig
     */
    protected void copiarConfiguracoes(ApplicationConfig applicationConfig) {
        try {
            BeanUtilsBean.getInstance().copyProperties(this, applicationConfig);
            ReflexaoUtil.copiarValoresBooleanos(this, applicationConfig);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new VisualizadorNegocioException(Messages.get(MensagemValidacao.ERRO_VISUALIZADOR_PROBLEMA_RECUPERANDO_CONFIGS), e);
        }
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public Map<String, String> getLocalizedStrings() {
        return localizedStrings;
    }

    public void setLocalizedStrings(Map<String, String> localizedStrings) {
        this.localizedStrings = localizedStrings;
    }

    public String getThumbsImageBase64Encoded() {
        return thumbsImageBase64Encoded;
    }

    public void setThumbsImageBase64Encoded(String thumbsImageBase64Encoded) {
        this.thumbsImageBase64Encoded = thumbsImageBase64Encoded;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getDocViewerId() {
        return docViewerId;
    }

    public void setDocViewerId(String docViewerId) {
        this.docViewerId = docViewerId;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Boolean isEnableRightClickMenu() {
        return enableRightClickMenu;
    }

    public void setEnableRightClickMenu(Boolean enableRightClickMenu) {
        this.enableRightClickMenu = enableRightClickMenu;
    }

    public Boolean isShowHeader() {
        return showHeader;
    }

    public void setShowHeader(Boolean showHeader) {
        this.showHeader = showHeader;
    }

    public Boolean isShowZoom() {
        return showZoom;
    }

    public void setShowZoom(Boolean showZoom) {
        this.showZoom = showZoom;
    }

    public Boolean isShowPaging() {
        return showPaging;
    }

    public void setShowPaging(Boolean showPaging) {
        this.showPaging = showPaging;
    }

    public Boolean isShowPrInteger() {
        return showPrInteger;
    }

    public void setShowPrInteger(Boolean showPrInteger) {
        this.showPrInteger = showPrInteger;
    }

    public Boolean isShowFileExplorer() {
        return showFileExplorer;
    }

    public void setShowFileExplorer(Boolean showFileExplorer) {
        this.showFileExplorer = showFileExplorer;
    }

    public Boolean isShowThumbnails() {
        return showThumbnails;
    }

    public void setShowThumbnails(Boolean showThumbnails) {
        this.showThumbnails = showThumbnails;
    }

    public Boolean isShowToolbar() {
        return showToolbar;
    }

    public void setShowToolbar(Boolean showToolbar) {
        this.showToolbar = showToolbar;
    }

    public Boolean isOpenThumbnails() {
        return openThumbnails;
    }

    public void setOpenThumbnails(Boolean openThumbnails) {
        this.openThumbnails = openThumbnails;
    }

    public Boolean isZoomToFitWidth() {
        return zoomToFitWidth;
    }

    public void setZoomToFitWidth(Boolean zoomToFitWidth) {
        this.zoomToFitWidth = zoomToFitWidth;
    }

    public Boolean isZoomToFitHeight() {
        return zoomToFitHeight;
    }

    public void setZoomToFitHeight(Boolean zoomToFitHeight) {
        this.zoomToFitHeight = zoomToFitHeight;
    }

    public Integer getInitialZoom() {
        return initialZoom;
    }

    public void setInitialZoom(Integer initialZoom) {
        this.initialZoom = initialZoom;
    }

    public Integer getPreloadPagesCount() {
        return preloadPagesCount;
    }

    public void setPreloadPagesCount(Integer preloadPagesCount) {
        this.preloadPagesCount = preloadPagesCount;
    }

    public Boolean isEnableSidePanel() {
        return enableSidePanel;
    }

    public void setEnableSidePanel(Boolean enableSidePanel) {
        this.enableSidePanel = enableSidePanel;
    }

    public Boolean isScrollOnFocus() {
        return scrollOnFocus;
    }

    public void setScrollOnFocus(Boolean scrollOnFocus) {
        this.scrollOnFocus = scrollOnFocus;
    }

    public String getStrikeOutColor() {
        return strikeOutColor;
    }

    public void setStrikeOutColor(String strikeOutColor) {
        this.strikeOutColor = strikeOutColor;
    }

    public String getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(String highlightColor) {
        this.highlightColor = highlightColor;
    }

    public String getUnderlineColor() {
        return underlineColor;
    }

    public void setUnderlineColor(String underlineColor) {
        this.underlineColor = underlineColor;
    }

    public String getTextFieldBackgroundColor() {
        return textFieldBackgroundColor;
    }

    public void setTextFieldBackgroundColor(String textFieldBackgroundColor) {
        this.textFieldBackgroundColor = textFieldBackgroundColor;
    }

    public Integer getEnabledTools() {
        return enabledTools;
    }

    public void setEnabledTools(Integer enabledTools) {
        this.enabledTools = enabledTools;
    }

    public Integer getConnectorPosition() {
        return connectorPosition;
    }

    public void setConnectorPosition(Integer connectorPosition) {
        this.connectorPosition = connectorPosition;
    }

    public Boolean isSaveReplyOnFocusLoss() {
        return saveReplyOnFocusLoss;
    }

    public void setSaveReplyOnFocusLoss(Boolean saveReplyOnFocusLoss) {
        this.saveReplyOnFocusLoss = saveReplyOnFocusLoss;
    }

    public Boolean isClickableAnnotations() {
        return clickableAnnotations;
    }

    public void setClickableAnnotations(Boolean clickableAnnotations) {
        this.clickableAnnotations = clickableAnnotations;
    }

    public Boolean isDisconnectUncommented() {
        return disconnectUncommented;
    }

    public void setDisconnectUncommented(Boolean disconnectUncommented) {
        this.disconnectUncommented = disconnectUncommented;
    }

    public Boolean isEnableStandardErrorHandling() {
        return enableStandardErrorHandling;
    }

    public void setEnableStandardErrorHandling(Boolean enableStandardErrorHandling) {
        this.enableStandardErrorHandling = enableStandardErrorHandling;
    }

    public Integer getStrikeoutMode() {
        return strikeoutMode;
    }

    public void setStrikeoutMode(Integer strikeoutMode) {
        this.strikeoutMode = strikeoutMode;
    }

    public Boolean isUndoEnabled() {
        return undoEnabled;
    }

    public void setUndoEnabled(Boolean undoEnabled) {
        this.undoEnabled = undoEnabled;
    }

    public Boolean isAnyToolSelection() {
        return anyToolSelection;
    }

    public void setAnyToolSelection(Boolean anyToolSelection) {
        this.anyToolSelection = anyToolSelection;
    }

    public Boolean isTabNavigationEnabled() {
        return tabNavigationEnabled;
    }

    public void setTabNavigationEnabled(Boolean tabNavigationEnabled) {
        this.tabNavigationEnabled = tabNavigationEnabled;
    }

    public Integer getMinimumImageWidth() {
        return minimumImageWidth;
    }

    public void setMinimumImageWidth(Integer minimumImageWidth) {
        this.minimumImageWidth = minimumImageWidth;
    }

    public String getSidebarContainerSelector() {
        return sidebarContainerSelector;
    }

    public void setSidebarContainerSelector(String sidebarContainerSelector) {
        this.sidebarContainerSelector = sidebarContainerSelector;
    }

    public Boolean isUsePageNumberInUrlHash() {
        return usePageNumberInUrlHash;
    }

    public void setUsePageNumberInUrlHash(Boolean usePageNumberInUrlHash) {
        this.usePageNumberInUrlHash = usePageNumberInUrlHash;
    }

    public Boolean isTextSelectionSynchronousCalculation() {
        return textSelectionSynchronousCalculation;
    }

    public void setTextSelectionSynchronousCalculation(Boolean textSelectionSynchronousCalculation) {
        this.textSelectionSynchronousCalculation = textSelectionSynchronousCalculation;
    }

    public Boolean isVariableHeightPageSupport() {
        return variableHeightPageSupport;
    }

    public void setVariableHeightPageSupport(Boolean variableHeightPageSupport) {
        this.variableHeightPageSupport = variableHeightPageSupport;
    }

    public Boolean isUseJavaScriptDocumentDescription() {
        return useJavaScriptDocumentDescription;
    }

    public void setUseJavaScriptDocumentDescription(Boolean useJavaScriptDocumentDescription) {
        this.useJavaScriptDocumentDescription = useJavaScriptDocumentDescription;
    }

    public Boolean isRightPanelEnabled() {
        return rightPanelEnabled;
    }

    public void setRightPanelEnabled(Boolean rightPanelEnabled) {
        this.rightPanelEnabled = rightPanelEnabled;
    }

    public Boolean isCreateMarkup() {
        return createMarkup;
    }

    public void setCreateMarkup(Boolean createMarkup) {
        this.createMarkup = createMarkup;
    }

    public Boolean isUsePdf() {
        return usePdf;
    }

    public void setUsePdf(Boolean usePdf) {
        this.usePdf = usePdf;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSelectionContainerSelector() {
        return selectionContainerSelector;
    }

    public void setSelectionContainerSelector(String selectionContainerSelector) {
        this.selectionContainerSelector = selectionContainerSelector;
    }

    public String getGraphicsContainerSelector() {
        return graphicsContainerSelector;
    }

    public void setGraphicsContainerSelector(String graphicsContainerSelector) {
        this.graphicsContainerSelector = graphicsContainerSelector;
    }

    public void aplicarConfiguracoes(Map<String, Object> configuracoes) {
        configuracoes.forEach((nome, valor) -> {
            try {
                Class<?> tipo = ReflexaoUtil.getTipoDeCampo(InformacoesGroupDocs.this, nome);
                Object valorConvertido = new ConvertUtilsBean().convert(valor, tipo);
                ReflexaoUtil.setValorDeCampo(InformacoesGroupDocs.this, nome, valorConvertido);
            } catch (Exception e) {
                // ignorado
                LOG.warn("Problema definindo valor de \"" + nome + "\" em " + getClass().getSimpleName() + ": " + e.getMessage());
            }
        });
    }
}
