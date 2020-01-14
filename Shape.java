
import RedBlack.RBTree;
import Util.ArrayList;

public class Shape implements ShapeInterface
{
	//think if you need to make a rbtree of edges wiht associated triangles
	//think if you need to maintain an arraylist of points, triangles, and edges?
	BST edges = new BST();
	Hashtable ttable = new Hashtable();
	vhashtable vtable = new vhashtable();
	//Graph tgraph = new Graph();
	//Graph2 vgraph = new Graph2();
	public boolean ADD_TRIANGLE(float [] triangle_coord){
		boolean var;
		Point ab = new Point(triangle_coord[3] - triangle_coord[0], triangle_coord[4] - triangle_coord[1], triangle_coord[5] - triangle_coord[2]);
		Point bc = new Point(triangle_coord[6] - triangle_coord[0], triangle_coord[7] - triangle_coord[1], triangle_coord[8] - triangle_coord[2]);
		if(bc.getX()*ab.getY() == bc.getY()*ab.getX() && bc.getY()*ab.getZ() == bc.getZ()*ab.getY() && bc.getX()*ab.getZ() == bc.getZ()*ab.getX()) {
			return false;
		}
		else { 
			Point p1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
			Point p2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
			Point p3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
			Triangle t = new Triangle(p1, p2, p3);
			//System.out.println(t.hashCode() + "shape");
			ttable.add(t, t.hashCode());
			//tgraph.add(t);
			vtable.add(t);
			Edge e1 = new Edge(p1, p2);
			Edge e2 = new Edge(p2, p3);
			Edge e3 = new Edge(p1, p3);
			//System.out.println("adding : " + e1 + ", " + e2 + ", "+e3);
			edges.insert(e1);
			edges.insert(e2);
			edges.insert(e3);
			return true;
		}
		
	}
	public int TYPE_MESH(){
		return edges.find_mesh();
	}
	
	//check if temp is working properly
	public EdgeInterface [] BOUNDARY_EDGES(){
		//only for semi-proper mesh, return edges in ascending order
		ArrayList<Edge> temp = edges.boundaryedges();
			if(temp.size() != 0) {
				//edges.inordertraversal();
				Edge[] boundary = new Edge[temp.size()];
				for(int i=0; i<temp.size(); i++) {
					boundary[i] = temp.get(i);
				}
				return boundary;
			}
			else
				return null;
	}
	
	public int COUNT_CONNECTED_COMPONENTS(){
		return Triangle.total;
	}
	
