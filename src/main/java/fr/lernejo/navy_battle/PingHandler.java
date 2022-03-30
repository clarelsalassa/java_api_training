package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;

public class PingHandler {
    private final Server server;

    public PingHandler(Server server){
        this.server = server;
    }

    public void pingContext(HttpServer http) {
        http.createContext("/ping", exchange -> server.response("OK", exchange, 200));
    }

}
