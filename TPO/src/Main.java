/*Librerias para Swing
    import javax.swing.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    
    TO DO:

- ORDENAR LAS MATERIAS DE LAS CARRERAS POR AÑO
- VER COMO IMPLEMENTAR LA COLISION ENTRE MATERIA Y JUGADOR
- SEGUIMIENTO DE MATERIAS Y APLAZOS (se puede utilizar una lista o array para almacenar las mat de cada nivel)
- DEFINIR COMO SE INCREMENTAN LOS NIVELES Y QUE PASA CUANDO LO HACEN (aumenta velo de materias, mayor tamaño, mas materias)

*/
import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
        Jugador pj1 = null;

        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Ingrese un nombre: ");
        String nombre = scanner1.nextLine();
        System.out.println("Ingrese una carrera");
        String carrera = scanner1.next();
        scanner1.close();

        if (carrera.equalsIgnoreCase("Ingenieria")) {
            pj1 = new Ingeniero(nombre, carrera);
        } else if (carrera.equalsIgnoreCase("Licensiatura")) {
            pj1 = new Licensiado(nombre, carrera);
        } else if (carrera.equalsIgnoreCase("Tecnicatura")) {
            pj1 = new Tecnico(nombre, carrera);
        }
        if (pj1 != null)
            pj1.mostrarDatos(); // Mostrar datos en consola
        else //significa que la carrera ingresada fue incorrecta
            System.out.println("Carrera no valida ! ");
    }
}
 /*
public static void main(String[] args) {
    // Crear el JFrame
    JFrame frame = new JFrame("Login");
    frame.setSize(400, 300); //  tamaño
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null); // Usar null para posicionar los componentes manualmente

    // Centrar el JFrame
    frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla

    // Crear etiquetas y campos de texto
    JLabel nombreLabel = new JLabel("Nombre:");
    nombreLabel.setBounds(10, 20, 80, 25);
    frame.add(nombreLabel);

    JTextField nombreText = new JTextField();
    nombreText.setBounds(100, 20, 250, 25);
    frame.add(nombreText);

    JLabel carreraLabel = new JLabel("Carrera:");
    carreraLabel.setBounds(10, 60, 80, 25);
    frame.add(carreraLabel);

    JTextField carreraText = new JTextField();
    carreraText.setBounds(100, 60, 250, 25);
    frame.add(carreraText);

    // Crear el botón de iniciar
    JButton iniciarButton = new JButton("Iniciar");
    iniciarButton.setBounds(150, 150, 100, 25);
    frame.add(iniciarButton);

    // Añadir acción al botón
    iniciarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = nombreText.getText().trim(); // Obtener y recortar el nombre
            String carrera = carreraText.getText().trim(); // Obtener y recortar la carrera

            // Validar que los campos no estén vacíos y que contengan solo letras
            if (nombre.isEmpty() || carrera.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, complete ambos campos.");
                return; // Salir si hay un campo vacío
            }

            if (!nombre.matches("[a-zA-Z\\s]+") || !carrera.matches("[a-zA-Z\\s]+")) {
                JOptionPane.showMessageDialog(frame, "Los campos deben contener solo letras.");
                return; // Salir si hay caracteres no válidos
            }

            // Crear el jugador según la carrera ingresada. Lo creamos primero haciendolo null por si despues la carrera ingresada no es valida.
            Jugador pj1 = null;

            if (carrera.equalsIgnoreCase("Ingenieria")) {
                pj1 = new Ingeniero(nombre, carrera);
            } else if (carrera.equalsIgnoreCase("Licenciatura")) {
                pj1 = new Licensiado(nombre, carrera);
            } else if (carrera.equalsIgnoreCase("Tecnicatura")) {
                pj1 = new Tecnico(nombre, carrera);
            }

            // Comprobar si pj1 fue creado
            if (pj1 != null) {
                pj1.mostrarDatos(); // Mostrar datos en consola
                JOptionPane.showMessageDialog(frame, "Bienvenido " + nombre + " a la carrera de " + carrera);
            } else {
                JOptionPane.showMessageDialog(frame, "Carrera no válida. Por favor, intente de nuevo.");
            }
        }
    });

    // Configurar el JFrame
    frame.setVisible(true);
}
}
 */
