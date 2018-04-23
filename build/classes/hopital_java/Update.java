/*
 MODULE DE  MISE A JOUR
 on veut ajouter, modifier et supprimer dans la BDD
 */
package hopital_java;

import controler.ControlerModule;
import db.Connexion;
import java.awt.CheckboxGroup;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.border.LineBorder;

/**
 *
 * @author Tao Tuong Vi
 */
public class Update extends JPanel {

    private Connexion co_bdd;
    private ControlerModule ctrl;

    private JButton btnAddPers, btnAdd, btnEdit, btnEditDel, btnDelete, btnExit, btnSearch, btnEditedit;
    private JPanel panelinfo, panelpers, paneledit;
    private JTextField txtAddress, txtName, txtPrenom, txtTel, txtNum;
    private JTextField txtSpecialite;
    private JTextField txtCode_service, txtRotation, txtSalaire;
    private JTextField txtSearch;
    private JLabel label, label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8;

    private JTextField txtAddressbis, txtNamebis, txtPrenombis, txtTelbis, txtNumbis;
    private JTextField txtSpecialitebis;
    private JTextField txtCode_servicebis, txtRotationbis, txtSalairebis;
    private JLabel labelbis, labelbis_1, labelbis_2, labelbis_3, labelbis_4, labelbis_5, labelbis_6, labelbis_7, labelbis_8;

    private JLabel verification;
    //private JCheckBox employe, infirmier, docteur;
    //private JComboBox cboTable;

