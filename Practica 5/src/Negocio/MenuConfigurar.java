package Negocio;

import java.util.ArrayList;

public class MenuConfigurar<T> extends MenuPrincipal {
	public static ArbolBinario<Integer> arbolNumerico=new ArbolBinario<Integer>();
	public static ArbolBinario<String> arbolCaracter=new ArbolBinario<String>();
	@SuppressWarnings("unused")
	private static Archivo archivoDatos=new Archivo("Datos2.txt");
	
	public MenuConfigurar(String parTitulo, String[] parVectoropciones) {
		super(parTitulo, parVectoropciones);
	}
	
	@SuppressWarnings("unchecked")
	private void adicionarNodoNuevo(ArbolBinario<T> arbol) {
		Archivo arc=new Archivo();
		int numHijo=0,numPadre=0,numerico = 0;
		String caracter = "";
		ArrayList<T> padresHoja=new ArrayList<T>();
		Nodo<T> nuevo=null,padreNodo=null;
		T elemento = null;
		arbol=tipoArbol(arbol);
		padresHoja=arbol.sacarHojas();
		while ((numPadre<1 || numPadre>padresHoja.size()) && arbol.getRaiz()!=null){
			Consola.escribirSaltarLinea("Los Padres a elegir son: ", false);
			for (int i = 0; i < padresHoja.size(); i++) {
				Consola.escribirSaltarLinea((i+1)+"."+padresHoja.get(i), false);
			}
			numPadre=Consola.leer("Digite el padre al que quiere añadir el elemento: ", numPadre,false);
		}
		padreNodo= arbol.retornarNodo(padresHoja.get(numPadre-1));
		while ((numHijo<1 || numHijo>2) && arbol.getRaiz()!=null){
			numHijo=Consola.leer("1.Hijo Izquierdo 2.Hijo Derecho \nDigite a que tipo hijo se quiere añadir el elemento: ", numHijo,false);
		}
		if (Consola.esNumerico(""+arbol.getRaiz().getElemento())) {
			do {
				numerico=Consola.leer("Digite el nuevo elemento a ingresar: ", numerico,false);
			} while (!Consola.esNumerico(""+numerico) || numerico<1);
			nuevo=(Nodo<T>) new Nodo<Integer>(numerico);
			elemento=(T) Integer.valueOf(numerico);
		}else {
			do {
				caracter=Consola.leer("Digite el nuevo elemento a ingresar: ", caracter,false);
			} while (!Consola.esNumerico(caracter));
			nuevo=(Nodo<T>) new Nodo<String>(caracter);
			elemento=(T) caracter;
		}
		
		if (arbol.getRaiz()==null) {
			arbol.insertar(nuevo.getElemento());
		}else if (numHijo==1) {
			padreNodo.setIzq(nuevo);
			arc.escribirArchivo("Datos.txt", padreNodo.getElemento().toString(), ""+elemento, "Izq");
		}
		else {
			padreNodo.setDer(nuevo);
			arc.escribirArchivo("Datos.txt", padreNodo.getElemento().toString(), ""+elemento, "Der");
		}
		arbol.mostrarArbolSangrado();
	}
	
	@SuppressWarnings("unchecked")
	private void mostrarPadreHermano(ArbolBinario<T> arbol) {
		int datoNumerico=-1;
		String respuesta,datoCaracter=" ";
		T elemento=null;
		arbol=tipoArbol(arbol);
		if (Consola.esNumerico(""+arbol.getRaiz().getElemento())) {
			elemento=(T)Integer.valueOf(Consola.leer("Dígite el elemento a buscar: ",datoNumerico,false));
		}else {
			elemento=(T) Consola.leer("Dígite el elemento a buscar: ",datoCaracter,false);
		}
		respuesta=arbol.retornarPadreHermano(elemento);
		Consola.escribirSaltarLinea(respuesta, false);
	}
	
	private void mostrarRecorridos(ArbolBinario<T> arbol){
		arbol=tipoArbol(arbol);
		arbol.mostrarRecorridos();
		arbol.mostrarArbolSangrado();
	}
	
	@SuppressWarnings("unchecked")
	private ArbolBinario<T> tipoArbol(ArbolBinario<T> arbol) {
		int tipoArbol=0;
		do {
			tipoArbol=Consola.leer("1.Arbol Numerico \n2.Arbol Caracter \nDígite el tipo de arbol:", tipoArbol,false);
		} while (tipoArbol<1 || tipoArbol>2);
		if (tipoArbol==1) {
			arbol=(ArbolBinario<T>) arbolNumerico;
		}else if(tipoArbol==2) {
			arbol=(ArbolBinario<T>) arbolCaracter;
		}
		return arbol;
	}
	
	public static int seleccionArbol(String dato) {
		if (dato.equals("0") || (Consola.esNumerico(dato) && !dato.equals("1"))) {//Si el dato es 0 es un arbol numerico
			return 0; 
		}else if (dato.equals("1") || !Consola.esNumerico(dato)) {//Si el dato es 1 es un arbol caracter
			return 1;
		}
		return -1;//No pertenece a ninguno de los dos arboles
	}
	
	@Override
	protected void procesarOpcion() {
		ArbolBinario<T> arbol=new ArbolBinario<T>();
		switch (atrOpcion) {
			case 1:adicionarNodoNuevo(arbol);break;
			case 2:mostrarPadreHermano(arbol);break;
			case 3:mostrarRecorridos(arbol);break;
			case 4:Consola.escribirSaltarLinea("Gracias por utilizar el programa!", false);break;
		}
	}
}
