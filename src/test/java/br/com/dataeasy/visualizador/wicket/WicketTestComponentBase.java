package br.com.dataeasy.visualizador.wicket;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.uncapitalize;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.reflect.Whitebox.setInternalState;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.panel.Panel;
import org.junit.After;
import org.junit.Before;
import org.powermock.api.easymock.PowerMock;

import br.com.dataeasy.visualizador.mock.InjectedMock;
import br.com.dataeasy.visualizador.wicket.application.WicketConstantes;
import br.com.dataeasy.visualizador.wicket.application.WicketIds;
import br.com.dataeasy.visualizador.wicket.util.WicketUtil;

public abstract class WicketTestComponentBase<T extends Component> extends WicketTestBase {

    @Before
    public void setUp() throws Exception {
        PowerMock.resetAll();
        injetarMocksNoTeste();
        inicializarWicketTester();
        inicializarTeste();
    }

    private void injetarMocksNoTeste() {
        List<Field> camposDoTeste = getCamposDaClasseTeste(newArrayList(), getClass());
        for (Field campo : camposDoTeste) {
            Class<?> tipo = campo.getType();
            String nomeDoCampo = campo.getName();
            String nomeDaClasseDoBean = uncapitalize(tipo.getSimpleName());
            if (campo.isAnnotationPresent(InjectedMock.class)) {
                if (!appctxMock.containsBean(nomeDaClasseDoBean)) {
                    appctxMock.putBean(nomeDaClasseDoBean, createMock(tipo));
                }
                Object mock = appctxMock.getBean(nomeDaClasseDoBean);
                setInternalState(this, nomeDoCampo, mock);
            }
        }
    }

    private List<Field> getCamposDaClasseTeste(List<Field> fields, Class<?> type) {
        fields.addAll(asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null && !type.equals(WicketTestBase.class)) {
            getCamposDaClasseTeste(fields, type.getSuperclass());
        }
        return fields;
    }

    @After
    public void tearDown() {
        PowerMock.resetAll();
        System.gc();
    }

    protected void inicializarTeste() {
        // Fazer nada.
    }

    protected void confirmar(Panel confirmacaoPanel, String btnPrincipal, String btnSim, String modal) {
        tester.executeAjaxEvent(confirmacaoPanel.get(btnPrincipal), WicketConstantes.EVENTO_ON_CLICK);
        tester.executeAjaxEvent(confirmacaoPanel.get(btnSim), WicketConstantes.EVENTO_ON_CLICK);
        for (Behavior behavior : ((ModalWindow) confirmacaoPanel.get(modal)).getBehaviors()) {
            if (behavior instanceof AbstractAjaxBehavior) {
                this.tester.executeBehavior((AbstractAjaxBehavior) behavior);
            }
        }
    }

    protected AjaxFormChoiceComponentUpdatingBehavior getAjaxFormChoiceComponentUpdatingBehavior(Component componente) {
        AjaxFormChoiceComponentUpdatingBehavior retorno = null;
        for (Behavior behavior : componente.getBehaviors()) {
            if (behavior instanceof AjaxFormChoiceComponentUpdatingBehavior) {
                retorno = (AjaxFormChoiceComponentUpdatingBehavior) behavior;
                break;
            }
        }
        return retorno;
    }

    protected static String getFormId(String id) {
        return WicketUtil.getWicketId(WicketIds.FORM, id);
    }

    protected void assertInvisivelForm(String caminho) {
        tester.assertInvisible(getFormId(caminho));
    }

    protected void assertVisivelForm(String caminho) {
        tester.assertVisible(getFormId(caminho));
    }

}
