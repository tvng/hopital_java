/**
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
*/

/* !!!!!!!!!!   -> Notes : CONTIENT LE MAIN */
/* !!!!!!!!!! & oubliez pas la javadoc */
//aussi j'ai mis tous les attributs machin en anglais , sauf les commentaires pour expliquer


package hopital_java;

import javax.swing.*; //librairie graphique



public class Hospital{

    
    /** main    */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //On instancie une fenetre "JFrame" de la classe "Graphic" qui contiendra tout
        Graphic gFrame=new Graphic();
        
         //le programme se ferme qd on quitte la fenetre
        gFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         //on affiche la fenetre, sur laquelle tout va se dérouler
        gFrame.setVisible (true);
        
    }
    
}
