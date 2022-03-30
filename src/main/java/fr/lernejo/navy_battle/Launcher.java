package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length >= 1) {
            int portNumber = Integer.parseInt(args[0]);
            if (portNumber < 1024 || portNumber > 65535){
                System.err.println("Enter port number between 1024 and 65535:");
                return;
            }
            Server newServer = new Server(portNumber);
            newServer.runServer();
            if (args.length == 2){
                newServer.client.createPostRequest(args[1], args[0]);
            }
        }
        else
            System.err.println("No argument, program needs 1 or 2 arguments to be launched");

    }
}
