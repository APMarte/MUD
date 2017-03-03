package org.academiadecodigo.roothless.client;

import org.academiadecodigo.roothless.client.player.Player;
import org.academiadecodigo.roothless.client.player.PlayerFactory;
import org.academiadecodigo.roothless.client.player.PlayerType;

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
    private BufferedReader br;
    private Player player;


    private void connect() throws IOException {


        scanner = new Scanner(System.in);
        System.out.println("Host:");
        String host = scanner.nextLine();
        System.out.println("Port:");
        int port = Integer.parseInt(scanner.nextLine());

        socket = new Socket(host, port);
        out = new DataOutputStream(socket.getOutputStream());

        createPlayer();

        Thread thread = new Thread(new ServerListener(new BufferedReader(new InputStreamReader(socket.getInputStream()))));
        thread.start();
    }

    public Socket getSocket() {
        return socket;
    }

    private void sendMessage() {

        while (!socket.isClosed()) {
            try {

                if (socket.isClosed()) {
                    break;
                }

                //PARSE DO CLIENT /A 2 50
                //comparar com hasacted
                System.out.println(player.getName() + " choose your action: ");
                String outputMSG = scanner.nextLine();
                parseClient(outputMSG);

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

    public void createPlayer() {

        int numClass = 0;
        br = new BufferedReader(new InputStreamReader(System.in)); // vai fazer de Scanner

        System.out.println("Insert username: "); // aceita se o utilizador nao introduzir nada
        String name = scanner.nextLine();

        try {
            out.write((name+"\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        do {
            System.out.println("Chose your Class: \n 1- ARCHER  2-PALADIN   3-PRIEST    4-SORCERER  5-THIEF \n");
            try {
                numClass = Integer.parseInt(br.readLine());
                chosePlayerType(numClass, name);
            } catch (NumberFormatException e) {
                System.out.println("Invalid operation!");
                e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (numClass < 1 || numClass > 5);

        System.out.println(player.toString());

    }

    private void chosePlayerType(int numClass, String name) {

        if (numClass == 1) {
            player = PlayerFactory.getNewPlayer(name, PlayerType.ARCHER);
        } else if (numClass == 2) {
            player = PlayerFactory.getNewPlayer(name, PlayerType.PALADIN);
        } else if (numClass == 3) {
            player = PlayerFactory.getNewPlayer(name, PlayerType.PRIEST);
        } else if (numClass == 4) {
            player = PlayerFactory.getNewPlayer(name, PlayerType.SORCERER);
        } else if (numClass == 5) {
            player = PlayerFactory.getNewPlayer(name, PlayerType.THIEF);
        } else {
            System.out.println("Invalid operation!");
        }

    }


    private void parseClient(String message) throws IOException {

        String command = message.split(" ")[0];

        String str;

        if (command.charAt(0) != '/') {         //compare first char from command
            out.write(message.getBytes());
            out.flush();
        }else {
            switch (command) {
                case "/a":
                    if (!player.getHasActed()) {
                        str = message + player.getBaseDamage() + "\n";
                        out.write(str.getBytes());
                        player.setHasActed(true);
                    } else{
                        System.out.println("Wait for your turn");
                    }
                        break;
                case "/d":
                    if(!player.getHasActed()){
                    out.write((message+"\n").getBytes());
                    player.setHasActed(true);
                    }else{
                        System.out.println("Wait for your turn");
                    }
                    break;
                case "/pick":
                    if(!player.getHasActed()) {
                        out.write((message+"\n").getBytes());
                        player.setHasActed(true);
                    }else {
                        System.out.println("Wait for your turn");
                    }
                    break;
                case "/option":
                    if(player.getHasActed()) {
                        System.out.println(message);
                        out.write((message+"\n").getBytes());
                        player.setHasActed(true);
                    }else{
                        System.out.println("Wait for your turn");
                    }
                    break;
                case "/w":
                    str = message;
                    break;
                case "/help":
                    System.out.println("oix");
                    break;
                default:
                    System.out.println("Invalid Command");
                    break;
            }
            out.flush();
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
