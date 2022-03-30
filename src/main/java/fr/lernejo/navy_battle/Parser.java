package fr.lernejo.navy_battle;


import com.sun.net.httpserver.HttpExchange;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Parser {

    public boolean isValidBody(JSONObject jsonObject) {
        try (InputStream inputStream = getClass().getResourceAsStream("/schemaPost.json")) {
            JSONObject inputSchema = new JSONObject(new JSONTokener(inputStream));
            Schema schema = SchemaLoader.load(inputSchema);
            try {
                schema.validate(jsonObject);
                return true;
            }
            catch (ValidationException e) {
                System.err.println("Invalid schema" + e);
            }
        }
        catch (IOException e) {
            System.err.println("No schema: " +  e);
        }
        return false;
    }

    public JSONObject getRequest(HttpExchange exchange){
        try (InputStream os = exchange.getRequestBody()){
            BufferedReader rStream = new BufferedReader(new InputStreamReader(os, StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            String input;
            while ((input = rStream.readLine()) != null)
                stringBuilder.append(input);
            return new JSONObject(stringBuilder.toString());
        }
        catch (IOException e){
            return null;
        }
    }


}
