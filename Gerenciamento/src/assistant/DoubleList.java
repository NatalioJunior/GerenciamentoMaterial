package assistant;

import exceptions.GeneralException;

public class DoubleList <T> implements InterList <T>{
	//Classe para elementos internos da lista;
		class Element{
			//Atributos da classe Element;
			int id;
			T object;
			Element next;
			Element prev;
			
			//Construtor da classe Element;
			public Element(T object) {
				this.id = nextId;
				nextId++;
				this.object = object;
				this.next = null;
				this.prev = null;
			}
			
		}
		
		//Atributos da lista duplamente encadeada;
		public int nextId;
		public Element head;
		public Element tail;
		public int size;
		
		//Construtor da lista duplamente encadeada;
		public DoubleList() {
			this.head = null;
			this.tail = null;
			this.nextId = 1;
			this.size = 0;
		}
		
		public void show() {
			Element ptr = head;
			
			if (ptr == null) {
				throw new GeneralException("ERRO: Lista vazia!");
			}
			
			while (ptr != null) {
				System.out.println("ID do elemento: " + ptr.id + "\n");
				System.out.println("Elemento: " + ptr.object + "\n");
				ptr = ptr.next;
			}
			
		}

		public void showReverse() {
			Element ptr = tail;
			
			if (ptr == null) {
				throw new GeneralException("ERRO: Lista vazia!");
			}
			
			while (ptr != null) {
				System.out.println("ID do elemento: " + ptr.id + "\n");
				System.out.println("Elemento: " + ptr.object + "\n");
				ptr = ptr.prev;
			}
			
		}

		private Element searchId(int id) {
			Element ptr = head;
			
			while (ptr != null) {
				if (ptr.id == id) {
					return ptr;
				}
				ptr = ptr.next;
			}
			
			return null;	//Se o id não existir ou lista for vazia, retorna null;
			
		}
		
		/*private Element searchBefore(int id) {
			Element ptr = head;
			
			while (ptr != null) {
				if (ptr.next != null && ptr.next.id == id) {
					return ptr;
				}
				ptr = ptr.next;
			}
			
			return null;	//Se o id não existir ou lista for vazia, retorna null;
		}*/
		
		public void addAfter(T object, int id) {
			Element ptr =  searchId(id);
			
			if (ptr == null) {
				throw new GeneralException("ERRO: ID não existe ou lista está vazia!");
			} 
			else {
				Element add = new Element(object);
				
				if (ptr == tail) {			//Identificar se o ID pertence ao ultimo elemento.
					tail = add;
				}
				else {						//ID pertence a algum elemento intermediário.
					
					Element aux = ptr.next;	//ptr.next será diferente de nulo, pois o ponteiro não está em tail.
					aux.prev = add;
				}
				
				add.prev = ptr;
				add.next = ptr.next;		//Null, se ptr for tail;
				ptr.next = add;
				
				size++;
			}		
		}

		public void addFirst(T object) {
			Element add = new Element(object);
			
			if (head == null) {				//Identificar se a lista está vazia;
				head = add;
				tail = add;
				//add é o primeiro e o ultimo elemento;
			}
			else {
				add.next = head;
				head.prev = add;
				head = add;
			}
			
			size++;
		}

		public void addLast(T object) {
			Element add = new Element(object);
			
			if (tail == null) {				//Identificar se a lista está vazia;
				head = add;
				tail = add;
				//add é o primeiro e o ultimo elemento;
			}
			else {
				tail.next = add;
				add.prev = tail;
				tail = add;
			}
			
			size++;
		}

		public T removeFirst() {
			Element ptr = head;
			T removido = null;
			
			if (head == null) {
				throw new GeneralException("ERRO: Lista vazia!");
			}
			else {
				removido = ptr.object;
				
				if (head == tail) {				//identificar se contém apenas um elemento na lista;
					head = null;
					tail = null;
				}
				else {
					head = ptr.next;
				}
				
				ptr.next = null;
				ptr.prev = null;		//isolando o elemento;
				size--;
			}
			return removido;
		}

		public T removeLast() {
			Element ptr = tail;
			T removido = null;
			
			if (tail == null) {
				throw new GeneralException("ERRO: Lista vazia!");
			}
			else {
				removido = ptr.object;
				
				if (tail == head) {
					head = null;
					tail = null;
				}
				else {
					tail = ptr.prev;
					ptr.prev = null;
					tail.next = null;			//tail sempre deve apontar para null;
				}
				
				size--;
			}
			return removido;
		}

		public T removeId(int id) {
			Element ptr = searchId(id);
			T removido = null;
			
			if (head == null) {
				throw new GeneralException("ERRO: Lista está vazia!");
			}		
			else {
				Element before = ptr.prev;
				if (before == null) {							//Se o antecessor for null, ou ID não existe ou faz parte do primeiro elemento;
					
					if (head.id == id) {						//Remove o primeiro elemento;
						removido = removeFirst();				//removeFirst() faz todas verificações necessárias;
					}
					else {
						System.out.println("ERRO: ID não existe!"); // O ID não existe;
						return null;
					}
				}
				else {
					//ID existe, ou é o último elemento ou é um elemento intermediário.
					Element after = null;
					after = ptr.next;
					removido = ptr.object;
					
					if (ptr == tail) {					//Identifica se é o último elemento;
						removido = removeLast();		//removeLast() faz todas verificações necessárias;
					}
					else {
						before.next = after;
						after.prev = before;
						ptr.prev = null;
						ptr.next = null;
						size--;
					}
				}
			}
			
			return removido;
		}

		public T peekFirst() {
			if (head == null) {
				throw new GeneralException("ERRO: Lista está vazia!");
			}
			else {
				return head.object;
			}	
		}

		public T peekLast() {
			if (tail == null) {
				throw new GeneralException("ERRO: Lista está vazia!");
			}
			else {
				return tail.object;
			}
		}

		public T search(int id) {
			Element ptr = searchId(id);
			if (ptr == null) {
				throw new GeneralException("ERRO: Lista está vazia ou ID não existe!");
			}
			else {
				return ptr.object;
			}
		}
}
