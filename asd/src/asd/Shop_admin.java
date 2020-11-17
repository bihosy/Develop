package asd;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Shop_admin extends JFrame implements ActionListener {
	
	
	
	// --------DB 테이블 관련 --------//
		String header[] = { "상품번호", "사진경로", "상품명", "가격", "사이즈","내용" };
		String contents[][] = {};
		String s = "";
		DefaultTableModel Table_model;
		JTable tb_Pro;
		// --------DB 테이블 관련 --------//

		// ----------------파일 리드라이트-----------//
		BufferedReader br = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
		PrintWriter pw = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구

		FileReader fr = null;// 파일객체 읽어오기
		FileWriter fw = null;// 파일객체 쓰기

		String gr = "product.txt";// 경로저장
		String img_gr = "noimage.png";
		String size = "";

		String l;// 파일 읽어서 문자열 저장
		int idx = 0;
		// ----------------파일 리드라이트-----------//
	
	
	
	
	
	
	

	JPanel pnl = new JPanel();
	JPanel pnl_logoinfo = new JPanel();
	JPanel pnl_menu = new JPanel();
	JPanel pnl_btn = new JPanel();
	JPanel pnl_main = new JPanel();
	JPanel pnl_image = new JPanel();
	JPanel pnl_info = new JPanel();
	JPanel pnl_under = new JPanel();
	

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

	
	String[] str_btn_name = { "모자", "상의", "아웃터", "하의", "신발", "악세서리" };
	int i;
	int j;
	int k;
	JButton[] btn_menu = new JButton[6];
	JButton btn_write = new JButton("글쓰기");
	Image image = null;
	Image resizeImage = null;
	
	
	public Shop_admin() {
		
		
		
		// --------DB 테이블 관련 --------//
				Table_model = new DefaultTableModel(contents, header);
				tb_Pro = new JTable(Table_model);
				// --------DB 테이블 관련 --------//

				try {
					fr = new FileReader(gr);
					br = new BufferedReader(fr);// 읽어온 파일 버퍼에 객체 담기

					while ((l = br.readLine()) != null) {

						String[] str = l.split("/");

						System.out.print(str[0] + "/");
						System.out.print(str[1] + "/");
						System.out.print(str[2] + "/");
						System.out.print(str[3] + "/");
						System.out.print(str[4] + "/");
						System.out.println(str[5]);

						Table_model.addRow(str);
						idx = Integer.parseInt(str[0]) + 1;

					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					try {
						fr.close();
						br.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

		
		
		
		
		
		
		
		
		

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

		for (int i = 0; i< Table_model.getRowCount(); i++) {

			img_gr =Table_model.getValueAt(i, 1).toString();
			String pro_name = Table_model.getValueAt(i, 2)+"";
			String pro_price = Table_model.getValueAt(i, 3)+"";
			String pro_size = Table_model.getValueAt(i, 4)+"";
			try {
				image = ImageIO.read(new File(img_gr));
				resizeImage = image.getScaledInstance(150, 250, Image.SCALE_SMOOTH);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			lb_image[i] = new JLabel();
			pnl_arry[i] = new JPanel();
			pnl_arry2[i] = new JPanel();
			pnl_arry3[i] = new JPanel();
			pnl_arry4[i] = new JPanel();
			lb_image[i].setIcon(new ImageIcon(resizeImage));
			
			
			
			pnl_arry3[i].setLayout(new BoxLayout(pnl_arry3[i], BoxLayout.Y_AXIS));
			pnl_arry4[i].setLayout(new BoxLayout(pnl_arry4[i], BoxLayout.Y_AXIS));
			
			pnl_arry[i].setLayout(new FlowLayout());
			lb_name[i] = new JLabel("이름 : ");
			lb_size[i] = new JLabel("사이즈 : ");
			lb_price[i] = new JLabel("가격 : ");
			lb_jumun[i] = new JLabel("[주문폭주]");
			lb_1day[i] = new JLabel("[당일배송]");
			lb_name_arry[i] = new JLabel(pro_name);
			lb_size_arry[i] = new JLabel(pro_size);
			lb_price_arry[i] = new JLabel(pro_price);
			
			pnl_main.add(pnl_arry[i]);
			pnl_arry[i].add(lb_image[i], "North");
			
			pnl_arry[i].add(pnl_arry2[i], "Center");
			
			pnl_arry2[i].add(pnl_arry3[i]);
			pnl_arry2[i].add(pnl_arry4[i]);
			
			pnl_arry3[i].add(lb_name[i]);
			pnl_arry3[i].add(lb_size[i]);
			pnl_arry3[i].add(lb_price[i]);
			pnl_arry3[i].add(lb_jumun[i]);
			
			pnl_arry4[i].add(lb_name_arry[i]);
			pnl_arry4[i].add(lb_size_arry[i]);
			pnl_arry4[i].add(lb_price_arry[i]);
			pnl_arry4[i].add(lb_1day[i]);

		}
		pnl_under.add(btn_write);
		btn_write.addActionListener(this);
		this.add(pnl_under, "South");

		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Shop_admin();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new Write();
		this.setDefaultCloseOperation(3);
		this.setVisible(false);
	}

}
