package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Launcher {
    public static void main(String[] args) throws IOException {

        System.out.println("Aller c'est parti mon gars!");

        HttpServer server = new Server().createServer();
        server.start();


    }
}
