package mvc.controller;

import mvc.dao.GoodsDAO;
import mvc.dao.GoodsDAOImpl;
import mvc.dto.Admin;
import mvc.dto.Goods;
import mvc.dto.Notice;
import mvc.dto.OrderDetail;
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
			// System.out.println(adminId);
			 // System.out.println(adminPw);
			  
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
		public static void GoodsUpdateName(Goods goods) {
		try {
			adminService.GoodsUpdateName(goods);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	 //수정
	public static void GoodsUpdatePr(Goods goods) {
		try {
			adminService.GoodsUpdatePr(goods);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	public static void GoodsUpdateDi(Goods goods) {
		try {
			adminService.GoodsUpdateDi(goods);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
	//수정
	public static void GoodsUpdateSo(Goods goods) {
		try {
			adminService.GoodsUpdateSo(goods);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	 //수정
	public static void GoodsUpdateSt(Goods goods) {
		try {
			adminService.GoodsUpdateSt(goods);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
   //상품 삭제하기  
   public static  void Goodsdelete(int goodsCode) {
    try {
    	adminService.goodsDelete(goodsCode);
		} catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
  
   //일통계보기
	  
		public static OrderDetail getTodaysTotalOrderDetail() {
			OrderDetail orderDetail = null;
			 try {
				 orderDetail = adminService.getTodaysTotalOrderDetail();
				  EndView.PrintDayStatistic(orderDetail);
					} catch (Exception e) {
						//e.printStackTrace();
						FailView.errorMessage(e.getMessage());
					}
			 
			return orderDetail;
			
		}
		
	   
	   

   
   //월통계보기
		public static OrderDetail getMonthTotalOrderDetail() {
			OrderDetail orderDetail = null;
			 try {
	              orderDetail = adminService.getMonthTotalOrderDetail();
				  EndView.PrintMonthStatistic(orderDetail);
					} catch (Exception e) {
						//e.printStackTrace();
						FailView.errorMessage(e.getMessage());
					}
			 
			return orderDetail;
		}
   
		
   //공지띄우기

			public static void NoticeInsert(Notice notice) {
				//String notice = null;
				try {
					adminService.NoticeInsert(notice);
					//EndView.printMessage(notice);
				}catch (Exception e) {
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
