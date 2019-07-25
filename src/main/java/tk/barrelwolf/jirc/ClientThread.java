package tk.barrelwolf.jirc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket;

    ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());

            StringBuilder messageBuffer = new StringBuilder();

            Character lastChar, currentChar = null;
            int read, count = 0;
            while ((read = isr.read()) != -1) {
                lastChar = currentChar;
                currentChar = (char) read;
                ++count;

                if (count <= 1024) {
                    messageBuffer.append(currentChar);
                }

                if (lastChar != null && lastChar == '\r' && currentChar == '\n') {
                    JIRC.print("Message from " + socket.getInetAddress().getCanonicalHostName() + ": \"" + messageBuffer.toString() +"\"");
                    messageBuffer = new StringBuilder();
                    count = 0;
                    currentChar = null;
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
