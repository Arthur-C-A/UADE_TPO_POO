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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
    private Mapa mapa; // Modificado por Zoilo
    private Image fondoImagen;
    private JButton botonReiniciar;


    public RenderizarJuego() {

        mapa = new Mapa("src/resources/mapa.jpeg");

        fondoImagen = new ImageIcon("src/resources/uade.jpg").getImage();

        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        Login login = new Login();
        estudiante = login.mostrarLogin();

        materias = new ArrayList<>();
        nivelActual = estudiante.getAnio();
        juegoTerminado = false;
        rand = new Random();

        iniciarNivel();
        reproducirMusica();

        timer = new Timer(20, this);
        timer.start();

        setLayout(null);
        botonReiniciar = new JButton("Reiniciar");
        botonReiniciar.setFont(new Font("Tiny5", Font.BOLD, 16));
        botonReiniciar.setBounds(650, 20, 120, 40);  // Ajusta la posición y tamaño como desees
        botonReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });
        add(botonReiniciar);

    }

    private void reiniciarJuego() {
        // Guardar los datos en el historial
        guardarHistorialGraduado();

        // Cerrar la ventana actual
        JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(this);  // Obtiene el JFrame que contiene el panel actual
        if (ventanaActual != null) {
            ventanaActual.dispose();  // Cierra la ventana
        }

        // Crear una nueva instancia de RenderizarJuego
        RenderizarJuego nuevoJuego = new RenderizarJuego();

        // Crear un nuevo JFrame para la nueva instancia del juego
        JFrame nuevaVentana = new JFrame("UADE STUDENT RACE");
        nuevaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nuevaVentana.add(nuevoJuego);  // Agregar el nuevo panel de juego
        nuevaVentana.pack();  // Ajusta el tamaño según el panel
        nuevaVentana.setLocationRelativeTo(null);
        nuevaVentana.setVisible(true);  // Mostrar la nueva ventana
    }


