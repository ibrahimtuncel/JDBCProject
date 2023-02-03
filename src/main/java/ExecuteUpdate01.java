import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. adım Driver a kayıt
        Class.forName("org.postgresql.Driver");

        //2. adım database a baglanma
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane", "postgres", "899016");

        //3. adım statement olustur
        Statement st = con.createStatement();
        System.out.println("Connection basarili");

        //1.ornek: number_of_employees degeri ortalama calisan sayisindan az olan number_of_employees degerlerini 16000 olarak UPDATE edin
     String sql1="update companies set number_of_employees='19000'\n" +
             "\twhere number_of_employees<(select avg (number_of_employees)from companies)";
     int updateSatirSayisi=st.executeUpdate(sql1);
        System.out.println("update edilen veri sayisi:"+updateSatirSayisi);

        String sql1a="select * from companies";
        ResultSet rs1=st.executeQuery(sql1a);
        while(rs1.next()){
            String d= rs1.getString("company_id");
            String e= rs1.getString("company");
            String f= rs1.getString("number_of_employees");
            System.out.println(d+"-"+e+"-"+f);
        }
        con.close();
        st.close();
    }

}
