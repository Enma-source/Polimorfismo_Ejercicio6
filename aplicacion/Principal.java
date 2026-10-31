package aplicacion;

import javax.swing.SwingUtilities;
import modelo.PanelControl;


public class Principal {
    public static void main(String[] argumentos) {
        SwingUtilities.invokeLater(() -> {
            modelo.EstiloVisual.configurar();
            PanelControl modelo = new PanelControl();
            modelo.cargarInicial();
            VistaPanel vista = new VistaPanel();
            ControladorPanel controlador = new ControladorPanel(modelo, vista);
            controlador.iniciar();
        });
    }
}
