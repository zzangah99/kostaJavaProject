package mvc.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Orders;

public interface OrdersDAO {
	/**
	 * 주문하기 
	 * 1) order 테이블에 insert
	 * 2) orderline테이블에 insert 
	 * 3) 재고량(stock)감소시키기(update)
	 * 4) 기프티콘 생성 주문이었으면 기프티콘 코드 생성해서 기프티콘 테이블에 insert
	 * 
	 * 기프티콘 사용 주문
	 * 1) 코드 입력받기
	 * 2) 코드에 해당하는 정보로 주문하기(주문 내역의 총 결제금액은 0)
	 */
	String[] orderInsert(Orders order) throws SQLException;

	/**
	 * 주문내역보기
	 */
	List<Orders> selectOrdersByUserId(String userId) throws SQLException;
	
}
