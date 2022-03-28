package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class Client {

    public HttpClient createClient(){
        return HttpClient.newHttpClient();
    }

    public HttpRequest createRequest(String adversaryUrl, String myPort){
        return HttpRequest.newBuilder()
            .uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + myPort + "\", \"message\":\"hello\"}"))
            .build();
    }
}
