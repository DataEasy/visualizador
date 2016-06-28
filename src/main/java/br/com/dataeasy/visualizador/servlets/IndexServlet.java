package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

import br.com.dataeasy.visualizador.config.ApplicationConfig;
import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.negocio.service.VisualizacaoDocumentoService;

import com.groupdocs.annotation.exception.AnnotationException;
import com.groupdocs.annotation.handler.AnnotationHandler;

/**
 * <b>Description:</b>Servlet que atende à requisição inicial de visualização de documentos<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 02/06/2016
 */
@WebServlet(name = "IndexServlet", urlPatterns = { "/abrirServlet" })
public class IndexServlet extends AnnotationServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Binario binario = new Binario(request.getParameter("caminho"), request.getParameter("mimeType"));
        binario.setToken(request.getParameter("token"));

        VisualizacaoDocumentoService service = ContextLoader.getCurrentWebApplicationContext().getBean(VisualizacaoDocumentoService.class);
        service.ajustarEValidarAbertura(binario);

        ApplicationConfig applicationConfig = visualizadorConfig.getApplicationConfig();
        String idArquivo = visualizadorConfig.getIdArquivo(binario);

        // Configure localization
        String header = "";

        try {
            header = annotationHandler.getHeader(applicationConfig.getApplicationPath(), request);
        } catch (AnnotationException e) {
            Logger.getLogger(this.getClass()).error(e);
        }
        request.setAttribute("annotation_head", header);
        final String userName = AnnotationHandler.ANONYMOUS_USERNAME;

        String userGuid = null;
        try {
            userGuid = annotationHandler.getUserGuid(AnnotationHandler.ANONYMOUS_USERNAME);
            request.setAttribute("annotation_scripts", annotationHandler.getAnnotationScript(idArquivo, userName, userGuid));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/index.jsp");
            requestDispatcher.forward(request, response);
        } catch (AnnotationException e) {
            Logger.getLogger(this.getClass()).error(e);
        }
    }

}
