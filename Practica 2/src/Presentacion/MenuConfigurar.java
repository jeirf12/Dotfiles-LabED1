package Presentacion;

import Logica.*;

public class MenuConfigurar extends Menu{
	private Lista<Persona> personas;
	
	public MenuConfigurar(String parTitulo, String[] parVectoropciones) {
		super(parTitulo, parVectoropciones);
	}

	private void agregarLista() {
		this.personas = new Lista<Persona>();
		personas.agregar(new Persona(1234, "mateo", 27));
		personas.agregar(new Persona(1324, "cris", 26));
		personas.agregar(new Persona(1243, "holy", 28));
		personas.agregar(new Persona(2134, "julian", 20));
	}

	private void mostrarNumeroDigitos() {
		int numero = -1;
		do{ numero = Consola.leer("Dígite el número: ", numero); } while(numero < 0);
		Consola.escribirSaltarLinea("El numero de digitos total del numero " + numero + " es: " + numeroDigitos(numero, 0));
	}

	private int numeroDigitos(int n, int aux){
		if (n != 0) return numeroDigitos(n / 10, aux + 1);
		return aux;
	}

	private void mostrarPalindromo() {
		int numero = -1;
		do{ numero = Consola.leer("Dígite el número: ", numero); } while(numero < 0);
		palindromo(numero);
	}

	private void palindromo(int palindromo){
		int nI = invertirNumero(palindromo, 0);
		String mensaje = "";
		if (palindromo == nI) mensaje = "El número " + nI + " es palindromo: ";
		else mensaje = "El número " + nI + " no es palindromo: ";
		Consola.escribirSaltarLinea(mensaje);
	}

	private int invertirNumero(int n, int aux){
		if (n != 0) return invertirNumero(n / 10, aux * 10 + (n % 10));
		return aux;
	}

	public void mayorEdad() {
		Persona mayor = mayorEdad(personas.getCabeza(), 0, null);
		if (mayor != null) Consola.escribirSaltarLinea("La persona de mayor edad es: " + mayor.getNombre() + " id: " + mayor.getId() + " edad: " + mayor.getEdad());
	}

	private Persona mayorEdad(Nodo<Persona> aux, int edad, Persona mayor) {
		if(aux != null && aux.getDato().getEdad() >= edad) return mayorEdad(aux.getSiguiente(), aux.getDato().getEdad(), aux.getDato());
		else if(aux != null && aux.getDato().getEdad() < edad) return mayorEdad(aux.getSiguiente(), edad, mayor);
		return mayor;
	}

	public void menorEdad() {
		Persona menor = menorEdad(personas.getCabeza(), personas.getCabeza().getDato().getEdad(), null);
		if (menor != null) Consola.escribirSaltarLinea("La persona de menor edad es: " + menor.getNombre() + " id: " + menor.getId() + " edad: " + menor.getEdad());
	}

	private Persona menorEdad(Nodo<Persona> aux, int edad, Persona menor) {
		if(aux != null && aux.getDato().getEdad() <= edad) return menorEdad(aux.getSiguiente(), aux.getDato().getEdad(), aux.getDato());
		else if(aux != null && aux.getDato().getEdad() > edad) return menorEdad(aux.getSiguiente(), edad, menor);
		return menor;
	}

	public void promedioEdad() {
		int sumaEdad = sumaEdad(personas.getCabeza(), 0);
		if (sumaEdad != 0) Consola.escribirSaltarLinea("El promedio de edad es: " + sumaEdad / personas.getTamanio());
	}

	private static int sumaEdad(Nodo<Persona> aux, int suma) {
		if (aux != null) return sumaEdad(aux.getSiguiente(), suma + aux.getDato().getEdad());
		return suma;
	}

	@Override
	public void procesarOpcion() {
		if (personas == null) agregarLista();
		switch (atrOpcion) {
			case 1: mostrarNumeroDigitos(); break;
			case 2: mostrarPalindromo(); break;
			case 3: personas.mostrar(); break;
			case 4: mayorEdad(); break;
			case 5: menorEdad(); break;
			case 6: promedioEdad(); break;
			case 7: Consola.escribirSaltarLinea("Gracias por utlizar el programa!"); break;
		}	
	}
}
