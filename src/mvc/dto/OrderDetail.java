package mvc.dto;

public class OrderDetail {
	
	String totalPrice;
	int totalQuantity;

		
		public OrderDetail() {}
		public OrderDetail(String totalPrice, int totalQuantity) {
			super();
			this.totalPrice = totalPrice;
			this.totalQuantity = totalQuantity;
		}



		public String getTotalPrice() {
			return totalPrice;
		}


		public void setTotalPrice(String totalPrice) {
			this.totalPrice = totalPrice;
		}


		public int getTotalQuantity() {
			return totalQuantity;
		}


		public void setTotalQuantity(int totalQuantity) {
			this.totalQuantity = totalQuantity;
		}


		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("OrderDetail [totalPrice=");
			builder.append(totalPrice);
			builder.append(", totalQuantity=");
			builder.append(totalQuantity);
			builder.append("]");
			return builder.toString();
		}
	
}
