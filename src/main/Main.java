package main;

import list.List;

public class Main {

	/*
	 * Program starts execution here. Creates a new instance of Main.
	 * */
	public static void main(String[] args) {
		new Main();
	}

	private Main() {
		List<List> list = new List<List>();

		List<Integer> list1 = new List<Integer>();
		list1.add(4);
		list1.add(2);

		List<String> list2 = new List<String>();
		list2.add("Apple");
		list2.add("Banana");

		list.add(list1);
		list.add(list2);

		System.out.println(list);
	}

}
