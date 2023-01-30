import java.sql.*;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    //1. adım Driver a kayıt
        Class.forName("org.postgresql.Driver");

        //2. adım database a baglanma
        Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane","postgres","899016");

        //3. adım statement olustur
        Statement st=con.createStatement();
        System.out.println("Connection basarili");


        //4. adım RessultSet sorgu querry olustur
        //1: Ornek: "workers" adinda bir table olusturup "worker_id, worker_name, worker_salary" sutunlarini ekleyin
        boolean sql1=st.execute("Create Table workers(worker_id VARCHAR(20),worker_name VARCHAR(20), worker_salary INT)");
       System.out.println("sql1 = " + sql1);//false döner cunku data cagirmadık
        /*
        execute() metotunu DDL- data definition language-(create,drop,alter table)data tanımlama yaparken
        ve DQL data querry language (select) icin kullanılır.
        i) eger execute() metotu DDL icin kullanılırsa "false" return eder
        ii)eger execute() metotu DQL icin kullanılırsa sonuc veri alınırsa true döner
         */

        //2. Ornek: Table'a worker_address sutunu ekleyerek alter yapin
        String sql2="Alter Table workers Add worker_adress VARCHAR(80)";
        boolean sql2a=st.execute(sql2);
        System.out.println("sql2a = " + sql2a);

        //3.Ornek: workers table'ini silin
        String sql3="Drop Table workers";
        boolean sql3a=st.execute(sql3);
        System.out.println("sql3a = " + sql3a);

        //5. adım : baglantı ve statement i kapat
        con.close();
        st.close();
    }
}
