package modelo;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ComponenteTareas extends ComponenteBase implements Interactivo {
    private final List<Tarea> tareas = new ArrayList<>();
    private JPanel panel;
    private JTextArea area;
    private JButton boton;

    public ComponenteTareas(int id, String titulo, int memoriaKB) {
        super(id, titulo, memoriaKB);
    }

    public void agregarTarea(String descripcion) {
        tareas.add(new Tarea(descripcion));
        actualizarVista();
    }

    public boolean tienePendientes() {
        for (Tarea tarea : tareas) {
            if (!tarea.estaCompletada()) { return true; }
        }
        return false;
    }

    @Override
    public String obtenerContenido() {
        if (tareas.isEmpty()) { return "No hay tareas registradas."; }
        StringBuilder contenido = new StringBuilder();
        for (Tarea tarea : tareas) {
            contenido.append(tarea.estaCompletada() ? "[X] " : "[ ] ")
                    .append(tarea.obtenerDescripcion()).append("\n");
        }
        return contenido.toString().trim();
    }

    @Override
    public void interactuar() {
        for (Tarea tarea : tareas) {
            if (!tarea.estaCompletada()) {
                tarea.completar();
                break;
            }
        }
        actualizarVista();
    }

    private void actualizarVista() {
        if (area != null) {
            area.setText(obtenerContenido());
            boton.setEnabled(tienePendientes());
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
            boton = EstiloVisual.boton("Completar siguiente tarea", java.awt.Color.WHITE);
            boton.addActionListener(evento -> interactuar());
            EstiloVisual.prepararTexto(area, panel.getBackground());
            panel.add(EstiloVisual.desplazar(area), BorderLayout.CENTER);
            panel.add(boton, BorderLayout.SOUTH);
            actualizarVista();
        }
        return panel;
    }
}
