package modelo;

import javax.swing.JComponent;


public interface Renderizable extends Comparable<Renderizable> {
    int obtenerId();
    String obtenerTitulo();
    int obtenerConsumoMemoriaKB();
    String obtenerContenido();
    String obtenerConfiguracion();
    JComponent obtenerVista();
}