	public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){
		Point p1 = new Point(triangle_coord_1[0], triangle_coord_1[1], triangle_coord_1[2]);
		Point p2 = new Point(triangle_coord_1[3], triangle_coord_1[4], triangle_coord_1[5]);
		Point p3 = new Point(triangle_coord_1[6], triangle_coord_1[7], triangle_coord_1[8]);
		Triangle t = new Triangle(p1, p2, p3);
		Point p4 = new Point(triangle_coord_2[0], triangle_coord_2[1], triangle_coord_2[2]);
		Point p5 = new Point(triangle_coord_2[3], triangle_coord_2[4], triangle_coord_2[5]);
		Point p6 = new Point(triangle_coord_2[6], triangle_coord_2[7], triangle_coord_2[8]);
		Triangle t1 = new Triangle(p4, p5, p6);
		Triangle s = ttable.search(t.hashCode(), t);
		Triangle s1 = ttable.search(t1.hashCode(), t1);
		if(s != null && s1 != null) {
			if(s.c.connected == s1.c.connected)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){
		Point p1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point p2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point p3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
		Triangle t = new Triangle(p1, p2, p3);
		Triangle t1 = ttable.search(t.hashCode(), t);
		if(t1 != null) {
			ArrayList<Triangle> temp = t1.neighbor;
			Triangle[] boundary = new Triangle[temp.size()];
			for(int i=0; i<temp.size(); i++) {
				boundary[i] = temp.get(i);
			}
			return boundary;
		}
		else 
			return null;
	}
	
	public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		Point p1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point p2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point p3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
		Triangle t = new Triangle(p1, p2, p3);
		Triangle t1 = ttable.search(t.hashCode(), t);
		if(t1 != null) {
			Edge[] edges = {new Edge(p1, p2) ,new Edge(p1, p3), new Edge(p2, p3)};
			return edges;
		}
		else
			return null;
	}
	
	public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		Point p1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point p2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point p3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
		Triangle t = new Triangle(p1, p2, p3);
		Triangle t1 = ttable.search(t.hashCode(), t);
		if(t1 != null) {
			Point[] points = {p1, p2, p3};
			return points;
		}
		else
			return null;
	}
	
	public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		Point p1 = new Point(triangle_coord[0], triangle_coord[1], triangle_coord[2]);
		Point p2 = new Point(triangle_coord[3], triangle_coord[4], triangle_coord[5]);
		Point p3 = new Point(triangle_coord[6], triangle_coord[7], triangle_coord[8]);
		Triangle t = new Triangle(p1, p2, p3);
		Triangle t1 = ttable.search(t.hashCode(), t);
		if(t1 != null) {
			ArrayList<Triangle> arr = vtable.searcht(t1);
			if(arr.size() != 0) {
				Triangle[] boundary = new Triangle[arr.size()];
				for(int i=0; i<arr.size(); i++) {
					boundary[i] = arr.get(i);
				}
				return boundary;
			}
			else
				return null;
		}
		else
			return null;
	}
	
	public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){
		Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		ArrayList<Triangle> arr = vtable.searchv(p.hashCode(), p);
		if(arr.size() != 0) {
			Triangle[] boundary = new Triangle[arr.size()];
			for(int i=0; i<arr.size(); i++) {
				boundary[i] = arr.get(i);
			}
			return boundary;
		}
		else
			return null;
	}
	
	//optimize 
	public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){
		Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		ArrayList<Triangle> arr = vtable.searchv(p.hashCode(), p);
		if(arr.size() != 0 ) {
			ArrayList<Point> v = new ArrayList<Point>(); 
			//ArrayList<Point> temp = new ArrayList<Point>();
			for(int i=0; i<arr.size(); i++) {
				if(p.compareTo(arr.get(i).p1) == 0) {
					if(!v.contains(arr.get(i).p2))
						v.add(arr.get(i).p2);
					if(!v.contains(arr.get(i).p3))
						v.add(arr.get(i).p3);
				}
				else if(p.compareTo(arr.get(i).p2) == 0) {
					if(!v.contains(arr.get(i).p1))
						v.add(arr.get(i).p1);
					if(!v.contains(arr.get(i).p3))
						v.add(arr.get(i).p3);
				}
				else if(p.compareTo(arr.get(i).p3) == 0) {
					if(!v.contains(arr.get(i).p2))
						v.add(arr.get(i).p2);
					if(!v.contains(arr.get(i).p1))
						v.add(arr.get(i).p1);
				}
			}
			Point[] boundary = new Point[v.size()];
			for(int i=0; i<v.size(); i++) {
				boundary[i] = v.get(i);
			}
			//System.out.println(boundary.length);
			return boundary;
		}
		else
			return null;
	}
	
	//bhaiya ka tareeka khud ka map define karo like an rbtree
	//define search and insert - rbtree search and insert
	public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){
		Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		ArrayList<Triangle> arr = vtable.searchv(p.hashCode(), p);
		if(arr.size() != 0) {
			ArrayList<Edge> v = new ArrayList<Edge>(); 
			for(int i=0; i<arr.size(); i++) {
				if(arr.get(i).e1.contains(p)) {
					if(!v.contains(arr.get(i).e1))
						v.add(arr.get(i).e1);
				}
				if(arr.get(i).e2.contains(p)) {
					if(!v.contains(arr.get(i).e2))
						v.add(arr.get(i).e2);
				}
				if(arr.get(i).e3.contains(p)) {
					if(!v.contains(arr.get(i).e3))
						v.add(arr.get(i).e3);
				}
			}
			Edge[] boundary = new Edge[v.size()];
			for(int i=0; i<v.size(); i++) {
				boundary[i] = v.get(i);
			}
			return boundary;
		}
		else
			return null;
	}
	
	public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){
		Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
		ArrayList<Triangle> arr = vtable.searchv(p.hashCode(), p);
		if(arr.size() != 0) {
			Triangle[] boundary = new Triangle[arr.size()];
			for(int i=0; i<arr.size(); i++) {
				boundary[i] = arr.get(i);
				//System.out.println(arr.get(i));
			}
			return boundary;
		}
		else
			return null;
		
	}
	
	public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){
		Point p1 = new Point(edge_coordinates[0], edge_coordinates[1], edge_coordinates[2]);
		Point p2 = new Point(edge_coordinates[3], edge_coordinates[4], edge_coordinates[5]);
		Edge e = new Edge(p1, p2);
		if(edges.search(e) != null) {
			ArrayList<Triangle> a = vtable.searchv(p1.hashCode(), p1);
//			for(int i=0; i<a.size(); i++) {
//				System.out.println(a.get(i).o);
//			}
			//System.out.println();
			ArrayList<Triangle> b = vtable.searchv(p2.hashCode(), p2);
//			for(int i=0; i<b.size(); i++) {
//				System.out.println(b.get(i).o);
//			}
			ArrayList<Triangle> temp = Graph2.merge1(a, b);
			Triangle[] boundary = new Triangle[temp.size()];
			//System.out.println(temp.size());
			for(int i=0; i<temp.size(); i++) {
				boundary[i] = temp.get(i);
			}
			return boundary;
			
		}
		else
			return null;
	}
	
	//merge sort right now
	public PointInterface [] CENTROID (){
		RBTree<Integer, Triangle> rlcom = ttable.rlcom;
		//System.out.println(lcom.size());
		ArrayList<Point> points = new ArrayList<Point>();
		for(int i=0; i<ttable.number.size(); i++) {
			points.add(components.centroid(rlcom.search(ttable.number.get(i)).getValues()));
		}
//		for(int i=0; i<lcom.size(); i++) {
//			points.add(lcom.get(i).centroid());
//		}
		Point[] boundary = new Point[points.size()];
		for(int i=0; i<points.size(); i++) {
			boundary[i] = points.get(i);
			System.out.println(boundary[i]+ "  fdsfdsafasfdsfdsaf");
		}
		mergesort(boundary, 0, boundary.length-1);
		return boundary;
		}
	
		public void merge(Point[] temp, int l, int m, int r) {
			Point[] l1 = new Point[m-l+1];
			Point[] l2 = new Point[r-m];
			for(int i=0; i<m-l+1; i++) {
				l1[i] = temp[l+i];
				//System.out.println(l1[i]);
			}
			for(int j=0; j<r-m; j++) {
				//System.out.println(l2[j]);
				l2[j] = temp[j+m+1];
			}
			int i=0,j=0,k=l;
			while(i<l1.length && j<l2.length) {
				if(l1[i].compareTo(l2[j]) == -1 || l1[i].compareTo(l2[j]) == 0) {
					temp[k] = l1[i];
					i++;
					k++;
				}
				else {
					temp[k] = l2[j];
					j++; 
					k++;
				}	
			}
			while(i<l1.length) {
				temp[k] = l1[i];
				i++;
				k++;
			}
			while(j<l2.length) {
				temp[k] = l2[j];
				j++;
				k++;
			}
			
		}
	
		public void mergesort(Point[] temp, int l, int r) {
			if(l<r) {
				int mid = (l+r)/2;
				mergesort(temp,l,mid);
				mergesort(temp,mid+1,r);
				merge(temp,l,mid,r);
			}
		}
		
		public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
			Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]);
			ArrayList<Triangle> arr = vtable.searchv(p.hashCode(), p);
			ArrayList<components> lcom = ttable.lcom;
			for(int i=0; i<lcom.size(); i++) {
				if(lcom.get(i).contains(arr.get(0)) != null) {
					return lcom.get(i).centroid1();
				}
			}
			return null;
		}
		
		public PointInterface [] CLOSEST_COMPONENTS(){
			ArrayList<components> lcom = ttable.lcom;
			if(lcom.size() > 1) {
				Node1 n = find_min_dist(lcom.get(0).v, lcom.get(1).v);
				Node1 n1 = null;
				for(int i=0; i<lcom.size(); i++) {
					for(int j=i+1; j<lcom.size(); j++) {
						n1 = find_min_dist(lcom.get(i).v, lcom.get(j).v);
						if(n1.val < n.val) {
							n = n1;
						}
					}
				}
				return n.p;
			}
			else
				return null;
		}
		
		float dist(Point p1, Point p2) {
			float x = (p1.x-p2.x)*(p1.x-p2.x);
			float y = (p1.y-p2.y)*(p1.y-p2.y);
			float z = (p1.z-p2.z)*(p1.z-p2.z);
			return (float) Math.sqrt(x+y+z);
		}
		
		private Node1 find_min_dist(ArrayList<Point> a1, ArrayList<Point> a2) {
			float min = dist(a1.get(0), a2.get(0));
			Node1 n = null;
			for(int i=0; i<a1.size(); i++) {
				for(int j=0; j<a2.size(); j++) {
					float dist = dist(a1.get(i), a2.get(j));
					if(dist < min) {
						min = dist;
						n = new Node1(a1.get(i), a2.get(j), min);
					}
				}
			}
			return n;
		}
		
		public int MAXIMUM_DIAMETER(){
			ArrayList<components> lcom = ttable.lcom;
			int max = lcom.get(0).size;
			components arr = lcom.get(0);
//			System.out.println(lcom.size());
			for(int i=0; i<lcom.size(); i++) {
				//System.out.println(lcom.get(i).size);
				if(lcom.get(i).size > max) {
					max = lcom.get(i).size;
					arr = lcom.get(i);
				}
			}
			//System.out.println(arr.size);
			int max_dia = 0;
			for(int i=0 ; i<arr.size; i++) {
				int n = components.BFS(arr.arr.get(i));
				if(max_dia < n) {
					max_dia = n;
				}
			}
			return max_dia;
		}

		
