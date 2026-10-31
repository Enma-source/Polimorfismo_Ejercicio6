package modelo;

public class Tarea {
    private final String descripcion;
    private boolean completada;

    public Tarea(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La tarea necesita descripción.");
        }
        this.descripcion = descripcion.trim();
    }

    public String obtenerDescripcion() { return descripcion; }
    public boolean estaCompletada() { return completada; }
    public void completar() { completada = true; }
}
