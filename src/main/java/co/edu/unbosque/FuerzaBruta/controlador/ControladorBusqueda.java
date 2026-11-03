package co.edu.unbosque.FuerzaBruta.controlador;

import co.edu.unbosque.FuerzaBruta.modelo.AlgoritmoBM;
import co.edu.unbosque.FuerzaBruta.modelo.AlgoritmoKMP;
import co.edu.unbosque.FuerzaBruta.modelo.LectorPdf;
import co.edu.unbosque.FuerzaBruta.modelo.ResultadoBusqueda;
import co.edu.unbosque.FuerzaBruta.vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

// Controla los eventos de la vista y coordina el modelo
public class ControladorBusqueda {

    private VentanaPrincipal vista;
    private LectorPdf lector;
    private AlgoritmoKMP kmp;
    private AlgoritmoBM bm;
    private String textoActual;

    // Inicializa el controlador, los modelos y los eventos
    public ControladorBusqueda(VentanaPrincipal vista) {
        this.vista = vista;
        this.lector = new LectorPdf();
        this.kmp = new AlgoritmoKMP();
        this.bm = new AlgoritmoBM();
        this.textoActual = "";

        configurarEventos();
    }

    // Asigna los ActionListener tradicionales a los botones principales
    private void configurarEventos() {
        vista.getBtnCargarPdf().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarArchivoPdf();
            }
        });

        vista.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarBusqueda();
            }
        });
    }

    // Abre el selector de archivos y extrae el texto PDF
    public void cargarArchivoPdf() {
        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos PDF (*.pdf)", "pdf");
        selector.setFileFilter(filtro);

        int opcion = selector.showOpenDialog(vista);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selector.getSelectedFile();
            try {
                textoActual = lector.extraerTexto(archivoSeleccionado);
                vista.getAreaTexto().setText(textoActual);
                vista.getAreaTexto().setCaretPosition(0);
                vista.getLblResultado().setText("PDF cargado correctamente: " + archivoSeleccionado.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(vista, "Error al leer el archivo PDF.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Ejecuta el algoritmo seleccionado y resalta las palabras encontradas
    public void ejecutarBusqueda() {
        if (textoActual == null || textoActual.length() == 0) {
            JOptionPane.showMessageDialog(vista, "Primero debe cargar un archivo PDF.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String palabra = vista.getTxtPalabra().getText();
        if (palabra != null) {
            palabra = palabra.trim();
        }

        if (palabra == null || palabra.length() == 0) {
            JOptionPane.showMessageDialog(vista, "Por favor escriba una palabra válida (no solo espacios).", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean caseSensitive = vista.getChkCaseSensitive().isSelected();
        ResultadoBusqueda resultado;
        String nombreAlgoritmo;

        if (vista.getRbKMP().isSelected()) {
            resultado = kmp.buscar(textoActual, palabra, caseSensitive);
            nombreAlgoritmo = "KMP";
        } else {
            resultado = bm.buscar(textoActual, palabra, caseSensitive);
            nombreAlgoritmo = "Boyer-Moore";
        }

        if (resultado.isEncontrado()) {
            vista.getLblResultado().setText(
                "Resultado (" + nombreAlgoritmo + "): Sí se encontró la palabra. Apariciones: " + resultado.getCantidad()
            );
            vista.resaltarPalabras(resultado.getPosiciones(), palabra.length());
        } else {
            vista.getLblResultado().setText(
                "Resultado (" + nombreAlgoritmo + "): No se encontró la palabra en el documento. Apariciones: 0"
            );
            vista.resaltarPalabras(new ArrayList<Integer>(), 0);
        }
    }
}
