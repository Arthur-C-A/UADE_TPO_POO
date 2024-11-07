package vista;

import controller.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class RenderizarJuego extends JPanel implements ActionListener, KeyListener {
    private Estudiante estudiante;
    private ArrayList<Materia> materias;
    private Timer timer;
    private int nivelActual;
    private int panelWidth = 800;
    private int panelHeight = 600;
    private boolean juegoTerminado;
    private int velocidadBase = 7;
    private Clip musicClip;
    private Random rand;
    private int tiempoHastaProximaMateria;
    private static final int INTERVALO_MATERIA_MIN = 250; // 1/4 de seg
    private static final int INTERVALO_MATERIA_MAX = 1000; // 1 seg
    private Image fondoImagen;

    public RenderizarJuego() {
        fondoImagen = new ImageIcon("src/resources/uade.jpg").getImage();

        // Crear el panel para la entrada de datos
        JPanel panelEntrada = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Dibujar la imagen para cubrir todo el panel
                g2d.drawImage(fondoImagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        // Establecer el diseño a GridBagLayout y configurar dimensiones preferidas
        panelEntrada.setLayout(new GridBagLayout());
        panelEntrada.setPreferredSize(new Dimension(500, 300));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes alrededor de cada componente
        gbc.fill = GridBagConstraints.HORIZONTAL;

// Etiqueta de nombre
        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0
        panelEntrada.add(labelNombre, gbc);

// Campo de texto para nombre
        JTextField campoNombre = new JTextField();
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1; // Columna 1
        gbc.gridy = 0; // Fila 0
        gbc.weightx = 1.0; // Permitir que el campo nombre crezca
        panelEntrada.add(campoNombre, gbc);

// Etiqueta de carrera
        JLabel labelCarrera = new JLabel("Carrera:");
        labelCarrera.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; // Columna 0
        gbc.gridy = 1; // Fila 1
        panelEntrada.add(labelCarrera, gbc);

// ComboBox para seleccionar carrera
        String[] carreras = {"Ingeniería", "Licenciatura", "Tecnicatura"};
        JComboBox<String> comboCarrera = new JComboBox<>(carreras);
        comboCarrera.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1; // Columna 1
        gbc.gridy = 1; // Fila 1
        panelEntrada.add(comboCarrera, gbc);

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

            // Crear la carrera según la selección del usuario
            Carrera carrera;
            switch (carreraSeleccionada) {
                case "Ingeniería":
                    carrera = new Ingenieria();
                    break;
                case "Licenciatura":
                    carrera = new Licenciatura();
                    break;
                case "Tecnicatura":
                    carrera = new Tecnicatura();
                    break;
                default:
                    carrera = new Ingenieria(); // Predeterminado
            }
            estudiante = new Estudiante(carrera, nombreEstudiante);
        } else {
            System.exit(0);
        }


        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        //estudiante = new Estudiante(new Ingenieria()); // Puedes cambiar a Licenciatura o Tecnicatura
        materias = new ArrayList<>();
        nivelActual = estudiante.getAnio();
        juegoTerminado = false;
        rand = new Random();

        iniciarNivel();
        reproducirMusica();

        timer = new Timer(20, this);
        timer.start();
    }

    private void reproducirMusica() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Neia\\IdeaProjects\\uade-student-race\\src\\resources\\bg_music.wav"));
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            musicClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error al reproducir música: " + e.getMessage());
        }
    }

    private void iniciarNivel() {
        estudiante.moverJugador(panelWidth / 2, panelHeight - 50);
        materias.clear();
        tiempoHastaProximaMateria = generarTiempoAparicion();
    }

    private int generarTiempoAparicion() {
        return rand.nextInt(INTERVALO_MATERIA_MAX - INTERVALO_MATERIA_MIN + 1) + INTERVALO_MATERIA_MIN;
    }

    private void generarMateria() {
        String[] materiasDisponibles = estudiante.getMaterias();
        String nombreMateria = materiasDisponibles[rand.nextInt(materiasDisponibles.length)];
        int y = rand.nextInt(panelHeight - 150) + 75;
        int velocidad = velocidadBase * (nivelActual * 2);
        materias.add(new Materia(nombreMateria, panelWidth, y, velocidad));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

    // Dibujar META
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString("META", panelWidth / 2 - 30, 30);

        // Dibujar estudiante
        estudiante.renderizarMonoChino(g);

        // Dibujar materias
        for (Materia materia : materias) {
            materia.dibujar(g2d);
        }

        // Información visible
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
        g2d.drawString("Carrera: " + estudiante.getCarreraNombre(), 10, 20);
        g2d.drawString("Año: " + estudiante.getAnio(), 10, 40);
        g2d.drawString("Aplazos: " + estudiante.getCantidadAplazos(), 10, 60);



        if (juegoTerminado) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            if (estudiante.getCantidadAplazos() >= estudiante.getCarrera().getAplazosMaximos()) {
                g2d.drawString("¡Juego Terminado! Aplazos Excedidos", panelWidth / 2 - 200, panelHeight / 2);
            } else {
                g2d.drawString("¡Felicitaciones " + estudiante.getNombre() + "! Graduado en " + estudiante.getCarrera().getNombreCarrera() + "!", panelWidth / 2 - 150, panelHeight / 2);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!juegoTerminado) {
            moverMaterias();
            verificarColisiones();
            verificarNivelCompletado();
            manejarAparicionMaterias();
        }
        repaint();
    }

    private void manejarAparicionMaterias() {
        tiempoHastaProximaMateria -= timer.getDelay();
        if (tiempoHastaProximaMateria <= 0) {
            generarMateria();
            tiempoHastaProximaMateria = generarTiempoAparicion();
        }
    }

    private void moverMaterias() {
        Iterator<Materia> iterator = materias.iterator();
        while (iterator.hasNext()) {
            Materia materia = iterator.next();
            materia.mover();
            if (materia.fueraDePantalla()) {
                iterator.remove();
            }
        }
    }

    private void verificarColisiones() {
        Rectangle estudianteRect = new Rectangle(estudiante.getPosicionX(), estudiante.getPosicionY(), 30, 30);
        Iterator<Materia> iterator = materias.iterator();
        while (iterator.hasNext()) {
            Materia materia = iterator.next();
            Rectangle materiaRect = new Rectangle(materia.getPosicionX(), materia.getPosicionY(), materia.getAncho(), materia.getAlto());
            if (estudianteRect.intersects(materiaRect)) {
                estudiante.agregarAplazo();
                iterator.remove();
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
            estudiante.avanzarAnio();
            if (nivelActual > estudiante.getCarrera().getDuracionCarrera()) {
                if (estudiante.getCantidadAplazos() < estudiante.getCarrera().getAplazosMaximos()) {
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
}