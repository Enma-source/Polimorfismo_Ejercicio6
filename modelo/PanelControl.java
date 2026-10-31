package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/** Gestiona una sola lista principal, sin conocer clases concretas. */
public class PanelControl {
    private final List<Renderizable> componentes = new ArrayList<>();

    public void cargarInicial() {
        componentes.clear();
        for (Renderizable componente : CatalogoInicial.crearComponentes()) {
            agregarComponente(componente);
        }
    }

    public void agregarComponente(Renderizable componente) {
        Objects.requireNonNull(componente, "El componente es obligatorio.");
        if (buscarPorId(componente.obtenerId()) != null) {
            throw new IllegalArgumentException("El ID ya existe.");
        }
        componentes.add(componente);
    }

    public List<Renderizable> obtenerComponentes() {
        // Se protege la estructura de la lista; los widgets conservan su identidad.
        return Collections.unmodifiableList(new ArrayList<>(componentes));
    }

    public Renderizable buscarPorId(int id) {
        for (Renderizable componente : componentes) {
            if (componente.obtenerId() == id) { return componente; }
        }
        return null;
    }

    public List<Renderizable> buscarPorNombre(String nombre) {
        String criterio = Objects.requireNonNull(nombre, "Escribe un nombre.")
                .trim().toLowerCase(Locale.ROOT);
        List<Renderizable> resultados = new ArrayList<>();
        for (Renderizable componente : componentes) {
            if (componente.obtenerTitulo().toLowerCase(Locale.ROOT).contains(criterio)) {
                resultados.add(componente);
            }
        }
        return Collections.unmodifiableList(resultados);
    }

    public void ordenarPorMemoria() {
        Collections.sort(componentes);
    }

    public boolean interactuarPorId(int id) {
        Renderizable componente = buscarPorId(id);
        if (componente instanceof Interactivo) {
            ((Interactivo) componente).interactuar();
            return true;
        }
        return false;
    }
}
