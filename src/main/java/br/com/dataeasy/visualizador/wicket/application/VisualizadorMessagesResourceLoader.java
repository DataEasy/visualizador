package br.com.dataeasy.visualizador.wicket.application;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.resource.loader.IStringResourceLoader;

import br.com.dataeasy.visualizador.negocio.mensagens.Messages;

/**
 * <b>Description:</b> Utiliza o messages.properties do projeto de negócio como fonte de resources para a camada web. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 09/07/2015
 */
public class VisualizadorMessagesResourceLoader implements IStringResourceLoader {

    @Override
    public final String loadStringResource(final Class<?> clazz, final String key, Locale locale, final String style, final String variation) {
        Locale loc = locale;
        if (loc == null) {
            loc = Session.exists() ? Session.get().getLocale() : Locale.getDefault();
        }
        Control utf8Control = new UTF8Control();
        try {
            return ResourceBundle.getBundle(Messages.NOME_ARQUIVO, loc, utf8Control).getString(key);
        } catch (MissingResourceException mrx) {
            try {
                return ResourceBundle.getBundle(Messages.NOME_ARQUIVO, loc, Thread.currentThread().getContextClassLoader(), utf8Control)
                        .getString(key);
            } catch (MissingResourceException mrx2) {
                return null;
            }
        }
    }

    @Override
    public final String loadStringResource(final Component component, final String key, Locale locale, final String style, final String variation) {
        return loadStringResource((Class<?>) null, key, locale, style, variation);
    }

}
