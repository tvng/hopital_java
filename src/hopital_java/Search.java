/*
 MODULE DE  RECHERCHE
 */
package hopital_java;

import controler.*;
import controler.ControlerModule;
import db.Connexion;
import java.awt.*;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.TitledBorder;


/**
 *
 * @author Tao Tuong Vi
 */
public class Search extends JPanel {

    private Connexion co_bdd;
    private JButton submit_search; 
    private ControlerModule ctrl;
    
    private JFrame frame;
    private JPanel panelinfo;
    private JPanel panelsearch;
    private JComboBox cboTable;
    private JComboBox cboId;
    private JComboBox cboId2;
    private JComboBox cboId3;
    private JComboBox cboId4;
    private JComboBox cboId5;
    private JComboBox cboId6;
    private JComboBox cboId7;
    private JComboBox cboId8;
    //private Controler controler;
    
    private JCheckBox employe;
    private JCheckBox docteur;
    private JCheckBox soigne;
    private JCheckBox hospitalisation;
    private JCheckBox malade;
    private JCheckBox infirmier;
    private JCheckBox chambre;
    private JCheckBox service;
    
    private JCheckBox numero, nom, prenom, adresse, tel;
    private JCheckBox numero_doc, specialite;
    private JCheckBox code, nom_service, batiment, directeur;
    private JCheckBox numero_inf, code_service, rotation, salaire;
    private JCheckBox code_service_ch, no_ch, nb_lits;
    private JCheckBox no_malade, nom_malade, prenom_malade, adresse_malade, tel_malade, mutuelle; //malade
    private JCheckBox no_malade_hosp, code_service_hosp, no_chambre_hosp, lit_hosp; //hospitalisation
    private JCheckBox no_docteur_soigne, no_malade_soigne;
    
    private JLabel lab_search;
    private JLabel nameTable;
    private JLabel lab_fonction;
    
    private JTextField fonction;
    
    private JTextArea textArea;
    //private List<String> names = Arrays.asList("employe", "docteur", "soigne", "hospitalisation", "malade", "infirmier", "chambre", "service");
    
    
    //private JButton submit;
    
    /** CTOR  @param co */
    public Search(Connexion co)
    {
        co_bdd=co; //on a la base de donnee
        ctrl=new ControlerModule(this);
        
       
        //submit_search=new JButton("Lancer la recherche");
        //submit_search.addActionListener(ctrl);
        
        
        //run_search();
        searchGrid();
        
    }
    
    /** */
    public void run_search()
    {
        this.add(new JTextField("mutuelle"));
        this.add(submit_search);
    }
    
