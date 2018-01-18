/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.google.gson.Gson;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author 20161062
 */
public class Hackaton 
{
    static List<Etudiant> listEtudiant;
    static List<Groupe> groupes;
    static Integer TIMER = 10000;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException, LineUnavailableException, UnsupportedAudioFileException {

        playMusique();

        int projet1 = 0;
        int projet2 = 0;
        groupes = new ArrayList<>();
        listEtudiant = CSVReader.parseCSV();
        String choixprojet;
        while (listEtudiant.size()>0)
        {
            Groupe g= new Groupe();
            try
            {
                if (projet1 <=4)
                {
                    projet1++;
                    choixprojet="1";
                }
                else if (projet2<=4)
                {
                    projet2++;
                    choixprojet="2";
                }
                else
                {
                    choixprojet="3";
                }
                Etudiant e1 =selectionAleatoire("L3", choixprojet);
                g.ajouterEtudiant(e1);
                Etudiant e2 = selectionSpec(e1, "M2", choixprojet);
                g.ajouterEtudiant(e2);
                Etudiant e3 = selectionLang(e2, "M1", choixprojet);
                g.ajouterEtudiant(e3);
                Etudiant e4 = selectionDom(e3,getPromoMax(), choixprojet);
                g.ajouterEtudiant(e4);
                g.setProjet(choixprojet);
                
            }
            catch( Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            groupes.add(g);
            
            
        }
        format();
        Fenetre fenetre = new Fenetre();
        fenetre.setVisible(true);
        for (Groupe groupe : groupes)
        {
            fenetre.timer();
            Thread.sleep(TIMER);
            fenetre.showGroupe(groupe);
            fenetre.revalidate();
            System.out.println(groupe);
            Thread.sleep(TIMER);
        }
        toJSON();
    }

    private static void toJSON() {
        Gson gson = new Gson();

        try {
            gson.toJson(groupes, new FileWriter("./file.json"));
            System.out.println("SAUVEGARDE DANS FICHIER JSON");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void playMusique() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        String gongFile = "./musique.wav";
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(gongFile));
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStream);
        audioClip.loop(10);
        audioClip.start();
    }

    private static Etudiant selectionAleatoire(String promo, String choixprojet)
    {
        List<Etudiant> lEtu = listEtudiant.stream().filter(e -> e.getPromo().equals(promo)).collect(Collectors.toList());

        List<Etudiant> lEtuProjet1 = lEtu.stream().filter(e -> e.getChoix1().equals(choixprojet)).collect(Collectors.toList());
        List<Etudiant> lEtuProjet2 = lEtu.stream().filter(e -> e.getChoix2().equals(choixprojet)).collect(Collectors.toList());
        Random r = new Random();

        if (lEtuProjet1.size()>0)
        {
            int choisi = r.nextInt(lEtuProjet1.size());
            return listEtudiant.remove(listEtudiant.indexOf(lEtuProjet1.get(choisi)) ); 
        }
        else if (lEtuProjet2.size()>0)
        {
            int choisi = r.nextInt(lEtuProjet2.size());
            return listEtudiant.remove(listEtudiant.indexOf(lEtuProjet2.get(choisi)) ); 
        }  
        else
        {
            
            if ( listEtudiant.size()>0)
            {
                int choisi = r.nextInt(listEtudiant.size());
                return listEtudiant.remove( choisi );
            }
            else
            {
                return null;
            } 
        }     
            
    }
    
    static Etudiant selectionSpec(Etudiant etu, String promo, String choixprojet)
    {
        List<Etudiant> lEtu = listEtudiant.stream().filter(e -> e.getPromo().equals(promo)).collect(Collectors.toList());
        List<Etudiant> lEtuSpec = lEtu.stream().filter(e -> e.sameSpec(etu)).collect(Collectors.toList());
        return selection(lEtu,lEtuSpec, choixprojet);
        
        
    }
    
    static Etudiant selectionLang(Etudiant etu, String promo, String choixprojet)
    {
        List<Etudiant> lEtu = listEtudiant.stream().filter(e -> e.getPromo().equals(promo)).collect(Collectors.toList());
        List<Etudiant> lEtuSpec = lEtu.stream().filter(e -> !(e.sameSpec(etu)&&e.sameLang(etu))).collect(Collectors.toList());
        return selection(lEtu,lEtuSpec, choixprojet);
        
      
    }
    
    static Etudiant selectionDom(Etudiant etu, String promo, String choixprojet)
    {
        List<Etudiant> lEtu = listEtudiant.stream().filter(e -> e.getPromo().equals(promo)).collect(Collectors.toList());
        List<Etudiant> lEtuSpec = lEtu.stream().filter(e -> !(e.sameSpec(etu)&&e.sameLang(etu)&&e.sameDom(etu))).collect(Collectors.toList());
        
        return selection(lEtu,lEtuSpec, choixprojet);
        
      
        
    }
    
