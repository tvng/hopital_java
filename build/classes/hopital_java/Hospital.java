/**
 * @author DUCRET Amandine, PAGES Hermance, TAO Tuong Vi
*/

/* -> Notes par tuong vi : ce .java CONTIENT LE MAIN */

/* !!!!!!!!!! & oubliez pas la javadoc */
//j'ai mis tous les attributs/methodes anglais , sauf les commentaires pour expliquer

/*

pour l'instant la connection distante au serveur ECE on s'en occupe pas car le serveur est saturé.
on fait alors une connexion locale avec une bdd "hopital" sur WAMP phpmyadmin. 
Cette bdd est fournie dans le .zip sur campus c'est hopital.sql

 guide ici : http://campus.ece.fr/pluginfile.php/177623/mod_resource/content/1/Guide%20TP%20%20MySQL.pdf

*/

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
