package br.com.dataeasy.visualizador.wicket.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * <b>Description:</b> Listener para complementar funcionalidades do wicket referente ao RequestCycle. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 12/06/2015
 */
public class DefaultRequestCycleListener extends AbstractRequestCycleListener {

    private static final Log LOG = LogFactory.getLog(DefaultRequestCycleListener.class);

    @Override
    public IRequestHandler onException(final RequestCycle cycle, final Exception ex) {
        IRequestHandler handler = OnExceptionHandler.handle(cycle, ex);
        if (handler == null) {
            LOG.error(ex.getMessage(), ex);
            handler = super.onException(cycle, ex);
        }
        return handler;
    }

}
