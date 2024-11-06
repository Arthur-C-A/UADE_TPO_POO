package controller;

import java.util.ArrayList;
import java.util.List;

public class Licenciatura extends Carrera {
    private final String[] materiasPropiasLicenciatura = {
            "Marketing",
            "Matemática_Dis",
            "Testing",
            "Fundamentos_Economía",
            "Gestión de Personas_Tec.",
            "Diseño_Análisis_Algoritmos",
            "Dirección_Proyectos_Tec.",
            "Liderazgo_Negociación",
            "Examen_Inglés",
            "Seminario_Gestión_Tec.",
            "Negocios_Tecnológicos"
    };

    public Licenciatura() {
        super("Licenciatura en Gestión IT", 4,7);
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
        return materiasPropiasLicenciatura;
    }
}
