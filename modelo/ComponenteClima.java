package modelo;

import java.awt.BorderLayout;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComponenteClima extends ComponenteBase implements Interactivo {
    private final String ciudad;
    private final double temperaturaCelsius;
    private boolean mostrarCelsius = true;
    private JPanel panel;
    private JLabel etiqueta;

    public ComponenteClima(int id, String titulo, int memoriaKB,
            String ciudad, double temperaturaCelsius) {
        super(id, titulo, memoriaKB);
        this.ciudad = validarTexto(ciudad);
        if (!Double.isFinite(temperaturaCelsius) || temperaturaCelsius < -273.15) {
            throw new IllegalArgumentException("Temperatura inválida.");
        }
        this.temperaturaCelsius = temperaturaCelsius;
    }

    @Override
    public String obtenerContenido() {
        double temperatura = mostrarCelsius ? temperaturaCelsius
                : temperaturaCelsius * 9.0 / 5.0 + 32;
        return String.format(Locale.ROOT, "%s: %.1f %s", ciudad, temperatura,
                mostrarCelsius ? "°C" : "°F");
    }

    @Override
    public void interactuar() {
        mostrarCelsius = !mostrarCelsius;
        if (etiqueta != null) { etiqueta.setText(obtenerContenido()); }
    }

    @Override
    public JComponent obtenerVista() {
        if (panel == null) {
            panel = crearPanel();
            etiqueta = new JLabel(obtenerContenido());
            JButton boton = new JButton("Cambiar °C / °F");
            boton.addActionListener(evento -> interactuar());
            panel.add(etiqueta, BorderLayout.CENTER);
            panel.add(boton, BorderLayout.SOUTH);
        }
        return panel;
    }
}
