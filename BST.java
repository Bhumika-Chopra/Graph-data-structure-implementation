import Util.ArrayList;

public class BST {
	Node root;
	int s1,s2,s3;
	public BST() {
		root = null;
		s1 = s2 = s3 = 0;
	}
	public Node search(Edge e) {
		return search(root, e.length(), e);
	}
	private Node search(Node root, float key, Edge e) {  
		if (root == null) 
			return root;
		
	    if (root.key == key) {
	        if(root.value.compareTo(e) == 0) 
	        	return root;
	    }
	    
	    if (key <= root.key) 
	        return search(root.left, key, e); 
	    
	    return search(root.right, key, e);
	} 
	public boolean insert(Edge e) { 
		//System.out.println("inserted edge " + e.toString());
		if(search(e) != null) {
			Node n = search(e);
			n.value.numtriangle++;
			return false;
		}
		else {
			root = insert(root, e.length(), e);
			//System.out.println(e.length());
			return true;
		}
	   } 
	      
	Node insert(Node root, float key, Edge e) { 
		if (root == null) { 
			root = new Node(key, e); 
	        return root; 
	    } 
//		if(key == root.key) {
//			if(root.value.compareTo(e) == 0) {
//				e.numtriangle++;
//				return root;
//			}
//		}
		if (key <= root.key) 
	    	root.left = insert(root.left, key, e); 
	    else if (key > root.key) 
	    	root.right = insert(root.right, key, e); 
	    return root; 
	    } 
	//ArrayList<Integer> temp = new ArrayList<Integer>();
	int find_mesh() {
		s1 = s2 = s3 = 0;
		inorder(root);
		if(s3 > 0) 
			return 3;
		else if(s1 > 0) 
			return 2;
		else
			return 1;
	}
	void inorder(Node root) {
		if(root == null) 
			return;
		inorder(root.left);
		if(root.value.numtriangle > 2)
			s3++;
		else if (root.value.numtriangle == 1) 
			s1++;
		else
			s2++;
		inorder(root.right);
	}
	ArrayList<Edge> temp;
	
	ArrayList<Edge> boundaryedges() {
		temp = new ArrayList<Edge>();
		inorder1(root);
		return temp;
		
	}

	void inorder1(Node root) {
		if(root == null) 
			return;
		inorder1(root.left);
		if(root.value.numtriangle == 1)	
			temp.add(root.value);
		inorder1(root.right);
	}
	
	void inordertraversal() {
		inordertraversal(root);
	}
	
	void inordertraversal(Node root) {
		if(root == null) 
			return;
		inordertraversal(root.left);
		System.out.println(root.key + " " + root.value + " " + root.value.numtriangle);
		inordertraversal(root.right);
	}
	
}
