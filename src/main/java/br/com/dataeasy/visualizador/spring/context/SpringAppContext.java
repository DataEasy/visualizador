package br.com.dataeasy.visualizador.spring.context;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.google.common.annotations.VisibleForTesting;

/**
 * <b>Description:</b> <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author vinicius.carvalho
 * @version Revision: $ Date: 27/05/2015
 */
public final class SpringAppContext implements ApplicationContextAware {

    private static ApplicationContext appContext;

    @SuppressWarnings("PMD.UnnecessaryConstructor")
    @VisibleForTesting
    public SpringAppContext() {
        super();
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanAlias) {
        return (T) getAppContext().getBean(beanAlias);
    }

    /**
     * Retorna o Spring Bean que tem a classe em questão. O nome é derivado da classe, utilizando-se o nome simples desta com a primeira letra
     * minúscula.
     *
     * @param clazz classe do tipo do bean gerenciado pelo Spring
     * @return o bean gerenciado pelo Spring
     */
    public static <T extends Object> T getBean(Class<T> clazz) {
        String nome = clazz.getSimpleName();
        return clazz.cast(getBean(StringUtils.uncapitalize(nome)));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        staticSetApplicationContext(applicationContext);
    }

    private static void staticSetApplicationContext(ApplicationContext applicationContext) {
        appContext = applicationContext;
    }
}
