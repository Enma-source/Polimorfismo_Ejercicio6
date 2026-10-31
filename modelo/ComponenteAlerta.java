package modelo;

import java.awt.BorderLayout;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ComponenteAlerta extends ComponenteBase implements Interactivo {
    private final String mensaje;
    private final PrioridadAlerta prioridad;
    private boolean leida;
    private JPanel panel;
    private JTextArea area;
    private JButton boton;

    public ComponenteAlerta(int id, String titulo, int memoriaKB,
            String mensaje, PrioridadAlerta prioridad) {
        super(id, titulo, memoriaKB);
        this.mensaje = validarTexto(mensaje);
        this.prioridad = Objects.requireNonNull(prioridad, "La prioridad es obligatoria.");
    }

    @Override
    public String obtenerContenido() {
        return "Prioridad: " + prioridad + "\n" + mensaje
                + "\nEstado: " + (leida ? "Leída" : "Pendiente de lectura");
    }

    @Override
    public void interactuar() {
        leida = true;
        actualizarVista();
    }

    private void actualizarVista() {
        if (area != null) {
            area.setText(obtenerContenido());
            boton.setEnabled(!leida);
        }
    }

    @Override
    public JComponent obtenerVista() {
        if (panel == null) {
            panel = crearPanel();
            area = new JTextArea();
            area.setEditable(false);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            boton = new JButton("Marcar como leída");
            boton.addActionListener(evento -> interactuar());
            panel.add(new JScrollPane(area), BorderLayout.CENTER);
            panel.add(boton, BorderLayout.SOUTH);
            actualizarVista();
        }
        return panel;
    }
}
