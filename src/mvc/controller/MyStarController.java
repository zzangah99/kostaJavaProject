package mvc.controller;

import mvc.dto.MyStar;
import mvc.service.MyStarService;
import mvc.view.EndView;
import mvc.view.FailView;

public class MyStarController {
		static MyStarService myStarService = new MyStarService();
	
	/**
	  * 마이페이지->내가쓴 별점 보기(not null) 
	  * */
	public static void myStar(String userId) {
		try {
			MyStar myStar = myStarService.myStar(userId);
			EndView.myStar(myStar);
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
		}

	/**
	  * 마이페이지->내가쓴 별점 평가(not null) 
	  * */
	public static void myStarAssess(String userId) {
		try {
			MyStar myStar = myStarService.myStarAssess(userId);
			EndView.myStarAssess(myStar);
			}catch(Exception e) {
				//e.printStackTrace();
				FailView.errorMessage(e.getMessage());
			}
		}

	
		

	}
