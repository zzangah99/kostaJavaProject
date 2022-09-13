package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import mvc.dao.GoodsDAO;
import mvc.dao.GoodsDAOImpl;
import mvc.dto.GiftCon;
import mvc.dto.Goods;
import mvc.dto.Option;
import mvc.dto.OrderLine;
import mvc.dto.Orders;
import mvc.util.DbUtil;

public class OrdersDAOImpl implements OrdersDAO {
	GoodsDAO goodsDao = new GoodsDAOImpl();
	private Properties profile = DbUtil.getProfile();
	Set<String> codeSet = new HashSet<String>();
	
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
	
	@Override
	public String[] orderInsert(Orders order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		//insert into order_order (user_id, order_quan, order_price, user_cp_code, order_payment, gift_code, take_out) 
		//values (?, ?, ?, ?, ?, ?, ?)
		String sql = profile.getProperty("orderOrder.insertAll");//insert into order_order (user_id, order_quan, order_price, user_cp_code, order_payment, gift_code, take_out) values (?, ?, ?, ?, ?, ?, ?)
		String resultArr[] = new String[2];
		int orderTotal[] = getOrderTotal(order);
		
		try {
		   con = DbUtil.getConnection();
		   con.setAutoCommit(false);
		   
		   ps = con.prepareStatement(sql);
		   
		   ps.setString(1, order.getUserId());
		   ps.setInt(2, orderTotal[1]);//총구매수량
		   ps.setInt(3, orderTotal[0]);//총구매금액구하는 메소드 호출
		   ps.setString(4, order.getUserCpCode());
		   ps.setString(5, order.getOrderPayment());
		   ps.setString(6, order.getGiftCode());
		   ps.setString(7, order.getTakeOut());
		   
		   int result = ps.executeUpdate();
		   if(result==0) {
			   con.rollback();
			   throw new SQLException("주문에 실패하였습니다");
		   }
		   else {
			   int re [] = orderLineInsert(con, order); //주문상세 등록하기 
			   for(int i : re) {
				   if(i != 1) {//
					   con.rollback();
					   throw new SQLException("주문에 실패하였습니다");
				   }
			   }
			   
			   //주문수량만큼 재고량 감소하기
			   decrementStock(con, order.getOrderLineList());
			   //con.commit();
		   }
		   
		   resultArr[0] = Integer.toString(result);//성공여부
		   if(order.getCheckGiftCon() == 1) resultArr[1] = orderCodeReturn(con);//기프티콘코드
		   else resultArr[1] = null;
		   
    }finally {
  	  	con.commit();
    	DbUtil.dbClose(con, ps , null);
    }
		
		return resultArr;
	}
	
	
	
	/**
	 * 주문상세 등록하기 
	 * */
	public int[] orderLineInsert(Connection con, Orders order) throws SQLException{
		PreparedStatement ps = null;
		String sql = profile.getProperty("orderDetail.insert");
		//insert into order_detail (detail_code, order_code, goods_code, detail_price, detail_quan, goods_name) values (detail_seq.nextval, order_seq.currval, ?, ?, ?, ?)
		int result[] = null;
		
		try {
			ps = con.prepareStatement(sql);
			for (OrderLine orderline : order.getOrderLineList()) {
				Goods goods = goodsDao.goodsSelectBygoodsCode(orderline.getGoodsCode());
				
				ps.setInt(1, orderline.getGoodsCode());//상품코드
				ps.setInt(2, goods.getGoodsPrice() * orderline.getDetailQuan());//상품별 구매금액
				ps.setInt(3, orderline.getDetailQuan());// 상품별 구매 수량
				ps.setString(4, orderline.getGoodsName());//상품이름
				ps.addBatch(); 
				ps.clearParameters();
				ps.executeBatch();
				
				//System.out.println("orderline.getOptionList().size() = "+orderline.getOptionList().size());
				//System.out.println("orderline.getOptionList() = " +  orderline.getOptionList());
				//옵션
				int re = optionListInsert(con, orderline.getOptionList().get(0)); //주문 상세 하나에 옵션이 여러개 등록 
				if(re != 1) {
					con.rollback();
					throw new SQLException("상품의 옵션 처리 중 주문에 실패하였습니다");
				}
			}
			result = ps.executeBatch();

		} finally {
			DbUtil.dbClose(null, ps, null);
		}
		
		return result;
		
	}
	
	
	/**
	 * 상세 주문에 옵션 등록하기
	 */
	//public int optionListInsert(Connection con, OrderLine orderline) throws SQLException{
	public int optionListInsert(Connection con, Option op) throws SQLException{
		PreparedStatement ps = null;
		
		//insert into option_op ( size_code, tem, syrup, def, whip ) values (?, ?, ?, ?, ?)
		//insert into option_op ( size_code, tem, syrup, def, whip ) values (?, ?, ?, ?, ?)
		int result = 0;
		
		try {
			
		//	for (Option option : orderline.getOptionList()) {
				//ps.setInt(1, orderline.getDetailCode());
				String sql = profile.getProperty("optionOp.insert");
				ps = con.prepareStatement(sql);
				ps.setInt(1, op.getSizeCode());
				ps.setString(2, op.getTem());
				ps.setString(3, op.getSyrup());
				ps.setString(4, op.getDef());
				ps.setString(5, op.getWhip());
				
				result = ps.executeUpdate();
			//}
			
			

		} finally {
			DbUtil.dbClose(null, ps, null);
		}
		
		return result;
	}
	
	
	
