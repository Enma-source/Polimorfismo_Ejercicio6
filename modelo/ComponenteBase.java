package modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ComponenteBase implements Renderizable {
    private final int id;
    private final String titulo;
    private final int consumoMemoriaKB;

    protected ComponenteBase(int id, String titulo, int consumoMemoriaKB) {
        if (id <= 0 || consumoMemoriaKB < 0) {
            throw new IllegalArgumentException("ID positivo y memoria no negativa.");
        }
        this.id = id;
        this.titulo = validarTexto(titulo);
        this.consumoMemoriaKB = consumoMemoriaKB;
    }

    protected static String validarTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El texto no puede estar vacío.");
        }
        return texto.trim();
    }

    @Override
    public final int obtenerId() { return id; }

    @Override
    public final String obtenerTitulo() { return titulo; }

    @Override
    public final int obtenerConsumoMemoriaKB() { return consumoMemoriaKB; }

    @Override
    public final int compareTo(Renderizable otro) {
        int resultado = Integer.compare(consumoMemoriaKB,
                otro.obtenerConsumoMemoriaKB());
        return resultado != 0 ? resultado : Integer.compare(id, otro.obtenerId());
    }

    @Override
    public String obtenerConfiguracion() {
        return "ID: " + id + "\nNombre: " + titulo
                + "\nMemoria estimada: " + consumoMemoriaKB + " KB"
                + "\nInteractivo: " + (this instanceof Interactivo ? "Sí" : "No")
                + "\n" + obtenerContenido();
    }

    protected JPanel crearPanel() {
        Color[] colores = {EstiloVisual.AZUL, EstiloVisual.MENTA,
                EstiloVisual.DURAZNO, EstiloVisual.LILA, EstiloVisual.ROSA};
        Color fondo = colores[((id - 1) / 2) % colores.length];
        JPanel panel = EstiloVisual.tarjeta(fondo, 26);
        panel.setPreferredSize(new Dimension(330, 245));
        JPanel encabezado = new JPanel(new BorderLayout(6, 8));
        encabezado.setOpaque(false);
        encabezado.add(EstiloVisual.etiqueta(titulo, 16, EstiloVisual.TEXTO),
                BorderLayout.NORTH);
        encabezado.add(EstiloVisual.etiqueta("ID " + id + "  ·  "
                + consumoMemoriaKB + " KB", 12, EstiloVisual.SUAVE), BorderLayout.SOUTH);
        panel.add(encabezado, BorderLayout.NORTH);
        return panel;
    }
}
