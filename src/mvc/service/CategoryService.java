package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.CategoryDAO;

import mvc.dto.Category;
import mvc.dto.Goods;

public class CategoryService {
	CategoryDAO categoryDAO = new CategoryDAOImpl();
	
	public List<Category> selectCategory() throws SQLException{
		List<Category> categoryList = categoryDAO.selectCategory();
		if(categoryList.size() == 0) throw new SQLException("현재 상품이 없습니다.");
		return categoryList;
	}


}