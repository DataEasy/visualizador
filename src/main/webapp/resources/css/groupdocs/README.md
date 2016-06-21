# Por que pastas 'fonts' e 'images' dentro deste diretório?

* Os diretórios 'fonts' e 'images' são necessários EXATAMENTE aqui, pois os arquivos CSS do GroupDocs necessitam deles nestes locais.
* Modificar os arquivos CSS para apontarem para outro caminho **NÃO É UMA OPÇÃO**, já que isso prejudica manter o GroupDocs atualizado. Se atualizarmos um CSS, por exemplo, teremos que atualizar novamente os caminhos que apontam para fontes e imagens que foram mudados de lugar em outro momento.