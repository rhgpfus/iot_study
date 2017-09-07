package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.DBCon;
import dto.GoodsInfo;
import dto.VendorInfo;

public class GoodsServiceImpl implements GoodsService{

	@Override
	public List<GoodsInfo> selectGoodsList(GoodsInfo gi) {
		Connection con = null;
		DBCon db = null;
		List<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		try {
			db = new DBCon();
			con = db.getCon();
			String sql = "select gi.ginum, gi.giname, gi.gidesc, gi.vinum, gi.gicredat, gi.gimofdat, gi.gicreusr, u.name, gi.gimofusr," + 
					" (select u2.name from user as u2 where gi.gimofusr = u2.user_no) as name2" + 
					" from goods_info as gi, user as u" + 
					" where gi.gicreusr = u.user_no";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				GoodsInfo rgi = new GoodsInfo();
				rgi.setGiNum(rs.getInt("giNum"));
				rgi.setGiName(rs.getString("giName"));
				rgi.setGiDesc(rs.getString("giDesc"));
				rgi.setViNum(rs.getInt("viNum"));
				rgi.setGiCredat(rs.getString("giCredat"));
				rgi.setGiMofdat(rs.getString("giMofdat"));
				rgi.setGiCreusr(rs.getInt("giCreusr"));
				rgi.setGiMofusr(rs.getInt("giMofusr"));
				rgi.setName(rs.getString("name"));
				rgi.setName2(rs.getString("name2"));
				
				goodsList.add(rgi);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return goodsList;
	}

	@Override
	public GoodsInfo selectGoods(GoodsInfo gi) {
		Connection con = null;
		DBCon db = null;
		List<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		try {
			db = new DBCon();
			con = db.getCon();
			String sql = "select ginum, giname, gidesc, vinum, gicredat, gimofdat, gicreusr, gimofusr from goods_info";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				GoodsInfo rgi = new GoodsInfo();
				rgi.setGiNum(rs.getInt("giNum"));
				rgi.setGiName(rs.getString("giName"));
				rgi.setGiDesc(rs.getString("giDesc"));
				rgi.setViNum(rs.getInt("viNum"));
				rgi.setGiCredat(rs.getString("giCredat"));
				rgi.setGiMofdat(rs.getString("giMofdat"));
				rgi.setGiCreusr(rs.getInt("giCreusr"));
				rgi.setGiMofusr(rs.getInt("giMofusr"));
				goodsList.add(rgi);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return gi;
	}

	@Override
	public int deleteGoods(GoodsInfo gi) {
		
		return 0;
	}

	@Override
	public int insertGoods(GoodsInfo gi) {
		
		return 0;
	}

	@Override
	public int updateGoods(GoodsInfo gi) {
		
		return 0;
	}

	@Override
	public List<VendorInfo> selectVendorList(VendorInfo vi) {
		Connection con = null;
		DBCon db = null;
		List<VendorInfo> vendorList = new ArrayList<VendorInfo>();
		try {
			db = new DBCon();
			con = db.getCon();
			String sql = "select * from vendor_info";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				VendorInfo rVi = new VendorInfo();
				rVi.setViNum(rs.getInt("vinum"));
				rVi.setViName(rs.getString("viname"));
				rVi.setViDesc(rs.getString("videsc"));
				vendorList.add(rVi);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return vendorList;
	}

}
