# Visualizador

Serviço de visualização de documentos e arquivos em navegador web.

Baseado na biblioteca [GroupDocs.Annotations](http://www.groupdocs.com/java/document-annotation-library), suporta [vários tipos de arquivo](http://www.groupdocs.com/java/document-annotation-library/features) para visualização e cache de páginas e miniaturas.

### Informações sobre geração do WAR

O arquivo de licença do GroupDocs é inserido no WAR durante sua geração pelo Maven. Já existe um caminho padrão definido no projeto. Se quiser que este arquivo seja copiado de um local especifico, defina o valor do atributo `diretorio.licenca.groupdocs` durante a geração do Maven. Ex.:

* `mvn package -Ddiretorio.licenca.groupdocs=c:\\projeto\\groupdocs`
* `mvn package -Ddiretorio.licenca.groupdocs=/opt/licencas/groupdocs`