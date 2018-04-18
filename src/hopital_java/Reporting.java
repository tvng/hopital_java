/*
 MODULE DE  REPORTING/GENERER DES GRAPHIQUES

J'utilise le JFreeChart qui est demandé dans le pdf du cahier des charges
explications au fur et a mesure du code

J'ai aussi expliqué comment RECUPERER les données SQL de l'arrayList SQL de "Connexion" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

La scrollbar sur ce panel se fait dans "GRAPHIC" dans gotomodule (j'ai pas trouvé comment le mettre dans la classe Reporting...)

 */
package hopital_java;

import controler.ControlerModule;
import db.Connexion;
import java.awt.Color;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart;

import javax.swing.*;

import org.jfree.chart.*; //librairie pour les graphiques
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import org.jfree.data.general.PieDataset;


/**
 *
 * @author Ducret Amandine, Pages Hermance, Tao Tuong Vi
 */

public class Reporting extends JPanel{
    
    private Connexion co_bdd;
    private ControlerModule ctrl;
   
    /** CTOR
     * @param _co
     */
    public Reporting(Connexion _co)
    {     
        co_bdd=_co;
        
        //layout BoxLayout
        //le "PAGE_AXIS" permet d'afficher tout a la suite verticalement
        //pour tout afficher horizontalement c'est LINE_AXIS
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
  
        // ********** Graphique du  nombre de malades par service ********** ///
        // on va utiliser un bar chart
        
        JFreeChart nb_malades; //nombre de malades par service
        
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

        JFreeChart nb_doc; //nombre de malades par service
        //donc on crée notre bar chart
        nb_doc= ChartFactory.createBarChart("Nombre de malades par service", // chart title
        "Service", // domain axis label
        "Nombre", // range axis label
        dataset, // data
        PlotOrientation.VERTICAL, // orientation
        true, // include legend
        true, // tooltips?
        false // URLs?
        );
        
        ChartPanel nb_docPanel = new ChartPanel(nb_doc, false);
        this.add(nb_docPanel);
        
        
  
        
        // ********** Graphique du  nombre docteurs par spécialité ********** ///
        // on va utiliser un bar chart
        
        ArrayList <String> al2=new ArrayList<>();

        try {
            al2=co_bdd.remplirChampsRequete("SELECT COUNT(numero), specialite FROM docteur GROUP BY specialite");
            
        } catch (SQLException ex) {
            System.out.println("erreur");
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        
        for (int i=0; i<al2.size(); i++)
        {
            String[] splitted;
            //on utilise la fonction "split" et on sépare notre string sur la 'virgule' du string de l'arraylist 
            splitted = al2.get(i).split(",");
            dataset2.addValue(Integer.parseInt(splitted[0]), splitted[1], splitted[1]);            

        }

        
        nb_malades= ChartFactory.createBarChart("Nombre de docteurs par specialite", "Spécialité", "Nombre",
        dataset2, // data
        PlotOrientation.VERTICAL, true, // include legend
        true, // tooltips?
        false // URLs?
        );
        
        //on affiche, faut creer un objet de type "ChartPanel"
        ChartPanel nb_maladesPanel = new ChartPanel(nb_malades, false);
        //on ajoute au panel actuel
        this.add(nb_maladesPanel);
        
        
        
        
        
        
         // ********** Graphique du  nombre de malade par mutuelle ********** ///
        // on va utiliser un pie chart
        
        ArrayList <String> al3 = new ArrayList<>();

        try {
            al3 = co_bdd.remplirChampsRequete("SELECT COUNT(numero), mutuelle FROM malade GROUP BY mutuelle");
            
        } catch (SQLException ex) {
            System.out.println("erreur");
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // create a dataset     
        DefaultPieDataset dataset3 = new DefaultPieDataset();
        
        for (int i=0; i<al3.size(); i++)
        {
            String[] splitted;
            //on utilise la fonction "split" et on sépare notre string sur la 'virgule' du string de l'arraylist 
            splitted = al3.get(i).split(",");
            dataset3.setValue(splitted[1], Integer.parseInt(splitted[0]));
        }

        JFreeChart nb_mal_mut; //nombre de malades par mutuelle
        //donc on crée notre pie chart
        nb_mal_mut = ChartFactory.createPieChart("Nombre de malades par mutuelles", // chart title  
        dataset3, // data
        true, // include legend
        true, // tooltips?
        false // URLs?
        );
        
        //on affiche, faut creer un objet de type "ChartPanel"
        ChartPanel nb_mal_mut_panel = new ChartPanel(nb_mal_mut, false);
        //on ajoute au panel actuel
        this.add(nb_mal_mut_panel);  
    }
}