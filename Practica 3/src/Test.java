import java.util.Scanner;

public class Test {
	private static Scanner entrada = new Scanner(System.in);

	public static <T> void main(String[] args){ ejerciciosMatriz(); }

	private static void ejerciciosMatriz(){
		int n, m;
		System.out.println("Digite el numero de filas de la matriz: ");
		n = entrada.nextInt();
		System.out.println("Digite el numero de columnas de la matriz: ");
		m = entrada.nextInt();
		int matriz[][] = new int [n][m];
		double vector[] = new double[n * m];
		matriz = llenarMatriz(matriz, 0, 0);
		System.out.println("La matriz es: ");
		mostrarMatriz(matriz, 0, 0);
		vector = llenarVector(matriz, vector, 0, 0, 0);
		ordenarRapido(vector);
		System.out.println("El vector ordenado queda");
		imprimirVector(vector, 0);
		System.out.println();
		System.out.println("La mediana es: ");
		mediana(vector);
		System.out.println();
		promedio(vector);
	}

	private static int[][] llenarMatriz(int [][] matriz, int n, int m){
		if (n < matriz.length) {
			if (m < matriz[matriz.length - 1].length) {
				System.out.println("Digite el valor: ");
				matriz[n][m] = entrada.nextInt();
				llenarMatriz(matriz, n, m + 1);
			} else llenarMatriz(matriz, n + 1, 0);
		}
		return matriz;
	}

	private static void mostrarMatriz(int [][] matriz, int n, int m){
		if (n < matriz.length) {
			if (m < matriz[matriz.length - 1].length) {
				System.out.print(" " + matriz[n][m]);
				if ((m + 1) == matriz[matriz.length - 1].length) System.out.println();
				mostrarMatriz(matriz, n, m + 1);
			}
			else mostrarMatriz(matriz, n + 1, 0);
		}
	}

	private static double[] llenarVector(int matriz[][], double vector[], int n, int m, int p){
		if (n < matriz.length) {
			if (m < matriz[matriz.length - 1].length) {
				if (p < vector.length) {
					vector[p] = matriz[n][m];
					llenarVector(matriz, vector, n, m + 1, p + 1);
				}
			}
			else llenarVector(matriz, vector, n + 1, 0, p);
		}
		return vector;
	}

	private static void imprimirVector(double vector[], int n){
		if (n < vector.length) {
			System.out.print(" " + (int)vector[n]);
			imprimirVector(vector, n + 1);
		}
	}

	private static void mediana(double vector[]){
		double med;
		if (vector.length % 2 != 0) System.out.print(" " + (int)vector[vector.length / 2]);
		else {
			med = ((vector[(vector.length / 2) - 1] + vector[vector.length / 2]) / 2);
			System.out.print(" " + med);
		}
	}

	private static int particionar(double[] vector, int posInicial, int posFinal) {
		double dato, temporal, indiceInicial, indiceFinal;
		dato = vector[posInicial];
		indiceInicial = posInicial;
		indiceFinal = posFinal;

		while (indiceInicial < posFinal && vector[(int)indiceInicial] <= dato) indiceInicial++;

		while (vector[(int)indiceFinal] > dato) indiceFinal--;

		while (indiceInicial < indiceFinal) {
			temporal = vector[(int)indiceInicial];
			vector[(int)indiceInicial] = vector[(int)indiceFinal];
			vector[(int)indiceFinal] = temporal;
			indiceInicial++;
			indiceFinal--;

			while (vector[(int) indiceInicial] <= dato) indiceInicial++;

			while (vector[(int) indiceFinal] > dato) indiceFinal--;
		}
		temporal = vector[posInicial];
		vector[posInicial] = vector[(int)indiceFinal];
		vector[(int)indiceFinal] = temporal;
		return (int)indiceFinal;
	}

	private static void ordenar(double [] vector, int posInicial, int posFinal) {
		int p;
		if (posFinal > posInicial) {
			p = particionar(vector, posInicial, posFinal);
			ordenar(vector, posInicial, p - 1);
			ordenar(vector, p + 1, posFinal);
		}
	}

	private static void ordenarRapido(double[] vector) { ordenar(vector, 0, vector.length - 1); }

	private static void promedio(double [] vector) { promedio(vector, 0, 0); }

	private static void promedio(double [] vector, double suma, int indice) {
		if (indice < vector.length) {
			suma += vector[indice];
			promedio(vector, suma, indice + 1);
		}
		if (indice == vector.length) {
			System.out.println(suma);
			System.out.println(vector.length);
			Double promedio = (double)(suma / vector.length);
			System.out.println("Promedio: " + promedio);
		}
	}
}
