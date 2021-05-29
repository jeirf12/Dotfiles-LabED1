package Negocio;

import java.util.ArrayList;

public class Arbol <T>{
	private Nodo<T> raiz;

	public Arbol() {
		this.raiz = null;
	}
	public Nodo<T> getRaiz() {
		return raiz;
	}
	//Ingresa los valores al arbol
	public void insertar(Comparable<T> elemento) {
		raiz = insertar(raiz, elemento);
	}
	@SuppressWarnings("unchecked")
	private Nodo<T> insertar(Nodo<T> arbol, Comparable<T> elemento) {
		if (arbol == null) {
			arbol = new Nodo<T>((T) elemento, null, null);
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
	private void preorden(){
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
	private int nivelDeUnDato(Comparable<T> n) {
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
	//Muestra si un elemento tiene primos hermanos y los muestra cuales son si así es
	public void primoHermano(Comparable<T> dato) {
		int nivelDato = nivelDeUnDato(dato);
		ArrayList<T> primos = new ArrayList<T>();
		if (nivelDato == 0) {
			System.out.println("el dato " + dato + " no tiene primos porque es la raiz");
		} else if (nivelDato > 0) {
			primoHermano(raiz, primos, dato, 0, nivelDato);
			if (primos.size() != 0) {
				System.out.println("Los primos hermanos de " + dato + " son:");
				for (int i = 0; i < primos.size(); i++) {
					System.out.print(" " + primos.get(i));
				}
			} else {
				System.out.println("El dato " + dato + " no tiene primos Hermanos");
			}
		} else {
			System.out.println("El dato " + dato + " no existe en el arbol");
		}
	}
	//Muestra si dos valores ingresados son primos hermanos o no
	@SuppressWarnings("unchecked")
	public void primoHermano(Comparable<T> dato1,Comparable<T> dato2) {
		if (dato1.compareTo((T) dato2)!=0) {
			ArrayList<T> primosD1 = new ArrayList<T>();
			ArrayList<T> primosD2 = new ArrayList<T>();
			Nodo<T> padreD1 = buscarPadre(dato1);
			Nodo<T> padreD2 = buscarPadre(dato2);
			int nivelDato1 = nivelDeUnDato(dato1);
			int nivelDato2 = nivelDeUnDato(dato2);
			if (nivelDato1 == 0 || nivelDato2 == 0) {
				System.out.println(dato1 + "-" + dato2 + " Uno de los datos ingresados no son primos hermanos porque es la raiz");
			} else if (nivelDato1 > 0 && nivelDato2 > 0) {
				if (nivelDato1 == nivelDato2) {
					primoHermano(raiz, primosD1, dato1, 0, nivelDato1);
					primoHermano(raiz, primosD2, dato2, 0, nivelDato2);
					if (primosD1.equals(primosD2)) {
						if (padreD1 == padreD2) {
							System.out.println(dato1 + "-" + dato2 + " No son primos, son hermanos");
						}
					} else {
						for (int i = 0; i < primosD1.size(); i++) {
							if (primosD1.get(i).equals(dato2)) {
								System.out.println(dato1 + "-" + dato2 + " son primos hermanos");
								break;
							}else {
								if (i == (primosD1.size() - 1)) {
									System.out.println(dato1 + "-" + dato2 + " No son primos hermanos");
								}
							}
						}
					}
				} else {
					System.out.println(dato1 + "-" + dato2 + " No son primos hermanos");
				}
			} else {
				System.out.println("Los datos " + dato1 + " ó " + dato2 + " No existen en el arbol");
			}
		}else {
			System.out.println("Los datos ingresados son iguales");
		}
		
	}
	//Llena un lista con los primos de un dato dado, y con su nivel ya hallado
	private void primoHermano(Nodo<T> arbol,ArrayList<T> primos,Comparable<T> dato,int nivel, int nivelDato) {
		Nodo<T> padre=buscarPadre(dato);
		Nodo<T> hermanoHijo=BuscarHermano(dato);
		@SuppressWarnings("unchecked")
		Nodo<T> padreHermano=BuscarHermano((Comparable<T>)padre.getElemento());
		if (padreHermano!=null) {
			if (padreHermano.getIzq()!=null) {
				if (hermanoHijo!=null) {
					if (!dato.equals(padreHermano.getIzq().getElemento()) && !hermanoHijo.getElemento().equals(padreHermano.getIzq().getElemento())) {
						primos.add((T)padreHermano.getIzq().getElemento());
					}
				}else {
					if (!dato.equals(padreHermano.getIzq().getElemento())) {
						primos.add((T)padreHermano.getIzq().getElemento());
					}
				}
			}
			if(padreHermano.getDer()!=null) {
				if (hermanoHijo!=null) {
					if (!dato.equals(padreHermano.getDer().getElemento()) && !hermanoHijo.getElemento().equals(padreHermano.getDer().getElemento())) {
						primos.add((T)padreHermano.getDer().getElemento());
					}
				}else {
					if (!dato.equals(padreHermano.getDer().getElemento())) {
						primos.add((T)padreHermano.getDer().getElemento());
					}
				}

			}
		}
	}
	//Saca el Nodo del hermano, de un elemento ingresado
	private Nodo<T> BuscarHermano(Comparable<T> dato) {
		Nodo<T> padre= buscarPadre(raiz,dato,raiz);
		if(padre!=null) {
			if(dato.compareTo((T)padre.getElemento())<0) {
				return padre.getDer();
			}
			else {
				return padre.getIzq();
			}
		}
		return padre;
	}
	//Saca el Nodo padre de un elemento ingresado
	private Nodo<T> buscarPadre(Comparable<T> dato){
		return buscarPadre(raiz, dato, raiz);
	}
	private Nodo<T> buscarPadre(Nodo<T> arbol, Comparable<T> dato, Nodo<T> padre){
		if(arbol!=null) {
			if(dato.compareTo(arbol.getElemento())==0) {
				if(arbol.getElemento()!=raiz.getElemento()) {
					return padre;
				}
			}
			else if(dato.compareTo(arbol.getElemento())<0) {
				return buscarPadre(arbol.getIzq(),dato,arbol);
			}
			else {
				return buscarPadre(arbol.getDer(),dato,arbol);
			}
		}
		return null;
	}
	
}
