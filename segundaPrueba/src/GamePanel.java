import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel {
    private int posRojoX = 3; // Posición inicial del cuadrado rojo en la columna 0
    private int posRojoY = 7; // Fila del cuadrado rojo
    private JPanel[][] casillas; // Matriz de paneles
    private Timer[] blueTimers; // Timers para mover los cuadrados azules
    private int[] blueXPositions; // Posiciones de los cuadrados azules
    private static final int FILAS = 8; // Total de filas
    private static final int COLUMNAS = 6; // Total de columnas
    private Random random;

    public GamePanel() {
        setLayout(new GridLayout(FILAS, COLUMNAS));
        casillas = new JPanel[FILAS][COLUMNAS];

        // Crear las casillas del tablero
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                casillas[i][j] = new JPanel();
                casillas[i][j].setPreferredSize(new Dimension(80, 80)); // Tamaño de las casillas
                casillas[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(casillas[i][j]);
            }
        }

        // Pintar el cuadrado inicial (rojo)
        casillas[posRojoY][posRojoX].setBackground(Color.RED); // Posición inicial del cuadrado rojo

        // Inicializar y mover los cuadrados azules
        blueTimers = new Timer[FILAS - 1]; // Reducir tamaño en uno, ya que no puede haber azul en la fila 7
        blueXPositions = new int[FILAS - 1]; // Ajustar el tamaño del arreglo
        random = new Random();

        // Configurar los cuadrados azules en filas aleatorias
        for (int i = 0; i < FILAS - 1; i++) {
            int fila;
            do {
                fila = random.nextInt(FILAS); // Seleccionar fila aleatoria (0-7)
            } while (fila == posRojoY); // Asegurarse de que no esté en la fila del cuadrado rojo

            // Inicializar la posición del cuadrado azul
            blueXPositions[i] = random.nextInt(COLUMNAS); // Posición inicial aleatoria del cuadrado azul

            iniciarMovimientoAzul(fila, i); // Iniciar el movimiento del cuadrado azul
        }

        // Añadir Listener para mover el cuadrado rojo
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moverRojoDerecha();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moverRojoIzquierda();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    moverRojoArriba();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    moverRojoAbajo();
                }
            }
        });
    }

    private void iniciarMovimientoAzul(int fila, int index) {
        int delay = random.nextInt(800) + 200; // Tiempo de movimiento aleatorio entre 200ms y 1000ms

        blueTimers[index] = new Timer(delay, new ActionListener() { // Mover a intervalos aleatorios
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar la casilla azul actual
                casillas[fila][blueXPositions[index]].setBackground(null);

                // Actualizar la posición del cuadrado azul
                blueXPositions[index]++;
                if (blueXPositions[index] >= COLUMNAS) {
                    blueXPositions[index] = 0; // Reiniciar al llegar al final de la fila
                }

                // Pintar el cuadrado azul en la nueva posición
                casillas[fila][blueXPositions[index]].setBackground(Color.BLUE);
                repaint();

                // Comprobar si colisiona con el cuadrado rojo
                comprobarColisionConAzul(fila);
            }
        });

        blueTimers[index].start(); // Iniciar el movimiento del cuadrado azul
    }

    private void comprobarColisionConAzul(int fila) {
        // Verificar si el cuadrado azul colisiona con el rojo
        if (fila == posRojoY && blueXPositions[fila] == posRojoX) {
            reiniciarPosicionRojo(); // Si colisiona, reiniciar la posición del rojo
        }
    }

    private void moverRojoDerecha() {
        if (posRojoX < COLUMNAS - 1) { // Verificar límite derecho
            casillas[posRojoY][posRojoX].setBackground(null); // Limpiar la posición anterior
            posRojoX++; // Mover a la derecha
            casillas[posRojoY][posRojoX].setBackground(Color.RED); // Pintar la nueva posición
        }
    }

    private void moverRojoIzquierda() {
        if (posRojoX > 0) { // Verificar límite izquierdo
            casillas[posRojoY][posRojoX].setBackground(null); // Limpiar la posición anterior
            posRojoX--; // Mover a la izquierda
            casillas[posRojoY][posRojoX].setBackground(Color.RED); // Pintar la nueva posición
        }
    }

    private void moverRojoArriba() {
        if (posRojoY > 0) { // Verificar límite superior
            casillas[posRojoY][posRojoX].setBackground(null); // Limpiar la posición anterior
            posRojoY--; // Mover hacia arriba
            casillas[posRojoY][posRojoX].setBackground(Color.RED); // Pintar la nueva posición
        }
    }

    private void moverRojoAbajo() {
        if (posRojoY < FILAS - 1) { // Verificar límite inferior
            casillas[posRojoY][posRojoX].setBackground(null); // Limpiar la posición anterior
            posRojoY++; // Mover hacia abajo
            casillas[posRojoY][posRojoX].setBackground(Color.RED); // Pintar la nueva posición
        }
    }

    private void reiniciarPosicionRojo() {
        JOptionPane.showMessageDialog(this, "¡Colisión con un cuadrado azul! Volviendo a la posición inicial.");
        casillas[posRojoY][posRojoX].setBackground(null); // Limpiar la posición actual
        posRojoX = 3; // Volver a la posición inicial
        posRojoY = 7; // Reiniciar la fila también
        casillas[posRojoY][posRojoX].setBackground(Color.RED); // Pintar el cuadrado rojo de nuevo
    }
}
