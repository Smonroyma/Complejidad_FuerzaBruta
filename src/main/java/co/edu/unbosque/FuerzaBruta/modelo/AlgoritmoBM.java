package co.edu.unbosque.FuerzaBruta.modelo;

import java.util.ArrayList;

// Implementación del algoritmo de búsqueda Boyer-Moore (heurística mal carácter)
public class AlgoritmoBM {

    // Boyer-Moore: Llena tabla con última posición de cada carácter
    public int[] calcularTablaMalCaracter(String patron) {
        int[] tablaMalCaracter = new int[65536];

        for (int i = 0; i < 65536; i++) {
            tablaMalCaracter[i] = -1;
        }

        for (int i = 0; i < patron.length(); i++) {
            tablaMalCaracter[(int) patron.charAt(i)] = i;
        }

        return tablaMalCaracter;
    }

    // Boyer-Moore: Busca el patrón comparando de derecha a izquierda
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
        int[] tablaMalCaracter = calcularTablaMalCaracter(patron);

        int desplazamiento = 0;

        while (desplazamiento <= (n - m)) {
            int j = m - 1;

            while (j >= 0 && patron.charAt(j) == texto.charAt(desplazamiento + j)) {
                j--;
            }

            if (j < 0) {
                posiciones.add(desplazamiento);

                if (desplazamiento + m < n) {
                    char siguienteCaracter = texto.charAt(desplazamiento + m);
                    desplazamiento += m - tablaMalCaracter[(int) siguienteCaracter];
                } else {
                    desplazamiento += 1;
                }
            } else {
                char caracterTexto = texto.charAt(desplazamiento + j);
                int salto = j - tablaMalCaracter[(int) caracterTexto];
                if (salto < 1) {
                    salto = 1;
                }
                desplazamiento += salto;
            }
        }

        boolean encontrado = posiciones.size() > 0;
        int cantidad = posiciones.size();
        return new ResultadoBusqueda(encontrado, cantidad, posiciones);
    }
}
