package br.com.dataeasy.visualizador.negocio.modelo;

import java.io.Serializable;

/**
 * <b>Description:</b>Model para binário a ser visualizado.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 7 de abr de 2016
 */
public class Binario implements Serializable {

    private static final long serialVersionUID = 3397324501686485354L;
    private String            caminhoCompleto;
    private String            mimeType;
    private String            token;

    public Binario() {
        super();
    }

    public Binario(String caminhoCompleto, String mimeType) {
        this(caminhoCompleto, mimeType, null);
    }

    public Binario(String caminhoCompleto, String mimeType, String token) {
        super();
        this.caminhoCompleto = caminhoCompleto;
        this.mimeType = mimeType;
        this.token = token;
    }

    public String getCaminhoCompleto() {
        return caminhoCompleto;
    }

    public void setCaminhoCompleto(String caminhoCompleto) {
        this.caminhoCompleto = caminhoCompleto;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
