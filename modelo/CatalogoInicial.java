package modelo;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


public final class CatalogoInicial {
    private CatalogoInicial() {
        throw new AssertionError("Clase de utilidad: no se instancia.");
    }

    public static List<Renderizable> crearComponentes() {
        List<Renderizable> componentes = new ArrayList<>();
        componentes.add(new ComponenteClima(1, "Clima Guatemala", 120,
                "Ciudad de Guatemala", 22.0));
        componentes.add(new ComponenteClima(2, "Clima Quetzaltenango", 110,
                "Quetzaltenango", 16.0));

        ComponenteTareas estudio = new ComponenteTareas(3, "Tareas de estudio", 180);
        estudio.agregarTarea("Revisar segregación de interfaces");
        estudio.agregarTarea("Completar el diagrama UML");
        componentes.add(estudio);
        ComponenteTareas proyecto = new ComponenteTareas(4, "Tareas del proyecto", 160);
        proyecto.agregarTarea("Probar la búsqueda por ID");
        proyecto.agregarTarea("Preparar la entrega");
        componentes.add(proyecto);

        componentes.add(new ComponenteAlerta(5, "Alerta de respaldo", 80,
                "Realizar el respaldo del proyecto.", PrioridadAlerta.ALTA));
        componentes.add(new ComponenteAlerta(6, "Alerta de reunión", 70,
                "Revisar la agenda de trabajo.", PrioridadAlerta.MEDIA));
        componentes.add(new ComponenteReloj(7, "Reloj Guatemala", 50,
                ZoneId.of("America/Guatemala")));
        componentes.add(new ComponenteReloj(8, "Reloj UTC", 50, ZoneId.of("UTC")));
        componentes.add(new ComponenteFrase(9, "Frase de aprendizaje", 30,
                "Cada práctica mejora tu forma de resolver problemas.", "Anónimo"));
        componentes.add(new ComponenteFrase(10, "Frase de constancia", 25,
                "Avanza un paso y vuelve a intentarlo.", "Anónimo"));
        return componentes;
    }
}
