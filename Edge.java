
public class Edge implements EdgeInterface, Comparable<Edge> {
	Point p1,p2;
	int numtriangle;
	public Edge(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		numtriangle = 1;
	}
	
	public boolean contains(Point p) {
		if(p1.compareTo(p) == 0 || p2.compareTo(p) == 0)
			return true;
		return false;
	}
	
	public float length() {
		float x = p1.getX() - p2.getX();
		float y = p1.getY() - p2.getY();
		float z = p1.getZ() - p2.getZ();
		return x*x + y*y + z*z;
	}
	
	@Override
	public PointInterface[] edgeEndPoints() {
		Point[] p = {p1,p2};
		return p;
	}
	@Override
	public int compareTo(Edge arg0) {
		if((p1.compareTo(arg0.p1) == 0 && p2.compareTo(arg0.p2) == 0) || (p1.compareTo(arg0.p2) == 0 && p2.compareTo(arg0.p1) == 0) )
			return 0;
		return 1;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		Edge e = (Edge) obj;
//		if((p1.compareTo(e.p1) == 0 && p2.compareTo(e.p2) == 0) || (p1.compareTo(e.p2) == 0 && p2.compareTo(e.p1) == 0) )
//			return true;
//		return false;
//	}
	
	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
//		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
//		return result;
		return p1.hashCode() + p2.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return ("[ " +  p1.toString() + "," + p2.toString() + " ]");
	}
}
