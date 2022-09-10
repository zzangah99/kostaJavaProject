package mvc.controller;

import java.util.List;

import mvc.dto.Category;
import mvc.service.CategoryService;
import mvc.view.EndView;
import mvc.view.FailView;

public class CategoryController {
	static CategoryService categoryService = new CategoryService();
	
	public static void selectCategory() {
		try {
			List<Category> categoryList = categoryService.selectCategory();
			EndView.printsCategoryList(categoryList);
			
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
