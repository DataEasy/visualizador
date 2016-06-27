package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author imy
 */
@WebServlet(name = "GetDocumentPageImageServlet", urlPatterns = { "/GetDocumentPageImageHandler/*", "//GetDocumentPageImageHandler/*" })
public class GetDocumentPageImageServlet extends AnnotationServlet {
    private static final long   serialVersionUID = 1L;
    private static final Logger LOG              = LoggerFactory.getLogger(GetDocumentPageImageServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int width = Integer.parseInt(request.getParameter("width"));
        int quality = Integer.parseInt(request.getParameter("quality"));
        boolean usePdf = Boolean.parseBoolean(request.getParameter("usePdf"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        boolean isPrint = Boolean.parseBoolean(request.getParameter("isPrint"));

        String watermarkPosition = request.getParameter("watermarkPosition");
        Integer watermarkFontSize = Integer.valueOf(request.getParameter("watermarkFontSize"));
        Boolean useHtmlBasedEngine = Boolean.valueOf(request.getParameter("useHtmlBasedEngine"));
        Boolean rotate = Boolean.valueOf(request.getParameter("rotate"));

        String path = request.getParameter("path");
        Object o = null;
        try {
            o = annotationHandler.getDocumentPageImageHandler(path, width, quality, usePdf, pageIndex, isPrint, watermarkPosition, watermarkFontSize,
                    useHtmlBasedEngine, rotate, response);
        } catch (Exception e) {
            LOG.error("get document page image handler", e);
        }
        if (o instanceof InputStream) {
            writeOutput((InputStream) o, response);
        }
    }
}
