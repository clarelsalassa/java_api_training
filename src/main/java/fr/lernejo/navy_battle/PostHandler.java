package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import com.sun.net.httpserver.HttpServer;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PostHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String bodyPass = "Accepted";
        String bodyBad = "Bad request";
        InputStream stream = exchange.getRequestBody();
        JSONObject inSchema = new JSONObject(new JSONTokener(stream));

        try (InputStream inputStream = getClass().getResourceAsStream("/schemaPost.json")) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(rawSchema);
            schema.validate(inSchema);
            exchange.getResponseHeaders().set("Accept", "application/json");
            exchange.sendResponseHeaders(202, bodyPass.length());

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bodyPass.getBytes());
            }
        } catch (ValidationException e) {

            exchange.sendResponseHeaders(400, bodyBad.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bodyBad.getBytes());
            }
        }
    }
}
