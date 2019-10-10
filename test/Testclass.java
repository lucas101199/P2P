public class Testclass {

    /*
    What do we do?:
	P2P File-sharing system based on BitTorrent technology

We need:
	Distributed Hash Table
	Trackers -> checks availability
	Chord Technology
	Interface

We use:
	Bittorrent implementation (library)


________________________________________________________________

How to Write a Bittorrent Client
www.kristenwidman.com/blog/33/how-to-write-a-bittorrent-client-part-1/



	Wireshark: play around with it
	Download: written bittorrent client (utorrent / official bittorrent client)
	Download: .torrent file




	Tracker(HTTPS Service):	information about torrent and peers
		NOT the file you want to download, but a list of who has/downloads it
		Responds to GET request with list of peers


	Build connection to peers
	+ Initial Handshake


		Connect_to_tracker
			'announce' key in .torrent metafile: url of tracker
			announce + parameters = GET Request
		Parse_tracker_response
			?
		Connect_to_peers
			Connection: TCP
			First: connect to one peer (but for more peers ->)
			Create own event-driven programming loop using sockets and select calls.
		Handshake_with_peers
			1. Send Handshake to peer: info_hash & peer_id
			Current protocol version (1.0), ‘pstrlen’ = 19 and ‘pstr’ = ‘BitTorrent protocol’
			-> combined as long byte string
			2.1 Response: check handshake if peer_id is matches expected peer_id
			-> Close connection if not matching
			2.2 Response: check handshake if info_hash matches ...
			-> Close connection if not matching



www.kristenwidman.com/blog/33/how-to-write-a-bittorrent-client-part-2/


	1. Message Passing - Overview
		11 types of messages (contain: Header-Length, Payload-Length)
		We need: Create & Parse - methods for each type
		Whole message separated in several smaller packages
			Note: use length prefix



	2. Have & Bitfield

		We need: which pieces of the file I want to download does the peer have?
			-> 'Have' Message Type: Client sends a have message for every piece he has
			-> 'Bitfield': Client sends one message with a byte payload
				(i.e. ‘\xfe\xff’ = 1111111011111111 (pieces 0-15, piece 7 is missing)



	3. Choke/Unchoke and Interested/Not Interested

		Used to indicate whether a peer will send you files or not
		At first all connections: choke/Not interested
		Interested: interested in downloading
		Unchoke: downloadable
		Choke: not downloadable

		Order: 1. Handshake, 2. Interested Message, 3. Wait for getting Unchoked, 4. Request for pieces



	4. Request

		Request Size for every block
		Note: last block smaller than request size
		Check if peer has the block you're looking for
		Track (which blocks you requested): avoid duplicates


	5. Piece

		is a Response Message: contains information of a block
		Hash_Check: to verify no bad data was sent


	6. Cancel and Port

		Note: both not necessary for a working BitTorrent Client


Important Note: The Port message type is used by clients which support a Distributed Hash Table (DHT) approach 	to finding peers rather than using a tracker


	7. Writing to file. When?

		1. after hash-check of every block
		2. after downloading complete file


     */
}
