package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.URI;

public class GetHandler implements HttpHandler {
    private final Server server;

    public GetHandler(Server server) {
        this.server = server;
    }

    public void getContext(HttpServer http){
        http.createContext("/api/game/fire", exchange -> {
            if (!"GET".equals(exchange.getRequestMethod())) {
                server.response("Bad POST Method", exchange, 404);
                return;
            }
            handle(exchange);
        });
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        URI requestURI = exchange.getRequestURI();
        String boardCell = requestURI.toString().split("=")[1];
        String results = server.sea.touchedBoardCell(boardCell).stateOfShips();
        boolean shipsLeft = server.sea.isLeft();
        server.response("{\"consequence\": \"" + results + "\", \"shipLeft\": " + shipsLeft + "}", exchange, 200);
        if (shipsLeft) {
            server.client.createGetRequest();
        }
    }
}
