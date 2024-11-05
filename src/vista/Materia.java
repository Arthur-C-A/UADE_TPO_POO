package vista;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

public class Materia {
    private String nombre;
    private int posicionX;
    private int posicionY;
    private int ancho;
    private int alto;
    private int velocidad;
    private Color color;
    private static final int VELOCIDAD_MAXIMA = 10;

    public Materia(String nombre, int posicionX, int posicionY, int velocidad) {
        this.nombre = nombre;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.ancho = 100;
        this.alto = 30;
        this.velocidad = Math.min(velocidad, VELOCIDAD_MAXIMA);
        this.color = new Color((int)(Math.random() * 0x1000000));
    }

    public void mover() {
        posicionX -= velocidad;
    }

    public void aumentarVelocidad() {
        this.velocidad = Math.min(this.velocidad + 1, VELOCIDAD_MAXIMA);
    }

    public void dibujar(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(posicionX, posicionY, ancho, alto);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString(nombre, posicionX + 5, posicionY + 20);
    }

    public boolean fueraDePantalla() {
        return posicionX + ancho < 0;
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

    public int getVelocidad() {
        return velocidad;
    }
}