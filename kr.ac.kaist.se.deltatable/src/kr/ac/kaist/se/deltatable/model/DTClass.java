package kr.ac.kaist.se.deltatable.model;

public class DTClass<T> {
	private T origin;
	private int index;
	
	public DTClass(T origin, int i)
	{
		this.setOrigin(origin);
		setIndex(i);
	}

	public T getOrigin() {
		return origin;
	}

	public void setOrigin(T origin) {
		this.origin = origin;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
