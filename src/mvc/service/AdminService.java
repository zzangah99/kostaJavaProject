package mvc.service;



import java.sql.SQLException;
import java.util.UUID;

import mvc.dao.AdminDAO;
import mvc.dao.AdminDAOImpl;
import mvc.dto.Admin;
import mvc.dto.Goods;
import mvc.dto.Notice;
import mvc.dto.OrderDetail;
import mvc.exception.NotFoundException;
import mvc.session.AdminSession;
import mvc.session.AdminSessionSet;



public class AdminService {
	 static final int result = 0;
	 static final Goods goods = null;
	static AdminDAO adminDao = new AdminDAOImpl();

  /**
   * 로그인
   * */
	public Admin login(String adminId, String adminPw)throws NotFoundException , SQLException{ 
		// System.out.println(adminId);
		//  System.out.println(adminPw);
		  
		Admin admin = adminDao.login(adminId, adminPw);
		if(admin==null) {
			throw new NotFoundException("정보를 다시 확안해주세요.");
		}
		
		//로그인 된 정보 저장하기
		AdminSession adminsession = new AdminSession();
		adminsession.setAdminSessionId(UUID.randomUUID().toString());
		//System.out.println("adminservice adminsession111");
		
		AdminSessionSet adminsessionSet = AdminSessionSet.getInstance();
		adminsessionSet.add(adminsession);
		
		return admin;
	}

		/**
		 * 상품 등록 
		 * 
		 */
	public static void GoodsInsert(Goods goods) throws SQLException, NotFoundException {
		int[] result = adminDao.GoodsInsert(goods);
	
		if(result==null) {
			throw new NotFoundException("상품등록에 실패하였습니다");
		}else {
			System.out.println("상품이 등록되었습니다");
		}
	
	}

		/**
		 * 상품 수정
		 */

		public void GoodsUpdateName(Goods goods) throws SQLException, NotFoundException {
			int result = adminDao.GoodsUpdateName(goods);
				
			if(result==0) {
				throw new NotFoundException("상품이 수정되지 않았습니다");
			}else {
				System.out.println("상품이 수정되었습니다");

		    }
		}
		
		public void GoodsUpdatePr(Goods goodsCode) throws SQLException, NotFoundException {
			int result = adminDao.GoodsUpdatePr(goodsCode);
				
			if(result==0) {
				throw new NotFoundException();
			}else {
				System.out.println("상품이 수정되었습니다");

		    }
		}
		public void GoodsUpdateDi(Goods goodsCode) throws SQLException, NotFoundException {
			int result = adminDao.GoodsUpdateDi(goodsCode);
				
			if(result==0) {
				throw new NotFoundException();
			}else {
				System.out.println("상품이 수정되었습니다");

		    }
		}
		
		
		
		public void GoodsUpdateSo(Goods goods) throws SQLException, NotFoundException {
			int result = adminDao.GoodsUpdateSo(goods);
				
			if(result==0) {
				throw new NotFoundException();
			}else {
				System.out.println("상품이 수정되었습니다");

		    }
		}
		
		public void GoodsUpdateSt(Goods goodsCode) throws SQLException, NotFoundException {
			int result = adminDao.GoodsUpdateSt(goodsCode);
				
			if(result==0) {
				throw new NotFoundException();
			}else {
				System.out.println("상품이 수정되었습니다");

		    }
		}

		

		/**
		 * 상품 삭제
		 */
	
	
		public void goodsDelete(int goodCode) throws SQLException, NotFoundException {
			int result = adminDao.GoodsDelete(goodCode);
		
			if(result==0) {
				throw new NotFoundException();
			}else {
				System.out.println("상품이 삭제되었습니다");
			}
		
		}

		
		/*
		 *통계보기 
		 */
		//일
		public OrderDetail getTodaysTotalOrderDetail() throws SQLException {
			OrderDetail orderDetail= null;
			try {
				
			orderDetail = adminDao.getTodaysTotalOrderDetail();
		
			}catch (Exception e) {
				return new OrderDetail();
			
			}
		return orderDetail;
		}
		
		//월
		public OrderDetail getMonthTotalOrderDetail() throws SQLException {
			OrderDetail orderDetail= null;

			try {
			orderDetail = adminDao.getMonthTotalOrderDetail();
		
			}catch (Exception e) {
				return new OrderDetail();
			
			}
		return orderDetail;
		}
		
	
		/**
		 * 공지 등
         */
			public static void NoticeInsert(Notice notice) throws SQLException, NotFoundException {
				int result = adminDao.NoticeInsert(notice);
				if(result == 0) {
					throw new NotFoundException("공지등록에 실패하였습니다");
			    } else {
				   System.out.println("공지가 등록되었습니다");
			    }
			
		    }
			/**
			 * 공지 등록
	         */
				public String NoticePrint() throws SQLException, NotFoundException {
					String notice = adminDao.noticeprint();
					if(notice == null) throw new NotFoundException("공지등록에 실패하였습니다");
					
					return notice;
				}
				

}



