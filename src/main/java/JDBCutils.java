import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCutils {
    public static void main(String[] args) {
        connectToDatabase();
        createStatement();
    }
    private static Connection connection;
    private  static Statement statement;
    //1. adım Driver a kayıt
    //2. adım database a baglanma
    public static  Connection connectToDatabase(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane", "postgres", "899016");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(connection!=null){
            System.out.println("connection basarili");
        }else{
            System.out.println("connection basarisis");
        }
        return connection;
    }
    public static Statement createStatement(){
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(connection!=null){
            System.out.println("st connection basarili");
        }else{
            System.out.println("st connection basarisis");
        }
        return statement;
    }



}
