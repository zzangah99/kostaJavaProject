package mvc.dao;


import java.sql.SQLException;
import java.util.List;
import mvc.dto.Admin;
import mvc.dto.Goods;
import mvc.dto.Notice;
import mvc.dto.OrderDetail;
import mvc.dto.OrderLine;
import mvc.dto.Orders;


public interface AdminDAO {
  /**
   * 로그인하기
   * */
	Admin login(String adminId, String adminPw)throws SQLException;
	
	/**
	 * 레코드 전체검색
	 * select * from goosdCode
	 */
	List<Goods> goodsSelectAll() throws SQLException;
	
	/**
	 * 상품 등록하기
	 */
	int GoodsInsert(Goods goods) throws SQLException;
	
	/**
	 * GoodsCode에 해당하는 게시물 내용 수정하기
	 * update goods set content = ? where goosdCode = ?
	 */

	int GoodsUpdateName(String goods) throws SQLException;
	int GoodsUpdateSo(int goodsCode) throws SQLException;
	int GoodsUpdateSt(int goodsCode) throws SQLException;
	int GoodsUpdatePr(int goodsCode) throws SQLException;
	
	
	/**
	 * GoodsCode에 해당하는 레코드 삭제
	 *
	 */

	int GoodsDelete() throws SQLException;


	
	
	
	/* 
	 통계보기
	 */
	   //일통계보기
	OrderDetail getTodaysTotalOrderDetail() throws SQLException;
	//월통계
	OrderDetail getMonthTotalOrderDetail() throws SQLException;


	
	
	/**
	 * 공지 등록하기
	 insert into notice(notice_num, adminId, noticeDate, noticeTitel,noticeContent) "
					+ "values(?,?,sysdate,?,?,)
	 */
	String noticeprint() throws SQLException;
	
}

