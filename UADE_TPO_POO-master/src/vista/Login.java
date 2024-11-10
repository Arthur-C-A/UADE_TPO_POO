package vista;

import controller.*;

import javax.swing.*;
import java.awt.*;

public class Login {
    private Image fondoImagen;
    private Estudiante estudiante;

    public Login() {
        fondoImagen = new ImageIcon("src/resources/uade.jpg").getImage();
    }

    public Estudiante mostrarLogin() {
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

        return estudiante;
    }

}