//	public static void main(String[] args) {
//		Shape s = new Shape();
//		ADD_TRIANGLE -1 0 0 0 -1 0 0 0 3
//		ADD_TRIANGLE -1 0 0 0 -1 0 0 1 0
//		ADD_TRIANGLE -1 0 0 0 0 3 0 1 0
//		ADD_TRIANGLE 1 0 0 0 -1 0 0 1 0
//		ADD_TRIANGLE 1 0 0 0 -1 0 0 0 3
//		ADD_TRIANGLE 1 0 0 0 1 0 0 0 3
//		float[] triangle_coord1 = {-1,0,0,0,-1,0,0,0,3};
//		float[] triangle_coord2 = {-1,0,0,0,-1,0,0,1,0};
//		float[] triangle_coord3 = {-1,0,0,0,0,3,0,1,0};
//		float[] triangle_coord4 = {1,0,0,0,-1,0,0,1,0};
//		float[] triangle_coord5 = {1,0,0,0,-1,0,0,0,3};
//		float[] triangle_coord6 = {1,0,0,0,1,0,0,0,3};
//		System.out.println(s.ADD_TRIANGLE(triangle_coord1));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord2));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord3));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord4));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord5));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord6));
//		System.out.println(s.TYPE_MESH());
//		//COUNT_CONNECTED_COMPONENTS
//		System.out.println(s.TYPE_MESH());
//		System.out.println(s.COUNT_CONNECTED_COMPONENTS());
//		//IS_CONNECTED  -1 0 0 0 -1 0 0 0 3  1 0 0 0 1 0 0 0 3
//		System.out.println(s.IS_CONNECTED(triangle_coord1, triangle_coord6));
////		Edge[] edge = (Edge[]) s.BOUNDARY_EDGES();
//		System.out.println(s.BOUNDARY_EDGES());
//		System.out.println(s.COUNT_CONNECTED_COMPONENTS() + "r");
//		System.out.println(s.IS_CONNECTED(triangle_coord1, triangle_coord6) + "r");
//		System.out.println(s.BOUNDARY_EDGES());
//		//NEIGHBORS_OF_POINT -1 0 0
//		System.out.println();
//		System.out.println("NEIGHBOUR OF POINT");
//		float[] n = {-1,0,0};
//		Point[] p = (Point[]) s.NEIGHBORS_OF_POINT(n);
//		if(p == null) {
//			System.out.println(p);
//		}
//		else {
//			for(int i=0; i<p.length; i++) {
//				System.out.println(p[i]);
//			}
//		}
////		//NEIGHBORS_OF_TRIANGLE -1 0 0 0 -1 0 0 0 3
//		System.out.println();
//		System.out.println("NEIGHBOUR OF TRIANGLE");
//		float[] triangle_coord = {-1,0,0,0,-1,0,0,0,3};
//		Triangle[] t = (Triangle[]) s.NEIGHBORS_OF_TRIANGLE(triangle_coord);
//		for(int i=0; i<t.length; i++) {
//			System.out.println(t[i]);
//		}
////		//INCIDENT_TRIANGLES  1 0 0
//		float[] n1 = {1,0,0};
//		System.out.println();
//		System.out.println("INCIDENT TRIANGLES");
//		Triangle[] t1 = (Triangle[]) s.INCIDENT_TRIANGLES(n1);
//		for(int i=0; i<t1.length; i++) {
//			System.out.println(t1[i]);
//		}
////		VERTEX_NEIGHBOR_TRIANGLE -1 0 0 0 -1 0 0 0 3
//		System.out.println();
//		System.out.println("VERTEX NEIGHBOUR OF TRIANGLES");
//		Point[] p1 = (Point[]) s.VERTEX_NEIGHBOR_TRIANGLE(triangle_coord);
//		for(int i=0; i<p1.length; i++) {
//			System.out.println(p1[i]);
//		}
//////		EXTENDED NEIGHBOR_TRIANGLE -1 0 0 0 -1 0 0 0 3
//		System.out.println();
//		System.out.println("EXTENDED NEIGHBOUR OF TRIANGLES");
//		Triangle[] t2 = (Triangle[]) s.EXTENDED_NEIGHBOR_TRIANGLE(triangle_coord);
//		for(int i=0; i<t2.length; i++) {
//			System.out.println(t2[i]);
//		}
//		System.out.println();
//		System.out.println("EXTENDED NEIGHBOUR OF TRIANGLES");
//		Triangle[] t21 = (Triangle[]) s.EXTENDED_NEIGHBOR_TRIANGLE(triangle_coord);
//		for(int i=0; i<t21.length; i++) {
//			System.out.println(t21[i]);
//		}
//		System.out.println();
//		System.out.println("MAXIMUM DIAMETER");
//		System.out.println(s.MAXIMUM_DIAMETER());
////		//EDGE_NEIGHBOR_TRIANGLE -1 0 0 0 -1 0 0 0 3
//		System.out.println();
//		System.out.println("EDGE MEIGHBOUR OF TRIANGLES");
//		Edge[] e = (Edge[]) s.EDGE_NEIGHBOR_TRIANGLE(triangle_coord);
//		for(int i=0; i<e.length; i++) {
//			System.out.println(e[i]);
//		}
//		System.out.println();
//		System.out.println("MAXIMUM DIAMETER");
//		System.out.println(s.MAXIMUM_DIAMETER());
////		//TRIANGLE_NEIGHBOR_OF_EDGE -1 0 0 0 -1 0
//		System.out.println();
//		System.out.println("TRIANGLE NEIGHBOUR OF EDGE");
//		float[] edge_coordinates = {-1,0,0,0,-1,0};
//		Triangle[] t3 = (Triangle[]) s.TRIANGLE_NEIGHBOR_OF_EDGE(edge_coordinates);
//		for(int i=0; i<t3.length; i++) {
//			System.out.println(t3[i]);
//		}
//		System.out.println();
//		System.out.println("TRIANGLE NEIGHBOUR OF EDGE");
//		Triangle[] t31 = (Triangle[]) s.TRIANGLE_NEIGHBOR_OF_EDGE(edge_coordinates);
//		for(int i=0; i<t31.length; i++) {
//			System.out.println(t31[i]);
//		}
//		ADD_TRIANGLE -1 0 0 0 -1 0 0 0 3
//
//		ADD_TRIANGLE -1 0 0 0 -1 0 0 1 0
//
//		ADD_TRIANGLE -1 0 0 0 0 3 0 1 0
//
//		ADD_TRIANGLE 1 0 0 0 -1 0 0 1 0
//
//		ADD_TRIANGLE 1 0 0 0 1 0 0 0 3
//
//		TYPE_MESH
//
//		COUNT_CONNECTED_COMPONENTS
//
		//BOUNDARY_EDGES
