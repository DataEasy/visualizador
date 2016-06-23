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
@WebServlet(name = "GetImageServlet", urlPatterns = { "/images/*", "/resources/css/groupdocs/images/*" })
public class GetImageServlet extends AnnotationServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "image/png");

        String contextPath = request.getPathInfo();
        String[] path = contextPath.split("/");
        try {
            writeOutput((InputStream) annotationHandler.getImageHandler(path[path.length - 1], request, response), response);
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException("Problema ao carregar imagem.", e);
        }
    }
}
