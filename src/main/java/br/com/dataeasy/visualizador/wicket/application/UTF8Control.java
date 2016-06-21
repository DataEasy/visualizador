package br.com.dataeasy.visualizador.wicket.application;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import org.apache.wicket.resource.loader.IStringResourceLoader;

import br.com.dataeasy.visualizador.negocio.mensagens.Messages;
import br.com.dataeasy.visualizador.util.Constantes;

/**
 * <b>Description:</b> {@link Control} necessário para fazer com que o {@link IStringResourceLoader} do wicket leia o properties central da aplicação
 * em UTF-8. <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author pedro.castro
 * @version Revision: $ Date: 09/07/2015
 */
public class UTF8Control extends Control {

    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException,
            InstantiationException, IOException {
        String resourceName = Messages.getNomeArquivo(baseName, locale);
        ResourceBundle bundle = null;
        InputStream stream = null;
        if (reload) {
            URL url = loader.getResource(resourceName);
            if (url != null) {
                URLConnection connection = url.openConnection();
                if (connection != null) {
                    connection.setUseCaches(false);
                    stream = connection.getInputStream();
                }
            }
        } else {
            stream = loader.getResourceAsStream(resourceName);
        }
        if (stream != null) {
            try {
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, Constantes.UTF_8));
            } finally {
                stream.close();
            }
        }
        return bundle;
    }

}
