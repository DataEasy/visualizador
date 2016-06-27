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
     * parametrizar {@inheritDoc}
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
     * The License path.
     */
    @Value("${groupdocs.annotation.licensePath}")
    protected String  licensePath;
    /**
     * The Application path.
     */
    @Value("${groupdocs.annotation.applicationPath}")
    protected String  applicationPath;
    /**
     * The Base path.
     */
    @Value("${groupdocs.annotation.basePath}")
    protected String  basePath;

    /**
     * The Storage type.
     */
    @Value("${groupdocs.annotation.storageType}")
    protected String  storageType;
    /**
     * The Db server.
     */
    @Value("${groupdocs.annotation.dbServer}")
    protected String  dbServer;
    /**
     * The Db port.
     */
    @Value("${groupdocs.annotation.dbPort}")
    protected Integer dbPort;
    /**
     * The Db name.
     */
    @Value("${groupdocs.annotation.dbName}")
    protected String  dbName;
    /**
     * The Db username.
     */
    @Value("${groupdocs.annotation.dbUsername}")
    protected String  dbUsername;
    /**
     * The Db password.
     */
    @Value("${groupdocs.annotation.dbPassword}")
    protected String  dbPassword;
    /**
     * The Storage path.
     */
    @Value("${groupdocs.annotation.storagePath}")
    protected String  storagePath;
    /**
     * The Use authorization.
     */
    @Value("${groupdocs.annotation.useAuthorization}")
    protected Boolean useAuthorization;
    /**
     * The Use cache.
     */
    @Value("${groupdocs.annotation.useCache}")
    protected Boolean useCache;
    /**
     * The Expiration date.
     */
    @Value("${groupdocs.annotation.expirationDate}")
    protected Integer expirationDate;
    /**
     * The Encryption key.
     */
    @Value("${groupdocs.annotation.encryptionKey}")
    protected String  encryptionKey;
    /**
     * The Locales path.
     */
    @Value("${groupdocs.annotation.localesPath}")
    protected String  localesPath;
    /**
     * The Quality.
     */
    @Value("${groupdocs.annotation.quality}")
    protected Integer quality;
    /**
     * The Show thumbnails.
     */
    @Value("${groupdocs.annotation.showThumbnails}")
    protected Boolean showThumbnails;
    /**
     * The Open thumbnails.
     */
    @Value("${groupdocs.annotation.openThumbnails}")
    protected Boolean openThumbnails;
    /**
     * The Initial zoom.
     */
    @Value("${groupdocs.annotation.initialZoom}")
    protected Integer initialZoom;
    /**
     * The Zoom to fit width.
     */
    @Value("${groupdocs.annotation.zoomToFitWidth}")
    protected Boolean zoomToFitWidth;
    /**
     * The Zoom to fit height.
     */
    @Value("${groupdocs.annotation.zoomToFitHeight}")
    protected Boolean zoomToFitHeight;
    /**
     * The Width.
     */
    @Value("${groupdocs.annotation.width}")
    protected Integer width;
    /**
     * The Height.
     */
    @Value("${groupdocs.annotation.height}")
    protected Integer height;
    /**
     * The Show print.
     */
    @Value("${groupdocs.annotation.showPrint}")
    protected Boolean showPrint;
    /**
     * The Show zoom.
     */
    @Value("${groupdocs.annotation.showZoom}")
    protected Boolean showZoom;
    /**
     * The Show paging.
     */
    @Value("${groupdocs.annotation.showPaging}")
    protected Boolean showPaging;
    /**
     * The Preload pages count.
     */
    @Value("${groupdocs.annotation.preloadPagesCount}")
    protected Integer preloadPagesCount;
    /**
     * The Show header.
     */
    @Value("${groupdocs.annotation.showHeader}")
    protected Boolean showHeader;
    /**
     * The Show file explorer.
     */
    @Value("${groupdocs.annotation.showFileExplorer}")
    protected Boolean showFileExplorer;
    /**
     * The Use em scaling.
     */
    @Value("${groupdocs.annotation.useEmScaling}")
    protected Boolean useEmScaling;
    /**
     * The Enable right click menu.
     */
    @Value("${groupdocs.annotation.enableRightClickMenu}")
    protected Boolean enableRightClickMenu;
    /**
     * The Show toolbar.
     */
    @Value("${groupdocs.annotation.showToolbar}")
    protected Boolean showToolbar;
    /**
     * The Enable side panel.
     */
    @Value("${groupdocs.annotation.enableSidePanel}")
    protected Boolean enableSidePanel;
    /**
     * The Scroll on focus.
     */
    @Value("${groupdocs.annotation.scrollOnFocus}")
    protected Boolean scrollOnFocus;
    /**
     * The Strike out color.
     */
    @Value("${groupdocs.annotation.strikeOutColor}")
    protected String  strikeOutColor;
    /**
     * The Highlight color.
     */
    @Value("${groupdocs.annotation.highlightColor}")
    protected String  highlightColor;
    /**
     * The Underline color.
     */
    @Value("${groupdocs.annotation.underlineColor}")
    protected String  underlineColor;
    /**
     * The Text field background color.
     */
    @Value("${groupdocs.annotation.textFieldBackgroundColor}")
    protected String  textFieldBackgroundColor;
    /**
     * The Tab navigation enabled.
     */
    @Value("${groupdocs.annotation.tabNavigationEnabled}")
    protected Boolean tabNavigationEnabled;
    /**
     * The Minimum image width.
     */
    @Value("${groupdocs.annotation.minimumImageWidth}")
    protected Integer minimumImageWidth;
    /**
     * The Area tool options pen width.
     */
    @Value("${groupdocs.annotation.areaToolOptionsPenWidth}")
    protected Integer areaToolOptionsPenWidth;
    /**
     * The Area tool options pen color.
     */
    @Value("${groupdocs.annotation.areaToolOptionsPenColor}")
    protected String  areaToolOptionsPenColor;
    /**
     * The Area tool options pen dash style.
     */
    @Value("${groupdocs.annotation.areaToolOptionsPenDashStyle}")
    protected Integer areaToolOptionsPenDashStyle;
    /**
     * The Area tool options brush color.
     */
    @Value("${groupdocs.annotation.areaToolOptionsBrushColor}")
    protected String  areaToolOptionsBrushColor;
    /**
     * The Polyline tool options pen width.
     */
    @Value("${groupdocs.annotation.polylineToolOptionsPenWidth}")
    protected Integer polylineToolOptionsPenWidth;
    /**
     * The Polyline tool options pen color.
     */
    @Value("${groupdocs.annotation.polylineToolOptionsPenColor}")
    protected String  polylineToolOptionsPenColor;
    /**
     * The Polyline tool options pen dash style.
     */
    @Value("${groupdocs.annotation.polylineToolOptionsPenDashStyle}")
    protected Integer polylineToolOptionsPenDashStyle;
    /**
     * The Polyline tool options brush color.
     */
    @Value("${groupdocs.annotation.polylineToolOptionsBrushColor}")
    protected String  polylineToolOptionsBrushColor;
    /**
     * The Arrow tool options pen width.
     */
    @Value("${groupdocs.annotation.arrowToolOptionsPenWidth}")
    protected Integer arrowToolOptionsPenWidth;
    /**
     * The Arrow tool options pen color.
     */
    @Value("${groupdocs.annotation.arrowToolOptionsPenColor}")
    protected String  arrowToolOptionsPenColor;
    /**
     * The Arrow tool options pen dash style.
     */
    @Value("${groupdocs.annotation.arrowToolOptionsPenDashStyle}")
    protected Integer arrowToolOptionsPenDashStyle;
    /**
     * The Arrow tool options brush color.
     */
    @Value("${groupdocs.annotation.arrowToolOptionsBrushColor}")
    protected String  arrowToolOptionsBrushColor;
    @Value("${groupdocs.annotation.distanceToolOptionsPenColor}")
    private String    distanceToolOptionsPenColor;
    /**
     * The Pen color.
     */
    @Value("${groupdocs.annotation.penColor}")
    protected String  penColor;
    /**
     * The Pen width.
     */
    @Value("${groupdocs.annotation.penWidth}")
    protected Integer penWidth;
    /**
     * The Pen style.
     */
    @Value("${groupdocs.annotation.penStyle}")
    protected Integer penStyle;
    /**
     * The Enabled tools.
     */
    @Value("${groupdocs.annotation.enabledTools}")
    protected Integer enabledTools;
    /**
     * The Connector position.
     */
    @Value("${groupdocs.annotation.connectorPosition}")
    protected Integer connectorPosition;
    /**
     * The Save reply on focus loss.
     */
    @Value("${groupdocs.annotation.saveReplyOnFocusLoss}")
    protected Boolean saveReplyOnFocusLoss;
    /**
     * The Clickable annotations.
     */
    @Value("${groupdocs.annotation.clickableAnnotations}")
    protected Boolean clickableAnnotations;
    /**
     * The Disconnect uncommented.
     */
    @Value("${groupdocs.annotation.disconnectUncommented}")
    protected Boolean disconnectUncommented;
    /**
     * The Strikeout mode.
     */
    @Value("${groupdocs.annotation.strikeoutMode}")
    protected Integer strikeoutMode;
    /**
     * The Any tool selection.
     */
    @Value("${groupdocs.annotation.anyToolSelection}")
    protected Boolean anyToolSelection;
    /**
     * The Sidebar container selector.
     */
    @Value("${groupdocs.annotation.sidebarContainerSelector}")
    protected String  sidebarContainerSelector;
    /**
     * The Use page number in url hash.
     */
    @Value("${groupdocs.annotation.usePageNumberInUrlHash}")
    protected Boolean usePageNumberInUrlHash;
    /**
     * The Text selection synchronous calculation.
     */
    @Value("${groupdocs.annotation.textSelectionSynchronousCalculation}")
    protected Boolean textSelectionSynchronousCalculation;
    /**
     * The Variable height page support.
     */
    @Value("${groupdocs.annotation.variableHeightPageSupport}")
    protected Boolean variableHeightPageSupport;
    /**
     * The Create markup.
     */
    @Value("${groupdocs.annotation.createMarkup}")
    protected Boolean createMarkup;
    /**
     * The Use pdf.
     */
    @Value("${groupdocs.annotation.use_pdf}")
    protected Boolean usePdf;
    /**
     * The Mode.
     */
    @Value("${groupdocs.annotation.mode}")
    protected String  mode;
    /**
     * The Selection container selector.
     */
    @Value("${groupdocs.annotation.selectionContainerSelector}")
    protected String  selectionContainerSelector;
    /**
     * The Graphics container selector.
     */
    @Value("${groupdocs.annotation.graphicsContainerSelector}")
    protected String  graphicsContainerSelector;
    /**
     * The Use browser cache.
     */
    @Value("${groupdocs.annotation.useBrowserCache}")
    protected Boolean useBrowserCache;
    /**
     * The Widget id.
     */
    @Value("${groupdocs.annotation.widgetId}")
    protected String  widgetId;
    /**
     * The Right panel enabled.
     */
    @Value("${groupdocs.annotation.rightPanelEnabled}")
    protected Boolean rightPanelEnabled;
    /**
     * The Max cache size.
     */
    @Value("${groupdocs.annotation.maxCacheSize}")
    protected Long    maxCacheSize;
    /**
     * The Upload path.
     */
    @Value("${groupdocs.annotation.uploadPath}")
    protected String  uploadPath;
    /**
     * The Undo enabled.
     */
    @Value("${groupdocs.annotation.undoEnabled}")
    protected Boolean undoEnabled;
    /**
     * The Jquery file download cookie name.
     */
    @Value("${groupdocs.annotation.jqueryFileDownloadCookieName}")
    protected String  jqueryFileDownloadCookieName;
    /**
     * The Watermark font size.
     */
    @Value("${groupdocs.annotation.watermarkFontSize}")
    protected Integer watermarkFontSize;
    /**
     * The Watermark position.
     */
    @Value("${groupdocs.annotation.watermarkPosition}")
    protected String  watermarkPosition;
    /**
     * The Watermark text.
     */
    @Value("${groupdocs.annotation.watermarkText}")
    protected String  watermarkText;
    /**
     * The Convert word documents completely.
     */
    @Value("${groupdocs.annotation.convertWordDocumentsCompletely}")
    protected Boolean convertWordDocumentsCompletely;
    /**
     * The Ignore document absence.
     */
    @Value("${groupdocs.annotation.ignoreDocumentAbsence}")
    protected Boolean ignoreDocumentAbsence;
    /**
     * The Preload pages on browser side.
     */
    @Value("${groupdocs.annotation.preloadPagesOnBrowserSide}")
    protected Boolean preloadPagesOnBrowserSide;
    /**
     * The Print with watermark.
     */
    @Value("${groupdocs.annotation.printWithWatermark}")
    protected Boolean printWithWatermark;
    /**
     * The Support page rotation.
     */
    @Value("${groupdocs.annotation.supportPageRotation}")
    protected Boolean supportPageRotation;
    /**
     * The Store logic.
     */
    @Value("${groupdocs.annotation.storeLogic}")
    protected String  storeLogic;
    /**
     * The Print annotations.
     */
    @Value("${groupdocs.annotation.printAnnotations}")
    protected Boolean printAnnotations;
    /**
     * The Localization.
     */
    @Value("${groupdocs.annotation.localization}")
    protected String  localization;
    /**
     * The Disable atmosphere.
     */
    @Value("${groupdocs.annotation.disableAtmosphere}")
    protected Boolean disableAtmosphere;
    /**
     * The File display name.
     */
    @Value("${groupdocs.annotation.fileDisplayName}")
    protected String  fileDisplayName;
    /**
     * The Is case sensitive.
     */
    @Value("${groupdocs.annotation.isCaseSensitive}")
    protected Boolean caseSensitive;
    /**
     * The Search for separate words.
     */
    @Value("${groupdocs.annotation.searchForSeparateWords}")
    protected Boolean searchForSeparateWords;
    @Value("${groupdocs.annotation.defaultFileName}")
    private String    defaultFileName;

    @Value("${groupdocs.annotation.encoding}")
    private String    encoding;

    @Value("${groupdocs.annotation.fontsPath}")
    private String    fontsPath;

    @Value("${groupdocs.annotation.pdfPrintMarginsEnabled}")
    private Boolean   pdfPrintMarginsEnabled;

    @Value("${groupdocs.annotation.customDocumentCss}")
    private String    customDocumentCss;

    @Value("${groupdocs.annotation.useVirtualScrolling}")
    private Boolean   useVirtualScrolling;

    @Value("${groupdocs.annotation.showHiddenSlides}")
    private Boolean   showHiddenSlides;

    @Value("${groupdocs.annotation.imageResolution}")
    private Integer   imageResolution;

    @Value("${groupdocs.annotation.imageWebRequestTimeout}")
    private Integer   imageWebRequestTimeout;

    @Value("${groupdocs.annotation.typewriterImportEnabled}")
    private Boolean   typewriterImportEnabled;

    /**
     * Gets license path.
     *
     * @return the license path
     */
    @Override
    public String getLicensePath() {
        return licensePath;
    }

    /**
     * Sets license path.
     *
     * @param licensePath the license path
     */

    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }

    /**
     * Gets application path.
     *
     * @return the application path
     */
    @Override
    public String getApplicationPath() {
        return "null".equalsIgnoreCase(applicationPath) ? null : applicationPath;
    }

    /**
     * Sets application path.
     *
     * @param applicationPath the application path
     */

    public void setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
    }

    /**
     * Gets storage type.
     *
     * @return the storage type
     */
    public String getStorageType() {
        return storageType;
    }

    /**
     * Sets storage type.
     *
     * @param storageType the storage type
     */

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    /**
     * Gets db server.
     *
     * @return the db server
     */
    public String getDbServer() {
        return dbServer;
    }

    /**
     * Sets db server.
     *
     * @param dbServer the db server
     */

    public void setDbServer(String dbServer) {
        this.dbServer = dbServer;
    }

    /**
     * Gets db port.
     *
     * @return the db port
     */
    public Integer getDbPort() {
        return dbPort;
    }

    /**
     * Sets db port.
     *
     * @param dbPort the db port
     */

    public void setDbPort(Integer dbPort) {
        this.dbPort = dbPort;
    }

    /**
     * Gets db name.
     *
     * @return the db name
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * Sets db name.
     *
     * @param dbName the db name
     */

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * Gets db username.
     *
     * @return the db username
     */
    public String getDbUsername() {
        return dbUsername;
    }

    /**
     * Sets db username.
     *
     * @param dbUsername the db username
     */

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    /**
     * Gets db password.
     *
     * @return the db password
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * Sets db password.
     *
     * @param dbPassword the db password
     */

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
     * Gets storage path.
     *
     * @return the storage path
     */
    public String getStoragePath() {
        return "null".equals(storagePath) ? null : storagePath;
    }

    /**
     * Sets storage path.
     *
     * @param storagePath the storage path
     */

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * Is use authorization boolean.
     *
     * @return the boolean
     */

    public Boolean isUseAuthorization() {
        return useAuthorization;
    }

    /**
     * Sets use authorization.
     *
     * @param useAuthorization the use authorization
     */

    public void setUseAuthorization(Boolean useAuthorization) {
        this.useAuthorization = useAuthorization;
    }

    /**
     * Is use cache boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isUseCache() {
        return useCache;
    }

    /**
     * Gets encoding.
     *
     * @return the encoding
     */
    @Override
    public String getEncoding() {
        return encoding;
    }

    /**
     * Sets encoding.
     *
     * @param encoding the encoding
     */

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * Gets fonts path.
     *
     * @return the fonts path
     */
    @Override
    public String getFontsPath() {
        return fontsPath;
    }

    /**
     * Sets fonts path.
     *
     * @param fontsPath the fonts path
     */

    public void setFontsPath(String fontsPath) {
        this.fontsPath = fontsPath;
    }

    /**
     * Sets use cache.
     *
     * @param useCache the use cache
     */

    public void setUseCache(Boolean useCache) {
        this.useCache = useCache;
    }

    /**
     * Gets expiration date.
     *
     * @return the expiration date
     */
    @Override
    public Integer getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets expiration date.
     *
     * @param expirationDate the expiration date
     */

    public void setExpirationDate(Integer expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets encryption key.
     *
     * @return the encryption key
     */
    @Override
    public String getEncryptionKey() {
        return encryptionKey;
    }

    /**
     * Sets encryption key.
     *
     * @param encryptionKey the encryption key
     */

    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    /**
     * Gets locales path.
     *
     * @return the locales path
     */
    @Override
    public String getLocalesPath() {
        return localesPath;
    }

    /**
     * Sets locales path.
     *
     * @param localesPath the locales path
     */

    public void setLocalesPath(String localesPath) {
        this.localesPath = localesPath;
    }

    /**
     * Gets quality.
     *
     * @return the quality
     */
    @Override
    public Integer getQuality() {
        return quality;
    }

    /**
     * Sets quality.
     *
     * @param quality the quality
     */

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    /**
     * Is show thumbnails boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isShowThumbnails() {
        return showThumbnails;
    }

    /**
     * Sets show thumbnails.
     *
     * @param showThumbnails the show thumbnails
     */

    public void setShowThumbnails(Boolean showThumbnails) {
        this.showThumbnails = showThumbnails;
    }

    /**
     * Is open thumbnails boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isOpenThumbnails() {
        return openThumbnails;
    }

    /**
     * Sets open thumbnails.
     *
     * @param openThumbnails the open thumbnails
     */

    public void setOpenThumbnails(Boolean openThumbnails) {
        this.openThumbnails = openThumbnails;
    }

    /**
     * Gets initial zoom.
     *
     * @return the initial zoom
     */
    @Override
    public Integer getInitialZoom() {
        return initialZoom;
    }

    /**
     * Sets initial zoom.
     *
     * @param initialZoom the initial zoom
     */

    public void setInitialZoom(Integer initialZoom) {
        this.initialZoom = initialZoom;
    }

    /**
     * Is zoom to fit width boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isZoomToFitWidth() {
        return zoomToFitWidth;
    }

    /**
     * Sets zoom to fit width.
     *
     * @param zoomToFitWidth the zoom to fit width
     */

    public void setZoomToFitWidth(Boolean zoomToFitWidth) {
        this.zoomToFitWidth = zoomToFitWidth;
    }

    /**
     * Is zoom to fit height boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isZoomToFitHeight() {
        return zoomToFitHeight;
    }

    /**
     * Sets zoom to fit height.
     *
     * @param zoomToFitHeight the zoom to fit height
     */

    public void setZoomToFitHeight(Boolean zoomToFitHeight) {
        this.zoomToFitHeight = zoomToFitHeight;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    @Override
    public Integer getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */

    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    @Override
    public Integer getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */

    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Is show print boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isShowPrint() {
        return showPrint;
    }

    /**
     * Sets show print.
     *
     * @param showPrint the show print
     */

    public void setShowPrint(Boolean showPrint) {
        this.showPrint = showPrint;
    }

    /**
     * Is show zoom boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isShowZoom() {
        return showZoom;
    }

    /**
     * Sets show zoom.
     *
     * @param showZoom the show zoom
     */

    public void setShowZoom(Boolean showZoom) {
        this.showZoom = showZoom;
    }

    /**
     * Is show paging boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isShowPaging() {
        return showPaging;
    }

    /**
     * Sets show paging.
     *
     * @param showPaging the show paging
     */

    public void setShowPaging(Boolean showPaging) {
        this.showPaging = showPaging;
    }

    /**
     * Gets preload pages count.
     *
     * @return the preload pages count
     */
    @Override
    public Integer getPreloadPagesCount() {
        return preloadPagesCount;
    }

    /**
     * Sets preload pages count.
     *
     * @param preloadPagesCount the preload pages count
     */

    public void setPreloadPagesCount(Integer preloadPagesCount) {
        this.preloadPagesCount = preloadPagesCount;
    }

    /**
     * Is show header boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isShowHeader() {
        return showHeader;
    }

    /**
     * Sets show header.
     *
     * @param showHeader the show header
     */

    public void setShowHeader(Boolean showHeader) {
        this.showHeader = showHeader;
    }

    /**
     * Is show file explorer boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isShowFileExplorer() {
        return showFileExplorer;
    }

    /**
     * Sets show file explorer.
     *
     * @param showFileExplorer the show file explorer
     */

    public void setShowFileExplorer(Boolean showFileExplorer) {
        this.showFileExplorer = showFileExplorer;
    }

    /**
     * Is use em scaling boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isUseEmScaling() {
        return useEmScaling;
    }

    /**
     * Is pdf print margins enabled boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isPdfPrintMarginsEnabled() {
        return pdfPrintMarginsEnabled;
    }

    /**
     * Sets pdf print margins enabled.
     *
     * @param pdfPrintMarginsEnabled the pdf print margins enabled
     */

    public void setPdfPrintMarginsEnabled(Boolean pdfPrintMarginsEnabled) {
        this.pdfPrintMarginsEnabled = pdfPrintMarginsEnabled;
    }

    /**
     * Sets use em scaling.
     *
     * @param useEmScaling the use em scaling
     */

    public void setUseEmScaling(Boolean useEmScaling) {
        this.useEmScaling = useEmScaling;
    }

    /**
     * Is enable right click menu boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isEnableRightClickMenu() {
        return enableRightClickMenu;
    }

    /**
     * Sets enable right click menu.
     *
     * @param enableRightClickMenu the enable right click menu
     */

    public void setEnableRightClickMenu(Boolean enableRightClickMenu) {
        this.enableRightClickMenu = enableRightClickMenu;
    }

    /**
     * Is show toolbar boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isShowToolbar() {
        return showToolbar;
    }

    /**
     * Sets show toolbar.
     *
     * @param showToolbar the show toolbar
     */

    public void setShowToolbar(Boolean showToolbar) {
        this.showToolbar = showToolbar;
    }

    /**
     * Is enable side panel boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isEnableSidePanel() {
        return enableSidePanel;
    }

    /**
     * Sets enable side panel.
     *
     * @param enableSidePanel the enable side panel
     */

    public void setEnableSidePanel(Boolean enableSidePanel) {
        this.enableSidePanel = enableSidePanel;
    }

    /**
     * Is scroll on focus boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isScrollOnFocus() {
        return scrollOnFocus;
    }

    /**
     * Sets scroll on focus.
     *
     * @param scrollOnFocus the scroll on focus
     */

    public void setScrollOnFocus(Boolean scrollOnFocus) {
        this.scrollOnFocus = scrollOnFocus;
    }

    /**
     * Gets strike out color.
     *
     * @return the strike out color
     */
    @Override
    public String getStrikeOutColor() {
        return strikeOutColor;
    }

    /**
     * Sets strike out color.
     *
     * @param strikeOutColor the strike out color
     */

    public void setStrikeOutColor(String strikeOutColor) {
        this.strikeOutColor = strikeOutColor;
    }

    /**
     * Gets highlight color.
     *
     * @return the highlight color
     */
    @Override
    public String getHighlightColor() {
        return highlightColor;
    }

    /**
     * Sets highlight color.
     *
     * @param highlightColor the highlight color
     */

    public void setHighlightColor(String highlightColor) {
        this.highlightColor = highlightColor;
    }

    /**
     * Gets underline color.
     *
     * @return the underline color
     */
    @Override
    public String getUnderlineColor() {
        return underlineColor;
    }

    /**
     * Sets underline color.
     *
     * @param underlineColor the underline color
     */

    public void setUnderlineColor(String underlineColor) {
        this.underlineColor = underlineColor;
    }

    /**
     * Gets text field background color.
     *
     * @return the text field background color
     */
    @Override
    public String getTextFieldBackgroundColor() {
        return textFieldBackgroundColor;
    }

    /**
     * Sets text field background color.
     *
     * @param textFieldBackgroundColor the text field background color
     */

    public void setTextFieldBackgroundColor(String textFieldBackgroundColor) {
        this.textFieldBackgroundColor = textFieldBackgroundColor;
    }

    /**
     * Is tab navigation enabled boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isTabNavigationEnabled() {
        return tabNavigationEnabled;
    }

    /**
     * Sets tab navigation enabled.
     *
     * @param tabNavigationEnabled the tab navigation enabled
     */

    public void setTabNavigationEnabled(Boolean tabNavigationEnabled) {
        this.tabNavigationEnabled = tabNavigationEnabled;
    }

    /**
     * Gets minimum image width.
     *
     * @return the minimum image width
     */
    @Override
    public Integer getMinimumImageWidth() {
        return minimumImageWidth;
    }

    /**
     * Sets minimum image width.
     *
     * @param minimumImageWidth the minimum image width
     */

    public void setMinimumImageWidth(Integer minimumImageWidth) {
        this.minimumImageWidth = minimumImageWidth;
    }

    /**
     * Gets area tool options pen width.
     *
     * @return the area tool options pen width
     */
    @Override
    public Integer getAreaToolOptionsPenWidth() {
        return areaToolOptionsPenWidth;
    }

    /**
     * Sets area tool options pen width.
     *
     * @param areaToolOptionsPenWidth the area tool options pen width
     */

    public void setAreaToolOptionsPenWidth(Integer areaToolOptionsPenWidth) {
        this.areaToolOptionsPenWidth = areaToolOptionsPenWidth;
    }

    /**
     * Gets area tool options pen color.
     *
     * @return the area tool options pen color
     */
    @Override
    public String getAreaToolOptionsPenColor() {
        return areaToolOptionsPenColor;
    }

    /**
     * Sets area tool options pen color.
     *
     * @param areaToolOptionsPenColor the area tool options pen color
     */

    public void setAreaToolOptionsPenColor(String areaToolOptionsPenColor) {
        this.areaToolOptionsPenColor = areaToolOptionsPenColor;
    }

    /**
     * Gets area tool options pen dash style.
     *
     * @return the area tool options pen dash style
     */
    @Override
    public Integer getAreaToolOptionsPenDashStyle() {
        return areaToolOptionsPenDashStyle;
    }

    /**
     * Sets area tool options pen dash style.
     *
     * @param areaToolOptionsPenDashStyle the area tool options pen dash style
     */

    public void setAreaToolOptionsPenDashStyle(Integer areaToolOptionsPenDashStyle) {
        this.areaToolOptionsPenDashStyle = areaToolOptionsPenDashStyle;
    }

    /**
     * Gets area tool options brush color.
     *
     * @return the area tool options brush color
     */
    @Override
    public String getAreaToolOptionsBrushColor() {
        return areaToolOptionsBrushColor;
    }

    /**
     * Sets area tool options brush color.
     *
     * @param areaToolOptionsBrushColor the area tool options brush color
     */

    public void setAreaToolOptionsBrushColor(String areaToolOptionsBrushColor) {
        this.areaToolOptionsBrushColor = areaToolOptionsBrushColor;
    }

    /**
     * Gets polyline tool options pen width.
     *
     * @return the polyline tool options pen width
     */
    @Override
    public Integer getPolylineToolOptionsPenWidth() {
        return polylineToolOptionsPenWidth;
    }

    /**
     * Sets polyline tool options pen width.
     *
     * @param polylineToolOptionsPenWidth the polyline tool options pen width
     */

    public void setPolylineToolOptionsPenWidth(Integer polylineToolOptionsPenWidth) {
        this.polylineToolOptionsPenWidth = polylineToolOptionsPenWidth;
    }

    /**
     * Gets polyline tool options pen color.
     *
     * @return the polyline tool options pen color
     */
    @Override
    public String getPolylineToolOptionsPenColor() {
        return polylineToolOptionsPenColor;
    }

    /**
     * Sets polyline tool options pen color.
     *
     * @param polylineToolOptionsPenColor the polyline tool options pen color
     */

    public void setPolylineToolOptionsPenColor(String polylineToolOptionsPenColor) {
        this.polylineToolOptionsPenColor = polylineToolOptionsPenColor;
    }

    /**
     * Gets polyline tool options pen dash style.
     *
     * @return the polyline tool options pen dash style
     */
    @Override
    public Integer getPolylineToolOptionsPenDashStyle() {
        return polylineToolOptionsPenDashStyle;
    }

    /**
     * Sets polyline tool options pen dash style.
     *
     * @param polylineToolOptionsPenDashStyle the polyline tool options pen dash style
     */

    public void setPolylineToolOptionsPenDashStyle(Integer polylineToolOptionsPenDashStyle) {
        this.polylineToolOptionsPenDashStyle = polylineToolOptionsPenDashStyle;
    }

    /**
     * Gets polyline tool options brush color.
     *
     * @return the polyline tool options brush color
     */
    @Override
    public String getPolylineToolOptionsBrushColor() {
        return polylineToolOptionsBrushColor;
    }

    /**
     * Sets polyline tool options brush color.
     *
     * @param polylineToolOptionsBrushColor the polyline tool options brush color
     */

    public void setPolylineToolOptionsBrushColor(String polylineToolOptionsBrushColor) {
        this.polylineToolOptionsBrushColor = polylineToolOptionsBrushColor;
    }

    /**
     * Gets arrow tool options pen width.
     *
     * @return the arrow tool options pen width
     */
    @Override
    public Integer getArrowToolOptionsPenWidth() {
        return arrowToolOptionsPenWidth;
    }

    /**
     * Sets arrow tool options pen width.
     *
     * @param arrowToolOptionsPenWidth the arrow tool options pen width
     */

    public void setArrowToolOptionsPenWidth(Integer arrowToolOptionsPenWidth) {
        this.arrowToolOptionsPenWidth = arrowToolOptionsPenWidth;
    }

    /**
     * Gets arrow tool options pen color.
     *
     * @return the arrow tool options pen color
     */
    @Override
    public String getArrowToolOptionsPenColor() {
        return arrowToolOptionsPenColor;
    }

    /**
     * Sets arrow tool options pen color.
     *
     * @param arrowToolOptionsPenColor the arrow tool options pen color
     */

    public void setArrowToolOptionsPenColor(String arrowToolOptionsPenColor) {
        this.arrowToolOptionsPenColor = arrowToolOptionsPenColor;
    }

    /**
     * Gets arrow tool options pen dash style.
     *
     * @return the arrow tool options pen dash style
     */
    @Override
    public Integer getArrowToolOptionsPenDashStyle() {
        return arrowToolOptionsPenDashStyle;
    }

    /**
     * Sets arrow tool options pen dash style.
     *
     * @param arrowToolOptionsPenDashStyle the arrow tool options pen dash style
     */

    public void setArrowToolOptionsPenDashStyle(Integer arrowToolOptionsPenDashStyle) {
        this.arrowToolOptionsPenDashStyle = arrowToolOptionsPenDashStyle;
    }

    /**
     * Gets arrow tool options brush color.
     *
     * @return the arrow tool options brush color
     */
    @Override
    public String getArrowToolOptionsBrushColor() {
        return arrowToolOptionsBrushColor;
    }

    /**
     * Gets distance tool options pen color.
     *
     * @return the distance tool options pen color
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
     * Sets arrow tool options brush color.
     *
     * @param arrowToolOptionsBrushColor the arrow tool options brush color
     */

    public void setArrowToolOptionsBrushColor(String arrowToolOptionsBrushColor) {
        this.arrowToolOptionsBrushColor = arrowToolOptionsBrushColor;
    }

    /**
     * Gets pen color.
     *
     * @return the pen color
     */
    @Override
    public String getPenColor() {
        return penColor;
    }

    /**
     * Sets pen color.
     *
     * @param penColor the pen color
     */

    public void setPenColor(String penColor) {
        this.penColor = penColor;
    }

    /**
     * Gets pen width.
     *
     * @return the pen width
     */
    @Override
    public Integer getPenWidth() {
        return penWidth;
    }

    /**
     * Sets pen width.
     *
     * @param penWidth the pen width
     */

    public void setPenWidth(Integer penWidth) {
        this.penWidth = penWidth;
    }

    /**
     * Gets pen style.
     *
     * @return the pen style
     */
    @Override
    public Integer getPenStyle() {
        return penStyle;
    }

    /**
     * Sets pen style.
     *
     * @param penStyle the pen style
     */

    public void setPenStyle(Integer penStyle) {
        this.penStyle = penStyle;
    }

    /**
     * Gets enabled tools.
     *
     * @return the enabled tools
     */
    @Override
    public Integer getEnabledTools() {
        return enabledTools;
    }

    /**
     * Sets enabled tools.
     *
     * @param enabledTools the enabled tools
     */

    public void setEnabledTools(Integer enabledTools) {
        this.enabledTools = enabledTools;
    }

    /**
     * Gets connector position.
     *
     * @return the connector position
     */
    @Override
    public Integer getConnectorPosition() {
        return connectorPosition;
    }

    /**
     * Sets connector position.
     *
     * @param connectorPosition the connector position
     */

    public void setConnectorPosition(Integer connectorPosition) {
        this.connectorPosition = connectorPosition;
    }

    /**
     * Is save reply on focus loss boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isSaveReplyOnFocusLoss() {
        return saveReplyOnFocusLoss;
    }

    /**
     * Sets save reply on focus loss.
     *
     * @param saveReplyOnFocusLoss the save reply on focus loss
     */

    public void setSaveReplyOnFocusLoss(Boolean saveReplyOnFocusLoss) {
        this.saveReplyOnFocusLoss = saveReplyOnFocusLoss;
    }

    /**
     * Is clickable annotations boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isClickableAnnotations() {
        return clickableAnnotations;
    }

    /**
     * Sets clickable annotations.
     *
     * @param clickableAnnotations the clickable annotations
     */

    public void setClickableAnnotations(Boolean clickableAnnotations) {
        this.clickableAnnotations = clickableAnnotations;
    }

    /**
     * Is disconnect uncommented boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isDisconnectUncommented() {
        return disconnectUncommented;
    }

    /**
     * Sets disconnect uncommented.
     *
     * @param disconnectUncommented the disconnect uncommented
     */

    public void setDisconnectUncommented(Boolean disconnectUncommented) {
        this.disconnectUncommented = disconnectUncommented;
    }

    /**
     * Gets strikeout mode.
     *
     * @return the strikeout mode
     */
    @Override
    public Integer getStrikeoutMode() {
        return strikeoutMode;
    }

    /**
     * Sets strikeout mode.
     *
     * @param strikeoutMode the strikeout mode
     */

    public void setStrikeoutMode(Integer strikeoutMode) {
        this.strikeoutMode = strikeoutMode;
    }

    /**
     * Is any tool selection boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isAnyToolSelection() {
        return anyToolSelection;
    }

    /**
     * Sets any tool selection.
     *
     * @param anyToolSelection the any tool selection
     */

    public void setAnyToolSelection(Boolean anyToolSelection) {
        this.anyToolSelection = anyToolSelection;
    }

    /**
     * Gets sidebar container selector.
     *
     * @return the sidebar container selector
     */
    @Override
    public String getSidebarContainerSelector() {
        return sidebarContainerSelector;
    }

    /**
     * Sets sidebar container selector.
     *
     * @param sidebarContainerSelector the sidebar container selector
     */

    public void setSidebarContainerSelector(String sidebarContainerSelector) {
        this.sidebarContainerSelector = sidebarContainerSelector;
    }

    /**
     * Is use page number in url hash boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isUsePageNumberInUrlHash() {
        return usePageNumberInUrlHash;
    }

    /**
     * Sets use page number in url hash.
     *
     * @param usePageNumberInUrlHash the use page number in url hash
     */

    public void setUsePageNumberInUrlHash(Boolean usePageNumberInUrlHash) {
        this.usePageNumberInUrlHash = usePageNumberInUrlHash;
    }

    /**
     * Is text selection synchronous calculation boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isTextSelectionSynchronousCalculation() {
        return textSelectionSynchronousCalculation;
    }

    /**
     * Sets text selection synchronous calculation.
     *
     * @param textSelectionSynchronousCalculation the text selection synchronous calculation
     */

    public void setTextSelectionSynchronousCalculation(Boolean textSelectionSynchronousCalculation) {
        this.textSelectionSynchronousCalculation = textSelectionSynchronousCalculation;
    }

    /**
     * Is variable height page support boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isVariableHeightPageSupport() {
        return variableHeightPageSupport;
    }

    /**
     * Sets variable height page support.
     *
     * @param variableHeightPageSupport the variable height page support
     */

    public void setVariableHeightPageSupport(Boolean variableHeightPageSupport) {
        this.variableHeightPageSupport = variableHeightPageSupport;
    }

    /**
     * Is create markup boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isCreateMarkup() {
        return createMarkup;
    }

    /**
     * Sets create markup.
     *
     * @param createMarkup the create markup
     */

    public void setCreateMarkup(Boolean createMarkup) {
        this.createMarkup = createMarkup;
    }

    /**
     * Is use pdf boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isUse_pdf() {
        return usePdf;
    }

    /**
     * Sets use pdf.
     *
     * @param usePdf the use pdf
     */

    public void setUsePdf(Boolean usePdf) {
        this.usePdf = usePdf;
    }

    /**
     * Gets mode.
     *
     * @return the mode
     */
    @Override
    public String getMode() {
        return mode;
    }

    /**
     * Sets mode.
     *
     * @param mode the mode
     */

    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Gets selection container selector.
     *
     * @return the selection container selector
     */
    @Override
    public String getSelectionContainerSelector() {
        return selectionContainerSelector;
    }

    /**
     * Sets selection container selector.
     *
     * @param selectionContainerSelector the selection container selector
     */

    public void setSelectionContainerSelector(String selectionContainerSelector) {
        this.selectionContainerSelector = selectionContainerSelector;
    }

    /**
     * Gets graphics container selector.
     *
     * @return the graphics container selector
     */
    @Override
    public String getGraphicsContainerSelector() {
        return graphicsContainerSelector;
    }

    /**
     * Sets graphics container selector.
     *
     * @param graphicsContainerSelector the graphics container selector
     */

    public void setGraphicsContainerSelector(String graphicsContainerSelector) {
        this.graphicsContainerSelector = graphicsContainerSelector;
    }

    /**
     * Is use browser cache boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isUseBrowserCache() {
        return useBrowserCache;
    }

    /**
     * Sets use browser cache.
     *
     * @param useBrowserCache the use browser cache
     */

    public void setUseBrowserCache(Boolean useBrowserCache) {
        this.useBrowserCache = useBrowserCache;
    }

    /**
     * Gets widget id.
     *
     * @return the widget id
     */
    @Override
    public String getWidgetId() {
        return widgetId;
    }

    /**
     * Sets widget id.
     *
     * @param widgetId the widget id
     */

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    /**
     * Is right panel enabled boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isRightPanelEnabled() {
        return rightPanelEnabled;
    }

    /**
     * Sets right panel enabled.
     *
     * @param rightPanelEnabled the right panel enabled
     */

    public void setRightPanelEnabled(Boolean rightPanelEnabled) {
        this.rightPanelEnabled = rightPanelEnabled;
    }

    /**
     * Gets max cache size.
     *
     * @return the max cache size
     */
    @Override
    public Long getMaxCacheSize() {
        return maxCacheSize;
    }

    /**
     * Sets max cache size.
     *
     * @param maxCacheSize the max cache size
     */

    public void setMaxCacheSize(Long maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    /**
     * Gets upload path.
     *
     * @return the upload path
     */
    @Override
    public String getUploadPath() {
        return uploadPath;
    }

    /**
     * Sets upload path.
     *
     * @param uploadPath the upload path
     */

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    /**
     * Is undo enabled boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isUndoEnabled() {
        return undoEnabled;
    }

    /**
     * Sets undo enabled.
     *
     * @param undoEnabled the undo enabled
     */

    public void setUndoEnabled(Boolean undoEnabled) {
        this.undoEnabled = undoEnabled;
    }

    /**
     * Gets jquery file download cookie name.
     *
     * @return the jquery file download cookie name
     */
    @Override
    public String getJqueryFileDownloadCookieName() {
        return jqueryFileDownloadCookieName;
    }

    /**
     * Sets jquery file download cookie name.
     *
     * @param jqueryFileDownloadCookieName the jquery file download cookie name
     */

    public void setJqueryFileDownloadCookieName(String jqueryFileDownloadCookieName) {
        this.jqueryFileDownloadCookieName = jqueryFileDownloadCookieName;
    }

    /**
     * Gets watermark font size.
     *
     * @return the watermark font size
     */
    @Override
    public Integer getWatermarkFontSize() {
        return watermarkFontSize;
    }

    /**
     * Sets watermark font size.
     *
     * @param watermarkFontSize the watermark font size
     */

    public void setWatermarkFontSize(Integer watermarkFontSize) {
        this.watermarkFontSize = watermarkFontSize;
    }

    /**
     * Gets watermark position.
     *
     * @return the watermark position
     */
    @Override
    public String getWatermarkPosition() {
        return watermarkPosition;
    }

    /**
     * Sets watermark position.
     *
     * @param watermarkPosition the watermark position
     */

    public void setWatermarkPosition(String watermarkPosition) {
        this.watermarkPosition = watermarkPosition;
    }

    /**
     * Gets watermark text.
     *
     * @return the watermark text
     */
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
     * Is convert word documents completely boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isConvertWordDocumentsCompletely() {
        return convertWordDocumentsCompletely;
    }

    /**
     * Gets file display name.
     *
     * @return the file display name
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
     * Sets convert word documents completely.
     *
     * @param convertWordDocumentsCompletely the convert word documents completely
     */

    public void setConvertWordDocumentsCompletely(Boolean convertWordDocumentsCompletely) {
        this.convertWordDocumentsCompletely = convertWordDocumentsCompletely;
    }

    /**
     * Is ignore document absence boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isIgnoreDocumentAbsence() {
        return ignoreDocumentAbsence;
    }

    /**
     * Sets ignore document absence.
     *
     * @param ignoreDocumentAbsence the ignore document absence
     */

    public void setIgnoreDocumentAbsence(Boolean ignoreDocumentAbsence) {
        this.ignoreDocumentAbsence = ignoreDocumentAbsence;
    }

    /**
     * Is preload pages on browser side boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isPreloadPagesOnBrowserSide() {
        return preloadPagesOnBrowserSide;
    }

    /**
     * Sets preload pages on browser side.
     *
     * @param preloadPagesOnBrowserSide the preload pages on browser side
     */

    public void setPreloadPagesOnBrowserSide(Boolean preloadPagesOnBrowserSide) {
        this.preloadPagesOnBrowserSide = preloadPagesOnBrowserSide;
    }

    /**
     * Is print with watermark boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isPrintWithWatermark() {
        return printWithWatermark;
    }

    /**
     * Sets print with watermark.
     *
     * @param printWithWatermark the print with watermark
     */

    public void setPrintWithWatermark(Boolean printWithWatermark) {
        this.printWithWatermark = printWithWatermark;
    }

    /**
     * Is support page rotation boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isSupportPageRotation() {
        return supportPageRotation;
    }

    /**
     * Sets support page rotation.
     *
     * @param supportPageRotation the support page rotation
     */

    public void setSupportPageRotation(Boolean supportPageRotation) {
        this.supportPageRotation = supportPageRotation;
    }

    /**
     * Gets store logic.
     *
     * @return the store logic
     */
    public String getStoreLogic() {
        return storeLogic;
    }

    /**
     * Sets store logic.
     *
     * @param storeLogic the store logic
     */

    public void setStoreLogic(String storeLogic) {
        this.storeLogic = storeLogic;
    }

    /**
     * Is print annotations boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isPrintAnnotations() {
        return printAnnotations;
    }

    /**
     * Is typewriter import enabled boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isTypewriterImportEnabled() {
        return typewriterImportEnabled;
    }

    /**
     * Sets typewriter import enabled.
     *
     * @param typewriterImportEnabled the typewriter import enabled
     */

    public void setTypewriterImportEnabled(Boolean typewriterImportEnabled) {
        this.typewriterImportEnabled = typewriterImportEnabled;
    }

    /**
     * Sets print annotations.
     *
     * @param printAnnotations the print annotations
     */

    public void setPrintAnnotations(Boolean printAnnotations) {
        this.printAnnotations = printAnnotations;
    }

    /**
     * Is disable atmosphere boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isDisableAtmosphere() {
        return disableAtmosphere;
    }

    /**
     * Is case sensitive boolean.
     *
     * @return the boolean
     */
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

    /**
     * Is search for separate words boolean.
     *
     * @return the boolean
     */
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
     * Gets localization.
     *
     * @return the localization
     */
    @Override
    public String getLocalization() {
        return localization;
    }

    /**
     * Sets localization.
     *
     * @param localization the localization
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
        return defaultFileName == null || defaultFileName.isEmpty() || "null".equalsIgnoreCase(defaultFileName) ? null : defaultFileName;
    }

    /**
     * Sets default file name.
     *
     * @param defaultFileName the default file name
     */

    public void setDefaultFileName(String defaultFileName) {
        this.defaultFileName = defaultFileName;
    }

    /**
     * Gets custom document css.
     *
     * @return the custom document css
     */
    @Override
    public String getCustomDocumentCss() {
        return customDocumentCss;
    }

    /**
     * Sets custom document css.
     *
     * @param customDocumentCss the custom document css
     */

    public void setCustomDocumentCss(String customDocumentCss) {
        this.customDocumentCss = customDocumentCss;
    }

    /**
     * Is use virtual scrolling boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isUseVirtualScrolling() {
        return useVirtualScrolling == null ? false : useVirtualScrolling;
    }

    /**
     * Sets use virtual scrolling.
     *
     * @param useVirtualScrolling the use virtual scrolling
     */

    public void setUseVirtualScrolling(Boolean useVirtualScrolling) {
        this.useVirtualScrolling = useVirtualScrolling;
    }

    /**
     * Is show hidden slides boolean.
     *
     * @return the boolean
     */
    @Override
    public Boolean isShowHiddenSlides() {
        return showHiddenSlides;
    }

    /**
     * Sets show hidden slides.
     *
     * @param showHiddenSlides the show hidden slides
     */

    public void setShowHiddenSlides(Boolean showHiddenSlides) {
        this.showHiddenSlides = showHiddenSlides;
    }

    /**
     * Gets image resolution.
     *
     * @return the image resolution
     */
    @Override
    public Integer getImageResolution() {
        return imageResolution;
    }

    /**
     * Sets image resolution.
     *
     * @param imageResolution the image resolution
     */

    public void setImageResolution(Integer imageResolution) {
        this.imageResolution = imageResolution;
    }

    /**
     * Gets image web request timeout.
     *
     * @return the image web request timeout
     */
    @Override
    public Integer getImageWebRequestTimeout() {
        return imageWebRequestTimeout;
    }

    /**
     * Sets image web request timeout.
     *
     * @param imageWebRequestTimeout the image web request timeout
     */

    public void setImageWebRequestTimeout(Integer imageWebRequestTimeout) {
        this.imageWebRequestTimeout = imageWebRequestTimeout;
    }
}
