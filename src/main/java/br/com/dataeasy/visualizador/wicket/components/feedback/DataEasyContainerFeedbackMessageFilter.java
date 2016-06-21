package br.com.dataeasy.visualizador.wicket.components.feedback;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;

/**
 * <b>Description:</b> Filtro para determinar em que componente são exibidas as mensagens ao usuário. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 *    Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author Pedro
 * @version Revision: $ Date: 13/06/2015
 */
@SuppressWarnings("serial")
public class DataEasyContainerFeedbackMessageFilter extends ContainerFeedbackMessageFilter {

    private int level;

    public DataEasyContainerFeedbackMessageFilter(MarkupContainer container, int level) {
        super(container);
        this.level = level;
    }

    @Override
    public boolean accept(FeedbackMessage message) {
        return super.accept(message) && message.getLevel() == level;
    }

}