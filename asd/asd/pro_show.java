package asd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.stream.util.EventReaderDelegate;

public class pro_show extends JFrame implements ActionListener {
	
	static Image[] Image = new Image[99];
	
	JLabel lb_name,lb_price,lb_size;
	static JLabel lb_name2, lb_price2, lb_size2, lb_color,lb_img;
	static String[][] String_size = new String [99][3];
	static int price=0;
	JPanel pnl_main = new JPanel(null);
	
	static int cc =0;
	static String[] bas_name = new String[99];
	static String[] bas_price = new String[99];
	static String[] bas_size = new String[99];
	JButton btn_baguni; // 장바구니 버튼
	JButton btn_buy; // 구매 버튼
	static JTextArea ta_under; // 상품설명 텍스트에어리어
	Border lineBorder = BorderFactory.createLineBorder(Color.black, 3);
    // 텍스트와 TextArea 경계 사이에 여백을 두기 위해서 emptyBorder를 생성합니다.
    Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
    //TextArea에 lineBorder(검정테두리), emptyBorder(여백)로 구성된 복합 경계선을 설정합니다.
    
    
    String[] String_color = {"흰색(White)", "회색(Gray)", "검정색(Black)", "파란색(Blue)"};
	
	JComboBox cb_size; // 사이즈 콤보박스
	//JComboBox cb_color; // 색상 콤보박스
	
	public pro_show(){
		
		
		
		lb_name = new JLabel("상품명");
		lb_price = new JLabel("가  격");
		lb_size = new JLabel("사이즈");
	//	lb_color = new JLabel("색  상");
		lb_name2 = new JLabel();
		lb_price2 = new JLabel();
		lb_img = new JLabel();
		btn_baguni = new JButton("장바구니");
		btn_baguni.setBackground(Color.WHITE);
		btn_baguni.addActionListener(this);
		btn_buy = new JButton("구매");
		btn_buy.setBackground(Color.WHITE);
		btn_buy.addActionListener(this);
		ta_under = new JTextArea();
		ta_under.setEditable(false);;
		//ta_under.setText();
		
		
		cb_size = new JComboBox(String_size[Shop_nomal.sizenum]);		
	//	cb_color = new JComboBox(String_color);
		
		//this.setLayout(null);
		
		lb_img.setBounds(50, 50, 150, 250);
		lb_img.setOpaque(true);
		lb_img.setBackground(Color.CYAN);
		lb_name.setBounds(270, 65, 50, 25);
		lb_price.setBounds(270, 125, 50, 25);
		lb_size.setBounds(270, 185, 50, 25);
	//	lb_color.setBounds(270, 215, 50, 25);
		lb_name2.setBounds(370, 50, 175, 50);
		lb_price2.setBounds(370, 125, 175, 25);
		cb_size.setBounds(370, 185, 175, 25);
//		cb_color.setBounds(370, 215, 175, 25);
		
		btn_baguni.setBounds(250, 265, 125, 35);
		btn_buy.setBounds(420, 265, 125, 35);
		ta_under.setBounds(50, 320, 500, 200);
	    ta_under.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		
		lb_name.setHorizontalAlignment(JLabel.LEFT);
		lb_price.setHorizontalAlignment(JLabel.LEFT);
		lb_size.setHorizontalAlignment(JLabel.LEFT);
		lb_name2.setHorizontalAlignment(JLabel.RIGHT);
		lb_price2.setHorizontalAlignment(JLabel.RIGHT);
		
		this.setSize(600, 600);
		
		setLocationRelativeTo(null);
		pnl_main.add(lb_name);
		pnl_main.add(lb_price);
		pnl_main.add(lb_size);
	//	pnl_main.add(lb_color);
		pnl_main.add(lb_name2);
		pnl_main.add(lb_price2);
		pnl_main.add(cb_size);
		pnl_main.add(lb_img);
	//	pnl_main.add(cb_color);
		pnl_main.add(btn_baguni);
		pnl_main.add(btn_buy);
		pnl_main.add(ta_under);
		pnl_main.setBackground(Color.WHITE);
		this.add(pnl_main);
		
		
		
		this.setVisible(true);
		this.setResizable(false);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new pro_show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn_baguni){
			
			
			Shop_nomal.Bas_info[1]=lb_name2.getText();
			Shop_nomal.Bas_info[2]=cb_size.getSelectedItem().toString();
			Shop_nomal.Bas_info[3]=lb_price2.getText();
			
			
			Shop_nomal.bas_model.addRow(Shop_nomal.Bas_info);
			
			
			
			bas_name[cc]=lb_name2.getText();
			bas_price[cc]=lb_price2.getText();
			bas_size[cc]=cb_size.getSelectedItem().toString();
			cc++;
			price = price + Integer.parseInt(Shop_nomal.get_price);
			//System.out.println(cc);
			this.setDefaultCloseOperation(3);
			this.setVisible(false);
			//System.out.println(Shop_nomal.bas_count);
			
		}
		if(e.getSource()==btn_buy){
			new paymentTotal();
			this.setDefaultCloseOperation(3);
			paymentTotal.lb_Requiredinput.setText(lb_name2.getText()); 
			this.setVisible(false);
			
			for (int memi = 0; memi < Shop_nomal.mem_model.getRowCount(); memi++) {

				String sksnrl = Shop_nomal.mem_model.getValueAt(memi, 6).toString();
				String vhs = Shop_nomal.mem_model.getValueAt(memi, 5).toString();

				String[] adr = new String[2];
				String[] phnum = new String[3];
				adr = sksnrl.split("=");
				phnum = vhs.split("-");

				if (Shop_nomal.userid.equals(Shop_nomal.mem_model.getValueAt(memi, 0).toString())) {
					CusInfo.fd_reciever.setText(Shop_nomal.mem_model.getValueAt(memi, 2).toString());
					CusInfo.fd_address2.setText(adr[0]);
					CusInfo.fd_address3.setText(adr[1]);
					CusInfo.fd_phone.setText(phnum[1]);
					CusInfo.fd_phone2.setText(phnum[2]);

					if (phnum[0].equals("010")) {
						CusInfo.cb_phone.setSelectedIndex(0);
					}
					if (phnum[0].equals("011")) {
						CusInfo.cb_phone.setSelectedIndex(1);
					}
					if (phnum[0].equals("016")) {
						CusInfo.cb_phone.setSelectedIndex(2);
					}
					if (phnum[0].equals("017")) {
						CusInfo.cb_phone.setSelectedIndex(3);
					}

				}
			}
		}
		
	}

}
