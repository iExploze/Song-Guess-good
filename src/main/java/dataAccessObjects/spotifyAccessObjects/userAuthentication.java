package dataAccessObjects.spotifyAccessObjects;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class userAuthentication {

    String url;
    String response;
    public userAuthentication(String url) throws IOException {
        this.url = url;
        this.openWebPage(url);
    }

    public void setResponse(String response) {
        this.response=response;
    }
    public String getResponse() {return this.response;}
    private void openWebPage(String url) throws IOException {
        startServer(this);
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
                int i = 0;
                while (this.getResponse() == null) {
                    Thread.sleep(1000);
                    i += 1;
                    if (i == 7) {
                        System.out.println("Authentication Could Not be Completed. Try Again!");
                        System.exit(0);
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("Desktop is not supported. Cannot open the webpage.");
        }
    }

    private static void startServer(userAuthentication userAuthentication) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/callback", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String query = exchange.getRequestURI().getQuery();
                userAuthentication.setResponse(query);
                // Here, 'query' will contain the query part of the redirected URL.
                // You can parse it to get the information you need.

                String response = "Received!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();


            }
        });
        server.start();

    }

}
