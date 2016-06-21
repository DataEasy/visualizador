package br.com.dataeasy.visualizador.wicket.application;

import org.apache.wicket.Session;
import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.lang.Exceptions;

import br.com.dataeasy.visualizador.negocio.excecoes.NegocioException;

/**
 * <b>Description:</b> Classe que contém a lógica de processamento de exceções de negócio a serem tratadas pela aplicação na camada web. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 12/06/2015
 */
public class OnExceptionHandler {

    public static IRequestHandler handle(RequestCycle cycle, Exception ex) {
        IPageRequestHandler handler = null;
        NegocioException negocioException = Exceptions.findCause(ex, NegocioException.class);
        if (negocioException != null) {
            handler = PageRequestHandlerTracker.getLastHandler(cycle);
            for (String mensagem : negocioException.getMensagens()) {
                ((WebPage) handler.getPage()).error(mensagem);
            }
        }
        return handler;
    }

    public static void error(Throwable throwable) {
        if (throwable instanceof NegocioException) {
            error((NegocioException) throwable);
        } else {
            Session.get().error(throwable.getLocalizedMessage());
        }
    }

    public static void error(NegocioException ex) {
        for (String mensagem : ex.getMensagens()) {
            Session.get().error(mensagem);
        }
    }

    public static void warn(Throwable throwable) {
        if (throwable instanceof NegocioException) {
            warn((NegocioException) throwable);
        } else {
            Session.get().warn(throwable.getLocalizedMessage());
        }
    }

    public static void warn(NegocioException ex) {
        for (String mensagem : ex.getMensagens()) {
            Session.get().warn(mensagem);
        }
    }

}
