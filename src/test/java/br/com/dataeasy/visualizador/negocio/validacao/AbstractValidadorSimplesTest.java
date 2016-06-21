package br.com.dataeasy.visualizador.negocio.validacao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;

import br.com.dataeasy.visualizador.negocio.excecoes.NegocioException;
import br.com.dataeasy.visualizador.negocio.mensagens.Messages;
import br.com.dataeasy.visualizador.util.Constantes;

/**
 * <b>Description:</b>Classe base para testes unitários de Validadores.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2016 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 11/04/2016
 * @param <T>
 */
public abstract class AbstractValidadorSimplesTest<T extends ValidadorSimples<E>, E> extends AbstractValidadorTest<T> {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        instancia.adicionarRegras(); // Executado do PostConstructor
        Messages.loadPropertiesFileInMemory(Constantes.LOCALE_PADRAO);
    }

    protected final void validar(E objetoValidado) {
        replayAll();
        try {
            instancia.validar(objetoValidado);
        } finally {
            verifyAll();
        }
    }

    protected void assertMensagemExistente(NegocioException e, String chaveMensagem, String... parametros) {
        List<String> mensagens = e.getMensagens();

        String mensagem = getMensagem(chaveMensagem, parametros);
        Assert.assertTrue(mensagens.contains(mensagem));
    }

}
