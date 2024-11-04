package src.controller;

import java.util.ArrayList;

public class Estudiante {
    private Carrera carrera;
    private ArrayList<String> materiasAprobadas;
    private int cantidadAplazos;
    private int anio;
    private int velocidadAlumno;
    private int posicionX;
    private int posicionY;

    public Estudiante(Carrera carrera) {
        this.carrera = carrera;
        this.materiasAprobadas = new ArrayList<>();
        this.cantidadAplazos = 0;
        this.anio = 0;
        this.posicionX = 0;
        this.posicionY = 0;
    }

    public void moverJugador(int posX, int posY) {
        this.posicionX = posX;
        this.posicionY = posY;
    }

    public void agregarMateriaAprobada(String materia) {
        if (!materiasAprobadas.contains(materia)) {
            this.materiasAprobadas.add(materia);
        }
    }

    public String getCarrera() {
        return carrera.getNombreCarrera();
    }

    public ArrayList<String> getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public int getCantidadAplazos() {
        return cantidadAplazos;
    }

    public void agregarAplazo() {
        this.cantidadAplazos++;
    }

    public int getCantidadMateriasAprobadas() {
        return materiasAprobadas.size();
    }

    public int getTotalMaterias() {
        return carrera.getMaterias().length;
    }

    public boolean finCarrera() {
        return getCantidadMateriasAprobadas() + cantidadAplazos >= getTotalMaterias();
    }

    public void aumentarAnio() {
        this.anio++;
    }

    public int getAnio() {
        return anio;
    }
}
