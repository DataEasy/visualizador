package br.com.dataeasy.visualizador.wicket;

import org.apache.wicket.Application;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * <b>Description:</b> Classe auxiliar para renderizar os elementos básicos das páginas html. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 08/06/2015
 */
public class HeadRenderer {

    private IHeaderResponse response;

    public HeadRenderer(final IHeaderResponse response) {
        this.response = response;
    }

    public HeadRenderer javascriptOnDomReadyHeaderItemForScript(String script) {
        response.render(OnDomReadyHeaderItem.forScript(script));
        return this;
    }

    public HeadRenderer javascriptHeaderItemForUrl(String url) {
        response.render(JavaScriptHeaderItem.forUrl(url));
        return this;
    }

    public HeadRenderer javascriptHeaderItemForReference(Class<?> classe, String nome) {
        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(classe, nome)));
        return this;
    }

    public HeadRenderer cssHeaderItemForUrl(String url) {
        response.render(CssHeaderItem.forUrl(url));
        return this;
    }

    public HeadRenderer javascriptJQuery() {
        response.render(JavaScriptHeaderItem.forReference(Application.get().getJavaScriptLibrarySettings()
                .getJQueryReference()));
        return this;
    }

}