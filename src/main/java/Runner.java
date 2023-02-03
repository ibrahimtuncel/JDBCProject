import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {
        Connection con= JDBCutils.connectToDataBase("localhost","Arcane", "postgres", "899016");
        Statement statement=JDBCutils.createStatement();

        //JDBCutils.execute("Create Table students(name VARCHAR(20),okul_no INT, address VARCHAR(80))");
       // JDBCutils.execute("Create Table okul (okul_name VARCHAR(20),okul_kapasitesi INT, address VARCHAR(80))");

        //4. Adim: Query (sorgu) olustur/calistir
//        JdbcUtils.execute("Create Table students(name VARCHAR(20),okul_no INT, address VARCHAR(80))");

//        JdbcUtils.execute("Create Table okul (okul_name VARCHAR(20),okul_kapasitesi INT, address VARCHAR(80))");


        JDBCutils.createTable("mudur1", "name VARCHAR(20)","ili INT ", "ogretmen_ismi VARCHAR(80)", "okul_no INT");
        JDBCutils.closeConnectionVeStatement();


    }
}
