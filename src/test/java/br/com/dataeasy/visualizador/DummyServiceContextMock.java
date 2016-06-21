package br.com.dataeasy.visualizador;

import br.com.dataeasy.visualizador.negocio.service.AbstractTesteUnitario;

/**
 * <b>Description:</b> Contexto customizado para simular o padrão IoC e DI do Spring <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.sousa
 * @version Revision: $ Date: 11/04/2016
 */
@SuppressWarnings("serial")
public class DummyServiceContextMock<T extends AbstractTesteUnitario<E>, E> extends AbstractDummyContext<T, E> {

    public static <T extends AbstractTesteUnitario<E>, E> DummyServiceContextMock<T, E> criarContexto(T teste, E instanciaClasseTestada) {
        DummyServiceContextMock<T, E> contexto = new DummyServiceContextMock<T, E>();
        contexto.instanciarMocks(instanciaClasseTestada);
        contexto.injetarDependecias(teste);
        return contexto;

    }
}
