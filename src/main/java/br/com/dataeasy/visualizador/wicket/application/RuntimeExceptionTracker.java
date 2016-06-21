package br.com.dataeasy.visualizador.wicket.application;

import org.apache.wicket.MetaDataKey;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * <b>Description:</b> Realiza o rastreio das runtime exceptions lançadas pela aplicação <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 08/06/2015
 */
@SuppressWarnings("serial")
public class RuntimeExceptionTracker extends AbstractRequestCycleListener {

    public static final MetaDataKey<IRequestHandler>  EXCEPTION_REQUEST_HANDLER = new MetaDataKey<IRequestHandler>() {
                                                                                };

    public static final MetaDataKey<RuntimeException> EXCEPTION                 = new MetaDataKey<RuntimeException>() {
                                                                                };

    @Override
    public void onExceptionRequestHandlerResolved(RequestCycle cycle, IRequestHandler handler, Exception exception) {
        cycle.setMetaData(EXCEPTION_REQUEST_HANDLER, handler);
    }

    @Override
    public IRequestHandler onException(RequestCycle cycle, Exception exception) {
        if (exception instanceof RuntimeException) {
            cycle.setMetaData(EXCEPTION, (RuntimeException) exception);
        }
        return cycle.getMetaData(EXCEPTION_REQUEST_HANDLER);
    }

    public static RuntimeException getException(RequestCycle cycle) {
        return cycle.getMetaData(EXCEPTION);
    }

}
