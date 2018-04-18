/*
 * Ceci est la VUE, le JFrame. Elle possède en attributs des JPanels et autres.
   C'est genre une big boite qui contient en attributs des panneaux d'affichage
   Elle est liée à Controler.java, donc dès qu'on fera un clic, ça va aller dans Controler (qui contient un attribut de type Graphic) et
    réappeler des fonctions de Graphic. 

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

    //notre controleur
    private Controler ctrl;
    
    private LogIn log_in; //panel de la page de connexion
   
    private JSplitPane split_pane; //ça c'est un truc qui est divisé en deux, donc pour afficher 2 panels en meme temps
    
    private Menu main_menu; //panel de menu 
    
    //un panel pour chaque module
    private Search search_pan;
    private Update update_pan;
    private Reporting reporting_pan;
    
    //truc pour se connecter à la bdd
    private Connexion co_bdd;
    
    // On cree un boolean pour valider les identifiants
    private boolean identifiants = false;
    
    public Graphic()
    { 
        
        ctrl = new Controler (this); //on lie à un controlleur
        
        setTitle("PROJET S6 - DUCRUET PAGES TAO");
        setSize(800, 600); //size of the frame
     
        //On instancie les classes
        log_in=new LogIn();
        
        //on affiche la page de login sur notre frame
        getContentPane().add(log_in); 
        
        //on ajoute un listener au bouton "valider" de notre panneau login
        log_in.getSubmit().addActionListener(ctrl);
        log_in.getLocal().addActionListener(ctrl);
        log_in.getDistant().addActionListener(ctrl);
        
        //on instancie le menu pour lui mettre des actionlistener
        main_menu=new Menu();
        //on ajoute des listener aux boutons de notre menu
        main_menu.getUpdate().addActionListener(ctrl);
        main_menu.getSearch().addActionListener(ctrl);
        main_menu.getReporting().addActionListener(ctrl);
        
        
        
    }
    
    //Cette méthode permet, avec l'action listener de la classe Controler de changer de panel
    /** Passe de l'écran de connection à l'écran avec menu+ module de recherche (par défaut)*/
    public void goToMenu(String _command)
    {

        if (("valider_connection".equals(_command)))  //si on a cliqué sur le bouton valider de la page d'accueil login
        {
            //******************ceci est la connexion à la DB locale
            try {                
                System.out.println("connection à la db");
                co_bdd=new Connexion(log_in.getNameBDD(), log_in.getLogin(), log_in.getPasswordBDD()); //ctor de Connexion
            } catch (ClassNotFoundException | SQLException e) {
               System.out.println("CONNECTEZ-VOUS SUR WAMP !!!!!!!!!!!!!! (fail connexion to db)");
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
    
    /** Fonction pour changer l'affichage des differents panels de modules */
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
            
            JScrollPane sb=new JScrollPane(reporting_pan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            
            split_pane.setBottomComponent(sb); //pour le remplacer par le pan de recherche
        }
        
    }
    
    
    /** GETTERS
     * @return  */
    public LogIn getLogIn()   {
        return log_in; }
   
    public Menu getMenu()   {
        return main_menu; }
    
    public boolean getIdentifiants() {
        return identifiants;
    }
    
    /** SETTERS */
     public void setIdentifiants(boolean identifiants) {
        this.identifiants = identifiants;
    }
    
}
