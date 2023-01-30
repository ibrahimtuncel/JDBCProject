import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. adım Driver a kayıt
        Class.forName("org.postgresql.Driver");

        //2. adım database a baglanma
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane","postgres","899016");

        //3. adım statement olustur
        Statement st=con.createStatement();
        System.out.println("Connection basarili");

        //1. Orn: region id'si 1 olan "country name" degerlerini cagirin
        String sql1="SELECT country_name from countries where region_id=1";
        boolean sonuc=st.execute(sql1);
        System.out.println("sonuc = " + sonuc);

        //record-satır görmek icin executequerry() metotu kullanılır
        ResultSet rs1=st.executeQuery(sql1);
        System.out.println("rs1 = " + rs1);
        while (rs1.next()){
            rs1.getString(1); //veya  rs1.getString("country_name");
            System.out.println(rs1.getString(1));
        }
        //2. orn: "region_id'nin 2'den buyuk oldugu "country_id" ve "country_name" degerlerini cagirin
        String sql2="SELECT country_id,country_name from countries where region_id>2";
        boolean sonuc2=st.execute(sql2);
        System.out.println("sonuc2 = " + sonuc2);

        ResultSet rs2=st.executeQuery(sql2);
        System.out.println("rs2 = " + rs2);
        while(rs2.next()){
            String a= rs2.getString(1);//String a= rs2.getString("country_id");
            String b=rs2.getString(2);//String b= rs2.getString("country_name");
            String c= rs2.getString("country_name");
            System.out.println(a+"-"+b+" :"+c);
        }

        con.close();
        st.close();

    }
}
