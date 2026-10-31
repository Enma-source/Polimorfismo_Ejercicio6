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
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 218, 228)),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)));
        panel.setPreferredSize(new Dimension(310, 210));
        JLabel encabezado = new JLabel(titulo + "  ·  " + consumoMemoriaKB + " KB");
        encabezado.setFont(encabezado.getFont().deriveFont(Font.BOLD, 14f));
        panel.add(encabezado, BorderLayout.NORTH);
        return panel;
    }
}
