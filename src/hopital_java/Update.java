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
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
/**
 *
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
 */
public class Update extends JPanel{
    
    private Connexion co_bdd;
    private ControlerModule ctrl;
    
    private JButton btnAddPers, btnAdd, btnEdit, btnDelete, btnExit;
    private JPanel panelinfo, panelpers, paneledit;
    private JTextField txtAddress, txtName, txtPrenom, txtTel, txtNum;
    private JTextField txtSpecialite;
    private JTextField txtCode_service, txtRotation, txtSalaire;
    private JCheckBox employe, infirmier, docteur;
    private JComboBox cboTable;
    
    private DefaultTableModel tblModel;
    JTable tblList;
    
    public Update(Connexion _co)
    {
        //JLabel debug=new JLabel ("update (label a supprimer)");
        //add(debug);
        co_bdd=_co;
        affichage();
        //Dans ce module on veut ajouter, modifier et supprimer dans la BDD
    
    }
    
    public void affichage(){
        JFrame frame=new JFrame("Edit");
        frame.setBounds(100, 100, 800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        btnAddPers = new JButton("Add");
        
        btnAddPers.setBounds(200, 45, 73, 30);
        frame.getContentPane().add(btnAddPers);
               
        btnAdd = new JButton("Add");
        
        btnAdd.setBounds(400, 300, 73, 30);
        frame.getContentPane().add(btnAdd);
        
        btnEdit = new JButton("Edit");
        
        btnEdit.setBounds(300, 45, 73, 30);
        frame.getContentPane().add(btnEdit);
        
        btnDelete = new JButton("Delete");
        
        btnDelete.setBounds(400, 45, 73, 30);
        frame.getContentPane().add(btnDelete);
        
        btnExit = new JButton("Exit");
        
        btnExit.setBounds(500, 45, 73, 30);
        frame.getContentPane().add(btnExit);
        
        panelpers = new JPanel();
        panelpers.setBorder(new TitledBorder(null, "Personne", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelpers.setBounds(10, 10, 760, 100);
        
        frame.getContentPane().add(panelpers);
        panelpers.setLayout(null);
        
        panelinfo = new JPanel();
        panelinfo.setBorder(new TitledBorder(null, "information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelinfo.setBounds(10, 110, 760, 680);
        
        frame.getContentPane().add(panelinfo);
        panelinfo.setLayout(null);
        
        paneledit = new JPanel();
        paneledit.setBorder(new TitledBorder(null, "Edit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        paneledit.setBounds(10, 110, 760, 680);
        
        frame.getContentPane().add(paneledit);
        paneledit.setLayout(null);
                
        String[] personne = new String[] {"employe", "docteur","infirmier"};
 
        JComboBox<String> combopers = new JComboBox<>(personne);
        combopers.setBounds(30, 40, 100, 20);
 
        // add to the parent container (e.g. a JFrame):
        panelpers.add(combopers);
 
        // get the selected item:
        String selectedPers = (String) combopers.getSelectedItem();
        System.out.println("You seleted the book: " + selectedPers);
        
        txtName = new JTextField();
        txtName.setColumns(10);
        txtName.setBounds(130,40, 206, 20);
        panelinfo.add(txtName);
        
        txtPrenom = new JTextField();
        txtPrenom.setColumns(10);
        txtPrenom.setBounds(130,70, 206, 20);
        panelinfo.add(txtPrenom);
        
        txtAddress = new JTextField();
        txtAddress.setColumns(10);
        txtAddress.setBounds(130,100, 206, 20);
        panelinfo.add(txtAddress);
        
        txtNum = new JTextField();
        txtNum.setColumns(10);
        txtNum.setBounds(130,130, 206, 20);
        panelinfo.add(txtNum);
        
        txtTel = new JTextField();
        txtTel.setColumns(10);
        txtTel.setBounds(130,160, 206, 20);
        panelinfo.add(txtTel);
        
        txtSpecialite = new JTextField();
        txtSpecialite.setColumns(10);
        txtSpecialite.setBounds(130,190, 206, 20);
        panelinfo.add(txtSpecialite);
        
        txtCode_service = new JTextField();
        txtCode_service.setColumns(10);
        txtCode_service.setBounds(130,220, 206, 20);
        panelinfo.add(txtCode_service);
        
        txtRotation = new JTextField();
        txtRotation.setColumns(10);
        txtRotation.setBounds(130,250, 206, 20);
        panelinfo.add(txtRotation);
        
        txtSalaire = new JTextField();
        txtSalaire.setColumns(10);
        txtSalaire.setBounds(130,280, 206, 20);
        panelinfo.add(txtSalaire);
                
        JLabel label = new JLabel("Nom : ");
        label.setBounds(20, 40, 60, 14);
        panelinfo.add(label);
        
        JLabel label_1 = new JLabel("Prenom : ");
        label_1.setBounds(20, 70, 60, 14);
        panelinfo.add(label_1);
        
        JLabel label_2 = new JLabel("Adresse : ");
        label_2.setBounds(20, 100, 60, 14);
        panelinfo.add(label_2);
        
        JLabel label_3 = new JLabel("Numero : ");
        label_3.setBounds(20, 130, 60, 14);
        panelinfo.add(label_3);
        
        JLabel label_4 = new JLabel("Telephone : ");
        label_4.setBounds(20, 160, 70, 14);
        panelinfo.add(label_4);
        
        JLabel label_5 = new JLabel("Specialite : ");
        label_5.setBounds(20, 190, 70, 14);
        panelinfo.add(label_5);
        
        JLabel label_6 = new JLabel("Code Service : ");
        label_6.setBounds(20, 220, 85, 14);
        panelinfo.add(label_6);
        
        JLabel label_7 = new JLabel("Rotation : ");
        label_7.setBounds(20, 250, 70, 14);
        panelinfo.add(label_7);
        
        JLabel label_8 = new JLabel("Salaire : ");
        label_8.setBounds(20, 280, 70, 14);
        panelinfo.add(label_8);
        
        //JList list = new JList();
        //list.setBounds(50, 300, 130, 130);
        //panelinfo.add(list);
        
        tblList = new JTable(new DefaultTableModel(null, new Object[]{"Nom","Prenom","Adresse","Numero","Telephone"}));
        tblModel = (DefaultTableModel) tblList.getModel();
        /*
        tblList = new JTable(tblModel){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false; //disallow the editing of any cell
            }
        };*/
        
        //tblList.setBorder(new LineBorder(new Color(0,0,0)));
        //tblList.setBounds(55,400,600,200);
        JScrollPane scrollPane = new JScrollPane(tblList);
        scrollPane.setBounds(55, 300, 600, 200);
        paneledit.add(scrollPane);
        //paneledit.add(new JScrollPane(tblList));
        //displayList();
        btnAdd.setVisible(false);
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
            public void mouseClicked(MouseEvent arg0){
                if(btnAddPers.getText().equals("Add")){
                    panelinfo.setVisible(true);
                    btnAdd.setVisible(true);
                    String selectedPers = (String) combopers.getSelectedItem();
                    
                    if(selectedPers=="infirmier"){
                        txtCode_service.setVisible(true);
                        txtRotation.setVisible(true);
                        txtSalaire.setVisible(true);
                                                
                        label_6.setVisible(true);
                        label_7.setVisible(true);
                        label_8.setVisible(true);
                    }
                    
                    if(selectedPers=="docteur"){
                        txtSpecialite.setVisible(true);                      
                        label_5.setVisible(true);
                    }
                    
                                
                    tblList.setEnabled(true);    
                }else{
                    btnAdd.setEnabled(false);
                    btnEdit.setEnabled(false);
                    btnDelete.setEnabled(false);
                    btnExit.setEnabled(false);
                    tblList.setEnabled(false);
                }
            }    
        });
        
        btnEdit.addMouseListener(new MouseAdapter() {
            
            public void mouseClicked(MouseEvent arg0){
                paneledit.setVisible(true);
            }
            
        });
        btnAdd.addMouseListener(new MouseAdapter() {
            
            public void mouseClicked(MouseEvent arg0){
                if(btnAdd.getText().equalsIgnoreCase("Add")){
                    btnAdd.setText("Save");
                    btnEdit.setText("Cancel");
                    btnDelete.setEnabled(false);
                    btnExit.setEnabled(false);
                    tblList.setEnabled(false);
                }
                else{
                    //ArrayList <String> al=new ArrayList<>();
                    try {
                            System.out.println("blub");
//on entre une requete SQL (cf le Cahier des charges)
                            co_bdd.ajouterChamp("INSERT INTO employe values ('"+txtNum.getText()+"','"+txtName.getText()+"','"+txtPrenom.getText()+"','"+txtAddress.getText()+"','"+txtTel.getText()+"')");
                        } 
                    catch (SQLException ex) {
                    System.out.println("erreur");
                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}