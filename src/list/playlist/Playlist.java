package list.playlist;

import list.List;

/*
 * A Playlist is a collection of Songs. It is a subclass of List. An object
 * of type List<Song> could be used instead of Playlist. This class is used
 * for convenience.
 * */
public class Playlist extends List {

	private String name;

	/*
	 * Creates an empty playlist.
	 * */
	public Playlist(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
