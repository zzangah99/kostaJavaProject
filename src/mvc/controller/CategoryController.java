package mvc.controller;

import java.util.List;

import mvc.dto.Category;
import mvc.service.CategoryService;
import mvc.view.EndView;
import mvc.view.FailView;

public class CategoryController {
	static CategoryService categoryservice = new CategoryService();
	
	public static void selectCategory() {
		try {
			List<Category> categoryList = categoryservice.selectCategory();
			//EndView.printGoodsList(categoryList));
			
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
