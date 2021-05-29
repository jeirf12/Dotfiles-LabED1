package Negocio;

public class Arbol <T> {
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
			raiz = new Nodo<T>((T) elemento);
		} else {
			raiz = raiz.insertarNodo(raiz, (Comparable<T>) elemento);
		}
	}

	public void imprimirArbolNiveles() {
		System.out.println(" Recorrido por niveles");
		imprimirArbolNiveles(raiz, 0);
	}

	private void imprimirArbolNiveles(Nodo<T> arbol, int nivel) {
		if (arbol != null) {
			imprimirArbolNiveles(arbol.getIzq(), nivel + 1);
			System.out.println(" " + nivel + " " + arbol.getElemento());
			imprimirArbolNiveles(arbol.getDer(), nivel + 1);
		}
	}
	public void recorridos(){
		preorden();
		inorden();
		postorden();
		System.out.println();
	}
	private void preorden(){
		System.out.println("\n Recorrido preorden ");
		preorden(raiz);
	}
	private void preorden(Nodo<T> arbol){
		if (arbol!=null) {
			System.out.print(arbol.getElemento()+" ");
			preorden(arbol.getIzq());
			preorden(arbol.getDer());
		}
	}
	private void inorden(){
		System.out.println("\n Recorrido inorden ");
		inorden(raiz);
	}
	private void inorden(Nodo<T> arbol){
		if (arbol!=null) {
			inorden(arbol.getIzq());
			System.out.print(arbol.getElemento()+" ");
			inorden(arbol.getDer());
		}
	}
	private void postorden(){
		System.out.println("\n Recorrido postorden ");
		postorden(raiz);
	}
	private void postorden(Nodo<T> arbol){
		if (arbol!=null) {
			postorden(arbol.getIzq());
			postorden(arbol.getDer());
			System.out.print(arbol.getElemento()+" ");
		}
	}
	public void mayorElemento(){
		Nodo<T> mayor=mayorElemento(raiz);
		int nivelMayor=nivelNodo(mayor.getElemento());
		System.out.println("\n Mayor Elemento "+mayor.getElemento()+" y esta en el nivel: "+nivelMayor+"\n");
	}
	private Nodo<T> mayorElemento(Nodo<T> arbol){
		if (arbol.getDer()!=null) {
			return mayorElemento(arbol.getDer());
		}
		return arbol;
	}
	public void menorElemento(){
		Nodo<T> menor=menorElemento(raiz);
		int nivelMenor=nivelNodo(menor.getElemento());
		System.out.println("\n menor Elemento "+menor.getElemento()+" y esta en el nivel: "+nivelMenor+"\n");
	}
	private Nodo<T> menorElemento(Nodo<T> arbol){
		if (arbol.getIzq()!=null) {
			return menorElemento(arbol.getIzq());
		}
		return arbol;
	}
	public void nodosNivel(int n){
		nodosNivel(raiz,n,0);
	}
	public void nodosNivel(Nodo<T> arbol, int n, int nivel){
		if (arbol!=null) {
			nodosNivel(arbol.getIzq(),n,nivel+1);
			if (n==nivel) {
				System.out.print(arbol.getElemento()+" ");
			}	
			nodosNivel(arbol.getDer(),n,nivel+1);
		}
	}
	public int nivelNodo(T dato) {
		return nivelNodo(raiz, dato,0,-1);
	}
	private int nivelNodo(Nodo<T> arbol,T dato, int nivel, int nivelDato) {
		if (arbol!=null) {
			nivelDato=nivelNodo(arbol.getIzq(), dato, nivel+1,nivelDato);
			if (dato==arbol.getElemento()) {
				nivelDato=nivel;
			}
			nivelDato=nivelNodo(arbol.getDer(),dato,nivel+1,nivelDato);
		}
		return nivelDato;
	}
	public int Niveles(){
		return Niveles(raiz, 0);
	}
	public int Niveles(Nodo<T> arbol, int n){
		if (arbol!=null) {
			if (arbol.getIzq()!=null) {
				return Niveles(arbol.getIzq(), n+1);
			}	
			else {
				return Niveles(arbol.getDer(), n+1);
			}
		}
		return n-1;
	}
	
}
