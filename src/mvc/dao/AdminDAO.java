package mvc.dao;


import java.sql.SQLException;
import java.util.List;
import mvc.dto.Admin;
import mvc.dto.Goods;
import mvc.dto.Notice;


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

	int GoodsUpdateName(Goods goods) throws SQLException;
	int GoodsUpdatePr(Goods goods) throws SQLException;
	int GoodsUpdateSo(Goods goods) throws SQLException;
	int GoodsUpdateSt(Goods goods) throws SQLException;
	
	/**
	 * GoodsCode에 해당하는 레코드 삭제
	 * delete from goods where goosdCode = ?
	 */

	int GoodsDelete(int goodsNo) throws SQLException;
	
	/* 
	 통계보기
	 */
	
	
	
	/**
	 * 공지 등록하기
	 insert into notice(notice_num, adminId, noticeDate, noticeTitel,noticeContent) "
					+ "values(?,?,sysdate,?,?,)
	 */
	int NoticePrint(Notice notice) throws SQLException;

	int goodsDelete();



	
	
}

