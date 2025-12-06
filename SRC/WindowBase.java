import java.awt.*;
import java.awt.event.*;

public class WindowBase extends Frame {

    public boolean closed = false;

    public WindowBase(String title, int w, int h) {
        setTitle(title);
        setSize(w, h);

        // Common behavior for all windows (polymorphism through overriding)
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closed = true;
                dispose();
            }
        });
    }

    // ----- POLYMORPHISM: method overloading -----

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showMessage(String msg, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(msg);
        }
    }
}
