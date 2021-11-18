// for deposit class, we could get the amount of money that the user wants to deposit. In this class, the amount_of_money()function will
//return a float value for the amount_of_money.
package dininghallUSER;
import java.awt.event.*;
import java.sql.Connection;
import javax.swing.*; 
import java.awt.*; 
import dininghallUSER.depositmoney;
public class deposit implements ActionListener{
	static JTextField l;
	static JFrame frame;
	public void depositaction() { 
		/**
		 * @param 
		 * @return 
		 */
		JFrame frame=new JFrame("deposit");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deposit c=new deposit();
		JPanel P=new JPanel();
		JButton bconfirm;
		l=new JTextField(16);
		bconfirm=new JButton("confirm");
		bconfirm.addActionListener(c);
		P.add(bconfirm);
		P.add(l);
		frame.add(P);
        frame.setSize(800, 600);
        frame.show(); 
	}
	public float amount_of_money() {
		/**
		 * @param
		 * return amount_of_money
		 */
		float amount_of_money=Float.parseFloat(l.getText());
		return amount_of_money;
	}
	public void actionPerformed(ActionEvent e) {
		/**
		 * @param e
		 * @return
		 */
		float balance=0;
		String s=e.getActionCommand();
		if (s.charAt(0)== 'c') {
			depositmoney t=new depositmoney();
			balance=t.depositmoneyfunction();
			JDialog d=new JDialog(frame,"balance");
			JLabel l=new JLabel("the balance is "+String.valueOf(balance));
			d.add(l);
			d.setSize(100,100);
			d.setVisible(true);
			}
	}
}
