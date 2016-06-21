package br.com.dataeasy.visualizador.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;

import br.com.dataeasy.visualizador.VisualizadorInfraException;
import br.com.dataeasy.visualizador.negocio.excecoes.VisualizadorNegocioException;
import br.com.dataeasy.visualizador.negocio.mensagens.MensagemValidacao;
import br.com.dataeasy.visualizador.negocio.mensagens.Messages;

/**
 * <b>Title:</b> ReflexaoUtil.java <br>
 * <b>Description:</b> <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author vinicius.carvalho
 * @version Revision: $ Date: 29/05/2015
 */
public final class ReflexaoUtil {

    private static final String IS = "is";

    private ReflexaoUtil() {
        //
    }

    /**
     * Faz cópia de valores booleanos quando utilizando wrapper Boolean, mas o getter da propriedade se chama isXXX().
     * <code>BeanUtils.copyProperties()</code> não funciona aqui, pois os setters e getters para o bean utilizam Boolean, mas o getter se chama
     * isXXX(), o que deveria ser feito <b>somente</b> se o tipo de dado fosse booleano primitivo. Por isso o BeanUtils não consegue fazer a cópia de
     * valores.
     *
     * @param destino bean destino
     * @param origem bean origem
     */
    public static void copiarValoresBooleanos(Object destino, Object origem) {
        for (Method getter : destino.getClass().getMethods()) {
            String nomeGetter = getter.getName();
            if (nomeGetter.startsWith(IS)) {
                try {
                    String nomeSetter = "set" + nomeGetter.substring(IS.length());
                    Method setter = destino.getClass().getMethod(nomeSetter, Boolean.class);
                    Boolean valor = (Boolean) origem.getClass().getMethod(nomeGetter).invoke(origem);
                    setter.invoke(destino, valor);
                } catch (NoSuchMethodException | SecurityException e) {
                    continue;
                } catch (InvocationTargetException | IllegalAccessException e) {
                    tratarComoExcecaoGeral(e);
                }
            }
        }
    }

    private static void tratarComoExcecaoGeral(Exception e) {
        throw new VisualizadorNegocioException(Messages.get(MensagemValidacao.ERRO_VISUALIZADOR_ERRO_GERAL, e.getMessage()), e);
    }

    /**
     * Define valor de um field num objeto.
     *
     * @param objeto o objeto que contém o campo.
     * @param nomeCampo o nome do campo
     * @param valor o valor
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setValorDeCampo(Object objeto, String nomeCampo, Object valor) {
        try {
            Field field = objeto.getClass().getDeclaredField(nomeCampo);
            field.setAccessible(true);
            field.set(objeto, valor);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            tratarComoExcecaoGeral(e);
        }
    }

    public static Class<?> getTipoDeCampo(Object objeto, String nomeCampo) throws NoSuchFieldException {
        Field field = objeto.getClass().getDeclaredField(nomeCampo);
        return field.getType();
    }

    @SuppressWarnings("unchecked")
    public static <T> T instanciarParametro(Class<?> classe) {
        return (T) instanciarParametro(classe, 0);
    }

    public static <T> T instanciarParametro(Class<?> classe, int index) {
        Class<T> classeInstancia = getTipoParametrizado(classe, index);
        return instanciarObjetoPorReflexao(classeInstancia);
    }

    /**
     * Retorna a classe correspondente ao primeiro tipo parametrizado da classe informada.
     *
     * @param classe parametrizada
     * @return tipo da classe Class<T>
     */
    public static <T> Class<T> getTipoParametrizado(Class<?> classe) {
        return getTipoParametrizado(classe, 0);
    }

    /**
     * Retorna a classe correspondente ao parâmetro no índice correspondente, da classe informada.
     *
     * @param classe parametrizada
     * @param index - índice do parâmetro
     * @return Class<T>
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getTipoParametrizado(Class<?> classe, int index) {
        Type superClass = classe.getGenericSuperclass();
        while (!(superClass instanceof ParameterizedType)) {
            superClass = ((Class<T>) superClass).getGenericSuperclass();
        }
        return (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[index];
    }

    public static <T> T instanciarObjetoPorReflexao(Class<T> classe) {
        T retorno = null;
        try {
            if (classe != null) {
                retorno = BeanUtils.instantiateClass(classe);
            }
        } catch (BeanInstantiationException e) {
            throw new VisualizadorInfraException("Problema instanciando classe " + classe, e);
        }
        return retorno;
    }
}
