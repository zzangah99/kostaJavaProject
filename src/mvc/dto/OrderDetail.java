<<<<<<< HEAD
package mvc.dto;

public class OrderDetail {
	
	int totalPrice;
	int totalQuantity;

		
		public OrderDetail() {}
		public OrderDetail(int totalPrice, int totalQuantity) {
			super();
			this.totalPrice = totalPrice;
			this.totalQuantity = totalQuantity;
		}



		public int getTotalPrice() {
			return totalPrice;
		}


		public void setTotalPrice(int totalPrice) {
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
=======
package mvc.dto;

public class OrderDetail {
	
	int totalPrice;
	int totalQuantity;

		
		public OrderDetail() {}
		public OrderDetail(int totalPrice, int totalQuantity) {
			super();
			this.totalPrice = totalPrice;
			this.totalQuantity = totalQuantity;
		}



		public int getTotalPrice() {
			return totalPrice;
		}


		public void setTotalPrice(int totalPrice) {
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
>>>>>>> yunabranch/yuna
