import java.io.InputStream;
import java.net.*;

public class Client {

    int peer_id;

    public Client(int peer_id) {
        this.peer_id = peer_id;
    }

    public static void main(String args[]) throws Exception {
        Client client = new Client(20);
        String url = "http://localhost:8080";
        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()

        String param1 = String.valueOf(client.peer_id);

        String query = String.format("peer_id=%s",
                URLEncoder.encode(param1, charset));

        URLConnection connection = new URL(url + "?" + query).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();
    }
}
