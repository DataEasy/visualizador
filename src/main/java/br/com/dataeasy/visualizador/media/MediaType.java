package br.com.dataeasy.visualizador.media;

/**
 *
 * <b>Description:</b>Alguns content-types utilizados no Visualizador.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author Alex Bobkov
 * @author rafael.fontoura
 * @version Revision: $ Date: 22/06/2015
 */
public enum MediaType {
    APPLICATION_JSON("application/json"),
    TEXT_HTML("text/html"),
    IMAGE_PNG("image/png"),
    TEXT_PLAIN("text/plain");

    private String name;

    private MediaType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
