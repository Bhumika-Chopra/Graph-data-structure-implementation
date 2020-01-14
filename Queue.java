// This class implements the Queue
public class Queue<V> implements QueueInterface<V>{

	//TODO Complete the Queue implementation
    private NodeBase<V>[] queue;
    private int capacity, currentSize, front, rear;
    
    @SuppressWarnings("unchecked")
    public Queue(int capacity) {    
    	this.capacity = capacity;
    	this.currentSize = 0;
    	this.front = -1;
    	this.rear = -1;
    	queue = new NodeBase[capacity] ;
    }

    public int size() {
    	return currentSize;
    }

    public boolean isEmpty() {
    	return (currentSize == 0);
    }
	
    public boolean isFull() {
    	return (currentSize == capacity);
    }

    public void enqueue(Nodes<V> node) {
    	if(!this.isFull()) {
    		if(rear == capacity -1) {
    			rear = 0;
    			queue[rear] = node;
    			currentSize++;
    		}
    		else if (rear == -1){
    			rear++;
    			front++;
    			queue[rear] = node;
    			currentSize++;
    		}
    		else{
    			rear++;
    			queue[rear] = node;
    			currentSize++;
    		}
    	}
    }

    @SuppressWarnings("unchecked")    
    public NodeBase<V> dequeue() {
    	if(this.isEmpty()) {
    		return null;
    	}
    	else {
    		if(front == rear) {
    			Nodes<V> element = (Nodes<V>) queue[front];
    			front = rear = -1;
    			currentSize--;
    			return (NodeBase<V>) element;
    		}
    		else if(front == capacity - 1) {
    			NodeBase<V> element = (NodeBase<V>) queue[front];
    			front = 0;
    			currentSize--;
    			return (NodeBase<V>) element;
    		}
    		else {
    			NodeBase<V> element = (NodeBase<V>) queue[front];
    			front++;
    			currentSize--;
    			return (NodeBase<V>) element;
    		}
    	}
    	
    }
}

