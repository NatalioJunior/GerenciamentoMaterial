package assistant;

public interface InterList <T> {
	void show();
	void showReverse(); //Não implementado na lista simples.
	void addAfter(T object, int id);
	void addFirst(T object);
	void addLast(T object);
	
	T removeFirst();
	T removeLast();
	T removeId(int id);
	
	T peekFirst();
	T peekLast();
	T search(int id);
}
