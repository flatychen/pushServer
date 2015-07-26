package cn.flaty;

import java.net.URL;
import java.security.ProtectionDomain;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * jetty 启动
 * 
 * @author flatychen
 * @date 2015-5-6
 * @version
 */
public class StartServer {

	/**
	 * 工程名称
	 */
	private static String projectName = "push";

	/**
	 * 
	 */
	private static int port = 8080;

	public static void main(String[] args) {

//		 设定Spring的profile
		 Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);

		JettyServer server = new JettyServer(projectName, port);
		try {
			server.startUp();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}

	private static class JettyServer {

		private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";
		private Server server;
		private WebAppContext context;

		public JettyServer(String contextPath, int port) {
			super();

			System.out.println("[INFO] Server running at http://localhost:"
					+ port + "/" + projectName);
			System.out
					.println("[HINT] Hit Enter to reload the application quickly");

			this.initServer(port);
			this.initContext(contextPath);
			server.setHandler(context);
		}

		private void initServer(int port) {
			server = new Server(port);
			server.setStopAtShutdown(true);
			SelectChannelConnector connector = new SelectChannelConnector();
			connector.setPort(port);
			connector.setReuseAddress(false);
			server.setConnectors(new Connector[] { connector });
		}

		private void initContext(String contextPath) {
			// 设置 webapp目录
			ProtectionDomain protectionDomain = StartServer.class
					.getProtectionDomain();
			URL location = protectionDomain.getCodeSource().getLocation();
			WebAppContext context = new WebAppContext(DEFAULT_WEBAPP_PATH, "/"
					+ contextPath);
			this.context = context;
		}

		private void reloadContext() throws Exception {
			WebAppContext context = (WebAppContext) server.getHandler();
			System.out.println("[INFO] Application reloading");
			context.stop();
			WebAppClassLoader classLoader = new WebAppClassLoader(context);
			classLoader.addClassPath("target/classes");
			classLoader.addClassPath("target/test-classes");
			context.setClassLoader(classLoader);
			context.start();
			System.out.println("[INFO] Application reloaded");
		}

		public void startUp() throws Exception {
			server.start();
			watchReload();
		}

		private void watchReload() throws Exception {
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					reloadContext();
				}
			}
		}

	}

}
