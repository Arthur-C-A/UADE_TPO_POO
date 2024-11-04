package src.controller;

import java.util.ArrayList;
import java.util.List;

public class Licenciatura extends Carrera {
    private final String[] materiasPropiasLicenciatura = {
            "Marketing",
            "Matemática Discreta",
            "Testing de Aplicaciones",
            "Fundamentos de Economía",
            "Gestión de Personas en Organizaciones de Tecnología",
            "Diseño y Análisis de Algoritmos",
            "Dirección de Proyectos de Tecnología",
            "Liderazgo y Negociación",
            "Examen de Inglés",
            "Seminario de Gestión de Tecnología",
            "Negocios Tecnológicos"
    };

    public Licenciatura() {
        super("Licenciatura en Gestión IT", 4);
    }

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
        return materiasPropiasLicenciatura;
    }
}
