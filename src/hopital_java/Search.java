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
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.TitledBorder;


/**
 *
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
 */
public class Search extends JPanel {

    private Connexion co_bdd;
    private JButton submit_search; 
    private ControlerModule ctrl;
    
    private JPanel panelinfo;
    private JPanel pi2; //panel dans le panel d'info A=Afficher R=Rechercher
    private JPanel panelsearch;
    
    
    private GridBagConstraints grid;
    
    private JComboBox combotable; //j'utilise celui la pour connaitre quelle table on selectionne
    //(= ds quelle table on fait notre recherche bdd)
    private String table_selected;
    
    //selectionner les categories que l'on veut afficher
    private JCheckBox nom, prenom, adresse, tel; //Docteur, Infirmier, Malade 
    private JCheckBox numero; //Docteur, Infirmier, Malade
    private JCheckBox mutuelle, nb_soignants; //malade
    private JCheckBox specialite, nb_soignes; //Docteur
    private JCheckBox code_service, rotation, salaire; //infirmier
    
    private JCheckBox nom_service, batiment, directeur; //service
    private JCheckBox code_service_ch, no_ch, nb_lits; //chambre
    
    
    //pour faire des recherches 
    private JTextField tf_nom, tf_prenom;  //Docteur, Infirmier, Malade 
    private JTextField tf_mutuelle;//, tf_nb_soignants;//malade
    private JTextField tf_nb_soignes; //docteur
    private JComboBox cb_rotation; //infirmier : blank, JOUR, NUIT
    
    
    //+ moyenne salaire et order by alphabetique machin, groupby  *******************************************
    //a faire plus tard!

   
    private JLabel lab_search;
    private JLabel nameTable;
    private JLabel lab_fonction;
    
 //   private JTextField fonction;
    
   // private JTextArea textArea;
    //private List<String> names = Arrays.asList("employe", "docteur", "soigne", "hospitalisation", "malade", "infirmier", "chambre", "service");
    
    
    
