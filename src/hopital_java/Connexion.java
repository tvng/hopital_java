/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital_java;

import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Tao Tuong Vi
 */
public class Connexion {
    
    //attributs
    private JPanel pan;
    
    private JTextField nameBDD;
    private JTextField login;
    private JTextField password;
    
    private JLabel lab_nameBDD;
    private JLabel lab_login;
    private JLabel lab_password;
    
    private JButton submit;
    
    public Connexion()
    {
        //instanciation
        pan=new JPanel();
        
        lab_nameBDD=new JLabel("Nom de la BDD :");
        lab_login=new JLabel("ID :");
        lab_password=new JLabel("Mot de passe :");
        
        nameBDD=new JTextField();
        login=new JTextField();
        password=new JTextField();  
        
        nameBDD.setPreferredSize(new Dimension(100, 50));
        login.setPreferredSize(new Dimension(100, 50));
        password.setPreferredSize(new Dimension(100, 50));
        
        submit=new JButton("valider");
        
        
        pan.add(lab_nameBDD);
        pan.add(nameBDD);
        pan.add(lab_login);
        pan.add(login);
        pan.add(lab_password);
        pan.add(password);
        pan.add(submit);
    
    }
    
    
    public JPanel getPan_connexion()
    {
        return pan;
    }
    
}
