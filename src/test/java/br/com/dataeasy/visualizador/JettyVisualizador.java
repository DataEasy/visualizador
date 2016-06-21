package br.com.dataeasy.visualizador;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.JettyWebXmlConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Classe standalone para início do Jetty Server em ambiente local/desenvolvimento. Use o parâmetro -Dcom.sun.management.jmxremote para iniciar o JMX
 * (e, por exemplo, conectar com o jconsole).
 */
@SuppressWarnings("PMD")
public class JettyVisualizador {

    private static final String CAMINHO_CONTEXTO = "/visualizador";
    private static final int PORTA = 8090;

    /**
     * Função principal. Inicia o jetty server.
     *
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("wicket.configuration", "development");

        Server server = new Server();

        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSecureScheme("https");
        httpConfig.setSecurePort(8443);
        httpConfig.setOutputBufferSize(32768);

        ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
        http.setPort(PORTA);
        http.setIdleTimeout(1000 * 60 * 60);

        server.addConnector(http);

        WebAppContext webApp = new WebAppContext();
        webApp.setServer(server);
        webApp.setContextPath(CAMINHO_CONTEXTO);
        webApp.setWar("src/main/webapp");
        webApp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/classes/.*");
        webApp.setOverrideDescriptor("/jetty-web.xml");

        server.setHandler(webApp);

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
        server.addEventListener(mBeanContainer);
        server.addBean(mBeanContainer);

        Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        classlist.addBefore(JettyWebXmlConfiguration.class.getName(), AnnotationConfiguration.class.getName());

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(100);
        }
    }

}
