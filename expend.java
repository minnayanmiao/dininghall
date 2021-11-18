//for expend class, we will use the price of order as one parameter. we will get oldbalance from database, and use oldbalance subtract the price of order 
//to get the new balance. getbalance() function and expendmoney() function will return an Array for the price of order and newbalance for the user.
package dininghallUSER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dininghallUSER.accountid;
import java.util.ArrayList;
import java.util.List;
public class expend {
	public Float[] expendmoney() {
		/**
		 * @param
		 * @return balance,pricetotal
		 */
		Float ar[]=new Float[2];
		String url="jdbc:mysql://localhost:3306/abc";
		String user="root";
		String password="123";
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			ar=getbalance(conn);
			//  deletedata(conn);
			conn.close();
			}catch(ClassNotFoundException e) {
				System.out.println("sorrry, can't find the driver");
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				System.out.println("the operation is good for database.");
			}
		return ar;
	}
	public Float[] getbalance(Connection conn) {
		/**
		 * @param conn
		 * @return balance, pricetotal
		 */
		float balance=0;
		float pricetotal=0;
		try {
			PreparedStatement psql;
			Statement stmt=conn.createStatement();
			ResultSet rs;
			psql=conn.prepareStatement("update account_detail set balance=? where account_id=?");
			accountid u=new accountid();
			String account_id=u.accountidd();
			rs=stmt.executeQuery("SELECT price FROM order_info WHERE account_id="+account_id);
			ArrayList<Float> price=new ArrayList<Float>();
			while (rs.next()) {
				Float price1=rs.getFloat("price");
				price.add(price1);
			}
			for (int index=0;index<price.size();index++) {
				pricetotal=pricetotal+price.get(index);
			}
			rs=stmt.executeQuery("SELECT balance FROM account_detail WHERE account_id="+account_id);
			Float[] oldbalance=new Float[1];
			while (rs.next()) {
				Float oldbalance1=rs.getFloat("balance");
				oldbalance[0]= oldbalance1;
			}
			/**
			 * @param pricetotal
			 * @return balance
			 */
			/*@
			 @ requires account_id!=null && pricetotal<=balance ;
			 @ ensures balance==\old(balance)-pricetotal;
			 */ 
			balance=oldbalance[0]-pricetotal;
			psql.setFloat(1,balance);
			psql.setString(2,account_id);
			psql.executeUpdate();
			psql.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("data has been updated");
		}
		Float ar[]=new Float[2];
		ar[0]=balance;
		ar[1]=pricetotal;
		return ar;
	}
	public void deletedata(Connection conn) {
		/**
		 * @param conn
		 * @return
		 */
		try {
		PreparedStatement psql;
		ResultSet rs;
		accountid u=new accountid();
		String account_id=u.accountidd();
	    psql=conn.prepareStatement("DELETE FROM order_info WHERE account_id=?");
	    psql.setString(1, account_id);
	    psql.executeUpdate();
	    psql.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("data has been deleted");
		}	
	}
}
