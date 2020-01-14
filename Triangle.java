import Util.ArrayList;

public class Triangle implements TriangleInterface, Comparable<Triangle> {
	Point p1,p2,p3;
	Edge e1,e2,e3;
	connected_to c;
	static int order = 0;
	int o;
	static int total = 0;
	ArrayList<Triangle> neighbor;
	public Triangle(Point p1, Point p2, Point p3) {
		//if((p3.getX() - p2.getX()) * (p2.getY() - p1.getY()) == (p3.ge)
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		order++;
		o = order;
		c = new connected_to();
		e1 = new Edge(p1, p2);
		e2 = new Edge(p2, p3);
		e3 = new Edge(p1, p3);
		neighbor = new ArrayList<Triangle>();
	}
	
	public Point centroid() {
		Point p = new Point( (p1.getX() + p2.getX() + p3.getX())/3, (p1.getY() + p2.getY() + p3.getY())/3, (p1.getZ() + p2.getZ() + p3.getZ())/3);
		return p;
	}
	
	public void add(Triangle t) {
		//if ((p1.compareTo(t.p1) == 1 && p2.compareTo(t.p2) == 1 ) || (p2.compareTo(t.p2) == 1 && p3.compareTo(t.p3) ==1) || (p1.compareTo(t.p2) == 1 && p3.compareTo(t.p3) ==1))
		neighbor.add(t);
	}
	@Override
	public PointInterface[] triangle_coord() {
		Point[] p = {p1,p2,p3};
		return p;
	}
	
//	public Edge[] getedges(Triangle t) {
//		Edge e1 = new Edge(t.p1, t.p2);
//		Edge e2 = new Edge(t.p2, t.p3);
//		Edge e3 = new Edge(t.p1, t.p3);
//		Edge[] e = {e1,e2,e3};
//		return e;
//	}
	
	public boolean compareedges (Triangle t) {
		if(e1.compareTo(t.e1) == 0 || e1.compareTo(t.e2) == 0 || e1.compareTo(t.e3) == 0) 
			return true;
		else if(e2.compareTo(t.e1) == 0 || e2.compareTo(t.e2) == 0 || e2.compareTo(t.e3) == 0) 
			return true;
		else if(e3.compareTo(t.e1) == 0 || e3.compareTo(t.e2) == 0 || e3.compareTo(t.e3) == 0)
			return true;
		else
			return false;
	}
	
//	@Override
//	public int hashCode() {
//		return this.o;
//	}
	@Override
	public int compareTo(Triangle arg0) {
		if(p1.compareTo(arg0.p1) == 0 && p2.compareTo(arg0.p2) == 0 && p3.compareTo(arg0.p3) == 0)
			return 0;
		else if (this.compareedges(arg0))
			return 2;
		return -1;
	}
	
	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
		return java.lang.Math.abs(p1.hashCode() + p2.hashCode() + p3.hashCode());
//		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
//		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
//		result = prime * result + ((p3 == null) ? 0 : p3.hashCode());
//		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Triangle other = (Triangle) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		if (p3 == null) {
			if (other.p3 != null)
				return false;
		} else if (!p3.equals(other.p3))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[ " + p1.toString() + "," + p2.toString() + "," + p3.toString() + " ]";
	}
}
