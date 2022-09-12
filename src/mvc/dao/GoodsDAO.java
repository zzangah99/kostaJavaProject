package mvc.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.dto.Goods;

public interface GoodsDAO {

	/**
	 * 대분류 상품 검색 (커피,스무디와 같은)
	 */
	
	List<Goods> selectBever(int num, String userId) throws SQLException;

	/**
	 * goodsName에 해당하는 정보 검색
	 */
	List<Goods> goodsSelectBygoodsName(String keyword) throws SQLException;
	
	
	/**
	 * goodsCode에 해당하는 정보 검색
	 */
	Goods goodsSelectBygoodsCode(int goodsCode, String userId) throws SQLException;


	/**
	 * goodsStock에 해당하는 정보 검색(품절여부)
	 */
	//boolean goodsSelectByStock(int goodsCode, String goodsName) throws SQLException;
} 