//    private void mostrarLogin() {
//        // Crear el panel para la entrada de datos
//        JPanel panelEntrada = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                Graphics2D g2d = (Graphics2D) g;
//
//                // Dibujar la imagen para cubrir todo el panel
//                g2d.drawImage(fondoImagen, 0, 0, getWidth(), getHeight(), this);
//            }
//        };
//        // Establecer el diseño a GridBagLayout y configurar dimensiones preferidas
//        panelEntrada.setLayout(new GridBagLayout());
//        panelEntrada.setPreferredSize(new Dimension(500, 300));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes alrededor de cada componente
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//
//
//// Etiqueta de nombre
//        JLabel labelNombre = new JLabel("Nombre:");
//        labelNombre.setFont(new Font("Arial", Font.BOLD, 18));
//        gbc.gridx = 0; // Columna 0
//        gbc.gridy = 0; // Fila 0
//        panelEntrada.add(labelNombre, gbc);
//
//// Campo de texto para nombre
//        JTextField campoNombre = new JTextField();
//        campoNombre.setFont(new Font("Arial", Font.PLAIN, 16));
//        gbc.gridx = 1; // Columna 1
//        gbc.gridy = 0; // Fila 0
//        gbc.weightx = 1.0; // Permitir que el campo nombre crezca
//        panelEntrada.add(campoNombre, gbc);
//
//// Etiqueta de carrera
//        JLabel labelCarrera = new JLabel("Carrera:");
//        labelCarrera.setFont(new Font("Arial", Font.BOLD, 18));
//        gbc.gridx = 0; // Columna 0
//        gbc.gridy = 1; // Fila 1
//        panelEntrada.add(labelCarrera, gbc);
//
//// ComboBox para seleccionar carrera
//        String[] carreras = {"Ingeniería", "Licenciatura", "Tecnicatura"};
//        JComboBox<String> comboCarrera = new JComboBox<>(carreras);
//        comboCarrera.setFont(new Font("Arial", Font.PLAIN, 16));
//        gbc.gridx = 1; // Columna 1
//        gbc.gridy = 1; // Fila 1
//        panelEntrada.add(comboCarrera, gbc);
//
//        // Mostrar el panel en un JOptionPane para obtener los datos
//        int resultado = JOptionPane.showConfirmDialog(
//                null,
//                panelEntrada,
//                "Login",
//                JOptionPane.OK_CANCEL_OPTION,
//                JOptionPane.PLAIN_MESSAGE
//        );
//
//        // Verificar si el usuario presionó "OK"
//        if (resultado == JOptionPane.OK_OPTION) {
//            String nombreEstudiante = campoNombre.getText();
//            String carreraSeleccionada = (String) comboCarrera.getSelectedItem();
//
//            // Validar que el nombre no esté vacío
//            if (nombreEstudiante.isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para continuar.");
//                System.exit(0); // Salir del juego si no hay nombre
//            }
//
//            // Crear la carrera según la selección del usuario
//            Carrera carrera;
//            switch (carreraSeleccionada) {
//                case "Ingeniería":
//                    carrera = new Ingenieria();
//                    break;
//                case "Licenciatura":
//                    carrera = new Licenciatura();
//                    break;
//                case "Tecnicatura":
//                    carrera = new Tecnicatura();
//                    break;
//                default:
//                    carrera = new Ingenieria(); // Predeterminado
//            }
//            estudiante = new Estudiante(carrera, nombreEstudiante);
//        } else {
//            System.exit(0);
//        }
//
//
//    }
//


    private void reproducirMusica() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src/resources/pokachi.wav"));
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

        // Modificado por Zoilo: Dibujar el mapa
        mapa.dibujar(g);

        // Dibujar estudiante
        estudiante.renderizarMonoChino(g);

        // Dibujar materias
        for (Materia materia : materias) {
            materia.dibujar(g2d);
        }


        // Información visible
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Tiny5", Font.PLAIN, 19));
        g2d.drawString("Carrera: " + estudiante.getCarreraNombre(), 10, 20);
        g2d.drawString("Año: " + estudiante.getAnio(), 10, 40);
        g2d.drawString("Aplazos: " + estudiante.getCantidadAplazos(), 10, 60);

        if (juegoTerminado) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            if (estudiante.getCantidadAplazos() >= estudiante.getCarrera().getAplazosMaximos()) {
                String mensaje = "¡Juego Terminado! Aplazos Excedidos";
                FontMetrics fm = g2d.getFontMetrics();
                int x = (panelWidth - fm.stringWidth(mensaje)) / 2; // Calcula la posición X para centrar
                int y = panelHeight / 2;
                g2d.drawString(mensaje, x, y);
            } else {
                String mensaje = "¡Felicitaciones! ¡Graduado!";
                FontMetrics fm = g2d.getFontMetrics();
                int x = (panelWidth - fm.stringWidth(mensaje)) / 2; // Calcula la posición X para centrar
                int y = panelHeight / 2;
                g2d.drawString(mensaje, x, y);
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

        // Creado por Zoilo: Verificar colisiones con el mapa
        if (mapa.colisiona(estudianteRect)) {
            // Revertir movimiento
            estudiante.moverJugador(panelWidth / 2, panelHeight - 50);
        }

        // Verificar si el estudiante ha llegado a la puerta de nivel
        if (mapa.esPuertaNivel(estudianteRect)) {
            nivelActual++;
            estudiante.avanzarAnio();
            if (nivelActual > estudiante.getCarrera().getDuracionCarrera()) {
                if (estudiante.getCantidadAplazos() < estudiante.getCarrera().getAplazosMaximos()) {
                    juegoTerminado = true;
                    guardarHistorialGraduado();
                }
            } else {
                iniciarNivel();
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
                    guardarHistorialGraduado();
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

    private void guardarHistorialGraduado() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("historial_graduados.txt", true)))) {
            writer.println("Nombre: " + estudiante.getNombre());
            writer.println("Carrera: " + estudiante.getCarreraNombre());
            writer.println("Aplazos: " + estudiante.getCantidadAplazos());
            writer.println("Duración de la carrera: " + estudiante.getCarrera().getDuracionCarrera() + " años");
            writer.println("--------------------------");
        } catch (IOException e) {
            System.out.println("Error al guardar historial de graduado: " + e.getMessage());
        }
    }
}
