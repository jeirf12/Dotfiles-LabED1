package Presentacion;

import java.io.*;
import java.util.ArrayList;

import Negocio.*;

public class Test {	
	public static void main(String[] args) throws IOException {
		Archivo archi=new Archivo();
		Lista<RecursoBibliografico> recursos= new Lista<RecursoBibliografico>();
		Lista<RecursoBibliografico> recursosOrdenadosAscendentes=new Lista<RecursoBibliografico>();
		ArrayList<String> datos=new ArrayList<String>();
		datos=archi.leer("Datos.txt");
		llenarLista(recursos, datos);
		//mostrarBiblioteca(recursos);
		buscarRecursoBibliografico(recursos, "Fisica3");
		buscarRecursoBibliografico(recursos, 0);
		eliminarRecursoBibliografico(recursos, "Fisica2");
		eliminarRecursoBibliografico(recursos, 1);
		posicionRecursosBibliograficos(recursos);
		/*System.out.println("\n Lista Ordenada Ascendente: \n");
		recursosOrdenadosAscendentes=ordenarAscendente(recursos, 0, recursos.getTamanio()-1);
		mostrarBiblioteca(recursosOrdenadosAscendentes);
		System.out.println("\n Lista Ordenada Descendente");
		mostrarBiblioteca(ordenarDescendente(recursosOrdenadosAscendentes));*/
		
		
	}
	public static void llenarLista(Lista<RecursoBibliografico> lista, ArrayList<String> datos) {
		try {
			String []linea=null;
			String []lineaAut=null;
			int cont;
			for (int i = 0; i < datos.size(); i++) {
				cont=0;
				linea=datos.get(i).split(",");
				ArrayList<Autor> autores=new ArrayList<Autor>();
				while (cont<2) {
					lineaAut=linea[cont].split(";");
					Autor aut=new Persona(lineaAut[0], lineaAut[1]);
					autores.add(aut);
					cont++;
				}
				RecursoBibliografico libro= new Libro(linea[2], autores, Integer.parseInt(linea[3]), linea[4], linea[5], Integer.parseInt(linea[6]), Integer.parseInt(linea[7]), linea[8], linea[9]);
				lista.agregar(libro);
			}
		} catch (Exception ex) {
			System.err.println("Error: "+ex.getMessage());
		}
	}
	public static void mostrarBiblioteca(Lista<RecursoBibliografico> lista) {
		for (int i = 0; i < lista.getTamanio(); i++) {
			lista.obtenerElementoPosicion(i).mostrarRecurso();
		}
	}
	public static Lista<RecursoBibliografico> ordenarAscendente(Lista<RecursoBibliografico> lista, int izquierda, int derecha) {
		int pivote= (lista.obtenerElementoPosicion(izquierda).getAnio()+lista.obtenerElementoPosicion(derecha).getAnio())/2;
		int i=izquierda;
		int j=derecha;
		RecursoBibliografico aux;
		while (i<j) {
			while (lista.obtenerElementoPosicion(i).getAnio() <= pivote) {
				i++;
			}
			while (lista.obtenerElementoPosicion(j).getAnio() > pivote) {
				j--;
			}
			if (i<j) {
				aux=lista.obtenerElementoPosicion(j);
				lista.editarPorPosicion(j, lista.obtenerElementoPosicion(i));
				lista.editarPorPosicion(i, aux);
				i++;
				j--;
			}
		}
		if (izquierda<j) {
			lista=ordenarAscendente(lista, izquierda, j);
		}
		if (derecha>i) {
			lista=ordenarAscendente(lista, i, derecha);
		}
		return lista;
	}
	public static Lista<RecursoBibliografico> ordenarDescendente(Lista<RecursoBibliografico> datos) {
		Lista<RecursoBibliografico> ordenDesc=new Lista<RecursoBibliografico>();
		if (!datos.esVacia()) {
			for (int i = datos.getTamanio()-1; i>=0; i--) {
				ordenDesc.agregar(datos.obtenerElementoPosicion(i));
			}
		}
		return ordenDesc;
	} 
	public static void buscarRecursoBibliografico(Lista<RecursoBibliografico> lista,String nombre) {
		for (int i = 0; i < lista.getTamanio(); i++) {
			if (lista.obtenerElementoPosicion(i).getNombre().equals(nombre)) {
				lista.obtenerElementoPosicion(i).mostrarRecurso();
			}
		}
	}
	public static void buscarRecursoBibliografico(Lista<RecursoBibliografico> lista,int posicion) {
		if (posicion>=0 && lista.getTamanio()>posicion) {
			lista.obtenerElementoPosicion(posicion).mostrarRecurso();
		}else {
			System.out.println("La posicion no existe dentro de la lista");
		}
	}
	public static void eliminarRecursoBibliografico(Lista<RecursoBibliografico> lista,String nombre) {
		for (int i = 0; i < lista.getTamanio(); i++) {
			if (lista.obtenerElementoPosicion(i).getNombre().equals(nombre)) {
				if (lista.eliminarElemento(lista.obtenerElementoPosicion(i))) {
					System.out.println("El recurso se ha eliminado correctamente!");
				}
			}
		}
	}
	public static void eliminarRecursoBibliografico(Lista<RecursoBibliografico> lista,int posicion) {
		if (posicion>0 && lista.getTamanio()>posicion) {
			System.out.println(lista.eliminarElementoPosicion(posicion));
		}else {
			System.out.println("La posicion no existe dentro de la lista");
		}
	}
	public static void posicionRecursosBibliograficos(Lista<RecursoBibliografico> lista) {
		for (int i = 0; i < lista.getTamanio(); i++) {
			System.out.println("POSICION #"+i);
			lista.obtenerElementoPosicion(i).mostrarRecurso();
		}
	}
	
}
