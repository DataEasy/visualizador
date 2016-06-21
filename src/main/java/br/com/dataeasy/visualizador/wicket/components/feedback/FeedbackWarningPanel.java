package br.com.dataeasy.visualizador.wicket.components.feedback;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.feedback.FeedbackMessage;

/**
 * <b>Description:</b> Painel para exibição de mensagens de aviso. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author Pedro
 * @version Revision: $ Date: 18/08/2015
 */
@SuppressWarnings("serial")
public class FeedbackWarningPanel extends FeedbackBasePanel {

    public FeedbackWarningPanel(String id) {
        super(id, FeedbackMessage.WARNING);
    }

    public FeedbackWarningPanel(String id, MarkupContainer container) {
        super(id, container, FeedbackMessage.WARNING);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        setVisible(anyMessage(FeedbackMessage.WARNING));
    }

}