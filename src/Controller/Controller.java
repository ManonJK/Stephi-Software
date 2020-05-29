package Controller;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Controller {

    public static ResultSet Info;

    public static Integer recupID(Integer ID){
        int message = 0;
        try {
            Connection.connect();
            Info = Connection.state.executeQuery("SELECT d.id FROM dependances_biens d JOIN biens b ON d.id_bien=b.id JOIN ventes v ON b.id=v.id_bien WHERE v.id = '"+ ID +"'");
            while(Info.next()){
                message = Info.getInt("d.id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
        return message;
    }

    public static String creaDep(int id_bien, String type, String superficie) {
        String message = "";
        try {
            Connection.connect();
            Info = Connection.state.executeQuery("INSERT INTO dependances_biens (id_bien, id_dependance, superficie) VALUES ('" +id_bien +"', '" +  type +"', '"+ superficie +"') ON DUPLICATE KEY UPDATE superficie = '"+ superficie + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
        return message;
    }


    public static Boolean Connexion(String email, String password){
        boolean message = false;
        try{
            Connection.connect();
            String BDDpasswd = "";
            Info = Connection.state.executeQuery ("SELECT password FROM users WHERE email= '" + email + "'");
            while (Info.next()){
                BDDpasswd = Info.getString("password");
            }
            System.out.println(BDDpasswd);
            System.out.println(password);

            if (BCrypt.checkpw(password, BDDpasswd)){
                System.out.println("It matches");
                message = true;}
            else {
                System.out.println("It does not match");
                message=false;
            }


        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de la lecture dans la BDD");
        }
        return message;
    }


    //Affichage nombre de ventes total
    public static String getVentesTot(){
        String message = "";
        try{
            Connection.connect();
            Info = Connection.state.executeQuery("SELECT COUNT(*) AS ventes FROM ventes where status = 'Vendu'");
            while (Info.next()){
                message = Info.getString("ventes");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de la lecture dans la BDD");
        }
        return message;
        // Dans la vue on construit la table de réservations comme ceci :
        // JTable table = new JTable(buildTableModel(rs));
    }

    //Affichage nombre de ventes trimestrel
    public static String getVentesTri(){
        String message = "";
        try{
            Connection.connect();
            Info = Connection.state.executeQuery("SELECT COUNT(*) AS ventes FROM ventes where status = 'Vendu' and ");
            while (Info.next()){
                message = Info.getString("ventes");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de la lecture dans la BDD");
        }
        return message;
        // Dans la vue on construit la table de réservations comme ceci :
        // JTable table = new JTable(buildTableModel(rs));
    }

    //Affichage nombre vendeurs
    public static String getSellersNb(){
        String message = "";
        try{
            Connection.connect();
            Info = Connection.state.executeQuery("SELECT COUNT(*) AS nb_sellers FROM users where id IN (SELECT id_user from biens)");
            while (Info.next()){
                message = Info.getString("nb_sellers");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de la lecture dans la BDD");
        }
        return message;
        // Dans la vue on construit la table de réservations comme ceci :
        // JTable table = new JTable(buildTableModel(rs));
    }

    //Affichage nombre de biens en vente
    public static String getSalesNb(){
        String message = "";
        try{
            Connection.connect();
            Info = Connection.state.executeQuery("SELECT COUNT(*) AS nb_sales FROM biens b JOIN ventes v ON b.id = v.id_bien WHERE v.status = 'Vendu'");
            while (Info.next()){
                message = Info.getString("nb_sales");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de la lecture dans la BDD");
        }
        return message;
        // Dans la vue on construit la table de réservations comme ceci :
        // JTable table = new JTable(buildTableModel(rs));
    }

    //Affichage des clients
    public static ResultSet getAllClients(){

        try{
            Connection.connect();
            Info = Connection.state.executeQuery("SELECT id, nom, prenom, email, phone FROM users where id_agent is not null");

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de la lecture dans la BDD");
        }

        return Info;
        // Dans la vue on construit la table de réservations comme ceci :
        // JTable table = new JTable(buildTableModel(rs));
    }

    //Affichage des biens en vente
    public static ResultSet getAllBiens() {

        try {
            Connection.connect();
            Info = Connection.state.executeQuery("SELECT b.id, CONCAT(u.nom,' ',u.prenom) AS propriétaire, t.titre as type, b.superficie, b.localisation, b.prix_vente prix FROM biens b JOIN users u ON b.id_user=u.id JOIN ventes v ON v.id_bien = b.id JOIN types t ON t.id=b.id_type WHERE v.status = 'En cours'");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de la lecture dans la BDD");
        }

        return Info;
        // Dans la vue on construit la table de réservations comme ceci :
    }

    //Affichage infos clients
    public static String getclientInfos(int id, String info){
        String message = "";
        try{
            Connection.connect();
            Info = Connection.state.executeQuery("SELECT * FROM users where id = '" + id + "'");
            while (Info.next()){
                message = Info.getString(info);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de la lecture dans la BDD");
        }
        return message;

    }

    //Modifier les infos d'un client
    public static String modifyClient(int id, String nom,String prenom,String mail,String phone,String birth){
        String message = "";
        try{
            Connection.connect();
            Connection.state.executeUpdate("UPDATE users set nom = '" + nom + "', prenom ='"+prenom+"', email ='"+mail+"', phone='"+phone+"', birth_date = '"+birth+"' where id = '" + id + "'");
            message = "Les informations du client ont bien été enregistrées";

        }catch (SQLException e) {
            e.printStackTrace();
            message = "Il y a eu une erreur, les informations n'ont pas pu être modifiées";
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de l'écriture dans la BDD");
            message = "Il y a eu une erreur, les informations n'ont pas pu être modifiées";
        }
        return message;
    }

    //Annuler une vente
    public static String cancelSale(int id){
        String message = "";
        try{
            Connection.connect();
            Connection.state.executeUpdate("UPDATE ventes set status = 'Annulée' where id = '" + id + "'");
            message = "La vente a bien été annulée";

        }catch (SQLException e) {
            e.printStackTrace();
            message = "Il y a eu une erreur, la vente n'a pas pu être annulée";
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de l'écriture dans la BDD");
            message = "Il y a eu une erreur, la vente n'a pas pu être annulée";
        }
        return message;
    }

    //Supprimer un client
    public static String del_client(int id){
        String message = "";
        try{
            Connection.connect();
            Connection.state.executeUpdate("UPDATE users set archive = true where id = '" + id + "'");
            Connection.state.executeUpdate("UPDATE ventes v join biens b ON v.id_bien = b.id set status = 'Annulée' where b.id_user = '" + id + "' AND v.status = 'En cours'");
            message = "Le compte de l'utilisateur a bien été supprimé";

        }catch (SQLException e) {
            e.printStackTrace();
            message = "Il y a eu une erreur, le compte n'a pas pu être archivé";
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de l'écriture dans la BDD");
            message = "Il y a eu une erreur, le compte n'a pas pu être archivé";
        }
        return message;
    }

    //Avoir la liste des biens d'une personne en particulier

    public static ResultSet getUserBiens(int id){
        try {
            Connection.connect();
            Info = Connection.state.executeQuery("SELECT v.id, t.titre as type, b.superficie, b.localisation, b.prix_vente prix, v.status FROM biens b JOIN users u ON b.id_user=u.id JOIN ventes v ON v.id_bien = b.id JOIN types t ON t.id=b.id_type WHERE u.id = '"+id+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de la lecture dans la BDD");
        }

        return Info;
        // Dans la vue on construit la table de réservations comme ceci :
    }

    //Récupérer les informations d'une vente
    public static String getSaleInfos(int id, String info){
        String message = "";
        try{
            Connection.connect();
            Info = Connection.state.executeQuery("SELECT b.*, t.titre as type FROM biens b join types t on b.id_type=t.id where b.id = '" + id + "'");
            while (Info.next()){
                message = Info.getString(info);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e + " - Erreur lors de la lecture dans la BDD");
        }
        return message;
    }







        //Méthode de construction pour JTable avec la BDD
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
}
