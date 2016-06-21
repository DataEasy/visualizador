package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupdocs.annotation.exception.AnnotationException;

import br.com.dataeasy.visualizador.VisualizadorInfraException;

/**
 * @author imy
 */
@WebServlet(name = "GetFontServlet", urlPatterns = { "/fonts/*" })
public class GetFontServlet extends AnnotationServlet {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-type", "application/x-font-woff");
        
        String[] split = request.getRequestURI().split("/");
        if (split.length > 0) {
            try {
                writeOutput((InputStream) annotationHandler.getFontHandler(split[split.length - 1], request, response), response);
            } catch (AnnotationException e) {
                throw new VisualizadorInfraException("Problema ao carregar fonte.", e);
            }
        }
    }
}
