import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class Fenetre extends JFrame {

    private Panel panel;

    Fenetre() {
        this.setTitle("Hackathon Game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 400));

        this.pack();
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        this.createUI();
    }

    private void createUI() {
        panel = new Panel();
    }


    public void showGroupe(Groupe groupe) {
        this.getContentPane().removeAll();
        JLabel label = new JLabel("Projet "+groupe.getProjet()+" - \n Formation n°"+groupe.getId());
        Border border = label.getBorder();
        Border margin = new EmptyBorder(40,10,10,10);
        label.setBorder(new CompoundBorder(border, margin));

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        this.getContentPane().add(label, BorderLayout.NORTH);
        this.getContentPane().add(panel, BorderLayout.CENTER);
        panel.removeProfils();
        panel.setProfils(groupe);
    }

    public void timer() {
        this.getContentPane().removeAll();
        JLabel jLabel =new JLabel("Nouvelle formation dans");
        Border border = jLabel.getBorder();
        Border margin = new EmptyBorder(40,10,10,10);
        jLabel.setBorder(new CompoundBorder(border, margin));

        JLabel timer = new JLabel("5");
        jLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.CENTER);

        timer.setFont(new Font("Arial", Font.PLAIN, 60));
        timer.setHorizontalAlignment(JLabel.CENTER);
        timer.setVerticalAlignment(JLabel.CENTER);

        getContentPane().add(jLabel, BorderLayout.NORTH);
        getContentPane().add(timer, BorderLayout.CENTER);

        new Thread(() -> {
            for(int i=10; i>0; i--) {
                timer.setText(String.valueOf(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
