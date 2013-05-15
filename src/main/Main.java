package main;

import list.List;
import list.playlist.Playlist;

import java.util.Scanner;

public class Main {

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
				// make a playlist
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
						System.out.println("$ usage: mkpl [-n|-name]");
					}
				}
			} else if (command.equals("ls")) {
				if (playlists.size() > 0) {
					System.out.println("$ " + playlists.get(0).getName());
					for (int i = 1; i < playlists.size(); i++) {
						System.out.println("  " + playlists.get(i).getName());
					}
				} else {
					System.out.println("$ No playlists");
				}
			} else if (command.equals("help")) {
				if (parameters.size() == 0) {
					System.out.println("$ List of Commands:");
					System.out.println("$ mkpl [-n|-name] <arg>\n  ls");
				} else {
					// help of a particular command
					if (parameters.get(0).equals("mkpl")) {
						System.out.println("$ usage: mkpl [-n|-name] <name>");
					} else if (parameters.get(0).equals("ls")) {
						System.out.println("$ usage: ls");
					} else if (parameters.get(0).equals("help")) {
						System.out.println("$ usage: help\n         help <cmd>");
					}
				}
			} else {
				// bad command entered
				System.out.println("$ Unknown command, See help for more information");
			}
		}
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
