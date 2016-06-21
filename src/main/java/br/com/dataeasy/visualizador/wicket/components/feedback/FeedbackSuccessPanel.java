package br.com.dataeasy.visualizador.wicket.components.feedback;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.feedback.FeedbackMessage;

/**
 * <b>Description:</b> Painel para exibição de mensagens de sucesso. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 *    Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author Pedro
 * @version Revision: $ Date: 13/06/2015
 */
@SuppressWarnings("serial")
public class FeedbackSuccessPanel extends FeedbackBasePanel {

    public FeedbackSuccessPanel(String id) {
        super(id, FeedbackMessage.SUCCESS);
    }

    public FeedbackSuccessPanel(String id, MarkupContainer container) {
        super(id, container, FeedbackMessage.SUCCESS);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        setVisible(anyMessage(FeedbackMessage.SUCCESS));
    }

}