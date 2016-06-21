package br.com.dataeasy.visualizador.wicket.components.feedback;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

@SuppressWarnings("serial")
public abstract class FeedbackBasePanel extends FeedbackPanel {

    private FeedbackBasePanel(String id, IFeedbackMessageFilter filter) {
        super(id, filter);
        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
    }

    public FeedbackBasePanel(String id, final int level) {
        this(id, new IFeedbackMessageFilter() {
            @Override
            public boolean accept(FeedbackMessage message) {
                return message.getLevel() == level;
            }
        });
    }

    public FeedbackBasePanel(String id, MarkupContainer container, final int level) {
        this(id, new DataEasyContainerFeedbackMessageFilter(container, level));
    }

}
