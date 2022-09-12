package mvc.service;
import java.sql.SQLException;
import java.util.List;

import mvc.dao.GoodsDAO;
import mvc.dao.GoodsDAOImpl;
import mvc.dto.Goods;

public class GoodsService {
	GoodsDAO goodsDAO = new GoodsDAOImpl();
	
	
	/**
	 * 카테고리에 해당하는 상품 검색 
	 */
	public List<Goods> selectBever(int num, String userId) throws SQLException{
		List<Goods> menuList = goodsDAO.selectBever(num, userId);
		if(menuList.size() == 0) throw new SQLException("해당 번호는 존재하지 않습니다. 다시 입력해주세요. ");
		return menuList;
		
		
	}
	
	
	/**
	 * 상품이름에 해당하는 상품검색
	 * */
	public List<Goods> goodsSelectBygoodsName(String keyword) throws SQLException{
		List <Goods>key = goodsDAO.goodsSelectBygoodsName(keyword);
		if(key.size() == 0 || key.isEmpty()) throw new SQLException(keyword + "의 상품을 찾을 수 없습니다.");
		return key;
	}
	
	
	
}