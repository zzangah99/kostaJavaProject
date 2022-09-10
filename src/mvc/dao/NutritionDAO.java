package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Nutrition;

public interface NutritionDAO {
	
	List<Nutrition> goodsNutrition(int goodsCode) throws SQLException;
}
