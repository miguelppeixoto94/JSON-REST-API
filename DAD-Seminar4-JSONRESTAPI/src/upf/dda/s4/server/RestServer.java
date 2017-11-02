package upf.dda.s4.server;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.net.httpserver.HttpServer;

public class RestServer {

	public static void main(String[] args) throws IOException {
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(15000).build();
		ResourceConfig config = new ResourceConfig(Services.class);
		HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
		System.out.println("Server started...");
	}
	
}