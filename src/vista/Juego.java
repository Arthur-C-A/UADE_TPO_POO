package vista;

import controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Juego extends JPanel implements ActionListener, KeyListener {
    private Estudiante estudiante;
    private ArrayList<Materia> materias;
    private ArrayList<Materia> materiasParaSiguienteNivel;
    private Timer timer;
    private int nivelActual;
    private int panelWidth = 800;
    private int panelHeight = 600;
    private boolean juegoTerminado;
    private int velocidadBase = 2;

    public Juego() {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        estudiante = new Estudiante(new Ingenieria()); // Puedes cambiar a Licenciatura o Tecnicatura
        materias = new ArrayList<>();
        materiasParaSiguienteNivel = new ArrayList<>();
        nivelActual = 1;
        juegoTerminado = false;

        iniciarNivel();
        reproducirMusica();

        timer = new Timer(20, this);
        timer.start();
    }

    private void reproducirMusica() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src/resources/sound/musica.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            System.out.println("Error al reproducir música: " + e.getMessage());
        }
    }

    private void iniciarNivel() {
        estudiante.moverJugador(panelWidth / 2, panelHeight - 50);
        materias.clear();
        materias.addAll(materiasParaSiguienteNivel);
        materiasParaSiguienteNivel.clear();
        generarMaterias();
    }

    private void generarMaterias() {
        Random rand = new Random();
        String[] materiasDisponibles = estudiante.getMaterias();
        HashSet<Integer> posicionesY = new HashSet<>();

        int cantidadMaterias = 5 - materias.size();
        for (int i = 0; i < cantidadMaterias; i++) {
            String nombreMateria = materiasDisponibles[rand.nextInt(materiasDisponibles.length)];
            int y;
            do {
                y = rand.nextInt(panelHeight - 150) + 75;
            } while (!posicionesY.add(y));

            int velocidad = velocidadBase + nivelActual;
            materias.add(new Materia(nombreMateria, panelWidth, y, velocidad));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dibujar META
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("META", panelWidth / 2 - 30, 30);

        // Dibujar estudiante
        estudiante.renderizarMonoChino(g);

        // Dibujar materias
        for (Materia materia : materias) {
            g2d.setColor(materia.getColor());
            g2d.fillRect(materia.getPosicionX(), materia.getPosicionY(), materia.getAncho(), materia.getAlto());
            g2d.setColor(Color.BLACK);
            g2d.drawString(materia.getNombre(), materia.getPosicionX(), materia.getPosicionY() + 15);
        }

        // Información visible
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString("Carrera: " + estudiante.getCarreraNombre(), 10, 20);
        g2d.drawString("Año: " + estudiante.getAnio(), 10, 40);
        g2d.drawString("Aplazos: " + estudiante.getCantidadAplazos(), 10, 60);

        // Materias aprobadas
        g2d.drawString("Materias Aprobadas: " + estudiante.getMateriasAprobadas().toString(), 10, 80);

        if (juegoTerminado) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            if (estudiante.getCantidadAplazos() >= estudiante.getCarrera().getAplazosMaximos()) {
                g2d.drawString("¡Juego Terminado! Aplazos Excedidos", panelWidth / 2 - 200, panelHeight / 2);
            } else {
                g2d.drawString("¡Felicitaciones! ¡Graduado!", panelWidth / 2 - 150, panelHeight / 2);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!juegoTerminado) {
            moverMaterias();
            verificarColisiones();
            verificarNivelCompletado();
        }
        repaint();
    }

    private void moverMaterias() {
        for (Materia materia : materias) {
            materia.mover();
            if (materia.getPosicionX() + materia.getAncho() < 0) {
                materia.setPosicionX(panelWidth);
            }
        }
    }

    private void verificarColisiones() {
        Rectangle estudianteRect = new Rectangle(estudiante.getPosicionX(), estudiante.getPosicionY(), 30, 30);
        for (int i = materias.size() - 1; i >= 0; i--) {
            Materia materia = materias.get(i);
            Rectangle materiaRect = new Rectangle(materia.getPosicionX(), materia.getPosicionY(), materia.getAncho(), materia.getAlto());
            if (estudianteRect.intersects(materiaRect)) {
                estudiante.agregarAplazo();
                materias.remove(i);
                materia.aumentarVelocidad();
                materiasParaSiguienteNivel.add(materia);
                if (estudiante.getCantidadAplazos() >= estudiante.getCarrera().getAplazosMaximos()) {
                    juegoTerminado = true;
                } else {
                    estudiante.moverJugador(panelWidth / 2, panelHeight - 50);
                    estudiante.reducirVelocidad();
                }
                break;
            }
        }
    }

    private void verificarNivelCompletado() {
        if (estudiante.getPosicionY() <= 0) {
            nivelActual++;
            if (nivelActual > estudiante.getCarrera().getDuracionCarrera()) {
                if (estudiante.getCantidadAplazos() < estudiante.getCarrera().getAplazosMaximos()) {
                    juegoTerminado = true; // Estudiante graduado exitosamente
                }
            } else {
                iniciarNivel();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int x = estudiante.getPosicionX();
        int y = estudiante.getPosicionY();
        int velocidad = estudiante.getVelocidadAlumno();

        if (key == KeyEvent.VK_A) x -= velocidad;
        if (key == KeyEvent.VK_D) x += velocidad;
        if (key == KeyEvent.VK_W) y -= velocidad;
        if (key == KeyEvent.VK_S) y += velocidad;

        x = Math.max(0, Math.min(x, panelWidth - 30));
        y = Math.max(0, Math.min(y, panelHeight - 30));

        estudiante.moverJugador(x, y);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Crossy University");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new Juego());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
