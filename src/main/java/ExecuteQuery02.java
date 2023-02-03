import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. adım Driver a kayıt
        Class.forName("org.postgresql.Driver");

        //2. adım database a baglanma
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arcane", "postgres", "899016");

        //3. adım statement olustur
        Statement st = con.createStatement();
        System.out.println("Connection basarili");

//1. ornek: companies tablosundan en yuksek ikinci number_of_employees degeri olanin company ve number_of_employees degerlerini yazdirin
    String sql1="Select * From companies Order By number_of_employees DESC Offset 1 Row Fetch Next Row Only";
        ResultSet rs1=st.executeQuery(sql1);
        while (rs1.next()){
        String a= rs1.getString(1);
        String b= rs1.getString(2);
        String c= rs1.getString(3);
            System.out.println(a+"-"+b+"-"+c);
        }
        System.out.println("--------------------------");
        String sql2="SELECT company_id, company, number_of_employees\n" +
                "               FROM companies\n" +
                "               Where number_of_employees =\n" +
                "               (Select Max(number_of_employees)\n" +
                "             \tFrom companies\n" +
                "                Where number_of_employees <\n" +
                "                (Select Max(number_of_employees)\n" +
                "                From companies))";

        ResultSet rs2=st.executeQuery(sql2);
        while (rs2.next()){
            String d= rs2.getString("company_id");
            String e= rs2.getString("company");
            String f= rs2.getString("number_of_employees");
            System.out.println(d+"-"+e+"-"+f);
        }
        con.close();
        st.close();
    }
    }
