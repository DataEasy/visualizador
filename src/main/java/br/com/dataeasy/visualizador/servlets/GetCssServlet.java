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
@WebServlet(name = "GetCssServlet", urlPatterns = { "/GetCssHandler" })
public class GetCssServlet extends AnnotationServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "text/css");

        try {
            writeOutput((InputStream) annotationHandler.getCssHandler(request.getParameter("script"), request, response), response);
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException("Problema ao carregar CSS.", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }
}
