package Negocio;

import java.io.*;
import java.util.ArrayList;

public class Archivo {
	private FileReader archivolec;
	private FileWriter archivoEsc;
	private BufferedReader archivoLectura;
	private PrintWriter archivoEscritura;
	public Archivo() {
		try {
			archivoEsc=null;
			archivolec=null;
			archivoLectura= null;
			archivoEscritura=null;
		} catch (Exception ex) {
			System.err.println("Error: "+ex.getMessage());
		}
	}
	public ArrayList<String> leerArchivo(String nombre){
		String lineaArchivo;
		ArrayList<String> parLineas = new ArrayList<String>();
		try {
			archivolec= new FileReader(nombre);
			archivoLectura=new BufferedReader(archivolec);
			while ((lineaArchivo = archivoLectura.readLine()) != null) {
				parLineas.add(lineaArchivo);
			}
			archivoLectura.close();
		} catch (Exception ex) {
			System.err.println("Error: "+ex.getMessage());
		}
		
		return parLineas;
	}
	public void escribirArchivo(String nombre,String dato) {
        try {
        	archivoEsc=new FileWriter(nombre,true);
        	archivoEscritura=new PrintWriter(archivoEsc);
        	archivoEscritura.write(dato);
            archivoEscritura.close();
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
	}
}
