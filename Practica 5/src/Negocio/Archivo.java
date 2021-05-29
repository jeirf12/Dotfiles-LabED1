package Negocio;

import java.io.*;
import java.util.ArrayList;

public class Archivo {
	private FileReader archivoEntrada;
	private FileWriter archivoSalida;
	
	public Archivo(){
		archivoEntrada=null;
		archivoSalida=null;
	}
	public Archivo(String rutaArchivo) {
		int opcionArbol;
		String [] lineaDatos=null;
		ArrayList<String> datos=this.leerArchivo(rutaArchivo);
		for (int i=0; i<datos.size(); i++) {
			lineaDatos=datos.get(i).split(",");
			opcionArbol=MenuConfigurar.seleccionArbol(lineaDatos[0]);
			if (opcionArbol==0) {
				if (Consola.esNumerico(lineaDatos[0]) && !lineaDatos[0].equals("0")) {
					if (MenuConfigurar.arbolNumerico.getRaiz()==null) {
						MenuConfigurar.arbolNumerico.insertar(Integer.parseInt(lineaDatos[0]));
					}else {
						if (Consola.esNumerico(lineaDatos[1])) {
							MenuConfigurar.arbolNumerico.insertar(Integer.parseInt(lineaDatos[0]), Integer.parseInt(lineaDatos[1]), lineaDatos[2]);
						}
					}
				}
			}
			else if (opcionArbol==1) {
				if (!Consola.esNumerico(lineaDatos[0])) {
					if (MenuConfigurar.arbolCaracter.getRaiz()==null) {
						MenuConfigurar.arbolCaracter.insertar(lineaDatos[0]);
					}else {
						if (!Consola.esNumerico(lineaDatos[1])) {
							MenuConfigurar.arbolCaracter.insertar(lineaDatos[0], lineaDatos[1], lineaDatos[2]);
						}
					}
				}
			}
		}
	}
	
	public ArrayList<String> leerArchivo(String nombre){
		String linea;
		ArrayList<String> datos=new ArrayList<String>();
		try {
			archivoEntrada =new FileReader(nombre);
			BufferedReader entrada=new BufferedReader(archivoEntrada);
			while ((linea=entrada.readLine())!=null) {
				datos.add(linea);
			}
		} catch (Exception ex) {
			System.out.println("ERROR: "+ex.getMessage());
		}
		return datos;
	}
	
	public void escribirArchivo(String nombreArchivo,String padre, String elemento,String parentesco) {
	        try {
	        	archivoSalida = new FileWriter(nombreArchivo, true);
	        	
	            if (padre.equals("") || parentesco.equals("Raiz")) {
	            	archivoSalida.write(padre+"\n");
				} else {
					archivoSalida.write("\n"+padre+","+elemento+","+parentesco);
				}
	            archivoSalida.close();
	        } catch (IOException ex) {
	            System.out.println("Error: "+ex.getMessage());
	        }
	}
}
