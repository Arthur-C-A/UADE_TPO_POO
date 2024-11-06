package controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Carrera {
    private String nombreCarrera;
    private int duracionCarrera;
    private int aplazosMaximos;

    protected final String[] materiasGenerales = {
            "Introd_Algoritmia",
            "Sist_Información_I",
            "Arq_Computadores",
            "Diseño_Des_Web",
            "Algorit_Est_Datos I",
            "Sist_Información II",
            "Sist_Operativos",
            "Redes_Datos",
            "POO",
            "Ing_Datos I",
            "App_Interactivas",
            "Proceso_Des_Software",
            "Desarrollo_App I",
            "Probabilidad_Estad",
            "Seg_Integridad_Info",
            "Ing_Datos II",
            "Desarrollo_App II",
            "Ciencia_Datos",
            "Eval_Proye_Tec",
            "Tendencias_Tec",
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
