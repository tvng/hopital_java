/*
 MODULE DE  REPORTING/GENERER DES GRAPHIQUES
 */
package hopital_java;

import controler.ControlerModule;
import db.Connexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
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

        try {
            //on entre une requete SQL 
            al=co_bdd.remplirChampsRequete("SELECT COUNT(no_malade), code_service FROM hospitalisation GROUP BY code_service");
        } catch (SQLException ex) {
            System.out.println("erreur");
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        System.out.println(al);
        
        //on crée un objet qui contiendra toutes nos données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        
        //valeurs test que j'ai repris du pdf
        dataset.addValue(1.0, "Row 1", "Column 1");
        dataset.addValue(5.0, "Row 1", "Column 2");
        // ************ je sais pas comment faire pour recupérer les données dans l'arraylist
        // 
        
        //petit test
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