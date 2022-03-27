package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public HttpServer createServer() throws IOException {
        // Scan input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a port number (between 1024 and 65535):");
        int port;

        // Validate the input
        while (true){
            if (!scanner.hasNextInt()){
                System.out.println("Strings not allowed");
                System.out.println("Please enter an integer between 1024 and 65535");
                scanner.next();
                continue;
            }
            port = scanner.nextInt();

            if (port < 1024 || port > 65535){
                System.out.println("That port number is invalid");
                System.out.println("Enter a port number between 1024 and 65535:");
            }
            else break;
        }

        // Create server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        server.setExecutor(executor);
        server.createContext("/ping", new MyHandler());

        return server;
    }
}
