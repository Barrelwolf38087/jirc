package tk.barrelwolf.jirc;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.HashMap;

public class Client {
    private Socket socket;
    private HashMap<Attribute, String> attributes;

    public Client(Socket socket) {
        this.socket = socket;
        this.attributes = new HashMap<>();
    }

    public Socket getSocket() { return socket; }

    /**
     * Get the String associated with the given {@link Attribute} for this client.
     * @param attribute The attribute to get.
     * @return The value of the attribute, or the default value for the attribute if it does not apply to this client.
     */
    @Nullable
    public String getAttribute(Attribute attribute) {
        return attributes.getOrDefault(attribute, attribute.getDefaultValue());
    }

    public void setAttribute(Attribute attribute, String value) { attributes.put(attribute, value); }

    public boolean hasAttribute(Attribute attribute) { return attributes.containsKey(attribute); }

    public void send(Message message) throws IOException {
        socket.getOutputStream().write(message.toString().getBytes(Charset.forName("UTF-8")));
    }
}
