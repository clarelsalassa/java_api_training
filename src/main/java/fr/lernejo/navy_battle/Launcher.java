package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Launcher {
    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length >= 1) {
            int port = Integer.parseInt(args[0]);
            HttpServer server = new Server().createServer(port);
            server.start();
            if (args.length == 2){
                HttpClient client = new Client().createClient();
                HttpRequest request = new Client().createRequest(args[1], args[0]);
                client.send(request, HttpResponse.BodyHandlers.ofString());
            }
        }
        else
            System.out.println("No argument");
    }
}
