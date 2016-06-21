package br.com.dataeasy.visualizador.servlets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>Description:</b> <br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 17/08/2015
 */
public class ViewDocumentServletTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewDocumentServletTest.class);

    @Test
    @SuppressWarnings("PMD.MagicNumbers")
    public void test() {
        String regex = "File extension (.+?) of filename (.+?) cannot be opened, and looks to be corrupted."
                + " Please contact us via our support forums sharing the document for review.";
        String valor = "File extension pdf of filename 4564.pdf cannot be opened, and looks to be corrupted."
                + " Please contact us via our support forums sharing the document for review.";

        Matcher matcher = Pattern.compile(regex).matcher(valor);
        LOGGER.info(String.valueOf(matcher.find()));
        Assert.assertEquals("pdf", matcher.group(1));
        Assert.assertEquals("4564.pdf", matcher.group(2));
    }

    @Test
    @Ignore
    public void testDoGet() {
        // testa execução do doGet()
    }

    @Test
    @Ignore
    public void doPost() {
        // testa execução do doPost() com sucesso
    }

    @Test
    @Ignore
    public void doPostSemSucessoQuandoReasonDoGroupDocsENula() {
        // testa execução do doPost() quando o GroupDocs cria JSON com 'reason' nula
    }

    @Test
    @Ignore
    public void doPostSemSucessoQuandoReasonNaoPossuiExcecaoProjetoCorrespondente() {
        // testa execução do doPost() quando o GroupDocs cria JSON com 'reason' para a qual não há exceção correspondente no projeto cliente chamador
    }
}
