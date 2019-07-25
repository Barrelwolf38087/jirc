package tk.barrelwolf.jirc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JIRC {
    private static final int port = 6667;

    public static void main(String[] args) {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Listening on port " + port);

            //noinspection InfiniteLoopStatement
            while (true) {

                Socket clientSocket = serverSocket.accept();

                new ClientThread(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    static synchronized void print(String s) {
        System.out.println(s);
    }
}
