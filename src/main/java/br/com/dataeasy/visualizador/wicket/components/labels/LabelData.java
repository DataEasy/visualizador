package br.com.dataeasy.visualizador.wicket.components.labels;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.wicket.markup.html.basic.Label;

/**
 * <b>Description:</b> Componente label para propriedades do tipo {@link LocalDate} <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 *    Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author iuri.pereira
 * @version Revision: $ Date: Jul 28, 2015
 */
@SuppressWarnings("serial")
public class LabelData extends Label {

    private static DateTimeFormatter formatadorData     = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter formatadorDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public LabelData(String id, Object data) {
        super(id, data == null ? null : getDataFormatada(data));
        setOutputMarkupId(true);
    }

    private static String getDataFormatada(Object data) {
        String dataFormatada = "";
        if (data instanceof LocalDate) {
            dataFormatada = formatadorData.format((LocalDate) data);
        } else if (data instanceof LocalDateTime) {
            dataFormatada = formatadorDataHora.format((LocalDateTime) data);
        }
        return dataFormatada;
    }

}
