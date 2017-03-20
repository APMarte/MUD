package org.academiadecodigo.roothless;

import org.academiadecodigo.roothless.client.Client;
import org.academiadecodigo.roothless.server.Server;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by codecadet on 07/03/17.
 */

public class Main {

    public static void main(String[] args) {

        Server server = null;
        Client client = null;
        Scanner scan = new Scanner(System.in);
        String choice;
        do {
            System.out.println("Type either client or server");
            choice = scan.nextLine();
        }while (!choice.equals("server") && !choice.equals("client"));

        try {
            if (choice.equals("server")) {
                server = new Server();
                server.listen();

            } else if (choice.equals("client")) {
                client = new Client();
                client.connect();


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (server != null) {
                    server.getServerSocket().close();
                }
                if (client != null) {
                    client.getSocket().close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


