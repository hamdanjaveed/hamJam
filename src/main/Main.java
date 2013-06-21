package main;

import list.List;
import list.playlist.Playlist;
import song.Song;

import java.util.Scanner;

public class Main {

	private static final String makePlaylistHelpString = "mkpl [-n|-name] <name>";
	private static final String listHelpString         = "ls [-p]";
	private static final String helpHelpString         = "help\n  help <cmd>";
	private static final String makeSongHelpString     = "mksong [-n] <name> [-y] <year> [-ar] <artist> [-al] <album> -g <genre> -r <rating> -p <playlist> *op* [-i] <index>";

	private Scanner scanner;

	private List<Playlist> playlists;

	private Playlist nowPlaying;

	/*
	 * Program starts execution here. Creates a new instance of Main.
	 * */
	public static void main(String[] args) {
		new Main();
	}

	private Main() {
		scanner = new Scanner(System.in);
		playlists = new List<Playlist>();
		nowPlaying = new Playlist("nowPlaying");
		Playlist p1 = new Playlist("Playlist1");
		p1.add(new Song("song 1", 1995, "playlist 1 artist 1", "playlist 1 album 1", "rock"));
		p1.add(new Song("song 2", 1996, "playlist 1 artist 2", "playlist 1 album 2", "rock"));
		p1.add(new Song("song 3", 1997, "playlist 1 artist 3", "playlist 1 album 3", "rock"));
		Playlist p2 = new Playlist("Playlist2");
		p2.add(new Song("playlist 2 song 1", 1996, "playlist 2 artist 2", "playlist 2 album 2", "rock"));
		p2.add(new Song("playlist 2 song 2", 1997, "playlist 2 artist 3", "playlist 2 album 3", "rock"));
		Playlist p3 = new Playlist("Playlist3");
		p3.add(new Song("playlist 3 song 1", 1995, "playlist 3 artist 1", "playlist 3 album 1", "rock"));
		p3.add(new Song("playlist 3 song 2", 1996, "playlist 3 artist 2", "playlist 3 album 2", "rock"));
		p3.add(new Song("playlist 3 song 3", 1997, "playlist 3 artist 3", "playlist 3 album 3", "rock"));
		Playlist p4 = new Playlist("Playlist4");
		p4.add(new Song("playlist 4 song 1", 1995, "playlist 4 artist 1", "playlist 4 album 1", "rock"));
		p4.add(new Song("playlist 4 song 2", 1996, "playlist 4 artist 2", "playlist 4 album 2", "rock"));
		p4.add(new Song("playlist 4 song 3", 1997, "playlist 4 artist 3", "playlist 4 album 3", "rock"));
		Playlist p5 = new Playlist("Playlist5");
		p5.add(new Song("playlist 5 song 1", 1995, "playlist 5 artist 1", "playlist 5 album 1", "rock"));
		playlists.add(p1);
		//playlists.add(p2);
		//playlists.add(p3);
		//playlists.add(p4);
		//playlists.add(p5);
		// run the program
		runProgram();
	}

	/*
	 * Contains the program loop.
	 * */
	private void runProgram() {
		while (true) {
			// print $
			System.out.print("hamJam$ ");
			// get the entire line of input from the user
			String input = getInput();
			List<String> arguments = new List<String>();
			List<String> parameters = new List<String>();
			String command = getCommandAndArgumentsAndParameters(input, arguments, parameters);

			if (command == null) {
				// no command
			} else if (command.equals("exit")) {
				System.out.println("$ Terminating...");
				break;
			} else if (command.equals("mkpl")) {
				executeCommandMakePlaylist(arguments, parameters);
			} else if (command.equals("ls")) {
				executeCommandList(arguments, input);
			} else if (command.equals("help")) {
				executeCommandHelp(parameters);
			} else if (command.equals("mksong")) {
				executeCommandMakeSong(input, arguments, parameters);
			} else if (command.equals("rmsong")) {
				executeCommandRemoveSong(input, arguments, parameters);
			} else if (command.equals("play")) {
				executeCommandPlay(input, arguments, parameters);
			} else if (command.equals("editsong")) {
				executeCommandEditSong(input, arguments, parameters);
			} else {
				// bad command entered
				System.out.println("$ Unknown command, See help for more information");
			}
		}
	}

