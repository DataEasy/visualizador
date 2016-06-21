package br.com.dataeasy.visualizador.servlets;

import static com.groupdocs.annotation.common.Utils.toJson;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

import br.com.dataeasy.visualizador.config.ApplicationConfig;
import br.com.dataeasy.visualizador.config.VisualizadorConfig;
import br.com.dataeasy.visualizador.media.MediaType;
import br.com.dataeasy.visualizador.spring.context.SpringAppContext;

import com.groupdocs.annotation.common.Utils;
import com.groupdocs.annotation.domain.response.StatusResult;
import com.groupdocs.annotation.exception.AnnotationException;
import com.groupdocs.annotation.handler.AnnotationHandler;

/**
 * @author imy
 */
public abstract class AnnotationServlet extends HttpServlet {
    private static final long     serialVersionUID       = 1L;
    protected static final String MESSAGE_HANDLER_THROWS = "Handler throws exception: {0}";
    protected static final String DEFAULT_ENCODING       = "UTF-8";
    protected VisualizadorConfig  visualizadorConfig;
    protected AnnotationHandler   annotationHandler;

    /**
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        visualizadorConfig = ContextLoader.getCurrentWebApplicationContext().getBean(VisualizadorConfig.class);
        this.annotationHandler = visualizadorConfig.getAnnotationHandler();
    }

    protected void writeOutput(MediaType contentType, HttpServletResponse response, Object object) throws IOException {
        String json = (String) object;
        if (contentType != null && !contentType.toString().isEmpty()) {
            response.setContentType(contentType.toString());
        }
        response.getOutputStream().write(json.getBytes(DEFAULT_ENCODING));
    }

    protected void writeOutput(InputStream inputStream, HttpServletResponse response) throws IOException {
        if (inputStream == null) {
            Logger.getLogger(this.getClass()).error("inputStream is null");
        }
        IOUtils.copy(inputStream, response.getOutputStream());
        Utils.closeStreams(inputStream, response.getOutputStream());
    }

    protected void tratarAnnotationExceptionJson(HttpServletResponse response, AnnotationException e) throws IOException {
        java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, MESSAGE_HANDLER_THROWS, e.getMessage());
        writeOutput(MediaType.APPLICATION_JSON, response, toJson(new StatusResult(false, e.getMessage())));
    }

    protected void configurarCaminhoDaApp(HttpServletRequest request) {
        ApplicationConfig applicationConfig = SpringAppContext.getBean(ApplicationConfig.class);
        String path = applicationConfig.getApplicationPath();
        if (StringUtils.isBlank(path) || "null".equalsIgnoreCase(path)) {
            String porta = ":" + request.getServerPort();
            if (":80".equals(porta)) {
                porta = "";
            }
            String url = request.getScheme() + "://" + request.getServerName() + porta + request.getContextPath();
            applicationConfig.setApplicationPath(url);
        }
    }
}
