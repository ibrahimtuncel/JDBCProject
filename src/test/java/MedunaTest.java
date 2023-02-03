import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedunaTest {
    /*
Given user connects to database
    (hostname: medunna.com, DatabaseName: medunna_db, Username: medunna_user, Password: medunna_pass_987
When user sends the query to get the names of created_by column from "room" table
Then Assert that there are some rooms created by "john_doe"
And user closes the connection
 */

    @Test
    public void MedunaTest() throws SQLException {
        //Given user connects to the database
        Connection connection= JDBCutils.connectToDataBase("medunna.com","medunna_db", "medunna_user", "medunna_pass_987");
        Statement statement=JDBCutils.createStatement();

        //When user sends the query to get the names of created_by column from "room" table
        String sql="select created_by From room";
        ResultSet rs1=statement.executeQuery(sql);
        List<String> createdByName=new ArrayList<>();
        while(rs1.next()){
            createdByName.add(rs1.getString(1));
        }
        System.out.println("createdByName = " + createdByName);

        //Then Assert that there are some rooms created by "john_doe"
        Assert.assertTrue(createdByName.contains("john_doe"));

        //And user closes the connection
        JDBCutils.closeConnectionVeStatement();
    }
}
