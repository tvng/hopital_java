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


public class ControlerModule implements ActionListener{

    
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
       System.out.println("controle module");
       s.request();
    }
 
}