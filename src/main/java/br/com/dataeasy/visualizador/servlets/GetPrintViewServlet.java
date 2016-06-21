package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupdocs.annotation.exception.AnnotationException;

import br.com.dataeasy.visualizador.media.MediaType;

/**
 * @author Aleksey Permyakov on 10.06.14.
 */
@WebServlet(name = "GetPrintViewServlet", urlPatterns = { "/document-annotation/GetPrintViewHandler/*" })
public class GetPrintViewServlet extends AbstractDoPostComJSONNaExcecaoAnnotationServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doPostInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, AnnotationException {
        writeOutput(MediaType.APPLICATION_JSON, response, annotationHandler.getPrintViewHandler(request, response).toString());
    }
}
