/*
   CONTROLEUR UTILISE PAR LES "MODULES"
 */



package controler;

import hopital_java.Search;
import hopital_java.Graphic;
import java.awt.event.*;
import javax.swing.JOptionPane;



/**
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
*/


public class ControlerModule implements ActionListener, ItemListener{

    
    private Search s;
    
    /** CTOR
     * @param _s
     */
    public ControlerModule(Search _s)
    {
        s=_s;
    }
            
    @Override
    public void actionPerformed(ActionEvent ae) {
       
        String command=ae.getActionCommand();
        
        if (command=="Lancer la recherche") {
            System.out.println("controle module");
            s.request();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        
         if (ie.getStateChange() == ItemEvent.SELECTED) {
            Object item=ie.getItem();
             System.out.println("événement déclenché sur : " + ie.getItem());
            
              s.changeTable(String.valueOf(item));  
         }
       

    }
 
}