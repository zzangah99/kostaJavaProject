package mvc.controller;

import java.sql.SQLException;
import java.util.List;

import mvc.dto.Orders;
import mvc.service.OrderService;
import mvc.view.EndView;
import mvc.view.FailView;

public class OrdersController {
    private static OrderService orderService = new OrderService();
	/**
	 * 주문하기
	 * */
	public static void insertOrders(Orders order) {
		try {
		  String result[] = orderService.insertOrders(order);
		  EndView.printMessage("주문이 완료되었습니다");
		  
		  if(result[1] != null) EndView.printMessage("기프티콘 코드는 "+ result[1] +" 입니다. 따로 저장해두신 뒤 코드를 입력해서 사용하세요");
		}catch (Exception e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}
	}
	
	/**
	 * 주문내역보기
	 * */
	public static void selectOrdersByUserId(String userId) {
		try {
			 List<Orders> orderList = orderService.selectOrdersByUserId(userId);
             EndView.printOrderByUserId(orderList);
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
			
		}
	}
	
}
