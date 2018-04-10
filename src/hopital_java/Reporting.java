/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Ducret Amandine, Pages Hermance, Tao Tuong Vi
 */

public class Reporting extends JPanel{
    
    private Connexion co_bdd;
    private ControlerModule ctrl;
    
    public Reporting(Connexion _co)
    {
        co_bdd=_co;
        JLabel debug=new JLabel ("reporting");
        add(debug);
    }
    
}