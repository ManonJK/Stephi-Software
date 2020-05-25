package Controller;

import java.sql.DriverManager;
import java.sql.Statement;

public class Connection {
    public static Statement state;
    public static java.sql.Connection con;
    public static void connect() {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stephi_place?useSSL=false","root",""); //3306 est le port par défaut de mysql / 3308 est le port par defaut de MariaDB
            state = con.createStatement();

        }catch(Exception e){ System.out.println(e);}


    }
}
