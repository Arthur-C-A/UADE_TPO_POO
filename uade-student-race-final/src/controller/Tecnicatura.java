package src.controller;

public class Tecnicatura extends Carrera {
    private final String[] materiasPropiasTecnicatura = {
            "Testing de Aplicaciones",
            "Optativa 1 (Marketing Digital)",
            "Trabajo Integrador Final",
            "Optativa 2 (Gestión de Proyectos)"
    };

    public Tecnicatura() {
        super("Tecnicatura en Desarrollo de Software", 3);
    }

    @Override
    public String[] getMateriasPropias() {
        return materiasPropiasTecnicatura;
    }
}
