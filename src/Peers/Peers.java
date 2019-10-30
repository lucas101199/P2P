package Peers;

import Torrent.Torrent;

import java.net.InetSocketAddress;
import java.util.List;

public class Peers {

    int id;
    int port;
    InetSocketAddress address;
    Peers_listener l ;
    Torrent torrent;
    List<byte[]> bytes;

    public Peers(int id, int port) {
        this.id = id;
        this.port = port;
        this.address = new InetSocketAddress("localhost", this.port);
        l = new Peers_listener(this);
        l.start();

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
