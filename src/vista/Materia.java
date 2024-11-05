package vista;

import java.awt.Color;

public class Materia {
    private String nombre;
    private int posicionX;
    private int posicionY;
    private int ancho;
    private int alto;
    private int velocidad;
    private Color color;

    public Materia(String nombre, int posicionX, int posicionY, int velocidad) {
        this.nombre = nombre;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.ancho = 100;
        this.alto = 30;
        this.velocidad = velocidad;
        this.color = new Color((int)(Math.random() * 0x1000000));
    }

    public void mover() {
        posicionX -= velocidad;
    }

    public void aumentarVelocidad() {
        this.velocidad += 1;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public Color getColor() {
        return color;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }
}