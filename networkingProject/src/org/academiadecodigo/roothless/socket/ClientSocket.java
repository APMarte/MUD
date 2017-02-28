package org.academiadecodigo.roothless.socket;

import org.academiadecodigo.roothless.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by apm on 27-02-2017.
 */
public class ClientSocket {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            Socket clientSocket  = serverSocket.accept();


        } catch (IOException e) {
            e.printStackTrace();

        }

        Game game = new Game();

    }






}
