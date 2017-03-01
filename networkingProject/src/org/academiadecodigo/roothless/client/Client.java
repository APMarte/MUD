package org.academiadecodigo.roothless.client;

import org.academiadecodigo.roothless.server.Game;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by apm on 27-02-2017.
 */
public class Client {

    private Socket socket;
    private Scanner scanner;
    private DataOutputStream out;

    private void connect() throws IOException {
        scanner = new Scanner(System.in);
        System.out.println("Host:");
        String host = scanner.nextLine();
        System.out.println("Port:");
        int port = Integer.parseInt(scanner.nextLine());

        socket = new Socket(host, port);

        Thread thread = new Thread(new ServerListener(new BufferedReader(new InputStreamReader(socket.getInputStream()))));
        thread.start();
    }

    public Socket getSocket() {
        return socket;
    }

    private void sendMessage() {

        while (!socket.isClosed()) {
            try {
                out = new DataOutputStream(socket.getOutputStream());
                String outputMSG = scanner.nextLine() + "\n";
                if (socket.isClosed()) {
                    break;
                }
                out.write(outputMSG.getBytes());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();

        try {
            client.connect();
            client.sendMessage();

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

    private class ServerListener implements Runnable {

        private BufferedReader in;

        public ServerListener(BufferedReader in) {
            this.in = in;
        }

        @Override
        public void run() {

            while (!socket.isClosed()) {
                System.out.println(Thread.currentThread());
                try {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String serverMSG = in.readLine();
                    if (serverMSG == null) {
                        System.out.println("Server connection is closed.");
                        socket.close();
                        break;
                    }
                    System.out.println(serverMSG);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
