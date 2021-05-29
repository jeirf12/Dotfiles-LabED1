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

	private int factorEquilibrio(Nodo<T> arbol) {
		if (arbol == null)
		{
			return 0;
		}
		else {
			return (arbol.getFactorEquilibrio() + 1);
		}
	}

	private int mFE(int alturaIzquierdo, int alturaDerecho) {
		if (alturaIzquierdo > alturaDerecho)
			return alturaIzquierdo;
		else
			return alturaDerecho;
	}

	@SuppressWarnings("unchecked")
	private Nodo<T> insertar(Nodo<T> arbol, Comparable<T> elemento) {
		if (arbol == null) {
			arbol = new Nodo<T>((T) elemento);
		} else if (elemento.compareTo(arbol.getElemento()) < 0) {
			arbol.setIzq(insertar(arbol.getIzq(), elemento));
			if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == -2) {
				if (elemento.compareTo(arbol.getIzq().getElemento()) < 0)
					arbol = RotacionSimpleDer(arbol);
				else
					arbol = RotacionDobleIzq_Der(arbol);
			}
		} else if (elemento.compareTo(arbol.getElemento()) > 0) {
			arbol.setDer(insertar(arbol.getDer(), elemento));
			if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == 2) {
				if (elemento.compareTo(arbol.getDer().getElemento()) > 0)
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
	public void imprimirArbol(){
		imprimirArbol(raiz, 0);
	}
	private void imprimirArbol(Nodo<T> arbol,int nivel){
		if (arbol!=null) {
			imprimirArbol(arbol.getIzq(),nivel+1);
			System.out.println(" "+nivel+" "+arbol.getElemento());
			imprimirArbol(arbol.getDer(), nivel+1);
		}
	}
	public void funcionLucasPrimo(int elemento) {
		funcionLucasPrimo(elemento, 0,0,0);
	}
	private void funcionLucasPrimo(int elemento,int contador,int contador2,int lucas) {
		if (elemento>contador || elemento>contador2) {
			lucas=funcionLucas(contador);
			if (validaPrimoLucas(lucas, 1, 0)) {
				System.out.println(" "+lucas);
				contador2++;
			}
			funcionLucasPrimo(elemento,contador+1,contador2,lucas);
		}
	}
	
	public void compararLucas(int elemento,int n,int contador){
		if (elemento==funcionLucas(n)) {
			if (validaPrimoLucas(elemento, 1, 0)) {
				System.out.println(" "+elemento+" Pertenece a la secuencia y es el termino "+(contador+1));
			}
		}else if(elemento>funcionLucas(n)){
			compararLucas(elemento, n+1,contador+1);
		}
	}
	public void mostrarLucasArbol() {
		mostrarLucasArbol(raiz);
	}
	public void mostrarLucasArbol(Nodo<T> arbol) {
		if (arbol!=null) {
			mostrarLucasArbol(arbol.getIzq());
			compararLucas((int)arbol.getElemento(), 0,0);
			mostrarLucasArbol(arbol.getDer());
		}
	}

	public boolean validaPrimoLucas(int lucas,int n,int cont) {
		if (n<=lucas) {
			if (lucas % n==0) {
				return validaPrimoLucas(lucas, n+1,cont+1);
			}
			return validaPrimoLucas(lucas, n+1, cont);
		}
		if (cont==2) {
			return true;
		}else{
			return false;
		}
	}
	
	private int funcionLucas(int n){
		if (n==0) {
			return 2;
		}else if (n==1) {
			return 1;
		}else {
			return funcionLucas(n-1)+funcionLucas(n-2);
		}
	}
}
