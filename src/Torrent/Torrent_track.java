package Torrent;

import java.util.*;

import Peers.Peers;

public class Torrent_track {
    Torrent torrent;
    Map<byte[], List<Peers>> track;

    public Torrent_track(Torrent torrent) {
        this.torrent = torrent;

        this.track = new HashMap<>();
        //fill the map with all the pieces contain in the torrent file
        for(byte[] piece : torrent.getPieces()) {
            track.put(piece, new LinkedList<Peers>());
        }
    }

    public void fillMapWithId(Peers peer_id, byte[] piece) {
        List<Peers> list_peer = track.get(piece);
        list_peer.add(peer_id);
    }

    public Map<byte[], List<Peers>> getHashMap() {
        return track;
    }
}
