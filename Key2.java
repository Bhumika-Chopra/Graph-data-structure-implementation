
public class Key2 implements Comparable<Key2>{
	Point p;
	public Key2(Point p) {
		this.p = p;
	}
	public float distance(Point p) {
		return p.x * p.x + p.y * p.y + p.z * p.z;
	}
//	@Override
//	public int compareTo(Triangle t) {
//		if(p.compareTo(t.p1) == 0 || p.compareTo(t.p2) == 0 || p.compareTo(t.p3) == 0) {
//			return 0;
//		}
//		//can use an even simpler criteria that is the x coordinate maybe
//		else if(this.distance(p) > distance(t.centroid()) )
//			return 1;
//		return -1;
//	}
	
//	@Override
//	public String toString() {
//		return p.toString();
//	}
	@Override
	public int compareTo(Key2 arg0) {
		if(p.compareTo(arg0.p) == 0) 
			return 0;
		else if(distance(p) == distance(arg0.p)) {
			if(p.x > arg0.p.x)
				return 1;
			else if(p.x == arg0.p.x && p.y > arg0.p.y)
				return 1;
			else if(p.x == arg0.p.x && p.y == arg0.p.y && p.z > arg0.p.z)
				return 1;
		}
		else if(distance(p) > distance(arg0.p))
			return 1;
		return -1;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return p.toString();
	}
}
