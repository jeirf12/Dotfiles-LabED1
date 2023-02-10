package Negocio;

import java.util.ArrayList;

public class MenuConfigurar <T> extends MenuPrincipal {
	private ArbolBinario<Integer> arbolNumerico;
	private ArbolBinario<String> arbolCaracter;
	private Archivo archivoDatos;
	private int tamanioNumeros;
	private int tamanioCaracteres;
	private int tamanioFinal;

	public MenuConfigurar(String parTitulo, String[] parVectoropciones) { super(parTitulo, parVectoropciones);}

	private void llenarArboles() {
		this.archivoDatos = new Archivo("Datos.txt");
		this.arbolNumerico = new ArbolBinario<Integer>();
		this.arbolCaracter = new ArbolBinario<String>();
		int opcionArbol = 0;
		String [] lineaDatos = null;
		ArrayList<String> datos = archivoDatos.getDatos();
		for (int i = 0; i < datos.size(); i++) {
			lineaDatos = datos.get(i).split(",");
			if(lineaDatos.length == 1) opcionArbol = this.seleccionArbol(lineaDatos[0]);
			switch(opcionArbol) {
				case 0: 
					if (Consola.esNumerico(lineaDatos[0]) && !lineaDatos[0].equals("0")) {
						if (this.arbolNumerico.getRaiz() == null) this.arbolNumerico.insertar(Integer.parseInt(lineaDatos[0]));
						else if (Consola.esNumerico(lineaDatos[1])) this.arbolNumerico.insertar(Integer.parseInt(lineaDatos[0]), Integer.parseInt(lineaDatos[1]), lineaDatos[2]);
						this.tamanioNumeros = i;
						this.tamanioCaracteres = i + 1;
					}
					break;
				case 1: 
					if (!Consola.esNumerico(lineaDatos[0])) {
						if (this.arbolCaracter.getRaiz() == null) this.arbolCaracter.insertar(lineaDatos[0]);
						else if (!Consola.esNumerico(lineaDatos[1])) this.arbolCaracter.insertar(lineaDatos[0], lineaDatos[1], lineaDatos[2]);
						this.tamanioCaracteres++;
					}
					break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void adicionarNodoNuevo(ArbolBinario<T> arbol) {
		int numHijo = 0, numPadre = 0, numerico = 0;
		String caracter = "";
		ArrayList<T> padresHoja = new ArrayList<T>();
		Nodo<T> nuevo = null, padreNodo = null;
		T elemento = null;
		arbol = tipoArbol(arbol);
		padresHoja = arbol.sacarHojas();
		while ((numPadre < 1 || numPadre > padresHoja.size()) && arbol.getRaiz() != null){
			Consola.escribirSaltarLinea("Los Padres a elegir son: ");
			for (int i = 0; i < padresHoja.size(); i++) Consola.escribirSaltarLinea((i + 1) + "." + padresHoja.get(i));
			numPadre = Consola.leer("Digite el padre al que quiere a�adir el elemento: ", numPadre);
		}
		padreNodo = arbol.retornarNodo(padresHoja.get(numPadre - 1));
		while ((numHijo < 1 || numHijo > 2) && arbol.getRaiz() != null) numHijo = Consola.leer("1.Hijo Izquierdo 2.Hijo Derecho \nDigite a que tipo hijo se quiere a�adir el elemento: ", numHijo);
		if (Consola.esNumerico("" + arbol.getRaiz().getElemento())) {
			do { numerico = Consola.leer("Digite el nuevo elemento a ingresar: ", numerico); } 
			while (!Consola.esNumerico("" + numerico) || numerico < 1);
			nuevo = (Nodo<T>) new Nodo<Integer>(numerico);
			elemento = (T) Integer.valueOf(numerico);
		}else {
			do { caracter = Consola.leer("Digite el nuevo elemento a ingresar: ", caracter); } 
			while (Consola.esNumerico(caracter));
			nuevo = (Nodo<T>) new Nodo<String>(caracter);
			elemento = (T) caracter;
		}
		String linea = padreNodo.getElemento().toString() + "," + elemento + ",";
		if (arbol.getRaiz() == null) arbol.insertar(nuevo.getElemento());
		else if (numHijo == 1) {
			padreNodo.setIzq(nuevo);
			archivoDatos.escribirArchivo(linea + "Izq", tamanioFinal + 1);
		}
		else {
			padreNodo.setDer(nuevo);
			archivoDatos.escribirArchivo(linea + "Der", tamanioFinal + 1);
		}
		arbol.mostrarArbolSangrado();
	}
	
	@SuppressWarnings("unchecked")
	private void mostrarPadreHermano(ArbolBinario<T> arbol) {
		int datoNumerico = -1;
		String respuesta, datoCaracter = "";
		T elemento = null;
		arbol = tipoArbol(arbol);
		if (Consola.esNumerico("" + arbol.getRaiz().getElemento())) elemento = (T)Integer.valueOf(Consola.leer("D�gite el elemento a buscar: ", datoNumerico));
		else elemento = (T)Consola.leer("D�gite el elemento a buscar: ", datoCaracter);
		respuesta = arbol.retornarPadreHermano(elemento);
		Consola.escribirSaltarLinea(respuesta);
	}
	
	private void mostrarRecorridos(ArbolBinario<T> arbol) {
		arbol = this.tipoArbol(arbol);
		arbol.mostrarRecorridos();
		arbol.mostrarArbolSangrado();
	}
	
	@SuppressWarnings("unchecked")
	private ArbolBinario<T> tipoArbol(ArbolBinario<T> arbol) {
		int tipoArbol = 0;
		do { tipoArbol = Consola.leer("1.Arbol Numerico \n2.Arbol Caracter \nD�gite el tipo de arbol:", tipoArbol); } 
		while (tipoArbol < 1 || tipoArbol > 2);
		arbol = tipoArbol == 1 ? (ArbolBinario<T>)this.arbolNumerico : (ArbolBinario<T>)this.arbolCaracter;
		tamanioFinal = tipoArbol == 1 ? tamanioNumeros : tamanioCaracteres;
		return arbol;
	}
	
	public int seleccionArbol(String dato) {
		//Si el dato es 0 es un arbol numerico
		if (dato.equals("0") || (Consola.esNumerico(dato) && !dato.equals("1"))) return 0; 
		//Si el dato es 1 es un arbol caracter
		else if (dato.equals("1") || !Consola.esNumerico(dato)) return 1;
		//No pertenece a ninguno de los dos arboles
		return -1;
	}
	
	@Override
	protected void procesarOpcion() {
		this.llenarArboles();
		ArbolBinario<T> arbol = new ArbolBinario<T>();
		switch (atrOpcion) {
			case 1: this.adicionarNodoNuevo(arbol); break;
			case 2: this.mostrarPadreHermano(arbol); break;
			case 3: this.mostrarRecorridos(arbol); break;
			case 4: Consola.escribirSaltarLinea("Gracias por utilizar el programa!"); break;
		}
	}
}
