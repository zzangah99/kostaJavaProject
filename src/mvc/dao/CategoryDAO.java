package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Category;

public interface CategoryDAO {
	
	 List<Category> selectCategory() throws SQLException;
		
	

}
