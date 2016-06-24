package br.com.dataeasy.visualizador.config;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dataeasy.visualizador.VisualizadorInfraException;
import br.com.dataeasy.visualizador.localization.LocalizacaoCustomizada;
import br.com.dataeasy.visualizador.negocio.excecoes.VisualizadorNegocioException;
import br.com.dataeasy.visualizador.negocio.mensagens.MensagemValidacao;
import br.com.dataeasy.visualizador.negocio.mensagens.Messages;
import br.com.dataeasy.visualizador.negocio.modelo.Binario;
import br.com.dataeasy.visualizador.util.Constantes;
import br.com.dataeasy.visualizador.wicket.application.UTF8Control;

import com.groupdocs.annotation.data.connector.IConnector;
import com.groupdocs.annotation.exception.AnnotationException;
import com.groupdocs.annotation.handler.AnnotationHandler;
import com.groupdocs.annotation.localization.ILocalization;
import com.groupdocs.viewer.config.ServiceConfiguration;
import com.groupdocs.viewer.domain.path.EncodedPath;
import com.groupdocs.viewer.domain.path.GroupDocsPath;

/**
 * <b>Description:</b>Configurações centralizadas e inicializadas do Visualizador. Fornece acesso às configurações globais do componente.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 16/06/2015
 */
// inicializa o bean na primeira requisição. Necessário para definir URL base da aplicação (acessa HttpServletRequest)
@Component
@SuppressWarnings("serial")
public class VisualizadorConfig implements Serializable {

    public static final String               USUARIO_VISUALIZADOR_PADRAO = AnnotationHandler.ANONYMOUS_USERNAME;
    private static final Map<String, String> EXTENSOES                   = new HashMap<>();

