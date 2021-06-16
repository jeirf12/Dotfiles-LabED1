package com.mycompany.negocio;

import java.util.Comparator;

/**
 *
 * @author fallen
 */
public class AristaSort<T> implements Comparator<Arista<T>> {

    @Override
    public int compare(Arista<T> t, Arista<T> t1) {
        return t.getCoste() - t1.getCoste();
    }
    
}
