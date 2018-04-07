/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hopital_java;

import java.awt.*;
import javax.swing.*;

/**
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
*/


//notes : Le GridBagLayout c'est un truc graphique pour definir l'emplacement de nos objets sur la page
// et c'est l'enfer

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
        
        Dimension dimension = new Dimension(200, 40); //pour la taille des labels et machins
        
        //texte simple
        JLabel hello = new JLabel("Bienvenue, veuillez vous connecter");
        grid.gridx = 0; //on se positionne sur le grid en (0, 0)
        grid.gridy = 0;
        grid.gridwidth = 2; //le bouton prend la place de 3 boutons en largeur
        
        grid.insets = new Insets(0, 0, 50, 0); //marges : top left bot right
        pan.add(hello, grid); //on ajoute le texte en (0,0)
        
        //boutons radio
        local = new JRadioButton("connection locale");
        distant = new JRadioButton("connection distante");
        ButtonGroup buttgroup = new ButtonGroup(); //on doit ajouter le tout a un groupe  pour que le radio button soit a choix unique
        
        buttgroup.add(local);
        buttgroup.add(distant); 
       
        
        grid.insets = new Insets(5, 5, 5, 5); //on definit de nouvelles marges pour la suite
        
        grid.weightx=1; //on prend 100% de la place
        grid.anchor = GridBagConstraints.LINE_END;//on aligne le contenu de la case a droite 
        grid.gridwidth=1; //on reset la valeur comme quoi la cellule du grid prend une seule cellule
        grid.gridy = 1; //on se positionne sur une nouvelle case (x, y)
        grid.gridx = 0;
        pan.add(local, grid); //on ajoute notre bouton local dans cette case
        
        grid.anchor = GridBagConstraints.LINE_START; //on aligne la case a gauche
        grid.gridy=1;
        grid.gridx =1;
        pan.add(distant, grid);
       
 
        //partie avec rentree d'informations
        lab_nameBDD=new JLabel("Nom de la BDD :");
        lab_login=new JLabel("ID :");
        lab_password=new JLabel("Mot de passe :");
        //instanciation des zones de txt
        nameBDD=new JTextField();
        login=new JTextField();
        password=new JTextField();  
        //on dimensionne
        nameBDD.setPreferredSize(dimension);
        login.setPreferredSize(dimension);
        password.setPreferredSize(dimension);

        grid.gridx = 0;
        grid.gridy = 2;
        grid.anchor = GridBagConstraints.LINE_END;//on aligne a droite
        
        //on ajoute les labels
        pan.add(lab_nameBDD, grid);
        grid.gridy = 3;
        pan.add(lab_login, grid);
        grid.gridy = 4;
        pan.add(lab_password, grid);
        
        //on ajoute les zones de text
        grid.anchor = GridBagConstraints.LINE_START;
        grid.gridx = 1;
        grid.gridy = 2;
        pan.add(nameBDD, grid);
        grid.gridy = 3;
        pan.add(login, grid);
        grid.gridy = 4;
        pan.add(password, grid);
        
        //pareil pour le bouton valider
        submit=new JButton("valider");
        grid.gridy = 5;
        pan.add(submit, grid);
        
        
    }
    
            
    public JPanel getPan_connexion()
    {
        return pan;
    }
    
}
