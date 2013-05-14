package song;

/*
 * The Song class contains all the information for one particular Song. A List of Songs
 * makes up a Playlist.
 * */
public class Song {

	// the name or title of the song
	private String nameOfSong;
	// the year of release
	private int yearReleased;
	// the artist of the song
	private String artist;
	// the album this song belongs too
	private String album;
	// the genre of this song
	private String genre;
	// the rating of this song
	private double rating;

	/*
	 * Create a new Song with the specified attributes.
	 * */
	public Song(String nameOfSong, int yearReleased, String artist, String album, String genre) {
		// assign variables
		setNameOfSong(nameOfSong);
		setYearReleased(yearReleased);
		setArtist(artist);
		setAlbum(album);
		setGenre(genre);
		setRating(-1);
	}

	public String getNameOfSong() {
		return nameOfSong;
	}

	public void setNameOfSong(String nameOfSong) {
		this.nameOfSong = nameOfSong;
	}

	public int getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(int yearReleased) {
		this.yearReleased = yearReleased;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

}
