import RedBlack.RBTree;
import Util.ArrayList;

public class Hashtable {
	ArrayList<Integer> number = new ArrayList<Integer>();
	ArrayList<Triangle> arr = new ArrayList<Triangle>();
	ArrayList<components> lcom = new ArrayList<components>();
	RBTree<Integer, Triangle> rlcom = new RBTree<Integer, Triangle>();
	int size;
	LinkedList<Triangle>[] list;
	public Hashtable() {
		size = 29;
		list = new LinkedList[29];
		for(int i=0; i<29; i++) {
			list[i] = new LinkedList<Triangle>();
		}
	}
	
//	public Triangle find(Triangle t) {
//		for(int i=0; i<arr.size(); i++) {
//			if(arr.get(i).compareTo(t) == 0) {
//				return arr.get(i);
//			}
//		}
//		return null;
//	}

	public void add(Triangle t, int h) {
		boolean ispartof = false;
		int count = 0;
		ArrayList<components> indices = new ArrayList<components>();
		for(int i=0; i<arr.size(); i++) {
			//if ((arr.get(i).triangle_coord()[0].compareTo(t.triangle_coord()[0]) == 1 && arr.get(i).triangle_coord()[1].compareTo(t.p2) == 1 ) || (p2.compareTo(t.p2) == 1 && p3.compareTo(t.p3) ==1) || (p1.compareTo(t.p2) == 1 && p3.compareTo(t.p3) ==1))
			if(arr.get(i).compareTo(t) == 2) {
				components temp = new components();
				if(t.c.connected == arr.get(i).c.connected) 
					continue;
				for(int j=0; j<lcom.size(); j++) {
					if(lcom.get(j).contains(arr.get(i)) != null) {
						temp = lcom.get(j);
						if(count == 0)
							temp.add(t);
						indices.add(temp);
						count++;
					}
				}
				arr.get(i).add(t);
				ispartof = true;
			}
		}
		if(count > 1) {
			components temp = indices.get(0);
			for(int i=0; i<indices.get(1).size; i++) {
				temp.add(indices.get(1).arr.get(i));
			}
			if(count == 3) {
				for(int i=0; i<indices.get(2).size; i++) {
					temp.add(indices.get(2).arr.get(i));
				}
			}
			components.empty_list(indices.get(1));
			components.empty_list(indices.get(2));
			Triangle.total -= Triangle.total-count+1;
		}
		if(!ispartof && arr.size() != 0) {
			components temp = new components();
			temp.add(t);
			lcom.add(temp);
			Triangle.total++;
		}
		if(arr.size() == 0) {
			Triangle.total = 1;
			components temp = new components();
			temp.add(t);
			lcom.add(temp);
		}
		arr.add(t);
		int index = h%29;
		//System.out.println(h + "gfdgs");
		list[index].add(t);
		rlcom.insert(t.c.connected, t);
		number.add(t.c.connected);
	}
	
	public Triangle search(int h, Triangle t) {
		int index = h%size;
		Position<Triangle> root = list[index].head;
		while(root !=  null) {
			if(root.data.compareTo(t) == 0) {
				return root.data;
			}
			root = root.n;
		}
		return null;
	}

}
