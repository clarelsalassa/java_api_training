package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Client {
    final HttpClient client;
    final Attack attack;
    final ArrayList<String> adversaryUrl;

    public Client() {
        attack = new Attack();
        client = createClient();
        adversaryUrl = new ArrayList<>(1);
    }

    private HttpClient createClient(){
        return HttpClient.newHttpClient();
    }

    public boolean createPostRequest(String adversaryUrl, String myPort){
        this.adversaryUrl.add(adversaryUrl);
        HttpRequest newRequest = HttpRequest.newBuilder().uri(URI.create(adversaryUrl + "/api/game/start")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + myPort + "\", \"message\":\"hello\"}"))
            .build();
        try {
            client.sendAsync(newRequest, HttpResponse.BodyHandlers.ofString()).thenAccept(r -> System.out.println(r.statusCode()));
            return true;
        }
        catch (Exception e) {
            System.err.println("Error when starting the game: " + e);
        }
        return false;
    }

    public boolean createGetRequest(){
        HttpRequest newRequest = HttpRequest.newBuilder()
            .uri(URI.create(adversaryUrl.get(0) + "/api/game/fire" + "?cell=" + attack.getAttack()))
            .setHeader("Accept", "application/json")
            .GET()
            .build();
        try {
            client.sendAsync(newRequest, HttpResponse.BodyHandlers.ofString()).thenAccept(r  -> System.out.println(r.statusCode()));
            return true;
        }
        catch(Exception exception) {
            System.err.println("Error when attacking: " + exception);
        }
        return false;
    }
}
