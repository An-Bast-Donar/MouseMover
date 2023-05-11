import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseMoverGUI extends JFrame implements ActionListener {
    private JButton startButton, stopButton;
    private MouseMover mouseMover;

    public MouseMoverGUI() throws AWTException {
        // Título de la ventana
        super("Mouse Mover");

        // Agrega el icono de la ventana
        ImageIcon icon = new ImageIcon("assets/images/cursor.png");
        setIconImage(icon.getImage());

        // Cierra la ventana cuando se presiona el botón de cerrar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Define un BorderLayout para el JFrame
        setLayout(new BorderLayout());

        // Crea el JLabel con el texto
        JLabel label = new JLabel(
                "<html><center>This program moves the mouse automatically at regular intervals.<br>Press Start to begin movement and Stop to pause it.</center></html>");

        // Agrega el JLabel al centro del JFrame
        add(label, BorderLayout.CENTER);

        // Crea los botones de inicio y detención del movimiento del ratón
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        stopButton.setEnabled(false);

        // Crea un JPanel para agregar los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        // Agrega el JPanel de botones debajo del JLabel
        add(buttonPanel, BorderLayout.SOUTH);

        // Registra los botones con el controlador de eventos
        startButton.addActionListener(this);
        stopButton.addActionListener(this);

        // Ajusta el tamaño de la ventana al tamaño de los botones
        pack();

        // Centra la ventana en la pantalla
        setLocationRelativeTo(null);

        // Hace visible la ventana
        setVisible(true);

        // Instancia el singleton MouseMover
        mouseMover = MouseMover.getInstance();
    }

    // Implementa el método actionPerformed de la interfaz ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            // Inicia el movimiento del ratón
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            mouseMover.startMouseMover();
        } else if (e.getSource() == stopButton) {
            // Detiene el movimiento del ratón
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            mouseMover.stopMouseMover();
        }
    }

    // Función principal que crea la interfaz de usuario
    public static void main(String[] args) throws AWTException {
        new MouseMoverGUI();
    }
}
