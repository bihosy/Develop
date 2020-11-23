package asd;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.xml.stream.util.EventReaderDelegate;

public class pro_show extends JFrame implements ActionListener {
	
	static Image[] Image = new Image[10];
	
	
	JLabel lb_name,lb_price,lb_size;
	static JLabel lb_name2, lb_price2, lb_size2, lb_color,lb_img;
	static String[][] String_size = new String [9][3];
	static int price=0;
	
	static int cc =0;
	static String[] bas_name = new String[10];
	static String[] bas_price = new String[10];
	static String[] bas_size = new String[10];
	JButton btn_baguni; // 장바구니 버튼
	JButton btn_buy; // 구매 버튼
	static JTextArea ta_under; // 상품설명 텍스트에어리어
	Border lineBorder = BorderFactory.createLineBorder(Color.black, 3);
    // 텍스트와 TextArea 경계 사이에 여백을 두기 위해서 emptyBorder를 생성합니다.
    Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
    //TextArea에 lineBorder(검정테두리), emptyBorder(여백)로 구성된 복합 경계선을 설정합니다.
    
    
    String[] String_color = {"흰색(White)", "회색(Gray)", "검정색(Black)", "파란색(Blue)"};
	
	JComboBox cb_size; // 사이즈 콤보박스
	JComboBox cb_color; // 색상 콤보박스
	
	public pro_show(){
		
		
		
		lb_name = new JLabel("상품명");
		lb_price = new JLabel("가  격");
		lb_size = new JLabel("사이즈");
		lb_color = new JLabel("색  상");
		lb_name2 = new JLabel();
		lb_price2 = new JLabel();
		lb_img = new JLabel();
		btn_baguni = new JButton("장바구니");
		btn_baguni.addActionListener(this);
		btn_buy = new JButton("구매");
		ta_under = new JTextArea();
		ta_under.setEditable(false);;
		//ta_under.setText();
		
		
		cb_size = new JComboBox(String_size[Shop_nomal.sizenum]);		
		cb_color = new JComboBox(String_color);
		
		this.setLayout(null);
		
		lb_img.setBounds(50, 50, 150, 250);
		lb_img.setOpaque(true);
		lb_img.setBackground(Color.CYAN);
		lb_name.setBounds(270, 65, 50, 25);
		lb_price.setBounds(270, 115, 50, 25);
		lb_size.setBounds(270, 165, 50, 25);
		lb_color.setBounds(270, 215, 50, 25);
		lb_name2.setBounds(370, 50, 175, 50);
		lb_price2.setBounds(370, 115, 175, 25);
		cb_size.setBounds(370, 165, 175, 25);
		cb_color.setBounds(370, 215, 175, 25);
		
		btn_baguni.setBounds(250, 265, 125, 35);
		btn_buy.setBounds(420, 265, 125, 35);
		ta_under.setBounds(50, 320, 500, 200);
	    ta_under.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		
		lb_name.setHorizontalAlignment(JLabel.LEFT);
		lb_price.setHorizontalAlignment(JLabel.LEFT);
		lb_size.setHorizontalAlignment(JLabel.LEFT);
		lb_name2.setHorizontalAlignment(JLabel.CENTER);
		lb_price2.setHorizontalAlignment(JLabel.RIGHT);
		
		this.setSize(600, 600);
		
		setLocationRelativeTo(null);
		this.add(lb_name);
		this.add(lb_price);
		this.add(lb_size);
		this.add(lb_color);
		this.add(lb_name2);
		this.add(lb_price2);
		this.add(cb_size);
		this.add(lb_img);
		this.add(cb_color);
		this.add(btn_baguni);
		this.add(btn_buy);
		this.add(ta_under);
		
		
		
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
			bas_name[cc]=lb_name2.getText();
			bas_price[cc]=lb_price2.getText();
			bas_size[cc]=cb_size.getSelectedItem().toString();
			cc++;
			price = price + Integer.parseInt(Shop_nomal.get_price);
			System.out.println(cc);
			this.setDefaultCloseOperation(3);
			this.setVisible(false);
			//System.out.println(Shop_nomal.bas_count);
			
		}
		
	}

}
