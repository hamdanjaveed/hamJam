package list;

import list.node.Node;
import song.Song;

/*
 * A List is an object comprised of multiple Node objects. The List object
 * does not contain any information itself, but delegates that responsibility
 * to each of its Node objects. The List contains information about which Node
 * is the 'headNode' (the beginning of the List), and also provides convenience
 * methods that allow the user to add, remove and look up information.
 * */
public class List<T> {

	// a reference to the headNode Node
	private Node<T> headNode;

	/*
	 * Constructs an empty List object that contains no Nodes.
	 * */
	public List() {

	}

	/*
	 * Adds the passes in element to the end of the list. Creates a Node object
	 * and then adds the Node to the end of the list.
	 * */
	public void add(T element) {
		add(new Node<T>(null, element));
	}

	public void addAt(T element, int index) {
		if (index >= 0 && index < size()) {
			Node<T> atIndexPrev = getNode(index);
			Node<T> beforeIndexPrev = null;
			if (index > 0) {
				beforeIndexPrev = getNode(index - 1);
			}
			Node<T> newNode = new Node<T>(atIndexPrev, element);
			if (beforeIndexPrev != null)
				beforeIndexPrev.setNextNode(newNode);
		}
	}

	/*
	 * Add a Node to the end of the List. First finds the last Node in the List,
	 * then sets that last Node's nextNode to the node that was passed in as
	 * the argument. If the List has no Nodes, then sets the headNode as the
	 * Node that was passed in as the argument.
	 * */
	private void add(Node<T> nodeToBeAdded) {
		Node<T> lastNode = getLastNode();
		if (lastNode != null) {
			lastNode.setNextNode(nodeToBeAdded);
		} else {
			setHeadNode(nodeToBeAdded);
		}
	}

	public String toString() {
		String listToString = "[";
		if (headNode != null) {
			Node<T> node = headNode;
			listToString += node.getElement().toString();
			node = node.getNextNode();
			while (node != null) {
				listToString += ", " + node.getElement().toString();
				node = node.getNextNode();
			}
		}
		listToString += "]";
		return listToString;
	}

	/*
	 * Gets the last node in the List. Looks through all the Nodes in the list
	 * starting from the headNode. Returns the Node that does not connect to
	 * any other Node. Method returns null if the List does not contain any
	 * Nodes.
	 * */
	public Node<T> getLastNode() {
		Node<T> lastNode = headNode;
		if (lastNode != null) {
			while (lastNode.getNextNode() != null) {
				lastNode = lastNode.getNextNode();
			}
		}
		return lastNode;
	}

	public int size() {
		int size = 0;
		Node<T> n = headNode;
		if (n != null) {
			size++;
			while (n.getNextNode() != null) {
				size++;
				n = n.getNextNode();
			}
		}
		return size;
	}

	public boolean contains(T element) {
		boolean contains = false;
		Node<T> n = headNode;
		if (n != null) {
			T e = n.getElement();
			if (e.equals(element)) {
				contains = true;
			}
			while (n.getNextNode() != null) {
				n = n.getNextNode();
				e = n.getElement();
				if (e.equals(element)) {
					contains = true;
				}
			}
		}
		return contains;
	}

	public void remove(int index) {
		if (index == 0) {
			headNode = getNode(1);
		} else {
			Node<T> atIndex = getNode(index);
			Node<T> prevIndex = getNode(index - 1);
			prevIndex.setNextNode(atIndex.getNextNode());
		}
	}

	public void removeByName(String name) {
		int index = getIndexOfSong(name);
		remove(index);
	}

	private int getIndexOfSong(String name) {
		int index = 0;
		for (int i = 0; i < size(); i++) {
			T element = get(i);
			if (element instanceof Song) {
				Song s = (Song)element;
				if (s.getNameOfSong().equals(name)) {
					index = i;
					break;
				}
			}
		}
		return index;
	}

	public void print() {
		if (size() > 0) {
			System.out.println("$ " + get(0).toString());
			for (int i = 1; i < size(); i++) {
				System.out.println("  " + get(i));
			}
		} else {
			System.out.println("$ List contains no elements");
		}
	}

	public Node<T> getNode(int index) {
		Node<T> n = headNode;
		if (index < size()) {
			int cIndex = 0;
			for (;cIndex != index; cIndex++) {
				if (n.getNextNode() != null) {
					n = n.getNextNode();
				}
			}
			return n;
		} else {
			return null;
		}
	}

	public T get(int index) {
		Node<T> n = headNode;
		if (index < size() || n == null) {
				int cIndex = 0;
				for (;cIndex != index; cIndex++) {
					if (n.getNextNode() != null) {
						n = n.getNextNode();
					}
				}
			return n.getElement();
		} else {
			return null;
		}
	}

	// getters and setters

	public Node<T> getHeadNode() {
		return headNode;
	}

	public void setHeadNode(Node<T> head) {
		this.headNode = head;
	}

}
