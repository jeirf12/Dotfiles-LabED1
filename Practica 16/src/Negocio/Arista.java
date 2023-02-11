package Negocio;

/**
 *
 * @author fallen
 */
public class Arista<T> {
    private NodoGrafo<T> inicial;
    private NodoGrafo<T> fin;
    private int coste;

    public Arista(NodoGrafo<T> inicial, NodoGrafo<T> fin, int coste) { this.inicial = inicial; this.fin = fin; this.coste = coste; }
    
    public NodoGrafo<T> getInicial() { return this.inicial; }

    public void setInicial(NodoGrafo<T> inicial) { this.inicial = inicial; }

    public NodoGrafo<T> getFin() { return this.fin; }

    public void setFin(NodoGrafo<T> fin) { this.fin = fin; }

    public int getCoste() { return this.coste; }

    public void setCoste(int coste) { this.coste = coste; }  
}
