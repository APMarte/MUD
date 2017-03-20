package org.academiadecodigo.roothless.client;

import org.academiadecodigo.roothless.client.player.Player;
import org.academiadecodigo.roothless.client.player.PlayerFactory;
import org.academiadecodigo.roothless.client.player.PlayerType;
import java.io.*;
import java.net.Socket;
import java.net.URL;
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
    private boolean isChating; //se esta no chat ou



    public void connect() throws IOException {


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


        sendMessage();
    }

    public Socket getSocket() {
        return socket;
    }

    public void sendMessage() {

        while (!socket.isClosed()) {
            try {

                if (socket.isClosed()) {
                    break;
                }

                if (!isChating) {
                    System.out.println(player.getName() + " choose your action: ");
                }
                String outputMSG = scanner.nextLine();
                parseClient(outputMSG);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

  /*  public static void main(String[] args) {
        Client client = new Client();

        try {
            client.connect();
            //client.sendMessage();

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
    }*/

    public void createPlayer() {

        int numClass = 0;
        br = new BufferedReader(new InputStreamReader(System.in)); // vai fazer de Scanner
        String name = "";
        while (name.equals("")) {
            System.out.println("Insert username: "); // aceita se o utilizador nao introduzir nada
            name = scanner.nextLine();
        }
        do {
            System.out.println("Choose your Class: \n  (1) Archer  (2) Paladin  (3) Priest  (4) Sorcerer" +
                    "  (5) Thief");
            String line = "";
            try {

                numClass = Integer.parseInt(br.readLine());
                String path = "resources/classes/" + numClass + ".txt";
                URL resource = getClass().getResource(path.startsWith("/") ? path : "/" + path);
                br = new BufferedReader(new InputStreamReader(resource.openStream()));
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                String playerName = name + " " + identifyClass(numClass) + "\n"; // string auxiliar
                out.write(playerName.getBytes());
                chosePlayerType(numClass, name);
            } catch (NumberFormatException e) {
                System.out.println("Invalid operation!");
                e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (numClass < 1 || numClass > 5 /*&& bin.readLine() != "OK"*/);


    }

    public String identifyClass(int num) {

        switch (num) {
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
        if (!message.equals("")) {
            String command = message.split(" ")[0];
            String path = ("resources/help");
            URL resource = getClass().getResource(path.startsWith("/") ? path : "/" + path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream()));

            String str;

            if (command == null || command.charAt(0) != '/') {
                isChating = true;
                message += "\n";
                out.write(message.getBytes());
                out.flush();
            } else {

                if (!player.isDead()) {     // TODO: 06/03/17 pôr a condição à cabeça para nao estar a identar todo o resto do codigo. O morto devia poder usar /help e /whisper

                    isChating = false;
                    switch (command) {
                        case "/skill":
                            if (!player.getHasActed()) {
                                if (message.split(" ").length == 2 && message.split(" ")[1].matches("[1, 2]")) {
                                    str = message + " " + player.getChoosenClass() + " " + player.getName() + " " + player.dmgCalc() + "\n";
                                    out.write(str.getBytes());
                                } else {
                                    System.out.println("Invalid /skill command (/skill (1)/(2))");
                                    break;
                                }
                                player.setHasActed(true);
                            } else {
                                System.out.println("Wait for your turn");
                            }
                            break;
                        case "/defense":
                            if (!player.getHasActed()) {
                                out.write((message + "\n").getBytes());
                                player.setHasActed(true);
                                player.setDefense(player.getdefense() + 10); //TODO: ver se o valor é para manter.
                                System.out.println(player.getName() + " has gain 10 in Defense " + player.getDefense());
                            } else {
                                System.out.println("Wait for your turn");
                            }
                            break;
                        case "/pick":
                            if (!player.getHasActed()) {
                                out.write((message + "\n").getBytes());
                            } else {
                                System.out.println("Wait for your turn");
                            }
                            break;
                        case "/option":
                            if (player.getHasActed()) {
                                System.out.println(message);
                                out.write((message + "\n").getBytes());
                                player.setHasActed(true);
                            } else {
                                System.out.println("Please wait for your turn");
                            }
                            break;
                        case "/w":
                            out.write((message + "\n").getBytes());
                            break;
                        case "/help":
                            String line = "";
                            String result = "";
                            while ((line = reader.readLine()) != null) {
                                result += line + "\n";
                            }
                            System.out.println(result);
                            reader.close();
                            break;
                        default:
                            System.out.println("Invalid Command");
                            break;
                    }
                    out.flush();
                } else {
                    System.out.println("Not accepted.");
                }
            }
        }
    }

    private void serverParser(String message) {

        if (message.contains("|")) {
            System.out.println(message.split("[|]")[1]);
        }

        switch (message.split(" ")[1]) {

            case "hp":
                player.setHealth(player.getHealth() - Integer.parseInt(message.split(" ")[2]));
                System.out.println(player.getName() + " I'll eat you alive! Your health decrease to: " + player.getHealth());
                if (player.getHealth() < 0) {
                    player.setDead(true);
                    System.out.println("morri");
                }
                break;
            case "dmg":
                player.setBaseDamage(player.getBaseDamage() + Integer.parseInt(message.split(" ")[2]));
                System.out.println(player.getName() + " base damage increased to: " + player.getBaseDamage());
                break;
            case "int":
                player.setIntelligence(player.getIntelligence() + Integer.parseInt(message.split(" ")[2]));
                System.out.println(player.getName() + " intelligence increased to: " + player.getIntelligence());
                break;
            case "str":
                player.setStrength(player.getStrength() + Integer.parseInt(message.split(" ")[2]));
                System.out.println(player.getName() + " strength increased to: " + player.getStrength());
                break;
            case "def":
                player.setDefense(player.getDefense() + Integer.parseInt(message.split(" ")[2]));
                System.out.println(player.getName() + " defese increased to: " + player.getDefense());
                break;
            case "dex":
                player.setDexterity(player.getDexterity() + Integer.parseInt(message.split(" ")[2]));
                System.out.println(player.getName() + " dexterity increased to: " + player.getDexterity());
                break;
            case "fth":
                player.setFaith(player.getFaith() + Integer.parseInt(message.split(" ")[2]));
                System.out.println(player.getName() + " faith increased to: " + player.getFaith());
                break;
            case "crit":
                player.setCritChance(player.getCritChance() + Integer.parseInt(message.split(" ")[2]));
                System.out.println(player.getName() + " critical chance increased to: " + player.getCritChance());
                break;
            case "hasActed":
                player.setHasActed(false);
                System.out.println("Next turn");
                break;
        }
    }

    private class ServerListener implements Runnable {

        private BufferedReader in;

        public ServerListener(BufferedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (!socket.isClosed()) {
                    String serverMSG = in.readLine();
                    if (serverMSG == null) {
                        System.out.println("Server connection is closed.");
                        socket.close();
                        break;
                    }

                    if (serverMSG.startsWith("/") && serverMSG.split(" ")[0].equals("/modify")) {
                        serverParser(serverMSG);
                    } else {
                        System.out.println(serverMSG);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
