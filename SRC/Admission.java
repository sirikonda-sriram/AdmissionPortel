import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Map;
import java.util.LinkedHashMap;

public class Admission extends WindowBase implements ActionListener {

    TextField tfName, tfPhone;
    Choice branchChoice, courseChoice;
    Label lblIdValue;

    String admissionId;
    Map<String, Map<String, String>> data = new LinkedHashMap<>();

    public Admission() {
        super("Admission Form", 420, 320); 
        setLayout(new GridLayout(6, 2, 6, 6));

        Map<String,String> m;

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

        add(new Label("Enter Name:"));
        tfName = new TextField();
        add(tfName);

        add(new Label("Enter Phone Number:"));
        tfPhone = new TextField();
        add(tfPhone);

        add(new Label("Select Branch:"));
        branchChoice = new Choice();
        for (String br : data.keySet()) branchChoice.add(br);
        branchChoice.addItemListener(e -> loadCourses());
        add(branchChoice);

        add(new Label("Select Course:"));
        courseChoice = new Choice();
        loadCourses();
        add(courseChoice);

        add(new Label("Generated Admission ID:"));
        lblIdValue = new Label("(will be generated on submit)");
        add(lblIdValue);

        Button btn = new Button("Submit Admission");
        btn.addActionListener(this);
        add(btn);

        Button btnExit = new Button("Cancel");
        btnExit.addActionListener(ae -> {
            closed = true;
            dispose();
        });
        add(btnExit);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    void loadCourses() {
        courseChoice.removeAll();
        String br = branchChoice.getSelectedItem();
        if (br == null) return;
        for (String c : data.get(br).keySet()) courseChoice.add(c);
    }

    // --------- POLYMORPHISM (method overloading) ---------
    String generateAdmissionId() {
        return "NAT" + (System.currentTimeMillis() % 100000);
    }

    String generateAdmissionId(int length) {
        Random r = new Random();
        int num = (int)(Math.pow(10, length-1) + r.nextInt((int)Math.pow(10, length-1)));
        return "NAT-" + num;
    }

    // ---------------- ERROR DIALOG ----------------
    void showErrorDialog(String msg) {
        Dialog d = new Dialog(this, "Error", true);
        d.setLayout(new FlowLayout());
        d.add(new Label(msg));
        Button ok = new Button("OK");
        ok.addActionListener(e -> d.dispose());
        d.add(ok);
        d.setSize(250, 120);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }

    // ---------------- INFO DIALOG ----------------
    public void showMessage(String msg) {
        Dialog d = new Dialog(this, "Message", true);
        d.setLayout(new FlowLayout());
        d.add(new Label(msg));
        Button ok = new Button("OK");
        ok.addActionListener(e -> d.dispose());
        d.add(ok);
        d.setSize(250, 120);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = tfName.getText().trim();
        String phone = tfPhone.getText().trim();

        if (name.length() == 0) {
            showErrorDialog("Name cannot be empty!");
            return;
        }

        if (!phone.matches("\\d{10}")) {
            showErrorDialog("Invalid Phone Number");
            return;
        }

        // Using overloaded method (polymorphism!)
        admissionId = generateAdmissionId(5);
        lblIdValue.setText(admissionId);

        // -----------------------------
        // ✔️ PRINTING DETAILS TO CONSOLE
        // -----------------------------
        System.out.println("===== ADMISSION DETAILS =====");
        System.out.println("Name        : " + name);
        System.out.println("Phone       : " + phone);
        System.out.println("Branch      : " + branchChoice.getSelectedItem());
        System.out.println("Course      : " + courseChoice.getSelectedItem());
        System.out.println("Admission ID: " + admissionId);
        System.out.println("==============================");

        showMessage("Admission Successful!", 1);

        closed = true;
        dispose();
    }
}
