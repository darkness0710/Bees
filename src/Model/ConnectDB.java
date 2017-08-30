package Model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

/* HaiNT47 */

public class ConnectDB {
	
	public ConnectDB(){
		
	}

	 public static Connection Connect() throws ClassNotFoundException, SQLException {
		 	Connection conn = null;
	        String userName = "sa";
	        String password = "123456";
	        String url = "jdbc:sqlserver://localhost:1433;databaseName=Fsoft_final_bee;";
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        conn = java.sql.DriverManager.getConnection(url, userName, password);
	        return conn;
	 }
	
}
