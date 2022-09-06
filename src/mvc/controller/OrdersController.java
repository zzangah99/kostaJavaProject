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
		  orderService.insertOrders(order);
		  EndView.printMessage("주문이 완료되었습니다");
		}catch (Exception e) {
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
