import java.util.Scanner;

public class Test {
	public static Scanner entrada=new Scanner(System.in);
	public static <T> void main(String[] args){
		ejerciciosMatriz();
		
	}
	public static void ejerciciosMatriz(){
		int n,m;
		System.out.println("Digite el numero de filas de la matriz: ");
		n=entrada.nextInt();
		System.out.println("Digite el numero de columnas de la matriz: ");
		m=entrada.nextInt();
		int matriz[][]=new int [n][m];
		double vector[]=new double[n*m];
		matriz=llenarMatriz(matriz, 0, 0);
		System.out.println("La matriz es: ");
		mostrarMatriz(matriz, 0, 0);
		vector=llenarVector(matriz, vector, 0, 0, 0);
		//vector=burbujaRecursivo(vector, 0, 0);
		ordenarRapido(vector);
		//mezclar(vector);
		System.out.println("El vector ordenado queda");
		imprimirVector(vector, 0);
		System.out.println();
		System.out.println("La mediana es: ");
		mediana(vector);
		System.out.println();
		promedio(vector);
	}
	public static int[][] llenarMatriz(int [][] matriz,int n,int m){
		if (n<matriz.length) {
			if (m<matriz[matriz.length-1].length) {
				System.out.println("Digite el valor: ");
				matriz[n][m]=entrada.nextInt();
				llenarMatriz(matriz, n, m+1);
			}
			else {
				llenarMatriz(matriz, n+1,0);
			}
		}
		return matriz;
	}
	public static void mostrarMatriz(int [][] matriz,int n,int m){
		if (n<matriz.length) {
			if (m<matriz[matriz.length-1].length) {
				System.out.print(" "+matriz[n][m]);
				if ((m+1)==matriz[matriz.length-1].length) {
					System.out.println();
				}
				mostrarMatriz(matriz, n, m+1);
			}
			else {
				mostrarMatriz(matriz, n+1, 0);
			}
		}
	}
	public static double[] llenarVector(int matriz[][],double vector[],int n,int m,int p){
		if (n<matriz.length) {
			if (m<matriz[matriz.length-1].length) {
				if (p<vector.length) {
					vector[p]=matriz[n][m];
					llenarVector(matriz,vector, n, m+1,p+1);
				}
			}
			else {
				llenarVector(matriz,vector, n+1,0,p);
			}
		}
		return vector;
	}
	public static void imprimirVector(double vector[],int n){
		if (n<vector.length) {
			System.out.print(" "+(int)vector[n]);
			imprimirVector(vector, n+1);
		}
	}
	public static void mediana(double vector[]){
		double med;
		if (vector.length%2!=0) {
			System.out.print(" "+(int)vector[vector.length/2]);
		}
		else {
			med=((vector[(vector.length/2)-1]+vector[vector.length/2])/2);
			System.out.print(" "+med);
		}
	}
	public static double[] burbujaRecursivo(double vector[],int i,int j){
		double aux=0;
		if (i<vector.length) {
			if (j<vector.length-1) {
				if (vector[j]>vector[j+1]) {
					aux=vector[j];
					vector[j]=vector[j+1];
					vector[j+1]=aux;
				}
				burbujaRecursivo(vector, i, j+1);
			}
			burbujaRecursivo(vector, i+1, 0);
		}
		return vector;
	}
	private static int particionar(double[] vector,int posInicial,int posFinal) {
		double dato,temporal,indiceInicial,indiceFinal;
		dato=vector[posInicial];
		indiceInicial=posInicial;
		indiceFinal=posFinal;
		while (indiceInicial<posFinal && vector[(int) indiceInicial]<=dato) {
			indiceInicial++;
		}
		while (vector[(int) indiceFinal]>dato) {
			indiceFinal--;
		}
		while (indiceInicial<indiceFinal) {
			temporal=vector[(int) indiceInicial];
			vector[(int) indiceInicial]=vector[(int) indiceFinal];
			vector[(int) indiceFinal]=temporal;
			indiceInicial++;
			indiceFinal--;
			while (vector[(int) indiceInicial]<=dato) {
				indiceInicial++;
			}
			while (vector[(int) indiceFinal]>dato) {
				indiceFinal--;
			}
		}
		temporal=vector[posInicial];
		vector[posInicial]=vector[(int) indiceFinal];
		vector[(int) indiceFinal]=temporal;
		return (int) indiceFinal;
	}
	private static void ordenar(double [] vector,int posInicial,int posFinal) {
		int p;
		if (posFinal>posInicial) {
			p=particionar(vector, posInicial, posFinal);
			ordenar(vector, posInicial, p-1);
			ordenar(vector, p+1, posFinal);
		}
	}
	public static void ordenarRapido(double[] vector) {
		ordenar(vector, 0, vector.length-1);
	}
	private static void mezcla(double [] vector,int posInicial,int media,int posFinal) {
		double[] auxiliar=vector;
		int indiceInicial,indiceMedia,indiceAuxiliar;
		indiceInicial=posInicial;
		indiceMedia=media+1;
		indiceAuxiliar=posInicial;
		while (indiceMedia<=media && indiceMedia<=posFinal) {
			if (auxiliar[indiceInicial]<auxiliar[indiceMedia]) {
				vector[indiceAuxiliar]=auxiliar[indiceInicial];
				indiceInicial++;
			}else {
				vector[indiceAuxiliar]=auxiliar[indiceMedia];
				indiceMedia++;
			}
			indiceAuxiliar++;
		}
		while (indiceInicial<=media) {
			vector[indiceAuxiliar]=auxiliar[indiceInicial];
			indiceInicial++;
			indiceAuxiliar++;
		}
		while (indiceMedia<=posFinal) {
			vector[indiceAuxiliar]=auxiliar[indiceMedia];
			indiceMedia++;
			indiceAuxiliar++;
		}
	}
	private static int ordena(double [] vector,int posInicial,int posFinal) {
		int media;
		if (posInicial==posFinal) {
			return 0;
		}
		media=posInicial+((posFinal-posInicial)/2);
		ordena(vector, posInicial, media);
		ordena(vector, media+1, posFinal);
		mezcla(vector, posInicial, media, posFinal);
		return -1;
	}
	public static void mezclar(double [] vector) {
		ordena(vector, 0, vector.length-1);
	}
	public static void promedio(double [] vector) {
		promedio(vector,0,0);
	}
	private static void promedio(double [] vector, double suma,int indice) {
		if (indice<vector.length) {
			suma+=vector[indice];
			promedio(vector, suma, indice+1);
		}
		if (indice==vector.length) {
			System.out.println(suma);
			System.out.println(vector.length);
			Double promedio=(double) (suma/vector.length);
			System.out.println("Promedio: "+promedio);
		}
	}
}
