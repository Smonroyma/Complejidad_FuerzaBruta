# Buscador de Texto en PDF (KMP y Boyer-Moore)

Proyecto académico de Complejidad Algorítmica (Ingeniería de Sistemas - 5to Semestre) para buscar palabras o frases dentro de documentos PDF utilizando algoritmos clásicos de búsqueda de cadenas.

## Descripción
La aplicación permite cargar cualquier archivo PDF desde el computador, visualizar su contenido completo en pantalla y buscar palabras o frases eligiendo entre dos algoritmos de búsqueda exacta:
- **KMP (Knuth-Morris-Pratt):** Utiliza el arreglo de prefijos propios más largos que también son sufijos (`LPS`) para evitar retrocesos innecesarios en el texto.
- **BM (Boyer-Moore):** Utiliza la heurística del mal carácter (`tablaMalCaracter`) comparando de derecha a izquierda para realizar saltos sobre el texto.

Además, permite activar o desactivar la opción **Case Sensitive** (distinguir mayúsculas y minúsculas), muestra si el texto buscado se encuentra o no, indica el número total de apariciones y resalta en color amarillo todas las coincidencias.

## Arquitectura MVC
El proyecto está organizado bajo el patrón **Modelo - Vista - Controlador (MVC)** en el paquete `co.edu.unbosque.FuerzaBruta`:

```text
co.edu.unbosque.FuerzaBruta
├── Main.java                       # Punto de entrada que inicia Vista y Controlador
├── modelo/
│   ├── ResultadoBusqueda.java      # Almacena estado, cantidad y posiciones encontradas
│   ├── LectorPdf.java              # Extrae el texto del PDF usando Apache PDFBox
│   ├── AlgoritmoKMP.java           # Búsqueda de cadenas mediante Knuth-Morris-Pratt
│   └── AlgoritmoBM.java            # Búsqueda de cadenas mediante Boyer-Moore
├── vista/
│   └── VentanaPrincipal.java       # Interfaz gráfica en Java Swing y resaltado amarillo
└── controlador/
    └── ControladorBusqueda.java    # Gestiona eventos de botones y conecta Vista con Modelo
```

## Tecnologías Utilizadas
- **Java** (POO estándar y Java Swing)
- **Maven** (Gestión de dependencias)
- **Apache PDFBox `2.0.29`** (Extracción de texto de archivos PDF)

## Cómo Compilar y Ejecutar
1. Clonar o abrir el proyecto en tu IDE (IntelliJ IDEA, Eclipse o NetBeans) como proyecto Maven.
2. Descargar las dependencias ejecutando:
   ```bash
   mvn clean compile
   ```
3. Ejecutar la clase principal `co.edu.unbosque.FuerzaBruta.Main`.
