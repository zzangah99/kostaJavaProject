package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.dto.GiftCon;
import mvc.dto.Orders;

public interface GiftConDAO {

	/** 기프티콘 조회
	 * @throws SQLException 
	 */
	GiftCon selectGiftCon(Orders order) throws SQLException;
	
	/**
	 * 기프티콘 사용
	 * @throws SQLException 
	 */
	int orderByGiftCon(Orders order) throws SQLException;
	
}
