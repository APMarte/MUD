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
    private boolean isChating; //se esta no chat ou não
    private BufferedReader bin;




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

                if(!isChating){
                    System.out.println(player.getName() + " choose your action: ");
                }
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
        try {
            bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Insert username: "); // aceita se o utilizador nao introduzir nada
        String name = scanner.nextLine();

        do {
            System.out.println("Choose your Class: \n  (1) Archer  (2) Paladin  (3) Priest  (4) Sorcerer  (5) Thief");
            try {

                numClass = Integer.parseInt(br.readLine());
                String str=name+ " " + identifyClass(numClass) + "\n"; // string auxiliar
                out.write(str.getBytes());
                chosePlayerType(numClass, name);
            } catch (NumberFormatException e) {
                System.out.println("Invalid operation!");
                e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (numClass < 1 || numClass > 5 /*&& bin.readLine() != "OK"*/);


    }

    public String identifyClass(int num){

        switch (num){
            case 1:
                return "Archer";
            case 2:
                return "Paladin";
            case 3:
                return "Priest";
            case 4:
                return "Sorcerer";
            case 5:
                return "Thief";
        }
        return null;
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

        if (command.charAt(0) != '/') {
            isChating=true;
            message+="\n";
            out.write(message.getBytes());
            out.flush();
        }

        else {

            isChating=false;
            switch (command) {
                case "/skill":
                    if (!player.getHasActed()) {
                        str = message +" "+ player.dmgCalc()+" "+player.getChoosenClass()+"\n";
                        out.write(str.getBytes());
                        player.setHasActed(true);
                    } else{
                        System.out.println("Wait for your turn");
                    }
                        break;
                case "/defense":
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
                        System.out.println("Please wait for your turn");
                    }
                    break;
                case "/w":
                    out.write((message+"\n").getBytes());
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
