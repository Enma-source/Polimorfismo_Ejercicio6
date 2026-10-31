package modelo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.HierarchyEvent;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class ComponenteReloj extends ComponenteBase {
    private final ZoneId zonaHoraria;
    private final DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
    private JPanel panel;
    private JLabel etiqueta;
    private Timer temporizador;

    public ComponenteReloj(int id, String titulo, int memoriaKB, ZoneId zonaHoraria) {
        super(id, titulo, memoriaKB);
        this.zonaHoraria = Objects.requireNonNull(zonaHoraria, "Zona obligatoria.");
    }

    @Override
    public String obtenerContenido() {
        return LocalTime.now(zonaHoraria).format(formato) + "  " + zonaHoraria;
    }

    private void actualizarHora() {
        etiqueta.setText(LocalTime.now(zonaHoraria).format(formato));
    }

    @Override
    public JComponent obtenerVista() {
        if (panel == null) {
            panel = crearPanel();
            etiqueta = new JLabel("", SwingConstants.CENTER);
            etiqueta.setFont(etiqueta.getFont().deriveFont(Font.PLAIN, 38f));
            etiqueta.setForeground(EstiloVisual.TEXTO);
            actualizarHora();
            temporizador = new Timer(1000, evento -> actualizarHora());
            
            panel.addHierarchyListener(evento -> {
                if ((evento.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                    if (panel.isShowing()) {
                        actualizarHora();
                        temporizador.start();
                    } else {
                        temporizador.stop();
                    }
                }
            });
            panel.add(etiqueta, BorderLayout.CENTER);
            panel.add(new JLabel(zonaHoraria.toString(), SwingConstants.CENTER),
                    BorderLayout.SOUTH);
        }
        return panel;
    }
}
