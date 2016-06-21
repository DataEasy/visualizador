package br.com.dataeasy.visualizador.util;

import java.util.Locale;
import java.util.TimeZone;

public final class Constantes {

    public static final Locale   LOCALE_PADRAO    = new Locale("pt", "BR");
    public static final String   UTF_8            = "UTF-8";
    public static final TimeZone TIMEZONE_PADRAO  = TimeZone.getTimeZone("America/Sao_Paulo");

    // se modificar, mudar também no VisualizadorPanel.html
    public static final String   DIV_VISUALIZADOR = "container-visualizador";
    public static final String   URL_ERRO_404     = "erro404";
    public static final String   MIME_TYPE_PDF    = "application/pdf";
    public static final String   MIME_TYPE_TIFF   = "image/tiff";

}
