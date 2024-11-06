package controller;

import java.util.ArrayList;
import java.util.List;

public class Licenciatura extends Carrera {
    private final String[] materiasPropiasLicenciatura = {
            "Marketing",
            "Matemática_Dis",
            "Testing",
            "Fund_Economía",
            "Gestión_Per_Tec.",
            "Dis_Análisis_Algorit",
            "Dirección_Proy_Tec.",
            "Liderazgo_Negociación",
            "Examen_Inglés",
            "Seminario_Gest_Tec.",
            "Negocios_Tec"
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
