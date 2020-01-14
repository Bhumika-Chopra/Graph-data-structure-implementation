

public class Position<T> implements Position_<T>{
	public T data;
	public Position<T> n;
	public Position (T data) {
		this.data = data;
		this.n = null;
	}
	public T value() {
		return this.data;
	}
	public Position<T> after() {
		return this.n;
	}
}
