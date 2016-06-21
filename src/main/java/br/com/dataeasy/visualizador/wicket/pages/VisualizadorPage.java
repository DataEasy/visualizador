package br.com.dataeasy.visualizador.wicket.pages;

import static br.com.dataeasy.visualizador.wicket.pages.VisualizadorPageUtils.criarMapa;
import static br.com.dataeasy.visualizador.wicket.pages.VisualizadorPageUtils.getParametroPost;

import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.devutils.stateless.StatelessComponent;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.negocio.service.VisualizacaoDocumentoService;
import br.com.dataeasy.visualizador.wicket.HeadRenderer;
import br.com.dataeasy.visualizador.wicket.VisualizadorPanel;
import br.com.dataeasy.visualizador.wicket.application.WicketIds;
import br.com.dataeasy.visualizador.wicket.components.feedback.DataEasyFeedbackPanel;

/**
 * <b>Description:</b>Tela com exemplo de utilização do Visualizador de Documentos<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 15/06/2015
 */
@StatelessComponent
public class VisualizadorPage extends WebPage {

    public static final String           PARAM_CAMINHO    = "caminho";
    public static final String           PARAM_MIME_TYPE  = "mimeType";
    public static final String           PARAM_TOKEN      = "token";
    public static final String           PARAM_PARAMETROS = "parametros";
    private static final long            serialVersionUID = 1L;
    private Map<String, Object>          configuracoes;

    @SpringBean
    private VisualizacaoDocumentoService visualizacaoDocumentoService;

    public VisualizadorPage() {
        super(new Model<Binario>(new Binario(getParametroPost(PARAM_CAMINHO), getParametroPost(PARAM_MIME_TYPE), getParametroPost(PARAM_TOKEN))));
        this.configuracoes = criarMapa(getParametroPost(PARAM_PARAMETROS));
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);
        HeadRenderer renderer = new HeadRenderer(response);
        renderer.javascriptJQuery();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new DataEasyFeedbackPanel(WicketIds.FEEDBACK_PANEL));

        Binario binario = (Binario) getDefaultModelObject();
        Component visualizadorPanel = new WebMarkupContainer(WicketIds.VISUALIZADOR_PANEL);
        visualizadorPanel.setVisible(false);
        add(visualizadorPanel);

        visualizacaoDocumentoService.ajustarEValidarAbertura(binario);
        visualizadorPanel = new VisualizadorPanel(WicketIds.VISUALIZADOR_PANEL, binario, configuracoes);
        visualizadorPanel.setRenderBodyOnly(true);
        replace(visualizadorPanel);
    }
}
