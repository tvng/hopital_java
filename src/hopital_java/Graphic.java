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

    
    public Graphic()
    { 
        //size of the frame
        setTitle("Test fenetre");
        setSize(800, 600);
     
        //different pannels for each functions
       
        Menu gmenu= new Menu(); //on instancie un pannel pour le menu
        pan_menu=gmenu.getPan_menu();
        
        LogIn co=new LogIn();
        pan_logIn=co.getPan_connexion(); // pareil pour le labyrinthe
        
        getContentPane().add(pan_logIn); // ajouter le panneau dans la fenêtre
        co.getSubmit().addActionListener(new ListenerChangePanel());
        
    }
    
    
       //Ecouteur de ton bouton
    public class ListenerChangePanel implements ActionListener{
        public void actionPerformed(ActionEvent clic) {
            //Appelle la méthode de changement de panel
           
            Graphic.this.remove(pan_logIn); //on enleve le pan precedent
            Graphic.this.getContentPane().add(pan_menu);
            Graphic.this.revalidate();  //pou reafficher
        }
    }
    
}
