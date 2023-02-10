package Negocio;

import java.io.*;
import java.util.ArrayList;

public class Archivo {
	private BufferedReader archivoLectura;
	private FileWriter archivoSalida;
	private ArrayList<String> datos;
	private String nombreArchivo;
	
	public Archivo(String rutaArchivo) { this.nombreArchivo = rutaArchivo; this.leerArchivo(); }
	
	public void leerArchivo(){
		this.datos = new ArrayList<String>();
		try {
			String linea;
			this.archivoLectura = new BufferedReader(new FileReader(this.nombreArchivo));
			while ((linea = this.archivoLectura.readLine()) != null) datos.add(linea);
		} catch (Exception ex) { System.out.println("ERROR: "+ex.getMessage()); }
	}
	
	public void escribirArchivo(String lineaNueva, int posicion) {
	    try {
			this.datos.add(posicion, lineaNueva);
	       	this.archivoSalida = new FileWriter(this.nombreArchivo);
			for (String linea : datos) this.archivoSalida.write(linea + "\n");
	        this.archivoSalida.close();
	    } catch (IOException ex) { System.out.println("Error: "+ex.getMessage()); }
	}

	public ArrayList<String> getDatos() { return this.datos; }
}
