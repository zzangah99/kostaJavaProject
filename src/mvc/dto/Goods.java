package mvc.dto;

public class Goods {

		private int goodsCode;
		private String goodsName;
		private int    goodsPrice;
		private String goodsDetail;
		private String soldout;
		private int    stock;
		private int categoryCode;
		private String userId;
		
		public Goods() {}
		
		public Goods(int goodsCode, String goodsName, int goodsPrice, String goodsDetail, String soldout, int stock, int categoryCode) {
			super();
			this.goodsCode = goodsCode;
			this.goodsName = goodsName;
			this.goodsPrice = goodsPrice;
			this.goodsDetail = goodsDetail;
			this.soldout = soldout;
			this.stock =stock;
			this.categoryCode = categoryCode;
			
		}
		
		public String getUserId() {
			return userId;
		}
		
		public void setUserId(String userId) {
			this.userId = userId;
		}

		public int getGoodsCode() {
			return goodsCode;
		}

		public void setGoodsCode(int goodsCode) {
			this.goodsCode = goodsCode;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public int getGoodsPrice() {
			return goodsPrice;
		}

		public void setGoodsPrice(int goodsPrice) {
			this.goodsPrice = goodsPrice;
		}

		public String getGoodsDetail() {
			return goodsDetail;
		}

		public void setGoodsDetail(String goodsDetail) {
			this.goodsDetail = goodsDetail;
		}
		
		public String getSoldOut() {
			return soldout;
		}
		
		public void setSoldOut(String soldout) {
			this.soldout = soldout;
		}
		
		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public int getCategoryCode() {
			return categoryCode;
		}

		public void setCategoryCode(int categoryCode) {
			this.categoryCode = categoryCode;
		}

		@Override
		public String toString() {
			return goodsCode + " | " + goodsName +" | " + " | " + goodsPrice + " | " + goodsDetail + " | " + soldout + " | " + stock + " | " + categoryCode;
		}

		 /**
		   *  객체가 다르더라도 상품번호가 같으면 무조건 같다라고 이해하기 위해 오버라이드
		   */
//			@Override
//			public int hashCode() {
//				final int prime = 31;
//				int result = 1;
//				result = prime * result + ((goodsCode == null) ? 0 : goodsCode.hashCode());
//				return result;
//			}
	//
//			@Override
//			public boolean equals(Object obj) {
//				if (this == obj)
//					return true;
//				if (obj == null)
//					return false;
//				if (getClass() != obj.getClass())
//					return false;
//				
//				Goods other = (Goods) obj;
//				if (goodsCode == null) {
//					if (other.goodsCode != null)
//						return false;
//				} else if (!goodsCode.equals(other.goodsId))
//					return false;
//				return true;
//			}
		 /**
		    @Override
			public int hashCode() {
		    	
				return goodsCode.hashCode();
			}
		 
		 @Override
			public boolean equals(Object obj) {
			
//			    if (this == obj)
//					return true;
//				if (obj == null)
//					return false;
//				if (getClass() != obj.getClass())
//					return false;
				
				Goods other = (Goods) obj;
				if(goodsCode.equals(other.goodsCode)) {
					return true;
				}else {
					return false;
				}
				
			}
			**/
	}




