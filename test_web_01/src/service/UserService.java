package service;

import java.util.List;
import java.util.Map;

public interface UserService {

	public String insertUser(Map<String, String> hm);
	
	public Map<String, String> selectUser(Map<String, String> hm);
	
	public Map<String, String> loginUser(Map<String, String>  hm);
	
	public int deleteUser(Map<String,String> hm);
	
	public int updateUser(Map<String,String> hm);
	
	public List<Map<String,String>> selectUserList(Map<String,String> hm);
	
}
