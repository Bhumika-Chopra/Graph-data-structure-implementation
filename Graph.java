import Util.ArrayList;
import RedBlack.RBTree;

public class Graph implements Graph_{
	ArrayList<Triangle> arr = new ArrayList<Triangle>();
	ArrayList<components> lcom = new ArrayList<components>();
	@Override
	public Triangle find(Triangle t) {
		for(int i=0; i<arr.size(); i++) {
			if(arr.get(i).compareTo(t) == 0) {
				return arr.get(i);
			}
		}
		return null;
	}

	@Override
	public void add(Triangle t) {
		boolean ispartof = false;
		int count = 0;
		ArrayList<components> indices = new ArrayList<components>();
		for(int i=0; i<arr.size(); i++) {
			//if ((arr.get(i).triangle_coord()[0].compareTo(t.triangle_coord()[0]) == 1 && arr.get(i).triangle_coord()[1].compareTo(t.p2) == 1 ) || (p2.compareTo(t.p2) == 1 && p3.compareTo(t.p3) ==1) || (p1.compareTo(t.p2) == 1 && p3.compareTo(t.p3) ==1))
			if(arr.get(i).compareTo(t) == 2) {
				components temp = new components();
//				if(t.c.connected != 0 && t.c.connected <= arr.get(i).c.connected) {
//					arr.get(i).c.connected = t.c.connected;
//				}
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
	}
}
