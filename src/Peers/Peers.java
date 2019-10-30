package Peers;

import java.net.InetSocketAddress;

public class Peers {

    int id;
    int port;
    InetSocketAddress address;

    public Peers(int id, int port) {
        this.id = id;
        this.port = port;
        this.address = new InetSocketAddress("localhost", this.port);
    }

    public int getId() {
        return id;
    }

    public int getPort() {
        return port;
    }

    public InetSocketAddress getAddress() {
        return address;
    }
}
