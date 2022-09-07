package mvc.controller;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import mvc.dto.Admin;
import mvc.dto.Goods;
import mvc.dto.Notice;
import mvc.dto.Orders;
import mvc.exception.NotFoundException;
import mvc.service.AdminService;
import mvc.util.DbUtil;
import mvc.view.EndView;
import mvc.view.FailView;
import mvc.view.MenuView;


public class AdminController {


	static AdminService adminService = new AdminService();
	
 /**
  * 로그인
 * @throws NotFoundException 
  * */
	public static void login(String adminId, String adminPw) {
		try {
			Admin admin = adminService.login(adminId, adminPw);
		
			MenuView.printMenuForAdmin(adminId);
			
			//MenuView.menu();
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}
	}
	
	

	
	//새로운 메뉴 추가
	
	public void GoodsInsert(Goods goods) {
		try {
			adminService.GoodsInsert(goods);
		}catch (Exception e) {
		    FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
     * 수정
     * */
	
	public static void GoodsUpdateName(int goodsCode) {
		try {
			adminService.GoodsUpdateName(0);
			EndView.printMessage("수정되었습니다.");
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	 //수정
	public static void GoodsUpdatePr(int goodsCode) {
		try {
			adminService.GoodsUpdatePr(0);
			EndView.printMessage("수정되었습니다.");
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	//수정
	public static void GoodsUpdateSo(int goodsCode) {
		try {
			adminService.GoodsUpdateSo(0);
			EndView.printMessage("수정되었습니다.");
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	 //수정
	public static void GoodsUpdateSt(int goodsCode) {
		try {
			adminService.GoodsUpdateSt(0);
			EndView.printMessage("수정되었습니다.");
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
   //상품 삭제하기  
   public static  void Goodsdelete(int goodsCode) {
    try {
    	adminService.goodsDelete(0);
			EndView.printMessage("삭제되었습니다.");
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
  
   //일통계보기
   

   public static void DayStatistic(String orderTime) {   
	    // 총주문내역보기
		//public static void TotalOrders(String userId) {
			try {
				 List<Orders> orderList = AdminService.tongySelectByDate(orderTime);
	             EndView.printOrderByDaySt(orderList);
			}catch (Exception e) {
				FailView.errorMessage(e.getMessage());
				
			}
		}

   
   //월통계보기
   public static void MonStatistic() {
	   
   }
   
		
   //공지띄우기

		public void NoticePrint(int noticeNum) {
			try {
				adminService.NoticePrint(noticeNum);
				EndView.printMessage("공지가 등록되었습니다.");
			}catch (Exception e) {
			    FailView.errorMessage(e.getMessage());
			}
		}
   
}

	
		