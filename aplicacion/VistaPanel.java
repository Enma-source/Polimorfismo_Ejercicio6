package aplicacion;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class VistaPanel extends JFrame {
    private final ContenidoPanel contenido = new ContenidoPanel();

    public VistaPanel() {
        super("Mi espacio · Panel de control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contenido);
        setMinimumSize(new Dimension(980, 600));
        setSize(1220, 860);
        setLocationRelativeTo(null);
    }
    public String obtenerCriterioBusqueda() { return contenido.obtenerCriterioBusqueda(); }
    public boolean buscarPorIdSeleccionado() { return contenido.buscarPorIdSeleccionado(); }
    public void alListar(ActionListener accion) { contenido.alListar(accion); }
    public void alOrdenar(ActionListener accion) { contenido.alOrdenar(accion); }
    public void alBuscar(ActionListener accion) { contenido.alBuscar(accion); }
    public void mostrarComponentes(List<JComponent> vistas) { contenido.mostrarComponentes(vistas); }
    public void mostrarConfiguracion(String texto) { contenido.mostrarConfiguracion(texto); }
    public void mostrarMensaje(String mensaje) { contenido.mostrarMensaje(mensaje); }
}
