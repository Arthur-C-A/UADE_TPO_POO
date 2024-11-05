package src.controller;

import java.util.ArrayList;
import java.util.List;

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
    public String[] getMaterias() {
        List<String> todasLasMaterias = new ArrayList<>();
        for (String materia : materiasGenerales) {
            todasLasMaterias.add(materia);
        }
        for (String materia : getMateriasPropias()) {
            todasLasMaterias.add(materia);
        }
        return todasLasMaterias.toArray(new String[0]);
    }

    public String[] getMateriasPropias() {
        return materiasPropiasTecnicatura;
    }
}