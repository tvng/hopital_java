/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital_java;

/** imports */
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 *
 * @author Tao Tuong Vi
 */
public class Menu extends JFrame implements ActionListener{

    private JPanel pan;
    private JButton search;
    private JButton update;
    private JButton generate;
    
    public Menu()
    {
        pan=new JPanel();
        
        search=new JButton("Rechercher");
        update=new JButton("MAJ");
        generate=new JButton("Générer");
        
        pan.add(search);
        pan.add(update);
        pan.add(generate);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
