package Exem05;

import java.util.ArrayList;
import java.util.HashMap;

public class ListExem {
	public static void main(String[] args) {
		ArrayList al;
		al = new ArrayList();
		
		al.add(1);
		al.add(2);
		al.add(3);
		al.add(4);
		al.add(5);
		
		
		for(int i=0;i<al.size();i++) {
			System.out.println(al.get(i));
		}
		
		HashMap<String,String> hm = new HashMap<String,String>();
		hm.put("1", "1");
		
	}
}
