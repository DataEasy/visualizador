package br.com.dataeasy.visualizador.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.config.service.Post;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.springframework.web.context.ContextLoader;

import br.com.dataeasy.visualizador.config.VisualizadorConfig;

import com.groupdocs.annotation.exception.AnnotationException;

/**
 *
 * <b>Description:</b>Serviço utilizando o Atmosphere Framework utilizado pelo GroupDocs.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @author Aleksey Permyakov
 * @version Revision: $ Date: 16/07/2014
 */
@ManagedService(path = "/annotation")
public class AtmosphereManagedService {
    private static final String MESSAGE_HANDLER_THROWS = "Handler throws exception: {0}";

    /**
     * On ready handler
     *
     * @param resource resource data received from socket
     */
    @Ready
    public void onReady(final AtmosphereResource resource) {
        try {
            VisualizadorConfig visualizadorConfig = ContextLoader.getCurrentWebApplicationContext().getBean(VisualizadorConfig.class);
            visualizadorConfig.getAnnotationHandler().onAtmosphereReady(resource);
        } catch (AnnotationException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, MESSAGE_HANDLER_THROWS, e.getMessage());
        }
    }

    /**
     * On message handler [POST]
     *
     * @param resource resource data received from socket
     */
    @Post
    @Message
    public void onMessage(AtmosphereResource resource) {
        try {
            VisualizadorConfig visualizadorConfig = ContextLoader.getCurrentWebApplicationContext().getBean(VisualizadorConfig.class);
            visualizadorConfig.getAnnotationHandler().onAtmosphereMessage(resource);
        } catch (AnnotationException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, MESSAGE_HANDLER_THROWS, e.getMessage());
        }
    }
}
