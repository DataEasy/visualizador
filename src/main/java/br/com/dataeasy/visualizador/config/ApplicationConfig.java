package br.com.dataeasy.visualizador.config;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.dataeasy.visualizador.VisualizadorInfraException;

import com.groupdocs.annotation.config.ServiceConfiguration;

/**
 * <b>Description:</b> Cofigurações básicas do GroupDocs importadas do <code>group-docs.properties</code>. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 22/06/2015
 */
@Component
// Esta classe representa as entradas de um longo arquivo properties. Devido a isso, a classe irá apresentar indicadores do PMD para 'classe longa' e
// 'classe com muitos campos' e 'classe com muitos métodos e campos públicos'.
@SuppressWarnings({ "PMD.ExcessiveClassLength", "PMD.TooManyFields", "PMD.ExcessivePublicCount", "serial" })
public class ApplicationConfig extends ServiceConfiguration implements Serializable {

    private static final String DIRETORIO_BASE     = "visualizador-base";
    private static final String SUBDIRETORIO_CACHE = "cache";
    /**
     * The License path.
     */
    @Value("${groupdocs.annotation.licensePath}")
    private String              licensePath;
    /**
     * The Application path.
     */
    @Value("${groupdocs.annotation.applicationPath}")
    private String              applicationPath;
    /**
     * The Base path.
     */
    @Value("${groupdocs.annotation.basePath}")
    private String              basePath;

