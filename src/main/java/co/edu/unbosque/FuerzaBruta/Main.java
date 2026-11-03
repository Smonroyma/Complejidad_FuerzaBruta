package co.edu.unbosque.FuerzaBruta;

import co.edu.unbosque.FuerzaBruta.controlador.ControladorBusqueda;
import co.edu.unbosque.FuerzaBruta.vista.VentanaPrincipal;

// Clase principal que inicia la aplicación Buscador de PDF
public class Main {

    // Instancia la vista y el controlador para iniciar el programa
    public static void main(String[] args) {
        VentanaPrincipal vista = new VentanaPrincipal();
        ControladorBusqueda controlador = new ControladorBusqueda(vista);
        vista.setVisible(true);
    }
}
