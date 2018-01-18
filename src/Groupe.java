
import java.util.ArrayList;
import java.util.List;


public class Groupe {
    private String projet;
    private List<Etudiant> listEtudiant=new ArrayList<>();
    private static int compteur = 0 ;
    private int id;

    Groupe()
    {
        compteur++;
        id = compteur;
    }
    
    public void ajouterEtudiant(Etudiant etudiant)
    {
        if(etudiant != null ) {
            listEtudiant.add(etudiant);
        }
        
    }

    public String getProjet() {
        return projet;
    }

    @Override
    public String toString() {
        return "Groupe {projet=" + projet  + ", listEtudiant=" + listEtudiant + ", id=" + id + '}';
    }

    public List<Etudiant> getListEtudiant() {
        return listEtudiant;
    }


    public void setProjet(String choixprojet) {
        this.projet = choixprojet;
    }

    public int getId() {
        return id;
    }
}
