package br.com.dataeasy.visualizador.servlets;

import static com.groupdocs.annotation.common.Utils.toJson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.groupdocs.annotation.domain.response.StatusResult;
import com.groupdocs.annotation.exception.AnnotationException;

import br.com.dataeasy.visualizador.media.MediaType;

/**
 * @author Aleksey Permyakov (16.07.14).
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet(name = "UploadFileServlet", urlPatterns = { "/document-annotation/UploadFileHandler/*" })
public class UploadFileServlet extends AnnotationServlet {
    private static final long                    serialVersionUID = 1L;
    private static final org.apache.log4j.Logger LOG              = org.apache.log4j.Logger.getLogger(UploadFileServlet.class);
    
    /**
     * GET request
     *
     * @param request object
     * @param response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }
    
    /**
     * POST request
     *
     * @param request object
     * @param response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String uploadFileName = request.getParameter("fileName");
        File tempFile = File.createTempFile("annotation-upload", "_" + uploadFileName);
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            IOUtils.copy(request.getInputStream(), outputStream);
        }
        try (InputStream uploadInputStream = new FileInputStream(tempFile)) {
            writeOutput(MediaType.APPLICATION_JSON, response, annotationHandler.uploadFileHandler(uploadFileName, uploadInputStream));
        } catch (AnnotationException e) {
            LOG.error(e.getMessage());
            writeOutput(MediaType.APPLICATION_JSON, response, toJson(new StatusResult(false, e.getMessage())));
        }
    }
}
