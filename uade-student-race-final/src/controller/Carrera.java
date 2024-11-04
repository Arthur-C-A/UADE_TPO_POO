package src.controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Carrera {
    private String nombreCarrera;
    private int duracionCarrera;
    private int aplazosMaximos;

    protected final String[] materiasGenerales = {
            "Introducción a la Algoritmia",
            "Sistemas de Información I",
            "Arquitectura de Computadores",
            "Diseño y Desarrollo Web",
            "Algoritmos y Estructuras de Datos I",
            "Sistemas de Información II",
            "Sistemas Operativos",
            "Redes de Datos",
            "Paradigma Orientado a Objetos",
            "Ingeniería de Datos I",
            "Aplicaciones Interactivas",
            "Proceso de Desarrollo de Software",
            "Desarrollo de Aplicaciones I",
            "Probabilidad y Estadística",
            "Seguridad e Integridad de la Información",
            "Ingeniería de Datos II",
            "Desarrollo de Aplicaciones II",
            "Ciencia de Datos",
            "Evaluación de Proyectos de Tecnología",
            "Tendencias Tecnológicas",
            "Derecho Informático"
    };

    public Carrera(String nombreCarrera, int duracionCarrera) {
        this.nombreCarrera = nombreCarrera;
        this.duracionCarrera = duracionCarrera;
        this.aplazosMaximos = 3;
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
