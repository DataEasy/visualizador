package br.com.dataeasy.visualizador.wicket.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.wicket.Component;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * <b>Description:</b>Utilitário para trabalhar com classes Wicket.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author Rafael
 * @version Revision: $ Date: 10/07/2015
 */
public final class WicketUtil {

    private WicketUtil() {
        //
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) RequestCycle.get().getResponse().getContainerResponse();
    }

    public static HttpSession getSession() {
        return ((ServletWebRequest) RequestCycle.get().getRequest()).getContainerRequest().getSession();
    }

    public static String getUrlBaseDaAplicacao() {
        HttpServletRequest request = getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @SuppressWarnings("unchecked")
    public static <C extends Component> C getComponente(Component componenteBase, String... ids) {
        return (C) componenteBase.get(getWicketId(ids));
    }

    public static String getWicketId(String... ids) {
        StringBuilder retorno = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            retorno.append(ids[i]);
            if (i < ids.length - 1) {
                retorno.append(':');
            }
        }
        return retorno.toString();
    }
}
