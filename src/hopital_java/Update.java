/*
 MODULE DE  MISE A JOUR
 on veut ajouter, modifier et supprimer dans la BDD
 */
package hopital_java;

import controler.ControlerModule;
import db.Connexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


/**
 *
 * @author Tao Tuong Vi
 */
public class Update extends JPanel{
    
    private Connexion co_bdd;
    private ControlerModule ctrl;
    
    public Update(Connexion _co)
    {
        JLabel debug=new JLabel ("update (label a supprimer)");
        add(debug);
        
        co_bdd=_co;
        //Dans ce module on veut ajouter, modifier et supprimer dans la BDD
    
    }
    
}