    /**
     * The Storage type.
     */
    @Value("${groupdocs.annotation.storageType}")
    private String              storageType;
    /**
     * The Db server.
     */
    @Value("${groupdocs.annotation.dbServer}")
    private String              dbServer;
    /**
     * The Db port.
     */
    @Value("${groupdocs.annotation.dbPort}")
    private Integer             dbPort;
    /**
     * The Db name.
     */
    @Value("${groupdocs.annotation.dbName}")
    private String              dbName;
    /**
     * The Db username.
     */
    @Value("${groupdocs.annotation.dbUsername}")
    private String              dbUsername;
    /**
     * The Db password.
     */
    @Value("${groupdocs.annotation.dbPassword}")
    private String              dbPassword;
    /**
     * The Storage path.
     */
    @Value("${groupdocs.annotation.storagePath}")
    private String              storagePath;
    /**
     * The Use authorization.
     */
    @Value("${groupdocs.annotation.useAuthorization}")
    private Boolean             useAuthorization;
    /**
     * The Use cache.
     */
    @Value("${groupdocs.annotation.useCache}")
    private Boolean             useCache;
    /**
     * The Expiration date.
     */
    @Value("${groupdocs.annotation.expirationDate}")
    private Integer             expirationDate;
    /**
     * The Encryption key.
     */
    @Value("${groupdocs.annotation.encryptionKey}")
    private String              encryptionKey;
    /**
     * The Locales path.
     */
    @Value("${groupdocs.annotation.localesPath}")
    private String              localesPath;
    /**
     * The Quality.
     */
    @Value("${groupdocs.annotation.quality}")
    private Integer             quality;
    /**
     * The Show thumbnails.
     */
    @Value("${groupdocs.annotation.showThumbnails}")
    private Boolean             showThumbnails;
    /**
     * The Open thumbnails.
     */
    @Value("${groupdocs.annotation.openThumbnails}")
    private Boolean             openThumbnails;
    /**
     * The Initial zoom.
     */
    @Value("${groupdocs.annotation.initialZoom}")
    private Integer             initialZoom;
    /**
     * The Zoom to fit width.
     */
    @Value("${groupdocs.annotation.zoomToFitWidth}")
    private Boolean             zoomToFitWidth;
    /**
     * The Zoom to fit height.
     */
    @Value("${groupdocs.annotation.zoomToFitHeight}")
    private Boolean             zoomToFitHeight;
    /**
     * The Width.
     */
    @Value("${groupdocs.annotation.width}")
    private Integer             width;
    /**
     * The Height.
     */
    @Value("${groupdocs.annotation.height}")
    private Integer             height;
    /**
     * The Show print.
     */
    @Value("${groupdocs.annotation.showPrint}")
    private Boolean             showPrint;
    /**
     * The Show zoom.
     */
    @Value("${groupdocs.annotation.showZoom}")
    private Boolean             showZoom;
    /**
     * The Show paging.
     */
    @Value("${groupdocs.annotation.showPaging}")
    private Boolean             showPaging;
    /**
     * The Preload pages count.
     */
    @Value("${groupdocs.annotation.preloadPagesCount}")
    private Integer             preloadPagesCount;
    /**
     * The Show header.
     */
    @Value("${groupdocs.annotation.showHeader}")
    private Boolean             showHeader;
    /**
     * The Show file explorer.
     */
    @Value("${groupdocs.annotation.showFileExplorer}")
    private Boolean             showFileExplorer;
    /**
     * The Use em scaling.
     */
    @Value("${groupdocs.annotation.useEmScaling}")
    private Boolean             useEmScaling;
    /**
     * The Enable right click menu.
     */
    @Value("${groupdocs.annotation.enableRightClickMenu}")
    private Boolean             enableRightClickMenu;
    /**
     * The Show toolbar.
     */
    @Value("${groupdocs.annotation.showToolbar}")
    private Boolean             showToolbar;
    /**
     * The Enable side panel.
     */
    @Value("${groupdocs.annotation.enableSidePanel}")
    private Boolean             enableSidePanel;
    /**
     * The Scroll on focus.
     */
    @Value("${groupdocs.annotation.scrollOnFocus}")
    private Boolean             scrollOnFocus;
    /**
     * The Strike out color.
     */
    @Value("${groupdocs.annotation.strikeOutColor}")
    private String              strikeOutColor;
    /**
     * The Highlight color.
     */
    @Value("${groupdocs.annotation.highlightColor}")
    private String              highlightColor;
    /**
     * The Underline color.
     */
    @Value("${groupdocs.annotation.underlineColor}")
    private String              underlineColor;
    /**
     * The Text field background color.
     */
    @Value("${groupdocs.annotation.textFieldBackgroundColor}")
    private String              textFieldBackgroundColor;
    /**
     * The Tab navigation enabled.
     */
    @Value("${groupdocs.annotation.tabNavigationEnabled}")
    private Boolean             tabNavigationEnabled;
    /**
     * The Minimum image width.
     */
    @Value("${groupdocs.annotation.minimumImageWidth}")
    private Integer             minimumImageWidth;
    /**
     * The Area tool options pen width.
     */
    @Value("${groupdocs.annotation.areaToolOptionsPenWidth}")
    private Integer             areaToolOptionsPenWidth;
    /**
     * The Area tool options pen color.
     */
    @Value("${groupdocs.annotation.areaToolOptionsPenColor}")
    private String              areaToolOptionsPenColor;
    /**
     * The Area tool options pen dash style.
     */
    @Value("${groupdocs.annotation.areaToolOptionsPenDashStyle}")
    private Integer             areaToolOptionsPenDashStyle;
    /**
     * The Area tool options brush color.
     */
    @Value("${groupdocs.annotation.areaToolOptionsBrushColor}")
    private String              areaToolOptionsBrushColor;
    /**
     * The Polyline tool options pen width.
     */
    @Value("${groupdocs.annotation.polylineToolOptionsPenWidth}")
    private Integer             polylineToolOptionsPenWidth;
    /**
     * The Polyline tool options pen color.
     */
    @Value("${groupdocs.annotation.polylineToolOptionsPenColor}")
    private String              polylineToolOptionsPenColor;
    /**
     * The Polyline tool options pen dash style.
     */
    @Value("${groupdocs.annotation.polylineToolOptionsPenDashStyle}")
    private Integer             polylineToolOptionsPenDashStyle;
    /**
     * The Polyline tool options brush color.
     */
    @Value("${groupdocs.annotation.polylineToolOptionsBrushColor}")
    private String              polylineToolOptionsBrushColor;
    /**
     * The Arrow tool options pen width.
     */
    @Value("${groupdocs.annotation.arrowToolOptionsPenWidth}")
    private Integer             arrowToolOptionsPenWidth;
    /**
     * The Arrow tool options pen color.
     */
    @Value("${groupdocs.annotation.arrowToolOptionsPenColor}")
    private String              arrowToolOptionsPenColor;
    /**
     * The Arrow tool options pen dash style.
     */
    @Value("${groupdocs.annotation.arrowToolOptionsPenDashStyle}")
    private Integer             arrowToolOptionsPenDashStyle;
    /**
     * The Arrow tool options brush color.
     */
    @Value("${groupdocs.annotation.arrowToolOptionsBrushColor}")
    private String              arrowToolOptionsBrushColor;
    /**
     * The Distance pen color.
     */
    @Value("${groupdocs.annotation.distanceToolOptionsPenColor}")
    private String              distanceToolOptionsPenColor;
    /**
     * The Pen color.
     */
    @Value("${groupdocs.annotation.penColor}")
    private String              penColor;
    /**
     * The Pen width.
     */
    @Value("${groupdocs.annotation.penWidth}")
    private Integer             penWidth;
    /**
     * The Pen style.
     */
    @Value("${groupdocs.annotation.penStyle}")
    private Integer             penStyle;
    /**
     * The Enabled tools.
     */
    @Value("${groupdocs.annotation.enabledTools}")
    private Integer             enabledTools;
    /**
     * The Connector position.
     */
    @Value("${groupdocs.annotation.connectorPosition}")
    private Integer             connectorPosition;
    /**
     * The Save reply on focus loss.
     */
    @Value("${groupdocs.annotation.saveReplyOnFocusLoss}")
    private Boolean             saveReplyOnFocusLoss;
    /**
     * The Clickable annotations.
     */
    @Value("${groupdocs.annotation.clickableAnnotations}")
    private Boolean             clickableAnnotations;
    /**
     * The Disconnect uncommented.
     */
    @Value("${groupdocs.annotation.disconnectUncommented}")
    private Boolean             disconnectUncommented;
    /**
     * The Strikeout mode.
     */
    @Value("${groupdocs.annotation.strikeoutMode}")
    private Integer             strikeoutMode;
    /**
     * The Any tool selection.
     */
    @Value("${groupdocs.annotation.anyToolSelection}")
    private Boolean             anyToolSelection;
    /**
     * The Sidebar container selector.
     */
    @Value("${groupdocs.annotation.sidebarContainerSelector}")
    private String              sidebarContainerSelector;
    /**
     * The Use page number in url hash.
     */
    @Value("${groupdocs.annotation.usePageNumberInUrlHash}")
    private Boolean             usePageNumberInUrlHash;
    /**
     * The Text selection synchronous calculation.
     */
    @Value("${groupdocs.annotation.textSelectionSynchronousCalculation}")
    private Boolean             textSelectionSynchronousCalculation;
    /**
     * The Variable height page support.
     */
    @Value("${groupdocs.annotation.variableHeightPageSupport}")
    private Boolean             variableHeightPageSupport;
    /**
     * The Create markup.
     */
    @Value("${groupdocs.annotation.createMarkup}")
    private Boolean             createMarkup;
    /**
     * The Use _ pdf.
     */
    @Value("${groupdocs.annotation.use_pdf}")
    private Boolean             usePdf;
    /**
     * The Mode.
     */
    @Value("${groupdocs.annotation.mode}")
    private String              mode;
    /**
     * The Selection container selector.
     */
    @Value("${groupdocs.annotation.selectionContainerSelector}")
    private String              selectionContainerSelector;
    /**
     * The Graphics container selector.
     */
    @Value("${groupdocs.annotation.graphicsContainerSelector}")
    private String              graphicsContainerSelector;
    /**
     * The Use browser cache.
     */
    @Value("${groupdocs.annotation.useBrowserCache}")
    private Boolean             useBrowserCache;
    /**
     * The Widget id.
     */
    @Value("${groupdocs.annotation.widgetId}")
    private String              widgetId;
    /**
     * The Right panel enabled.
     */
    @Value("${groupdocs.annotation.rightPanelEnabled}")
    private Boolean             rightPanelEnabled;
    /**
     * The Max cache size.
     */
    @Value("${groupdocs.annotation.maxCacheSize}")
    private Long                maxCacheSize;
    /**
     * The Upload path.
     */
    @Value("${groupdocs.annotation.uploadPath}")
    private String              uploadPath;
    /**
     * The Undo enabled.
     */
    @Value("${groupdocs.annotation.undoEnabled}")
    private Boolean             undoEnabled;
    /**
     * The Jquery file download cookie name.
     */
    @Value("${groupdocs.annotation.jqueryFileDownloadCookieName}")
    private String              jqueryFileDownloadCookieName;
    /**
     * The Watermark font size.
     */
    @Value("${groupdocs.annotation.watermarkFontSize}")
    private Integer             watermarkFontSize;
    /**
     * The Watermark position.
     */
    @Value("${groupdocs.annotation.watermarkPosition}")
    private String              watermarkPosition;
    /**
     * The Watermark text.
     */
    @Value("${groupdocs.annotation.watermarkText}")
    private String              watermarkText;
    /**
     * The Convert word documents completely.
     */
    @Value("${groupdocs.annotation.convertWordDocumentsCompletely}")
    private Boolean             convertWordDocumentsCompletely;
    /**
     * The Ignore document absence.
     */
    @Value("${groupdocs.annotation.ignoreDocumentAbsence}")
    private Boolean             ignoreDocumentAbsence;
    /**
     * The Preload pages on browser side.
     */
    @Value("${groupdocs.annotation.preloadPagesOnBrowserSide}")
    private Boolean             preloadPagesOnBrowserSide;
    /**
     * The Print with watermark.
     */
    @Value("${groupdocs.annotation.printWithWatermark}")
    private Boolean             printWithWatermark;
    /**
     * The Support page rotation.
     */
    @Value("${groupdocs.annotation.supportPageRotation}")
    private Boolean             supportPageRotation;
    /**
     * The Store logic.
     */
    @Value("${groupdocs.annotation.storeLogic}")
    private String              storeLogic;
    /**
     * The Print annotations.
     */
    @Value("${groupdocs.annotation.printAnnotations}")
    private Boolean             printAnnotations;
    /**
     * The Localization.
     */
    @Value("${groupdocs.annotation.localization}")
    private String              localization;
    /**
     * The Disable atmosphere.
     */
    @Value("${groupdocs.annotation.disableAtmosphere}")
    private Boolean             disableAtmosphere;
    /**
     * The File display name.
     */
    @Value("${groupdocs.annotation.fileDisplayName}")
    private String              fileDisplayName;
    /**
     * The Is case sensitive.
     */
    @Value("${groupdocs.annotation.isCaseSensitive}")
    private Boolean             caseSensitive;
    /**
     * The Search for separate words.
     */
    @Value("${groupdocs.annotation.searchForSeparateWords}")
    private Boolean             searchForSeparateWords;
    /**
     * default file name.
     */
    @Value("${groupdocs.annotation.defaultFileName}")
    private String              defaultFileName;