	private void executeCommandEditSong(String input, List<String> arguments, List<String> parameters) {
		if (arguments.contains("-p") && arguments.contains("-i")) {
			String playlist = getParameterForArgument(input, "-p");
			if (playlistExists(playlist)) {
				int songIndex = Integer.parseInt(getParameterForArgument(input, "-i"));
				Song song = (Song)(playlists.get(getIndexOfPlaylist(playlist)).get(songIndex));
				if (arguments.contains("-n")) {
					song.setNameOfSong(getParameterForArgument(input, "-n"));
				}
				if (arguments.contains("-y")) {
					song.setYearReleased(Integer.parseInt(getParameterForArgument(input, "-y")));
				}
				if (arguments.contains("-ar")) {
					song.setArtist(getParameterForArgument(input, "-ar"));
				}
				if (arguments.contains("-al")) {
					song.setAlbum(getParameterForArgument(input, "-al"));
				}
				if (arguments.contains("-g")) {
					song.setGenre(getParameterForArgument(input, "-g"));
				}
				if (arguments.contains("-r")) {
					song.setRating(Double.parseDouble(getParameterForArgument(input, "-r")));
				}
			}
		}
	}

	private void executeCommandPlay(String input, List<String> arguments, List<String> parameters) {
		if (arguments.contains("-p")) {
			String playlist = getParameterForArgument(input, "-p");
			if (playlistExists(playlist)) {
				nowPlaying = playlists.get(getIndexOfPlaylist(playlist));
			}
		} else if (arguments.contains("-n") && nowPlaying.size() > 0) {
			nowPlaying.remove(0);
		}
		if (nowPlaying.size() > 0)
			System.out.println("$ Now playing: " + nowPlaying.get(0).toString());
		else
			System.out.println("$ No songs to play");
	}

	private void executeCommandRemoveSong(String input, List<String> arguments, List<String> parameters) {
		if ((arguments.contains("-s") || arguments.contains("-i") && arguments.contains("-p"))) {
			String playlist = getParameterForArgument(input, "-p");
			if (playlistExists(playlist)) {
				int index = getIndexOfPlaylist(playlist);
				if (arguments.contains("-s")) {
					playlists.get(index).removeByName(getParameterForArgument(input, "-s"));
				} else {
					playlists.get(index).remove(Integer.parseInt(getParameterForArgument(input, "-i")));
				}
			}
		} else {
			System.out.println("$ Insufficient arguments to remove song, See help rmsong for more information");
		}
	}

	private boolean playlistExists(String a) {
		for (int i = 0; i < playlists.size(); i++) {
			if (playlists.get(i).getName().equals(a)) {
				return true;
			}
		}
		return false;
	}

	private void executeCommandMakeSong(String input, List<String> arguments, List<String> parameters) {
		if (!arguments.contains("-n") || !arguments.contains("-y") || !arguments.contains("-ar") || !arguments.contains("-al") || !arguments.contains("-g") || !arguments.contains("-r") || !arguments.contains("-p")) {
			System.out.println("$ Insufficient arguments to make song, See help mksong for more information");
		} else if (parameters.size() < 7) {
			System.out.println("$ Insufficient parameters to make song, See help mksong for more information");
		} else {
			String playlist = getParameterForArgument(input, "-p");
			if (playlistExists(playlist)) {
				// create song and add to playlist
				Song song = new Song(getParameterForArgument(input, "-n"), Integer.parseInt(getParameterForArgument(input, "-y")), getParameterForArgument(input, "-ar"), getParameterForArgument(input, "-al"), getParameterForArgument(input, "-g"));
				if (arguments.contains("-i")) {
					playlists.get(getIndexOfPlaylist(playlist)).addAt(song, Integer.parseInt(getParameterForArgument(input, "-i")));
				} else {
					playlists.get(getIndexOfPlaylist(playlist)).add(song);
				}
			} else {
				System.out.println("$ Playlist does not exist, See help mkpl for more information");
			}
		}
	}

