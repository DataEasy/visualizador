package br.com.dataeasy.visualizador.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.BooleanUtils;

import br.com.dataeasy.visualizador.media.MediaType;
import br.com.dataeasy.visualizador.negocio.mensagens.MensagemValidacao;
import br.com.dataeasy.visualizador.negocio.mensagens.Messages;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

/**
 * @author imy
 */
@WebServlet(name = "ViewDocumentServlet", urlPatterns = { "/document-viewer/ViewDocumentHandler" })
public class ViewDocumentServlet extends AnnotationServlet {

    private static final int                 INDEX_GRUPO      = 2;
    private static final long                serialVersionUID = 1L;
    private static final String              REASON           = "Reason";
    private static final String              SUCCESS          = "success";
    private static final Map<String, String> EXCECOES;

    static {
        EXCECOES = new HashMap<String, String>();
        EXCECOES.put("File extension (.+?) of filename (.+?) cannot be opened, and looks to be corrupted."
                + " Please contact us via our support forums sharing the document for review.",
                MensagemValidacao.ERRO_VISUALIZADOR_ARQUIVO_CORROMPIDO);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        configurarCaminhoDaApp(request);
        try {
            Object obj = annotationHandler.viewDocumentHandler(request, response);
            tratarResposta(response, obj);
        } catch (Exception e) {
            Map<String, String> json = new HashMap<String, String>();
            json.put(SUCCESS, Boolean.FALSE.toString());
            json.put(REASON, Messages.get(MensagemValidacao.ERRO_VISUALIZADOR_ERRO_GERAL, e.getClass() + ": " + e.getMessage()));
            writeOutput(MediaType.APPLICATION_JSON, response, new Gson().toJson(json));
        }
    }

    /**
     * Trata a resposta a ser exibida para o usuário, exibindo mensagem amigágel caso ocorra erro.
     *
     * @param response o response o HttpServletResponse
     * @param obj o JSON de retorno da chamada ao GroupDocs
     */
    @SuppressWarnings("unchecked")
    protected void tratarResposta(HttpServletResponse response, Object obj) throws IOException {
        LinkedTreeMap<String, Object> json = (LinkedTreeMap<String, Object>) new Gson().fromJson(obj.toString(), Object.class);

        Boolean success = (Boolean) json.get(SUCCESS);
        if (BooleanUtils.isFalse(success)) {
            traduzirExcecao(response, json);
        } else {
            writeOutput(MediaType.APPLICATION_JSON, response, obj);
        }
    }

    /**
     * Exibe mensagem amigável ao usuário na ocorrência de exceção.
     *
     * @param response o response o HttpServletResponse
     * @param json o JSON de retorno da chamada ao GroupDocs
     */
    protected void traduzirExcecao(HttpServletResponse response, LinkedTreeMap<String, Object> json) throws IOException {
        String reason = (String) json.get(REASON);
        String chave = null;
        Object[] parametros = null;

        if (reason == null) {
            chave = MensagemValidacao.ERRO_VISUALIZADOR_ARQUIVO_NAO_ENCONTRADO;
            parametros = new String[] { json.toString() };
        } else {
            for (Entry<String, String> entry : EXCECOES.entrySet()) {
                Matcher matcher = Pattern.compile(entry.getKey()).matcher(reason);
                if (matcher.find()) {
                    chave = entry.getValue();
                    parametros = new String[] { matcher.group(INDEX_GRUPO) };
                    break;
                }
            }
        }

        if (chave == null) {
            chave = MensagemValidacao.ERRO_VISUALIZADOR_ERRO_GERAL;
            parametros = new String[] { reason };
        }

        json.put(REASON, Messages.get(chave, parametros));
        writeOutput(MediaType.APPLICATION_JSON, response, new Gson().toJson(json));
    }
}
