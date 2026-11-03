package co.edu.unbosque.FuerzaBruta.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

// Ventana gráfica principal del buscador de texto en PDF
public class VentanaPrincipal extends JFrame {

    private JButton btnCargarPdf;
    private JTextField txtPalabra;
    private JRadioButton rbKMP;
    private JRadioButton rbBM;
    private ButtonGroup grupoAlgoritmos;
    private JCheckBox chkCaseSensitive;
    private JButton btnBuscar;
    private JTextArea areaTexto;
    private JLabel lblResultado;

    // Constructor que configura la ventana e inicializa sus componentes
    public VentanaPrincipal() {
        setTitle("Buscador de Texto en PDF - KMP y Boyer-Moore");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        inicializarComponentes();
    }

    // Crea y organiza los paneles, botones y área de texto
    private void inicializarComponentes() {
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        btnCargarPdf = new JButton("Cargar PDF");
        JLabel lblBuscar = new JLabel("Palabra:");
        txtPalabra = new JTextField(15);

        rbKMP = new JRadioButton("KMP", true);
        rbBM = new JRadioButton("Boyer-Moore");
        grupoAlgoritmos = new ButtonGroup();
        grupoAlgoritmos.add(rbKMP);
        grupoAlgoritmos.add(rbBM);

        chkCaseSensitive = new JCheckBox("Distinguir Mayúsculas/Minúsculas");
        btnBuscar = new JButton("Buscar");

        panelSuperior.add(btnCargarPdf);
        panelSuperior.add(lblBuscar);
        panelSuperior.add(txtPalabra);
        panelSuperior.add(rbKMP);
        panelSuperior.add(rbBM);
        panelSuperior.add(chkCaseSensitive);
        panelSuperior.add(btnBuscar);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        JScrollPane scrollTexto = new JScrollPane(areaTexto);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        lblResultado = new JLabel("Resultado: Cargue un archivo PDF para comenzar.");
        panelInferior.add(lblResultado);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollTexto, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // Resalta en color amarillo todas las posiciones encontradas
    public void resaltarPalabras(ArrayList<Integer> posiciones, int longitudPalabra) {
        Highlighter resaltador = areaTexto.getHighlighter();
        resaltador.removeAllHighlights();
        HighlightPainter pintor = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);

        for (int i = 0; i < posiciones.size(); i++) {
            int inicio = posiciones.get(i);
            int fin = inicio + longitudPalabra;
            try {
                resaltador.addHighlight(inicio, fin, pintor);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    // Retorna el botón para cargar el archivo PDF
    public JButton getBtnCargarPdf() {
        return btnCargarPdf;
    }

    // Retorna la caja de texto con la palabra buscada
    public JTextField getTxtPalabra() {
        return txtPalabra;
    }

    // Retorna el botón de selección del algoritmo KMP
    public JRadioButton getRbKMP() {
        return rbKMP;
    }

    // Retorna el botón de selección del algoritmo Boyer-Moore
    public JRadioButton getRbBM() {
        return rbBM;
    }

    // Retorna el checkbox de distinción entre mayúsculas y minúsculas
    public JCheckBox getChkCaseSensitive() {
        return chkCaseSensitive;
    }

    // Retorna el botón para iniciar la búsqueda
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    // Retorna el área donde se muestra el texto del PDF
    public JTextArea getAreaTexto() {
        return areaTexto;
    }

    // Retorna la etiqueta inferior donde se muestran los resultados
    public JLabel getLblResultado() {
        return lblResultado;
    }
}
