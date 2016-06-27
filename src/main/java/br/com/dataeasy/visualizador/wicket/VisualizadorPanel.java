package br.com.dataeasy.visualizador.wicket;

import java.util.Map;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.dataeasy.visualizador.config.InformacoesGroupDocs;
import br.com.dataeasy.visualizador.config.VisualizadorConfig;
import br.com.dataeasy.visualizador.negocio.excecoes.NegocioException;
import br.com.dataeasy.visualizador.negocio.modelo.Binario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.groupdocs.annotation.handler.AnnotationHandler;

/**
 * <b>Description:</b>Panel que exibe o Visualizador com binário ao carregar página.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 10/07/2015
 */
@SuppressWarnings("serial")
public class VisualizadorPanel extends EntidadeBasePanel<Binario> {

    @SpringBean
    private VisualizadorConfig  visualizadorConfig;
    private Map<String, Object> configuracoes;

    public VisualizadorPanel(String id, Binario binario, Map<String, Object> configuracoes) {
        super(id, new Model<Binario>(binario));
        this.configuracoes = configuracoes;
    }

    @Override
    protected void adicionarComponentes() {
        visualizadorConfig.configurar(getObjetoDoModelo());
    }

    private String getCaminhoCss(String nomeArquivo) {
        return "resources/css/groupdocs/" + nomeArquivo;
    }

    private String getCaminhoJs(String nomeArquivo) {
        return "resources/js/groupdocs/" + nomeArquivo;
    }

    @Override
    public void renderHead(IHeaderResponse headerResponse) {
        super.renderHead(headerResponse);

        HeadRenderer renderer = new HeadRenderer(headerResponse);
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("jquery-ui-1.10.3.min.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("knockout-3.2.0.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("turn.min.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("modernizr.2.6.2.Transform2d.min.js"));
        headerResponse.render(JavaScriptHeaderItem.forScript(getScriptInicialObjetoVisualizador(), "parte1"));
        renderer.javascriptHeaderItemForUrl("resources/js/startswith.js");
        renderer.javascriptHeaderItemForUrl("resources/js/util.js");
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("visualizador-groupdocs.js"));
        headerResponse.render(JavaScriptHeaderItem.forScript("configurarModernizrCssTransformers();", "parte2"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("installableViewer.min.js"));
        headerResponse.render(JavaScriptHeaderItem.forScript("configurarApplicationPath();", "parte3"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("GroupdocsViewer.all.min.js"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("bootstrap-groupdocs.min.css"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("GroupdocsViewer.all.min.css"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("jquery-ui-1.10.3.dialog.min.css"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("jquery.tinyscrollbar.min.js"));
        renderer.javascriptHeaderItemForUrl(getCaminhoJs("GroupdocsAnnotation.all.min.js"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("Annotation.min.css"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("Annotation.Toolbox.min.css"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("fixes.css"));
        renderer.cssHeaderItemForUrl(getCaminhoCss("visualizador.css"));
        adicionarAjustesCssInternetExplorer(headerResponse);
//        renderer.javascriptHeaderItemForUrl(getCaminhoJs("atmosphere-min.js"));
//        renderer.javascriptHeaderItemForUrl(getCaminhoJs("GroupDocsAtmosphere.min.js"));
        headerResponse.render(JavaScriptHeaderItem.forScript("configurarContainer();", "parte4"));
    }

    /**
     * Adiciona ajustes condicionais em CSS caso o navegador do usuário seja o IE.
     *
     * @param headerResponse
     */
    private void adicionarAjustesCssInternetExplorer(IHeaderResponse headerResponse) {
        String ieFix1 = "<!--[if IE]><style type=\"text/css\"> input[type=\"text\"].input_search { padding-right: 30px; width: 65px; } </style> <![endif]-->";
        String ieFix2 = "<!--[if IE 9]><style type=\"text/css\"> span.input_search_clear { left: 140px; } </style> <![endif]-->";
        headerResponse.render(StringHeaderItem.forString(ieFix1));
        headerResponse.render(StringHeaderItem.forString(ieFix2));
    }

    /**
     * Cria Javascript adicionando à página informações sobre imagem a ser renderizada e usuário.
     */
    private String getScriptInicialObjetoVisualizador() {
        // Por enquanto, usuário anônimo. FUTURE: posteriormente, configurar autorização de acesso Recupera ID do usuário.
        String nomeUsuario = AnnotationHandler.ANONYMOUS_USERNAME;

        InformacoesGroupDocs info;

        try {
            info = visualizadorConfig.getInformacoesParaVisualizacao(getObjetoDoModelo(), nomeUsuario);
        } catch (NegocioException e) {
            info = visualizadorConfig.getInformacoesBasicas();
            info.setUserName(nomeUsuario);

            String msg = e.getMensagem();
            msg = msg.replaceAll("\n", "<br>");
            info.setMensagemErro("'" + msg + "'");
        }

        configurar(info);

        Gson gson = new GsonBuilder().serializeNulls().create();
        return "window.infoVisualizador = " + gson.toJson(info) + ";";
    }

    /**
     * Inclui configurações adicionais para o Visualizador.
     *
     * @param info o InformacoesGroupDocs
     */
    protected void configurar(InformacoesGroupDocs info) {
        if (this.configuracoes != null) {
            info.aplicarConfiguracoes(configuracoes);
        }
    }
}
