/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    /** @param co */
    public Search(Connexion co)
    {
        co_bdd=co; //on a la base de donnee
        ctrl=new ControlerModule(this);
        
       
        submit_search=new JButton("Lancer la recherche");
        submit_search.addActionListener(ctrl);
        
        
        run_search();
        
    }
    
    
    public void run_search()
    {
        this.add(new JTextField("mutuelle"));
        this.add(submit_search);
    }
    
    public void request()
    {
        System.out.println("REqQQQQUEST");
        
        ArrayList <String> al=new ArrayList<>();
      //CA MARCHE PAS  
       System.out.println("ici");

        try {

            al=co_bdd.remplirChampsRequete("SELECT nom FROM malade WHERE mutuelle = 'MAAF' ");
        } catch (SQLException ex) {
             System.out.println("erreur");
           Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         System.out.println(al);
    }
    
      
}
