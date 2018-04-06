/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**  -------- VUE -------- */

package hopital_java;



import javax.swing.*;
import java.awt.*;

/**
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
*/


public class Graphic extends JFrame{
    
    private CardLayout cl; //pour gerer le changement de panel
    
    //un panel pour chaque "page"
    private JPanel pan_menu;
    private JPanel pan_connexion;

    
    public Graphic()
    { 
        //size of the frame
        setTitle("Test fenetre");
        setSize(800, 600);
     
        //different pannels for each functions
       
        pan_menu= new JPanel(); //on instancie un pannel pour le menu
       
        LogIn co=new LogIn();
        pan_connexion=co.getPan_connexion(); // pareil pour le labyrinthe
        getContentPane().add(pan_connexion); // ajouter le panneau dans la fenêtre
        
        
        
    }
    
}
