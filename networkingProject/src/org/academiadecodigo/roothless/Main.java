package org.academiadecodigo.roothless;

import org.academiadecodigo.roothless.client.Client;
import org.academiadecodigo.roothless.server.Server;

import java.io.IOException;

/**
 * Created by codecadet on 07/03/17.
 */
public class Main {

    public static void main(String[] args) {

        if (args[0].equals("server")) {
            Server server = new Server();

            try {
                server.listen();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (server.getServerSocket() != null) {
                    try {
                        server.getServerSocket().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            Client client = new Client();

            try {
                client.connect();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (client.getSocket() != null) {
                    try {
                        client.getSocket().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
