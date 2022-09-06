package mvc.service;



import java.sql.SQLException;
import java.util.List;

import mvc.model.dao.AdminDAOImpl;
import mvc.exception.NotFoundException;
import mvc.model.dao.AdminDAO;
import mvc.model.dto.Admin;
import mvc.model.dto.Goods;
import mvc.model.dto.Notice;
import mvc.session.AdminSession;
import mvc.session.AdminSessionSet;


	public class AdminService {
		static AdminDAO adminDao = new AdminDAOImpl();
		
		
		
		
	  /**
	   * 로그인
	   * */
		public static Admin login(String adminId, String adminPw)throws NotFoundException , SQLException{
			Admin admin = adminDao.login(adminId, adminPw);
			if(admin==null) {
				throw new NotFoundException("정보를 다시 확안해주세요.");
			}
			
			//로그인 된 정보 저장하기
			AdminSession session = new AdminSession();
			
			AdminSessionSet sessionSet = AdminSessionSet.getInstance();
			sessionSet.add(session);
			
			return admin;
		}
		
		
		public interface BoardService {
			/**
			 * 모든 레코드 검색
			 */
			List<Goods> goodsSelectAll() throws SQLException;
			
			/**
			 * 게시물 등록 
			 */
			void goodsInsert(Goods good) throws SQLException;

			/**
			 * 게시물 수정
			 */
			void goodsUpdate(Goods goods) throws SQLException;

			/**
			 * 게시물 삭제
			 */
			void goodsDelete(int goodsNo) throws SQLException;
			
			/*
			 *통계보기 
			 */
			
			/**
			 * 공지 등록
			 */
			void NoticePrint(Notice notice) throws SQLException;
			
		}

	}
	