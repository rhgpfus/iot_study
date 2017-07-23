package Exem05;

import java.util.ArrayList;
import java.util.HashMap;

public class DataExem {

	public static void main(String[] args) {
		
		String data = "김덩개,26,청주" + "/";
		data += "고덩개,26,용인" + "/";
		data += "구서짱,28,신창" + "/";
		
		String[] datas = data.split("/");
		ArrayList<HashMap> dataList = new ArrayList<HashMap>();
		for(int i=0;i<datas.length;i++) {
			String[] inDatas = datas[i].split(",");
			HashMap<String,String> hm = new HashMap<String,String>();
			for(int j=0;j<inDatas.length;j++) {
				if(j==0) {
					hm.put("이름", inDatas[j]);
				}else if(j==1) {
					hm.put("나이", inDatas[j]);
				}else if(j==2) {
					hm.put("주소", inDatas[j]);
				}
			}
			dataList.add(hm);
		}
		for(HashMap hm : dataList) {
			System.out.println(hm.get("이름"));
			System.out.println(hm.get("나이"));
			System.out.println(hm.get("주소"));
		}
	}
	
}
