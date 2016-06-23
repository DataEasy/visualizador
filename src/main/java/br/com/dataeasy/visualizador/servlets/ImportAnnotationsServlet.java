package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dataeasy.visualizador.VisualizadorInfraException;
import br.com.dataeasy.visualizador.media.MediaType;

import com.groupdocs.annotation.exception.AnnotationException;

/**
 * @author Aleksey Permyakov (16.07.14).
 */
@WebServlet(name = "ImportAnnotationsServlet", urlPatterns = { "/ImportAnnotationsHandler/*" })
public class ImportAnnotationsServlet extends AnnotationServlet {
    private static final long serialVersionUID = 1L;

    /**
     * POST request
     *
     * @param request object
     * @param response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            writeOutput(MediaType.APPLICATION_JSON, response, annotationHandler.importAnnotationsHandler(request, response));
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException("Problema ao importar anotações.", e);
        }
    }

    /**
     * GET request
     *
     * @param request object
     * @param response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }
}
