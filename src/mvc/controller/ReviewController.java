package mvc.controller;

import java.util.List;

import mvc.dto.Review;
import mvc.service.ReviewService;
import mvc.view.EndView;
import mvc.view.FailView;

public class ReviewController {
	private static ReviewService reviewService = new ReviewService();
	
	public static void writeReview(Review review) {
		try {
			reviewService.reviewService(review);
			EndView.printReview(review.getReNum()+"번 리뷰에 등록 되었습니다.");
		}catch(Exception e) {
		FailView.errorMessage(e.getMessage());
	}
	
	}
}
