package Negocio;

public class Arbol <T>{
	private Nodo<T> raiz;

	public Arbol() {
		this.raiz = null;
	}

	public Nodo<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo<T> raiz) {
		this.raiz = raiz;
	}

	@SuppressWarnings("unchecked")
	public void insertar(T elemento) {
		if (raiz == null) {
			raiz = new Nodo<T>(elemento);
		} else {
			raiz = raiz.insertarNodo(raiz, (Comparable<T>) elemento);
		}
	}

	public void imprimirArbolNiveles() {
		imprimirArbolNiveles(raiz, 0);
	}

	private void imprimirArbolNiveles(Nodo<T> arbol, int nivel) {
		if (arbol != null) {
			imprimirArbolNiveles(arbol.getIzq(), nivel + 1);
			System.out.println(" " + nivel + " " + arbol.getElemento());
			imprimirArbolNiveles(arbol.getDer(), nivel + 1);
		}
	}
	public int contarNodosNivel(int n) {
		return contarNodosNivel(raiz, n, 0,0);
		 
	}

	private int contarNodosNivel(Nodo<T> arbol, int n, int nivel,int cont) {
		if (arbol != null) {
			cont=contarNodosNivel(arbol.getIzq(), n, nivel + 1,cont);
			if (n == nivel) {
				cont++;
			}
			cont =contarNodosNivel(arbol.getDer(), n, nivel + 1,cont);
		}
		return cont;
	}

	public int nivelesDelArbol() {
		return nivelesDelArbol(raiz, 0);
	}

	private int nivelesDelArbol(Nodo<T> arbol, int n) {
		if (arbol != null) {
			if (arbol.getIzq() != null){
				return nivelesDelArbol(arbol.getIzq(), n + 1);
			}
			return nivelesDelArbol(arbol.getDer(), n + 1);
		}
		return n - 1;
	}

	public void nivelDeUnDato(Comparable<T> n) {
		nivelDeUnNodo(raiz, n, 0);
	}
	private void nivelDeUnNodo(Nodo<T> arbol, Comparable<T> n, int nivel) {
		if (arbol != null) {
			nivelDeUnNodo(arbol.getIzq(), n, nivel + 1);
			if (n.compareTo(arbol.getElemento()) == 0) {
				System.out.println(nivel + " ");
			}
			nivelDeUnNodo(arbol.getDer(), n, nivel + 1);
		}
	}
	public void MostrarFibonacci() {
		MostrarFibonacci(raiz);
	}

	private void MostrarFibonacci(Nodo<T> arbol) {
		int fibonacci;
		if (arbol != null) {
			MostrarFibonacci(arbol.getIzq());
			fibonacci=compararFibonacci((int)arbol.getElemento(), 0);
			if (fibonacci!=-1) {
				System.out.println(fibonacci);
			} 
			MostrarFibonacci(arbol.getDer());
		}
	}
	
	public int compararFibonacci(int elemento,int n) {
		if (elemento==fibonacci(n)) {
			return elemento;
		}
		else if(elemento>fibonacci(n)) {
			return compararFibonacci(elemento, n+1);
		}
		return -1;
	}
	public int fibonacci(int n) {
		if (n<2) {
			return n;
		}
		return fibonacci(n-1)+fibonacci(n-2);
	}
	public int alturaArbol() {
		return alturaArbol(raiz, 1, 0);
	}
	private int alturaArbol(Nodo<T> arbol,int nivel ,int altura) {
		if (arbol!=null) {
			altura=alturaArbol(arbol.getIzq(), nivel+1, altura);
			if (nivel>altura) {
				altura=nivel;
			}
			altura=alturaArbol(arbol.getDer(), nivel+1, altura);
		}
		return altura;
	}
	public int retornaNivelPodar() {
		return retornaNivelPodar(nivelesDelArbol());
	}
	private int retornaNivelPodar(int nivel) {
		if (contarNodosNivel(nivel)<(nivelesDelArbol()+1)) {
			return nivel;
		}
		return retornaNivelPodar(nivel-1);
	}
	public void podar(int nivelPodar) {
		podar(raiz,0,nivelPodar);
	}
	private void podar(Nodo<T> arbol,int nivel,int nivelPodar) {
		if (arbol!=null) {
			podar(arbol.getIzq(), nivel+1,nivelPodar);
			if (nivel==nivelPodar-1) {
				arbol.setIzq(null);
				arbol.setDer(null);
			}
			podar(arbol.getDer(), nivel+1,nivelPodar);
		}
	}
}