    /** CTOR  @param co */
    public Search(Connexion co)
    {
        co_bdd=co; //on a la base de donnee
        ctrl=new ControlerModule(this);
        

        //layout BoxLayout
        //le "PAGE_AXIS" permet d'afficher tout a la suite verticalement
        //pour tout afficher horizontalement c'est LINE_AXIS
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
       
        //Rectangle Informations
        panelinfo = new JPanel();
        panelinfo.setLayout(new BoxLayout(panelinfo, BoxLayout.PAGE_AXIS));
        panelinfo.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        //   panelinfo.setBounds(10, 10, 760, 1000);    
        panelinfo.setPreferredSize(new Dimension(this.getWidth(), 200));
        
          //********************************************* dans le rectange d'informations
        nameTable=new JLabel("Vous recherchez une information dans la table : ");
        nameTable.setBounds(30, 20, 200, 20);
        panelinfo.add(nameTable);

        // On cree un tableau de string et on le met dans une ComboBox
        //ceci est notre combo box pour definir dans quelle table on va faire notre recherche
        //on limite a 5 tables car les autres sont pas importantes (pour l'instant en tout cas)
        String[] table = {"Malade","Docteur","Infirmier","Service", "Chambre"};
        combotable = new JComboBox<>(table);
        combotable.addItemListener(ctrl);
        panelinfo.add(combotable);
        
        pi2=new JPanel(new GridBagLayout()); 
        grid=new GridBagConstraints(); 
        
        //****************************************************  Rectangle search
        panelsearch = new JPanel();
        panelsearch.setBorder( new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelsearch.setPreferredSize(new Dimension(this.getWidth(), 200));
        
        
        // *********** Declaration des checkbox textfield machin pour le rectangle d'informations
        //aucun affichage ici!
       
        nom= new JCheckBox("nom", false);//doc, inf, mal
        prenom= new JCheckBox("prenom", false);//doc, inf, mal
        adresse= new JCheckBox("adresse", false);//doc, inf, mal
        tel= new JCheckBox("tel", false); //doc, inf, mal
        numero= new JCheckBox("numero", false); //doc, inf, mal - n°identification
    
        mutuelle= new JCheckBox("mutuelle", false);
        nb_soignants= new JCheckBox("nb de soignants", false); //malade
    
        specialite= new JCheckBox("specialite", false);  //doc
        nb_soignes= new JCheckBox("nb de soignes", false);  //Docteur
        
        code_service= new JCheckBox("code_service", false); 
        rotation= new JCheckBox("rotation", false);
        salaire= new JCheckBox("salaire", false);  //infirmier
    
        nom_service= new JCheckBox("nom_service", false);
        batiment= new JCheckBox("batiment", false); 
        directeur= new JCheckBox("directeur", false);  //service
        
        code_service_ch= new JCheckBox("code_service_ch", false); 
        no_ch= new JCheckBox("no_ch", false); 
        nb_lits= new JCheckBox("nb_lits", false);  //chambre
    
    
    //pour faire des recherches 
    
        Dimension d = new Dimension(150, 30); 
    
        tf_nom=new JTextField(); //Docteur, Infirmier, Malade 
        tf_prenom=new JTextField();//Docteur, Infirmier, Malade         
        tf_mutuelle=new JTextField();//malade
//        tf_nb_soignants=new JTextField();//malade
       // tf_nb_soignes=new JTextField();//doc
        
        tf_nom.setPreferredSize(d);
        tf_prenom.setPreferredSize(d);
        tf_mutuelle.setPreferredSize(d);
  //      tf_nb_soignants.setPreferredSize(d);
     //   tf_nb_soignes.setPreferredSize(d);
        
        
        String[] t = {"","JOUR", "NUIT"};
        cb_rotation = new JComboBox<>(t); //infirmier
       
        
        //*******
        this.add(panelinfo);
        this.add(panelsearch);
        
          
        submit_search=new JButton("Lancer la recherche");
        submit_search.addActionListener(ctrl);
        
        
        //******* affichage basique
        
        panelinfo.add(pi2);
        panelinfo.add(submit_search);

       
        table_selected="Malade";
        changeTable("Malade"); //on affiche la table "malade" par defaut
        
        this.setVisible(true);
        
    }
    
    
    
    public String generate_where(String sn, String sc, boolean b)
    {
        String where="";
        
        //sn pour name et sc pour contenu
        if (b==true)
        {
            where+= " AND ";
        }
                
        where+= sn + "= '" + sc + "'";
        
        return where;
        
    }
    /** */
    public void request()
    {
        
        System.out.println("REquEST (debug a effacer)");
        Queue <String> selection = new LinkedList<>();
        String select="";
        String from=table_selected;
        String where="";
        boolean bool_where=false; //false = premier "where" entré, //true = y'a déjà eu un
        String innerjoin="";
        String groupby="";
        
        ArrayList <String> al=new ArrayList<>();
      
        
        if (table_selected=="malade" || table_selected=="docteur" || table_selected=="infirmier")
        {
            
            // SELECT * 
            if(numero.isSelected()){selection.add(numero.getText());}
            if(nom.isSelected()){selection.add(nom.getText());}
            if(prenom.isSelected()){selection.add(prenom.getText());}
            if(adresse.isSelected()){selection.add(adresse.getText());}
            if(tel.isSelected()){selection.add(tel.getText());}
            
           
            //for each jtextfield get value
            if (!tf_nom.getText().isEmpty())
            {  
                where+= generate_where("nom", tf_nom.getText(), bool_where);
                bool_where=true;
            }
            
            if (!tf_prenom.getText().isEmpty())
            {
                where+= generate_where("prenom", tf_prenom.getText(), bool_where);
                bool_where=true;  
            }
            
            if (table_selected=="malade")
            {
                if(mutuelle.isSelected()){selection.add(mutuelle.getText());}
                if(nb_soignants.isSelected()) //alors la c'est la merde
                { 
                    
                    // requete sql 
                  /*  SELECT nom, prenom, COUNT(no_docteur)
                    FROM malade 
                    INNER JOIN soigne ON malade.numero=soigne.no_malade
                    GROUP BY no_malade 
                    */
                    selection.add("COUNT(no_docteur)");
                    innerjoin=" INNER JOIN soigne ON malade.numero=soigne.no_malade ";
                    groupby="no_malade";
                }
                
                 //for each jtextfield get value
                if (!tf_mutuelle.getText().isEmpty())
                {  
                    where+= generate_where("mutuelle", tf_mutuelle.getText(), bool_where);
                    bool_where=true;
                }
                
            }
            else if (table_selected=="docteur")
            {
                innerjoin+=" INNER JOIN employe ON docteur.numero=employe.numero ";
                if(specialite.isSelected()){selection.add(specialite.getText());}
                if(nb_soignes.isSelected())
                { 
                    
                    // requete sql 
                     /*  SELECT nom, prenom, COUNT(no_malade) FROM employe
                      INNER JOIN docteur ON docteur.numero=employe.numero
                      INNER JOIN soigne ON employe.numero=soigne.no_docteur
                      GROUP BY no_docteur
                    */
                    selection.add("COUNT(no_malade)");
                    innerjoin+=" INNER JOIN soigne ON employe.numero=soigne.no_docteur ";
                    groupby="no_docteur";
                }
            }
            else if (table_selected=="infirmier")
            {
                
                innerjoin+=" INNER JOIN employe ON infirmier.numero=employe.numero ";
                if(code_service.isSelected()){selection.add(code_service.getText());}
                if(salaire.isSelected()){selection.add(salaire.getText());}
                
                
                if(rotation.isSelected()){selection.add(rotation.getText());}
                if (!cb_rotation.getSelectedItem().equals(""))
                {
                    where+= generate_where("rotation", cb_rotation.getSelectedItem().toString(), bool_where);
                    bool_where=true; 
                }
        
                
            }
            
            
        }
        
      
        if (!selection.isEmpty())
        {
              // MISE EN FORME DU "SELECT" ___________________________
            int s_size=selection.size();///je prend ici sinon ça va diminuer au fil du temps 
            
            
            if (s_size==1)
            {
                select=selection.poll();
            }
            else {
                //on enleve tout
                
                select+=selection.poll();
                for (int i=0; i<s_size-1; i++)
                {
                    
                    select += ", ";                                   
                    select+= selection.poll();
                }

            }
            
            try {
                String sql_final;
                sql_final="SELECT " + select + " FROM " + from;
                
                if (innerjoin!="")
                { sql_final+= innerjoin;}
                
                if (where!="")
                {
                    sql_final+= " WHERE " + where;
                }
                
                if (groupby!="")
                {
                  sql_final+=" GROUP BY " + groupby;   
                }
                
                    System.out.println(sql_final);
            
                 al=co_bdd.remplirChampsRequete(sql_final);
                
                    System.out.println(al);
            
                    
                    
            } catch (SQLException ex) {
                System.out.println("erreur");
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            } 
        
        }
    }
    
    public void changeTable(String _table)
    {
        grid.insets = new Insets(3, 10, 3, 10);
       
        
        pi2.removeAll(); //on efface tout
      
        grid.gridwidth=1; //on reset la valeur comme quoi la cellule du grid prend une seule cellule
        
        grid.gridy = 0; //on se positionne sur une nouvelle case (x, y)
        grid.gridx = 0;
        pi2.add(new JLabel("Cocher pour afficher :"));
        grid.gridx=1;
        grid.anchor = GridBagConstraints.LINE_START;
        pi2.add(new JLabel("   Dont la valeur est :"));
        
        
        if (_table=="Malade" || _table=="Docteur" ||_table=="Infirmier" )
        {  
            
            System.out.println("mdi");
            //affichage commun des nom, prenom, adresse, telephone
            grid.anchor = GridBagConstraints.LINE_END;
            
            grid.gridy = 1; 
            grid.gridx = 0;
            pi2.add(numero, grid);
            
            grid.gridy = 2; 
            grid.gridx = 0;
            pi2.add(nom, grid);
            grid.gridx = 1;
            pi2.add(tf_nom, grid);
            
            grid.gridy = 3;
            grid.gridx = 0;
            pi2.add(prenom, grid);
            grid.gridx = 1;
            pi2.add(tf_prenom, grid);
            
            grid.gridy = 4;
            grid.gridx = 0;
            pi2.add(adresse, grid);
            grid.gridy = 5;
            pi2.add(tel, grid);

            if (_table=="Malade")
            {  
                 table_selected="malade";
                System.out.println(".m");
                
                grid.gridy = 6; 
                grid.gridx = 0;
                pi2.add(nb_soignants, grid);
                grid.gridx = 1;
//                pi2.add(tf_nb_soignants, grid);
               
                grid.gridy = 7; 
                grid.gridx = 0;
                pi2.add(mutuelle, grid);
                grid.gridx = 1;
                pi2.add(tf_mutuelle, grid);
                
            }
            else if (_table=="Docteur")
            {
                 table_selected="docteur";
                System.out.println(".d");
                grid.gridx = 0;
                grid.gridy = 6; 
                pi2.add(specialite, grid);
                
                grid.gridy = 7; 
                grid.gridx = 0;
                pi2.add(nb_soignes, grid);
                grid.gridx = 1;
//                pi2.add(tf_nb_soignes, grid);
            }
            else if(_table=="Infirmier")
            {
              
                 table_selected="infirmier";
                System.out.println(".i");
                grid.gridx = 0;
                grid.gridy = 6; 
                pi2.add(salaire, grid);
                grid.gridy = 7; 
                pi2.add(rotation, grid);
                grid.gridx = 1;
                pi2.add(cb_rotation, grid);
                
            }
           
        }
        
        
        if (_table=="Service")
        {
            table_selected="service";
        }
        
        if (_table=="Chambre")
        {
            table_selected="chambre";
        }
        pi2.repaint();
        pi2.revalidate();
        
    }
    
    public void searchGrid(){
        
 
        
        
        /* JButton button=new JButton();
        textArea = new JTextArea("Taaaa");
        button.setBounds(250, 342, 90, 30);
        this.add(button); */
        
        /*
        
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
    //  cboId.setBounds(250, 60, 150, 20);
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
  //    cboId2.setBounds(250, 90, 150, 20);
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
    //  cboId3.setBounds(250, 120, 150, 20);
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
      //cboId4.setBounds(250, 150, 150, 20);
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
     // cboId5.setBounds(250, 180, 150, 20);
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
     // cboId6.setBounds(250, 210, 150, 20);
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
    //    cboId7.setBounds(250, 240, 150, 20);
        panelinfo.add(cboId7);
        
        Vector v8=new Vector();
      
        no_docteur_soigne= new JCheckBox("docteur", false);
        no_malade_soigne= new JCheckBox("malade", false);
        v8.add("Soigne");
        v8.add(no_docteur_soigne);
        v8.add(no_malade_soigne);
        
        cboId8 = new JComboCheckBox(v8);
    //    cboId8.setBounds(250, 270, 150, 20);
        panelinfo.add(cboId8);
               
        lab_fonction=new JLabel("En fonction de : ");
       // lab_fonction.setBounds(30, 300, 200, 20);
        panelinfo.add(lab_fonction);
        
        fonction= new JTextField();
      //  fonction.setBounds(250, 300, 150, 20);
        fonction.setPreferredSize(new Dimension (100, 20));
        panelinfo.add(fonction);
        
      */
    }
    
    
  
    
    
}
