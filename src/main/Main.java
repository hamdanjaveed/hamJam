package main;

import list.List;

import java.util.Scanner;

public class Main {

	private Scanner scanner;

	/*
	 * Program starts execution here. Creates a new instance of Main.
	 * */
	public static void main(String[] args) {
		new Main();
	}

	private Main() {
		scanner = new Scanner(System.in);
		// run the program
		runProgram();
	}

	/*
	 * Contains the program loop.
	 * */
	private void runProgram() {
		while (true) {
			// get the entire line of input from the user
			String input = getInput();
			List<String> arguments = new List<String>();
			List<String> parameters = new List<String>();
			String command = getCommandAndArgumentsAndParameters(input, arguments, parameters);


			System.out.println(command);
			System.out.println();
			System.out.println(arguments);
			System.out.println();
			System.out.println(parameters);


			if (command.equals("exit")) {
				System.out.println("Terminating...");
				break;
			}
		}
	}

	private String getCommandAndArgumentsAndParameters(String input, List<String> arguments, List<String> parameters) {
		Scanner s = new Scanner(input);
		s.useDelimiter(" ");
		String command = s.next();
		while (s.hasNext()) {
			String element = s.next();
			if (element.charAt(0) == '-') {
				arguments.add(element);
			} else {
				parameters.add(element);
			}
		}
		return command;
	}

	private String getInput() {
		return scanner.nextLine();
	}

}
