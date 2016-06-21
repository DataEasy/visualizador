package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupdocs.annotation.exception.AnnotationException;

/**
 * @author imy
 */
@WebServlet(name = "GetPrintDocumentPageImageServlet", urlPatterns = { "/document-annotation/GetPrintDocumentPageImageHandler/*" })
public class GetPrintDocumentPageImageServlet extends AnnotationServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
            String path = request.getParameter("path");
            Boolean printAnnotations = Boolean.valueOf(request.getParameter("printAnnotations"));
            Object o = annotationHandler.getPrintDocumentPageImageHandler(path, pageIndex, printAnnotations, response);
            if (o instanceof InputStream) {
                writeOutput((InputStream) o, response);
            }
        } catch (AnnotationException e) {
            tratarAnnotationExceptionJson(response, e);
        }
    }
}
