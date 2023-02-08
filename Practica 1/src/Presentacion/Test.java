package Presentacion;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Negocio.*;

public class Test {	

	public static void main(String[] args) throws IOException {
		Archivo archi=new Archivo();
		Lista<RecursoBibliografico> recursos = new Lista<RecursoBibliografico>();
		Lista<RecursoBibliografico> recursosOrdenadosAscendentes = new Lista<RecursoBibliografico>();
		ArrayList<String> datos = new ArrayList<String>();
		datos = archi.leer("Datos.txt");
		if(datos == null) {
			System.out.println("Datos no encontrados");
			return;
		}
		llenarLista(recursos, datos);
		mostrarBiblioteca(recursos);
		buscarRecursoBibliografico(recursos, "Fisica3");
		buscarRecursoBibliografico(recursos, 0);
		eliminarRecursoBibliografico(recursos, "Fisica2");
		eliminarRecursoBibliografico(recursos, 1);
		posicionRecursosBibliograficos(recursos);
		actualizarCantidadRecursos(recursos);
		mostrarBiblioteca(recursos);
		System.out.println("\n Lista Ordenada Ascendente: ");
		recursosOrdenadosAscendentes = ordenarAscendente(recursos, 0, recursos.getTamanio()-1);
		mostrarBiblioteca(recursosOrdenadosAscendentes);
		System.out.println("\n Lista Ordenada Descendente ");
		mostrarBiblioteca(ordenarDescendente(recursosOrdenadosAscendentes));
	}

	public static void llenarLista(Lista<RecursoBibliografico> lista, ArrayList<String> datos) {
		try {
			for (int i = 0; i < datos.size(); i++) {
				String [] linea = datos.get(i).split(",");
				ArrayList<Autor> autores = new ArrayList<Autor>();
				for (int j = 0; j < 2; j++) {
					String [] lineaAut = linea[j].split(";");
					Autor aut = new Persona(lineaAut[0], lineaAut[1]);
					autores.add(aut);
				}
				RecursoBibliografico libro = new Libro(linea[2], autores, Integer.parseInt(linea[3]), linea[4], linea[5], Integer.parseInt(linea[6]), Integer.parseInt(linea[7]), linea[8], linea[9]);
				lista.agregar(libro);
			}
		} catch (Exception ex) { System.err.println("Error: "+ex.getMessage()); }
	}

	public static void mostrarBiblioteca(Lista<RecursoBibliografico> lista) { if(!lista.esVacia()) mostrarBiblioteca(lista.getCabeza()); }

	private static void mostrarBiblioteca(Nodo<RecursoBibliografico> aux) { if(aux != null) { aux.getDato().mostrarRecurso(); mostrarBiblioteca(aux.getSiguiente()); } }

	public static Lista<RecursoBibliografico> ordenarAscendente(Lista<RecursoBibliografico> lista, int izquierda, int derecha) {
		int pivote = (lista.obtenerElementoPosicion(izquierda).getAnio() + lista.obtenerElementoPosicion(derecha).getAnio()) / 2;
		int i = izquierda;
		int j = derecha;
		RecursoBibliografico aux;
		while (i < j) {
			while (lista.obtenerElementoPosicion(i).getAnio() <= pivote) {
				i++;
			}
			while (lista.obtenerElementoPosicion(j).getAnio() > pivote) {
				j--;
			}
			if (i < j) {
				aux = lista.obtenerElementoPosicion(j);
				lista.editarPorPosicion(j, lista.obtenerElementoPosicion(i));
				lista.editarPorPosicion(i, aux);
				i++;
				j--;
			}
		}
		if (izquierda < j) {
			lista = ordenarAscendente(lista, izquierda, j);
		}
		if (derecha > i) {
			lista = ordenarAscendente(lista, i, derecha);
		}
		return lista;
	}

	public static Lista<RecursoBibliografico> ordenarDescendente(Lista<RecursoBibliografico> datos) {
		Lista<RecursoBibliografico> ordenDesc = new Lista<RecursoBibliografico>();
		if (!datos.esVacia()) for (int i = datos.getTamanio()-1; i>=0; i--) ordenDesc.agregar(datos.obtenerElementoPosicion(i));
		return ordenDesc;
	} 