	/**
	 * 상품으로 재고량 감소시키키
	 * */
	public int[] decrementStock(Connection con , List<OrderLine> orderLineList)throws SQLException {
		PreparedStatement ps = null;
		String sql = profile.getProperty("goods.updateStockCountByCode");
		//update goods set stock = stock-? where goods_code = ?
		int result[] = null;
		
		try {
			ps = con.prepareStatement(sql);
			for (OrderLine orderline : orderLineList) {
				ps.setInt(1, orderline.getDetailQuan());
				ps.setInt(2, orderline.getGoodsCode());

				ps.addBatch(); // 일괄처리할 작업에 추가
				ps.clearParameters();
			}

			result = ps.executeBatch();// 일괄처리
		} finally {
			DbUtil.dbClose(null, ps, null);
		}

		return result;
	}
	
	
	/**
	 * 상품 총구매금액,수량 구하기
	 * */
	public int[] getOrderTotal(Orders order) throws SQLException {
		List<OrderLine> orderLineList= order.getOrderLineList();
	    int total[]=new int[2];
	   
		for(OrderLine line : orderLineList) {
			Goods goods = goodsDao.goodsSelectBygoodsCode(line.getGoodsCode());
			if(goods==null)throw new SQLException("상품번호 오류로 주문에 실패하였습니다");
			else if((goods.getStock() <= 0 )) throw new SQLException("재고량 부족으로 주문에 실패하였습니다");
			
			total[0] += line.getDetailQuan() * goods.getGoodsPrice();//구매금액
			total[1] += line.getDetailQuan();//구매수량
		}
		return total;
	}
	
	
	/**
	 * 주문내역 보기
	 * */
	@Override
	public List<Orders> selectOrdersByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = profile.getProperty("orderOrder.selectRecentById");
		//select order_code, user_id, to_char(order_time, 'YYYY-MM-DD HH24:MI:SS'), order_quan, order_price, user_cp_code, order_payment, gift_code, take_out  from order_order where user_id = ?
		List<Orders> list = new ArrayList<Orders>();
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Orders orders = new Orders(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));

				// 주문번호에 해당하는 상세정보 가져오기
				List<OrderLine> orderLineList = selectOrderLine(con, orders.getOrderCode());// 메소드 호출

				orders.setOrderLineList(orderLineList); // 주문 하나에 대한 상세 list 추가
				list.add(orders);
			}
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	
	/**
	 * 주문번호에 해당하는 주문상세 가져오기
	 * */
	public List<OrderLine> selectOrderLine(Connection con, int orderCode)throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = profile.getProperty("orderDetail.selectAllByOrderCode");
		//select * from order_detail where order_code = ?
		List<OrderLine> list = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderCode);
			rs = ps.executeQuery();

			while (rs.next()) {
				OrderLine orderLine = new OrderLine(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
				List<Option> opList = selectOption(con, orderLine.getDetailCode());
				orderLine.setOptionList(opList);
				
				list.add(orderLine);
			}
		} finally {
			DbUtil.dbClose(null, ps, rs);
		}
		return list;

	}
	
	/**
	 * 주문상세에 해당하는 옵션 가져오기
	 * */
	public List<Option> selectOption(Connection con, int detailCode)throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = profile.getProperty("optionOp.selectAllByDetailCode");
		//select * from option_op where detail_code = ?
		List<Option> opList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, detailCode);
			rs = ps.executeQuery();

			while (rs.next()) {
				String[] size = getSizeName(con, rs.getInt(2));
				Option option = new Option(rs.getInt(1), rs.getInt(2), size[0] ,rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				
				opList.add(option);
			}
		} finally {
			DbUtil.dbClose(null, ps, rs);
		}
		return opList;

	}
	
	/**
	 * 사이즈 이름,가격 가져오기
	 */
	public String[] getSizeName(Connection con, int sizeCode) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = profile.getProperty("sizeOp.selectSize");
		//select size_size, size_price from size_op where size_code = ?
		String size[] = new String[2];
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, sizeCode);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				if(rs.getString(1) == null) size[0] = "없음";
				else size[0] = rs.getString(1);//사이즈이름
				size[1] = Integer.toString(rs.getInt(2));//사이즈가격
			}
			
		} finally {
			DbUtil.dbClose(null, ps, rs);
		}
		
		return size;
	}
	
	/**
	 * 주문코드 이용해서 기프트콘 생성
	 */
	public String orderCodeReturn(Connection con) throws SQLException {
		PreparedStatement ps = null;
		String sql = profile.getProperty("giftInfo.insert");
		//insert into gift_info values(? , order_seq.currval, sysdate, 'N')
		String giftCode = randomCode();

		try {
			//con = DbUtil.getConnection();
			//con.setAutoCommit(false);

			ps = con.prepareStatement(sql);
			ps.setString(1, giftCode);

			int result = ps.executeUpdate();
			if (result == 0) {
				con.rollback();
				throw new SQLException("기프티콘 생성에 실패하였습니다");
			}

		} finally {
			DbUtil.dbClose(con, ps, null);
		}

		return giftCode;
	}
	
	/**
	 * 랜덤 코드 생성
	 */
	public String randomCode() {
		Random ran = new Random();
		int setSize = codeSet.size();
		String ranCode = null;
		
		while(codeSet.size() == setSize) {
			ranCode = Integer.toString(ran.nextInt(10000000)+1);
			ranCode = String.format("%08d", ranCode);
			codeSet.add(ranCode);
			if(codeSet.size() > setSize) break;
		}
		System.out.println("기프트코드: "+ranCode);
		return ranCode;
	}
	
}
