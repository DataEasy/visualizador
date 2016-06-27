package br.com.dataeasy.visualizador.wicket.pages;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * <b>Description:</b>Utilitária na manipulação e extração de parâmetros passados ao visualizador.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 18/04/2016
 */
public class VisualizadorPageUtils {

    /**
     * Cria mapa de parâmetros chave/valor a partir de parâmetro de requisição
     *
     * @param parametroPost parâmetro na requisição POST que contém todos os parâmetros a serem passados ao visualizador.
     * @return o mapa de parâmetros
     */
    public static Map<String, Object> criarMapa(String parametrosChaveValor) {
        if (StringUtils.isBlank(parametrosChaveValor)) {
            return null;
        }

        Map<String, Object> mapa = new HashMap<>();
        String[] parametros = parametrosChaveValor.split("&");
        for (String param : parametros) {
            String[] chaveEValor = param.split("=");
            mapa.put(chaveEValor[0].trim(), chaveEValor[1].trim());
        }

        return mapa;
    }

    public static String getParametroPost(String nome) {
        return RequestCycle.get().getRequest().getRequestParameters().getParameterValue(nome).toString();
    }
}
