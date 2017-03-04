package org.academiadecodigo.roothless.server;


import org.academiadecodigo.roothless.serverParser.ServerParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by apm on 27-02-2017.
 */

public class Server {

    public static void main(String[] args) {


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

    }

    private CopyOnWriteArrayList<ClientHandler> clientHandlersList = new CopyOnWriteArrayList<>();
    private ServerSocket serverSocket = null;
    private int counter = 0;
    private BufferedReader in;
    private Dungeon dungeon = new Dungeon();

    public CopyOnWriteArrayList<ClientHandler> getClientHandlersList() {
        return clientHandlersList;
    }

    private List<String> classesChosen = new LinkedList<>();

    private void listen() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Port number:");

        serverSocket = new java.net.ServerSocket(Integer.parseInt(scanner.nextLine()));

        ExecutorService pool = Executors.newFixedThreadPool(5);

        while (counter < 5) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandlersList.add(clientHandler);
            pool.submit(clientHandler);
            counter++;
        }

        //dungeon.enterDungeon(); //todo is it blocking?


    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    private String idGenerator() {

        String clientID = "Client-" + (counter+1);


        return clientID;
    }


    private class ClientHandler implements Runnable {

        private Socket clientSocket;
        private BufferedReader in;
        private String inputMSG;
        private String playerName;
        private String playerType;


        public ClientHandler(Socket clientSocket) {

            this.clientSocket = clientSocket;

        }

        public Socket getClientSocket() {
            return clientSocket;
        }

        public String getPlayerName() {
            return playerName;
        }


        @Override
        public void run() {

            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("connecting a client...");

               /* do {*/
                    String name = in.readLine();

                    playerName = name.split(" ")[0];
                    playerType = name.split(" ")[1];
                    classesChosen.add(playerType);


                    /////////
                    //try {

                        /*String classCheck = in.readLine();
                        playerType = classCheck;

                        System.out.println(playerType);

                        out.write("Escolhe essa merda" + "\n");
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } while (!classesChosen.contains(playerType));
                try {
                    out.write("OK" + "\n");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
/////////////////////////////////////
            } catch (IOException e) {
                e.printStackTrace();
            }


            while (!clientSocket.isClosed()) {

                try {

                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    inputMSG = in.readLine();

                    System.out.println(inputMSG);

                    if (inputMSG != null && inputMSG.split(" ")[0].charAt(0) != '/') {
                        broadcast();

                    } else if (inputMSG != null) {

                        String result = ServerParser.parseCommand(inputMSG);


                    } else {
                        clientSocket.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            clientHandlersList.remove(this); //TODO
            System.out.println(clientHandlersList.size());
        }

        private void broadcast() {

            try {


                String message = getPlayerName() +" says: "+ inputMSG + "\n";

                for (ClientHandler c : clientHandlersList) { //TODO
                    OutputStream out = c.getClientSocket().getOutputStream();
                    out.write(message.getBytes());
                    out.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
