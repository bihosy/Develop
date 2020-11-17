package asd;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Shop_UI extends JFrame {

	JPanel pnl = new JPanel();
	JPanel pnl_logoinfo = new JPanel();
	JPanel pnl_menu = new JPanel();
	JPanel pnl_btn = new JPanel();
	JPanel pnl_main = new JPanel();
	JPanel pnl_image = new JPanel();
	JPanel pnl_info = new JPanel();
	

	JPanel[] pnl_arry = new JPanel[8];
	JPanel[] pnl_arry2 = new JPanel[8];
	JPanel[] pnl_arry3 = new JPanel[8]; //상품정보
	JPanel[] pnl_arry4 = new JPanel[8]; //상품정보 내용
	
	JLabel[] lb_name = new JLabel[8];
	JLabel[] lb_size = new JLabel[8];
	JLabel[] lb_price = new JLabel[8];
	JLabel[] lb_jumun = new JLabel[8];
	JLabel[] lb_1day = new JLabel[8];
	JLabel[] lb_image = new JLabel[8];
	JLabel[] lb_name_arry = new JLabel[8];
	JLabel[] lb_size_arry = new JLabel[8];
	JLabel[] lb_price_arry = new JLabel[8];
	JLabel lb_top_logo = new JLabel("이미지"); // 팀로고 or 쇼핑몰 로고
	JLabel lb_top_info1 = new JLabel("아이디 : "); // 가입할때 입력한 아이디
	JLabel lb_top_info2 = new JLabel("등급 : "); // admin or user
	JLabel lb_top_setinfo1 = new JLabel(" rozen"); // 임시로 rozen넣어놈 작업할땐 지우고 받아온 아이디를 여기 라벨에 붙이면됨 
	JLabel lb_top_setinfo2 = new JLabel("admin");// 임시로 admin넣어놈 작업할땐 지우고 받아온 아이디를 여기 라벨에 붙이면됨 

	String[] str_name = { "전성훈의 반반투구1", "전성훈의 반반투구2", "전성훈의 반반투구3", "전성훈의 반반투구4", "전성훈의 반반투구5", "전성훈의 반반투구6",
			"전성훈의 반반투구7", "전성훈의 반반투구8" };
	String[] str_size = { "M~L(90~100)", "M~L(90~100)", "M~L(90~100)", "M~L(90~100)", "M~L(90~100)", "M~L(90~100)",
			"M~L(90~100)", "M~L(90~100)" };
	String[] str_price = { "250메소", "250메소", "250메소", "250메소", "250메소", "250메소", "250메소", "250메소" };
	String[] str_btn_name = { "모자", "상의", "아웃터", "하의", "신발", "악세서리" };
	int i;
	int j;
	int k;
	JButton[] btn_menu = new JButton[6];
	BufferedImage[] img=null;
	
	
	public Shop_UI() {
		
		
		this.setTitle("전성훈바보");
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		pnl.setLayout(new BorderLayout());
		pnl_menu.setLayout(new BorderLayout());
		pnl_btn.setLayout(new GridLayout(0, 6, 15, 0));
		pnl_main.setLayout(new GridLayout(0, 4));
		pnl_logoinfo.setLayout(new GridLayout(0, 2));
		pnl_info.setLayout(new GridLayout(2, 0));
		this.add(pnl);
		pnl.add(pnl_logoinfo, "North");
		pnl.add(pnl_menu, "Center");

		pnl_menu.add(pnl_btn, "North");
		pnl_menu.add(pnl_main, "Center");

		pnl_logoinfo.add(pnl_image);
		pnl_logoinfo.add(pnl_info);
		pnl_image.add(lb_top_logo);
		pnl_info.add(lb_top_info1);
		pnl_info.add(lb_top_setinfo1);
		pnl_info.add(lb_top_info2);
		pnl_info.add(lb_top_setinfo2);

		for (int j = 0; j < btn_menu.length; j++) {
			btn_menu[j] = new JButton(str_btn_name[j]);
			pnl_btn.add(btn_menu[j]);
		}

		for (int i = 0; i < pnl_arry.length; i++) {
			lb_image[i] = new JLabel("이미지자리");
			pnl_arry[i] = new JPanel();
			pnl_arry2[i] = new JPanel();
			pnl_arry3[i] = new JPanel();
			pnl_arry4[i] = new JPanel();
			
			
			
			pnl_arry3[i].setLayout(new BoxLayout(pnl_arry3[i], BoxLayout.Y_AXIS));
			pnl_arry4[i].setLayout(new BoxLayout(pnl_arry4[i], BoxLayout.Y_AXIS));
			
			pnl_arry[i].setLayout(new BorderLayout());
			lb_name[i] = new JLabel("이름 : ");
			lb_size[i] = new JLabel("사이즈 : ");
			lb_price[i] = new JLabel("가격 : ");
			lb_jumun[i] = new JLabel("[주문폭주]");
			lb_1day[i] = new JLabel("[당일배송]");
			lb_name_arry[i] = new JLabel(str_name[i]);
			lb_size_arry[i] = new JLabel(str_size[i]);
			lb_price_arry[i] = new JLabel(str_price[i]);
			
			pnl_main.add(pnl_arry[i]);
			pnl_arry[i].add(lb_image[i], "North");
			pnl_arry[i].add(pnl_arry2[i], "Center");
			
			pnl_arry2[i].add(pnl_arry3[i]);
			pnl_arry2[i].add(pnl_arry4[i]);
			
			pnl_arry3[i].add(lb_name[i]);
			pnl_arry4[i].add(lb_name_arry[i]);
			pnl_arry3[i].add(lb_size[i]);
			pnl_arry4[i].add(lb_size_arry[i]);
			pnl_arry3[i].add(lb_price[i]);
			pnl_arry4[i].add(lb_price_arry[i]);
			pnl_arry3[i].add(lb_jumun[i]);
			pnl_arry4[i].add(lb_1day[i]);

		}

		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Shop_UI();
	}

}
