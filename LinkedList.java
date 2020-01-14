

public class LinkedList<T> implements LinkedList_<T>{
	public Position<T> head;
	int counter = 0;
	public LinkedList() {
		head = null;
	}
	public Position_<T> add(T e) {
		Position<T> newobj = new Position<T>(e);
		//boolean ispresent = false;
		if(head == null) 
		{
			head = newobj;
		}
		else 
		{
			Position<T> p = head;
			while(p.after() != null)
			{
				p = p.after();
			}
				p.n = newobj;
		}
		counter++;
		return newobj;
	}
	

	@Override
	public int count() {
		return counter;
	}
	

}
