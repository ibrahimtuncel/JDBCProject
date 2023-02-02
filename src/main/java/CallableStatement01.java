import java.math.BigDecimal;
import java.sql.*;

public class CallableStatement01 {
    /*
    Java da metotlar return type sahibi olsada olmasada methot olarak adlandirilir
        sql de data return ediliyorsa "function" yoksa "procedure" denilir
    */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. adım Driver a kayıt
        Class.forName("org.postgresql.Driver");
        //2. adım database a baglanma
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane", "postgres", "899016");
        //3. adım statement olustur
        Statement st = con.createStatement();
        System.out.println("Connection basarili");

        //Callable Statement ile function cagirmayi parametrelendiriris
        //1. adım function kodunu yas
        String sql1="create or replace function toplamaF(x Numeric,y Numeric)\n" +
                "Returns Numeric language plpgsql\n" +
                "As\n" +
                "$$\n" +
                "Begin Return x+y;\n" +
                "End\n" +
                "$$";
           //create or replace function>>> fonksiyomu olustur var ise değiştir.,
           //Returns Numeric language plpgsql>>> prodocure language postgre sql
        //2. adım fonksiyonu calistir
        st.execute(sql1);
        //3. adım funtion u cagir
        CallableStatement cs1=con.prepareCall("{?=call toplamaF(?,?)}");//ilk ?-parametre return type
        //4. adım Return icin registerOutParameter(),parametreler icin set()... methotu kullan
        cs1.registerOutParameter(1,Types.NUMERIC);
        cs1.setInt(2,6);
        cs1.setInt(3,8);
        //5. adım execute () ile CallableStatement i calsitir.
        cs1.execute();
        //6.adım sonucu cagirmak icin return data type bakılır.
        BigDecimal toplam=cs1.getBigDecimal(1);
        System.out.println("toplam:"+toplam);


        //2.Ornek: Koninin hacmini hesaplayan bir function yazin // konu hacmi 3.14*r*r*h/3
        //1. adım function kodunu yas
        String sql2="create or replace function koniHacmiF(r Numeric,h Numeric)\n" +
                "Returns Numeric language plpgsql\n" +
                "As\n" +
                "$$\n" +
                "Begin Return 3.14*r*r*h/3;\n" +
                "End\n" +
                "$$";
        //2. adım fonksiyonu calistir
        st.execute(sql2);
        //3. adım funtion u cagir
        CallableStatement cs2=con.prepareCall("{?=call koniHacmiF(?,?)}");//ilk ?-parametre return type

        //4. adım Return icin registerOutParameter(),parametreler icin set()... methotu kullan
        cs2.registerOutParameter(1,Types.NUMERIC);
        cs2.setInt(2,4);
        cs2.setInt(3,10);
        //5. adım execute () ile CallableStatement i calsitir.
        cs2.execute();
        //6.adım sonucu cagirmak icin return data type bakılır.
        BigDecimal sonuc=cs2.getBigDecimal(1);
        System.out.println("toplam:"+sonuc);
        //System.out.println("%.4f",sonuc);

    }

}
