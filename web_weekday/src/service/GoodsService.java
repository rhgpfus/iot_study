package service;

import java.util.List;

import dto.GoodsInfo;
import dto.VendorInfo;

public interface GoodsService {

	public List<GoodsInfo> selectGoodsList(GoodsInfo gi);
	
	public List<VendorInfo> selectVendorList(VendorInfo vi);
	
	public GoodsInfo selectGoods(GoodsInfo gi);
	
	public int deleteGoods(GoodsInfo gi);
	
	public int insertGoods(GoodsInfo gi);
	
	public int updateGoods(GoodsInfo gi);
}
