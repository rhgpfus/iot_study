package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import common.DBCon;

public class UserServiceImpl implements UserService {

	@Override
	public Map<String, String> getUserLogin(String id, String pwd) {
		String result = "로긴실패";
		Connection con;
		try {
			DBCon db = new DBCon();
			con = db.getCon();
			String sql = "select * from user where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(pwd.equals(rs.getString("pwd"))){
					Map<String, String> m = new HashMap<String, String>();
					m.put("id", id);
					m.put("user_no", rs.getString("user_no"));
					m.put("name", rs.getString("name"));
					m.put("hobby", rs.getString("hobby"));
					m.put("admin", rs.getString("admin"));
					return m;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertUser(Map<String, String> hm) {
		String sql = "insert into user(id,pwd,name,hobby)";
		sql +=" values(?,?,?,?)";
		Connection con;
		try {
			DBCon db = new DBCon();
			con = db.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, hm.get("id"));
			ps.setString(2, hm.get("pwd"));
			ps.setString(3, hm.get("name"));
			ps.setString(4, hm.get("hobby"));
			int rCnt = ps.executeUpdate();
			return rCnt;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateUser(Map<String, String> hm) {
		String sql = "update user" + 
				" set id=?,pwd=?,name=?,hobby=?" + 
				" where user_no=?";
		Connection con;
		try {
			DBCon db = new DBCon();
			con = db.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, hm.get("id"));
			ps.setString(2, hm.get("pwd"));
			ps.setString(3, hm.get("name"));
			ps.setString(4, hm.get("hobby"));
			ps.setString(5, hm.get("user_no"));
			int rCnt = ps.executeUpdate();
			return rCnt;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteUser(Map<String, String> hm) {
		String sql = "delete from user where user_no=?";
		Connection con;
		try {
			DBCon db = new DBCon();
			con = db.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, hm.get("user_no"));
			int rCnt = ps.executeUpdate();
			return rCnt;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
