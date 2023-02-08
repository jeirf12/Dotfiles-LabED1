package Negocio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Archivo {
	private FileReader archivo;
	private BufferedReader archivoEntrada;

	public Archivo() { archivo=null; archivoEntrada=null; }

	public ArrayList<String> leer(String nombre) {
		try {
			String linea;
			ArrayList<String> datos = new ArrayList<String>();
			archivo = new FileReader(nombre);
			archivoEntrada = new BufferedReader(archivo);
			while ((linea = archivoEntrada.readLine()) != null) datos.add(linea);
			return datos;
		} catch (Exception ex) { System.err.println("Error: "+ex.getMessage()); }
		return null;
	}
}
