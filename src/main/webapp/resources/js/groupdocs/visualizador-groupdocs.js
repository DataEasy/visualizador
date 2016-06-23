var ID_CONTAINER_VISUALIZADOR = 'container-visualizador';

$(function() {
    $.ajaxSetup({
        beforeSend : function(jqXHR, settings) {
            if (settings.url && !settings.url.startsWith(window.location.protocol)) {
                var novaUrl = settings.url.replace(action.split("//")[0], window.location.protocol);
                settings.url = novaUrl;
            }
        }
    });

    var info = window.infoVisualizador;
    if (info.mensagemErro) {
        exibirMessagemErro(info.mensagemErro);
    } else {
        visualizarDocumentoAnotacaoComId(info);
    }
})

/**
 * Exibir mensagem de erro.
 *
 * @param msg
 *            exibe mensagem de erro ao usuário.
 */
function exibirMessagemErro(msg) {
    $("#mensagemErro").show();
    $("#mensagemErro").html("<button type=\"button\" class=\"close\" onclick=\"$('#mensagemErro').hide()\">×</button>");
    $("#mensagemErro").append(msg);
}

/**
 * Remoção de botões que não estarão disponíveis para usuário.
 */
function removerBotoes() {
    $('.import_file_uploader').remove();
    $('a[id="btnExport"]').parent().remove();
    $('a[id="btnExportWithoutComments"]').parent().remove();
}

/**
 * Importação dinâmica de JS ou CSS como em http://www.javascriptkit.com/javatutors/loadjavascriptcss.shtml.
 *
 * @param nome
 *            nome do arquivo
 * @param tipo
 *            css ou js
 */
function carregarJsOuCss(nome, tipo) {
    if (tipo == "js") { // Javascript
        var fileref = document.createElement('script')
        fileref.setAttribute("type", "text/javascript")
        fileref.setAttribute("src", nome)
    } else if (tipo == "css") { // CSS
        var fileref = document.createElement("link")
        fileref.setAttribute("rel", "stylesheet")
        fileref.setAttribute("type", "text/css")
        fileref.setAttribute("href", nome)
    }

    if (typeof fileref != "undefined") {
        document.getElementsByTagName("head")[0].appendChild(fileref)
    }
}

/**
 * Carrega dinamicamente JS do Modernizr, dependência do GroupDocs.
 */
function configurarModernizrCssTransformers() {
    if (!window.Modernizr.csstransforms) {
        $.ajax({
            url : getBaseUrl() + '/resources/js/groupdocs/turn.html4.min.js',
            dataType : 'script',
            type : 'GET',
            async : false
        });
    }
}

function configurarApplicationPath() {
    var urlBase = window.location.protocol + "//" + window.location.host + getContextPath() + '/';

    $.ui.groupdocsViewer.prototype.applicationPath = urlBase;
    $.ui.groupdocsViewer.prototype.useHttpHandlers = true;

    window.baseUrl = urlBase;
    window.isCaseSensitive = false;
    window.searchForSeparateWords = true;
}

/**
 * Configuração do GroupDocs.
 */
function configurarContainer() {
    var container = window.Container || new JsInject.Container();
    container.Register('PathProvider', function (c) {
        return jSaaspose.utils;
    }, true);
    window.Container = container;
    window.groupdocsAnnotationFrontEndVersion = function (){
        var gVersion = '1.1.0';
        var gUpDate = '2014.11.19';
        return 'GroupDocs.Annotation front-end v' + gVersion + ' updated by ' + gUpDate;
    };
}

/**
 * Abre widget do visualizador GroupDocs com componente de Annotations. Atualmente só exibe documentos, sem permitir trabalhar com anotações.
 *
 * @param info
 *            informações sobre usuário e documento a visualizar.
 */
function visualizarDocumentoAnotacaoComId(info) {
    var annotationWidget = $('#' + ID_CONTAINER_VISUALIZADOR).groupdocsAnnotation({
        localizedStrings : info.localizedStrings,
        thumbsImageBase64Encoded : info.thumbsImageBase64Encoded || undefined,
        width : 0,
        height : 0,
        fileId : info.fileId,
        docViewerId : 'annotation-widget-doc-viewer',
        quality : 100,
        enableRightClickMenu : false,
        showHeader : true,
        showZoom : true,
        showPaging : true,
        showPrint : false,
        showFileExplorer : false,
        showThumbnails : true,
        showToolbar : info.showToolbar,
        openThumbnails : true,
        zoomToFitWidth : info.zoomToFitWidth,
        zoomToFitHeight : info.zoomToFitHeight,
        initialZoom : 100,
        preloadPagesCount : 0,
        enableSidePanel : info.enableSidePanel,
        scrollOnFocus : true,
        strikeOutColor : '#00000c',
        highlightColor : '#000017',
        underlineColor : '#FF0000',
        textFieldBackgroundColor : '#990000',
        enabledTools : 8191,
        connectorPosition : 0,
        saveReplyOnFocusLoss : false,
        clickableAnnotations : true,
        disconnectUncommented : false,
        enableStandardErrorHandling : true,
        strikeoutMode : 1,
        undoEnabled : true,
        anyToolSelection : true,
        tabNavigationEnabled : false,
        minimumImageWidth : 150,
        areaToolOptions : {
            pen : {
                width : 1,
                color : -65536,
                dashStyle : 0
            },
            brush : {
                color : -16711936
            }
        },
        polylineToolOptions : {
            pen : {
                width : 1,
                color : -65536,
                dashStyle : 0
            },
            brush : {
                color : -16711936
            }
        },
        arrowToolOptions : {
            pen : {
                width : 1,
                color : -65536,
                dashStyle : 0
            },
            brush : {
                color : -16711936
            }
        },
        distanceToolOptions : {
            pen : {
                color : -16776961
            }
        },
        sideboarContainerSelector : 'div.comments_sidebar_wrapper',
        usePageNumberInUrlHash : false,
        textSelectionSynchronousCalculation : true,
        variableHeightPageSupport : true,
        useJavaScriptDocumentDescription : true,
        isRightPanelEnabled : info.rightPanelEnabled,
        createMarkup : true,
        use_pdf : 'true',
        _mode : 'annotatedDocument',
        selectionContainerSelector : "[name='selection-content']",
        graphicsContainerSelector : '.annotationsContainer',
        userName : info.userName,
        userId : info.userId
    }).error(function(erro) {
        // TODO: tratar amigavelmente
        alert("erro annotation: " + erro);
    }).ready(function() {
        removerBotoes();
        atualizarLayout();

        var varTimeout;
        window.onresize = function() {
            clearTimeout(varTimeout);
            varTimeout = setTimeout(atualizarLayout, 250);
        };
    });
}

/**
 * Ajusta tamanho do visualizador conforme janela do usuário.
 */
function atualizarLayout() {
    $containerVisualizador = $('#' + ID_CONTAINER_VISUALIZADOR);
    var alturaAtual = $containerVisualizador.height();

    if (alturaAtual < 70) {
        var altura = $(window).height() - 220;
        if (altura < 200) {
            altura = 200;
        }
        $containerVisualizador.css('height', altura);
    }

}
