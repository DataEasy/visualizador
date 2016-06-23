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
@WebServlet(name = "GetFileServlet", urlPatterns = { "/document-viewer/GetFileHandler/*" })
public class GetFileServlet extends AnnotationServlet {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String path = request.getQueryString().split("=")[1];
        try {
            writeOutput((InputStream) annotationHandler.getFileHandler(path, false, response), response);
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException("Problema ao carregar arquivo.", e);
        }
    }
}