	private int getIndexOfPlaylist(String playlist) {
		int index = 0;
		for (int i = 0; i < playlists.size(); i++) {
			if (playlists.get(i).getName().equals(playlist)) {
				index = i;
				break;
			}
		}
		return index;
	}

	private void executeCommandHelp(List<String> parameters) {
		if (parameters.size() == 0) {
			System.out.println("$ List of Commands:");
			System.out.println("$ " + makePlaylistHelpString + "\n  " + listHelpString + "\n  " + helpHelpString);
		} else {
			// help of a particular command
			if (parameters.get(0).equals("mkpl")) {
				System.out.println("$ usage: " + makePlaylistHelpString);
			} else if (parameters.get(0).equals("ls")) {
				System.out.println("$ usage: " + listHelpString);
			} else if (parameters.get(0).equals("help")) {
				System.out.println("$ usage: " + helpHelpString);
			} else if (parameters.get(0).equals("mksong")) {
				System.out.println("$ usage: " + makeSongHelpString);
			}
		}
	}

	private void executeCommandList(List<String> arguments, String input) {
		if (arguments.size() > 0) {
			if (arguments.get(0).equals("-p")) {
				if (playlists.size() > 0) {
					String param = getParameterForArgument(input, "-p");
					if (param != null) {
						if (arguments.contains("-l")) {
							Playlist p = playlists.get(Integer.parseInt(param));
							System.out.println(p.getLastNode().getElement());
						} else {
							Playlist p = playlists.get(Integer.parseInt(param));
							p.print();
						}
					} else {
						System.out.println("$ " + playlists.get(0).getName());
						for (int i = 1; i < playlists.size(); i++) {
							System.out.println("  " + playlists.get(i).getName());
						}
					}
				} else {
					System.out.println("$ No playlists");
				}
			}
		} else {
			System.out.println("$ Insufficient arguments to list, See help ls for more information");
		}
	}

	private void executeCommandMakePlaylist(List<String> arguments, List<String> parameters) {
		if (arguments.size() == 0) {
			// no args
			System.out.println("$ Insufficient arguments to make playlist, See help mkpl for more information");
		} else if (parameters.size() == 0) {
			// no params
			System.out.println("$ Insufficient parameters to make playlist, See help mkpl for more information");
		} else {
			// at least 1 arg and param
			if (arguments.get(0).equals("-n") || arguments.get(0).equals("-name")) {
				// good arg and param
				String pName = parameters.get(0);
				for (int i = 1; i < parameters.size(); i++) {
					pName += " " + parameters.get(i);
				}
				playlists.add(new Playlist(pName));
			} else {
				// bad argument
				System.out.println("$ Unknown argument");
				System.out.println("$ usage: " + makePlaylistHelpString);
			}
		}
	}

	private String getParameterForArgument(String input, String argument) {
		Scanner s = new Scanner(input);
		s.useDelimiter(" ");
		while (s.hasNext()) {
			String arg = s.next();
			if (arg.equals(argument)) {
				if (s.hasNext())
					return s.next();
			}
		}
		return null;
	}

	private String getCommandAndArgumentsAndParameters(String input, List<String> arguments, List<String> parameters) {
		Scanner s = new Scanner(input);
		s.useDelimiter(" ");
		String command = null;
		if (s.hasNext()) {
			command = s.next();
			while (s.hasNext()) {
				String element = s.next();
				if (element.charAt(0) == '-') {
					arguments.add(element);
				} else {
					parameters.add(element);
				}
			}
		}
		return command;
	}

	private String getInput() {
		return scanner.nextLine();
	}

}
