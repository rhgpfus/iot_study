package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.DBConnector;
import service.UserService;

public class UserServiceImpl implements UserService {


	@Override
	public Map<String, String> loginUser(Map<String, String>  hm) {
		Connection con;
		PreparedStatement ps;
		Map<String, String> resultMap = new HashMap<String, String>();
		String result=hm.get("id") + "는 없는 아이디 입니다.";
		try {
			con = DBConnector.getCon();
			System.out.println("연결 성공");
			String sql = "select * from user";
			sql += " where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1 , hm.get("id"));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(hm.get("pwd").equals(rs.getString("password"))){
					resultMap.put("id", rs.getString("id"));
					resultMap.put("name", rs.getString("name"));
					resultMap.put("hobby", rs.getString("hobby"));
					resultMap.put("user_no", rs.getString("user_no"));
					resultMap.put("admin", rs.getString("admin"));
					result = "로그인 성공하셨네요";
				}else{
					result = "비밀번호가 틀리셨습니다.";
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("result", result);
		return resultMap;
	}
	@Override
	public String insertUser(Map<String, String> hm) {
		String result = "실패";
		Connection con = null;
		 try {
			 con = DBConnector.getCon();
			 String sql = "insert into user(id,name,password,hobby)" + 
			 		" values(?,?,?,?)";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, hm.get("id"));
			 ps.setString(2, hm.get("name"));
			 ps.setString(3, hm.get("pwd"));
			 ps.setString(4, hm.get("hobby"));
			 int row = ps.executeUpdate();
			 if(row==1) {
					con.commit();
					result = hm.get("name") + "님 회원가입에 성공하셨습니다.";
				}else {
					con.rollback();
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				if(con!=null) {
					try {
						con.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
			finally{
				try {
					DBConnector.closeCon();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		 return result;
		
	}

	@Override
	public Map<String, String> selectUser(Map<String, String> hm) {
		Connection con;
		PreparedStatement ps;
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			con = DBConnector.getCon();
			System.out.println("연결 성공");
			String sql = "select * from user";
			sql += " where user_no=?";
			ps = con.prepareStatement(sql);
			ps.setString(1 , hm.get("user_no"));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				resultMap.put("id", rs.getString("id"));
				resultMap.put("name", rs.getString("name"));
				resultMap.put("hobby", rs.getString("hobby"));
				resultMap.put("user_no", rs.getString("user_no"));
				resultMap.put("admin", rs.getString("admin"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public int deleteUser(Map<String, String> hm) {
		Connection con = null;;
		 try {
			 con = DBConnector.getCon();
			 String sql = "delete from user";
					 sql += " where user_no=?";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, hm.get("user_no"));
			 int row = ps.executeUpdate();
				if(row==1) {
					con.commit();
				}else {
					con.rollback();
				}
				return row;
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				if(con!=null) {
					try {
						con.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
			finally{
				try {
					DBConnector.closeCon();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return 0;
	}

	@Override
	public int updateUser(Map<String, String> hm) {
		Connection con = null;
		 try {
			 con = DBConnector.getCon();
			 String sql = "update user";
			 	sql += " set name=?, password=?, hobby=?";
			 	sql += " where user_no=?";
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, hm.get("name"));
			 ps.setString(2, hm.get("pwd"));
			 ps.setString(3, hm.get("hobby"));
			 ps.setString(4, hm.get("user_no"));
			 int row = ps.executeUpdate();
				if(row==1) {
					con.commit();
				}else {
					con.rollback();
				}
				return row;
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				if(con!=null) {
					try {
						con.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}
			finally{
				try {
					DBConnector.closeCon();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return 0;
	}

	@Override
	public List<Map<String,String>> selectUserList(Map<String,String> hm){
		Connection con;
		PreparedStatement ps;
		List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
		try {
			con = DBConnector.getCon();
			String sql = "select * from user";
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String, String> resultMap = new HashMap<String, String>();
				resultMap.put("user_no", rs.getString("user_no"));
				resultMap.put("id", rs.getString("id"));
				resultMap.put("name", rs.getString("name"));
				resultMap.put("hobby", rs.getString("hobby"));
				userList.add(resultMap);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	

}
