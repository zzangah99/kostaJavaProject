package mvc.dao;

import java.sql.SQLException;
import java.util.List;
import mvc.dto.Orders;

public interface GiftConDAO {
	/**
	 * 기프티콘 코드생성
	 */
	int GiftConInsert(Orders order) throws SQLException;
	
	/**
	 * 기프티콘 코드로 정보 출력
	 */
	List<Orders> giftConInfo(String giftCode) throws SQLException;

	
	
}
