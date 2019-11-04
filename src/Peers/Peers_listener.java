package Peers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Peers_listener implements Runnable{

    private Peers peer;
    private ServerSocket serverSocket;
    private boolean alive;

    public Peers_listener (Peers peers) {
        peer = peers;
        alive = true;
        InetSocketAddress localAddress = peer.getAddress();
        int port = localAddress.getPort();

        //open server/listener socket
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Listening on port " + localAddress.getPort());
        } catch (IOException e) {
            throw new RuntimeException("\nCannot open listener port " + port + ". Now exit.\n", e);
        }
    }

    @Override
    public void run() {
        while (alive) {
            Socket talkSocket = null;
            try {
                talkSocket = serverSocket.accept();
                System.out.println(talkSocket.getPort());
            } catch (IOException e) {
                throw new RuntimeException(
                        "Cannot accepting connection", e);
            }

            //new talker
            new Thread();
        }
    }

    public void disconnect() throws IOException {
        serverSocket.close();
    }
}
