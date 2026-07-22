package com.footdablit2310.footdiscordmc;

import com.sun.net.httpserver.HttpsServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public final class FootHttpListener {

    private static HttpsServer server;

    public static void start(int port, SSLContext sslContext) throws IOException {
        server = HttpsServer.create(new InetSocketAddress(port), 0);
        server.setHttpsConfigurator(new com.sun.net.httpserver.HttpsConfigurator(sslContext));

        // Register POST endpoint
        server.createContext("/command", new CommandHandler());

        server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
        server.start();

        System.out.println("[FootDiscordMC] HTTPS listener started on port " + port);
    }

    public static void stop() {
        if (server != null) {
            server.stop(0);
        }
    }

    static class CommandHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                String body = new String(exchange.getRequestBody().readAllBytes());

                // Add to local processing database
                FootProcessingDB.add(body);

                String response = "{\"status\":\"queued\"}";
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }
}
