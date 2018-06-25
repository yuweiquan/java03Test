


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

	private static String driver = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@192.168.32.128:1521/orcl";
	private static String user = "scott";
	private static String password = "tiger";
	
	static{
		//注册驱动
		//DriverManager.registerDriver(driver)
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection(){
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			if (conn!=null) {
				System.out.println("连接成功");
				
			} else {
			System.out.println("连接失败");
			}
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 运行Java：
	 * java -Xms100M -Xmx200M HelloWorld
	 * 
	 * 技术方向：
	 * 1、性能优化
	 * 2、故障诊断：死锁(JDK: ThreadDump)
	 *               Oracle: 自动处理
	 */
	public static void release(Connection conn,Statement st,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null; ///-----> 原因：Java GC: Java的GC不受代码的控制
			}
		}
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				st = null;
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
	}
}












