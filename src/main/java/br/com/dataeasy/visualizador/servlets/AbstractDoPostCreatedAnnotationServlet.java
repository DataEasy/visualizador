package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupdocs.annotation.common.Utils;
import com.groupdocs.annotation.exception.AnnotationException;

/**
 * <b>Description:</b>Superclasse para Servlets que estendem AnnotationServlet, não suportam doGet() e possuem tratamento definindo status code 201
 * para a requisição caso ocorra exceção.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 28/09/2015
 */
@SuppressWarnings("serial")
public abstract class AbstractDoPostCreatedAnnotationServlet extends AbstractDoGetUnsupportedOperationAnnotationServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            doPostInternal(request, response);
        } catch (AnnotationException e) {
            Utils.err(AnnotationServlet.class, e);

        }
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
