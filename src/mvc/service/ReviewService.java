package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.ReviewDAO;
import mvc.dao.ReviewDAOImpl;
import mvc.dto.Review;

public class ReviewService {
	private ReviewDAO reviewDAO = new ReviewDAOImpl();
	
	public void reviewService(Review review) throws SQLException{
		if( reviewDAO.writeReview(review)== 0) throw new SQLException("리뷰가 등록 되지 않았습니다.");
		
	}
	
	
	
}
