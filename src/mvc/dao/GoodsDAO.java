package mvc.dao;
import java.sql.SQLException;

import java.util.List;

import mvc.dto.Goods;

public interface GoodsDAO {

	/**
	 * 대분류 상품 검색 
	 */
	
	List<Goods> selectBever(int num, String userId) throws SQLException;

	/**
	 * goodsName에 해당하는 정보 검색
	 */
	List<Goods> goodsSelectBygoodsName(String keyword) throws SQLException;
	
	
	/**
	 * goodsId에 해당하는 정보 검색
	 */
	Goods goodsSelectBygoodsCode(int goodsCode) throws SQLException;


	/**
	 * goodsStock에 해당하는 정보 검색
	 */
	//List<Goods> goodsSelectByStock(int goodsCode) throws SQLException;
} 
