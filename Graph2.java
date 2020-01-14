import RedBlack.RBTree;
import Util.ArrayList;

public class Graph2{
	RBTree<Key2, Triangle> vertices = new RBTree<Key2, Triangle>();
	
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
		
	public ArrayList<Triangle> findt(Triangle t) {
		Point[] p = (Point[]) t.triangle_coord();
		Key2 k1 = new Key2(p[0]);
		Key2 k2 = new Key2(p[1]);
		Key2 k3 = new Key2(p[2]);
		ArrayList<Triangle> t1 = vertices.search(k1).getValues();
		ArrayList<Triangle> t2 = vertices.search(k2).getValues();
		ArrayList<Triangle> t3 = vertices.search(k3).getValues();
		ArrayList<Triangle> arr = merge(t1,t2,t);
		return merge(arr, t3,t);
	}
	
	public ArrayList<Triangle> findv(Point p) {
		Key2 k = new Key2(p);
		ArrayList<Triangle> t1 = vertices.search(k).getValues();
		return t1;
	}

	public void add(Triangle t) {
		Point[] p = (Point[]) t.triangle_coord();
		Key2 k1 = new Key2(p[0]);
		Key2 k2 = new Key2(p[1]);
		Key2 k3 = new Key2(p[2]);
		vertices.insert(k1, t);
		vertices.insert(k2, t);
		vertices.insert(k3, t);
	}
}
