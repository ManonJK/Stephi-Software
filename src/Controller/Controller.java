package Controller;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class Controller {

    public static ResultSet Info;

    //Affichage nombre de ventes total
    public static String getVentesTot(){
        String message = "";
        try{
            Connection.connect();
            Info = Connection.state.executeQuery("SELECT COUNT(*) AS ventes FROM ventes where status = 'vendu'");
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
            Info = Connection.state.executeQuery("SELECT COUNT(*) AS ventes FROM ventes where status = 'vendu' and ");
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
            Info = Connection.state.executeQuery("SELECT id, nom, prenom, mail, phone FROM users WHERE archivé = false");

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
            Info = Connection.state.executeQuery("SELECT b.id, CONCAT(u.nom,' ',u.prenom) AS propriétaire, b.type, b.superficie, b.localisation, b.prix_vente prix FROM biens b JOIN users u ON b.id_user=u.id JOIN ventes v ON v.id_bien = b.id WHERE v.status = 'En cours'");

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
    public static String modifyClient(int id, String nom,String prenom,String mail,String adresse,String phone,String birth){
        String message = "";
        try{
            Connection.connect();
            Connection.state.executeUpdate("UPDATE users set nom = '" + nom + "', prenom ='"+prenom+"', mail ='"+mail+"', adresse='"+adresse+"', phone='"+phone+"', birth_date = '"+birth+"' where id = '" + id + "'");
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
            Connection.state.executeUpdate("UPDATE users set archivé = true where id = '" + id + "'");
            Connection.state.executeUpdate("UPDATE ventes v join biens b ON v.id_bien = b.id set status = 'Annulée' where b.id_user = '" + id + "'");
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
            Info = Connection.state.executeQuery("SELECT v.id, b.type, b.superficie, b.localisation, b.prix_vente prix, v.status FROM biens b JOIN users u ON b.id_user=u.id JOIN ventes v ON v.id_bien = b.id WHERE u.id = '"+id+"'");

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
            Info = Connection.state.executeQuery("SELECT * FROM biens where id = '" + id + "'");
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
