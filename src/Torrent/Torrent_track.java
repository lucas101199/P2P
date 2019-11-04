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
            track.put(piece, new LinkedList<>());
        }
    }

    public void fillMapWithPeer(Peers peer_id, byte[] piece) {
        List<Peers> old_list = track.get(piece);
        List<Peers> list_peer = track.get(piece);
        list_peer.add(peer_id);
        track.replace(piece, old_list, list_peer);
    }

    public void FillWithSeeder(Peers peer_id) {
        for (Map.Entry<byte[], List<Peers>> entry : track.entrySet()) {
            List<Peers> peers = entry.getValue();
            peers.add(peer_id);
            entry.setValue(peers);
        }
    }

    public Map<byte[], List<Peers>> getHashMap() {
        return track;
    }

    public Torrent getTorrent() {
        return torrent;
    }
}
