package br.com.dataeasy.visualizador.wicket;

import static org.apache.commons.lang.StringUtils.uncapitalize;
import static org.powermock.reflect.Whitebox.getInternalState;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.injection.IFieldValueFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;

import br.com.dataeasy.visualizador.VisualizadorInfraException;

/**
 * <b>Description:</b> Listener que reproduz a injeção de depêndecias feita pelo spring em componentes web <br>
 * Utilizado para injetar mocks em campos com anotações @SpringBean <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.sousa
 * @version Revision: $ Date: Sep 28, 2015
 */
public class SpringComponentInjectorCustom extends SpringComponentInjector {
    private IFieldValueFactory     fabrica;
    private ApplicationContextMock contexto;

    public SpringComponentInjectorCustom(final WebApplication webapp, final ApplicationContextMock ctx, final boolean wrapInProxies) {
        super(webapp, ctx, wrapInProxies);
        this.contexto = ctx;
        this.fabrica = getInternalState(this, "fieldValueFactory");
    }

    @Override
    public void onInstantiation(final Component component) {
        Field[] campos = buscarCampos(component.getClass(), fabrica);
        for (Field campo : campos) {
            if (!campo.isAccessible()) {
                campo.setAccessible(true);
            }
            String nomeDaClasseDoMock = uncapitalize(campo.getType().getSimpleName());
            Object mock = null;
            if (!contexto.containsBean(nomeDaClasseDoMock)) {
                // Se o mock não é encontrado, não é possível cria-lo aqui pois uma chamada para o método 'replayAll' já deve ter sido executada
                // Essa chamada altera o 'estado' dos mocks para != 'gravando'
                throw new VisualizadorInfraException("Um mock para [" + campo.getType().getSimpleName() + "] não foi encontrado/injetado.");
            }
            mock = contexto.getBean(nomeDaClasseDoMock);
            try {
                campo.set(component, mock);
            } catch (Exception e) {
                throw new VisualizadorInfraException("Erro ao instanciar componente.", e);
            }
        }
    }

    @SuppressWarnings("PMD.AvoidReassigningParameters")
    private Field[] buscarCampos(Class<?> clazz, final IFieldValueFactory factory) {
        List<Field> encontrados = new ArrayList<Field>();

        while (clazz != null) {
            Field[] campos = clazz.getDeclaredFields();
            for (final Field campo : campos) {
                if (factory.supportsField(campo)) {
                    encontrados.add(campo);
                }
            }
            clazz = clazz.getSuperclass();
        }

        return encontrados.toArray(new Field[encontrados.size()]);
    }
}
