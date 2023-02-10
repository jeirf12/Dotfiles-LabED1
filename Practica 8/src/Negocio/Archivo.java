package Negocio;

import java.io.*;
import java.util.ArrayList;

public class Archivo {
	private BufferedReader archivoEntrada;
	private ArrayList<String> datos;
	private String nombreArchivo;

	public Archivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; this.leer(); }

	private void leer() {
		String linea;
		this.datos = new ArrayList<String>();
		try {
			archivoEntrada = new BufferedReader(new FileReader(this.nombreArchivo));
			while ((linea = archivoEntrada.readLine()) != null) this.datos.add(linea);
		} catch (Exception ex) { System.err.println("Error: "+ex.getMessage()); }
	}

	public ArrayList<String> getDatos() { return this.datos; }
}
