package Negocio;

import java.io.*;
import java.util.ArrayList;

public class Archivo {
	private BufferedReader archivoEntrada;
	private String nombreArchivo;
	private ArrayList<String> datos;
	
	public Archivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; this.leer(); }
	
	private void leer() {
		try {
			String linea;
			this.datos = new ArrayList<String>();
			this.archivoEntrada = new BufferedReader(new FileReader(this.nombreArchivo));
			while ((linea = this.archivoEntrada.readLine()) != null) this.datos.add(linea);
		} catch (Exception ex) { Consola.escribirSaltarLinea("Error: "+ex.getMessage()); }
	}

	public ArrayList<String> getDatos() { return this.datos; }
}