    @Value("${groupdocs.annotation.encoding}")
    private String              encoding;

    @Value("${groupdocs.annotation.fontsPath}")
    private String              fontsPath;

    @Value("${groupdocs.annotation.pdfPrintMarginsEnabled}")
    private Boolean             pdfPrintMarginsEnabled;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLicensePath() {
        // FUTURE: posteriormente, tornar configurável o local da licença.
        return licensePath;
    }

    /**
     * Setter for property 'licensePath'.
     *
     * @param licensePath Value to set for property 'licensePath'.
     */
    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getApplicationPath() {
        return applicationPath;
    }

    /**
     * Setter for property 'applicationPath'.
     *
     * @param applicationPath Value to set for property 'applicationPath'.
     */
    public void setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBasePath() {
        if (basePath == null || basePath.trim().isEmpty()) {
            Path viewerBase = Paths.get(System.getProperty("java.io.tmpdir"), DIRETORIO_BASE);
            if (!Files.exists(viewerBase)) {
                try {
                    Files.createDirectory(viewerBase);
                } catch (IOException e) {
                    throw new VisualizadorInfraException(e);
                }
            }
            basePath = viewerBase.toString();
        }

        if (!basePath.endsWith(File.separator)) {
            basePath = basePath.concat(File.separator);
        }

        return this.basePath;
    }

    /**
     * Setter for property 'basePath'.
     *
     * @param basePath Value to set for property 'basePath'.
     */
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    /**
     * Getter for property 'storageType'.
     *
     * @return Value for property 'storageType'.
     */
    public String getStorageType() {
        return storageType;
    }

    /**
     * Setter for property 'storageType'.
     *
     * @param storageType Value to set for property 'storageType'.
     */
    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    /**
     * Getter for property 'dbServer'.
     *
     * @return Value for property 'dbServer'.
     */
    public String getDbServer() {
        return dbServer;
    }

    /**
     * Setter for property 'dbServer'.
     *
     * @param dbServer Value to set for property 'dbServer'.
     */
    public void setDbServer(String dbServer) {
        this.dbServer = dbServer;
    }

    /**
     * Getter for property 'dbPort'.
     *
     * @return Value for property 'dbPort'.
     */
    public Integer getDbPort() {
        return dbPort;
    }

    /**
     * Setter for property 'dbPort'.
     *
     * @param dbPort Value to set for property 'dbPort'.
     */
    public void setDbPort(Integer dbPort) {
        this.dbPort = dbPort;
    }

    /**
     * Getter for property 'dbName'.
     *
     * @return Value for property 'dbName'.
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * Setter for property 'dbName'.
     *
     * @param dbName Value to set for property 'dbName'.
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * Getter for property 'dbUsername'.
     *
     * @return Value for property 'dbUsername'.
     */
    public String getDbUsername() {
        return dbUsername;
    }

    /**
     * Setter for property 'dbUsername'.
     *
     * @param dbUsername Value to set for property 'dbUsername'.
     */
    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    /**
     * Getter for property 'dbPassword'.
     *
     * @return Value for property 'dbPassword'.
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * Setter for property 'dbPassword'.
     *
     * @param dbPassword Value to set for property 'dbPassword'.
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
     * Getter for property 'storagePath'.
     *
     * @return Value for property 'storagePath'.
     */
    public String getStoragePath() {
        return "null".equals(storagePath) ? null : storagePath;
    }

    /**
     * Setter for property 'storagePath'.
     *
     * @param storagePath Value to set for property 'storagePath'.
     */
    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * Getter for property 'useAuthorization'.
     *
     * @return Value for property 'useAuthorization'.
     */
    public Boolean isUseAuthorization() {
        return useAuthorization;
    }

    /**
     * Setter for property 'useAuthorization'.
     *
     * @param useAuthorization Value to set for property 'useAuthorization'.
     */

    public void setUseAuthorization(Boolean useAuthorization) {
        this.useAuthorization = useAuthorization;
    }

    /**
     * Getter for property 'useCache'.
     *
     * @return Value for property 'useCache'.
     */
    @Override
    public Boolean isUseCache() {
        return useCache;
    }

    /**
     * Setter for property 'useCache'.
     *
     * @param useCache Value to set for property 'useCache'.
     */

