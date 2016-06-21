package br.com.dataeasy.visualizador.servlets;

import static com.groupdocs.annotation.common.Utils.toJson;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupdocs.annotation.domain.response.StatusResult;
import com.groupdocs.annotation.exception.AnnotationException;

import br.com.dataeasy.visualizador.media.MediaType;

/**
 * <b>Description:</b>Superclasse para Servlets que estendem AnnotationServlet, não suportam doGet() e possuem tratamento passando um JSON de erro ao
 * response caso ocorra exceção.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 28/09/2015
 */
@SuppressWarnings("serial")
public abstract class AbstractDoPostComJSONNaExcecaoAnnotationServlet extends AbstractDoGetUnsupportedOperationAnnotationServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            doPostInternal(request, response);
        } catch (AnnotationException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, MESSAGE_HANDLER_THROWS, e.getMessage());
            writeOutput(MediaType.APPLICATION_JSON, response, toJson(new StatusResult(false, e.getMessage())));
        }
    }
}
