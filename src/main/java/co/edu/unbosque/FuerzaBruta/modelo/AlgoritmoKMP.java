package co.edu.unbosque.FuerzaBruta.modelo;

import java.util.ArrayList;

// Implementación del algoritmo de búsqueda Knuth-Morris-Pratt (KMP)
public class AlgoritmoKMP {

    // KMP: Calcula el arreglo de prefijos más largos (LPS)
    public int[] calcularLPS(String patron) {
        int m = patron.length();
        int[] lps = new int[m];
        int longitud = 0;
        int i = 1;
        lps[0] = 0;

        while (i < m) {
            if (patron.charAt(i) == patron.charAt(longitud)) {
                longitud++;
                lps[i] = longitud;
                i++;
            } else {
                if (longitud != 0) {
                    longitud = lps[longitud - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // KMP: Busca el patrón en el texto usando LPS
    public ResultadoBusqueda buscar(String texto, String patron, boolean caseSensitive) {
        ArrayList<Integer> posiciones = new ArrayList<Integer>();

        if (texto == null || patron == null) {
            return new ResultadoBusqueda(false, 0, posiciones);
        }

        patron = patron.trim();

        if (patron.length() == 0 || texto.length() < patron.length()) {
            return new ResultadoBusqueda(false, 0, posiciones);
        }

        if (!caseSensitive) {
            texto = texto.toLowerCase();
            patron = patron.toLowerCase();
        }

        int n = texto.length();
        int m = patron.length();
        int[] lps = calcularLPS(patron);

        int i = 0;
        int j = 0;

        while (i < n) {
            if (patron.charAt(j) == texto.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                posiciones.add(i - j);
                j = lps[j - 1];
            } else if (i < n && patron.charAt(j) != texto.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        boolean encontrado = posiciones.size() > 0;
        int cantidad = posiciones.size();
        return new ResultadoBusqueda(encontrado, cantidad, posiciones);
    }
}