    public void setUseCache(Boolean useCache) {
        this.useCache = useCache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getExpirationDate() {
        return expirationDate;
    }

    /**
     * Setter for property 'expirationDate'.
     *
     * @param expirationDate Value to set for property 'expirationDate'.
     */

    public void setExpirationDate(Integer expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEncryptionKey() {
        return encryptionKey;
    }

    /**
     * Setter for property 'encryptionKey'.
     *
     * @param encryptionKey Value to set for property 'encryptionKey'.
     */

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLocalesPath() {
        return localesPath;
    }

    /**
     * Setter for property 'localesPath'.
     *
     * @param localesPath Value to set for property 'localesPath'.
     */

    public void setLocalesPath(String localesPath) {
        this.localesPath = localesPath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getQuality() {
        return quality;
    }

    /**
     * Setter for property 'quality'.
     *
     * @param quality Value to set for property 'quality'.
     */

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    /**
     * Getter for property 'showThumbnails'.
     *
     * @return Value for property 'showThumbnails'.
     */
    @Override
    public Boolean isShowThumbnails() {
        return showThumbnails;
    }

    /**
     * Setter for property 'showThumbnails'.
     *
     * @param showThumbnails Value to set for property 'showThumbnails'.
     */

    public void setShowThumbnails(Boolean showThumbnails) {
        this.showThumbnails = showThumbnails;
    }

    /**
     * Getter for property 'openThumbnails'.
     *
     * @return Value for property 'openThumbnails'.
     */
    @Override
    public Boolean isOpenThumbnails() {
        return openThumbnails;
    }

    /**
     * Setter for property 'openThumbnails'.
     *
     * @param openThumbnails Value to set for property 'openThumbnails'.
     */

    public void setOpenThumbnails(Boolean openThumbnails) {
        this.openThumbnails = openThumbnails;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getInitialZoom() {
        return initialZoom;
    }

    /**
     * Setter for property 'initialZoom'.
     *
     * @param initialZoom Value to set for property 'initialZoom'.
     */

    public void setInitialZoom(Integer initialZoom) {
        this.initialZoom = initialZoom;
    }

    /**
     * Getter for property 'zoomToFitWidth'.
     *
     * @return Value for property 'zoomToFitWidth'.
     */
    @Override
    public Boolean isZoomToFitWidth() {
        return zoomToFitWidth;
    }

    /**
     * Setter for property 'zoomToFitWidth'.
     *
     * @param zoomToFitWidth Value to set for property 'zoomToFitWidth'.
     */

    public void setZoomToFitWidth(Boolean zoomToFitWidth) {
        this.zoomToFitWidth = zoomToFitWidth;
    }

    /**
     * Getter for property 'zoomToFitHeight'.
     *
     * @return Value for property 'zoomToFitHeight'.
     */
    @Override
    public Boolean isZoomToFitHeight() {
        return zoomToFitHeight;
    }

    /**
     * Setter for property 'zoomToFitHeight'.
     *
     * @param zoomToFitHeight Value to set for property 'zoomToFitHeight'.
     */

    public void setZoomToFitHeight(Boolean zoomToFitHeight) {
        this.zoomToFitHeight = zoomToFitHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getWidth() {
        return width;
    }

    /**
     * Setter for property 'width'.
     *
     * @param width Value to set for property 'width'.
     */

    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getHeight() {
        return height;
    }

    /**
     * Setter for property 'height'.
     *
     * @param height Value to set for property 'height'.
     */

    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Getter for property 'showPrint'.
     *
     * @return Value for property 'showPrint'.
     */
    @Override
    public Boolean isShowPrint() {
        return showPrint;
    }

    /**
     * Setter for property 'showPrint'.
     *
     * @param showPrint Value to set for property 'showPrint'.
     */

    public void setShowPrint(Boolean showPrint) {
        this.showPrint = showPrint;
    }

    /**
     * Getter for property 'showZoom'.
     *
     * @return Value for property 'showZoom'.
     */
    @Override
    public Boolean isShowZoom() {
        return showZoom;
    }

    /**
     * Setter for property 'showZoom'.
     *
     * @param showZoom Value to set for property 'showZoom'.
     */

    public void setShowZoom(Boolean showZoom) {
        this.showZoom = showZoom;
    }

    /**
     * Getter for property 'showPaging'.
     *
     * @return Value for property 'showPaging'.
     */
    @Override
    public Boolean isShowPaging() {
        return showPaging;
    }

    /**
     * Setter for property 'showPaging'.
     *
     * @param showPaging Value to set for property 'showPaging'.
     */

    public void setShowPaging(Boolean showPaging) {
        this.showPaging = showPaging;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getPreloadPagesCount() {
        return preloadPagesCount;
    }

    /**
     * Setter for property 'preloadPagesCount'.
     *
     * @param preloadPagesCount Value to set for property 'preloadPagesCount'.
     */

    public void setPreloadPagesCount(Integer preloadPagesCount) {
        this.preloadPagesCount = preloadPagesCount;
    }

    /**
     * Getter for property 'showHeader'.
     *
     * @return Value for property 'showHeader'.
     */
    @Override
    public Boolean isShowHeader() {
        return showHeader;
    }

    /**
     * Setter for property 'showHeader'.
     *
     * @param showHeader Value to set for property 'showHeader'.
     */

    public void setShowHeader(Boolean showHeader) {
        this.showHeader = showHeader;
    }

    /**
     * Getter for property 'showFileExplorer'.
     *
     * @return Value for property 'showFileExplorer'.
     */
    @Override
    public Boolean isShowFileExplorer() {
        return showFileExplorer;
    }

    /**
     * Setter for property 'showFileExplorer'.
     *
     * @param showFileExplorer Value to set for property 'showFileExplorer'.
     */

    public void setShowFileExplorer(Boolean showFileExplorer) {
        this.showFileExplorer = showFileExplorer;
    }

    /**
     * Getter for property 'useEmScaling'.
     *
     * @return Value for property 'useEmScaling'.
     */
    @Override
    public Boolean isUseEmScaling() {
        return useEmScaling;
    }

    /**
     * Setter for property 'useEmScaling'.
     *
     * @param useEmScaling Value to set for property 'useEmScaling'.
     */

    public void setUseEmScaling(Boolean useEmScaling) {
        this.useEmScaling = useEmScaling;
    }

    /**
     * Getter for property 'enableRightClickMenu'.
     *
     * @return Value for property 'enableRightClickMenu'.
     */
    @Override
    public Boolean isEnableRightClickMenu() {
        return enableRightClickMenu;
    }

    /**
     * Setter for property 'enableRightClickMenu'.
     *
     * @param enableRightClickMenu Value to set for property 'enableRightClickMenu'.
     */

    public void setEnableRightClickMenu(Boolean enableRightClickMenu) {
        this.enableRightClickMenu = enableRightClickMenu;
    }

    /**
     * Getter for property 'showToolbar'.
     *
     * @return Value for property 'showToolbar'.
     */
    @Override
    public Boolean isShowToolbar() {
        return showToolbar;
    }

    /**
     * Setter for property 'showToolbar'.
     *
     * @param showToolbar Value to set for property 'showToolbar'.
     */

    public void setShowToolbar(Boolean showToolbar) {
        this.showToolbar = showToolbar;
    }

    /**
     * Getter for property 'enableSidePanel'.
     *
     * @return Value for property 'enableSidePanel'.
     */
    @Override
    public Boolean isEnableSidePanel() {
        return enableSidePanel;
    }

    /**
     * Setter for property 'enableSidePanel'.
     *
     * @param enableSidePanel Value to set for property 'enableSidePanel'.
     */

    public void setEnableSidePanel(Boolean enableSidePanel) {
        this.enableSidePanel = enableSidePanel;
    }

    /**
     * Getter for property 'scrollOnFocus'.
     *
     * @return Value for property 'scrollOnFocus'.
     */
    @Override
    public Boolean isScrollOnFocus() {
        return scrollOnFocus;
    }

    /**
     * Setter for property 'scrollOnFocus'.
     *
     * @param scrollOnFocus Value to set for property 'scrollOnFocus'.
     */

    public void setScrollOnFocus(Boolean scrollOnFocus) {
        this.scrollOnFocus = scrollOnFocus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStrikeOutColor() {
        return strikeOutColor;
    }

    /**
     * Setter for property 'strikeOutColor'.
     *
     * @param strikeOutColor Value to set for property 'strikeOutColor'.
     */

    public void setStrikeOutColor(String strikeOutColor) {
        this.strikeOutColor = strikeOutColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHighlightColor() {
        return highlightColor;
    }

    /**
     * Setter for property 'highlightColor'.
     *
     * @param highlightColor Value to set for property 'highlightColor'.
     */

    public void setHighlightColor(String highlightColor) {
        this.highlightColor = highlightColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUnderlineColor() {
        return underlineColor;
    }

    /**
     * Setter for property 'underlineColor'.
     *
     * @param underlineColor Value to set for property 'underlineColor'.
     */

    public void setUnderlineColor(String underlineColor) {
        this.underlineColor = underlineColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTextFieldBackgroundColor() {
        return textFieldBackgroundColor;
    }

    /**
     * Setter for property 'textFieldBackgroundColor'.
     *
     * @param textFieldBackgroundColor Value to set for property 'textFieldBackgroundColor'.
     */

    public void setTextFieldBackgroundColor(String textFieldBackgroundColor) {
        this.textFieldBackgroundColor = textFieldBackgroundColor;
    }

    /**
     * Getter for property 'tabNavigationEnabled'.
     *
     * @return Value for property 'tabNavigationEnabled'.
     */
    @Override
    public Boolean isTabNavigationEnabled() {
        return tabNavigationEnabled;
    }

    /**
     * Setter for property 'tabNavigationEnabled'.
     *
     * @param tabNavigationEnabled Value to set for property 'tabNavigationEnabled'.
     */

    public void setTabNavigationEnabled(Boolean tabNavigationEnabled) {
        this.tabNavigationEnabled = tabNavigationEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMinimumImageWidth() {
        return minimumImageWidth;
    }

    /**
     * Setter for property 'minimumImageWidth'.
     *
     * @param minimumImageWidth Value to set for property 'minimumImageWidth'.
     */

    public void setMinimumImageWidth(Integer minimumImageWidth) {
        this.minimumImageWidth = minimumImageWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getAreaToolOptionsPenWidth() {
        return areaToolOptionsPenWidth;
    }

    /**
     * Setter for property 'areaToolOptionsPenWidth'.
     *
     * @param areaToolOptionsPenWidth Value to set for property 'areaToolOptionsPenWidth'.
     */

    public void setAreaToolOptionsPenWidth(Integer areaToolOptionsPenWidth) {
        this.areaToolOptionsPenWidth = areaToolOptionsPenWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAreaToolOptionsPenColor() {
        return areaToolOptionsPenColor;
    }

    /**
     * Setter for property 'areaToolOptionsPenColor'.
     *
     * @param areaToolOptionsPenColor Value to set for property 'areaToolOptionsPenColor'.
     */

    public void setAreaToolOptionsPenColor(String areaToolOptionsPenColor) {
        this.areaToolOptionsPenColor = areaToolOptionsPenColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getAreaToolOptionsPenDashStyle() {
        return areaToolOptionsPenDashStyle;
    }

    /**
     * Setter for property 'areaToolOptionsPenDashStyle'.
     *
     * @param areaToolOptionsPenDashStyle Value to set for property 'areaToolOptionsPenDashStyle'.
     */

    public void setAreaToolOptionsPenDashStyle(Integer areaToolOptionsPenDashStyle) {
        this.areaToolOptionsPenDashStyle = areaToolOptionsPenDashStyle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAreaToolOptionsBrushColor() {
        return areaToolOptionsBrushColor;
    }

    /**
     * Setter for property 'areaToolOptionsBrushColor'.
     *
     * @param areaToolOptionsBrushColor Value to set for property 'areaToolOptionsBrushColor'.
     */

    public void setAreaToolOptionsBrushColor(String areaToolOptionsBrushColor) {
        this.areaToolOptionsBrushColor = areaToolOptionsBrushColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getPolylineToolOptionsPenWidth() {
        return polylineToolOptionsPenWidth;
    }

    /**
     * Setter for property 'polylineToolOptionsPenWidth'.
     *
     * @param polylineToolOptionsPenWidth Value to set for property 'polylineToolOptionsPenWidth'.
     */

    public void setPolylineToolOptionsPenWidth(Integer polylineToolOptionsPenWidth) {
        this.polylineToolOptionsPenWidth = polylineToolOptionsPenWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPolylineToolOptionsPenColor() {
        return polylineToolOptionsPenColor;
    }

    /**
     * Setter for property 'polylineToolOptionsPenColor'.
     *
     * @param polylineToolOptionsPenColor Value to set for property 'polylineToolOptionsPenColor'.
     */

    public void setPolylineToolOptionsPenColor(String polylineToolOptionsPenColor) {
        this.polylineToolOptionsPenColor = polylineToolOptionsPenColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getPolylineToolOptionsPenDashStyle() {
        return polylineToolOptionsPenDashStyle;
    }

    /**
     * Setter for property 'polylineToolOptionsPenDashStyle'.
     *
     * @param polylineToolOptionsPenDashStyle Value to set for property 'polylineToolOptionsPenDashStyle'.
     */

    public void setPolylineToolOptionsPenDashStyle(Integer polylineToolOptionsPenDashStyle) {
        this.polylineToolOptionsPenDashStyle = polylineToolOptionsPenDashStyle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPolylineToolOptionsBrushColor() {
        return polylineToolOptionsBrushColor;
    }

    /**
     * Setter for property 'polylineToolOptionsBrushColor'.
     *
     * @param polylineToolOptionsBrushColor Value to set for property 'polylineToolOptionsBrushColor'.
     */

    public void setPolylineToolOptionsBrushColor(String polylineToolOptionsBrushColor) {
        this.polylineToolOptionsBrushColor = polylineToolOptionsBrushColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getArrowToolOptionsPenWidth() {
        return arrowToolOptionsPenWidth;
    }

    /**
     * Setter for property 'arrowToolOptionsPenWidth'.
     *
     * @param arrowToolOptionsPenWidth Value to set for property 'arrowToolOptionsPenWidth'.
     */

    public void setArrowToolOptionsPenWidth(Integer arrowToolOptionsPenWidth) {
        this.arrowToolOptionsPenWidth = arrowToolOptionsPenWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getArrowToolOptionsPenColor() {
        return arrowToolOptionsPenColor;
    }

    /**
     * Setter for property 'arrowToolOptionsPenColor'.
     *
     * @param arrowToolOptionsPenColor Value to set for property 'arrowToolOptionsPenColor'.
     */

    public void setArrowToolOptionsPenColor(String arrowToolOptionsPenColor) {
        this.arrowToolOptionsPenColor = arrowToolOptionsPenColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getArrowToolOptionsPenDashStyle() {
        return arrowToolOptionsPenDashStyle;
    }

    /**
     * Setter for property 'arrowToolOptionsPenDashStyle'.
     *
     * @param arrowToolOptionsPenDashStyle Value to set for property 'arrowToolOptionsPenDashStyle'.
     */

    public void setArrowToolOptionsPenDashStyle(Integer arrowToolOptionsPenDashStyle) {
        this.arrowToolOptionsPenDashStyle = arrowToolOptionsPenDashStyle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getArrowToolOptionsBrushColor() {
        return arrowToolOptionsBrushColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDistanceToolOptionsPenColor() {
        return distanceToolOptionsPenColor;
    }

    /**
     * Sets distance tool options pen color.
     *
     * @param distanceToolOptionsPenColor the distance tool options pen color
     */

    public void setDistanceToolOptionsPenColor(String distanceToolOptionsPenColor) {
        this.distanceToolOptionsPenColor = distanceToolOptionsPenColor;
    }

    /**
     * Setter for property 'arrowToolOptionsBrushColor'.
     *
     * @param arrowToolOptionsBrushColor Value to set for property 'arrowToolOptionsBrushColor'.
     */

    public void setArrowToolOptionsBrushColor(String arrowToolOptionsBrushColor) {
        this.arrowToolOptionsBrushColor = arrowToolOptionsBrushColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPenColor() {
        return penColor;
    }

    /**
     * Setter for property 'penColor'.
     *
     * @param penColor Value to set for property 'penColor'.
     */

    public void setPenColor(String penColor) {
        this.penColor = penColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getPenWidth() {
        return penWidth;
    }

    /**
     * Setter for property 'penWidth'.
     *
     * @param penWidth Value to set for property 'penWidth'.
     */

    public void setPenWidth(Integer penWidth) {
        this.penWidth = penWidth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getPenStyle() {
        return penStyle;
    }

    /**
     * Setter for property 'penStyle'.
     *
     * @param penStyle Value to set for property 'penStyle'.
     */

    public void setPenStyle(Integer penStyle) {
        this.penStyle = penStyle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getEnabledTools() {
        return enabledTools;
    }

    /**
     * Setter for property 'enabledTools'.
     *
     * @param enabledTools Value to set for property 'enabledTools'.
     */

    public void setEnabledTools(Integer enabledTools) {
        this.enabledTools = enabledTools;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getConnectorPosition() {
        return connectorPosition;
    }

    /**
     * Setter for property 'connectorPosition'.
     *
     * @param connectorPosition Value to set for property 'connectorPosition'.
     */

    public void setConnectorPosition(Integer connectorPosition) {
        this.connectorPosition = connectorPosition;
    }

    /**
     * Getter for property 'saveReplyOnFocusLoss'.
     *
     * @return Value for property 'saveReplyOnFocusLoss'.
     */
    @Override
    public Boolean isSaveReplyOnFocusLoss() {
        return saveReplyOnFocusLoss;
    }

    /**
     * Setter for property 'saveReplyOnFocusLoss'.
     *
     * @param saveReplyOnFocusLoss Value to set for property 'saveReplyOnFocusLoss'.
     */

    public void setSaveReplyOnFocusLoss(Boolean saveReplyOnFocusLoss) {
        this.saveReplyOnFocusLoss = saveReplyOnFocusLoss;
    }

    /**
     * Getter for property 'clickableAnnotations'.
     *
     * @return Value for property 'clickableAnnotations'.
     */
    @Override
    public Boolean isClickableAnnotations() {
        return clickableAnnotations;
    }

    /**
     * Setter for property 'clickableAnnotations'.
     *
     * @param clickableAnnotations Value to set for property 'clickableAnnotations'.
     */

    public void setClickableAnnotations(Boolean clickableAnnotations) {
        this.clickableAnnotations = clickableAnnotations;
    }

    /**
     * Getter for property 'disconnectUncommented'.
     *
     * @return Value for property 'disconnectUncommented'.
     */
    @Override
    public Boolean isDisconnectUncommented() {
        return disconnectUncommented;
    }

    /**
     * Setter for property 'disconnectUncommented'.
     *
     * @param disconnectUncommented Value to set for property 'disconnectUncommented'.
     */

    public void setDisconnectUncommented(Boolean disconnectUncommented) {
        this.disconnectUncommented = disconnectUncommented;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getStrikeoutMode() {
        return strikeoutMode;
    }

    /**
     * Setter for property 'strikeoutMode'.
     *
     * @param strikeoutMode Value to set for property 'strikeoutMode'.
     */

    public void setStrikeoutMode(Integer strikeoutMode) {
        this.strikeoutMode = strikeoutMode;
    }

    /**
     * Getter for property 'anyToolSelection'.
     *
     * @return Value for property 'anyToolSelection'.
     */
    @Override
    public Boolean isAnyToolSelection() {
        return anyToolSelection;
    }

    /**
     * Setter for property 'anyToolSelection'.
     *
     * @param anyToolSelection Value to set for property 'anyToolSelection'.
     */

    public void setAnyToolSelection(Boolean anyToolSelection) {
        this.anyToolSelection = anyToolSelection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSidebarContainerSelector() {
        return sidebarContainerSelector;
    }

    /**
     * Setter for property 'sidebarContainerSelector'.
     *
     * @param sidebarContainerSelector Value to set for property 'sidebarContainerSelector'.
     */

    public void setSidebarContainerSelector(String sidebarContainerSelector) {
        this.sidebarContainerSelector = sidebarContainerSelector;
    }

    /**
     * Getter for property 'usePageNumberInUrlHash'.
     *
     * @return Value for property 'usePageNumberInUrlHash'.
     */
    @Override
    public Boolean isUsePageNumberInUrlHash() {
        return usePageNumberInUrlHash;
    }

    /**
     * Setter for property 'usePageNumberInUrlHash'.
     *
     * @param usePageNumberInUrlHash Value to set for property 'usePageNumberInUrlHash'.
     */

    public void setUsePageNumberInUrlHash(Boolean usePageNumberInUrlHash) {
        this.usePageNumberInUrlHash = usePageNumberInUrlHash;
    }

    /**
     * Getter for property 'textSelectionSynchronousCalculation'.
     *
     * @return Value for property 'textSelectionSynchronousCalculation'.
     */
    @Override
    public Boolean isTextSelectionSynchronousCalculation() {
        return textSelectionSynchronousCalculation;
    }

    /**
     * Setter for property 'textSelectionSynchronousCalculation'.
     *
     * @param textSelectionSynchronousCalculation Value to set for property 'textSelectionSynchronousCalculation'.
     */

    public void setTextSelectionSynchronousCalculation(Boolean textSelectionSynchronousCalculation) {
        this.textSelectionSynchronousCalculation = textSelectionSynchronousCalculation;
    }

    /**
     * Getter for property 'variableHeightPageSupport'.
     *
     * @return Value for property 'variableHeightPageSupport'.
     */
    @Override
    public Boolean isVariableHeightPageSupport() {
        return variableHeightPageSupport;
    }

    /**
     * Setter for property 'variableHeightPageSupport'.
     *
     * @param variableHeightPageSupport Value to set for property 'variableHeightPageSupport'.
     */

    public void setVariableHeightPageSupport(Boolean variableHeightPageSupport) {
        this.variableHeightPageSupport = variableHeightPageSupport;
    }

    /**
     * Getter for property 'createMarkup'.
     *
     * @return Value for property 'createMarkup'.
     */
    @Override
    public Boolean isCreateMarkup() {
        return createMarkup;
    }

    /**
     * Setter for property 'createMarkup'.
     *
     * @param createMarkup Value to set for property 'createMarkup'.
     */

    public void setCreateMarkup(Boolean createMarkup) {
        this.createMarkup = createMarkup;
    }

    /**
     * Getter for property 'use_pdf'.
     *
     * @return Value for property 'use_pdf'.
     */
    @Override
    public Boolean isUse_pdf() {
        return usePdf;
    }

    /**
     * Setter for property 'use_pdf'.
     *
     * @param usePdf Value to set for property 'use_pdf'.
     */
    public void setUsePdf(Boolean usePdf) {
        this.usePdf = usePdf;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMode() {
        return mode;
    }

    /**
     * Setter for property 'mode'.
     *
     * @param mode Value to set for property 'mode'.
     */

    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSelectionContainerSelector() {
        return selectionContainerSelector;
    }

    /**
     * Setter for property 'selectionContainerSelector'.
     *
     * @param selectionContainerSelector Value to set for property 'selectionContainerSelector'.
     */

    public void setSelectionContainerSelector(String selectionContainerSelector) {
        this.selectionContainerSelector = selectionContainerSelector;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGraphicsContainerSelector() {
        return graphicsContainerSelector;
    }

    /**
     * Setter for property 'graphicsContainerSelector'.
     *
     * @param graphicsContainerSelector Value to set for property 'graphicsContainerSelector'.
     */

    public void setGraphicsContainerSelector(String graphicsContainerSelector) {
        this.graphicsContainerSelector = graphicsContainerSelector;
    }

    /**
     * Getter for property 'useBrowserCache'.
     *
     * @return Value for property 'useBrowserCache'.
     */
    @Override
    public Boolean isUseBrowserCache() {
        return useBrowserCache;
    }

    /**
     * Setter for property 'useBrowserCache'.
     *
     * @param useBrowserCache Value to set for property 'useBrowserCache'.
     */

    public void setUseBrowserCache(Boolean useBrowserCache) {
        this.useBrowserCache = useBrowserCache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWidgetId() {
        return widgetId;
    }

    /**
     * Setter for property 'widgetId'.
     *
     * @param widgetId Value to set for property 'widgetId'.
     */

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    /**
     * Getter for property 'rightPanelEnabled'.
     *
     * @return Value for property 'rightPanelEnabled'.
     */
    @Override
    public Boolean isRightPanelEnabled() {
        return rightPanelEnabled;
    }

    /**
     * Setter for property 'rightPanelEnabled'.
     *
     * @param rightPanelEnabled Value to set for property 'rightPanelEnabled'.
     */

    public void setRightPanelEnabled(Boolean rightPanelEnabled) {
        this.rightPanelEnabled = rightPanelEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getMaxCacheSize() {
        return maxCacheSize;
    }

    /**
     * Setter for property 'maxCacheSize'.
     *
     * @param maxCacheSize Value to set for property 'maxCacheSize'.
     */

    public void setMaxCacheSize(Long maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUploadPath() {
        return uploadPath;
    }

    /**
     * TODO: parametrizar {@inheritDoc}
     */
    @Override
    public String getCachePath() {
        Path path = Paths.get(getBasePath(), SUBDIRETORIO_CACHE);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new VisualizadorInfraException(e);
            }
        }
        return path.toString();
    }

    /**
     * Setter for property 'uploadPath'.
     *
     * @param uploadPath Value to set for property 'uploadPath'.
     */

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    /**
     * Getter for property 'undoEnabled'.
     *
     * @return Value for property 'undoEnabled'.
     */
    @Override
    public Boolean isUndoEnabled() {
        return undoEnabled;
    }

    /**
     * Setter for property 'undoEnabled'.
     *
     * @param undoEnabled Value to set for property 'undoEnabled'.
     */

    public void setUndoEnabled(Boolean undoEnabled) {
        this.undoEnabled = undoEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getJqueryFileDownloadCookieName() {
        return jqueryFileDownloadCookieName;
    }

    /**
     * Setter for property 'jqueryFileDownloadCookieName'.
     *
     * @param jqueryFileDownloadCookieName Value to set for property 'jqueryFileDownloadCookieName'.
     */

    public void setJqueryFileDownloadCookieName(String jqueryFileDownloadCookieName) {
        this.jqueryFileDownloadCookieName = jqueryFileDownloadCookieName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getWatermarkFontSize() {
        return watermarkFontSize;
    }

    /**
     * Setter for property 'watermarkFontSize'.
     *
     * @param watermarkFontSize Value to set for property 'watermarkFontSize'.
     */

    public void setWatermarkFontSize(Integer watermarkFontSize) {
        this.watermarkFontSize = watermarkFontSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWatermarkPosition() {
        return watermarkPosition;
    }

    /**
     * Setter for property 'watermarkPosition'.
     *
     * @param watermarkPosition Value to set for property 'watermarkPosition'.
     */

    public void setWatermarkPosition(String watermarkPosition) {
        this.watermarkPosition = watermarkPosition;
    }

    @Override
    public String getWatermarkText() {
        return watermarkText;
    }

    /**
     * Sets watermark text.
     *
     * @param watermarkText the watermark text
     */
    public void setWatermarkText(String watermarkText) {
        this.watermarkText = watermarkText;
    }

    /**
     * Getter for property 'convertWordDocumentsCompletely'.
     *
     * @return Value for property 'convertWordDocumentsCompletely'.
     */
    @Override
    public Boolean isConvertWordDocumentsCompletely() {
        return convertWordDocumentsCompletely;
    }

    /**
     * Gets file display name
     *
     * @return file display name
     */
    @Override
    public String getFileDisplayName() {
        return fileDisplayName;
    }

    /**
     * Sets file display name.
     *
     * @param fileDisplayName the file display name
     */

    public void setFileDisplayName(String fileDisplayName) {
        this.fileDisplayName = fileDisplayName;
    }

    /**
     * Setter for property 'convertWordDocumentsCompletely'.
     *
     * @param convertWordDocumentsCompletely Value to set for property 'convertWordDocumentsCompletely'.
     */

    public void setConvertWordDocumentsCompletely(Boolean convertWordDocumentsCompletely) {
        this.convertWordDocumentsCompletely = convertWordDocumentsCompletely;
    }

    /**
     * Getter for property 'ignoreDocumentAbsence'.
     *
     * @return Value for property 'ignoreDocumentAbsence'.
     */
    @Override
    public Boolean isIgnoreDocumentAbsence() {
        return ignoreDocumentAbsence;
    }

    /**
     * Setter for property 'ignoreDocumentAbsence'.
     *
     * @param ignoreDocumentAbsence Value to set for property 'ignoreDocumentAbsence'.
     */

    public void setIgnoreDocumentAbsence(Boolean ignoreDocumentAbsence) {
        this.ignoreDocumentAbsence = ignoreDocumentAbsence;
    }

    /**
     * Getter for property 'preloadPagesOnBrowserSide'.
     *
     * @return Value for property 'preloadPagesOnBrowserSide'.
     */
    @Override
    public Boolean isPreloadPagesOnBrowserSide() {
        return preloadPagesOnBrowserSide;
    }

    /**
     * Setter for property 'preloadPagesOnBrowserSide'.
     *
     * @param preloadPagesOnBrowserSide Value to set for property 'preloadPagesOnBrowserSide'.
     */

    public void setPreloadPagesOnBrowserSide(Boolean preloadPagesOnBrowserSide) {
        this.preloadPagesOnBrowserSide = preloadPagesOnBrowserSide;
    }

    /**
     * Getter for property 'printWithWatermark'.
     *
     * @return Value for property 'printWithWatermark'.
     */
    @Override
    public Boolean isPrintWithWatermark() {
        return printWithWatermark;
    }

    /**
     * Setter for property 'printWithWatermark'.
     *
     * @param printWithWatermark Value to set for property 'printWithWatermark'.
     */

    public void setPrintWithWatermark(Boolean printWithWatermark) {
        this.printWithWatermark = printWithWatermark;
    }

    /**
     * Getter for property 'supportPageRotation'.
     *
     * @return Value for property 'supportPageRotation'.
     */
    @Override
    public Boolean isSupportPageRotation() {
        return supportPageRotation;
    }

    /**
     * Setter for property 'supportPageRotation'.
     *
     * @param supportPageRotation Value to set for property 'supportPageRotation'.
     */

    public void setSupportPageRotation(Boolean supportPageRotation) {
        this.supportPageRotation = supportPageRotation;
    }

    /**
     * Getter for property 'storeLogic'.
     *
     * @return Value for property 'storeLogic'.
     */
    public String getStoreLogic() {
        return storeLogic;
    }

    /**
     * Setter for property 'storeLogic'.
     *
     * @param storeLogic Value to set for property 'storeLogic'.
     */

    public void setStoreLogic(String storeLogic) {
        this.storeLogic = storeLogic;
    }

    /**
     * Getter for property 'printAnnotations'.
     *
     * @return Value for property 'printAnnotations'.
     */
    @Override
    public Boolean isPrintAnnotations() {
        return printAnnotations;
    }

    /**
     * Setter for property 'printAnnotations'.
     *
     * @param printAnnotations Value to set for property 'printAnnotations'.
     */

    public void setPrintAnnotations(Boolean printAnnotations) {
        this.printAnnotations = printAnnotations;
    }

    /**
     * Gets disable atmosphere.
     *
     * @return the disable atmosphere
     */
    @Override
    public Boolean isDisableAtmosphere() {
        return disableAtmosphere;
    }

    @Override
    public Boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * Sets is case sensitive.
     *
     * @param caseSensitive the is case sensitive
     */

    public void setCaseSensitive(Boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    @Override
    public Boolean isSearchForSeparateWords() {
        return searchForSeparateWords;
    }

    /**
     * Sets search for separate words.
     *
     * @param searchForSeparateWords the search for separate words
     */

    public void setSearchForSeparateWords(Boolean searchForSeparateWords) {
        this.searchForSeparateWords = searchForSeparateWords;
    }

    /**
     * Sets disable atmosphere.
     *
     * @param disableAtmosphere the disable atmosphere
     */

    public void setDisableAtmosphere(Boolean disableAtmosphere) {
        this.disableAtmosphere = disableAtmosphere;
    }

    /**
     * Getter for property 'localization'.
     *
     * @return Value for property 'localization'.
     */
    @Override
    public String getLocalization() {
        return localization;
    }

    /**
     * Setter for property 'localization'.
     *
     * @param localization Value to set for property 'localization'.
     */

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    /**
     * Gets default file name.
     *
     * @return the default file name
     */
    public String getDefaultFileName() {
        return defaultFileName;
    }

    /**
     * Sets default file name.
     *
     * @param defaultFileName the default file name
     */
    public void setDefaultFileName(String defaultFileName) {
        this.defaultFileName = defaultFileName;
    }

    @Override
    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String getFontsPath() {
        return fontsPath;
    }

    public void setFontsPath(String fontsPath) {
        this.fontsPath = fontsPath;
    }

    @Override
    public Boolean isPdfPrintMarginsEnabled() {
        return pdfPrintMarginsEnabled;
    }

    public void setPdfPrintMarginsEnabled(Boolean pdfPrintMarginsEnabled) {
        this.pdfPrintMarginsEnabled = pdfPrintMarginsEnabled;
    }
}
