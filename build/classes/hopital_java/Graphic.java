/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**  -------- VUE -------- */

package hopital_java;

//importe les packages
import controler.*;
import db.Connexion;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
*/


public class Graphic extends JFrame {
    
   // private CardLayout cl; //pour gerer le changement de panel

    private Controler ctrl;
    
    //un panel pour chaque "page"
    private LogIn log_in;
    private Menu main_menu;
    private Search search_pan;
    
    private Connexion co_bdd;
    
    public Graphic()
    { 
        
        ctrl = new Controler (this); //on lie à un controlleur
        
        //size of the frame
        setTitle("PROJET S6 - DUCRUET PAGES TAO");
        setSize(800, 600);
     
        //On instancie les classes
        log_in=new LogIn();
        main_menu=new Menu();
        search_pan=new Search();
        
        //on affiche la page de login sur notre frame
        getContentPane().add(log_in); 
        
        //on ajoute un listener au bouton "valider" de notre panneau login
        log_in.getSubmit().addActionListener(ctrl);
        
        //on ajoute des listener aux boutons de notre menu
        main_menu.getUpdate().addActionListener(ctrl);
        main_menu.getSearch().addActionListener(ctrl);
        main_menu.getGenerate().addActionListener(ctrl);
        
        
    }
    
    //Cette méthode permet, avec l'action listener de la classe Controler de changer de panel
    //par exemple : passer de la vue "écran d'accueil login" au "menu principal"
    public void goToMenu(String _command)
    {
        System.out.println("Debug Changer de Vue");
        
        if (_command=="valider")  //si on a cliqué sur le bouton valider de la page d'accueil login
        {   
            // ********************** il faudra ajouter les conditions d'entrée à la BDD !!!!!!!!
            
            System.out.println("cmd = submit accueil login"); //debug
            
            
            //******************ceci est la connexion à la DB locale, et déclaré en dur
            //il faudra récupérer les infos que l'on entre sur la page d'accueil 
            try {                
                System.out.println("connection à la db");
                co_bdd=new Connexion("hopital", "root", ""); //ctor de Connexion
            } catch (Exception e) {
               System.out.println("fail connexion to db");
            }
            
            remove(log_in); //on enleve le pan precedent
            getContentPane().add(main_menu); //on ajoute le pan menu
            revalidate();  //pour reafficher
            
        }
    }
    
    //depuis le menu on accède a chaque module par exemple : rechercher, maj, etc
    public void goToModule(String _command)
    {
         if (_command=="Rechercher")
         {
           //on enleve le pan precedent
            getContentPane().removeAll();
            getContentPane().add(search_pan); //on ajoute le pan menu
            revalidate();  //pour reafficher
          
         }
    }
    
}
