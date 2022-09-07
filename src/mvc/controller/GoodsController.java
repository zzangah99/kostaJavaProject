package mvc.controller;
import java.sql.SQLException;
import java.util.List;

import mvc.dto.Goods;
import mvc.service.GoodsService;
import mvc.view.EndView;
import mvc.view.FailView;


public class GoodsController {
	static GoodsService goodsService = new GoodsService();
	
	/**
	 * 카테고리에 따른 음료 가져오기
	 */
	public static void selectBever(int num) {
		try {
			List<Goods> categoryList = goodsService.selectBever(num);
			EndView.printGoodsList(categoryList);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	
	
	
	public static void goodsSelectByName(String word) { //이름 검색
		try {
			List<Goods> list = goodsService.goodsSelectBygoodsName(word);
			EndView.printGoodsList(list);
		
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

    

	
}
