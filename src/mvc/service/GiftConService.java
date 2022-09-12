package mvc.service;

import java.sql.SQLException;

import mvc.dao.GiftConDAO;
import mvc.dao.GiftConDAOImpl;
import mvc.dto.GiftCon;
import mvc.dto.Orders;

public class GiftConService {
	GiftConDAO giftConDao = new GiftConDAOImpl();
	
	/**
	  * 기프티콘 주문하기
	  */
	 public void orderByGiftCon(Orders orders) throws SQLException{
		 int result = giftConDao.orderByGiftCon(orders);
		 if(result == 0) throw new SQLException("기프티콘 사용에 실패했습니다");
	 }
	 
	 /**
	  * 기프티콘 조회
	  */
	 public GiftCon selectGiftCon(Orders order) throws SQLException{
		 GiftCon giftcon = giftConDao.selectGiftCon(order);
		 if(giftcon == null) throw new SQLException("입력하신 기프티콘이 존재하지 않습니다");
		 else if(giftcon.getGiftFlag().equals("Y")) throw new SQLException("이미 사용된 기프티콘 입니다");
		 
		 return giftcon;
	 }
}
