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
import java.sql.SQLException;


import javax.swing.*;


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
    private Update update_pan;
    private Reporting reporting_pan;
    
    private Connexion co_bdd;
    
    private JSplitPane split_pane;
    
    public Graphic()
    { 
        
        ctrl = new Controler (this); //on lie à un controlleur
        
        //size of the frame
        setTitle("PROJET S6 - DUCRUET PAGES TAO");
        setSize(800, 600);
     
        //On instancie les classes
        log_in=new LogIn();
        main_menu=new Menu();
        
        
        //on affiche la page de login sur notre frame
        getContentPane().add(log_in); 
        
        //on ajoute un listener au bouton "valider" de notre panneau login
        log_in.getSubmit().addActionListener(ctrl);
        
        //on ajoute des listener aux boutons de notre menu
        main_menu.getUpdate().addActionListener(ctrl);
        main_menu.getSearch().addActionListener(ctrl);
        main_menu.getReporting().addActionListener(ctrl);
        
        
        
    }
    
    //Cette méthode permet, avec l'action listener de la classe Controler de changer de panel
    //par exemple : passer de la vue "écran d'accueil login" au "menu principal"
    public void goToMenu(String _command)
    {
        System.out.println("Debug Changer de Vue");
        
        if ("valider_connection".equals(_command))  //si on a cliqué sur le bouton valider de la page d'accueil login
        {   
            // ********************** il faudra ajouter les conditions d'entrée à la BDD !!!!!!!!
            
            System.out.println("cmd = submit accueil login"); //debug
            
            
            //******************ceci est la connexion à la DB locale, et déclaré en dur
            //il faudra récupérer les infos que l'on entre sur la page d'accueil 
            try {                
                System.out.println("connection à la db");
                co_bdd=new Connexion("hopital", "root", ""); //ctor de Connexion
            } catch (ClassNotFoundException | SQLException e) {
               System.out.println("fail connexion to db");
            }
            
            //on instancie nos panels que l'on va utiliser plus tard
            search_pan=new Search(co_bdd);
            update_pan=new Update(co_bdd);
            reporting_pan=new Reporting(co_bdd);
            
            //on ajoute à un JSplitPane nos panels : en haut le menu, en bas notre panel
            
            split_pane=new JSplitPane(JSplitPane.VERTICAL_SPLIT, main_menu, search_pan);
            
            
            remove(log_in); //on enleve le pan precedent qui est l'écran de connection
            getContentPane().add(split_pane); //on ajoute le pan split
            revalidate();  //pour reafficher
            
        }
    }
    
    //depuis le menu on accède a chaque module par exemple : rechercher, maj, etc
    public void goToModule(String _command)
    {
        //si on a cliqué sur le bouton "rechercher"
         if ("menu_rechercher".equals(_command))
         {
           /*//on enleve le pan precedent
            getContentPane().removeAll();
            getContentPane().add(search_pan); //on ajoute le pan menu
            revalidate();  //pour reafficher
          */
             //on enleve le pan du bas
            split_pane.remove(split_pane.getBottomComponent());
            split_pane.setBottomComponent(search_pan); //pour le remplacer par le pan de recherche
        }
         
        if ("menu_MAJ".equals(_command))
        {
            //on enleve le pan du bas
            split_pane.remove(split_pane.getBottomComponent());
            split_pane.setBottomComponent(update_pan); //pour le remplacer par le pan de recherche
        }
         
         if ("menu_generer".equals(_command))
        {
            //on enleve le pan du bas
            split_pane.remove(split_pane.getBottomComponent());
            split_pane.setBottomComponent(reporting_pan); //pour le remplacer par le pan de recherche
        }
        
    }
    
    
    /** GETTERS
     * @return  */
    public LogIn getLogIn()   {
        return log_in; }
   
    public Menu getMenu()   {
        return main_menu; }
        
}
