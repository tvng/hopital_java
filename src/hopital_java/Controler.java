/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital_java;

import java.awt.event.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Tao Tuong Vi
 */
public class Controler implements ActionListener{
 
    private Graphic vue;
    
    public Controler(Graphic _vue)
    {
        vue=_vue;
        
    }
    
    @Override
     public void actionPerformed(ActionEvent ae) {
            //Appelle la méthode de changement de panel
           Object source = ae.getSource();
           String command=ae.getActionCommand();

           vue.changeVue(command);
           
         //  if (source==)
           
	//	monMenu.switchVue(b.getActionCommand());
           // vue.remove(pan_logIn); //on enleve le pan precedent
         //   Graphic.this.getContentPane().add(pan_menu);
          //  Graphic.this.revalidate();  //pou reafficher
        }
}
