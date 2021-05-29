package Negocio;


public class Arbol <T>{
	private Nodo<T> raiz;

	public Arbol() {
		this.raiz = null;
	}
	public Nodo<T> getRaiz() {
		return raiz;
	}
	public void insertar(Comparable<T> elemento) {
		raiz = insertar(raiz, elemento);
	}
	@SuppressWarnings("unchecked")
	private Nodo<T> insertar(Nodo<T> arbol, Comparable<T> elemento) {
		if (arbol == null) {
			arbol =new Nodo<T>((T) elemento);
		} else if (elemento.compareTo((T) arbol.getElemento()) < 0) {
			arbol.setIzq(insertar(arbol.getIzq(), elemento));
			if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == -2) {
				if (elemento.compareTo((T) arbol.getIzq().getElemento()) < 0)
					arbol = RotacionSimpleDer(arbol);
				else
					arbol = RotacionDobleIzq_Der(arbol);
			}
		} else if (elemento.compareTo((T) arbol.getElemento()) > 0) {
			arbol.setDer(insertar(arbol.getDer(), elemento));
			if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == 2) {
				if (elemento.compareTo((T) arbol.getDer().getElemento()) > 0)
					arbol = RotacionSimpleIzq(arbol);
				else
					arbol = RotacionDobleDer_Izq(arbol);
			}
		} else {
			;// En el caso de que sean valores duplicados no hace nada
		}
		arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
		return arbol;
	}
	//Devuelve el valor de equilibrio de un arbol desequilibrado o equilibrado
	private int factorEquilibrio(Nodo<T> arbol) {
		if (arbol == null)
			return 0;
		else
			return (arbol.getFactorEquilibrio() + 1);
	}
	//Determina cuando un arbol esta desequilibrado o equilibrado en alguna de sus ramas
	private int mFE(int alturaIzquierdo, int alturaDerecho) {
		if (alturaIzquierdo > alturaDerecho)
			return alturaIzquierdo;
		else
			return alturaDerecho;
	}
	//Rotaciones del arbol Simple Derecho,Izquierdo y Doble Derecho,Izquierda
	private Nodo<T> RotacionSimpleDer(Nodo<T> arbol) {
		Nodo<T> subArbol = arbol.getIzq();
		arbol.setIzq(subArbol.getDer());
		subArbol.setDer(arbol);
		arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
		subArbol.setFactorEquilibrio(mFE(factorEquilibrio(subArbol.getIzq()), arbol.getFactorEquilibrio()));
		return subArbol;
	}
	private Nodo<T> RotacionSimpleIzq(Nodo<T> arbol) {
		Nodo<T> subArbol = arbol.getDer();
		arbol.setDer(subArbol.getIzq());
		subArbol.setIzq(arbol);
		arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
		subArbol.setFactorEquilibrio(mFE(factorEquilibrio(subArbol.getDer()), arbol.getFactorEquilibrio()));
		return subArbol;
	}
	private Nodo<T> RotacionDobleIzq_Der(Nodo<T> arbol) {
		arbol.setIzq(RotacionSimpleIzq(arbol.getIzq()));
		return RotacionSimpleDer(arbol);
	}
	private Nodo<T> RotacionDobleDer_Izq(Nodo<T> subArbol) {
		subArbol.setDer(RotacionSimpleDer(subArbol.getDer()));
		return RotacionSimpleIzq(subArbol);
	}
	//Imprime todo el arbol
	public void imprimirArbolNiveles() {
		imprimirArbolNiveles(raiz, 0);
	}
	private void imprimirArbolNiveles(Nodo<T> arbol,int nivel){ 
		if(arbol!=null){ 
			imprimirArbolNiveles(arbol.getIzq(),nivel+1);
			System.out.println(" "+nivel+" "+ arbol.getElemento()); 
			imprimirArbolNiveles(arbol.getDer(),nivel+1); 
		} 
	}
	//Muestra los recorridos preorden, inorden, postorden
	public void recorridos(){
		preorden();
		inorden();
		postorden();
		
	}
	//Muestra recorrido en preorden
	public void preorden(){
		System.out.println("\n Recorrido preorden ");
		preorden(raiz);
	}
	private void preorden(Nodo<T> arbol){
		if (arbol!=null) {
			System.out.println(arbol.getElemento()+" ");
			preorden(arbol.getIzq());
			preorden(arbol.getDer());
		}
	}
	//Muestra recorrido en inorden
	private void inorden(){
		System.out.println("\n Recorrido inorden ");
		inorden(raiz);
	}
	private void inorden(Nodo<T> arbol){
		if (arbol!=null) {
			inorden(arbol.getIzq());
			System.out.println(arbol.getElemento()+" ");
			inorden(arbol.getDer());
		}
	}
	//Muestra recorrido en postOrden
	private void postorden(){
		System.out.println("\n Recorrido postorden ");
		postorden(raiz);
	}
	private void postorden(Nodo<T> arbol){
		if (arbol!=null) {
			postorden(arbol.getIzq());
			postorden(arbol.getDer());
			System.out.println(arbol.getElemento()+" ");
		}
	}
	//Muestra el peso del arbol, contando sus hojas con el metodo contarHojas
	public void pesoArbol() {
		System.out.println("El peso del arbol es: "+contarHojas(raiz, 0));
	}
	//Cuenta los nodos que son hojas
	private int contarHojas(Nodo<T> arbol,int cont) {
		if (arbol!=null) {
			if (esHoja(arbol)) {
				cont++;
			}
			cont=contarHojas(arbol.getIzq(), cont);
			cont=contarHojas(arbol.getDer(), cont);
		}
		return cont;
	}
	//Valida si un nodo es hoja retornando true o false
	private boolean esHoja(Nodo<T> arbol) {
		if (arbol.getIzq()==null && arbol.getDer()==null) {
			return true;
		}
		return false;
	}
	//Saca el valor del nivel de un dato
	public int nivelDeUnDato(Comparable<T> n) {
		return nivelDeUnNodo(raiz, n, 0, -1);
	}
	private int nivelDeUnNodo(Nodo<T> arbol, Comparable<T> n, int nivel, int nivelDato) {
		if (arbol != null) {
			nivelDato=nivelDeUnNodo(arbol.getIzq(), n, nivel + 1,nivelDato);
			if (n.equals(arbol.getElemento())) {
				nivelDato=nivel;
			}
			nivelDato=nivelDeUnNodo(arbol.getDer(), n, nivel + 1,nivelDato);
		}
		return nivelDato;
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
	
	public void longitudCamino() {
		System.out.println("LCI: "+longitudCamino(raiz,0,0));
	}
	public int longitudCamino(Nodo<T> arbol, int nivel,int lci) {
		int numNodos=contarNodosNivel(nivel);
		if (arbol!=null) {
			lci=longitudCamino(arbol.getIzq(), nivel+1,lci);
			if (numNodos>0) {
				return lci+=(numNodos*(nivel+1));
			}
			lci=longitudCamino(arbol.getDer(), nivel+1,lci);
		}
		return lci+=(numNodos*(nivel+1));
	}

	private void nodosNivelReversa(Nodo<T> arbol, int n, int nivel) {
		if (arbol != null) {
			nodosNivelReversa(arbol.getDer(), n, nivel + 1);
			if (n == nivel)
				System.out.print(arbol.getElemento() + "  ");
			nodosNivelReversa(arbol.getIzq(), n, nivel + 1);
		}
	}

	private void nodosNivel(Nodo<T> arbol, int n, int nivel) {
		if (arbol != null) {
			nodosNivel(arbol.getIzq(), n, nivel + 1);
			if (n == nivel)
				System.out.print(arbol.getElemento() + "  ");
			nodosNivel(arbol.getDer(), n, nivel + 1);
		}
	}

	public void imprimirZigZag() {
		imprimirZigZag(0);
	}

	private void imprimirZigZag(int nivel) {
		if (nivel == 0) {
			nodosNivel(raiz, nivel, 0);
			imprimirZigZag(nivel + 1);
		} else {
			if (nivel != nivelesDelArbol() + 1) {
				if (nivel % 2 == 0) {
					nodosNivelReversa(raiz, nivel, 0);
					imprimirZigZag(nivel + 1);
				} else {
					nodosNivel(raiz, nivel, 0);
					imprimirZigZag(nivel + 1);
				}
			}
		}
	}
}
