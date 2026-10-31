package aplicacion;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import modelo.PanelControl;
import modelo.Renderizable;

/** Primer commit: demostración gráfica de las interfaces y del modelo. */
public class Principal {
    public static void main(String[] argumentos) {
        SwingUtilities.invokeLater(Principal::mostrarDemostracion);
    }

    private static void mostrarDemostracion() {
        PanelControl modelo = new PanelControl();
        modelo.cargarInicial();
        JPanel cuadricula = new JPanel(new GridLayout(0, 2, 12, 12));
        cuadricula.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        for (Renderizable componente : modelo.obtenerComponentes()) {
            cuadricula.add(componente.obtenerVista());
        }
        JFrame ventana = new JFrame("Panel de control · Primer commit");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(new JScrollPane(cuadricula));
        ventana.setSize(760, 740);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
