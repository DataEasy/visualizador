package br.com.dataeasy.visualizador.wicket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import br.com.dataeasy.visualizador.wicket.util.WicketUtil;

/**
 * <b>Description:</b> Superclasse de painéis baseados em algum pojo. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 22/06/2015
 */
@SuppressWarnings("serial")
public abstract class EntidadeBasePanel<T> extends Panel {

    public EntidadeBasePanel(String id, IModel<T> model) {
        super(id, model);
    }

    @SuppressWarnings("unchecked")
    public IModel<T> getModelo() {
        return (IModel<T>) getDefaultModel();
    }

    public T getObjetoDoModelo() {
        return getModelo().getObject();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        adicionarComponentes();
    }

    protected abstract void adicionarComponentes();

    public HttpServletRequest getHttpServletRequest() {
        return WicketUtil.getRequest();
    }

    public HttpServletResponse getHttpServletResponse() {
        return WicketUtil.getResponse();
    }

    protected <C extends Component> C getComponente(String... ids) {
        return WicketUtil.getComponente(this, ids);
    }

    protected void adicionar(AjaxRequestTarget target, String... wicketId) {
        target.add(getComponente(wicketId));
    }

}
