package br.com.dataeasy.visualizador.wicket;

import org.junit.Ignore;
import org.junit.Test;

/**
 * <b>Description:</b>Teste unitário da utilização do painel do visualizador.<br>
 * <b>Project:</b> visualizador <br>
 * <b>Company:</b> DataEasy Consultoria e Informática LTDA. <br>
 *
 * Copyright (c) 2015 DataEasy - Todos os direitos reservados.
 *
 * @author rafael.fontoura
 * @version Revision: $ Date: 24/08/2015
 */
@Ignore
public class VisualizadorPanelTest extends WicketTestComponentBase<VisualizadorPanel> {

    @Test
    public void testExibirTodosOsScriptsNecessarios() {
        // teste de caminho feliz
        // testar renderização de todos os JS e CSS minimamente necessários para exibição do visualizador
    }

    @Test
    public void testUtilizarCssDeCorrecoesAoUtilizarVisualizadorEmbutidoNaPagina() {
        // teste de caminho feliz
        // testa utilização do fixes-panel.css quando foi definido que AbstractWebPage.utilizandoVisualizadorNovaJanela() retorna false. Ou seja,
        // usuário está utilizando o VisualizadorPanel embutido na página.
    }

    @Test
    public void testVisualizarComProblemaAoPegarIdDeArquivo() {
        // testa envio de mensagem de erro ao visualizador ao tentar recuperar ID de imagem
    }
}
