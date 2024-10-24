import javax.swing.*;
import java.awt.*;

public class GridFrame extends JFrame {

    public GridFrame() {
        // Configurar el JFrame del tablero
        setTitle("Tablero");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Añadir el GamePanel con el layout en Grid
        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
    }
}
