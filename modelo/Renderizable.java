package modelo;

import javax.swing.JComponent;

/** Contrato común de todos los componentes del panel. */
public interface Renderizable extends Comparable<Renderizable> {
    int obtenerId();
    String obtenerTitulo();
    int obtenerConsumoMemoriaKB();
    String obtenerContenido();
    String obtenerConfiguracion();
    JComponent obtenerVista();
}
