package mvc.controller;
import java.sql.SQLException;
import java.util.List;

import mvc.dto.Goods;
import mvc.service.GoodsService;
import mvc.view.EndView;
import mvc.view.FailView;


public class GoodsController {
	static GoodsService goodsService = new GoodsService();
	
	public static void goodsSelectByCategory(int menu2) {
		try {
			List<Goods> categoryList = goodsService.goodsSelectByCategory(menu2);
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
