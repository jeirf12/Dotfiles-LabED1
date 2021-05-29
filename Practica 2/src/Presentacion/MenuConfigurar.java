package Presentacion;

import Logica.*;

public class MenuConfigurar extends Menu{
	private Lista<Persona> personas=new Lista<Persona>();
	
	public MenuConfigurar(String parTitulo, String[] parVectoropciones) {
		super(parTitulo, parVectoropciones);
	}
	private void agregarLista() {
		Lista<Persona> aux=new Lista<Persona>();
		Persona p1=new Persona(1234, "mateo", 27);
		Persona p2=new Persona(1324, "cris", 26);
		Persona p3=new Persona(1243, "holy", 28);
		Persona p4=new Persona(2134, "julian", 20);
		aux.Agregar(p1);
		aux.Agregar(p2);
		aux.Agregar(p3);
		aux.Agregar(p4);
		personas=aux;
	}
	private void mostrarNumeroDigitos() {
		int numero=0;
		do {
			numero=Consola.leer("Dígite el número: ", numero, false);
		}while(numero<1 || !Consola.esNumerico(""+numero));
		Consola.escribirSaltarLinea("El numero de digitos total del numero "+numero+" es: "+numeroDigitos(numero), false);
	}
	private int numeroDigitos(int n){
		int aux=0;
		if (n!=0) {
			aux++;
			return aux+numeroDigitos(n/10);
		}
		return aux;
	}
	private void mostrarPalindromo() {
		int numero=0;
		do {
			numero=Consola.leer("Dígite el número: ", numero, false);
		} while (numero<1 || !Consola.esNumerico(""+numero));
		palindromo(numero);
	}
	private void palindromo(int palindromo){
		int nI=invertirNumero(palindromo, 0);
		if (palindromo==nI) {
			Consola.escribirSaltarLinea("El número "+nI+" es palindromo: ", false);
		} else {
			Consola.escribirSaltarLinea("El número "+nI+" no es palindromo: ", false);
		}
	}
	private int invertirNumero(int n,int aux){
		if (n!=0) {
			aux=aux*10+(n%10);
			return invertirNumero(n/10,aux);
		}
		return aux;
	}
	@Override
	protected void procesarOpcion() {
		if (personas==null) {
			agregarLista();
		}
		switch (atrOpcion) {
			case 1:mostrarNumeroDigitos();break;
			case 2:mostrarPalindromo();break;
			case 3:personas.mostrar();break;
			case 4:Lista.mayorEdad(personas);break;
			case 5:Lista.menorEdad(personas);break;
			case 6:Lista.promedioEdad(personas);break;
			case 7:Consola.escribirSaltarLinea("Gracias por utlizar el programa!", false);break;
		}	
	}
	
}
