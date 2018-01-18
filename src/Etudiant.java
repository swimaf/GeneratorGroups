/**
 *
 * @author 20161062
 */
public class Etudiant {
    private String nom;
    private String prenom;
    private String promo;
    private String choix1;
    private String choix2;
    private String domaine;
    private String language;
    private String spec[];

    Etudiant(String nom, String prenom, String promo, String domaine, String language, String[] spec) {
        this(nom, prenom, promo);
        this.domaine = domaine.toLowerCase();
        this.language = language.toLowerCase();
        
        this.spec = new String[]{spec[0].toLowerCase(),spec[1].toLowerCase()};
        this.choix1="0";
        this.choix2="0";
    }

    Etudiant(String nom, String prenom, String promo) {
        this.nom = nom.toUpperCase();
        this.prenom = prenom.toLowerCase();
        this.promo = promo.toUpperCase();
        this.choix1="0";
        this.choix2="0";
    }
    
    Etudiant(String nom, String prenom, String promo, String domaine, String language, String[] spec, String choix1, String choix2)
    {
        this(nom, prenom, promo);
        this.domaine = domaine.toLowerCase();
        this.language = language.toLowerCase();
        
        this.spec = new String[]{spec[0].toLowerCase(),spec[1].toLowerCase()};
        this.choix1=choix1;
        this.choix2=choix2;
    }

    @Override
    public String toString() {
        return "\nEtudiant{" + "nom=" + nom + ", prenom=" + prenom + ", promo=" + promo + ", domaine=" + domaine + ", language=" + language + ", spec=" + spec + ", choix 1=" + choix1 + ", choix 2=" + choix2 + '}';
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPromo() {
        return promo;
    }

    public String getDomaine() {
        return domaine;
    }

    public String getLanguage() {
        return language;
    }

    public String[] getSpec() {
        return spec;
    }

    public String getChoix1() {
        return choix1;
    }

    public String getChoix2() {
        return choix2;
    }


    public Boolean sameSpec(Etudiant etu) {
        return (etu.getSpec() != null) && this.spec != null && (((etu.getSpec()[0].equals(this.spec[0])) || (etu.getSpec()[0].equals(this.spec[1]))) && ((etu.getSpec()[1].equals(this.spec[0])) || (etu.getSpec()[1].equals(this.spec[0]))));
    }
    
    public Boolean sameLang(Etudiant etu) {
        return (etu.getLanguage() != null) && (this.language != null) && (etu.getLanguage().equals(this.language));
    }
    
    public Boolean sameDom(Etudiant etu) {
        return (etu.getDomaine() != null) && (this.domaine != null) && (etu.getDomaine().equals(this.domaine));
    }
    
}
