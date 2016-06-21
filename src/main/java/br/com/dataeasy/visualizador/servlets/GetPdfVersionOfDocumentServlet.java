package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupdocs.annotation.exception.AnnotationException;

/**
 * @author Aleksey Permyakov
 */
@SuppressWarnings("serial")
@edu.umd.cs.findbugs.annotations.SuppressWarnings("DM_DEFAULT_ENCODING")
@WebServlet(name = "GetPdfVersionOfDocumentServlet", urlPatterns = { "/document-annotation/GetPdfVersionOfDocumentHandler" })
public class GetPdfVersionOfDocumentServlet extends AbstractDoPostComJSONNaExcecaoAnnotationServlet {

    @Override
    protected void doPostInternal(HttpServletRequest request, HttpServletResponse response) throws AnnotationException, IOException {
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getOutputStream().write(annotationHandler.getPdfVersionOfDocumentHandler(request, response).toString().getBytes());
    }
}
