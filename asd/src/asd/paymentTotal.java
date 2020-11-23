package asd;

import javax.swing.*;
import javax.swing.GroupLayout.Group;
import javax.swing.text.GapContent;
import java.awt.*;
import java.awt.event.*;

public class paymentTotal extends JFrame implements ActionListener{
	
	
	JPanel pnl_outside = new JPanel(); // 전체 패널
	JPanel pnl = new JPanel(); // 첫번째줄 //큰패널
	JPanel pnl2 = new JPanel(); // 두번째줄
	JPanel pnl3 = new JPanel(); // 세번째줄
	JPanel pnl4 = new JPanel(); // 네번째줄
	JPanel pnl5 = new JPanel(); // 다섯번째줄
	JPanel pnl6 = new JPanel(); // 여섯번째줄
	JPanel pnl7 = new JPanel(); // 일곱번째줄

	JPanel pnl8 = new JPanel(); // 작은패널
	JPanel pnl9 = new JPanel();
	JPanel pnl10 = new JPanel();
	JPanel pnl11 = new JPanel();
	JPanel pnl12 = new JPanel();
	JPanel pnl13 = new JPanel();
	JPanel pnl14 = new JPanel();
	JPanel pnl15 = new JPanel();
	JPanel pnl16 = new JPanel();
	JPanel pnl17 = new JPanel();
	JPanel pnl18 = new JPanel();
	JPanel pnl19 = new JPanel();
	JPanel pnl20 = new JPanel();
	JPanel pnl21 = new JPanel();
	JPanel pnl22 = new JPanel();
	JPanel pnl23 = new JPanel();
	JPanel pnl24 = new JPanel();

	JLabel lb_info = new JLabel("배송 정보");
	static JLabel lb_Requiredinput = new JLabel("*필수입력사항");
	JLabel lb_deliverylocation = new JLabel("배송지 선택");
	JLabel lb_from = new JLabel("받으시는분*");
	JLabel lb_address = new JLabel("주소*");
	JLabel lb_homenumber = new JLabel("집전화");
	JLabel lb_phonenumber = new JLabel("휴대전화*");
	JLabel lb_deliverymessage = new JLabel("베송메시지");
	// JLabel lb_radiobtn = new JLabel("주문지 정보와 동일");
	// JLabel lb_radiobtn2 = new JLabel("새로운 배송지");
	JLabel lb_simpleaddress = new JLabel("기본주소");
	JLabel lb_detailedaddress = new JLabel("상세주소");
	JLabel lb_bar = new JLabel("-");
	JLabel lb_bar2 = new JLabel("-");
	JLabel lb_bar3 = new JLabel("-");
	JLabel lb_bar4 = new JLabel("-");

	JRadioButton rb = new JRadioButton("주문자 정보와 동일");
	JRadioButton rb2 = new JRadioButton("새로운 배송지");

	JTextField tf = new JTextField(17);
	
	JTextField tf3 = new JTextField(20);
	JTextField tf4 = new JTextField(20);
	
	String[] str = {"02","051","055","032","031"};
	String[] str2 = {"010","011","016","017"};
	JComboBox cb = new JComboBox(str);
	JComboBox cb2 = new JComboBox(str2);
	
	JTextField tf5 = new JTextField(4);
	JTextField tf6 = new JTextField(4);
	JTextField tf7 = new JTextField(4);
	JTextField tf8 = new JTextField(4);
	JTextField tf9 = new JTextField(4);
	JTextField tf10 = new JTextField(4);
	JTextField tf11 = new JTextField(20);

	JButton address_btn = new JButton("우편번호");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	payment py = new payment();
	
	CusInfo ci = new CusInfo();
	
	JScrollPane sc;
	JPanel pnls;
	