	public static void buscarRecursoBibliografico(Lista<RecursoBibliografico> lista, String nombre) {
		var nodo = buscarRecursoBibliografico(lista.getCabeza(), nombre);
		if (nodo != null) {
			System.out.println("\n#----------------------------------------------------#\n");
			System.out.println("\tRecurso " + nombre + " Encontrado");
			nodo.getDato().mostrarRecurso();
		}
		else System.out.println("\n---> No hay recursos con el nombre " + nombre.toUpperCase());
	}

	private static Nodo<RecursoBibliografico> buscarRecursoBibliografico(Nodo<RecursoBibliografico> aux, String nombre) {
		if(aux != null) {
			if(!aux.getDato().getNombre().equals(nombre)) return buscarRecursoBibliografico(aux.getSiguiente(), nombre);
		}
		return aux;
	}

	public static void buscarRecursoBibliografico(Lista<RecursoBibliografico> lista, int posicion) {
		if (posicion >= 0 && lista.getTamanio() > posicion) {
			System.out.println("\n#----------------------------------------------------#\n");
			System.out.println("\tRecurso Encontrado en la posicion #" + posicion);
			lista.obtenerElementoPosicion(posicion).mostrarRecurso();
		}
		else System.out.println("\n---> La posicion " + posicion + " no existe dentro de la lista");
	}

	public static void eliminarRecursoBibliografico(Lista<RecursoBibliografico> lista, String nombre) {
		var nodo = buscarRecursoBibliografico(lista.getCabeza(), nombre);
		if (nodo != null && lista.eliminarElemento(nodo.getDato())) System.out.println("\n---> El recurso " + nodo.getDato().getNombre() + " se ha eliminado correctamente!");
		else System.out.println("\n---> El recurso " + nombre + " no se ha podido eliminar!");
	}

	public static void eliminarRecursoBibliografico(Lista<RecursoBibliografico> lista,int posicion) {
		if (posicion > 0 && lista.getTamanio() > posicion) System.out.println("\n---> El recurso eliminado es " + lista.eliminarElementoPosicion(posicion).getNombre() + " en la posicion " + posicion);
		else System.out.println("\n---> La posicion " + posicion + " no existe dentro de la lista");
	}

	public static void posicionRecursosBibliograficos(Lista<RecursoBibliografico> lista) { 
		posicionRecursosBibliograficos(lista.getCabeza(), 0);
	}

	private static void posicionRecursosBibliograficos(Nodo<RecursoBibliografico> aux, int posicion) {
		if( aux != null ) {
			System.out.println("\n#----------------------------------------------------#");
			System.out.println("\n\tPOSICION #"+posicion);
			aux.getDato().mostrarRecurso();
			posicionRecursosBibliograficos(aux.getSiguiente(), posicion + 1);
		}
	}

	public static void actualizarCantidadRecursos(Lista<RecursoBibliografico> lista) {
		Scanner leer = new Scanner(System.in);
		int posicion = 0, cantidad = 0, contador = 0;
		do{
			if(contador != 0) System.out.println("Alguno de los datos lo digito mal, vuelva a ingresarlos");
			System.out.println("***************** MENU ACTUALIZAR EXISTENCIAS *****************");
			actualizarCantidadRecursos(lista.getCabeza(), 1);
			System.out.println("Elija una opcion para escoger el Recurso: ");
			posicion = leer.nextInt();
			System.out.println("Digite la cantidad del Recurso a actualizar: ");
			cantidad = leer.nextInt();
			contador++;
		} while ((posicion <= 0 || posicion > lista.getTamanio()) || cantidad <= 0);
		lista.obtenerElementoPosicion(posicion - 1).setNumEjemplares(cantidad);
	}

	private static void actualizarCantidadRecursos(Nodo<RecursoBibliografico> aux, int contador) {
		if(aux != null) {
			System.out.println(contador+ ". " + aux.getDato().getNombre() + " tiene " + aux.getDato().getNumEjemplares() + "# de ejemplares");
			actualizarCantidadRecursos(aux.getSiguiente(), contador + 1);
		}
	}
}
