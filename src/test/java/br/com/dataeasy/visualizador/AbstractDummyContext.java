package br.com.dataeasy.visualizador;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.reflect.Whitebox.setInternalState;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.dataeasy.visualizador.mock.InjectedMock;
import br.com.dataeasy.visualizador.negocio.service.AbstractServiceTest;
import br.com.dataeasy.visualizador.negocio.service.AbstractTesteUnitario;

/**
 * <b>Description:</b> Classe abstrata utilizada por contextos simulados durante a execuçao de testes <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11/04/2016
 */
@SuppressWarnings("serial")
abstract class AbstractDummyContext<T extends AbstractTesteUnitario<E>, E> extends LinkedHashMap<Class<?>, Object> {

    protected void instanciarMocks(Object instancia) {
        List<Field> camposDaClasse = getAllFields(newArrayList(), instancia.getClass());
        for (Field campo : camposDaClasse) {
            Class<?> tipo = campo.getType();
            String nome = campo.getName();
            if (campo.isAnnotationPresent(Autowired.class) || campo.isAnnotationPresent(Inject.class)) {
                Object mock = get(tipo);
                if (mock == null) {
                    mock = createMock(tipo);
                    put(tipo, mock);
                }
                setInternalState(instancia, nome, mock);
            }
        }
    }

    protected void injetarDependecias(Object instancia) {
        List<Field> camposDaInstancia = getAllFields(newArrayList(), instancia.getClass());
        for (Field campo : camposDaInstancia) {
            Class<?> tipo = campo.getType();
            String nome = campo.getName();
            if (campo.isAnnotationPresent(InjectedMock.class)) {
                setInternalState(instancia, nome, get(tipo));
            }
        }
    }

    protected static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null && !type.equals(AbstractServiceTest.class)) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
}