	public paymentTotal() {
		
		
		
		
		
		
		
		
		
		
		pnl.setLayout(new BorderLayout());
		pnl2.setLayout(new BorderLayout());
		pnl3.setLayout(new BorderLayout());
		pnl4.setLayout(new BorderLayout());
		pnl5.setLayout(new BorderLayout());
		pnl6.setLayout(new BorderLayout());
		pnl7.setLayout(new BorderLayout());
		pnl15.setLayout(new GridLayout(3, 0));
		//pnl15.setBackground(Color.blue);
		pnl8.setSize(150, 50); //패널 크기조정
		pnl10.setSize(150, 50);
		pnl12.setSize(150, 50);
		pnl14.setSize(150, 50);
		pnl16.setSize(150, 50);
		pnl18.setSize(150, 50);
		pnl20.setSize(150, 50);
		
	

		pnl.add(pnl8);
		pnl.add(pnl9);
		pnl2.add(pnl10);
		pnl2.add(pnl11);
		pnl3.setLayout(null);
		pnl12.setBounds(18,13,80,80);
		pnl13.setBounds(153,13,200,80);
		pnl3.add(pnl12);
		pnl3.add(pnl13);
		
		pnl4.add(pnl14);
		pnl4.add(pnl15);
		pnl5.add(pnl16);
		pnl5.add(pnl17);
		pnl6.add(pnl18);
		pnl6.add(pnl19);
		pnl7.add(pnl20);
		pnl7.add(pnl21);
		
		
		
		

		pnl_outside.setLayout(new GridLayout(7,1));
		
		pnl8.add(lb_info);
		pnl9.add(lb_Requiredinput);// 필수입력사항

		pnl10.add(lb_deliverylocation);
		pnl11.add(rb); // 라디오버튼
		pnl11.add(rb2);
		pnl12.add(lb_from);
		pnl13.add(tf);// 텍스트필드
		pnl13.setLayout(new FlowLayout());
		
		pnl14.add(lb_address);

		pnl15.add(pnl22);
		pnl15.add(pnl23);
		pnl15.add(pnl24);

		
	
		pnl22.add(tf3);
		pnl22.add(lb_simpleaddress);
		pnl23.add(tf4);
		pnl23.add(lb_detailedaddress);

		pnl16.add(lb_homenumber);
		pnl17.add(cb);
		pnl17.add(lb_bar);
		pnl17.add(tf6);
		pnl17.add(lb_bar2);
		pnl17.add(tf7);
		pnl18.add(lb_phonenumber);
		pnl19.add(cb2);
		pnl19.add(lb_bar3);
		pnl19.add(tf9);
		pnl19.add(lb_bar4);
		pnl19.add(tf10);
		pnl20.add(lb_deliverymessage);
		pnl21.add(tf11);
		
		
		pnl_outside.add(pnl);
		pnl_outside.add(pnl2);
		pnl_outside.add(pnl3);
		pnl_outside.add(pnl4);
		pnl_outside.add(pnl5);
		pnl_outside.add(pnl6);
		pnl_outside.add(pnl7);
		
		
		
		ButtonGroup begin = new ButtonGroup();
		
		begin.add(rb);
		begin.add(rb2);
		
		rb.addActionListener(this);
		rb2.addActionListener(this);
		
		
		
		
		
		
		
		
		
		
		
		
		setSize(630, 1000);
		
		setTitle("payment2");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pnls=new JPanel(new GridLayout(0,1));

		
		
		pnls.add(ci);
		
		pnls.add(pnl_outside);
		
		pnls.add(py);
		pnls.setPreferredSize(new Dimension(590, 1800));
		sc = new JScrollPane(pnls);
		sc.getVerticalScrollBar().setUnitIncrement(16);
		
		
		this.add(sc);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new paymentTotal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource()==rb){
			tf.setText(CusInfo.fd_reciever.getText());
			cb.setSelectedIndex(CusInfo.cb_home.getSelectedIndex());
			cb2.setSelectedIndex(CusInfo.cb_phone.getSelectedIndex());
			tf3.setText(CusInfo.fd_address2.getText());
			tf4.setText(CusInfo.fd_address3.getText());
			tf6.setText(CusInfo.fd_home.getText());
			tf7.setText(CusInfo.fd_home2.getText());
			tf9.setText(CusInfo.fd_phone.getText());
			tf10.setText(CusInfo.fd_phone2.getText());
		}
		if(e.getSource()==rb2){
			tf.setText("");
			cb.setSelectedIndex(0);
			cb2.setSelectedIndex(0);
			tf3.setText("");
			tf4.setText("");
			tf6.setText("");
			tf7.setText("");
			tf9.setText("");
			tf10.setText("");
		}
		
		
		
		
	}

}
