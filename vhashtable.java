import RedBlack.RBTree;
import RedBlack.RedBlackNode;
import Util.ArrayList;

public class vhashtable {
	
	RBTree<Key2, Triangle> vertices = new RBTree<Key2, Triangle>();
	int size;
	static LinkedList<RedBlackNode<Key2, Triangle>>[] list;
	public vhashtable() {
		size = 29;
		list = new LinkedList[29];
		for(int i=0; i<29; i++) {
			list[i] = new LinkedList<RedBlackNode<Key2, Triangle>>();
		}
	}
	public static ArrayList<Triangle> merge1 (ArrayList<Triangle> a1, ArrayList<Triangle> a2) {
		int i=0,j=0;
		ArrayList<Triangle> temp = new ArrayList<Triangle>();
		while(i<a1.size() && j<a2.size()) {
			if(a1.get(i).o == a2.get(j).o) {
				temp.add(a1.get(i));
				i++; j++;
			}
			else if(a1.get(i).o < a2.get(j).o) {
				i++;
			}
			else {
				j++;
			}
		}
		return temp;
	}
	
	public static ArrayList<Triangle> merge (ArrayList<Triangle> a1, ArrayList<Triangle> a2, Triangle t) {
		int i=0,j=0;
		ArrayList<Triangle> temp = new ArrayList<Triangle>();
		while(i<a1.size() && j<a2.size()) {
			if(a1.get(i).o == a2.get(j).o) {
				if(a1.get(i).compareTo(t) != 0)	
					temp.add(a1.get(i));
				i++; j++;
			}
			else if(a1.get(i).o < a2.get(j).o) {
				if(a1.get(i).compareTo(t) != 0)
					temp.add(a1.get(i));
				i++;
			}
			else {
				if(a2.get(j).compareTo(t) != 0)
					temp.add(a2.get(j));
				j++;
			}
		}
		while(i<a1.size()) {
			if(a1.get(i).compareTo(t) != 0)
				temp.add(a1.get(i));
			i++;
		}
		while(j<a2.size()) {
			if(a2.get(j).compareTo(t) != 0)
				temp.add(a2.get(j));
			j++;
		}
		return temp;
	}
	
	public ArrayList<Triangle> searcht(Triangle t) {
		Point[] p = (Point[]) t.triangle_coord();
		ArrayList<Triangle> t1 = searchv(p[0].hashCode(), p[0]);
		ArrayList<Triangle> t2 = searchv(p[1].hashCode(), p[1]);
		ArrayList<Triangle> t3 = searchv(p[2].hashCode(), p[2]);
		ArrayList<Triangle> arr = merge(t1,t2,t);
		return merge(arr, t3,t);
	}


	public void add(Triangle t) {
		Point[] p = (Point[]) t.triangle_coord();
		Key2 k1 = new Key2(p[0]);
		Key2 k2 = new Key2(p[1]);
		Key2 k3 = new Key2(p[2]);
		RedBlackNode<Key2, Triangle> r1 = vertices.insert(k1, t);
		RedBlackNode<Key2, Triangle> r2 = vertices.insert(k2, t);
		RedBlackNode<Key2, Triangle> r3 = vertices.insert(k3, t);
		int i1 = (t.p1.hashCode())%29;
		int i2 = (t.p2.hashCode())%29;
		int i3 = (t.p3.hashCode())%29;
		if(!search(t.p1, t.p1.hashCode()) && r1!=null)
			list[i1].add(r1);
		if(!search(t.p2, t.p2.hashCode()))
			list[i2].add(r2);
		if(!search(t.p3, t.p3.hashCode()))
			list[i3].add(r3);
	}
	
	public boolean search(Point p, int h) {
		int index = h%29;
		Key2 k = new Key2(p);
		Position<RedBlackNode<Key2, Triangle>> root = list[index].head;
		while(root !=  null) {
			//System.out.println(root.data.key + "gfdsgds");
			if(root.data.key.compareTo(k) == 0) {
				return true;
			}
			root = root.n;
		}
		return false;
	}
	
	public ArrayList<Triangle> searchv(int h, Point p) {
		int index = h%size;
		Key2 k = new Key2(p);
		Position<RedBlackNode<Key2, Triangle>> root = list[index].head;
		while(root !=  null) {
			if(root.data.key.compareTo(k) == 0) {
				return root.data.getValues();
			}
			root = root.n;
		}
		return null;
	}
}
