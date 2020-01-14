import java.lang.reflect.Array;

import Util.ArrayList;

public class components {
	connected_to c;
	int size;
	ArrayList<Point> v = new ArrayList<Point>(); 
	ArrayList<Triangle> arr = new ArrayList<Triangle>();
	public components() {
		size = 0;
		c = new connected_to();
	}
	public void add(Triangle t) {
		if(size == 0) {
			c.connected = t.o;
			t.c = this.c;
			arr.add(t);
			size++;
			if(!v.contains(t.p1))
				v.add(t.p1);
			if(!v.contains(t.p2))
				v.add(t.p2);
			if(!v.contains(t.p3))
				v.add(t.p3);
			return;
		}
		t.c = this.c;
		arr.add(t);
		if(!v.contains(t.p1))
			v.add(t.p1);
		if(!v.contains(t.p2))
			v.add(t.p2);
		if(!v.contains(t.p3))
			v.add(t.p3);
		size++;
	}
	
	public static void empty_list(components c) {
		c = new components();
	}
	
	public Triangle contains(Triangle t) {
		return arr.contains1(t);
	}
	
	public static Point centroid(ArrayList<Triangle> arr) {
			ArrayList<Point> v = new ArrayList<Point>(); 
			for(int i=0; i<arr.size(); i++) {
					if(!v.contains(arr.get(i).p2))
						v.add(arr.get(i).p2);
					if(!v.contains(arr.get(i).p3))
						v.add(arr.get(i).p3);
					if(!v.contains(arr.get(i).p1))
						v.add(arr.get(i).p1);
					if(!v.contains(arr.get(i).p3))
						v.add(arr.get(i).p3);
					if(!v.contains(arr.get(i).p2))
						v.add(arr.get(i).p2);
					if(!v.contains(arr.get(i).p1))
						v.add(arr.get(i).p1);
				}
		float x=0,y=0,z=0;
		for(int i=0; i<v.size(); i++) {
			x += v.get(i).x;
			y += v.get(i).y;
			z += v.get(i).z;
		}
		return new Point(x/v.size(),y/v.size(),z/v.size());
	}
	
	public Point centroid1() {
	float x=0,y=0,z=0;
	for(int i=0; i<v.size(); i++) {
		x += v.get(i).x;
		y += v.get(i).y;
		z += v.get(i).z;
	}
	return new Point(x/v.size(),y/v.size(),z/v.size());
}
	
	
	static int BFS (Triangle t) {
		if(t.neighbor.size() != 0) {
			ArrayList<Triangle> visited = new ArrayList<Triangle>();
			int max = 0;
			Nodes<Triangle> s = new Nodes<Triangle>(0,t);
			Queue<Triangle> queue = new Queue<Triangle>(t.neighbor.size()); 
	//		System.out.println(visited[0] + "gel");
			visited.add(s.getValue());
			queue.enqueue(s);
			while(queue.size() != 0) {
				Nodes<Triangle> g = (Nodes<Triangle>) queue.dequeue();
				//System.out.println(g.d);
				for(int i=0; i<g.getValue().neighbor.size(); i++) {
					Nodes<Triangle> s1 = new Nodes<Triangle>(g.priority+1, g.getValue().neighbor.get(i));
					if(!visited.contains(s1.getValue())) {
						visited.add(s1.getValue());
						queue.enqueue(s1);
					}
				}
				if(g.priority > max) {
					//System.out.println(g.priority);
					max = g.priority;
				}
			}
			//System.out.println(max);
			return max;
		}
		return 0;
	}
	
}
