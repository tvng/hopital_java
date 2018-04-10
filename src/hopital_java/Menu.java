/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital_java;

/** imports */
import javax.swing.*;

import java.awt.*;

/**
 *
 * @author Tao Tuong Vi
 */
public class Menu extends JPanel{

    // ControlerMenu ctrl_m; //m pour menu
    
    private JButton search;
    private JButton update;
    private JButton reporting;
    
    /**
     *
     */
    public Menu()
    {
      
        search=new JButton("Rechercher");
        update=new JButton("MAJ");
        reporting=new JButton("Générer");
        
        add(search);
        add(update);
        add(reporting);
    }
    
     
    public JButton getSearch()
    {
        return search;
    } 
    
     public JButton getUpdate()
    {
        return update;
    } 
      public JButton getReporting()
    {
        return reporting;
    } 
   
    
}
