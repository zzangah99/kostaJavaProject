package mvc.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * DB 연동을 위한 로드,연결,닫기
 *
 */

public class DbUtil {
	private static Properties profile  = new Properties();	//Map 형태(key,value)
	
	/**
	 * 로드 : 연결하려는 DBMS를 선택(DB lib가 필요)
	 * static {} 또는 생성자에서 하는 게 좋음
	 */
	static {
		try {
			//2개의 외부 ~.properties 파일을 로딩
			profile.load(new FileInputStream("resources/dbInfo.properties"));
			profile.load(new FileInputStream("resources/query.properties"));
			
			Class.forName(profile.getProperty("driverName"));	//getProperty() : 인수로 key를 받아 value를 뽑는다
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getProfile() {
		return profile;
	}
	
	
	/**
	 * 연결
	 * 
	 */
	public static Connection getConnection() throws SQLException{
		Connection con = DriverManager.getConnection(profile.getProperty("url"), profile.getProperty("userName"), profile.getProperty("userPass"));
		
		return con;
	}
	
	
	/**
	 * 닫기 - 사용된 객체 닫기 - select인 경우
	 */
	public static void dbClose(Connection con, Statement st, ResultSet rs) {
		try{
			if(rs != null) rs.close();
			dbClose(con,st);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 닫기 - 사용된 객체 닫기 - DML or DDL인 경우
	 */
	public static void dbClose(Connection con, Statement st) {
		try{
			if(st != null) st.close();
			if(con != null) con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/*public static void main(String[] args) {
		try {
			System.out.println("======= 메인 시작 ======");
			Connection con = DbUtil.getConnection();
			System.out.println("con = " + con);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}*/
}
