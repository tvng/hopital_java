/*
Page de connexion de départ : rentrer les infos utilisateur, mdp, choix de bdd etc.
IL FAUT -> récupérer ces infos, blinder, puis vérifier qu'elles sont bonnes et de connecter à la bonne bdd.
Pour l'instant on ne fait que se connecter à la bdd locale/root car j'ai mis direct dans le code, mais en théorie
il faudrait récupérer les infos rentrées par l'utilisateur.
 */
package hopital_java;

import java.awt.*;
import javax.swing.*;

/**
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
*/


//notes : Le GridBagLayout c'est un truc graphique pour definir l'emplacement de nos objets sur la page


public class LogIn extends JPanel{
    
    //attributs
  //  private JPanel pan;
    
    //BDD
    private JRadioButton local;
    private JRadioButton distant;
    
    //login
    private JTextField nameBDD; //nom de la database
    private JTextField passwordBDD;  //mdp database
    private JTextField login; //ID "root" par exemple
    private JTextField password;//pour connection distante
    
    private JLabel lab_nameBDD;
    private JLabel lab_passwordBDD;
    private JLabel lab_login;
    private JLabel lab_password;//pour connection distante
    
    private JButton submit;

    GridBagConstraints grid;
    
    public LogIn()
    {
       
        //instanciation
        setLayout(new GridBagLayout());

        
        Dimension dimension = new Dimension(200, 40); //pour la taille des labels et machins             
        
        //boutons radio
        local = new JRadioButton("connection locale", true);
        local.setBackground(new Color(23,27,102));
        local.setForeground(new Color(126,205,250));
        
        distant = new JRadioButton("connection distante"); //on coche celui la par defaut
        distant.setBackground(new Color(23,27,102));
        distant.setForeground(new Color(126,205,250));
        
        //partie avec rentree d'informations
        lab_nameBDD=new JLabel("Nom de la BDD :");
        lab_passwordBDD=new JLabel("Mot de passe de la BDD :");
        lab_login=new JLabel("ID :");
        lab_password=new JLabel("Mot de passe ECE :");
        
        //instanciation des zones de txt
        nameBDD=new JTextField("hopital");
        passwordBDD=new JTextField();  
        login=new JTextField("root");
        password=new JTextField();  
        
        //on dimensionne
        nameBDD.setPreferredSize(dimension);
        passwordBDD.setPreferredSize(dimension);
        login.setPreferredSize(dimension);
        password.setPreferredSize(dimension);

        runLogIn();  //j'ai séparé le reste dans une méthode pour faire plus propre mais on aurait pu tout mettre à la suite
        
    }

    /** */
    //juste histoire de découper le constructeur
    public void runLogIn()
    {
        
        //  **** STRUCTURE *****  Positionnement des objets sur la page d'accueil de connection 
        
        //pour créer un layout, une structure sur le panel 
        grid=new GridBagConstraints();    //c'est léquivalent d'un tab en html
        
        //on doit ajouter les boutons radio a un groupe pour qu'ils soient a choix unique
        ButtonGroup buttgroup = new ButtonGroup(); 
        buttgroup.add(local);
        buttgroup.add(distant); 
        
        //texte simple de bienvenue
        JLabel hello = new JLabel("Bienvenue, veuillez vous connecter");
        
        grid.insets = new Insets(10, 0, 20, 0); //on définit des marges : top left bot right
        grid.gridx = 0; //on se positionne sur le grid en (0, 0)
        grid.gridy = 0;
        grid.gridwidth = 2; //le label de bienvenue prend la place de 3 boutons en largeur
        //c'est comme un colspan en html
        
        this.add(hello, grid); //on ajoute le texte en (0,0)
        this.setBackground(new Color(126,205,250));
          
        grid.insets = new Insets(5, 5, 5, 5); //on definit de nouvelles marges pour la suite
        
        grid.weightx=1; //on prend 100% de la place
        grid.anchor = GridBagConstraints.LINE_END;//on aligne le contenu de la case a droite 
        grid.gridwidth=1; //on reset la valeur comme quoi la cellule du grid prend une seule cellule
        grid.gridy = 1; //on se positionne sur une nouvelle case (x, y)
        grid.gridx = 0;
        this.add(local, grid); //on ajoute notre bouton local dans cette case
        
        grid.anchor = GridBagConstraints.LINE_START; //on aligne la case a gauche
        grid.gridy=1;
        grid.gridx =1;
        this.add(distant, grid);
        
        grid.gridx = 0;
        grid.gridy = 2;
        grid.anchor = GridBagConstraints.LINE_END;//on aligne a droite
        
        //on ajoute les labels
        this.add(lab_nameBDD, grid);
        grid.gridy = 3;
        this.add(lab_passwordBDD, grid);
        grid.gridy = 4;
        this.add(lab_login, grid);
        grid.gridy = 5;
        this.add(lab_password, grid);
        
        //on ajoute les zones de text
        grid.anchor = GridBagConstraints.LINE_START;
        grid.gridx = 1;
        grid.gridy = 2;
        this.add(nameBDD, grid);
        grid.gridy = 3;
        this.add(passwordBDD, grid);
        grid.gridy = 4;
        this.add(login, grid);
        grid.gridy = 5;
        this.add(password, grid);
        
        
        //pareil pour le bouton valider
        submit=new JButton("valider");
        grid.gridy = 6;
        this.add(submit, grid);
        
        // Image accueil
        JLabel icon = new JLabel(new ImageIcon("images/croix.png"));
        grid.gridy = 8;
        this.add(icon, grid);
        
    }
    
    public void runLogInLocal(){
        this.remove(password);
        this.remove(lab_password);
        
        this.revalidate();
        this.repaint();
    }
    
    public void runLogInDistant(){    
        grid.gridx = 0;
        grid.gridy = 5;
        grid.anchor = GridBagConstraints.LINE_END;//on aligne a droite
        this.add(lab_password, grid);
        
        grid.anchor = GridBagConstraints.LINE_START;
        grid.gridx = 1;
        grid.gridy=5;
        this.add(password, grid);
        
        this.revalidate();
        this.repaint();
    }

    /** GETTERS
     * @return le texte entre dans les champs */
    public JButton getSubmit()
    {
        return submit;
    }
    
    public String getNameBDD() {
        return nameBDD.getText();
    }

    public String getPasswordBDD() {
        return passwordBDD.getText();
    }

    public String getLogin() {
        return login.getText();
    }

    public String getPassword() {
        return password.getText();
    }
    
    public JRadioButton getLocal(){
        return local;
    }
    
    public JRadioButton getDistant(){
        return distant;
    }
    
}
