package br.com.dataeasy.visualizador;

import static com.google.common.collect.Lists.newArrayList;
import static org.powermock.reflect.Whitebox.setInternalState;

import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.dataeasy.visualizador.negocio.validacao.AbstractValidadorTest;

/**
 * <b>Description:</b> Contexto customizado para simular o padrão IoC e DI do Spring <br>
 * <b>Project:</b> visualizador<br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11/04/2016
 */
@SuppressWarnings("serial")
public class DummyValidatorContextMock<T extends AbstractValidadorTest<E>, E> extends AbstractDummyContext<T, E> {

    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    public static <T extends AbstractValidadorTest<E>, E> DummyValidatorContextMock<T, E> criarContextoValidacao(T teste, E instanciaValidadorTestado)
            throws Exception {
        DummyValidatorContextMock<T, E> contexto = new DummyValidatorContextMock<T, E>();
        contexto.instanciarRegrasDeValidacao(instanciaValidadorTestado);
        contexto.injetarDependecias(teste);
        return contexto;

    }

    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    private void instanciarRegrasDeValidacao(E instanciaValidadorTestado) throws Exception {
        List<Field> camposDoValidadorTestado = getAllFields(newArrayList(), instanciaValidadorTestado.getClass());
        for (Field campo : camposDoValidadorTestado) {
            Class<?> tipo = campo.getType();
            String nome = campo.getName();
            if (campo.isAnnotationPresent(Autowired.class) || campo.isAnnotationPresent(Inject.class)) {
                Object instanciaDaRegra = tipo.getConstructor().newInstance();
                // Cria os mocks que a regra depende
                instanciarMocks(instanciaDaRegra);
                // Injeta os mocks na regra
                injetarDependecias(instanciaDaRegra);
                // Injeta a regra no validador
                setInternalState(instanciaValidadorTestado, nome, instanciaDaRegra);
            }
        }
    }

}
