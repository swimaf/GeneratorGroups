import javax.swing.*;
import java.awt.*;

class Profil extends JPanel {
    Profil(String nom, String prenom) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        prenom=prenom.replaceFirst(".",(prenom.charAt(0)+"").toUpperCase());
        prenom = prenom.trim();
        String prenomImage = prenom.replaceAll("é", "e");
        prenomImage = prenomImage.replaceAll(" ", "-");
        prenomImage = prenomImage.replaceAll("ï", "i");

        String file ="photos/"+nom.replaceAll(" ", "-")+"_"+prenomImage+".PNG";
        ImageIcon imageForOne = new ImageIcon(file);
        System.out.println(file);
        JLabel image = new JLabel(imageForOne);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelName = new JLabel(nom+" "+prenom);
        labelName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(image);
        this.add(labelName);
    }
}
