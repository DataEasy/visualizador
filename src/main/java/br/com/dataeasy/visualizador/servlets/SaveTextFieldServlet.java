package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dataeasy.visualizador.media.MediaType;

import com.groupdocs.annotation.exception.AnnotationException;

/**
 * @author imy
 */
@SuppressWarnings("serial")
@WebServlet(name = "SaveTextFieldServlet", urlPatterns = { "/document-annotation/SaveTextFieldHandler", "/SaveTextFieldHandler" })
public class SaveTextFieldServlet extends AbstractDoPostComJSONNaExcecaoAnnotationServlet {

    @Override
    public void doPostInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AnnotationException {
        writeOutput(MediaType.APPLICATION_JSON, response, annotationHandler.saveTextFieldHandler(request, response));
    }
}
