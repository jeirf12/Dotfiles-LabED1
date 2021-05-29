package Logica;

import java.util.ArrayList;

public class Lista<T> {
	private Nodo<T> cabeza;
	private int tamanio;
	
	public Lista() {
		this.cabeza=null;
		this.tamanio=0;
	}
	
	public void setCabeza(Nodo<T> cabeza) {
		this.cabeza=cabeza;
	}
	public Nodo<T> getCabeza(){
		return this.cabeza;
	}
	public void setTamanio(int tamanio) {
		this.tamanio=tamanio;
	}
	public int getTamanio() {
		return this.tamanio;
	}
	public void Agregar(T elemento) {
		Nodo<T> nuevo = new Nodo<T>(elemento);
		if (!esVacia()) {
			Nodo<T> aux = recorre(cabeza);
			aux.setSiguiente(nuevo);
		}else {
			cabeza = nuevo;
		}
		tamanio++;
	}
	public Nodo<T> recorre(Nodo<T> aux){
		if (aux.getSiguiente()!=null) {
			return recorre(aux.getSiguiente());
		}
		return aux;
	}
	public boolean esVacia() {
		return this.cabeza==null;
	}
	public void mostrar(Nodo<T> aux){
		if (aux!=null) {
			Consola.escribirSaltarLinea(aux.getDato().toString(), false);
			mostrar(aux.getSiguiente());
		}
	}
	public void mostrar(){
		Consola.escribirSaltarLinea("C O N T E N I D O  D E  L A  L I S T A", false);
		mostrar(cabeza);
	}
	public ArrayList<Persona> obtenerLista(){
		ArrayList<Persona> personas=new ArrayList<Persona>();
		return obtenerLista(cabeza, personas);
	}
	public ArrayList<Persona> obtenerLista(Nodo<T> aux,ArrayList<Persona> personas) {
		if (aux!=null) {
			personas.add((Persona) aux.getDato());
			obtenerLista(aux.getSiguiente(), personas);
		}
		return personas;
	}
	public static void mayorEdad(Lista<Persona> aux) {
		Persona mayor=mayorEdad(aux.getCabeza(), 0, null);
		if (mayor!=null) {
			Consola.escribirSaltarLinea("La persona de mayor edad es: "+mayor.getNombre()+" id: "+mayor.getId()+" edad: "+mayor.getEdad(), false);
		}
	}
	private static Persona mayorEdad(Nodo<Persona> aux,int edad,Persona mayor) {
		if (aux!=null) {
			if (aux.getDato().getEdad()>=edad) {
				mayor=aux.getDato();
				return mayorEdad(aux.getSiguiente(), aux.getDato().getEdad(), mayor);
			}
			return mayorEdad(aux.getSiguiente(), edad, mayor);
		}
		return mayor;
	}
	public static void menorEdad(Lista<Persona> aux) {
		Persona menor=menorEdad(aux.getCabeza(),aux.getCabeza().getDato().getEdad(),null);
		if (menor!=null) {
			Consola.escribirSaltarLinea("La persona de menor edad es: "+menor.getNombre()+" id: "+menor.getId()+" edad: "+menor.getEdad(), false);
		}
	}
	private static Persona menorEdad(Nodo<Persona> aux,int edad,Persona menor) {
		if (aux!=null) {
			if (aux.getDato().getEdad()<=edad) {
				menor=aux.getDato();
				return menorEdad(aux.getSiguiente(), aux.getDato().getEdad(), menor);
			}
			return menorEdad(aux.getSiguiente(), edad, menor);
		}
		return menor;
	}
	public static void promedioEdad(Lista<Persona> aux) {
		int promedio=promedioEdad(aux.getCabeza(), 0);
		if (promedio!=0) {
			Consola.escribirSaltarLinea("El promedio de edad es: "+promedio/aux.getTamanio(), false);
		}
	}
	private static int promedioEdad(Nodo<Persona> aux,int promedio) {
		if (aux!=null) {
			promedio=promedio+aux.getDato().getEdad();
			return promedioEdad(aux.getSiguiente(),promedio);
		}
		return promedio;
	}
}
