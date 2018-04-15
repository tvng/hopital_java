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
public class Menu extends JPanel{

    // ControlerMenu ctrl_m; //m pour menu
    
    private JButton search;
    private JButton update;
    private JButton generate;
    
    public Menu()
    {
      
        search=new JButton("Rechercher");
        update=new JButton("MAJ");
        generate=new JButton("Générer");
        
        add(search);
        add(update);
        add(generate);
    }
    
     
    public JButton getSearch()
    {
        return search;
    } 
    
     public JButton getUpdate()
    {
        return update;
    } 
      public JButton getGenerate()
    {
        return generate;
    } 
   
    
}
