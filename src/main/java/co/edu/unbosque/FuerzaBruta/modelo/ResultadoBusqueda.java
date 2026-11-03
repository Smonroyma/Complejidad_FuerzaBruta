package co.edu.unbosque.FuerzaBruta.modelo;

import java.util.ArrayList;

// Guarda los datos obtenidos tras realizar una búsqueda
public class ResultadoBusqueda {

    private boolean encontrado;
    private int cantidad;
    private ArrayList<Integer> posiciones;

    // Constructor que inicializa los resultados de la búsqueda
    public ResultadoBusqueda(boolean encontrado, int cantidad, ArrayList<Integer> posiciones) {
        this.encontrado = encontrado;
        this.cantidad = cantidad;
        this.posiciones = posiciones;
    }

    // Retorna si la palabra fue encontrada o no
    public boolean isEncontrado() {
        return encontrado;
    }

    // Modifica el estado de si fue encontrada
    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    // Retorna cuántas veces apareció la palabra buscada
    public int getCantidad() {
        return cantidad;
    }

    // Modifica la cantidad de apariciones encontradas
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Retorna la lista de índices donde inicia cada coincidencia
    public ArrayList<Integer> getPosiciones() {
        return posiciones;
    }

    // Modifica la lista de posiciones encontradas
    public void setPosiciones(ArrayList<Integer> posiciones) {
        this.posiciones = posiciones;
    }
}
