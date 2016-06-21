package br.com.dataeasy.visualizador.localization;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.groupdocs.annotation.localization.ILocalization;
import com.groupdocs.annotation.localization.LocalizationKeys;

/**
 *
 * <b>Description:</b>Customização de localização para o GroupDocs.<br>
 * <b>Project:</b> docflow-web <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 20/10/2015
 */
public class LocalizacaoCustomizada implements ILocalization {

    private static final Map<String, String> VALORES           = new HashMap<String, String>();
    private static final String              RIGHT_PANEL_IMAGE = "LocalizedRightPanelImage";
    private String                           localizedRightPanelImage;

    public LocalizacaoCustomizada(ResourceBundle resourceBundle) {
        super();
        popularValores(resourceBundle);
    }

    /**
     * Inicializa mapa de chaves com valores de mensagens.
     *
     * @param resourceBundle o ResourceBundle que contem as mensagens/texto.
     */
    private void popularValores(ResourceBundle resourceBundle) {
        Enumeration<String> chaves = resourceBundle.getKeys();
        while (chaves.hasMoreElements()) {
            String chave = chaves.nextElement();
            VALORES.put(chave, resourceBundle.getString(chave));
        }

        this.localizedRightPanelImage = resourceBundle.getString(RIGHT_PANEL_IMAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLocalizedString(LocalizationKeys localizationKeys) {
        if (localizationKeys == null) {
            return null;
        }
        return VALORES.get(localizationKeys.name());
    }

    /**
     * Imagem GIF em codificação base64 utilizada para label do lado direito do visualizador de miniaturas. É gerada tag da seguinte forma:
     * <code>&lt;img src="data:image/gif;base64,R0lGODlhLQB3APcAAAAAAABShgZViAlX ... CMQCyAB9lXNHFBCjgUEAAOw=="&gt;</code>
     */
    @Override
    public String getLocalizedRightPanelImage() {
        return localizedRightPanelImage;
    }

    /**
     * Retorna cópia do mapa de valores das mensagens desta LocalizacaoCustomizada.
     */
    public Map<String, String> getValores() {
        return new HashMap<String, String>(VALORES);
    }
}