    /** */
    public void request()
    {
        System.out.println("REqQQQQUEST (debug a effacer)");
        String table="";
        String selection="";
        if(employe.isSelected()){
            table=employe.getText();
            
            if(numero.isSelected()){selection=numero.getText();}
            if(nom.isSelected()){selection=nom.getText();}
            if(prenom.isSelected()){selection=prenom.getText();}
            if(adresse.isSelected()){selection=adresse.getText();}
            if(tel.isSelected()){selection=tel.getText();}
        }
        
        if(docteur.isSelected()){ 
            table=docteur.getText();
            
            if(numero_doc.isSelected()){selection=numero_doc.getText();}
            if(specialite.isSelected()){selection=specialite.getText();}
        }
        if(service.isSelected()){
            table=docteur.getText();
            
            if(code.isSelected()){selection=code.getText();}
            if(nom_service.isSelected()){selection=nom_service.getText();}
            if(batiment.isSelected()){selection=batiment.getText();}
            if(directeur.isSelected()){selection=directeur.getText();}
        }
        
        if(infirmier.isSelected()){
            table=employe.getText();
            
            if(numero_inf.isSelected()){selection=numero_inf.getText();}
            if(code_service.isSelected()){selection=code_service.getText();}
            if(rotation.isSelected()){selection=rotation.getText();}
            if(salaire.isSelected()){selection=salaire.getText();}
        }
        if(chambre.isSelected()){
            table=chambre.getText();
            
            if(code_service_ch.isSelected()){selection=numero.getText();}
            if(no_ch.isSelected()){selection=no_ch.getText();}
            if(nb_lits.isSelected()){selection=nb_lits.getText();}
        }
        
        if(malade.isSelected()){
            table=malade.getText();
            
            if(no_malade.isSelected()){selection=no_malade.getText();}
            if(nom_malade.isSelected()){selection=nom_malade.getText();}
            if(prenom_malade.isSelected()){selection=prenom_malade.getText();}
            if(adresse_malade.isSelected()){selection=adresse_malade.getText();}
            if(tel_malade.isSelected()){selection=tel_malade.getText();}
            if(mutuelle.isSelected()){selection=mutuelle.getText();}
        }
        
        if(hospitalisation.isSelected()){
            table=hospitalisation.getText();
            
            if(no_malade_hosp.isSelected()){selection=no_malade_hosp.getText();}
            if(code_service_hosp.isSelected()){selection=code_service_hosp.getText();}
            if(no_chambre_hosp.isSelected()){selection=no_chambre_hosp.getText();}
            if(lit_hosp.isSelected()){selection=lit_hosp.getText();}
        }
        
        if(soigne.isSelected()){
            table=soigne.getText();
            
            if(no_docteur_soigne.isSelected()){selection=no_docteur_soigne.getText();}
            if(no_malade_soigne.isSelected()){selection=no_malade_soigne.getText();}
        }
        
        //on a une arraylist qui contient la tab recuperee par la requete sql
        ArrayList <String> al=new ArrayList<>();


        try {
            //on entre une requete SQL (cf le Cahier des charges)
            al=co_bdd.remplirChampsRequete("SELECT "+selection+ " FROM "+table+" WHERE prenom = 'Marat' ");
        } catch (SQLException ex) {
             System.out.println("erreur");
           Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        
       

        /*for(String a : al){
            textArea.append(a+"\n");
        }*/

        frame.revalidate();
        frame.repaint();
        System.out.println(al);
    }
    
    public void searchGrid(){
               
        frame=new JFrame("Formulaire de recherche");
        frame.setBounds(100, 100, 800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        //submit_search=new JButton("Valider");
        //submit_search.setBounds(250, 342, 90, 30);
        //submit_search.addActionListener(ctrl);
        //frame.getContentPane().add(submit_search);
        
        panelinfo = new JPanel();
        panelinfo.setBorder(new TitledBorder(null, "information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelinfo.setBounds(10, 10, 760, 430);
        
        frame.getContentPane().add(panelinfo);
        panelinfo.setLayout(null);
        
        panelsearch = new JPanel();
        panelsearch.setBorder( new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelsearch.setBounds(10, 450, 760, 200);
        frame.getContentPane().add(panelsearch);
        panelsearch.setLayout(null);
        JButton button=new JButton(
        //textArea = new JTextArea(
    "Taaaa " );
        button.setBounds(250, 342, 90, 30);
        frame.getContentPane().add(button);
        
        
        nameTable=new JLabel("Vous recherchez dans la table : ");
        nameTable.setBounds(30, 20, 200, 20);
        panelinfo.add(nameTable);
        
        employe= new JCheckBox("employe", false);
        docteur= new JCheckBox("docteur", false);
        soigne= new JCheckBox("soigne", false);
        hospitalisation= new JCheckBox("hospitalisation", false);
        malade= new JCheckBox("malade", false);
        infirmier= new JCheckBox("infirmier", false);
        chambre= new JCheckBox("chambre", false);
        service= new JCheckBox("service", false);
        
        Vector v = new Vector();
      //v.add("Europe");
      v.add(employe);
      v.add(docteur);
      v.add(soigne);
      //v.add("United States");
      v.add(hospitalisation);
      v.add(malade);
      v.add(infirmier);
      v.add(chambre);
      v.add(service);
  
      //panelinfo.add(new JComboCheckBox(v));
        
        cboTable = new JComboCheckBox(v);
        cboTable.setBounds(250, 20, 100, 20);
        panelinfo.add(cboTable);
               
        lab_search=new JLabel("Les identifiants : ");
        lab_search.setBounds(30, 60, 200, 20);
        panelinfo.add(lab_search);
        
        Vector v2=new Vector();
      
            numero= new JCheckBox("numero", false);
            nom= new JCheckBox("nom", false);
            prenom= new JCheckBox("prenom", false);
            adresse= new JCheckBox("adresse", false);
            tel= new JCheckBox("telephone", false);
            v2.add("Employe");
            v2.add(numero);
            v2.add(nom);
            v2.add(prenom);
            v2.add(adresse);
            v2.add(tel);
      
        
        cboId = new JComboCheckBox(v2);
        cboId.setBounds(250, 60, 150, 20);
        panelinfo.add(cboId);
       
        
        
        //lab_search=new JLabel("Les identifiants : ");
        //lab_search.setBounds(30, 60, 200, 20);
        //panelinfo.add(lab_search);
        
        Vector vbis=new Vector();
      
        numero_doc= new JCheckBox("numero_docteur", false);
            specialite= new JCheckBox("specialite", false);
            vbis.add("Docteur");
            vbis.add(numero_doc);
            vbis.add(specialite);
      
        
        cboId2 = new JComboCheckBox(vbis);
        cboId2.setBounds(250, 90, 150, 20);
        panelinfo.add(cboId2);
        
        
        Vector v3=new Vector();
      
        code= new JCheckBox("code", false);
        nom_service= new JCheckBox("nom service", false);
        batiment= new JCheckBox("batiment", false);
        directeur= new JCheckBox("directeur", false);
        v3.add("Service");
        v3.add(code);
        v3.add(nom_service);
        v3.add(batiment);
        v3.add(directeur);
      
        
        cboId3 = new JComboCheckBox(v3);
        cboId3.setBounds(250, 120, 150, 20);
        panelinfo.add(cboId3);
        
        Vector v4=new Vector();
      
        numero_inf= new JCheckBox("numero", false);
        code_service= new JCheckBox("code service", false);
        rotation= new JCheckBox("rotation", false);
        salaire= new JCheckBox("salaire", false);
        v4.add("Infirmier");
        v4.add(numero_inf);
        v4.add(code_service);
        v4.add(rotation);
        v4.add(salaire);
      
        
        cboId4 = new JComboCheckBox(v4);
        cboId4.setBounds(250, 150, 150, 20);
        panelinfo.add(cboId4);
        
        Vector v5=new Vector();
      
        code_service_ch= new JCheckBox("code service", false);
        no_ch= new JCheckBox("numero", false);
        nb_lits= new JCheckBox("nombre lits", false);
        v5.add("Chambre");
        v5.add(code_service);
        v5.add(no_ch);
        v5.add(nb_lits);
      
        
        cboId5 = new JComboCheckBox(v5);
        cboId5.setBounds(250, 180, 150, 20);
        panelinfo.add(cboId5);
        
        Vector v6=new Vector();
      
        no_malade= new JCheckBox("numero", false);
        nom_malade= new JCheckBox("nom", false);
        prenom_malade= new JCheckBox("rotation", false);
        adresse_malade= new JCheckBox("adresse", false);
        tel_malade= new JCheckBox("telephone", false);
        mutuelle= new JCheckBox("mutuelle", false);
        v6.add("Malade");
        v6.add(numero_inf);
        v6.add(code_service);
        v6.add(rotation);
        v6.add(salaire);
      
        
        cboId6 = new JComboCheckBox(v6);
        cboId6.setBounds(250, 210, 150, 20);
        panelinfo.add(cboId6);
        
        Vector v7=new Vector();
      
        no_malade_hosp= new JCheckBox("numero", false);
        code_service_hosp= new JCheckBox("code service", false);
        no_chambre_hosp= new JCheckBox("numero chambre", false);
        lit_hosp= new JCheckBox("lit", false);
        v7.add("Hospitalisation");
        v7.add(no_malade_hosp);
        v7.add(code_service_hosp);
        v7.add(no_chambre_hosp);
        v7.add(lit_hosp);
      
        
        cboId7 = new JComboCheckBox(v7);
        cboId7.setBounds(250, 240, 150, 20);
        panelinfo.add(cboId7);
        
        Vector v8=new Vector();
      
        no_docteur_soigne= new JCheckBox("docteur", false);
        no_malade_soigne= new JCheckBox("malade", false);
        v8.add("Soigne");
        v8.add(no_docteur_soigne);
        v8.add(no_malade_soigne);
        
        cboId8 = new JComboCheckBox(v8);
        cboId8.setBounds(250, 270, 150, 20);
        panelinfo.add(cboId8);
               
        lab_fonction=new JLabel("En fonction de : ");
        lab_fonction.setBounds(30, 300, 200, 20);
        panelinfo.add(lab_fonction);
        
        fonction= new JTextField();
        fonction.setBounds(250, 300, 150, 20);
        panelinfo.add(fonction);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
}
