package assignment3;

public class DoublyLinkedStack<T> implements StackInterface<T> {
    private static class Node<T> {
    	//Node variables
        private T nodeData;
        private Node<T> nextNode;
        private Node<T> previousNode;

        // Constructor
        public Node(T nodeData) {
            this.nodeData = nodeData;
        }
        //Setters and getters
        public T getNodeData() {
            return nodeData;
        }

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }
        
        @SuppressWarnings("unused")
		public Node<T> getPreviousNode() {
            return previousNode;
        }

        public void setPreviousNode(Node<T> previousNode) {
            this.previousNode = previousNode;
        }
    }

    private Node<T> top;
    private int size;

    // Constructor
    public DoublyLinkedStack() {
        top = null;
        size = 0;
    }

    //Push a new node to the top of the stack
    public void push(T newEntry) {
        Node<T> newNode = new Node<>(newEntry);
        newNode.setNextNode(top); 
        if (top != null) {
            top.setPreviousNode(newNode); 
        }
        top = newNode; 
        size++;
    }

    //pop and return the top element then decrement list
    public T pop() {
        if (isEmpty()) {
            return null; 
        }
        T topNodeData = top.getNodeData();
        top = top.getNextNode(); 
        if (top != null) {
            top.setPreviousNode(null);
        }
        size--;
        return topNodeData;
    }

    //Take a peek at the top element
    public T peek() {
        if (isEmpty()) {
            return null; 
        }
        return top.getNodeData();
    }

    //Check if empty
    public boolean isEmpty() {
        return size == 0;
    }

    //Clear the stack
    public void clear() {
        top = null; 
        size = 0; 
    }
}
