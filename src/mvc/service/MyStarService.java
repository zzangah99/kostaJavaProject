package mvc.service;

import java.sql.SQLException;

import mvc.dao.MyStarDAO;
import mvc.dao.MyStarDAOImpl;
import mvc.dto.MyStar;
import mvc.exception.NotFoundException;

public class MyStarService {
	MyStarDAO myStarDao = new MyStarDAOImpl();

	/**
	 * 마이페이지->내가쓴 별점 보기(not null) 
	  * */
	public MyStar myStar(String userId) throws SQLException {
		MyStar myStar=myStarDao.myStar(userId);
		if(myStar==null) {
			throw new SQLException("실패했습니다.");
		}
		return myStar;
	}
	
	/**
	 * 마이페이지->내가쓴 별점 평가(not null) 
	  * */
	public MyStar myStarAssess(String userId) throws NotFoundException {
		MyStar myStar=myStarDao.myStarAssess(userId);
		if(myStar==null) {
			throw new NotFoundException("별점을 등록해주세요.");
		}
		return myStar;
	}
	
}

