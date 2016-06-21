package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupdocs.annotation.exception.AnnotationException;

import br.com.dataeasy.visualizador.media.MediaType;

/**
 * @author imy
 */
@WebServlet(name = "GetImageUrlsServlet", urlPatterns = { "/document-viewer/GetImageUrlsHandler" })
public class GetImageUrlsServlet extends AbstractDoPostCreatedAnnotationServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doPostInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AnnotationException {
        writeOutput(MediaType.APPLICATION_JSON, response, annotationHandler.getImageUrlsHandler(request, response));
    }
}
