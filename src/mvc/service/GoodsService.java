package mvc.service;
import java.sql.SQLException;
import java.util.List;

import mvc.dao.GoodsDAO;
import mvc.dao.GoodsDAOImpl;
import mvc.dto.Goods;

public class GoodsService {
	GoodsDAO goodsDao = new GoodsDAOImpl();
	
	public List<Goods> goodsSelectByCategory(int menu2) throws SQLException{
		List<Goods> menuList = goodsDao.goodsSelectByCategory(menu2);
		if(menuList.size() == 0) throw new SQLException("현재 상품이 없습니다.");
		return menuList;
		
		
	}
	
	
	

	
	
	/**
	 * 상품번호에 해당하는 상품검색
	 * */
	public List<Goods> goodsSelectBygoodsName(String keyword) throws SQLException{
		List <Goods>key = goodsDao.goodsSelectBygoodsName(keyword);
		if(key.size() == 0 || key.isEmpty()) throw new SQLException(keyword + "의 상품을 찾을 수 없습니다.");
		return key;
	}
	
	
	
}