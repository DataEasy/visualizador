package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dataeasy.visualizador.VisualizadorInfraException;

import com.groupdocs.annotation.exception.AnnotationException;

/**
 * @author imy
 */
@WebServlet(name = "GetJsServlet", urlPatterns = { "/GetJsHandler" })
public class GetJsServlet extends AnnotationServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/javascript");

        try {
            writeOutput((InputStream) annotationHandler.getJsHandler(request.getParameter("script"), request, response), response);
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException("Problema ao carregar arquivo Javascript.", e);
        }
    }
}
