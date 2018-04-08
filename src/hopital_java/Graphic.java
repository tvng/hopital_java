/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**  -------- VUE -------- */

package hopital_java;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
*/


public class Graphic extends JFrame {
    
    private CardLayout cl; //pour gerer le changement de panel
    
    //un panel pour chaque "page"
    private JPanel pan_menu;
    private JPanel pan_logIn;

    private Controler ctrl;
    
    public Graphic()
    { 
        
        ctrl = new Controler (this); //on lie à un controlleur
        
        //size of the frame
        setTitle("PROJET S6 - DUCRUET PAGES TAO");
        setSize(800, 600);
     
        //different pannels for each functions
       
        Menu gmenu= new Menu(); //on instancie un pannel pour le menu
        pan_menu=gmenu.getPan_menu();
        
        LogIn co=new LogIn();
        pan_logIn=co.getPan_connexion(); // pareil pour le labyrinthe
        
        //on affiche la page de login sur notre frame
        getContentPane().add(pan_logIn); 
        
        //on ajoute un listener au bouton "valider" de notre paneau login
        co.getSubmit().addActionListener(ctrl);
        
        
    }
    
    //Cette méthode permet, avec l'action listener de la classe Controler de changer de panel
    //par exemple : passer de la vue "écran d'accueil login" au "menu principal"
    public void changeVue(String _command)
    {
        System.out.println("Debug Changer de Vue");
        
        if (_command=="valider") 
        {   
            //il faudra ajouter les conditions d'entrée au 
            
            System.out.println("cmd = submit accueil login");
            remove(pan_logIn); //on enleve le pan precedent
            getContentPane().add(pan_menu);
            revalidate();  //pour reafficher
    
        }
    }
    
}
