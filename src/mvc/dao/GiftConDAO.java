package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.GiftCon;
import mvc.dto.Orders;

public interface GiftConDAO {

	/**
	 * 기프티콘 코드로 정보 출력
	 */
	List<Orders> giftConInfo(String giftCode) throws SQLException;

	/**
	 * 기프티콘 조회
	 * @throws SQLException 
	 */
	GiftCon selectGiftCon(Orders order) throws SQLException;
	
	/**
	 * 기프티콘 사용
	 * @throws SQLException 
	 */
	int orderByGiftCon(Orders order) throws SQLException;
	
	/**
	 * 기프티콘 조회
	 */
	int useGiftCon(Orders order) throws SQLException;
}
