import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JTextField nombreText;
    private JTextField apellidoText;

    public LoginFrame() {
        // Configuración del JFrame para el login
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el JPanel y establecer layout nulo
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Etiquetas y campos de texto
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 20, 80, 25);
        panel.add(nombreLabel);

        nombreText = new JTextField();
        nombreText.setBounds(100, 20, 250, 25);
        panel.add(nombreText);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(10, 60, 80, 25);
        panel.add(apellidoLabel);

        apellidoText = new JTextField();
        apellidoText.setBounds(100, 60, 250, 25);
        panel.add(apellidoText);

        // Botón de iniciar
        JButton iniciarButton = new JButton("Iniciar");
        iniciarButton.setBounds(150, 120, 100, 25);
        panel.add(iniciarButton);

        // Añadir el panel al frame
        add(panel);

        // Acción del botón de iniciar
        iniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarLogin();
            }
        });
    }

    private void validarLogin() {
        try {
            String nombre = nombreText.getText().trim();
            String apellido = apellidoText.getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty()) {
                throw new IllegalArgumentException("Por favor, complete ambos campos.");
            }

            if (!nombre.matches("[a-zA-Z\\s]+") || !apellido.matches("[a-zA-Z\\s]+")) {
                throw new IllegalArgumentException("Los campos deben contener solo letras.");
            }

            // Si todo es válido, abrir el nuevo panel de juego
            GridFrame gridFrame = new GridFrame();
            gridFrame.setVisible(true);
            dispose(); // Cerrar el frame del login

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
