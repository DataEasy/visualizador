package br.com.dataeasy.visualizador.wicket.components.feedback;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.feedback.FeedbackMessage;

/**
 * <b>Description:</b> Painel para exibição de mensagens de erro. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 *    Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author Pedro
 * @version Revision: $ Date: 13/06/2015
 */
@SuppressWarnings("serial")
public class FeedbackErrorPanel extends FeedbackBasePanel {

    public FeedbackErrorPanel(String id) {
        super(id, FeedbackMessage.ERROR);
    }

    public FeedbackErrorPanel(String id, MarkupContainer container) {
        super(id, container, FeedbackMessage.ERROR);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        setVisible(anyErrorMessage());
    }

}