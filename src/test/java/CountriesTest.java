import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountriesTest {
    /*
Given user connects to the database
When user sends the query to get the region ids from "countries" table
Then verify that the number of region ids greater than 1 is 17.
And user closes the connection
 */
    @Test
    public  void countrytest() throws SQLException {
        //Given user connects to the database
        Connection connection= JDBCutils.connectToDataBase("localhost","Arcane", "postgres", "899016");
        Statement statement=JDBCutils.createStatement();
        //When user sends the query to get the region ids from "countries" table
        String sql="select region_id from countries";
        ResultSet resultSet=statement.executeQuery(sql);

        List<Integer>ids=new ArrayList<>();
        while(resultSet.next()){
            ids.add(resultSet.getInt(1));
        }
        System.out.println("ids = " + ids);

        //Then verify that the number of region ids greater than 1 is 17.
        List<Integer>birdenBuyukId=new ArrayList<>();
        for(Integer newEleman:ids){
            if(newEleman>1){
                birdenBuyukId.add(newEleman);
            }
        }
        System.out.println("birdenBuyukId = " + birdenBuyukId);
        Assert.assertEquals(17,birdenBuyukId.size());
        System.out.println(birdenBuyukId.size());

        //And user closes the connection
        JDBCutils.closeConnectionVeStatement();
    }
}
