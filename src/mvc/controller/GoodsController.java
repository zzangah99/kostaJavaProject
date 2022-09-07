package mvc.controller;
import java.sql.SQLException;
import java.util.List;

import mvc.dto.Goods;
import mvc.service.GoodsService;
import mvc.view.EndView;
import mvc.view.FailView;


public class GoodsController {
	static GoodsService goodsService = new GoodsService();
	/** 테스트
	public static void goodsSelectByCategory() {
		try {
			List<Goods> categoryList = goodsService.goodsSelectByCategory();
			EndView.printGoodsList(categoryList);
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	**/
	public static void goodsSelectByCategory() {
		try {
			List<Goods> categoryList = goodsService.goodsSelectByCategory();
			EndView.printsGoodsList(categoryList);
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