//		float[] triangle_coord1 = {-1,0,0,0,-1,0,0,0,3};
//		float[] triangle_coord2 = {-1,0,0,0,-1,0,0,1,0};
//		float[] triangle_coord3 = {-1,0,0,0,0,3,0,1,0};
//		float[] triangle_coord4 = {1,0,0,0,-1,0,0,1,0};
//		float[] triangle_coord5 = {1,0,0,0,1,0,0,0,3};
//		System.out.println(s.ADD_TRIANGLE(triangle_coord1));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord2));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord3));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord4));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord5));
//		System.out.println(s.TYPE_MESH());
//		System.out.println(s.COUNT_CONNECTED_COMPONENTS());
//		Edge[] edge = (Edge[]) s.BOUNDARY_EDGES();
//		for(int i=0; i<edge.length; i++) {
//			System.out.println(edge[i]);
//		}
//		System.out.println();
//		ADD_TRIANGLE -1 0 0 0 -1 0 0 1 0
//
//		ADD_TRIANGLE 0 -1 0 0 0 3 0 1 0
//
//		ADD_TRIANGLE 0 1 0 0 -1 0 1 0 0
//
//		TYPE_MESH
//
//		NEIGHBORS_OF_POINT -1 0 0 
//		ADD_TRIANGLE -1 0 0 0 -1 0 0 1 0
//
//		ADD_TRIANGLE 0 -1 0 0 0 3 0 1 0
//
//		ADD_TRIANGLE 0 1 0 0 -1 0 1 0 0
//		float[] triangle_coord1 = {-1,0,0,0,-1,0,0,1,0};
//		float[] triangle_coord2 = {0,-1,0,0,0,3,0,1,0};
//		float[] triangle_coord3 = {0,1,0,0,-1,0,1,0,0};
//		float[] triangle_coord4 = {1,1,1,9,-1,8,2,5,3};
//		System.out.println(s.ADD_TRIANGLE(triangle_coord1));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord2));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord3));
//		System.out.println(s.ADD_TRIANGLE(triangle_coord4));
//		System.out.println(s.TYPE_MESH() + "hello");
//		System.out.println(s.COUNT_CONNECTED_COMPONENTS());
//		float[] n = {0,-1,0};
//		Point[] p = (Point[]) s.NEIGHBORS_OF_POINT(n);
//		if(p == null) {
//			System.out.println(p);
//		}
//		for(int i=0; i<p.length; i++) {
//			System.out.println(p[i]);
//		}
//		System.out.println();
//		System.out.println("EDGE NEIGHBOUR OF POINT");
//		Edge[] p1 = (Edge[]) s.EDGE_NEIGHBORS_OF_POINT(n);
//		if(p1 == null) {
//			System.out.println(p1);
//		}
//		for(int i=0; i<p1.length; i++) {
//			System.out.println(p1[i]);
//		}
//		ADD_TRIANGLE -1 0 0 0 -1 0 0 1 0
//
//		ADD_TRIANGLE 3 4 5 6 7 8 9 10 12
//
//		COUNT_CONNECTED_COMPONENTS
//
		//IS_CONNECTED -1 0 0 0 -1 0 0 1 0 3 4 5 6 7 8 9 10 12
//		float[] cood = {-1,0,0,0,-1,0,0,1,0};
//		float[] cood2 = {3,4,5,6,7,8,9,10,12};
//		System.out.println(s.ADD_TRIANGLE(cood));
//		System.out.println(s.ADD_TRIANGLE(cood2));
//		//System.out.println(s.ADD_TRIANGLE(cood3));
////		System.out.println(s.TYPE_MESH() + "hello");
//		System.out.println(s.COUNT_CONNECTED_COMPONENTS());
//		System.out.println(s.IS_CONNECTED(cood, cood2));
//		Point[] p = (Point[]) s.CENTROID();
//		for(int i=0; i<p.length; i++) {
//			System.out.println(p[i]);
//		}
//		float[] p1 = {9,10,12};
//		System.out.println(s.CENTROID_OF_COMPONENT(p1));
//		Point[] t = (Point[]) s.CLOSEST_COMPONENTS();
//		for(int i=0; i<t.length; i++) {
//			System.out.println(t[i]);
//		}
//		System.out.println(s.MAXIMUM_DIAMETER());
//	}
}

