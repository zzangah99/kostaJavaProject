package mvc.controller;

import mvc.dao.GoodsDAO;
import mvc.dao.GoodsDAOImpl;
import mvc.dto.Admin;
import mvc.dto.Goods;
import mvc.service.AdminService;
import mvc.view.EndView;
import mvc.view.FailView;
import mvc.view.MenuView;


public class AdminController {
	GoodsDAO goodsDao = new GoodsDAOImpl();
	static AdminService adminService = new AdminService();
	
 /**
  * 로그인
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
	
	public static void GoodsUpdateName(int goodsCode,String goodsRename) {
		
		
		try {
			adminService.GoodsUpdateName(goodsCode);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	 //수정
	public static void GoodsUpdatePr(int goodsCode, int goodsReprice) {
		try {
			adminService.GoodsUpdatePr(0);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	//수정
	public static void GoodsUpdateSo(int goodsCode, String goodsReSo) {
		try {
			adminService.GoodsUpdateSo(0);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	 //수정
	public static void GoodsUpdateSt(int goodsCode, int goodsReSt) {
		try {
			adminService.GoodsUpdateSt(0);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
   //상품 삭제하기  
   public static  void Goodsdelete(int goodsCode) {
    try {
    	adminService.goodsDelete(0);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
  
   //일통계보기
	  
		public static void getTodaysTotalOrderDetail() {
			 try {
			    	adminService.getTodaysTotalOrderDetail();
					} catch (Exception e) {
						//e.printStackTrace();
						FailView.errorMessage(e.getMessage());
					}
			
		}
		
	   
	   

   
   //월통계보기
		public static void getMonthTotalOrderDetail() {
			 try {
			    	adminService.getMonthTotalOrderDetail();
					} catch (Exception e) {
						//e.printStackTrace();
						FailView.errorMessage(e.getMessage());
					}
			
		}
   
		
   //공지띄우기

		public static String NoticePrint() {
			String notice = null;
			try {
				notice = adminService.NoticePrint();
				EndView.printMessage(notice);
			}catch (Exception e) {
			    FailView.errorMessage(e.getMessage());
			}
			return notice;
		}




		
		
   
}
