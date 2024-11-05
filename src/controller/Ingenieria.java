package controller;

import java.util.ArrayList;
import java.util.List;

public class Ingenieria extends Carrera {
    private final String[] materiasPropiasIngenieria = {
            "Fundamentos de Informática",
            "Pensamiento Crítico y Comunicación",
            "Teoría de Sistemas",
            "Elementos de Álgebra y Geometría",
            "Fundamentos de Química",
            "Sistemas de Representación",
            "Matemática Discreta",
            "Álgebra",
            "Física I",
            "Cálculo I",
            "Fundamentos de Telecomunicaciones",
            "Cálculo II",
            "Teleinformática y Redes",
            "Física II",
            "Teoría de la Computación",
            "Modelado y Simulación",
            "Inteligencia Artificial",
            "Tecnología y Medio Ambiente",
            "Optativa 1 (Diseño de Moda)",
            "Arquitectura de Aplicaciones",
            "Calidad de Software",
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