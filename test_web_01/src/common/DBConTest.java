package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConTest {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/iot_study";
		String id = "root";
		String pwd = "tlqdnjs1";
		Connection con;
		Statement st;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, id, pwd);
			st = con.createStatement();
			String sql = "select * from user";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String user_no = rs.getString("user_no");
				String id1 = rs.getString("id");
				String password = rs.getString("password");
				System.out.println(" 번호 : " + user_no + " 아이디 : " + id1 + " 비밀번호 : " + password);
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
