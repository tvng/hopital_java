/*
 MODULE DE  REPORTING/GENERER DES GRAPHIQUES

J'utilise le JFreeChart qui est demandé dans le pdf du cahier des charges
explications au fur et a mesure du code

J'ai aussi expliqué comment RECUPERER les données SQL de l'arrayList SQL de "Connexion" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

 */
package hopital_java;

import controler.ControlerModule;
import db.Connexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import org.jfree.chart.*; //librairie pour les graphiques
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author Ducret Amandine, Pages Hermance, Tao Tuong Vi
 */

public class Reporting extends JPanel{
    
    private Connexion co_bdd;
    private ControlerModule ctrl;
    private JFreeChart nb_malades; //nombre de malades par service
    
    /** CTOR
     * @param _co
     */
    public Reporting(Connexion _co)
    {
        co_bdd=_co;
        JLabel debug=new JLabel ("reporting (label a suppr)");
        add(debug);
        
        
        // ********** Graphique du  nombre de malades par service ********** ///
        // on va utiliser un bar chart
        
        //on a une arraylist qui contient la tab recuperee par la requete SQL
        ArrayList <String> al=new ArrayList<>();
        //c'est une arraylist car c'est comme ça qu'a codé le prof (cf classe Connexion)

        try {
            //on entre une requete SQL 
            al=co_bdd.remplirChampsRequete("SELECT COUNT(no_malade), code_service FROM hospitalisation GROUP BY code_service");
            /* on recupere notre ArrayList.
             * Cette denière est composée String
             * chaque string est la concaténation d'une ligne de données dans notre bdd, séparé avec une virgule ','
            */
            
        } catch (SQLException ex) {
            System.out.println("erreur");
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        System.out.println(al);
        
        //on crée un objet qui contiendra toutes nos données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        //pour chaque "case" de l'arraySize
        for (int i=0; i<al.size(); i++)
        {
            //on crée un tableau de string pour séparer chaque donnée du string de l'arraysize
            String[] splitted;
            //on utilise la fonction "split" et on sépare notre string sur la 'virgule' du string de l'arraylist 
            splitted = al.get(i).split(",");
            
            //on sait que y'a que 2 cases dans notre tableau de string, car on a que deux valeurs
            //la somme des malades en 0 et le nom du service en 1.


            /* Voici comment la ligne pour ajouter nos données à l'objet Dataset se présente :

               dataset.addValue(1.0, "Row 1", "Column 1");
               donc en gros
               1.0 pour la valeur en int (abscisse)
               Row 1 = une catégorie (ici : un service = une catégorie)
                Column : si y'a plusieurs fois la même catégorie, si on affiche plusieurs "bar" differents pour une même catégorie
                //par exemple si on affiche une évolution du nombre de malades au fil du temps par service mais ce n'est pas le cas
                //je vous invite a faire des tests si c'est pas clair sorryyyy
            */
            
            dataset.addValue(Integer.parseInt(splitted[0]), splitted[1], splitted[1]);            

        }

        
        //donc on crée notre bar chart
        nb_malades= ChartFactory.createBarChart("Nombre de malades par service", // chart title
        "Service", // domain axis label
        "Nombre", // range axis label
        dataset, // data
        PlotOrientation.VERTICAL, // orientation
        true, // include legend
        true, // tooltips?
        false // URLs?
        );
        
        //on affiche, faut creer un objet de type "ChartPanel"
        ChartPanel chartPanel = new ChartPanel(nb_malades, false);
        //on ajoute au panel actuel
        this.add(chartPanel);
        
        
        
    }
    
}