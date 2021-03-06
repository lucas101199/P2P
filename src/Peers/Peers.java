package Peers;

import Seeder.Seeder;
import Torrent.Torrent;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Peers {

    public int id;
    public int port;
    public InetSocketAddress address;
    public Peers_listener l ;
    public Torrent torrent;
    public Map<byte[], byte[]> data; //hashmap which contains the data(second byte[]) for each pieces(first byte[]) of the file

    public Peers(int id, int port, Torrent torrent) {
        this.id = id;
        this.port = port;
        this.torrent = torrent;
        this.address = new InetSocketAddress("localhost", this.port);
        this.data = new HashMap<>();

        //fill the hashmap for the first time when a new peer is created with all the hash of pieces contains in the torrent and data to null
        for (byte[] pieces : torrent.getPieces()) {
            byte[] data_downloaded = new byte[256];
            this.data.put(pieces, data_downloaded);
        }

        l = new Peers_listener(this);
        l.start();
    }

    //Initial seeder
    public Peers(int id, int port, Torrent torrent, File file) throws Exception {
        this.id = id;
        this.port = port;
        this.torrent = torrent;
        this.address = new InetSocketAddress("localhost", this.port);
        this.data = new HashMap<>();

        l = new Peers_listener(this);
        l.start();

        //fill the hashmap for the first time when a new peer is created with all the hash of pieces and data of the file
        Seeder seeder = new Seeder(this, file);
        this.data = seeder.FillHashMapWithDataWhenInitialSeeder(file);
    }

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

    public Torrent getTorrent() {
        return torrent;
    }

    public Map<byte[], byte[]> getMap() {
        return this.data;
    }

    public void disconnect() throws IOException {
        l.disconnect();
    }

    public void connectToPeer(Peers peers) throws IOException {
        new Peers_client(new Socket("localhost", peers.port), peers).run();
    }
}
