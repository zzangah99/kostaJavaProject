package mvc.controller;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import mvc.dto.Admin;
import mvc.dto.Goods;
import mvc.dto.Notice;
import mvc.service.AdminService;
import mvc.util.DbUtil;
import mvc.view.MenuView;



public class AdminController {
	private static Scanner sc = new Scanner(System.in);

	static AdminController adminController = new AdminController();
 /**
  * 로그인
  * */
	public static void login(String adminId, String adminPw) {
		try {
			Admin admin = AdminService.login(adminId, adminPw);
			
			MenuView.printAdminMenu(adminId);
			//MenuView.menu();
		}catch (Exception e) {
			//e.printStackTrace();
			System.err.println("로그인에 실패하였습니다");
		}
	}
	
	

	
	//새로운 메뉴 추가
	 
	public static void GoodsInsert () {
	
	System.out.println("--새로운 상품 등록하기 --");

	System.out.print("상품코드를 입력해주세요 : ");
	String goodsCode = sc.nextLine();
	
	System.out.print("상품이름을 입력해주세요 : ");
	String goodsName = sc.nextLine();
	
	System.out.print("상품가격을 입력해주세요 : ");
	int goodsPrice = Integer.parseInt(sc.nextLine());
	
	System.out.print("상세설명을 입력해주세요 : ");
	int goodsDetail = Integer.parseInt(sc.nextLine());
	
	System.out.print("품절여부를 입력해주세요 : ");
	String soldOut = sc.nextLine();
	
	System.out.print("재고수량을 입력해주세요 : ");
	int stock = Integer.parseInt(sc.nextLine());
	
	System.out.print("카테고리코드를 입력해주세요 : ");
	String categoryCode =sc.nextLine();
	
	Goods goods =  new Goods("goodsCode", "goodsName", 0, "soldOut","stock",0,"categoryCode");
	}

	
	/**
     * 수정
     * */

     public static void GoodsUpdate() {
       System.out.println("수정 할 상품의 상품코드는?");
   	   String gcode = sc.nextLine();
   	   
    	 //BoardController.boardUpdate(board);
     }


   
   //상품 삭제하기  
   public static void Goodsdelete() {
	   
	   System.out.println("삭제 할 상품의 상품코드는?");
   	   String gcode = sc.nextLine();
}
   
   //일통계보기
   public static void DayStatistic() {
	
	}
   
   //월통계보기
   public static void MonStatistic() {
	   
   }
   
		
   //공지띄우기
   public static void NoticePrint () {
	 System.out.println("공지등록하기");
	 
  	 System.out.println("공지번호?");
  	 int noticeNum = Integer.parseInt(sc.nextLine());
  	 
  	 System.out.println("작성자?");
  	 String adminId = sc.nextLine();
  	 
  	 System.out.println("작성일자는?");
  	 String noticeDate = sc.nextLine();
  	 
  	 System.out.println("제목은?");
  	 String noticeTitel = sc.nextLine();
  	 
  	 System.out.println("내용은?");
  	 String noticeContent= sc.nextLine();

  	 Notice notice =  new Notice(0, "adminId", "noticeDate", "noticeTitel", "noticeContent");
   }
	 
	  
	
			
	public static void getTodaysTotalOrderDetail() {
				
	}




	public static void getMonthTotalOrderDetail() {
		
	}
	   
	  
 }

	
		
	

