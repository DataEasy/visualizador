package br.com.dataeasy.visualizador.wicket;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTesterHelper;
import org.junit.Assert;
import org.powermock.api.easymock.PowerMock;
import org.powermock.reflect.Whitebox;

import br.com.dataeasy.visualizador.util.ReflexaoUtil;
import br.com.dataeasy.visualizador.wicket.application.WicketIds;

public abstract class WicketTestPageBase<T extends WebPage> extends WicketTestComponentBase<T> {

    protected FormTester    formTester;
    protected T             instanciaPaginaTestada;
    protected LocalDate     hoje  = LocalDate.now();
    protected LocalDateTime agora = LocalDateTime.now();

    protected T getInstanciaPaginaTestada() {
        if (this.instanciaPaginaTestada == null) {
            this.instanciaPaginaTestada = instanciarPaginaTestada();
        }
        return this.instanciaPaginaTestada;
    }

    @SuppressWarnings("unchecked")
    protected T instanciarPaginaTestada() {
        return (T) ReflexaoUtil.instanciarParametro(getClass());
    }

    protected <P extends Page> P getInstanciaPagina(Class<P> classePagina) {
        return ReflexaoUtil.instanciarObjetoPorReflexao(classePagina);
    }

    protected Class<T> getClassePaginaTestada() {
        return ReflexaoUtil.getTipoParametrizado(getClass());
    }

    protected void setInstanciaPaginaTestada(T instanciaPaginaTestada) {
        this.instanciaPaginaTestada = instanciaPaginaTestada;
    }

    protected void criarMocksParaTesteQueRenderizaPagina() {
        // Deve ser sobrescrito.
    }

    @Override
    protected void inicializarTeste() {
        criarBeansMocksParaPagina();
        criarMassaDadosParaTesteQueRenderizaPagina();
    }

    public void criarBeansMocksParaPagina() {
        //
    };

    public void criarMassaDadosParaTesteQueRenderizaPagina() {
        // Deve ser sobrescrito.
    }

    protected void iniciarPagina() {
        iniciarPagina(getInstanciaPaginaTestada());
    }

    protected void iniciarPagina(Page pagina) {
        tester.startPage(pagina);
    }

    protected FormTester novoFormTester() {
        return tester.newFormTester(WicketIds.FORM);
    }

    public void testQueRenderizaPagina() {
        criarMocksParaTesteQueRenderizaPagina();
        PowerMock.replayAll();
        iniciarPagina();
        tester.assertRenderedPage(getClassePaginaTestada());
        PowerMock.verifyAll();
        assertPaginaRenderizada();
    }

    /**
     * Deve ser utilizado para assertiva de no Error/Info Messages ao carregar a página inicialmente
     */
    protected void assertPaginaRenderizada() {
        // implementação padrão não faz asserts
    }

    protected void executeAjax(final Component component, final String evento) {
        tester.executeAjaxEvent(component, evento);
    }

    protected void setAtributo(Object base, String atributo, Object valor) {
        Whitebox.setInternalState(base, atributo, valor);
    }

    protected void setAtributoDaPagina(String atributo, Object valor) {
        this.setAtributo(this.getInstanciaPaginaTestada(), atributo, valor);
    }

    protected void setAtributoEstaticoDaPagina(Class<?> classe, String atributo, Object valor) {
        setAtributo(classe, atributo, valor);
    }

    protected void assertErrorMessages(String... mensagens) {
        tester.assertErrorMessages(mensagens);
    }

    protected void assertInfoMessages(String... mensagens) {
        tester.assertInfoMessages(mensagens);
    }

    protected void assertSuccessMessages(String... expectedMessages) {
        List<Serializable> actualMessages = tester.getMessages(FeedbackMessage.SUCCESS);
        WicketTesterHelper.assertEquals(Arrays.asList(expectedMessages), actualMessages);
    }

    protected void assertWarnMessages(String... expectedInfoMessages) {
        List<Serializable> actualMessages = tester.getMessages(FeedbackMessage.WARNING);
        WicketTesterHelper.assertEquals(Arrays.asList(expectedInfoMessages), actualMessages);
    }

    protected void assertNoWarnMessage() {
        List<Serializable> messages = tester.getMessages(FeedbackMessage.WARNING);
        Assert.assertTrue("expect no warn message, but contains\n" + WicketTesterHelper.asLined(messages), messages.isEmpty());
    }

    protected void assertNoErrorMessage() {
        tester.assertNoErrorMessage();
    }

    protected void assertNoInfoMessage() {
        tester.assertNoInfoMessage();
    }

    protected void assertNoMessage() {
        assertNoMessage(true);
    }

    protected void assertNoMessage(boolean incluirSuccess) {
        assertNoErrorMessage();
        assertNoWarnMessage();
        assertNoInfoMessage();
        if (incluirSuccess) {
            assertNoSuccessMessage();
        }
    }

    protected void assertNoSuccessMessage() {
        List<Serializable> messages = tester.getMessages(FeedbackMessage.SUCCESS);
        Assert.assertTrue("expect no success message, but contains\n" + WicketTesterHelper.asLined(messages), messages.isEmpty());
    }

    protected void assertEnabledComponent(String componentPath, Class<? extends Component> classeEsperada) {
        tester.assertComponent(componentPath, classeEsperada);
        tester.assertEnabled(componentPath);
    }
}
