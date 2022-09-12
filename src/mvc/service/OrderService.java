package mvc.service;

import java.sql.SQLException;
import java.util.List;

import mvc.dao.OrdersDAO;
import mvc.dao.OrdersDAOImpl;
import mvc.dto.Orders;

public class OrderService {
	OrdersDAO orderDao = new OrdersDAOImpl();
	
   /**
    * 주문하기
    * */
	 public String[] insertOrders(Orders orders) throws SQLException{
		String result[] =  orderDao.orderInsert(orders);
		if(result[0].equals("0"))throw new SQLException("주문이 실패하였습니다.");
		return result;
	 }
	 
	 /**
	  * 주문 내역보기
	  * */
	 public List<Orders> selectOrdersByUserId(String userId)throws SQLException{
		 List<Orders> list = orderDao.selectOrdersByUserId(userId);
		 if(list==null || list.size()==0)throw new SQLException(userId+" 님의 주문내역이 없습니다.");
		 return list;
	 }
	 
}