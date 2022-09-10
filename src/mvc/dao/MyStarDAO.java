package mvc.dao;

import java.sql.SQLException;

import mvc.dto.MyStar;

public interface MyStarDAO {
	
	/**
	 * 마이페이지->별점 조회 
	 */
	MyStar myStar(String userId)throws SQLException;
	
	/**
	 * 마이페이지->별점 평가
	 */
	MyStar myStarAssess(String userId);


}
