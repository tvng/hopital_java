/*
    Cette classe est le controleur de "Graphic"
    Graphic possède un attribut de type Controler et vice versa
    Controler est utilisé lorsqu'un ActionListener intervient

 */
package controler;

import hopital_java.Graphic;
import java.awt.event.*;
import javax.swing.JOptionPane;



/**
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
*/


public class Controler implements ActionListener{
 
    //on lie "Graphic" à "Controler"
    private Graphic vue;
    
    public Controler(Graphic _vue)
    {
        vue=_vue;
    }
    
    //a chaque évènement/clic sur un des objets qui a un actionlistener, on appele cette méthode
    @Override
     public void actionPerformed(ActionEvent ae) {
           
        //on recupere l'objet sur lequel on a cliqué (pour l'instant on l'utilise pas mais je laisse la ligne au cas ou)
        Object source = ae.getSource();
        
        //------------------------------------
        
        //on recupere un string associe au truc sur lequel on a cliqué
        //par exemple, un JButton "valider" renverra le string "valider".
        String command=ae.getActionCommand();
        
     /*   if (command=="valider") {
            vue.goToMenu(command); //on appelle la methode dans "Graphic" 
        }
        
        if (command=="Rechercher")
        {
            System.out.println("On a cliqué sur -rechercher-");
            vue.goToModule(command);
        }

*/
        
    }
}
