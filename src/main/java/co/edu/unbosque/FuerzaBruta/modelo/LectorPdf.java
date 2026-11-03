package co.edu.unbosque.FuerzaBruta.modelo;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

// Clase encargada de extraer el texto de un PDF
public class LectorPdf {

    // Extrae y normaliza todo el texto del archivo PDF
    public String extraerTexto(File archivo) throws IOException {
        PDDocument documento = PDDocument.load(archivo);
        PDFTextStripper stripper = new PDFTextStripper();
        String texto = stripper.getText(documento);
        documento.close();
        texto = texto.replace("\r\n", "\n");
        return texto;
    }
}
