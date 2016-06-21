package br.com.dataeasy.visualizador;

import static org.apache.commons.lang.StringUtils.uncapitalize;
import static org.powermock.reflect.Whitebox.setInternalState;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.powermock.api.easymock.PowerMock;
import org.powermock.reflect.Whitebox;

import br.com.dataeasy.visualizador.negocio.mensagens.Messages;
import junit.framework.TestCase;

/**
 * <b>Description:</b> Superclasse de testes unitários e de integração. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11/04/2016
 */
public abstract class AbstractTeste<T> extends TestCase {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    protected T                    instancia;

    @Override
    @Before
    protected void setUp() throws Exception {
        super.setUp();
        addDependencias();
    }

    protected void addDependencias() {
        // Subclasses devem sobrescrever este método para incluir dependências.
    }

    protected void expectException(Class<? extends Exception> exceptionClass) {
        exception.expect(exceptionClass);
    }

    protected void expectExceptionMessagePorChave(String key) {
        exception.expectMessage(Messages.get(key));
    }

    protected void expectExceptionMessage(String chave, String... labels) {
        exception.expectMessage(getMensagem(chave, labels));
    }

    protected <P> P adicionarBean(P objeto) {
        return adicionarBean(uncapitalize(objeto.getClass().getSimpleName()), objeto);
    }

    protected <P> P adicionarBean(String atributo, P bean) {
        return adicionarBean(instancia, atributo, bean);
    }

    protected <P> P adicionarBean(Object instancia, String atributo, P bean) {
        setInternalState(instancia, atributo, bean);
        return bean;
    }

    protected <P> P adicionarMock(Class<P> clazz) {
        return adicionarMock(uncapitalize(clazz.getSimpleName()), clazz);
    }

    protected <P> P adicionarMock(String atributo, Class<P> clazz) {
        return adicionarBean(atributo, PowerMock.createMock(clazz));
    }

    protected <P> P adicionarMock(Object instancia, Class<P> clazz) {
        return adicionarBean(instancia, uncapitalize(clazz.getSimpleName()), PowerMock.createMock(clazz));
    }

    protected <P> P adicionarMock(Object instancia, String atributo, Class<P> clazz) {
        return adicionarBean(instancia, atributo, PowerMock.createMock(clazz));
    }

    protected <P> P getBean(Object instancia, Class<P> tipo) {
        return Whitebox.getInternalState(instancia, tipo);
    }

    protected <P> P getBean(Object instancia, String atributo) {
        return Whitebox.getInternalState(instancia, atributo);
    }

    protected void replayAll() {
        PowerMock.replayAll();
    }

    protected void verifyAll() {
        PowerMock.verifyAll();
    }

    protected String getMensagem(String chave, String... labels) {
        return Messages.get(chave, (Object[]) labels);
    }
}
