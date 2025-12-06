import java.awt.*;
import java.awt.event.*;

public class Feedback extends Frame implements ActionListener {   // Polymorphism: Frame + ActionListener

    public boolean closed = false;
    TextArea area;
    Button btnSubmit, btnExit;

    public Feedback() {

        // AWT Frame Settings
        setTitle("Admission Feedback");
        setSize(520, 340);
        setLayout(new BorderLayout(10, 10));

        // Admission Prompt / Message
        Label lbl = new Label("Please provide your feedback regarding the Admission process:");
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        add(lbl, BorderLayout.NORTH);

        // Text Area for User Feedback
        area = new TextArea();
        add(area, BorderLayout.CENTER);

        // Panel for Buttons
        Panel p = new Panel(new FlowLayout());
        btnSubmit = new Button("Submit");
        btnExit = new Button("Exit");

        // Action Listeners
        btnSubmit.addActionListener(this);   // Polymorphism (Runtime binding)
        btnExit.addActionListener(ae -> {
            closed = true;
            dispose();
        });

        p.add(btnSubmit);
        p.add(btnExit);

        add(p, BorderLayout.SOUTH);

        // Window Closing Listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closed = true;
                dispose();
            }
        });

        // Center window manually for AWT (since setLocationRelativeTo(null) is Swing)
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String fb = area.getText().trim();

        // Validation
        if (fb.isEmpty()) {
            Dialog d = new Dialog(this, "Warning", true);
            d.setLayout(new FlowLayout());
            d.add(new Label("Feedback cannot be empty!"));
            Button ok = new Button("OK");
            ok.addActionListener(ev -> d.dispose());
            d.add(ok);
            d.setSize(250, 120);
            d.setLocationRelativeTo(this);
            d.setVisible(true);
            return;
        }

        // Print in Console (as required)
        System.out.println("===== FEEDBACK RECEIVED FROM USER =====");
        System.out.println(fb);
        System.out.println("=======================================\n");

        // Thank you message
        Dialog done = new Dialog(this, "Thank You", true);
        done.setLayout(new FlowLayout());
        done.add(new Label("Thank you for submitting your feedback!"));
        Button ok = new Button("OK");
        ok.addActionListener(ev -> done.dispose());
        done.add(ok);
        done.setSize(300, 140);
        done.setLocationRelativeTo(this);
        done.setVisible(true);

        closed = true;
        dispose();
    }
}
