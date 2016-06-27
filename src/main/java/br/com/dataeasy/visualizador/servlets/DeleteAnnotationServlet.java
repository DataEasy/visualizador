package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dataeasy.visualizador.util.Constantes;

import com.groupdocs.annotation.exception.AnnotationException;

import edu.umd.cs.findbugs.annotations.SuppressWarnings;

/**
 * @author imy
 */
@WebServlet(name = "DeleteAnnotationServlet", urlPatterns = { "/DeleteAnnotationHandler" })
@SuppressWarnings("DM_DEFAULT_ENCODING")
public class DeleteAnnotationServlet extends AbstractDoPostComJSONNaExcecaoAnnotationServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPostInternal(HttpServletRequest request, HttpServletResponse response) throws ServletException, AnnotationException, IOException {
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getOutputStream().write(annotationHandler.deleteAnnotationHandler(request, response).toString().getBytes(Constantes.UTF_8));
    }
}
