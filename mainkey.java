// for mainkey class, this will make a window with the selection of expend or deposit.
package dininghallUSER;
import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*; 
import dininghallUSER.deposit;
import dininghallUSER.expend;
class mainkey implements ActionListener {
	JFrame frame;
	public void mainkeyaction() {
		/**
		 * @param 
		 * @return
		 */
		JFrame frame=new JFrame("the user account");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainkey m=new mainkey();
		JButton bexpend, bdeposit;
		bexpend=new JButton("expend");
        bdeposit=new JButton("deposit");
        JPanel P=new JPanel();
        bexpend.addActionListener(m);
        bdeposit.addActionListener(m);
        P.add(bexpend);
        P.add(bdeposit);
        frame.add(P);
        frame.setSize(800, 600);
        frame.show();
	}
	public void actionPerformed(ActionEvent e) {
		/**
		 * @param e
		 * @return
		 */
		float balance=0;
		float pricetotal=0;
		String s=e.getActionCommand();
		if (s.charAt(0)== 'd') {
			deposit z=new deposit();
			z.depositaction();
			z.actionPerformed(e);
		}else if(s.charAt(0)=='e'){
			expend j=new expend();
			balance=j.expendmoney()[0];
			pricetotal=j.expendmoney()[1];
			if(balance<0) {
				JDialog d=new JDialog(frame,"balance");
				JLabel l=new JLabel("you do not have enough money");
				d.add(l);
				d.setSize(100,100);
				d.setVisible(true);
			}else {
			JDialog d=new JDialog(frame,"balance and expend");
			JLabel l=new JLabel("the balance is "+String.valueOf(balance)+"the expend is"+String.valueOf(pricetotal));
			d.add(l);
			d.setSize(100,100);
			d.setVisible(true);
			}
		}
	 }
}
