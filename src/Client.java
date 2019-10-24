import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class Client {

    public static void main(String args[]) throws Exception {
        String url = "http://localhost:8080";
        String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
        String param1 = "value1";
        String param2 = "value2";
// ...

        String query = String.format("param1=%s&param2=%s",
                URLEncoder.encode(param1, charset),
                URLEncoder.encode(param2, charset));

        URLConnection connection = new URL(url + "?" + query).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();
    }
}
