package br.com.dataeasy.visualizador.negocio.service;

import static br.com.dataeasy.visualizador.util.ReflexaoUtil.instanciarParametro;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.dataeasy.visualizador.AbstractTeste;

/**
 * <b>Description:</b>Classe base para testes unitários que utilizam PowerMockRunner.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.sousa
 * @version Revision: $ Date: Jul 21, 2015
 */
@RunWith(PowerMockRunner.class)
public abstract class AbstractTesteUnitario<T> extends AbstractTeste<T> {

    @Override
    @Before
    public void setUp() throws Exception {
        PowerMock.resetAll();
        instancia = criarInstancia();
        super.setUp();
    }

    protected T criarInstancia() {
        return instanciarParametro(getClass());
    }

}
