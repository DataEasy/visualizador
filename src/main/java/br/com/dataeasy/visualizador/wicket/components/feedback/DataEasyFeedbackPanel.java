package br.com.dataeasy.visualizador.wicket.components.feedback;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.dataeasy.visualizador.wicket.application.WicketIds;

/**
 * <b>Description:</b> Painel para exibição de mensagens de sucesso, erro e informação. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 *    Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author Pedro
 * @version Revision: $ Date: 13/06/2015
 */
@SuppressWarnings("serial")
public class DataEasyFeedbackPanel extends Panel {

    private MarkupContainer container;

    public DataEasyFeedbackPanel(String id) {
        this(id, null);
    }

    public DataEasyFeedbackPanel(String id, MarkupContainer container) {
        super(id);
        setOutputMarkupId(true);
        this.container = container;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        FeedbackPanel feedbackErrorPanel = null;
        if (container == null) {
            feedbackErrorPanel = new FeedbackErrorPanel(WicketIds.FEEDBACK_ERROR);
            add(new FeedbackInfoPanel(WicketIds.FEEDBACK_INFO));
            add(new FeedbackSuccessPanel(WicketIds.FEEDBACK_SUCCESS));
            add(new FeedbackWarningPanel(WicketIds.FEEDBACK_WARNING));
        } else {
            feedbackErrorPanel = new FeedbackErrorPanel(WicketIds.FEEDBACK_ERROR, container);
            add(new FeedbackInfoPanel(WicketIds.FEEDBACK_INFO, container));
            add(new FeedbackSuccessPanel(WicketIds.FEEDBACK_SUCCESS, container));
            add(new FeedbackWarningPanel(WicketIds.FEEDBACK_WARNING, container));
        }
        add(feedbackErrorPanel);
    }

    public FeedbackPanel getFeedbackErrorPanel() {
        return (FeedbackPanel) get(WicketIds.FEEDBACK_ERROR);
    }

}