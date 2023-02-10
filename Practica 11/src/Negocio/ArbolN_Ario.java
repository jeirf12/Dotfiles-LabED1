package Negocio;

import java.util.ArrayList;

public class ArbolN_Ario<T> {
    private NodoArbol<T> raiz;
    
    public ArbolN_Ario() { this.raiz = null; }

    public NodoArbol<T> getRaiz() { return this.raiz; }

    public void setRaiz(NodoArbol<T> raiz) { this.raiz = raiz; }
    
    public void insertarRaiz(T dato) { this.raiz = new NodoArbol<T>(dato); }
    
    public void agregar(T padre, T hijo) { 
        NodoArbol<T> nuevo = new NodoArbol<T>(hijo);
        this.retornarNodo(padre).setHijos(nuevo);
    }

    public void imprimirNivelesArbol() { System.out.println("Recorrido Por Niveles"); this.imprimirNivelesArbol(this.raiz, 0); }

    private void imprimirNivelesArbol(NodoArbol<T> arbol, int nivel) {
        if (arbol.getHijos().getTamanio() > 0) {
            System.out.println(" " + nivel + " " + arbol.getElemento());
            NodoLista<NodoArbol<T>> aux = arbol.getHijos().getCabeza();
            this.imprimirNivelesArbol(aux, nivel);
        }else System.out.println(" " + nivel + " " + arbol.getElemento());
    }

    private void imprimirNivelesArbol(NodoLista<NodoArbol<T>> aux, int nivel) {
        if (!this.esNulo(aux)) {
            this.imprimirNivelesArbol(aux.getDato(), nivel + 1);
            this.imprimirNivelesArbol(aux.getSiguiente(), nivel);
        }
    }
    
    public boolean buscarElemento(T elemento) { return this.buscarElemento(this.raiz, elemento); }   

    private boolean buscarElemento(NodoArbol<T> arbol, T elemento) {
        if (arbol.getElemento().equals(elemento)) return true;
        else return buscarElemento(arbol.getHijos().getCabeza(), elemento);
    }

    private boolean buscarElemento(NodoLista<NodoArbol<T>> aux, T elemento) {
        if (!this.esNulo(aux)) {
            if (this.buscarElemento(aux.getDato(), elemento)) return true;
            return this.buscarElemento(aux.getSiguiente(), elemento);
        }
        return false;
    }
    
    public void alturaArbol() { System.out.println("La altura del arbol es: "+(this.alturaArbol(this.raiz, 0, 0) + 1)); }

    private int alturaArbol(NodoArbol<T> arbol, int nivel, int altura) {
        if (arbol != null) {
            if (nivel > altura) altura = nivel;
            altura = this.alturaArbol(arbol.getHijos().getCabeza(), nivel, altura);
        }
        return altura;
    }

    private int alturaArbol(NodoLista<NodoArbol<T>> aux, int nivel, int altura) {
        if (!this.esNulo(aux)) {
            altura = this.alturaArbol(aux.getDato(), nivel + 1, altura);
            return this.alturaArbol(aux.getSiguiente(), nivel, altura);
        }
        return altura;
    }
    
    public void contarHijos() {
        ArrayList<NodoArbol<T>> nodos = new ArrayList<NodoArbol<T>>();
        nodos = this.contarHijos(this.raiz, nodos, this.raiz.getHijos().getTamanio());
        System.out.println("El/Los Nodo de mayor numero de hijos es/son: ");
        for (int i = 0; i < nodos.size(); i++) System.out.println("Nodo: " + nodos.get(i).getElemento() + " N° Hijos: " + nodos.get(i).getHijos().getTamanio());
    }

    private ArrayList<NodoArbol<T>> contarHijos(NodoArbol<T> arbol, ArrayList<NodoArbol<T>> nodos, int mayor) {
        if (arbol.getHijos().getTamanio() >= mayor) {
            mayor = arbol.getHijos().getTamanio();
            nodos.add(arbol);
        }
        return this.contarHijos(arbol.getHijos().getCabeza(), nodos, mayor);
    }

    private ArrayList<NodoArbol<T>> contarHijos(NodoLista<NodoArbol<T>> aux, ArrayList<NodoArbol<T>> nodos, int mayor){
        if (!this.esNulo(aux)) {
            nodos = this.contarHijos(aux.getDato(), nodos, mayor);
            nodos = this.contarHijos(aux.getSiguiente(), nodos, mayor);
        }
        return nodos;
    }
    
    public void mostrarHijosNodo(T nodo) {
        NodoArbol<T> varNodo = this.retornarNodo(nodo);
        if (varNodo != null) {
            if (varNodo.getHijos().getTamanio() > 0) {
                NodoLista<NodoArbol<T>> aux = varNodo.getHijos().getCabeza();
                System.out.println("Los hijos del nodo " + nodo + " son: ");
                this.mostrarHijosNodo(aux);
                System.out.println();
            }else System.out.println("El dato " + nodo + " no tiene hijos, porque es una hoja");
        }else System.out.println("El dato ingresado " + nodo + " no tiene hijos, porque no existe el nodo");
    }

    private void mostrarHijosNodo(NodoLista<NodoArbol<T>> aux) {
        if (!this.esNulo(aux)) {
            System.out.print(" " + aux.getDato().getElemento());
            this.mostrarHijosNodo(aux.getSiguiente());
        }
    }
    
    public void mostrarNodosNivel(int n) {
        int altura = this.alturaArbol(this.raiz, 0, 0);
        System.out.println("Los nodos del nivel " + n + " son: ");
        this.mostrarNodosNivel(this.raiz, n, 0, altura);
        System.out.println();
    }

    private void mostrarNodosNivel(NodoArbol<T> arbol, int n, int nivel, int altura) {
        if (arbol != null) {
            if (n <= altura) {
                if (n != nivel) {
                    NodoLista<NodoArbol<T>> aux = arbol.getHijos().getCabeza();
                    this.mostrarNodosNivel(aux, n, nivel, altura);
                }else if (n == nivel) System.out.print(" " + arbol.getElemento());
            }else System.out.println("El nivel ingresado no existe en el arbol");
        }
    }

    private void mostrarNodosNivel(NodoLista<NodoArbol<T>> aux, int n, int nivel, int altura) {
        if (!this.esNulo(aux)) {
            this.mostrarNodosNivel(aux.getDato(), n, nivel + 1, altura);
            this.mostrarNodosNivel(aux.getSiguiente(), n, nivel, altura);
        }
    }
    
    private NodoArbol<T> retornarNodo(T dato) {
        if (this.buscarElemento(dato)) return this.retornarNodo(this.raiz, dato);
        return null;
    }

    private NodoArbol<T> retornarNodo(NodoArbol<T> arbol, T dato) {
        NodoLista<NodoArbol<T>> aux = arbol.getHijos().getCabeza();
        return this.retornarNodo(aux, arbol, dato);
    }

    private NodoArbol<T> retornarNodo(NodoLista<NodoArbol<T>> aux, NodoArbol<T> arbol, T dato){
        if (!this.esNulo(aux)) {
            if (arbol.getElemento().equals(dato)) return arbol;
            arbol = this.retornarNodo(aux.getDato(), dato);
            return this.retornarNodo(aux.getSiguiente(), arbol, dato);
        }
        return arbol;
    }

    private boolean esNulo(NodoLista<NodoArbol<T>> nodo) { return nodo == null; }
}
