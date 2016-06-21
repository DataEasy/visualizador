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
@SuppressWarnings("serial")
@WebServlet(name = "LoadFileBrowserTreeDataServlet", urlPatterns = { "/document-viewer/LoadFileBrowserTreeDataHandler" })
public class LoadFileBrowserTreeDataServlet extends AbstractDoPostComJSONNaExcecaoAnnotationServlet {

    @Override
    public void doPostInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AnnotationException {
        writeOutput(MediaType.APPLICATION_JSON, response, annotationHandler.loadFileBrowserTreeDataHandler(request, response));
    }
}
