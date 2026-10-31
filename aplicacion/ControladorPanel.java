package aplicacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.swing.JComponent;
import modelo.PanelControl;
import modelo.Renderizable;

public class ControladorPanel {
    private final PanelControl modelo;
    private final VistaPanel vista;

    public ControladorPanel(PanelControl modelo, VistaPanel vista) {
        this.modelo = Objects.requireNonNull(modelo);
        this.vista = Objects.requireNonNull(vista);
        vista.alListar(evento -> listarTodos());
        vista.alOrdenar(evento -> ordenarPorMemoria());
        vista.alBuscar(evento -> buscar());
    }

    public void iniciar() {
        listarTodos();
        vista.setVisible(true);
    }

    private void mostrar(List<Renderizable> componentes) {
        List<JComponent> vistas = new ArrayList<>();
        for (Renderizable componente : componentes) {
            vistas.add(componente.obtenerVista());
        }
        vista.mostrarComponentes(vistas);
    }

    private void listarTodos() {
        mostrar(modelo.obtenerComponentes());
    }

    private void ordenarPorMemoria() {
        modelo.ordenarPorMemoria();
        listarTodos();
    }

    private void buscar() {
        String criterio = vista.obtenerCriterioBusqueda();
        if (criterio.isEmpty()) {
            vista.mostrarMensaje("Escribe un ID o un nombre.");
            return;
        }
        List<Renderizable> resultados;
        if (vista.buscarPorIdSeleccionado()) {
            final int id;
            try {
                id = Integer.parseInt(criterio);
            } catch (NumberFormatException excepcion) {
                vista.mostrarMensaje("El ID debe ser un número entero positivo.");
                return;
            }
            if (id <= 0) {
                vista.mostrarMensaje("El ID debe ser un número entero positivo.");
                return;
            }
            Renderizable encontrado = modelo.buscarPorId(id);
            resultados = encontrado == null ? Collections.emptyList()
                    : Collections.singletonList(encontrado);
        } else {
            resultados = modelo.buscarPorNombre(criterio);
        }
        if (resultados.isEmpty()) {
            vista.mostrarMensaje("No se encontraron componentes.");
            return;
        }
        mostrar(resultados);
        StringBuilder configuracion = new StringBuilder();
        for (Renderizable componente : resultados) {
            if (configuracion.length() > 0) { configuracion.append("\n\n"); }
            configuracion.append(componente.obtenerConfiguracion());
        }
        vista.mostrarConfiguracion(configuracion.toString());
    }
}
