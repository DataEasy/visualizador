package br.com.dataeasy.visualizador.negocio.mensagens;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.function.Function;

import javax.annotation.Nullable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import br.com.dataeasy.visualizador.negocio.excecoes.VisualizadorNegocioException;
import br.com.dataeasy.visualizador.util.Constantes;

/**
 * <b>Description:</b>Utilitária para trabalhar com mensagens e i18n.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 07/04/2016
 */
public final class Messages {

    public static final String NOME_ARQUIVO = "messages";
    private static Messages    instancia    = new Messages();
    private Properties         properties;

    private Messages() {
        //
    }

    private static Messages getInstancia() {
        return instancia;
    }

    /**
     * Efetua a carga local do arquivo de mensagens.
     */
    public static void loadPropertiesFileInMemory(Locale locale) throws FileNotFoundException, IOException {
        if (getInstancia().properties == null) {
            // Monta o nome do arquivo para fazer a busca
            String nomeArquivo = getNomeArquivo(NOME_ARQUIVO, locale);
            // Captura o caminho completo do arquivo
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(nomeArquivo);
            // Cria um Properties e o carrega com o arquivo de mensagens..
            try (InputStreamReader isr = new InputStreamReader(is, Charset.forName(Constantes.UTF_8))) {
                getInstancia().properties = new Properties();
                getInstancia().properties.load(isr);
            }
        }
    }

    public static String getNomeArquivo(String baseName, Locale locale) {
        return baseName + "_" + locale.toString() + ".properties";
    }

    public static Properties getProperties() {
        if (getInstancia().properties == null) {
            try {
                loadPropertiesFileInMemory(Constantes.LOCALE_PADRAO);
            } catch (IOException e) {
                throw new VisualizadorNegocioException("Erro ao carregar properties de mensagens", e);
            }
        }
        return getInstancia().properties;
    }

    public static String get(String key, @Nullable Object... params) {
        assert (key != null);

        Properties propriedades = getProperties();
        String valorDaMensagem = ofNullable(propriedades.get(key)).orElse(key).toString();
        if (ArrayUtils.isNotEmpty(params)) {
            // Tenta traduzir os parametros, se não houver tradução, utiliza o valor real do argumento
            Function<Object, Object> mapper = param -> ofNullable(propriedades.get(param)).orElse(param);
            List<Object> listaParametrosTraduzidos = Arrays.stream(params).map(mapper).collect(toList());
            valorDaMensagem = formatar(valorDaMensagem, listaParametrosTraduzidos.toArray());
        }
        return valorDaMensagem;
    }

    public static String formatar(String mensagem, Object... params) {
        String resultado = mensagem;
        if (ArrayUtils.isNotEmpty(params) && !StringUtils.isEmpty(mensagem)) {
            MessageFormat mf = new MessageFormat(mensagem, Constantes.LOCALE_PADRAO);
            resultado = mf.format(params, new StringBuffer(), null).toString();
        }

        return resultado;
    }

    /**
     * Verifica se a mensagem está no padrão de uma chave e retorna a mensagem correspondente, se for o caso. Caso seja a mensagem já processada,
     * retorna a mesma.
     *
     * @param mensagem
     * @return String
     */
    public static String getMessage(String mensagem) {
        String valorMensagem = mensagem;
        if (mensagem.startsWith("erro.") //
                || mensagem.startsWith("sucesso.") //
                || mensagem.startsWith("aviso.")) {
            valorMensagem = get(mensagem);
        }
        return valorMensagem;
    }

    public static String newLine(int quantity) {
        String newLineCharBySO = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < quantity; i++) {
            sb.append(newLineCharBySO);
        }
        return sb.toString();
    }
}
