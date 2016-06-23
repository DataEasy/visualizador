package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.groupdocs.annotation.exception.AnnotationException;

import br.com.dataeasy.visualizador.VisualizadorInfraException;
import br.com.dataeasy.visualizador.media.MediaType;

/**
 * @author Aleksey Permyakov (18.11.2014)
 */
@WebServlet(name = "RotatePageServlet", urlPatterns = { "/document-viewer/RotatePageHandler/*" })
public class RotatePageServlet extends AnnotationServlet {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOG              = Logger.getLogger(RotatePageServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            writeOutput(MediaType.APPLICATION_JSON, response, annotationHandler.rotatePageHandler(request, response));
        } catch (AnnotationException e) {
            String msg = "Problema ao rotacionar imagem.";
            LOG.error(msg, e);
            throw new VisualizadorInfraException(msg, e);
        }
    }
}
