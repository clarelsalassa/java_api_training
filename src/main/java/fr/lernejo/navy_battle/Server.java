package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public HttpServer createServer(int port) throws IOException {
        if (port < 1024 || port > 65535){
            System.out.println("That port number is invalid");
            System.out.println("Enter a port number between 1024 and 65535:");
        }
        // Create server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        server.setExecutor(executor);
        server.createContext("/ping", new PingHandler());
        server.createContext("/api/game/start", new PostHandler());

        return server;
    }
}
