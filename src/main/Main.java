package main;

import list.List;
import list.playlist.Playlist;
import song.Song;

import java.util.Scanner;

public class Main {

	private static final String makePlaylistHelpString = "mkpl [-n|-name] <name>";
	private static final String listHelpString         = "ls [-p]";
	private static final String helpHelpString         = "help\n         help <cmd>";
	private static final String makeSongHelpString     = "";

	private Scanner scanner;

	private List<Playlist> playlists;

	/*
	 * Program starts execution here. Creates a new instance of Main.
	 * */
	public static void main(String[] args) {
		new Main();
	}

	private Main() {
		scanner = new Scanner(System.in);
		playlists = new List<Playlist>();
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
				executeCommandList(arguments);
			} else if (command.equals("help")) {
				executeCommandHelp(parameters);
			} else if (command.equals("mksong")) {
				executeCommandMakeSong(input, arguments, parameters);
			} else {
				// bad command entered
				System.out.println("$ Unknown command, See help for more information");
			}
		}
	}

	private void executeCommandMakeSong(String input, List<String> arguments, List<String> parameters) {
		System.out.println(arguments);
		System.out.println(arguments.contains("-n"));
		if (!arguments.contains("-n") || !arguments.contains("-y") || !arguments.contains("-ar") || !arguments.contains("-al") || !arguments.contains("-g") || !arguments.contains("-r") || !arguments.contains("-p")) {
			System.out.println("$ Insufficient arguments to make song, See help mksong for more information");
		} else if (parameters.size() < 7) {
			System.out.println("$ Insufficient parameters to make song, See help mksong for more information");
		} else {
			boolean playlistExists = false;
			int index = 0;
			for (int i = 0; i < playlists.size(); i++) {
				if (playlists.get(i).getName().equals(getParameterForArgument(input, "-p"))) {
					playlistExists = true;
					index = i;
					break;
				}
			}
			if (playlistExists) {
				// create song and add to playlist
				Song song = new Song(getParameterForArgument(input, "-n"), Integer.parseInt(getParameterForArgument(input, "-y")), getParameterForArgument(input, "-ar"), getParameterForArgument(input, "-al"), getParameterForArgument(input, "-g"));
				playlists.get(index).add(song);
			} else {
				System.out.println("$ Playlist does not exist");
			}
		}
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
			}
		}
	}

	private void executeCommandList(List<String> arguments) {
		if (arguments.get(0).equals("-p")) {
			if (playlists.size() > 0) {
				System.out.println("$ " + playlists.get(0).getName());
				for (int i = 1; i < playlists.size(); i++) {
					System.out.println("  " + playlists.get(i).getName());
				}
			} else {
				System.out.println("$ No playlists");
			}
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
