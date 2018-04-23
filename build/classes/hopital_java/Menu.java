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
 * @author Ducruet, Pages, Tao
 */
public class Menu extends JPanel{
    
    private JButton search;
    private JButton update;
    private JButton reporting;
    
    /**
      CTOR
     */
    public Menu()
    {
      
        search=new JButton("Rechercher");
        update=new JButton("MAJ");
        reporting=new JButton("Générer");
        
        this.setBackground(new Color(126,205,250));
        
        add(search);
        add(update);
        add(reporting);
    }
    
    /** GETTER
     * @return  */ 
    public JButton getSearch()
    {
        return search;
    } 
    
    /** GETTER
     * @return  */ 
     public JButton getUpdate()
    {
        return update;
    } 
     /** GETTER
     * @return  */ 
      public JButton getReporting()
    {
        return reporting;
    } 
   
    
}
