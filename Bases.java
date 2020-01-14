

// Base class for Node. Each node would be an element of the Priority Queue.
abstract class NodeBase<V> {
    protected int priority; // Priority of the node
    protected V value;	// Value stored by the node
    public abstract int getPriority(); // return the priority of the node
    public abstract V getValue();	// returns the value stored by the node
    public void show() {
	System.out.println(this.getPriority());
    }
}

// Interface for Queue
interface QueueInterface<V> {
    public int size(); // Returns the size, i.e. the number of elements in the queue
    public boolean isEmpty(); // Returns true if the queue is empty, else returns false
    public boolean isFull();  // Returns true if the queue is full, else returns false
    public void enqueue(Nodes<V> item); // Adds an item to the rear of the queue
    public NodeBase<V> dequeue(); // Removes an item from the front of the queue
}