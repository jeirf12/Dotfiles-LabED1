package Negocio;

import java.io.*;
import java.util.ArrayList;

public class Archivo {
	private BufferedReader archivoLectura;
	private PrintWriter archivoEscritura;
	private String nombreArchivo;
	private ArrayList<String> parLineas;

	public Archivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; this.leerArchivo(); }

	public void leerArchivo() {
		this.parLineas = new ArrayList<String>();
		try {
			String lineaArchivo;
			archivoLectura = new BufferedReader(new FileReader(this.nombreArchivo));
			while ((lineaArchivo = archivoLectura.readLine()) != null) this.parLineas.add(lineaArchivo);
			archivoLectura.close();
		} catch (Exception ex) { System.err.println("Error: "+ex.getMessage()); }
	}

	public void escribirArchivo(String dato) {
        try {
        	archivoEscritura = new PrintWriter(new FileWriter(this.nombreArchivo, true));
        	archivoEscritura.write(dato);
            archivoEscritura.close();
        } catch (IOException ex) { System.out.println("Error: "+ex.getMessage()); }
	}

	public ArrayList<String> getDatos() { return this.parLineas; }
}
