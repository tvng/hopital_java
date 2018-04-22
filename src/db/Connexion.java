/*
 *  La classe Connexion permet de se connecter, grâce à ses 2 constructeurs, à la BDD
locale (localhost) et distante du serveur de l'ECE https://sql.ece.fr/ via le tunnel SSH. Elle récupère ou met à jour les
données à la demande des modules de Recherche, de Mise à jour et de Génération et Historisation des Activités. Ses
méthodes permettent chacune d’exécuter une requête SQL de type String passée en paramètre.
 * 
 */
package db;

/*
 * Librairies importées
 */
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * Connexion a votre BDD locale ou à distance sur le serveur de l'ECE via le tunnel SSH
 * 
 * @author segado
 * 

 */
public class Connexion {

    /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat
     * requete
     */
    private Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    
    private PreparedStatement ps;
    
    /**
     * ArrayList public pour les tables
     */
    public ArrayList<String> tables = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de sélection
     */
    public ArrayList<String> requetes = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de MAJ
     */
    public ArrayList<String> requetesMaj = new ArrayList<>();

    /**
     * Constructeur avec 3 paramètres : nom, login et password de la BDD locale
     *
     * @param nameDatabase
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connexion(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost/" + nameDatabase;

        //création d'une connexion JDBC à la base 
        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
    }

    /**
     * Constructeur avec 4 paramètres : username et password ECE, login et
     * password de la BDD à distance sur le serveur de l'ECE
     * @param usernameECE
     * @param passwordECE
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connexion(String usernameECE, String passwordECE, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // Connexion via le tunnel SSH avec le username et le password ECE
        SSHTunnel ssh = new SSHTunnel(usernameECE, passwordECE);

        if (ssh.connect()) {
            System.out.println("Connexion reussie");

            // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
            String urlDatabase = "jdbc:mysql://localhost:3305/" + usernameECE;

            //création d'une connexion JDBC à la base
            conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

            // création d'un ordre SQL (statement)
            stmt = conn.createStatement();

        }
    }

    /**
     * Méthode qui ajoute la table en parametre dans son ArrayList
     *
     * @param table
     */
    public void ajouterTable(String table) {
        tables.add(table);
    }

    /**
     * Méthode qui ajoute la requete de selection en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequete(String requete) {
        requetes.add(requete);
    }

    /**
     * Méthode qui ajoute la requete de MAJ en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequeteMaj(String requete) {
        requetesMaj.add(requete);
    }

    /**
     * Méthode qui retourne l'ArrayList des champs de la table en parametre
     *
     * @param table
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsTable(String table) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = champs + " " + rsetMeta.getColumnLabel(i + 1);
        }

        // ajouter un "\n" à la ligne des champs
        champs = champs + "\n"; //************************** selon les bons conseils de segado faut enlever le \n mais à voir **********

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);

        // Retourner l'ArrayList
        return liste;
    }

    /**
     * Methode qui retourne l'ArrayList des champs de la requete en parametre
     * @param requete
     * @return 
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsRequete(String requete) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<String>();

        // tant qu'il reste une ligne 
        while (rset.next()) {
            String champs;
            champs = rset.getString(1); // ajouter premier champ

            // Concatener les champs de la ligne separes par ,
            for (int i = 1; i < nbColonne; i++) {
                champs = champs + "," + rset.getString(i + 1);
            }

            // ajouter un "\n" à la ligne des champs
    //        champs = champs + "\n";  /************************ askip enlever le \n pourrait sauver des vies */

            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs);
        }

        // Retourner l'ArrayList
        return liste;
    }

    /**
     * Méthode qui execute une requete de MAJ en parametre
     * @param requeteMaj
     * @throws java.sql.SQLException
     */
    public void executeUpdate(String requeteMaj) throws SQLException {
        stmt.executeUpdate(requeteMaj);
    }
    /*
    public void ajouterChamp(String requete) throws SQLException {
        System.out.println("blob");
        ps=conn.prepareStatement(requete);
        ps.executeUpdate();
        
    }*/
    
    public DefaultTableModel afficherTable(String table) throws SQLException {
        DefaultTableModel model = new DefaultTableModel(new String[]{}, 0);
        //String[] columnNames = new String[]{};
        if (table == "employe") {
            model.addColumn("numero");
            model.addColumn("nom");
            model.addColumn("prenom");
            model.addColumn("adresse");
            model.addColumn("telephone");
            //columnNames.add{"numero", "nom", "prenom", "adresse", "telephone"};
        } else if (table == "docteur") {
            model.addColumn("numero");
            model.addColumn("specialite");
            //model = new DefaultTableModel(new String[]{"Numero", "Specialite"}, 0);
        } else if (table == "infirmier") {
            model.addColumn("numero");
            model.addColumn("code_service");
            model.addColumn("rotation");
            model.addColumn("salaire");
            //model = new DefaultTableModel(new String[]{"Numero", "Code service", "Rotation", "Salaire"}, 0);
        }
        //model.setColumnIdentifiers(columnNames);
        //String sqll="SELECT * FROM employe";
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);

        if (table == "employe") {
            while (rs.next()) {
                String d = rs.getString("numero");
                String e = rs.getString("nom");
                String f = rs.getString("prenom");
                String g = rs.getString("adresse");
                String h = rs.getString("tel");
                model.addRow(new Object[]{d, e, f, g, h});
            }
        } else if (table == "docteur") {
            while (rs.next()) {
                String d = rs.getString("numero");
                String e = rs.getString("specialite");
                model.addRow(new Object[]{d, e});
            }
        } else if (table == "infirmier") {
            while (rs.next()) {
                String d = rs.getString("numero");
                String e = rs.getString("code_service");
                String f = rs.getString("rotation");
                String h = rs.getString("salaire");
                model.addRow(new Object[]{d, e, f, h});
            }
        }

        return model;
    }
    
}
