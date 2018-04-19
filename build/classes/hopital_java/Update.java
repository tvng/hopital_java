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
 * @author Tao Tuong Vi
 */
public class Update extends JPanel{
    
    private Connexion co_bdd;
    private ControlerModule ctrl;
    
    private JButton btnAdd, btnEdit, btnDelete, btnExit;
    private JPanel panelinfo;
    private JTextField txtAddress, txtName, txtPrenom, txtTel, txtNum;
    
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
        
        btnAdd = new JButton("Add");
        
        btnAdd.setBounds(30, 250, 73, 30);
        frame.getContentPane().add(btnAdd);
        
        btnEdit = new JButton("Edit");
        
        btnEdit.setBounds(113, 250, 73, 30);
        frame.getContentPane().add(btnEdit);
        
        btnDelete = new JButton("Delete");
        
        btnDelete.setBounds(196, 250, 73, 30);
        frame.getContentPane().add(btnDelete);
        
        btnExit = new JButton("Exit");
        
        btnExit.setBounds(289, 250, 73, 30);
        frame.getContentPane().add(btnExit);
        
        panelinfo = new JPanel();
        panelinfo.setBorder(new TitledBorder(null, "information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelinfo.setBounds(10, 10, 760, 680);
        
        frame.getContentPane().add(panelinfo);
        panelinfo.setLayout(null);
        
        txtAddress = new JTextField();
        txtAddress.setColumns(10);
        txtAddress.setBounds(110,90, 206, 20);
        panelinfo.add(txtAddress);
        
        txtName = new JTextField();
        txtName.setColumns(10);
        txtName.setBounds(110,30, 206, 20);
        panelinfo.add(txtName);
        
        txtPrenom = new JTextField();
        txtPrenom.setColumns(10);
        txtPrenom.setBounds(110,60, 206, 20);
        panelinfo.add(txtPrenom);
        
        txtNum = new JTextField();
        txtNum.setColumns(10);
        txtNum.setBounds(110,120, 206, 20);
        panelinfo.add(txtNum);
        
        txtTel = new JTextField();
        txtTel.setColumns(10);
        txtTel.setBounds(110,150, 206, 20);
        panelinfo.add(txtTel);
                
        JLabel label = new JLabel("Nom : ");
        label.setBounds(20, 30, 46, 14);
        panelinfo.add(label);
        
        JLabel label_1 = new JLabel("Prenom : ");
        label_1.setBounds(20, 60, 46, 14);
        panelinfo.add(label_1);
        
        JLabel label_2 = new JLabel("Adresse : ");
        label_2.setBounds(20, 90, 46, 14);
        panelinfo.add(label_2);
        
        JLabel label_3 = new JLabel("Numero : ");
        label_3.setBounds(20, 120, 46, 14);
        panelinfo.add(label_3);
        
        JLabel label_4 = new JLabel("Telephone : ");
        label_4.setBounds(20, 150, 46, 14);
        panelinfo.add(label_4);
        
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
    scrollPane.setBounds(55, 400, 600, 200);
    panelinfo.add(scrollPane);
        //panelinfo.add(new JScrollPane(tblList));
        //displayList();
        
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
                    ArrayList <String> al=new ArrayList<>();
                    try {
                            //on entre une requete SQL (cf le Cahier des charges)
                            al=co_bdd.ajouterChamp("INSERT INTO employe values ('"+txtNum.getText()+"','"+txtName.getText()+"','"+txtPrenom.getText()+"','"+txtAddress.getText()+"','"+txtTel.getText()+"')");
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