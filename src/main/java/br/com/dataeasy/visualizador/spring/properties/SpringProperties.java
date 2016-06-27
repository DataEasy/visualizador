package br.com.dataeasy.visualizador.spring.properties;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.builder.EqualsBuilder;

import br.com.dataeasy.visualizador.VisualizadorInfraException;

/**
 * <b>Description:</b> <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author vinicius
 * @version Revision: $ Date: 24/06/2015
 */
@SuppressWarnings("serial")
public class SpringProperties extends Properties {

    private String arquivoGroupDocs;

    public String getArquivoGroupDocs() {
        return arquivoGroupDocs;
    }

    public void setArquivoGroupDocs(String arquivoGroupDocs) {
        this.arquivoGroupDocs = arquivoGroupDocs;
    }

    public void init() {
        try {
            InputStream gdProperties = Thread.currentThread().getContextClassLoader().getResourceAsStream(arquivoGroupDocs);
            this.load(gdProperties);
        } catch (Exception e) {
            throw new VisualizadorInfraException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SpringProperties)) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        SpringProperties outro = (SpringProperties) o;
        return new EqualsBuilder().append(arquivoGroupDocs, outro.arquivoGroupDocs).isEquals();
    }

    @Override
    public int hashCode() {
        int inicial = super.hashCode();
        return inicial + (arquivoGroupDocs == null ? 0 : arquivoGroupDocs.hashCode());
    }
}
