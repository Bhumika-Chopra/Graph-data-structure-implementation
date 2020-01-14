
public class Nodes<V> extends NodeBase<V> {
	public Nodes( int priority, V value) {
		this.value = value;
		this.priority = priority;
	}

	//TODO Complete these methods	
	public int getPriority() {
		return this.priority;
	}

	public V getValue() {
		return this.value;
	}

}
