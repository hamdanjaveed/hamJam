package list;

import list.node.Node;

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
			listToString += node.getElement();
			node = node.getNextNode();
			while (node != null) {
				listToString += ", " + node.getElement();
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
	private Node<T> getLastNode() {
		Node<T> lastNode = headNode;
		if (lastNode != null) {
			while (lastNode.getNextNode() != null) {
				lastNode = lastNode.getNextNode();
			}
		}
		return lastNode;
	}

	// getters and setters

	public Node<T> getHeadNode() {
		return headNode;
	}

	public void setHeadNode(Node<T> head) {
		this.headNode = head;
	}

}
