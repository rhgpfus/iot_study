package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {

	Map<String, String> getUserLogin(String id, String pwd);
	
	Map<String, String> seleteUser(String user_no);
	
	int insertUser(Map <String, String> hm);
	
	int updateUser(Map <String, String> hm);
	
	int deleteUser(Map <String, String> hm);
	
	List<Map<String,String>> getUserList(Map<String, String> hm);
}
