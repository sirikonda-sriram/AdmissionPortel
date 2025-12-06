import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DisplayBranches extends Frame implements ActionListener {   // Polymorphism

    Button btnShowFee, btnExit;
    Choice branchChoice, courseChoice;
    Label feeLabel;

    Map<String, Map<String, String>> data = new LinkedHashMap<>();
    public boolean closed = false;

    public DisplayBranches() {

        setTitle("Branches, Courses & Fees");
        setSize(500, 360);
        setLayout(new BorderLayout(10, 10));

        // -------- DATA (Branch → Course → Fee) --------
        Map<String, String> m;

        m = new LinkedHashMap<>();
        m.put("JEE_MAINS", "₹ 2,55,000");
        m.put("EAMCET", "₹ 2,20,000");
        m.put("IPE", "₹ 1,50,000");
        data.put("Narsingi", m);

        m = new LinkedHashMap<>();
        m.put("JEE_MAINS", "₹ 2,40,000");
        m.put("EAMCET", "₹ 2,00,000");
        m.put("IPE", "₹ 1,30,000");
        data.put("Lbnagar", m);

        m = new LinkedHashMap<>();
        m.put("JEE_MAINS", "₹ 2,30,000");
        m.put("EAMCET", "₹ 1,80,000");
        m.put("IPE", "₹ 1,20,000");
        data.put("Mehdipatnam", m);

        // ---------------- CENTER PANEL (MIDDLE) ----------------
        Panel centerPanel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);

        Panel content = new Panel(new GridLayout(7, 1, 10, 10));

        Label heading = new Label("Select Branch & Course");
        heading.setFont(new Font("Dialog", Font.BOLD, 18));
        heading.setAlignment(Label.CENTER);
        content.add(heading);

        // Branch Selector
        content.add(new Label("Select Branch:"));
        branchChoice = new Choice();
        for (String br : data.keySet()) branchChoice.add(br);

        branchChoice.addItemListener(e -> loadCoursesForBranch());
        content.add(branchChoice);

        // Course Selector
        content.add(new Label("Select Course:"));
        courseChoice = new Choice();
        loadCoursesForBranch();
        content.add(courseChoice);

        // Fee Button
        btnShowFee = new Button("Show Fee");
        btnShowFee.setFont(new Font("Dialog", Font.BOLD, 13));
        btnShowFee.addActionListener(this);     // Polymorphism (same object behaves differently)
        content.add(btnShowFee);

        // Fee Display
        feeLabel = new Label("Fee: -");
        feeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        feeLabel.setAlignment(Label.CENTER);
        content.add(feeLabel);

        // Add to center panel
        centerPanel.add(content, gbc);
        add(centerPanel, BorderLayout.CENTER);

        // ---------------- EXIT BUTTON (BOTTOM) ----------------
        btnExit = new Button("EXIT");
        btnExit.setFont(new Font("Dialog", Font.BOLD, 13));
        btnExit.addActionListener(ae -> {
            closed = true;
            dispose();
        });
        add(btnExit, BorderLayout.SOUTH);

        // On closing window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closed = true;
                dispose();
            }
        });

        // AWT Safe Centering
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);

        setVisible(true);
    }

    // Load courses when branch changes
    void loadCoursesForBranch() {
        courseChoice.removeAll();
        String br = branchChoice.getSelectedItem();
        if (br == null) return;

        Map<String, String> cm = data.get(br);
        for (String c : cm.keySet()) courseChoice.add(c);
    }

    // Handle Show Fee button
    @Override
    public void actionPerformed(ActionEvent e) {
        String br = branchChoice.getSelectedItem();
        String c = courseChoice.getSelectedItem();
        if (br != null && c != null) {
            String fee = data.get(br).get(c);
            feeLabel.setText("Fee: " + fee);
        } else {
            feeLabel.setText("Fee: -");
        }
    }

    // On dispose() set closed = true
    @Override
    public void dispose() {
        closed = true;
        super.dispose();
    }
}
