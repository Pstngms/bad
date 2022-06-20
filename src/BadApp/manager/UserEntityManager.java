package BadApp.manager;

import BadApp.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEntityManager {
    public static String userRole(String login, String password) throws SQLException {
        try(Connection c = App.getConnection())
        {
            String sql = "SELECT * FROM User WHERE Login=? AND Password=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,login);
            ps.setString(2,password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return resultSet.getString("Role");
            }
            return null;
        }
    }
}
