import java.awt.*;

public class NAT {
    public static void main(String[] args) {

        System.out.println(".....Welcome to NATAcadamy Education Portal.....");

        DisplayBranches db = new DisplayBranches();
        System.out.println("\n*** AWT OPENED showing Available Courses ***");
        System.out.println(">> Use the controls to view fee, or click EXIT to continue...\n");

        // wait while DisplayBranches window is open
        while (!db.closed) {
            try { Thread.sleep(100); } catch (InterruptedException ie) {}
        }

        // ---------- AWT ADMISSION CONFIRMATION ----------
        Frame temp = new Frame();
        AdmissionPrompt ap = new AdmissionPrompt(temp);

        if (ap.userAnswer) {

            // user selected YES
            Admission adm = new Admission();
            System.out.println("\n*** Fill the AWT Admission Form ***");

            while (!adm.closed) {
                try { Thread.sleep(100); } catch (InterruptedException ie) {}
            }

        } else {
            System.out.println("User cancelled admission.");
        }

        // ---------- Feedback Window ----------
        Feedback fb = new Feedback();
        System.out.println("\n*** Please give your feedback in the AWT Window ***");

        while (!fb.closed) {
            try { Thread.sleep(100); } catch (InterruptedException ie) {}
        }

        System.out.println("Program Completed Successfully!");
    }
}
