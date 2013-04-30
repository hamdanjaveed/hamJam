package main;

import list.List;
import list.node.Node;

public class Main {

	/*
	 * Program starts execution here. Creates a new instance of Main.
	 * */
	public static void main(String[] args) {
		new Main();
	}

	private Main() {
		List<Integer> list = new List<Integer>();
		list.add(5);
		list.add(4);
		list.add(123);
		System.out.println(list);
	}

}
