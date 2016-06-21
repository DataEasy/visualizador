package br.com.dataeasy.visualizador.negocio.validacao;

import org.junit.Before;

import br.com.dataeasy.visualizador.DummyValidatorContextMock;
import br.com.dataeasy.visualizador.negocio.service.AbstractTesteUnitario;

/**
 * <b>Description:</b> <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11/04/2016
 */
public abstract class AbstractValidadorTest<T> extends AbstractTesteUnitario<T> {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        DummyValidatorContextMock.criarContextoValidacao(this, instancia);
    }

    @Override
    protected void addDependencias() {
        //
    }
}