    //private DefaultTableModel tblModel;
    JTable tblList;
/**
 * fait la connexion avec la bdd et appelle affichage()
 * @param _co
 * @throws SQLException 
 */
    public Update(Connexion _co) throws SQLException {
        //JLabel debug=new JLabel ("update (label a supprimer)");
        //add(debug);
        co_bdd = _co;
        affichage();
        //Dans ce module on veut ajouter, modifier et supprimer dans la BDD

    }
/**
 * affichage() permet d'afficher la fenetre MAJ
 * @throws SQLException 
 */
    public void affichage() throws SQLException {
        JFrame frame = new JFrame("Mise a jour");
        frame.setBounds(100, 100, 800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        //setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JFrame frame2 = new JFrame("Edit");
        frame2.setBounds(100, 100, 450, 500);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.getContentPane().setLayout(null);        

        btnEditedit = new JButton("Edit");
        btnEditedit.setBounds(200, 350, 73, 30);
        frame2.getContentPane().add(btnEditedit);

        btnAddPers = new JButton("Add");

        btnAddPers.setBounds(200, 45, 73, 30);
        frame.getContentPane().add(btnAddPers);

        btnAdd = new JButton("Add");

        btnAdd.setBounds(400, 150, 73, 30);
        frame.getContentPane().add(btnAdd);

        btnEdit = new JButton("Edit");

        btnEdit.setBounds(360, 165, 73, 30);
        frame.getContentPane().add(btnEdit);

        btnEditDel = new JButton("Edit/Delete");

        btnEditDel.setBounds(300, 45, 150, 30);
        frame.getContentPane().add(btnEditDel);

        btnDelete = new JButton("Delete");

        btnDelete.setBounds(450, 165, 73, 30);
        frame.getContentPane().add(btnDelete);

        btnExit = new JButton("Exit");

        btnExit.setBounds(500, 45, 73, 30);
        frame.getContentPane().add(btnExit);

        btnSearch = new JButton("Search");

        btnSearch.setBounds(260, 165, 87, 30);
        frame.getContentPane().add(btnSearch);
        
        panelpers = new JPanel();
        panelpers.setBorder( new TitledBorder(null, "Personne", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        //panelpers.setPreferredSize(new Dimension(2, 3));
        panelpers.setBounds(10, 10, 760, 100);
        //this.add(panelpers);
        frame.getContentPane().add(panelpers);
        panelpers.setLayout(null);

        panelinfo = new JPanel();
        panelinfo.setBorder(new TitledBorder(null, "information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelinfo.setBounds(10, 110, 760, 680);
        //panelinfo.setPreferredSize(new Dimension(this.getWidth(), 200));

        frame.getContentPane().add(panelinfo);
        panelinfo.setLayout(null);

        paneledit = new JPanel();
        paneledit.setBorder(new TitledBorder(null, "Edit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        paneledit.setBounds(10, 110, 760, 680);
        //paneledit.setPreferredSize(new Dimension(this.getWidth(), 200));

        frame.getContentPane().add(paneledit);
        paneledit.setLayout(null);

        String[] personne = new String[]{"employe", "docteur", "infirmier"};

        JComboBox<String> combopers = new JComboBox<>(personne);
        combopers.setBounds(30, 40, 100, 20);

        // add to the parent container (e.g. a JFrame):
        panelpers.add(combopers);

        txtName = new JTextField();
        txtName.setColumns(10);
        txtName.setBounds(130, 40, 206, 20);
        panelinfo.add(txtName);

        txtPrenom = new JTextField();
        txtPrenom.setColumns(10);
        txtPrenom.setBounds(130, 70, 206, 20);
        panelinfo.add(txtPrenom);

        txtAddress = new JTextField();
        txtAddress.setColumns(10);
        txtAddress.setBounds(130, 100, 206, 20);
        panelinfo.add(txtAddress);

        txtNum = new JTextField();
        txtNum.setColumns(10);
        txtNum.setBounds(130, 130, 206, 20);
        panelinfo.add(txtNum);

        txtTel = new JTextField();
        txtTel.setColumns(10);
        txtTel.setBounds(130, 160, 206, 20);
        panelinfo.add(txtTel);

        txtSpecialite = new JTextField();
        txtSpecialite.setColumns(10);
        txtSpecialite.setBounds(130, 190, 206, 20);
        panelinfo.add(txtSpecialite);

        txtCode_service = new JTextField();
        txtCode_service.setColumns(10);
        txtCode_service.setBounds(130, 220, 206, 20);
        panelinfo.add(txtCode_service);

        txtRotation = new JTextField();
        txtRotation.setColumns(10);
        txtRotation.setBounds(130, 250, 206, 20);
        panelinfo.add(txtRotation);

        txtSalaire = new JTextField();
        txtSalaire.setColumns(10);
        txtSalaire.setBounds(130, 280, 206, 20);
        panelinfo.add(txtSalaire);

        txtSearch = new JTextField("numero");
        txtSearch.setColumns(10);
        txtSearch.setBounds(30, 60, 206, 20);
        paneledit.add(txtSearch);

        txtSearch.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                txtSearch.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });

        label = new JLabel("Nom : ");
        label.setBounds(20, 40, 60, 14);
        panelinfo.add(label);

        label_1 = new JLabel("Prenom : ");
        label_1.setBounds(20, 70, 60, 14);
        panelinfo.add(label_1);

        label_2 = new JLabel("Adresse : ");
        label_2.setBounds(20, 100, 60, 14);
        panelinfo.add(label_2);

        label_3 = new JLabel("Numero : ");
        label_3.setBounds(20, 130, 60, 14);
        panelinfo.add(label_3);

        label_4 = new JLabel("Telephone : ");
        label_4.setBounds(20, 160, 70, 14);
        panelinfo.add(label_4);

        label_5 = new JLabel("Specialite : ");
        label_5.setBounds(20, 190, 70, 14);
        panelinfo.add(label_5);

        label_6 = new JLabel("Code Service : ");
        label_6.setBounds(20, 220, 85, 14);
        panelinfo.add(label_6);

        label_7 = new JLabel("Rotation : ");
        label_7.setBounds(20, 250, 70, 14);
        panelinfo.add(label_7);

        label_8 = new JLabel("Salaire : ");
        label_8.setBounds(20, 280, 70, 14);
        panelinfo.add(label_8);
        
        JLabel labell =new JLabel("");
        labell.setBounds(20, 350, 300, 14);
        panelinfo.add(labell);

        verification = new JLabel();
        verification.setBounds(30, 110, 400, 14);
        paneledit.add(verification);

        txtNamebis = new JTextField();
        txtNamebis.setBounds(130, 40, 206, 20);
        txtPrenombis = new JTextField();
        txtPrenombis.setBounds(130, 70, 206, 20);
        txtAddressbis = new JTextField();
        txtAddressbis.setBounds(130, 100, 206, 20);
        txtNumbis = new JTextField();
        txtNumbis.setBounds(130, 130, 206, 20);
        txtTelbis = new JTextField();
        txtTelbis.setBounds(130, 160, 206, 20);
        txtSpecialitebis = new JTextField();
        txtSpecialitebis.setBounds(130, 190, 206, 20);
        txtCode_servicebis = new JTextField();
        txtCode_servicebis.setBounds(130, 220, 206, 20);
        txtRotationbis = new JTextField();
        txtRotationbis.setBounds(130, 250, 206, 20);
        txtSalairebis = new JTextField();
        txtSalairebis.setBounds(130, 280, 206, 20);

        labelbis = new JLabel("Nom : ");
        labelbis.setBounds(20, 40, 80, 14);
        labelbis_1 = new JLabel("Prenom : ");
        labelbis_1.setBounds(20, 70, 80, 14);
        labelbis_2 = new JLabel("Adresse : ");
        labelbis_2.setBounds(20, 100, 80, 14);
        labelbis_3 = new JLabel("Numero : ");
        labelbis_3.setBounds(20, 130, 80, 14);
        labelbis_4 = new JLabel("Telephone : ");
        labelbis_4.setBounds(20, 160, 80, 14);
        labelbis_5 = new JLabel("Specialite : ");
        labelbis_5.setBounds(20, 190, 80, 14);
        labelbis_6 = new JLabel("Code service : ");
        labelbis_6.setBounds(20, 220, 90, 14);
        labelbis_7 = new JLabel("Rotation : ");
        labelbis_7.setBounds(20, 250, 80, 14);
        labelbis_8 = new JLabel("Salaire : ");
        labelbis_8.setBounds(20, 280, 80, 14);
        frame2.add(labelbis);
        frame2.add(txtNamebis);
        frame2.add(labelbis_1);
        frame2.add(txtPrenombis);
        frame2.add(labelbis_2);
        frame2.add(txtAddressbis);
        frame2.add(labelbis_3);
        frame2.add(txtNumbis);
        frame2.add(labelbis_4);
        frame2.add(txtTelbis);
        frame2.add(labelbis_5);
        frame2.add(txtSpecialitebis);
        frame2.add(labelbis_6);
        frame2.add(txtCode_servicebis);
        frame2.add(labelbis_7);
        frame2.add(txtRotationbis);
        frame2.add(labelbis_8);
        frame2.add(txtSalairebis);

        //JList list = new JList();
        //list.setBounds(50, 300, 130, 130);
        //panelinfo.add(list);
        tblList = new JTable(new DefaultTableModel());
        //tblList = new JTable(new DefaultTableModel(null, new Object[]{"Numero", "Nom","Prenom","Adresse","Tel"}));
//tblList.setModel(co_bdd.afficherTable());
//tblModel = (DefaultTableModel) tblList.getModel();
        /*
        tblList = new JTable(tblModel){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //disallow the editing of any cell
            }
        };*/
        tblList.setBorder(new LineBorder(new Color(0, 0, 0)));
        //tblList.setBounds(55,400,600,200);
        JScrollPane scrollPane = new JScrollPane(tblList);
        scrollPane.setBounds(20, 150, 720, 350);
        paneledit.add(scrollPane);
//paneledit.add(new JScrollPane(tblList));
        //displayList();
        btnAdd.setVisible(false);
        btnSearch.setVisible(false);
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        //tblList.setEnabled(false);
        panelinfo.setVisible(false);
        paneledit.setVisible(false);

        txtSpecialite.setVisible(false);
        txtCode_service.setVisible(false);
        txtRotation.setVisible(false);
        txtSalaire.setVisible(false);

        label_5.setVisible(false);
        label_6.setVisible(false);
        label_7.setVisible(false);
        label_8.setVisible(false);

        btnAddPers.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                //if (btnAddPers.getText().equals("Add")) {
                    panelinfo.setVisible(true);
                    paneledit.setVisible(false);
                    btnEdit.setVisible(false);
                    btnDelete.setVisible(false);
                    btnAdd.setVisible(true);
                    btnSearch.setVisible(false);

                    String selectedPers = (String) combopers.getSelectedItem();

                    if (selectedPers == "infirmier") {
                        txtNum.setVisible(true);
                        txtName.setVisible(false);
                        txtPrenom.setVisible(false);
                        txtTel.setVisible(false);
                        txtAddress.setVisible(false);
                        txtCode_service.setVisible(true);
                        txtRotation.setVisible(true);
                        txtSalaire.setVisible(true);
                        txtSpecialite.setVisible(false);

                        label.setVisible(false);
                        label_1.setVisible(false);
                        label_2.setVisible(false);
                        label_3.setVisible(true);
                        label_4.setVisible(false);
                        label_5.setVisible(false);
                        label_6.setVisible(true);
                        label_7.setVisible(true);
                        label_8.setVisible(true);
                    } else if (selectedPers == "docteur") {
                        txtNum.setVisible(true);
                        txtName.setVisible(false);
                        txtPrenom.setVisible(false);
                        txtTel.setVisible(false);
                        txtAddress.setVisible(false);
                        txtSpecialite.setVisible(true);
                        txtCode_service.setVisible(false);
                        txtRotation.setVisible(false);
                        txtSalaire.setVisible(false);

                        label.setVisible(false);
                        label_1.setVisible(false);
                        label_2.setVisible(false);
                        label_3.setVisible(true);
                        label_4.setVisible(false);
                        label_5.setVisible(true);
                        label_6.setVisible(false);
                        label_7.setVisible(false);
                        label_8.setVisible(false);
                    } else if (selectedPers == "employe") {
                        label.setVisible(true);
                        label_1.setVisible(true);
                        label_2.setVisible(true);
                        label_3.setVisible(true);
                        label_4.setVisible(true);
                        txtCode_service.setVisible(false);
                        txtRotation.setVisible(false);
                        txtSalaire.setVisible(false);
                        txtSpecialite.setVisible(false);

                        label.setVisible(true);
                        label_1.setVisible(true);
                        label_2.setVisible(true);
                        label_3.setVisible(true);
                        label_4.setVisible(true);
                        label_5.setVisible(false);
                        label_6.setVisible(false);
                        label_7.setVisible(false);
                        label_8.setVisible(false);
                    }

                    /*tblList.setEnabled(true);
                } else {
                    btnAdd.setEnabled(false);
                    btnEdit.setEnabled(false);
                    btnDelete.setEnabled(false);
                    btnExit.setEnabled(false);
                    tblList.setEnabled(false);
                }*/
            }
        });

        btnEditDel.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent arg0) {
                panelinfo.setVisible(false);
                btnAdd.setVisible(false);
                //btnSearch.setVisible(true);
                btnEdit.setVisible(true);
                btnDelete.setVisible(true);
                String selectedPers = (String) combopers.getSelectedItem();

                //tblList = new JTable(new DefaultTableModel());
                //else if(selectedPers=="docteur") {tblList = new JTable(new DefaultTableModel(null, new Object[]{"Numero", "Specialite"}));}   
                //else if(selectedPers=="infirmier"){tblList = new JTable(new DefaultTableModel(null, new Object[]{"Numero", "Code service","Rotation","Salaire"}));}
                //JScrollPane scrollPane = new JScrollPane(tblList);
                //scrollPane.setBounds(20, 50, 720, 350);
                //paneledit.add(scrollPane);
                try {
                    tblList.setModel(co_bdd.afficherTable(selectedPers));
                } catch (SQLException ex) {
                    Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                }
                paneledit.setVisible(true);
            }

        });

