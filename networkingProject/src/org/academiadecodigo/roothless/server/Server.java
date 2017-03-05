package org.academiadecodigo.roothless.server;


import org.academiadecodigo.roothless.serverParser.ServerParser;
import org.academiadecodigo.roothless.serverParser.Strategy;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

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
    private LinkedBlockingQueue<Strategy> queue = new LinkedBlockingQueue<Strategy>();
    private Dungeon dungeon = new Dungeon(queue);


    public CopyOnWriteArrayList<ClientHandler> getClientHandlersList() {
        return clientHandlersList;
    }

    private List<String> classesChosen = new LinkedList<>();

    private void listen() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Port number:");

        serverSocket = new ServerSocket(8080);//Integer.parseInt(scanner.nextLine()));

        ExecutorService pool = Executors.newFixedThreadPool(5);

        while (counter < 5) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandlersList.add(clientHandler);
            pool.submit(clientHandler);
            counter++;
        }

        dungeon.enterDungeon();
        System.out.println("left the dungeon");


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

        public String getPlayerType() {
            return playerType;
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
                        chatBroadcast();

                    } else if (inputMSG != null && inputMSG.split(" ")[0].equals("/w")) {
                        whispering();

                    } else if (inputMSG != null) {
                        queue.add(ServerParser.parseCommand(inputMSG, dungeon));
                        inputMSG = dungeon.checkActions();
                        systemBroadcast();

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

        private void chatBroadcast() {

            try {
                    String author = getPlayerName() + " <" + getPlayerType() + ">: ";
                    String message = author + inputMSG + "\n";

                    for (ClientHandler c : clientHandlersList) { //TODO
                        OutputStream out = c.getClientSocket().getOutputStream();
                        out.write(message.getBytes());
                        out.flush();
                    }

                }catch(IOException e){
                    e.printStackTrace();
                }


        }

        private void systemBroadcast() {

            try {
                String message = inputMSG + "\n"; //.split("[|]")[0] + "\n";
                //String message2 = inputMSG.split("[|]")[1] + "\n";

                for (ClientHandler c : clientHandlersList) { //TODO
                    OutputStream out = c.getClientSocket().getOutputStream();
                    out.write(message.getBytes());
                    out.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println("oix");
            //dungeon.setCountAction(dungeon.getCountAction() + 1);
        }

        private void whispering() {

            try {
                String nameClient = inputMSG.split(" ")[1];

                String message = getPlayerName() + " <" + getPlayerType() +"> whispered: " + inputMSG.substring(nameClient.length()+4) + "\n";
                System.out.println(message);

                for (ClientHandler c : clientHandlersList) {
                    if(c.getPlayerName().equals(nameClient)) {//TODO
                        OutputStream out = c.getClientSocket().getOutputStream();
                        out.write(message.getBytes());
                        out.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
