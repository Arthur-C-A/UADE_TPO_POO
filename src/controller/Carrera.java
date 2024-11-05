package controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Carrera {
    private String nombreCarrera;
    private int duracionCarrera;
    private int aplazosMaximos;

    protected final String[] materiasGenerales = {
            "Introd_Algoritmia",
            "Sistemas_Información_I",
            "Arq_Computadores",
            "Diseño_Desarrollo Web",
            "Algoritmos_Est_Datos I",
            "Sistemas_Información II",
            "Sistemas Operativos",
            "Redes_Datos",
            "POO",
            "Ingeniería_Datos I",
            "App_Interactivas",
            "Proceso_Desarrollo_Software",
            "Desarrollo_Aplicaciones I",
            "Probabilidad_Estadística",
            "Seg_Integridad_Información",
            "Ingeniería_Datos II",
            "Desarrollo_App II",
            "Ciencia_Datos",
            "Eval_Proyectos_Tecnología",
            "Tendencias Tecnológicas",
            "Derecho_Informático"
    };

    public Carrera(String nombreCarrera, int duracionCarrera, int aplazoMaximo) {
        this.nombreCarrera = nombreCarrera;
        this.duracionCarrera = duracionCarrera;
        this.aplazosMaximos = aplazoMaximo;
    }

    public abstract String[] getMaterias();

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public int getDuracionCarrera() {
        return duracionCarrera;
    }

    public int getAplazosMaximos() {
        return aplazosMaximos;
    }
}
