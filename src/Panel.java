import javax.swing.*;

public class Panel extends JPanel {
    Panel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void removeProfils()  {
        this.removeAll();
    }

    public void setProfils(Groupe groupe) {

        for(Etudiant etudiant:groupe.getListEtudiant()) {
            this.add(Box.createGlue());
            this.add(new Profil(etudiant.getNom(), etudiant.getPrenom()));
        }
        this.add(Box.createGlue());

    }



}
