package br.com.dataeasy.visualizador.wicket.application;

import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.markup.head.PriorityFirstComparator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import br.com.dataeasy.visualizador.util.Constantes;
import br.com.dataeasy.visualizador.wicket.pages.VisualizadorPage;

/**
 * <b>Description:</b>Classe principal da aplicação web, que indica a página principal.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 08/06/2015
 */
public class VisualizadorApplication extends WebApplication implements ApplicationContextAware {

    private ApplicationContext context;
    public static final String CHAVE_TITULO_SISTEMA = "sistema.titulo";

    public VisualizadorApplication() {
        super();
        getRequestCycleListeners().add(new PageRequestHandlerTracker());
        getRequestCycleListeners().add(new DefaultRequestCycleListener());
        getRequestCycleListeners().add(new RuntimeExceptionTracker());
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        inicializarConfiguracoesSecundarias();
    }

    private void inicializarConfiguracoesSecundarias() {
        getRequestCycleSettings().setResponseRequestEncoding(Constantes.UTF_8);
        getMarkupSettings().setDefaultAfterDisabledLink(null);
        getMarkupSettings().setDefaultBeforeDisabledLink(null);
        getMarkupSettings().setStripWicketTags(true);
        getResourceSettings().setHeaderItemComparator(new PriorityFirstComparator(true));
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, context, true));
        getResourceSettings().getStringResourceLoaders().add(new VisualizadorMessagesResourceLoader());

        this.mountPage("abrir", VisualizadorPage.class);
    }

    /**
     * Obtém o título do sistema para apresentar no cabeçalho das páginas. Por default, pega o que estiver na chave "sistema.titulo". Nos aplicativos
     * sem internacionalização, pode ser mais simples sobrescrever o método.
     *
     * @param page a página que usará o título
     */
    public String getTituloSistema(WebPage page) {
        return page.getString(CHAVE_TITULO_SISTEMA);
    }

    /**
     * Sobrescrevendo criação de Session para definir Locale padrão.
     *
     * @param request o Request do Wicket
     * @param response o Response do Wicket
     * @return o Session do Wicket
     */
    @Override
    public Session newSession(Request request, Response response) {
        Session session = super.newSession(request, response);
        session.setLocale(Constantes.LOCALE_PADRAO);
        return session;
    }

    @Override
    protected IConverterLocator newConverterLocator() {
        return new ConverterLocator();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return VisualizadorPage.class;
    }

}
