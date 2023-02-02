import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. adım Driver a kayıt
        Class.forName("org.postgresql.Driver");

        //2. adım database a baglanma
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane", "postgres", "899016");

        //3. adım statement olustur
        Statement st = con.createStatement();
        System.out.println("Connection basarili");
        /*
        Prepared Statement bir tane interface dir. birden cok kere calistirilabilen
        önceden derlenmiş bir sql kodunu temsil eder.
        Parametrelendirilmiş sql (query)) ile calisir. Bu sorguyu 0 veya daha fasla
        parametre ile kullanabiliris
         */

        //1. Orn: Prepared Statement kullanarak company adi IBM olan number_of_employees degerini 9999 olarak guncelleyin
        //1. adım  Prepared Statement query olustur, parametre yerine ? yasdık
        String sql1="update companies set number_of_employees=? where company=?";
        //2. adım Prepared Statement obje olustur
        PreparedStatement ps1=con.prepareStatement(sql1);
        //3. adım setInt(), setString() methotlari ile soru isaretleri yerine deger gönderme
        ps1.setInt(1,9998);
        ps1.setString(2,"IBM");
        //4. adım query i calistir
        int guncellenenSatirSayisi=ps1.executeUpdate();
        System.out.println(guncellenenSatirSayisi);

        String sql1a="select * from companies";
        ResultSet rs1=st.executeQuery(sql1a);
        while(rs1.next()){
            String d= rs1.getString("company_id");
            String e= rs1.getString("company");
            String f= rs1.getString("number_of_employees");
            System.out.println(d+"-"+e+"-"+f);
        }


        //1. Orn: Prepared Statement kullanarak company adi GOOGLE olan number_of_employees degerini 5555 olarak guncelleyin
        //3. adım setInt(), setString() methotlari ile soru isaretleri yerine deger gönderme
        ps1.setInt(1,5555);
        ps1.setString(2,"GOOGLE");
        //4. adım query i calistir
        int guncellenenSatirSayisi2=ps1.executeUpdate();
        System.out.println(guncellenenSatirSayisi2);

        String sql2a="select * from companies";
        ResultSet rs2=st.executeQuery(sql2a);
        while(rs2.next()){
            String d= rs2.getString("company_id");
            String e= rs2.getString("company");
            String f= rs2.getString("number_of_employees");
            System.out.println(d+"-"+e+"-"+f);
        }





        con.close();
        st.close();


    }
}
