import java.awt.*;
import java.awt.event.*;

public class AdmissionPrompt extends Dialog implements ActionListener {  // Polymorphism (implements ActionListener)

    public boolean userAnswer = false;  // true = YES, false = NO

    public AdmissionPrompt(Frame parent) {

        super(parent, "Admission Confirmation", true);
        setSize(380, 180);
        setLayout(new BorderLayout(12, 12));

        // Main message
        Label msg = new Label("Do you want to proceed with Admission?");
        msg.setFont(new Font("Arial", Font.BOLD, 14));
        msg.setAlignment(Label.CENTER);
        add(msg, BorderLayout.CENTER);

        // Buttons Panel
        Panel btnPanel = new Panel(new FlowLayout());
        Button yesBtn = new Button("YES");
        Button noBtn = new Button("NO");

        // Polymorphism in action
        yesBtn.addActionListener(this);
        noBtn.addActionListener(this);

        btnPanel.add(yesBtn);
        btnPanel.add(noBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // Closing Window = NO
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                userAnswer = false;
                dispose();
            }
        });

        // Centering for AWT windows
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width / 2 - getWidth() / 2, d.height / 2 - getHeight() / 2);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // YES = true, NO = false
        userAnswer = e.getActionCommand().equals("YES");
        dispose();
    }
}
