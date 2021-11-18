//For depositmoney class, we will get oldbalance from database.the parameter we will use is the amount of money.
// it will return the newbalnce after deposit the money.

package dininghallUSER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dininghallUSER.accountid;
import dininghallUSER.deposit;
public class depositmoney {
	/**
	 * @param balance
	 * @return
	 */
	/*@
	 @ invariant (\forall float balance;balance>=0);
	*/
	public float depositmoneyfunction() {
		/**
		 * @param
		 * @return balance
		 */
	float balance=0;
	String url="jdbc:mysql://localhost:3306/abc";
	String user="root";
	String password="123";
	Connection conn=null;
	Statement stmt=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url,user,password);
		insertdata(conn);
		balance=updatedata(conn);
		conn.close();
		}catch(ClassNotFoundException e) {
			System.out.println("sorrry, can't find the driver");
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			System.out.println("the operation is good for database.");
		}
	return balance;
	}
		
	public void insertdata(Connection conn) {
		/**
		 * @param conn
		 * @return 
		 */
		try {
			PreparedStatement psql=conn.prepareStatement("insert into account_detail(account_id,balance)"+"values(?,?)");
			int i=1;
			while (i<=20) {
				i++;
				psql.setInt(1,i);
				psql.setFloat(2,0);
				psql.executeUpdate();
			}
			psql.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("data is inserted succesfully");
		}
	}
	public float updatedata(Connection conn) {
		/**
		 * @param conn
		 * return balance
		 */
		float balance=0;
		try {
			PreparedStatement psql;
			Statement stmt=conn.createStatement();
			ResultSet rs;
			psql=conn.prepareStatement("update account_detail set balance=? where account_id=?");
			accountid u=new accountid();
			deposit o=new deposit();
			String account_id=u.accountidd();
			Float amount_of_money= o.amount_of_money();
			rs=stmt.executeQuery("SELECT balance FROM account_detail WHERE account_id="+account_id);
			Float[] oldbalance=new Float[1];
			while (rs.next()) {
				Float oldbalance1=rs.getFloat("balance");
				oldbalance[0]= oldbalance1;
			}
			/**
			 * @param amount_of_money
			 * return balance
			 */
			/*@ 
			 @ requires  amount_of_money>0 && account_id!=null;
			 @ ensures balance==\old(balance)+amount_of_money;
			 */
			balance=oldbalance[0]+amount_of_money;
			System.out.println(oldbalance[0]);
			System.out.println(balance);
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
		return balance;
	}
	
}
