package br.com.dataeasy.visualizador.wicket;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.powermock.api.easymock.PowerMock;

import br.com.dataeasy.visualizador.negocio.mensagens.Messages;
import br.com.dataeasy.visualizador.spring.context.SpringAppContext;

public abstract class WicketTestBase {

    protected DummyWebApplication    application = new DummyWebApplication();
    protected ApplicationContextMock appctxMock  = new ApplicationContextMock();
    protected WicketTester           tester;

    @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
    public WicketTestBase() {
        super();
        application.setApplicationContext(appctxMock);
        criarBeansMocksParaInfraestrutura();
        SpringComponentInjector springComponentInjector = new SpringComponentInjectorCustom(application, appctxMock, false);
        application.getComponentInstantiationListeners().add(springComponentInjector);
    }

    protected void inicializarWicketTester() {
        tester = new WicketTester(application);
        inicializarSpringAppContext();
    }

    protected void inicializarSpringAppContext() {
        SpringAppContext utils = new SpringAppContext();
        appctxMock.putBean("SpringAppContext", utils);
        utils.setApplicationContext(appctxMock);
    }

    protected void criarBeansMocksParaInfraestrutura() {
        //
    }

    @After
    public void limpar() {
        //
    }

    protected <P> P setBeanMock(Class<P> clazz) {
        String atributo = StringUtils.uncapitalize(clazz.getSimpleName());
        return setBeanMock(atributo, clazz);
    }

    protected <P> P setBean(P objeto) {
        String atributo = StringUtils.uncapitalize(objeto.getClass().getSimpleName());
        return setBeanMock(atributo, objeto);
    }

    protected <P> P setBeanMock(String atributo, Class<P> clazz) {
        P mock = PowerMock.createMock(clazz);
        return setBeanMock(atributo, mock);
    }

    @SuppressWarnings("unchecked")
    protected <P> P setBeanMock(String atributo, P mock) {
        if (appctxMock.containsBean(atributo)) {
            return (P) appctxMock.getBean(atributo);
        }
        appctxMock.putBean(atributo, mock);
        return mock;
    }

    protected String getUsuario() {
        return "00694501107";
    }

    public void setAppctxMock(ApplicationContextMock appctxMock) {
        this.appctxMock = appctxMock;
    }

    protected void executeAjaxFormComponentUpdatingBehavior(Component component) {
        for (final AjaxFormComponentUpdatingBehavior behavior : component.getBehaviors(AjaxFormComponentUpdatingBehavior.class)) {
            tester.executeBehavior(behavior);
        }
    }

    protected void assertValor(String componentPath, Object expectedValue, boolean enabled) {
        tester.assertModelValue(componentPath, expectedValue);
        if (enabled) {
            tester.assertEnabled(componentPath);
        } else {
            tester.assertDisabled(componentPath);
        }
    }

    /**
     * Retorna a mensagem a partir do properties. Os parâmetros podem ser chaves de labels, ou o próprio valor dos labels.
     *
     * @param chaveTemplate
     * @param params
     * @return String
     */
    protected String getMensagemFormatada(String chaveTemplate, String... params) {
        String[] parametros = new String[params.length];
        for (int i = 0; i < params.length; i++) {
            String label = params[i];
            if (label.indexOf(".") > 0) {
                Object[] parametros1 = {};
                parametros[i] = Messages.get(label, parametros1);
            } else {
                parametros[i] = label;
            }
        }
        return Messages.get(chaveTemplate, (Object[]) parametros);
    }

}
