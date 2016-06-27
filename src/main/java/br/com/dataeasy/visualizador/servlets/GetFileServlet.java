package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dataeasy.visualizador.VisualizadorInfraException;

import com.groupdocs.annotation.exception.AnnotationException;

/**
 * @author imy
 */
@WebServlet(name = "GetFileServlet", urlPatterns = { "/GetFileHandler/*" })
public class GetFileServlet extends AnnotationServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getQueryString().split("=")[1];
        try {
            writeOutput(annotationHandler.getFileHandler(path, false, response), response);
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException("Problema ao carregar arquivo.", e);
        }
    }
}
