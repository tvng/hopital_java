/*
    Cette classe est le controleur de "Graphic"
    Graphic possède un attribut de type Controler et vice versa
    Controler est utilisé lorsqu'un ActionListener intervient

 */
package controler;

import hopital_java.Graphic;
import java.awt.event.*;



/**
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
*/


public class Controler implements ActionListener{
 
    //on lie "Graphic" à "Controler"
    private Graphic vue;
    
    /** CTOR
     * @param _vue */
    public Controler(Graphic _vue)
    {
        vue=_vue;
    }
    
    //a chaque évènement/clic sur un des objets qui a un actionlistener, on appele cette méthode
    @Override
     public void actionPerformed(ActionEvent ae) {
           
        //on recupere l'objet sur lequel on a cliqué
        Object source = ae.getSource();
        
        //------------------------------------
       
    /*  //on recupere un string associe au truc sur lequel on a cliqué
        //par exemple, un JButton "valider" renverra le string "valider".
        
        String command=ae.getActionCommand();
        
        if (command=="valider") {
            vue.goToMenu(command); //on appelle la methode dans "Graphic" 
        }
    */
   
        //si l'object que l'on a récupéré c'est le bouton valider de la page login
        if (source==vue.getLogIn().getSubmit())
        {
            System.out.println("click sur le bouton valider du panel de connection");
            vue.goToMenu("valider_connection"); //on appelle la methode dans "Graphic"
            //& on lui envoie un String pour savoir sur quel bouton on a appuyé  
        }
        
         if (source==vue.getMenu().getSearch() )
        {
            System.out.println("click sur le bouton recherche du menu");
            vue.goToModule("menu_rechercher"); //on appelle la methode dans "Graphic" 
        }
        
         if (source==vue.getMenu().getUpdate() )
        {
            System.out.println("click sur le bouton recherche du menu");
            vue.goToModule("menu_MAJ"); //on appelle la methode dans "Graphic" 
        }
         
          if (source==vue.getMenu().getReporting() )
        {
            System.out.println("click sur le bouton de generation des graphiques du menu");
            vue.goToModule("menu_generer"); //on appelle la methode dans "Graphic" 
        }
         
    }
}
