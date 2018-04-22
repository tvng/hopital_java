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
    private JCheckBox specialite, patients; //Docteur
    private JCheckBox code_service, rotation, salaire; //infirmier

    private JCheckBox nom_service, batiment, directeur; //service
    private JCheckBox code_service_ch, no_ch, nb_lits; //chambre

    //pour faire des recherches 
    private JTextField tf_nom, tf_prenom;  //Docteur, Infirmier, Malade 
    private JTextField tf_mutuelle;//, tf_nb_soignants;//malade
    private JTextField tf_patients; //docteur
    private JComboBox cb_rotation; //infirmier : blank, JOUR, NUIT
    private JComboBox cb_service; //chambre
    
    //+ moyenne salaire et order by alphabetique machin, groupby  *******************************************
    //a faire plus tard!
    private JLabel lab_search;
    private JLabel nameTable;
    private JLabel lab_fonction;

    /**
     * Constructeur qui prend en parametre la connexion a la BDD Contient 2
     * panels : un pour sélectionner les infomations à rechercher et un pour
     * afficher cette recherche Panel "information" : JComboBox pour
     * sélectionner dans quelle table on veut faire notre recherche + Elements à
     * cocher/remplir pour la recherche + bouton valider
     *
     * @param co
     */
    public Search(Connexion co) {
        co_bdd = co; //on a la base de donnee
        ctrl = new ControlerModule(this);

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
                
        panelinfo.setBackground(new Color(187,164,230));

        //********************************************* dans le rectange d'informations
        nameTable = new JLabel("Vous recherchez une information dans la table : ");
        nameTable.setBounds(30, 20, 200, 20);
        panelinfo.add(nameTable);

        // On cree un tableau de string et on le met dans une ComboBox
        //ceci est notre combo box pour definir dans quelle table on va faire notre recherche
        //on limite a 5 tables car les autres sont pas importantes (pour l'instant en tout cas)
        String[] table = {"Malade", "Docteur", "Infirmier", "Service", "Chambre"};
        combotable = new JComboBox<>(table);
        combotable.addItemListener(ctrl);
        panelinfo.add(combotable);
        
        pi2 = new JPanel(new GridBagLayout());
        grid = new GridBagConstraints();

        //****************************************************  Rectangle search
        panelsearch = new JPanel();
        panelsearch.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelsearch.setPreferredSize(new Dimension(this.getWidth(), 200));
        panelsearch.setBackground(new Color(172,164,230));

        // *********** Declaration des checkbox textfield machin pour le rectangle d'informations
        //aucun affichage ici!
        nom = new JCheckBox("nom", false);//doc, inf, mal
        prenom = new JCheckBox("prenom", false);//doc, inf, mal
        adresse = new JCheckBox("adresse", false);//doc, inf, mal
        tel = new JCheckBox("tel", false); //doc, inf, mal
        numero = new JCheckBox("numero", false); //doc, inf, mal - n°identification

        mutuelle = new JCheckBox("mutuelle", false);
        nb_soignants = new JCheckBox("nb de soignants", false); //malade

        specialite = new JCheckBox("specialite", false);  //doc
        patients = new JCheckBox("patients", false);  //Docteur

        code_service = new JCheckBox("code du service", false);
        rotation = new JCheckBox("rotation", false);
        salaire = new JCheckBox("salaire", false);  //infirmier

        nom_service = new JCheckBox("nom du service", false);
        batiment = new JCheckBox("batiment", false);
        directeur = new JCheckBox("directeur", false);  //service

        code_service_ch = new JCheckBox("code du service", false);
        no_ch = new JCheckBox("numero de chambre", false);
        nb_lits = new JCheckBox("nombre de lits", false);  //chambre

        //pour faire des recherches 
        Dimension d = new Dimension(150, 30);

        tf_nom = new JTextField(); //Docteur, Infirmier, Malade 
        tf_prenom = new JTextField();//Docteur, Infirmier, Malade         
        tf_mutuelle = new JTextField();//malade
//        tf_nb_soignants=new JTextField();//malade
        // tf_patients=new JTextField();//doc

        tf_nom.setPreferredSize(d);
        tf_prenom.setPreferredSize(d);
        tf_mutuelle.setPreferredSize(d);
        //      tf_nb_soignants.setPreferredSize(d);
        //   tf_patients.setPreferredSize(d);

        String[] t = {"", "JOUR", "NUIT"};
        cb_rotation = new JComboBox<>(t); //infirmier
        
        
        String[] tt = {"", "REA", "CHG", "CAR"};
        cb_service = new JComboBox<>(tt); //infirmier

        //*******
        this.add(panelinfo);
        this.add(panelsearch);

        submit_search = new JButton("Lancer la recherche");
        submit_search.addActionListener(ctrl);

        //******* affichage basique
        panelinfo.add(pi2);
        panelinfo.add(submit_search);

        table_selected = "Malade";
        changeTable("Malade"); //on affiche la table "malade" par defaut

        this.setVisible(true);

    }

    /**
     * Pour concaténer les bouts de String et en faire qu'un seul
     *
     * @param sn
     * @param sc
     * @param b
     * @return
     */
    public String generate_where(String sn, String sc, boolean b) {
        String where = "";

        //sn pour name et sc pour contenu
        if (b == true) {
            where += " AND ";
        }

        where += sn + "= '" + sc + "'";

        return where;

    }

    /**
     * Pour concaténer les bouts de String et en faire qu'un seul
     *
     * @param selection
     * @return
     */
    public String generate_select(Queue<String> selection) {
        String select = "";
        int s_size = selection.size();///je prend ici la valeur fixe  car la taille diminue fil du temps 

        if (s_size == 1) {
            select = selection.poll();
        } else {
            //on enleve tout

            select += selection.poll();
            for (int i = 0; i < s_size - 1; i++) {
                select += ", ";
                select += selection.poll();
            }
        }

        return select;
    }

    /**
     * Apres avoir appuyé sur le bouton validé, on lance cette fonction Regarde
     * toutes les cases cochées/remplies et genere une requete SQL
     *
     */
    public void request() {
        System.out.println("On entre dans request");

        Queue<String> selection = new LinkedList<>();
        String select = "";
        String from = table_selected;
        String where = "";
        boolean bool_where = false; //false = premier "where" entré, //true = y'a déjà eu un
        String innerjoin = "";
        String groupby = "";
        String orderby = "";

        ArrayList<String> al = new ArrayList<>();

        if (table_selected == "malade") {
            //checkbox
            if (numero.isSelected()) {
                selection.add("malade.numero");
            }
            if (nom.isSelected()) {
                selection.add("malade.nom");
            }
            if (prenom.isSelected()) {
                selection.add("malade.prenom");
            }

            if (adresse.isSelected()) {
                selection.add("adresse");
            }
            if (tel.isSelected()) {
                selection.add("tel");
            }

            //jtextfield
            if (!tf_nom.getText().isEmpty()) {
                where += generate_where("malade.nom", tf_nom.getText(), bool_where);
                bool_where = true;
            }

            if (!tf_prenom.getText().isEmpty()) {
                where += generate_where("malade.prenom", tf_prenom.getText(), bool_where);
                bool_where = true;
            }

            //JChckbox
            if (mutuelle.isSelected()) {
                selection.add("mutuelle");
            }
            if (nb_soignants.isSelected()) //alors la c'est compliqué
            {

                /* on veut afficher le nb de docteurs
                SELECT nom, prenom, COUNT(no_docteur)
                FROM malade 
                INNER JOIN soigne ON malade.numero=soigne.no_malade
                GROUP BY no_malade 
                 */
 /*
                selection.add("COUNT(no_docteur)");
                innerjoin+=" INNER JOIN soigne ON malade.numero=soigne.no_malade ";
                groupby="no_malade";
                 */

 /* mtn on veut aussi afficher le nom du doc

                SELECT employe.nom, nbdoc.nb_doc
                FROM malade 

                INNER JOIN soigne ON malade.numero=soigne.no_malade
                INNER JOIN employe ON soigne.no_docteur=employe.numero
                INNER JOIN
                (SELECT COUNT(soigne.no_docteur) as nb_doc, soigne.no_malade from soigne group by soigne.no_malade)  nbdoc ON nbdoc.no_malade=soigne.no_malade

                ORDER BY malade.nom
                 */
                selection.add("employe.nom");
                selection.add("nbdoc.nb_doc");
                innerjoin += " INNER JOIN soigne ON malade.numero=soigne.no_malade";
                innerjoin += " INNER JOIN employe ON soigne.no_docteur=employe.numero";
                innerjoin += " INNER JOIN (SELECT COUNT(soigne.no_docteur) as nb_doc, soigne.no_malade from soigne group by soigne.no_malade) nbdoc ON nbdoc.no_malade=soigne.no_malade";
                orderby += "malade.nom";
            }

            //for each jtextfield get value
            if (!tf_mutuelle.getText().isEmpty()) {
                where += generate_where("mutuelle", tf_mutuelle.getText(), bool_where);
                bool_where = true;
            }

        } else if (table_selected == "docteur" || table_selected == "infirmier") {

            if (numero.isSelected()) {
                selection.add("employe.numero");
            }
            if (nom.isSelected()) {
                selection.add("employe.nom");
            }
            if (prenom.isSelected()) {
                selection.add("employe.prenom");
            }

            if (adresse.isSelected()) {
                selection.add("employe.adresse");
            }
            if (tel.isSelected()) {
                selection.add("employe.tel");
            }

            if (!tf_nom.getText().isEmpty()) {
                where += generate_where("employe.nom", tf_nom.getText(), bool_where);
                bool_where = true;
            }

            if (!tf_prenom.getText().isEmpty()) {
                where += generate_where("employe.prenom", tf_prenom.getText(), bool_where);
                bool_where = true;
            }

            //   ----------------
            if (table_selected == "docteur") {
                innerjoin += " INNER JOIN employe ON docteur.numero=employe.numero ";

                if (specialite.isSelected()) {
                    selection.add("specialite");
                }
                if (patients.isSelected()) {

                    // requete sql 
                    /*  SELECT nom, prenom, COUNT(no_malade) FROM employe
                      INNER JOIN docteur ON docteur.numero=employe.numero
                      INNER JOIN soigne ON employe.numero=soigne.no_docteur
                      GROUP BY no_docteur
                     */
                    selection.add("COUNT(no_malade)");
                    innerjoin += " INNER JOIN soigne ON employe.numero=soigne.no_docteur ";
                    groupby = "no_docteur";
                }
            }

            if (table_selected == "infirmier") {
                innerjoin += " INNER JOIN employe ON infirmier.numero=employe.numero ";

                if (code_service.isSelected()) {
                    selection.add("code_service");
                }
                if (salaire.isSelected()) {
                    selection.add("salaire");
                }

                if (rotation.isSelected()) {
                    selection.add("rotation");
                }
                if (!cb_rotation.getSelectedItem().equals("")) {
                    where += generate_where("rotation", cb_rotation.getSelectedItem().toString(), bool_where);
                    bool_where = true;
                }
            }

        } else if (table_selected == "service") {
            if (nom_service.isSelected()) {
                selection.add("service.nom");
            }
            if (batiment.isSelected()) {
                selection.add("batiment");
            }
            if (directeur.isSelected()) {
                selection.add("employe.nom");
                selection.add("prenom");
                innerjoin += " INNER JOIN employe ON service.directeur=employe.numero ";
            }
        } else if (table_selected == "chambre") {
            if (code_service_ch.isSelected()) {
                selection.add("chambre.code_service");
            }
            
            if (!cb_service.getSelectedItem().equals("")) {
                    where += generate_where("chambre.code_service", cb_service.getSelectedItem().toString(), bool_where);
                    bool_where = true;
            }
            
            if (no_ch.isSelected()) {
                selection.add("chambre.no_chambre");
            }
            if (nb_lits.isSelected()) {
                selection.add("chambre.nb_lits");
                selection.add("hospitalisation.lit");
                selection.add("malade.nom");
                selection.add("malade.prenom");
               /* 
                SELECT nb_lits, hospitalisation.lit,  malade.nom
                FROM chambre 
                INNER JOIN hospitalisation ON hospitalisation.no_chambre=chambre.no_chambre AND hospitalisation.code_service=chambre.code_service
                INNER JOIN malade ON malade.numero=hospitalisation.no_malade
                */
               
               innerjoin += " INNER JOIN hospitalisation ON hospitalisation.no_chambre=chambre.no_chambre AND hospitalisation.code_service=chambre.code_service";
               innerjoin += " INNER JOIN malade ON malade.numero=hospitalisation.no_malade";
                
            }
        }

        if (!selection.isEmpty()) {
            // MISE EN FORME DU "SELECT" 
            select = generate_select(selection);

            //on génère la requete SQL ____________________
            String sql_final;

            sql_final = "SELECT " + select + " FROM " + from;

            if (innerjoin != "") {
                sql_final += innerjoin;
            }
            if (where != "") {
                sql_final += " WHERE " + where;
            }
            if (groupby != "") {
                sql_final += " GROUP BY " + groupby;
            }
            if (orderby != "") {
                sql_final += " ORDER BY " + orderby;
            }

            System.out.println(sql_final);

            try {

                al = co_bdd.remplirChampsRequete(sql_final);
                System.out.println(al);

            } catch (SQLException ex) {
                System.out.println("erreur");
                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Cette fonction est appelee a chaque fois que l'on change de table dans le
     * JComboBox Elle gère l'affichage
     */
    public void changeTable(String _table) {
        grid.insets = new Insets(3, 10, 3, 10);

        pi2.removeAll(); //on efface tout

        grid.gridwidth = 1; //on reset la valeur comme quoi la cellule du grid prend une seule cellule

        grid.gridy = 0; //on se positionne sur une nouvelle case (x, y)
        grid.gridx = 0;
        pi2.add(new JLabel("Cocher pour afficher :"));
        grid.gridx = 1;
        grid.anchor = GridBagConstraints.LINE_START;
        pi2.add(new JLabel("   Dont la valeur est :"));

        if (_table == "Malade" || _table == "Docteur" || _table == "Infirmier") {

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

            if (_table == "Malade") {
                table_selected = "malade";
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

            } else if (_table == "Docteur") {
                table_selected = "docteur";
                System.out.println(".d");
                grid.gridx = 0;
                grid.gridy = 6;
                pi2.add(specialite, grid);

                grid.gridy = 7;
                grid.gridx = 0;
                pi2.add(patients, grid);
                grid.gridx = 1;
//                pi2.add(tf_patients, grid);
            } else if (_table == "Infirmier") {

                table_selected = "infirmier";
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

        if (_table == "Service") {
            table_selected = "service";

            grid.anchor = GridBagConstraints.LINE_END;

            grid.gridy = 1;
            grid.gridx = 0;
            pi2.add(nom_service, grid);


            grid.gridy = 2;
            grid.gridx = 0;
            pi2.add(batiment, grid);

            grid.gridy = 3;
            grid.gridx = 0;
            pi2.add(directeur, grid);

        }

        if (_table == "Chambre") {
            table_selected = "chambre";

            grid.gridy = 1;
            grid.gridx = 0;
            pi2.add(code_service_ch, grid);
            grid.gridx = 1;
            pi2.add(cb_service, grid);
            
            grid.gridy = 2;
            grid.gridx = 0;
            pi2.add(no_ch, grid);

            grid.gridy = 3;
            grid.gridx = 0;
            pi2.add(nb_lits, grid);
        }
        pi2.repaint();
        pi2.revalidate();

    }

}
