package br.com.dataeasy.visualizador.negocio.service;

import org.junit.Before;

import br.com.dataeasy.visualizador.DummyServiceContextMock;

/**
 * <b>Description:</b>Classe base para testes unitários que utilizam PowerMockRunner.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11/04/2016
 */
public abstract class AbstractServiceTest<T> extends AbstractTesteUnitario<T> {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        DummyServiceContextMock.criarContexto(this, instancia);
    }

    @Override
    protected void addDependencias() {
        //
    }

}