    @Autowired
    private ApplicationConfig                applicationConfig;

    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value = "SE_BAD_FIELD",
            justification = "Não é possível tornar a instancia serializável pois a api não é nossa.")
    protected AnnotationHandler              annotationHandler;
    private ILocalization                    localization;

    static {
        EXTENSOES.put("application/pdf", "pdf");
        EXTENSOES.put("image/tiff", "tif");
    }

    public AnnotationHandler getAnnotationHandler() {
        return annotationHandler;
    }

    public ApplicationConfig getApplicationConfig() {
        return applicationConfig;
    }

    @PostConstruct
    public void init() {
        try {
            applicationConfig.setWidgetId(Constantes.DIV_VISUALIZADOR);

            this.annotationHandler = criarAnnotationHandler();
        } catch (Exception e) {
            lancarExcecaoSeJaTratada(e);
            throw new VisualizadorInfraException("Problema ao inicializar infra do Visualizador.", e);
        }
    }

    private void lancarExcecaoSeJaTratada(Exception e) {
        if (e instanceof VisualizadorInfraException || e instanceof VisualizadorNegocioException) {
            throw (RuntimeException) e;
        }
    }

    private AnnotationHandler criarAnnotationHandler() {
        return criarAnnotationHandler(false);
    }

    private AnnotationHandler criarAnnotationHandler(boolean force) {
        if (annotationHandler == null || force) {
            TimeZone.setDefault(Constantes.TIMEZONE_PADRAO);
            ServiceConfiguration serviceConfiguration = new ServiceConfiguration(applicationConfig);
            IConnector connector = null;
            try {
                // Necessário para formatação de Datas
                Locale.setDefault(Constantes.LOCALE_PADRAO);

                annotationHandler = new AnnotationHandler(serviceConfiguration, connector);
                this.localization = criarLocalizacao();
            } catch (Exception e) {
                String msg = "Problema ao criar AnnotationHandler. O ApplicationConfig do GroupDocs foi carregado corretamente?";
                throw new VisualizadorInfraException(msg, e);
            }
        }
        return annotationHandler;
    }

    /**
     * Retorna as tags <script> e <link> para inicialização do GroupDocs.
     *
     * @param request o HttpServletRequest
     * @return String com tags <script> e <link>
     */
    public String getHeader(HttpServletRequest request) {
        try {
            return annotationHandler.getHeader(applicationConfig.getApplicationPath(), request);
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException(e);
        }
    }

    /**
     * Retorna o script que faz chamada Javascript à inicialização e utilização do GroupDocs.
     *
     * @param caminhoArquivo o caminho completo do arquivo a ser aberto.
     * @return o script a ser renderizado na página.
     */
    public String getAnnotationScripts(String caminhoArquivo) {
        String userName = USUARIO_VISUALIZADOR_PADRAO;
        File arquivo;
        if (caminhoArquivo == null) {
            arquivo = new File(applicationConfig.getBasePath() + applicationConfig.getDefaultFileName());
        } else {
            arquivo = new File(caminhoArquivo);
        }
        GroupDocsPath path = new EncodedPath(arquivo.getAbsolutePath(), annotationHandler.getConfiguration());
        String initialPath = path.getPath();

        try {
            String userGuid = annotationHandler.getUserGuid(userName);
            return annotationHandler.getAnnotationScript(initialPath, userName, userGuid);
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException(e);
        }
    }

    /**
     * Retorna identificador do usuário no GroupDocs.
     *
     * @param nomeUsuario nome do usuário
     * @return o identificador de usuário utilizado pelo GroupDocs
     */
    public String getIdUsuario(String nomeUsuario) {
        try {
            return annotationHandler.getUserGuid(nomeUsuario);
        } catch (AnnotationException e) {
            throw new VisualizadorInfraException("Problema ao requisitar ID de usuário no GroupDocs.", e);
        }
    }

    /**
     * Retorna informações necessárias para exibição de documento no Visualizador do GroupDocs.
     *
     * @param binario o binário, que contém referência a documento/imagem a ser exibido
     * @param nomeUsuario o nome/login do usuário a ser utilizado para registro de atividades (criação/alteração de anotações e comentários)
     * @return pacote de informações para renderização de imagem no Javascript.
     */
    public InformacoesGroupDocs getInformacoesParaVisualizacao(Binario binario, String nomeUsuario) {
        InformacoesGroupDocs config = getInformacoesBasicas();
        config.setFileId(getIdArquivo(binario));
        config.setUserName(nomeUsuario);
        config.setUserId(getIdUsuario(nomeUsuario));
        return config;
    }

    /**
     * Retorna InformacoesGroupDocs com básico de informações para o Javascript exibir uma mensagem de erro.
     */
    public InformacoesGroupDocs getInformacoesBasicas() {
        InformacoesGroupDocs info = new InformacoesGroupDocs(applicationConfig);
        if (this.localization instanceof LocalizacaoCustomizada) {
            LocalizacaoCustomizada localizacaoCustomizada = (LocalizacaoCustomizada) this.localization;
            info.setLocalizedStrings(localizacaoCustomizada.getValores());
            info.setThumbsImageBase64Encoded(localizacaoCustomizada.getLocalizedRightPanelImage());
        }
        return info;
    }

    /**
     * Retorna o ID de arquivo utilizado pelo GroupDocs para renderização no visualizador.
     *
     * @param binario o binário a ser visualizado
     * @return o identificador da imagem utilizado pelo GroupDocs
     */
    public String getIdArquivo(Binario binario) {
        try {
            Path linkSimbolico = criarLinkSimbolico(binario.getCaminhoCompleto(), binario.getMimeType());
            return new EncodedPath(linkSimbolico.toString(), annotationHandler.getConfiguration()).getPath();
        } catch (Exception e) {
            lancarExcecaoSeJaTratada(e);

            String msg = Messages.get(MensagemValidacao.ERRO_VISUALIZADOR_ERRO_GERAL, e.getClass().toString() + ": " + e.getMessage());
            throw new VisualizadorNegocioException(msg, e);
        }
    }

    /**
     * Cria link simbólico que tenha extensão PDF ou outra reconhecível pelo GroupDocs.
     *
     * @param caminhoOriginal caminho original do arquivo
     * @return o link simbólico no sistema de arquivos com extensão PDF/TIF que aponta para o arquivo original.
     */
    private Path criarLinkSimbolico(String caminhoOriginal, String mimeType) {
        Path target = Paths.get(caminhoOriginal.replace("\\", File.separator));
        String pathDiretorioTemp = System.getProperty("java.io.tmpdir");
        Path linkSimbolico = null;

        try {
            String nomeArquivo = FilenameUtils.getBaseName(caminhoOriginal);
            verificarContentType(mimeType);
            String extensao = "." + getExtensao(mimeType);
            linkSimbolico = Paths.get(pathDiretorioTemp, nomeArquivo + extensao);
            if (isLinkExistenteErrado(linkSimbolico, target)) {
                Files.delete(linkSimbolico);
            }

            if (Files.notExists(linkSimbolico, LinkOption.NOFOLLOW_LINKS)) {
                Files.createSymbolicLink(linkSimbolico, target);
            }
        } catch (IOException e) {
            throw new VisualizadorInfraException("Problema ao recuperar caminho do arquivo para visualização.", e);
        }
        return linkSimbolico;
    }

    /**
     * Retorna a extensão de um tipo de arquivo baseado no seu MIME Type.
     *
     * @param mimeType o MIME Type. Ex.: "application/pdf"
     * @return a extensão. Ex.: "pdf"
     */
    private String getExtensao(String mimeType) {
        return EXTENSOES.get(mimeType.toLowerCase());
    }

    /**
     * Verifica se o contentType do documento a ser visualizado está inválido
     *
     * @param mimeType o content type do documento
     */
    private void verificarContentType(String mimeType) {
        if (mimeType == null) {
            throw new VisualizadorNegocioException(Messages.get(MensagemValidacao.ERRO_VISUALIZADOR_MIME_TYPE_INVALIDO, "(nulo)"));
        }
    }

    /**
     * Verifica se existe link simbólico e se aponta para mesmo caminho do target.
     *
     * @param link o caminho do link simbólico
     * @param target o target considerado correto
     */
    private boolean isLinkExistenteErrado(Path link, Path target) {
        boolean resultado = false;
        if (Files.exists(link, LinkOption.NOFOLLOW_LINKS) && Files.isSymbolicLink(link)) {
            try {
                Path targetExistente = Files.readSymbolicLink(link);
                resultado = !target.equals(targetExistente);
            } catch (IOException e) {
                resultado = false;
            }
        }
        return resultado;
    }

    /**
     * Cria ILocalization a partir de configurações.
     */
    private ILocalization criarLocalizacao() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("language", Constantes.LOCALE_PADRAO, new UTF8Control());
        return new LocalizacaoCustomizada(resourceBundle);
    }

    /**
     * Define o diretório base do Visualizador, geralmente correspondendo ao caminho do armazém de binários ou volume. É necessário para cálculo da
     * pasta de cache.
     *
     * @param binario o documento a ser visualizado.
     */
    public void configurar(Binario binario) {
        if (binario == null || Files.notExists(Paths.get(binario.getCaminhoCompleto()))) {
            return;
        }

        String caminho = binario.getCaminhoCompleto();
        String diretorioBase = caminho.substring(0, caminho.lastIndexOf(FilenameUtils.getName(caminho)));

        if (!diretorioBase.equals(this.applicationConfig.getBasePath())) {
            this.applicationConfig.setBasePath(diretorioBase);
            criarAnnotationHandler(true);
        }
    }
}
