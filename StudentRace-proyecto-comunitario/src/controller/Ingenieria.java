package controller;

import java.util.ArrayList;
import java.util.List;

public class Ingenieria extends Carrera {
    private final String[] materiasPropiasIngenieria = {
            "Fundamentos_Informática",
            "Pensamiento_Crítico_Com.",
            "Teoría_Sistemas",
            "Element_Álgebra_Geometría",
            "Fundamentos_Química",
            "Sistemas_Representación",
            "Matemática_Dis",
            "Álgebra",
            "Física I",
            "Cálculo I",
            "Fundamentos_Telecom.",
            "Cálculo II",
            "Teleinformática_Redes",
            "Física II",
            "Teoría_Computación",
            "Modelado_Simulación",
            "Inteligencia_Artificial",
            "Tecnología_Medio Ambiente",
            "Optativa 1 (Diseño de Moda)",
            "Arquitectura_App",
            "Calidad_Software",
            "Optativa 2 (Estilismo de Moda)",
            "Negocios Internacionales",
            "Optativa 3 (Historia de la Moda)",
            "Optativa 4 (Fotografía de Moda)"
    };

    public Ingenieria() {
        super("Ingeniería en Informática", 5,10);
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
        return  materiasPropiasIngenieria;
    }

}
