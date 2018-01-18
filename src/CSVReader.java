/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20161062
 */
public class CSVReader 
{
    static public List<Etudiant> parseCSV()
    {
        String csvFile = "liste.csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        List<Etudiant> listEtudiant = new ArrayList<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine(); // on saute l'entéte
            while ((line = br.readLine()) != null) {

                // use comma as separator
                
                String[] etudiant = line.split(cvsSplitBy);
                Etudiant etu=null;
                if (etudiant.length>7)
                {
                    etu = new Etudiant(etudiant[1], etudiant[0], etudiant[2], etudiant[3], etudiant[4], new String[]{etudiant[5],etudiant[6]}, etudiant[7], etudiant[8]);
                }
                else if (etudiant.length > 3) 
                {
                    etu = new Etudiant(etudiant[1], etudiant[0], etudiant[2], etudiant[3], etudiant[4], new String[]{etudiant[5],etudiant[6]});
                }
                else 
                {
                    etu = new Etudiant(etudiant[1], etudiant[0], etudiant[2]);
                }
                    
                listEtudiant.add( etu);
                //System.out.println(etu.toString());
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return listEtudiant;
    }
}
