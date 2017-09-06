package service;

import java.util.List;

import dto.GoodsInfo;

public interface GoodsService {

	public List<GoodsInfo> selectGoodsList(GoodsInfo gi);
	
	public GoodsInfo selectGoods(GoodsInfo gi);
	
	public int deleteGoods(GoodsInfo gi);
	
	public int insertGoods(GoodsInfo gi);
	
	public int updateGoods(GoodsInfo gi);
}