        btnAdd.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent arg0) {

                //if (btnAdd.getText().equalsIgnoreCase("Add")) {
                /*btnAdd.setText("Save");
                btnEdit.setVisible(false);
                btnDelete.setVisible(false);
                btnExit.setEnabled(false);
                tblList.setEnabled(false);*/

                int a = JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter cette donnee?", "Message", JOptionPane.YES_NO_OPTION);

                //ArrayList <String> al=new ArrayList<>();
                if (a == 0) {
                    try {
                        String selectedPers = (String) combopers.getSelectedItem();
//on entre une requete SQL (cf le Cahier des charges)
                        int u=co_bdd.verify("SELECT count(*) FROM employe WHERE numero = "+txtNum.getText());
                        System.out.println(u);
                        if(u == 1){
                            if(selectedPers=="employe"){
                                co_bdd.executeUpdate("INSERT INTO " + selectedPers + " values ('" + txtNum.getText() + "','" + txtName.getText() + "','" + txtPrenom.getText() + "','" + txtAddress.getText() + "','" + txtTel.getText() + "')");
                            } else if(selectedPers=="docteur"){
                                co_bdd.executeUpdate("INSERT INTO " + selectedPers + " values ('" + txtNum.getText() + "','" + txtSpecialite.getText() + "')");
                            }else if(selectedPers =="infirmier"){
                                co_bdd.executeUpdate("INSERT INTO " + selectedPers + " values ('" + txtNum.getText() + "','" + txtCode_service.getText() + "','" + txtRotation.getText() + "','" + txtSalaire.getText() + "')");
                            }                           
                            labell.setText("Cette donnee a bien ete ajoutee");
                        }else if(u==0){
                            System.out.println("Cette donnee n'a pas pu etre ajoutee");
                            labell.setText("Cette donnee n'a pas pu etre ajoutee");
                        }
                    } catch (SQLException ex) {
                        System.out.println("erreur");
                        Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        tblList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                int selectedRowIndex = tblList.getSelectedRow();

                String selectedObject = (String) tblList.getModel().getValueAt(selectedRowIndex, 0);

                txtSearch.setText(selectedObject);

                /*String selectedPers = (String) combopers.getSelectedItem();
                try {
                    co_bdd.search(selectedPers, txtSearch.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            }
        });

        btnDelete.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent arg0) {
                int a = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette donnee?", "Message", JOptionPane.YES_NO_OPTION);
                String selectedPers = (String) combopers.getSelectedItem();

                if (a == 0) {
                    try {
                        co_bdd.executeUpdate("DELETE FROM " + selectedPers + " where numero='" + txtSearch.getText() + "'");
                        tblList.setModel(co_bdd.afficherTable(selectedPers));
                    } catch (SQLException ex) {
                        System.out.println("erreur");
                        Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        btnEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                String selectedPers = (String) combopers.getSelectedItem();

                int selectedRowIndex = tblList.getSelectedRow();

                if (selectedRowIndex < 0) {// && txtSearch.getText().equals("numero")) {
                    verification.setText("Vous devez selectionner une ligne");// ou entrer un numero");
                } else {

                    if (selectedPers == "employe") {
                        txtNumbis.setVisible(true);
                        txtNamebis.setVisible(true);
                        txtPrenombis.setVisible(true);
                        txtAddressbis.setVisible(true);
                        txtTelbis.setVisible(true);

                        txtSalairebis.setVisible(false);
                        txtRotationbis.setVisible(false);
                        txtCode_servicebis.setVisible(false);
                        txtSpecialitebis.setVisible(false);

                        labelbis.setVisible(true);
                        labelbis_1.setVisible(true);
                        labelbis_2.setVisible(true);
                        labelbis_3.setVisible(true);
                        labelbis_4.setVisible(true);
                        labelbis_5.setVisible(false);
                        labelbis_6.setVisible(false);
                        labelbis_7.setVisible(false);
                        labelbis_8.setVisible(false);

                        String selectedObject = (String) tblList.getModel().getValueAt(selectedRowIndex, 1);
                        txtNamebis.setText(selectedObject);

                        String selectedObject1 = (String) tblList.getModel().getValueAt(selectedRowIndex, 2);
                        txtPrenombis.setText(selectedObject1);

                        String selectedObject2 = (String) tblList.getModel().getValueAt(selectedRowIndex, 3);
                        txtAddressbis.setText(selectedObject2);

                        String selectedObject3 = (String) tblList.getModel().getValueAt(selectedRowIndex, 0);
                        txtNumbis.setText(selectedObject3);

                        String selectedObject4 = (String) tblList.getModel().getValueAt(selectedRowIndex, 4);
                        txtTelbis.setText(selectedObject4);

                    } else if (selectedPers == "infirmier") {
                        txtNumbis.setVisible(true);
                        txtNamebis.setVisible(false);
                        txtPrenombis.setVisible(false);
                        txtAddressbis.setVisible(false);
                        txtTelbis.setVisible(false);

                        txtSalairebis.setVisible(true);
                        txtRotationbis.setVisible(true);
                        txtCode_servicebis.setVisible(true);
                        txtSpecialitebis.setVisible(false);

                        labelbis.setVisible(false);
                        labelbis_1.setVisible(false);
                        labelbis_2.setVisible(false);
                        labelbis_3.setVisible(true);
                        labelbis_4.setVisible(false);
                        labelbis_5.setVisible(false);
                        labelbis_6.setVisible(true);
                        labelbis_7.setVisible(true);
                        labelbis_8.setVisible(true);

                        String selectedObject = (String) tblList.getModel().getValueAt(selectedRowIndex, 0);
                        txtNumbis.setText(selectedObject);

                        String selectedObject1 = (String) tblList.getModel().getValueAt(selectedRowIndex, 1);
                        txtCode_servicebis.setText(selectedObject1);

                        String selectedObject2 = (String) tblList.getModel().getValueAt(selectedRowIndex, 2);
                        txtRotationbis.setText(selectedObject2);

                        String selectedObject3 = (String) tblList.getModel().getValueAt(selectedRowIndex, 3);
                        txtSalairebis.setText(selectedObject3);

                    } else if (selectedPers == "docteur") {
                        txtNumbis.setVisible(true);
                        txtNamebis.setVisible(false);
                        txtPrenombis.setVisible(false);
                        txtAddressbis.setVisible(false);
                        txtTelbis.setVisible(false);

                        txtSalairebis.setVisible(false);
                        txtRotationbis.setVisible(false);
                        txtCode_servicebis.setVisible(false);
                        txtSpecialitebis.setVisible(true);

                        labelbis.setVisible(false);
                        labelbis_1.setVisible(false);
                        labelbis_2.setVisible(false);
                        labelbis_3.setVisible(true);
                        labelbis_4.setVisible(false);
                        labelbis_5.setVisible(true);
                        labelbis_6.setVisible(false);
                        labelbis_7.setVisible(false);
                        labelbis_8.setVisible(false);

                        String selectedObject = (String) tblList.getModel().getValueAt(selectedRowIndex, 0);
                        txtNumbis.setText(selectedObject);

                        String selectedObject1 = (String) tblList.getModel().getValueAt(selectedRowIndex, 1);
                        txtSpecialitebis.setText(selectedObject1);

                    }

                    frame2.setLocationRelativeTo(null);
                    frame2.setVisible(true);
                }
            }
        });

        btnEditedit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                int a = JOptionPane.showConfirmDialog(null, "Voulez-vous editer cette donnee?", "Message", JOptionPane.YES_NO_OPTION);
                String selectedPers = (String) combopers.getSelectedItem();
                verification.setText("");

                if (a == 0) {
                    try {
                        co_bdd.executeUpdate("UPDATE " + selectedPers + " set numero='" + txtNum.getText() + "',nom='" + txtName.getText() + "',prenom='" + txtPrenom.getText() + "',adresse='" + txtAddress.getText() + "',tel='" + txtTel.getText() + "' where numero='" + txtNum.getText() + "'");
                        tblList.setModel(co_bdd.afficherTable(selectedPers));

                    } catch (SQLException ex) {
                        System.out.println("erreur");
                        verification.setText("Nous n'avons pas pu editer cette ligne.");
                        //Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    frame2.setVisible(false);

                }
            }
        });
        
        btnExit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                frame.setVisible(false);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
