package dininghallUSER;
import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*; 
import dininghallUSER.mainkey;
public class accountid implements ActionListener {
	static JTextField b;
	public static void main(String[] args) {
		
		JFrame frame=new JFrame("the user accountid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accountid c=new accountid();
		JPanel P=new JPanel();
		JLabel account_id=new JLabel();
		JButton bconfirm;
		account_id.setText("account id:");
		b=new JTextField(16);
		bconfirm=new JButton("confirm");
		bconfirm.addActionListener(c);
		P.add(bconfirm);
		P.add(b);
		frame.add(P);
        frame.setSize(800, 600);
        frame.show(); 
	}
	public void actionPerformed(ActionEvent e) {
		String t;
		String s=e.getActionCommand();
		JFrame frame=new JFrame("the user account");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (s.charAt(0)== 'c') {
			t=accountidd();
			int result = Integer.parseInt(t);
			if (result>20) {
				JDialog d=new JDialog(frame,"account id");
				JLabel l=new JLabel("account is is not in the system");
				d.add(l);
				d.setSize(100,100);
				d.setVisible(true);
			}else {
			mainkey i=new mainkey();
			i.mainkeyaction();
			i.actionPerformed(e);
			}
		}
	}
	public String accountidd() {
		  return b.getText();
	}
}
