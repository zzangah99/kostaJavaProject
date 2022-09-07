package mvc.service;



import java.sql.SQLException;
import java.util.List;

import mvc.dao.AdminDAOImpl;
import mvc.exception.NotFoundException;
import mvc.dao.AdminDAO;
import mvc.dto.Admin;
import mvc.dto.Customer;
import mvc.dto.Goods;
import mvc.dto.Notice;
import mvc.dto.Orders;
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
		Admin admin = adminDao.login(adminId, adminPw);
		if(admin==null) {
			throw new NotFoundException("정보를 다시 확안해주세요.");
		}
		
		//로그인 된 정보 저장하기
		AdminSession adminsession = new AdminSession(adminId);
		
		AdminSessionSet adminsessionSet = AdminSessionSet.getInstance();
		adminsessionSet.add(adminsession);
		
		return admin;
	}

		/**
		 * 상품 등록 
		 * 
		 */
	public static void GoodsInsert(Goods goods) throws SQLException, NotFoundException {
		int result = adminDao.GoodsInsert(goods);
	
		if(result==0) {
			throw new NotFoundException("상품등록에 실패하였습니다");
		}else {
			System.out.println("상품이 등록되었습니다");
		}
	
	}

		/**
		 * 상품 수정
		 */

		public void GoodsUpdateName(int goodsCode) throws SQLException, NotFoundException {
			int result = adminDao.GoodsUpdateName(goods);
				
			if(result==0) {
				throw new NotFoundException("상품이 수정되지 않았습니다");
			}else {
				System.out.println("상품이 수정되었습니다");

		    }
		}
		
		public void GoodsUpdatePr(int goodsCode) throws SQLException, NotFoundException {
			int result = adminDao.GoodsUpdatePr(goods);
				
			if(result==0) {
				throw new NotFoundException("상품이 수정되지 않았습니다");
			}else {
				System.out.println("상품이 수정되었습니다");

		    }
		}
		
		public void GoodsUpdateSo(int goodsCode) throws SQLException, NotFoundException {
			int result = adminDao.GoodsUpdateSo(goods);
				
			if(result==0) {
				throw new NotFoundException("상품이 수정되지 않았습니다");
			}else {
				System.out.println("상품이 수정되었습니다");

		    }
		}
		
		public void GoodsUpdateSt(int goodsCode) throws SQLException, NotFoundException {
			int result = adminDao.GoodsUpdateSt(goods);
				
			if(result==0) {
				throw new NotFoundException("상품이 수정되지 않았습니다");
			}else {
				System.out.println("상품이 수정되었습니다");

		    }
		}

		

		/**
		 * 상품 삭제
		 */
	
	
		public void goodsDelete(int goodCode) throws SQLException, NotFoundException {
			int result = adminDao.goodsDelete();
		
			if(result==0) {
				throw new NotFoundException("상품이 삭제되지 않았습니다");
			}else {
				System.out.println("상품이 삭제되었습니다");
			}
		
		}

		
		/*
		 *통계보기 
		 */
		//일
		public static List<Orders> tongySelectByDate(String orderTime)throws SQLException, NotFoundException {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
		/**
		 * 공지 등록
         */
			public static void NoticePrint(int noticeNum) throws SQLException, NotFoundException {
				int result = adminDao.GoodsInsert(goods);
			
				if(result==0) {
					throw new NotFoundException("상품등록에 실패하였습니다");
				}else {
					System.out.println("상품이 등록되었습니다");
				}
			
			}
			
			
			
		}



