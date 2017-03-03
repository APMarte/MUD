package org.academiadecodigo.roothless.server;

import org.academiadecodigo.roothless.client.player.Player;
import org.academiadecodigo.roothless.client.player.PlayerType;
import org.academiadecodigo.roothless.serverParser.ServerParser;

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


    //private CopyOnWriteArrayList<Socket> socketList = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<ClientHandler> clientHandlersList = new CopyOnWriteArrayList<>();
    private ServerSocket serverSocket = null;
    private int counter = 1;
    private BufferedReader in;
    private Game game;
    private boolean listFull; //will be true when all player are in


    private void listen() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Port number:");

        serverSocket = new java.net.ServerSocket(Integer.parseInt(scanner.nextLine()));

        ExecutorService pool = Executors.newFixedThreadPool(5);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("connecting a client...");
            String name = in.readLine();


            ClientHandler clientHandler = new ClientHandler(name.split(" ")[0], name.split(" ")[1],clientSocket); //TODO: change the arguments
            clientHandlersList.add(clientHandler); //não passar classe e sempre que alguem falar no chat ou
            pool.submit(clientHandler);             ///w contruir string para mostrar class e name ou
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

    private void gameStart() {
        game = new Game();

        /*while(true) {                               //method to turn the game off // TODO: 03/03/17 implement things to turn everything off?
            if(!(game.isGameOn()) && listFull) {
                break; }
        }*/

        while (true) {                                    //method to start the game as soon as all the 5 players are on and their names and classes defined (it sets the gameon to true)
            if (game.canIStart(listFull)) {
                break;
            }
        }
    }


    public static void main(String[] args) {

        Server server = new Server();
        try {
            server.listen();
            if (server.clientHandlersList.size() == 5) {
                server.gameStart();
            }
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

    private class ClientHandler implements Runnable {

        private Socket clientSocket;
        private BufferedReader in;
        private String inputMSG;
        private String playerName;
        ServerParser parser;

        public ClientHandler(String playerName, String playerType, Socket clientSocket) {
            this.playerName = playerName;
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

            while (!clientSocket.isClosed()) {

                try {

                    parser = new ServerParser();
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    inputMSG = in.readLine();

                    System.out.println(inputMSG);

                    if (inputMSG != null && inputMSG.split(" ")[0].charAt(0) != '/') {
                        broadcast();

                    } else if (inputMSG != null) {

                        String result = parser.parseCommand(inputMSG);
                        System.out.println(result);

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

                String author = getPlayerName() + ": ";
                String message = author + inputMSG + "\n";

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
