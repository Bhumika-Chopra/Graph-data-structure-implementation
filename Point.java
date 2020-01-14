
public class Point implements PointInterface,Comparable<Point> {
	float x;
	float y;
	float z;
	//ArrayList<Triangle> face_neighbor;
	
	public Point(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		//face_neighbor = new ArrayList<Triangle>();
	}
	
//	public void add(Triangle t) {
//		face_neighbor.add(t);
//	}
	
	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getZ() {
		return z;
	}

	@Override
	public float[] getXYZcoordinate() {
		float[] point = {x,y,z};
		return point;
	}
	
	public float distance(Point p) {
		return p.x * p.x + p.y * p.y + p.z * p.z;
	}
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return java.lang.Math.abs(result);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}

	@Override
	public int compareTo(Point arg0) {
		if(this.x == arg0.getX() && this.y == arg0.getY() && this.z == arg0.getZ()) 
			return 0;
		else if(this.x < arg0.x) 
			return -1;
		else if(this.x == arg0.x && this.y<arg0.y)
			return -1;
		else if(this.x == arg0.x && this.y == arg0.y && this.z < arg0.z) 
			return -1;
		return 1;
	}

//	@Override
//	public boolean equals(Object obj) {
//		Point p = (Point) obj;
//		if(this.x == p.getX() && this.y == p.getY() && this.z == p.getZ()) 
//			return true;
//		return false;
//	}
	
	@Override
	public String toString() {
		return "[ " + String.valueOf(x) +"," + String.valueOf(y) +"," + String.valueOf(z) + " ]";
	}
}
