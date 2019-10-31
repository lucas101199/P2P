package Peers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Peers_listener extends Thread{

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
        } catch (IOException e) {
            throw new RuntimeException("\nCannot open listener port "+port+". Now exit.\n", e);
        }
    }

    @Override
    public void run() {
        while (alive) {
            Socket talkSocket = null;
            try {
                talkSocket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(
                        "Cannot accepting connection", e);
            }

            //new talker
            new Thread((Runnable) new Peers_client(talkSocket, peer)).start();
        }
    }
}
