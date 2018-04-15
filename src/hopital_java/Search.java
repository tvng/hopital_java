/*
 MODULE DE  RECHERCHE
 */
package hopital_java;

import controler.ControlerModule;
import db.Connexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


/**
 *
 * @author Tao Tuong Vi
 */
public class Search extends JPanel{

    private Connexion co_bdd;
    private JButton submit_search; 
    private ControlerModule ctrl;
    
    /** CTOR  @param co */
    public Search(Connexion co)
    {
        co_bdd=co; //on a la base de donnee
        ctrl=new ControlerModule(this);
        
       
        submit_search=new JButton("Lancer la recherche");
        submit_search.addActionListener(ctrl);
        
        
        run_search();
        
    }
    
    /** */
    public void run_search()
    {
        this.add(new JTextField("mutuelle"));
        this.add(submit_search);
    }
    
    /** */
    public void request()
    {
        System.out.println("REqQQQQUEST (debug a effacer)");
        
        //on a une arraylist qui contient la tab recuperee par la requete sql
        ArrayList <String> al=new ArrayList<>();


        try {
            //on entre une requete SQL (cf le Cahier des charges)
            al=co_bdd.remplirChampsRequete("SELECT nom FROM malade WHERE mutuelle = 'MAAF' ");
        } catch (SQLException ex) {
             System.out.println("erreur");
           Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         System.out.println(al);
    }
    
      
}
