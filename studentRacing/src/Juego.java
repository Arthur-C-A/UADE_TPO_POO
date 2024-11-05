package vista;

import src.controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;


public class Juego extends JPanel implements ActionListener, KeyListener {
    private Estudiante estudiante;
    private ArrayList<Materia> materias;
    private ArrayList<Materia> materiasParaSiguienteNivel;
    private Timer timer;
    private int nivelActual;
    private int panelWidth = 800;
    private int panelHeight = 600;
    private boolean juegoTerminado;
    private boolean nivelCompletado;
    private int velocidadBase = 2;

    private JTextField nombreText;
    private JTextField apellidoText;
    private JComboBox<String> carreraComboBox;

    public Juego() {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);



        // Crear el panel para la entrada de datos
        JPanel panelEntrada = new JPanel(new GridLayout(2, 2, 10, 10));

        // Etiquetas y campos para nombre y carrera
        JLabel labelNombre = new JLabel("Nombre:");
        JTextField campoNombre = new JTextField();
        JLabel labelCarrera = new JLabel("Carrera:");
        String[] carreras = {"Ingeniería", "Licenciatura", "Tecnicatura"};
        JComboBox<String> comboCarrera = new JComboBox<>(carreras);

        // Añadir los componentes al panel
        panelEntrada.add(labelNombre);
        panelEntrada.add(campoNombre);
        panelEntrada.add(labelCarrera);
        panelEntrada.add(comboCarrera);

        // Mostrar el panel en un JOptionPane para obtener los datos
        int resultado = JOptionPane.showConfirmDialog(
                null,
                panelEntrada,
                "Login",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Verificar si el usuario presionó "OK"
        if (resultado == JOptionPane.OK_OPTION) {
            String nombreEstudiante = campoNombre.getText();
            String carreraSeleccionada = (String) comboCarrera.getSelectedItem();

            // Validar que el nombre no esté vacío
            if (nombreEstudiante.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para continuar.");
                System.exit(0); // Salir del juego si no hay nombre
            }
        }





        estudiante = new Estudiante(new Ingenieria()); // Puedes cambiar a Licenciatura o Tecnicatura
        materias = new ArrayList<>();
        materiasParaSiguienteNivel = new ArrayList<>();
        nivelActual = 1;
        juegoTerminado = false;
        nivelCompletado = false;

        iniciarNivel();

        timer = new Timer(20, this);
        timer.start();
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
        g2d.setColor(Color.BLUE);
        g2d.fillRect(estudiante.getPosicionX(), estudiante.getPosicionY(), 30, 30);

        // Dibujar materias
        for (Materia materia : materias) {
            g2d.setColor(materia.getColor());
            g2d.fillRect(materia.getPosicionX(), materia.getPosicionY(), materia.getAncho(), materia.getAlto());
            g2d.setColor(Color.BLACK);
            g2d.drawString(materia.getNombre(), materia.getPosicionX(), materia.getPosicionY() + 15);
        }

        // Dibujar información
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString("Año: " + nivelActual, 10, 20);
        g2d.drawString("Aplazos: " + estudiante.getCantidadAplazos(), 10, 40);

        if (juegoTerminado) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            if (estudiante.getCantidadAplazos() >= estudiante.getCarrera().getAplazosMaximos()) {
                g2d.drawString("¡Juego terminado!", panelWidth / 2 - 150, panelHeight / 2);
            } else {
                g2d.drawString("¡Felicitaciones! ¡Graduado!", panelWidth / 2 - 200, panelHeight / 2);
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
                if (!materiasParaSiguienteNivel.isEmpty()) {
                    nivelActual--;
                    iniciarNivel();
                } else {
                    juegoTerminado = true;
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