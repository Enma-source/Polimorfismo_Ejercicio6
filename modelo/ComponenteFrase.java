package modelo;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ComponenteFrase extends ComponenteBase {
    private final String cita;
    private final String autor;
    private JPanel panel;

    public ComponenteFrase(int id, String titulo, int memoriaKB,
            String cita, String autor) {
        super(id, titulo, memoriaKB);
        this.cita = validarTexto(cita);
        this.autor = validarTexto(autor);
    }

    @Override
    public String obtenerContenido() {
        return "\"" + cita + "\"\nAutor: " + autor;
    }

    @Override
    public JComponent obtenerVista() {
        if (panel == null) {
            panel = crearPanel();
            JTextArea area = new JTextArea(obtenerContenido());
            area.setEditable(false);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            panel.add(new JScrollPane(area), BorderLayout.CENTER);
        }
        return panel;
    }
}
