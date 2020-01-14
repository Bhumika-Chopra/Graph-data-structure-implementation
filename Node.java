
public class Node {
	float key;
	Edge value;
	Node left;
	Node right;
	public Node(float key, Edge Value) {
		this.key = key;
		this.value = Value;
		left = right = null;
	}
}
