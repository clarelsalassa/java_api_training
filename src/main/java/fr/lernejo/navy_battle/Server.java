package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


public class Server {
    final int port;
    final Parser parser;
    final Sea sea;
    final Client client;

    public Server(int portNumber) {
        port = portNumber;
        parser = new Parser();
        client = new Client();
        sea = new Sea();
    }

    public HttpServer initServer() {
        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(port), 1);
            return server;
        }
        catch (IOException e) {
            System.err.println("Cannot create the server: " + e);
        }
        return null;
    }

    public boolean runServer() throws IOException {
        HttpServer server = initServer();
        if (server == null){
            return false;
        }
        server.setExecutor(Executors.newFixedThreadPool(1));
        new PingHandler(this).pingContext(server);
        new PostHandler(this).postContext(server);
        new GetHandler(this).getContext(server);

        server.start();
        return true;
    }

    public void response(String body, HttpExchange exchange, int resp) {
        try {
            exchange.sendResponseHeaders(resp, body.length());
        }
        catch (IOException e){
            System.err.println("Header problem: " + e);
        }
        try (OutputStream os = exchange.getResponseBody()){
            os.write(body.getBytes());
        }
        catch (IOException e) {
            System.err.println("Message problem: " + e);
        }
    }
}
