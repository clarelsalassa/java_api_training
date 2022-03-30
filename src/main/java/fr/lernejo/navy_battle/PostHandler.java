package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;

public class PostHandler implements HttpHandler {

    private final Server server;

    public PostHandler(Server server) {
        this.server = server;
    }

    public void postContext(HttpServer httpServer) {
        httpServer.createContext("/api/game/start", exchange -> {
            if (!"POST".equals(exchange.getRequestMethod())) {
                server.response("Bad POST Method", exchange, 404);
                return;
            }
            handle(exchange);
        });
    }
    @Override
    public void handle(HttpExchange exchange) {
        JSONObject jsonRequest = server.parser.getRequest(exchange);
        if (server.parser.isValidBody(jsonRequest)) {
            server.response("{\"id\": \"" + server.port + "\",\"url\": \"http://localhost:" +
                server.port + "\",\"message\": \"May the best code win\"}", exchange, 202);
            String adversaryUrl = jsonRequest.getString("url");
            server.client.adversaryUrl.add(adversaryUrl);
            server.client.createGetRequest();
        }
        else
            server.response("Bad request", exchange, 400);
    }
}
