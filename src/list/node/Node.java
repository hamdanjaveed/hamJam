package list.node;

/*
 * A Node is an object that holds information for a List. Each Node points to
 * two objects, another Node and an object of type specified by the user. A
 * bunch of Nodes strung together form a List.
 * */
public class Node<T> {

    // another nextNode object
    private Node<T> nextNode;
    // the actual element that this nextNode contains
    private T element;

    /*
     * Constructor for Node that takes in a Node object and an element.
     * */
    public Node(Node<T> node, T element) {
        // set the nextNode and element objects
        setNextNode(node);
        setElement(element);
    }

    // getters and setters

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> node) {
        this.nextNode = node;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

}
