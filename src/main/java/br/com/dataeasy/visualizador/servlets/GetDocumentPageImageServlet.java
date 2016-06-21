package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupdocs.annotation.common.Utils;

/**
 * @author imy
 */
@WebServlet(name = "GetDocumentPageImageServlet", urlPatterns = { "/document-viewer/GetDocumentPageImageHandler/*" })
public class GetDocumentPageImageServlet extends AnnotationServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int width = Integer.parseInt(request.getParameter("width"));
        int quality = Integer.parseInt(request.getParameter("quality"));
        boolean usePdf = Boolean.parseBoolean(request.getParameter("usePdf"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        boolean isPrint = Boolean.parseBoolean(request.getParameter("isPrint"));
        String path = request.getParameter("path");
        Object o = null;
        try {
            o = annotationHandler.getDocumentPageImageHandler(path, width, quality, usePdf, pageIndex, isPrint, response);
        } catch (Exception e) {
            Utils.err(AnnotationServlet.class, e);
        }
        if (o instanceof InputStream) {
            writeOutput((InputStream) o, response);
        }
    }
}
