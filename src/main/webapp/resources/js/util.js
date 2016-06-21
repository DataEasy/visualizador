/**
 * Retorna a URL base da aplicação. Ex.: http://localhost:8080/ ou http://www.dataeasy.com.br/.
 */
function getBaseUrl() {
    var href = window.location.href.split('/');
    return href[0] + '//' + href[2];
}

/**
 * Retorna o caminho do contexto da aplicação. Ex.: visualizador.
 */
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}
