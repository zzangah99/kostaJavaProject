package mvc.service;

import java.sql.SQLException;
import java.util.List;


import mvc.dao.CategoryDAO;
import mvc.dao.CategoryDAOImpl;
import mvc.dto.Category;
import mvc.dto.Goods;
import mvc.exception.NotFoundException;

public class CategoryService {
	CategoryDAO categoryDAO = new CategoryDAOImpl();
	
	public List<Category> selectCategory() throws NotFoundException, SQLException{
		List<Category> categoryList = categoryDAO.selectCategory();
		if(categoryList.size() == 0) throw new SQLException("현재 이 없습니다.");
		return categoryList;
	}


}