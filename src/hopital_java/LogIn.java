/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital_java;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Tao Tuong Vi
 */
public class LogIn {
    
    //attributs
    private JPanel pan;
    
    //BDD
    private JRadioButton local;
    private JRadioButton distant;
    
    //login
    private JTextField nameBDD;
    private JTextField login;
    private JTextField password;
    
    private JLabel lab_nameBDD;
    private JLabel lab_login;
    private JLabel lab_password;
    
    private JButton submit;
    
    public LogIn()
    {
        //instanciation
        pan=new JPanel();
        pan.setLayout(new GridBagLayout());
        
        //pour créer un layout, une structure sur le panel 
        GridBagConstraints grid=new GridBagConstraints();    //c'est léquivalent d'un tab en html
	
        grid.fill = GridBagConstraints.VERTICAL;
        grid.insets = new Insets(5, 5, 5, 5);
                
	//grid.gridwidth = 2; // weightx = nombre de cases en abscisse 
	//grid.gridheight = 6; // weightx = le nombre de cases en ordonnée
                
        //texte simple
        JLabel hello = new JLabel("Bienvenue, veuillez vous connecter");
        grid.gridx = 0; //on se positionne sur le grid en (0, 0)
        grid.gridy = 0;
        grid.gridwidth = 2; //le bouton prend la place de 3 boutons en largeur
        pan.add(hello, grid); //on ajoute le texte en (0,0)
        
        //boutons radio
        local = new JRadioButton("connection locale");
        distant = new JRadioButton("connection distante");
        ButtonGroup buttgroup = new ButtonGroup();
        buttgroup.add(local);
        buttgroup.add(distant);
        
        grid.gridwidth=1;
        grid.gridy = 1;
        grid.gridx = 0;
        pan.add(local, grid);
        
        grid.gridy=1;
        grid.gridx =1;
        pan.add(distant, grid);
        
        //partie avec rentree d'informations
        lab_nameBDD=new JLabel("Nom de la BDD :");
        lab_login=new JLabel("ID :");
        lab_password=new JLabel("Mot de passe :");
        
        nameBDD=new JTextField();
        login=new JTextField();
        password=new JTextField();  
        
        nameBDD.setPreferredSize(new Dimension(200, 50));
        login.setPreferredSize(new Dimension(200, 50));
        password.setPreferredSize(new Dimension(200, 50));

        grid.gridx = 0;
        grid.gridy = 2;
        pan.add(lab_nameBDD, grid);
        grid.gridy = 3;
        pan.add(lab_login, grid);
        grid.gridy = 4;
        pan.add(lab_password, grid);
        
        grid.gridx = 1;
        grid.gridy = 2;
        pan.add(nameBDD, grid);
        grid.gridy = 3;
        pan.add(login, grid);
        grid.gridy = 4;
        pan.add(password, grid);
        
        submit=new JButton("valider");
         grid.gridy = 5;
         grid.weightx=2;
        pan.add(submit, grid);
        
    }
    
            
    public JPanel getPan_connexion()
    {
        return pan;
    }
    
}
