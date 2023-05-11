import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

public class MouseMover {
    private static final int DELAY = 1000*60*3; // Intervalo de tiempo entre cada movimiento de ratón, en milisegundos
    private static final int DISTANCE = 50; // Distancia en píxeles que se moverá el ratón
    private static MouseMover instance;
    private Timer timer;
    private Robot robot;

    // Constructor privado para evitar la instanciación desde fuera de la clase
    private MouseMover() throws AWTException {
        robot = new Robot();
        timer = new Timer();
    }

    // Método público estático que devuelve la única instancia de la clase
    public static MouseMover getInstance() throws AWTException {
        if (instance == null) {
            instance = new MouseMover();
        }
        return instance;
    }

    public void startMouseMover() {
        TimerTask task = new TimerTask() {
            boolean isLeft = false;

            @Override
            public void run() {
                // Obtiene la posición actual del ratón
                Point mousePosition = MouseInfo.getPointerInfo().getLocation();
                int x = mousePosition.x;
                int y = mousePosition.y;

                // Mueve el ratón hacia la izquierda o hacia la derecha
                if (isLeft) {
                    robot.mouseMove(x - DISTANCE, y);
                } else {
                    robot.mouseMove(x + DISTANCE, y);
                }

                // Cambia la dirección del movimiento
                isLeft = !isLeft;
            }
        };

        // Programa la tarea de movimiento del ratón para que se ejecute cada cierto intervalo de tiempo
        timer.scheduleAtFixedRate(task, DELAY, DELAY);
    }

    public void stopMouseMover() {
        timer.cancel();
    }
}