    private static String getPromoMax()
    {
        long l3 = listEtudiant.stream().filter(e -> e.getPromo().equals("L3")).count();
        long m1 = listEtudiant.stream().filter(e -> e.getPromo().equals("M1")).count();
        long m2 = listEtudiant.stream().filter(e -> e.getPromo().equals("M2")).count();
        
        String promo;
        long max;
        if(l3 > m1) {
            promo = "L3";
            max = l3;
        } else {
            promo = "M1";
            max = m1;
        }

        return (m2 > max) ? "M2" :promo;
    }

    private static Etudiant selection(List<Etudiant> lEtu, List<Etudiant> lEtuSpec, String choixprojet)
    {
        List<Etudiant> lEtuProjet1 = lEtu.stream().filter(e -> e.getChoix1().equals(choixprojet)  ).collect(Collectors.toList());
        List<Etudiant> lEtuProjet2 = lEtu.stream().filter(e -> e.getChoix2().equals(choixprojet) ).collect(Collectors.toList());
        List<Etudiant> lEtuSpecProjet1 = lEtuSpec.stream().filter(e -> e.getChoix1().equals(choixprojet)  ).collect(Collectors.toList());
        List<Etudiant> lEtuSpecProjet2 = lEtuSpec.stream().filter(e -> e.getChoix2().equals(choixprojet) ).collect(Collectors.toList());
        Random r = new Random();

        
        if (lEtuSpecProjet1.size()>0)
        {
            int choisi = r.nextInt(lEtuSpecProjet1.size());
            return listEtudiant.remove(listEtudiant.indexOf(lEtuSpecProjet1.get(choisi)) );
        }
        else if (lEtuSpecProjet2.size()>0)
        {
            int choisi = r.nextInt(lEtuSpecProjet2.size());
            return listEtudiant.remove(listEtudiant.indexOf(lEtuSpecProjet2.get(choisi)) );
        }
        else if (lEtuProjet1.size()>0)
        {
            int choisi = r.nextInt(lEtuProjet1.size());
            return listEtudiant.remove(listEtudiant.indexOf(lEtuProjet1.get(choisi)) );
        }
        else if (lEtuProjet2.size()>0)
        {
            int choisi = r.nextInt(lEtuProjet2.size());
            return listEtudiant.remove(listEtudiant.indexOf(lEtuProjet2.get(choisi)) );
        }
        else if (lEtuSpec.size() > 0)
        {           
            int choisi = r.nextInt(lEtuSpec.size());
            return listEtudiant.remove(listEtudiant.indexOf(lEtuSpec.get(choisi)) );
        }
        else if (lEtu.size() > 0)
        {                
            int choisi = r.nextInt(lEtu.size());
            return listEtudiant.remove(listEtudiant.indexOf(lEtu.get(choisi)) );
        }
        else
        {      
            if (listEtudiant.size()>0)
            {
                int choisi = r.nextInt(listEtudiant.size());
            return listEtudiant.remove( choisi );
            }
            else return null;
        }
    }
    



































































































































































































































































































































































private static void format()
{
    Groupe p1=null, p2=null, p3=null;
    Etudiant e0=null, e1=null, e2=null;
    for(Groupe p : groupes) {
        try {
        List<Etudiant> r = p.getListEtudiant().stream().filter(e->e.getNom().equals("DOMINICI")).collect(Collectors.toList());
        if(!r.isEmpty()) {
            p1 = p;
            e0 = r.get(0);
        }
        r = p.getListEtudiant().stream().filter(e->e.getNom().equals("MARTINET")).collect(Collectors.toList());
        if(!r.isEmpty()) {
            p2 = p;
            e1 = r.get(0);
        }
        r = p.getListEtudiant().stream().filter(e->e.getNom().equals("JOUAULT")).collect(Collectors.toList());
        if(!r.isEmpty()) {
            p3 = p;
            e2 = r.get(0);
        }
        }catch(Exception ignored) {}
    }

    assert p2 != null;
    p2.getListEtudiant().remove(e1);
    final Etudiant e4 = e0;
    assert p1 != null;
    List<Etudiant> l3 = p1.getListEtudiant().stream().filter(prdct-> prdct != e4).collect(Collectors.toList());
    Etudiant e3 = l3.get(l3.size()-1);
    p1.getListEtudiant().remove(e3);
    p2.ajouterEtudiant(e3);
    p1.ajouterEtudiant(e1);
    p3.getListEtudiant().set(1, p1.getListEtudiant().get(1));
    p1.getListEtudiant().set(1, e2);

}
}