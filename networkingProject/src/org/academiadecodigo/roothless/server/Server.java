package org.academiadecodigo.roothless.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by apm on 27-02-2017.
 */
public class Server {


    private CopyOnWriteArrayList<Socket> socketList = new CopyOnWriteArrayList<>();
    private ServerSocket serverSocket = null;
    private int counter = 1;

    private void listen() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Port number:");

        serverSocket = new java.net.ServerSocket(Integer.parseInt(scanner.nextLine()));

        ExecutorService pool = Executors.newFixedThreadPool(5);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("connecting a client...");
            socketList.add(clientSocket);
            pool.submit(new ClientHandler(clientSocket));
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    private String idGenerator() {

        String clientID = "Client-" + counter;
        counter++;

        return clientID;
    }


    public static void main(String[] args) {

        Server server = new Server();
        try {
            server.listen();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server.getServerSocket()!= null) {
                try {
                    server.getServerSocket().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private class ClientHandler implements Runnable {

        private Socket clientSocket;
        private BufferedReader in;
        private String inputMSG;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;

        }

        @Override
        public void run() {

            while (!clientSocket.isClosed()) {

                try {

                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    inputMSG = in.readLine();

                    if (inputMSG != null) {
                        broadcast();
                    } else {
                        clientSocket.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socketList.remove(clientSocket);
            System.out.println(socketList.size());
        }

        private void broadcast() {

            try {

                String author = Thread.currentThread().getName() + ": ";
                String message = author + inputMSG + "\n";

                for (Socket s : socketList) {
                    OutputStream out = s.getOutputStream();
                    out.write(message.getBytes());
                    out.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
