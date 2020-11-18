package asd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Shop_nomal extends JFrame implements ActionListener, MouseListener {

	// --------DB 테이블 관련 --------//

	String header[] = { "상품번호", "사진경로", "상품명", "가격", "사이즈", "내용" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model, top_model, pants_model, shoes_model;
	JTable tb_Pro, tb_top, tb_pants, tb_shoes;
	// --------DB 테이블 관련 --------//

	Color bakcc = new Color(255, 255, 255, 0);

	// ----------------파일 리드라이트-----------//
	BufferedReader br = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
	PrintWriter pw = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구
	BufferedReader brtop = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
	PrintWriter pwtop = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구
	BufferedReader brpants = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
	PrintWriter pwpants = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구
	BufferedReader brshoes = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
	PrintWriter pwshoes = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구

	FileReader fr = null;// 파일객체 읽어오기
	FileWriter fw = null;// 파일객체 쓰기
	FileReader frtop = null;// 파일객체 읽어오기
	FileWriter fwtop = null;// 파일객체 쓰기
	FileReader frpants = null;// 파일객체 읽어오기
	FileWriter fwpants = null;// 파일객체 쓰기
	FileReader frshoes = null;// 파일객체 읽어오기
	FileWriter fwshoes = null;// 파일객체 쓰기

	String gr = "product.txt";// 경로저장
	String topgr = "top.txt";// 경로저장
	String pantsgr = "pants.txt";// 경로저장
	String shoesgr = "shoes.txt";// 경로저장

	String img_gr = "noimage.png";
	String size = "";

	String l;// 파일 읽어서 문자열 저장
	String nae;
	String sizevalue;
	static int sizenum;
	static int sizesize = 0;
	static int naesize = 0;
	int selnum = 0;

	int idx = 0;
	// ----------------파일 리드라이트-----------//
	ImageIcon background = new ImageIcon("ddd.jpg");
	JPanel pnl = new JPanel();
	JPanel pnl_logoinfo = new JPanel();
	JPanel pnl_menu = new JPanel();
	JPanel pnl_btn = new JPanel();
	JPanel pnl_img = new JPanel(); // 상단 이미지 패널
	JPanel pnl_main = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(background.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
	JPanel pnl_image = new JPanel();
	JPanel pnl_info = new JPanel();

	JPanel[] pnl_arry = new JPanel[9];

	JPanel[] pnl_arry2 = new JPanel[9];
	JPanel[] pnl_arry3 = new JPanel[9]; // 상품정보
	JPanel[] pnl_arry4 = new JPanel[9]; // 상품정보 내용

	JLabel[] lb_name = new JLabel[9];
	JLabel[] lb_size = new JLabel[9];
	JLabel[] lb_price = new JLabel[9];
	JLabel[] lb_jumun = new JLabel[9];
	JLabel[] lb_1day = new JLabel[9];
	JLabel[] lb_image = new JLabel[9];
	JLabel[] lb_name_arry = new JLabel[9];
	JLabel[] lb_size_arry = new JLabel[9];
	JLabel[] lb_price_arry = new JLabel[9];
	JLabel lb_top_logo = new JLabel("이미지"); // 팀로고 or 쇼핑몰 로고
	JLabel lb_top_info1 = new JLabel("아이디 : "); // 가입할때 입력한 아이디
	JLabel lb_top_info2 = new JLabel("등급 : "); // admin or user
	JLabel lb_top_setinfo1 = new JLabel(" rozen"); // 임시로 rozen넣어놈 작업할땐 지우고 받아온
													// 아이디를 여기 라벨에 붙이면됨
	JLabel lb_top_setinfo2 = new JLabel("admin");// 임시로 admin넣어놈 작업할땐 지우고 받아온
													// 아이디를 여기 라벨에 붙이면됨
	JLabel lb_Image;

	String[] str_btn_name = { "상의", "하의", "신발", "장바구니" };
	int i;
	int j;
	int k;
	JButton[] btn_menu = new JButton[str_btn_name.length];

	Image image = null;
	Image resizeImage = null;

	ImageIcon Image2 = new ImageIcon("");

	JScrollPane sp_pnl = new JScrollPane(pnl_main);

	public Shop_nomal() {

		// --------DB 테이블 관련 --------//
		Table_model = new DefaultTableModel(contents, header);
		top_model = new DefaultTableModel(contents, header);
		pants_model = new DefaultTableModel(contents, header);
		shoes_model = new DefaultTableModel(contents, header);

		tb_Pro = new JTable(Table_model);
		tb_top = new JTable(top_model);
		tb_pants = new JTable(pants_model);
		tb_shoes = new JTable(shoes_model);
		// --------DB 테이블 관련 --------//

		try {
			fr = new FileReader(gr);
			br = new BufferedReader(fr);// 읽어온 파일 버퍼에 객체 담기

			frtop = new FileReader(topgr);
			brtop = new BufferedReader(frtop);

			frpants = new FileReader(pantsgr);
			brpants = new BufferedReader(frpants);

			frshoes = new FileReader(shoesgr);
			brshoes = new BufferedReader(frshoes);

			while ((l = br.readLine()) != null) {

				String[] str = l.split("/");

				Table_model.addRow(str);
				idx = Integer.parseInt(str[0]) + 1;

			}
			while ((l = brtop.readLine()) != null) {

				String[] str = l.split("/");

				top_model.addRow(str);

			}
			while ((l = brpants.readLine()) != null) {

				String[] str = l.split("/");

				pants_model.addRow(str);

			}
			while ((l = brshoes.readLine()) != null) {

				String[] str = l.split("/");

				shoes_model.addRow(str);

			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				fr.close();
				frtop.close();
				frpants.close();
				frshoes.close();

				br.close();
				brtop.close();
				brpants.close();
				brshoes.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		lb_Image = new JLabel();

		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		pnl.setLayout(new BorderLayout());
		pnl_menu.setLayout(new BorderLayout());
		pnl_btn.setLayout(new GridLayout(0, str_btn_name.length, 15, 0));
		pnl_main.setLayout(new GridLayout(0, 4));
		pnl_logoinfo.setLayout(new GridLayout(0, 2));
		pnl_info.setLayout(new GridLayout(2, 0));
		this.add(pnl);
		pnl.add(pnl_logoinfo, "North");
		pnl.add(pnl_menu, "Center");

		pnl_logoinfo.setPreferredSize(new Dimension(1000, 100));
		lb_Image.setPreferredSize(new Dimension(1000, 100));

		// lb_Image

		 

		pnl_logoinfo.add(lb_Image);

		pnl_menu.add(pnl_btn, "North");

		pnl_image.add(lb_top_logo);
		pnl_info.add(lb_top_info1);
		pnl_info.add(lb_top_setinfo1);
		pnl_info.add(lb_top_info2);
		pnl_info.add(lb_top_setinfo2);
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

		for (int j = 0; j < btn_menu.length; j++) {
			btn_menu[j] = new JButton(str_btn_name[j]);
			btn_menu[j].addActionListener(this);
			pnl_btn.add(btn_menu[j]);
			btn_menu[j].setCursor(cursor);
		}

		/*
		 * for(int asd=0; asd<8; asd++){ for(int zxc=0; zxc<3; zxc++){
		 * pro_show.String_size[asd][zxc]=null; } }
		 */

		for (int i = 0; i < Table_model.getRowCount(); i++) {

			img_gr = Table_model.getValueAt(i, 1).toString();

			String pro_name = Table_model.getValueAt(i, 2) + "";
			String pro_price = Table_model.getValueAt(i, 3) + "";
			String pro_size = Table_model.getValueAt(i, 4) + "";

			nae = Table_model.getValueAt(i, 5) + "";
			sizevalue = Table_model.getValueAt(i, 4) + "";
			String[] sizestr = sizevalue.split(",");

			sizesize = sizestr.length;

			int sizei;
			int stri;
			for (sizei = 0; sizei < sizestr.length; sizei++) {

				pro_show.String_size[i][sizei] = sizestr[sizei];

			}

			System.out.println();

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
			pnl_arry[i].setBackground(bakcc);
			pnl_arry2[i].setBackground(bakcc);
			pnl_arry3[i].setBackground(bakcc);
			pnl_arry4[i].setBackground(bakcc);

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

			pnl_arry[i].add(lb_image[i], "North");
			pnl_arry2[i].addMouseListener(this);
			pnl_arry3[i].addMouseListener(this);
			pnl_arry4[i].addMouseListener(this);

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
			pnl_main.add(pnl_arry[i]);

		}

		int scrollcount = (Table_model.getRowCount() / 4) + 1;
		int scroll = 400;
		pnl_main.setPreferredSize(new Dimension(950, scroll * scrollcount));
		// pnl_menu.add(sp_pnl, "Center");
		pnl_menu.add(pnl_main, "Center");

		pnl_menu.add(pnl_main);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Shop_nomal();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == btn_menu[0]) {
			for (int asd = 0; asd < 9; asd++) {
				for (int zxc = 0; zxc < 3; zxc++) {
					pro_show.String_size[asd][zxc] = null;
				}
			}
			selnum = 1;
			pnl_main.removeAll();
			for (int i = 0; i < top_model.getRowCount(); i++) {

				img_gr = top_model.getValueAt(i, 1).toString();

				String pro_name = top_model.getValueAt(i, 2) + "";
				String pro_price = top_model.getValueAt(i, 3) + "";
				String pro_size = top_model.getValueAt(i, 4) + "";

				nae = top_model.getValueAt(i, 5) + "";
				sizevalue = top_model.getValueAt(i, 4) + "";
				String[] sizestr = sizevalue.split(",");

				sizesize = sizestr.length;

				int sizei;
				int stri;

				for (sizei = 0; sizei < sizestr.length; sizei++) {
					pro_show.String_size[i][sizei] = "";
					pro_show.String_size[i][sizei] = sizestr[sizei];

				}

				System.out.println();

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
				pnl_arry[i].setBackground(bakcc);
				pnl_arry2[i].setBackground(bakcc);
				pnl_arry3[i].setBackground(bakcc);
				pnl_arry4[i].setBackground(bakcc);
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

				pnl_arry[i].add(lb_image[i], "North");
				pnl_arry[i].addMouseListener(this);

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
				pnl_main.add(pnl_arry[i]);

			}
			pnl_menu.add(pnl_main, "Center");
			revalidate();
			repaint();
		}

		else if (e.getSource() == btn_menu[1]) {
			selnum = 2;
			pnl_main.removeAll();
			for (int i = 0; i < pants_model.getRowCount(); i++) {

				img_gr = pants_model.getValueAt(i, 1).toString();

				String pro_name = pants_model.getValueAt(i, 2) + "";
				String pro_price = pants_model.getValueAt(i, 3) + "";
				String pro_size = pants_model.getValueAt(i, 4) + "";

				nae = pants_model.getValueAt(i, 5) + "";
				sizevalue = pants_model.getValueAt(i, 4) + "";
				String[] sizestr = sizevalue.split(",");

				sizesize = sizestr.length;

				int sizei;
				int stri;
				for (sizei = 0; sizei < sizestr.length; sizei++) {

					pro_show.String_size[i][sizei] = sizestr[sizei];

				}

				System.out.println();

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
				pnl_arry[i].setBackground(bakcc);
				pnl_arry2[i].setBackground(bakcc);
				pnl_arry3[i].setBackground(bakcc);
				pnl_arry4[i].setBackground(bakcc);
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

				pnl_arry[i].add(lb_image[i], "North");
				pnl_arry[i].addMouseListener(this);

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
				pnl_main.add(pnl_arry[i]);

			}
			pnl_menu.add(pnl_main, "Center");
			revalidate();
			repaint();
		} else if (e.getSource() == btn_menu[2]) {
			selnum = 3;
			pnl_main.removeAll();
			sp_pnl.removeAll();
			for (int i = 0; i < shoes_model.getRowCount(); i++) {

				img_gr = shoes_model.getValueAt(i, 1).toString();

				String pro_name = shoes_model.getValueAt(i, 2) + "";
				String pro_price = shoes_model.getValueAt(i, 3) + "";
				String pro_size = shoes_model.getValueAt(i, 4) + "";

				nae = shoes_model.getValueAt(i, 5) + "";
				sizevalue = shoes_model.getValueAt(i, 4) + "";
				String[] sizestr = sizevalue.split(",");

				sizesize = sizestr.length;

				int sizei;
				int stri;

				for (sizei = 0; sizei < sizestr.length; sizei++) {

					pro_show.String_size[i][sizei] = sizestr[sizei];

				}

				System.out.println();

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
				pnl_arry[i].setBackground(bakcc);
				pnl_arry2[i].setBackground(bakcc);
				pnl_arry3[i].setBackground(bakcc);
				pnl_arry4[i].setBackground(bakcc);
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

				pnl_arry[i].add(lb_image[i], "North");
				pnl_arry[i].addMouseListener(this);

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
				pnl_main.add(pnl_arry[i]);

			}
			
			int scrollcount = (Table_model.getRowCount() / 4) + 1;
			int scroll = 400;
			pnl_main.setPreferredSize(new Dimension(950, scroll * scrollcount));
			sp_pnl = new JScrollPane(pnl_main);
			// pnl_menu.add(pnl_main, "Center");
			pnl_menu.add(sp_pnl, "Center");

			// pnl_menu.add(pnl_main, "Center");
			revalidate();
			repaint();
		}

		else if (e.getSource() == btn_menu[3]) {
			selnum = 4;
			pnl_main.removeAll();
			sp_pnl.removeAll();
			for (int i = 0; i < Table_model.getRowCount(); i++) {

				img_gr = Table_model.getValueAt(i, 1).toString();

				String pro_name = Table_model.getValueAt(i, 2) + "";
				String pro_price = Table_model.getValueAt(i, 3) + "";
				String pro_size = Table_model.getValueAt(i, 4) + "";

				nae = Table_model.getValueAt(i, 5) + "";
				sizevalue = Table_model.getValueAt(i, 4) + "";
				String[] sizestr = sizevalue.split(",");

				sizesize = sizestr.length;

				int sizei;
				int stri;

				for (sizei = 0; sizei < sizestr.length; sizei++) {

					pro_show.String_size[i][sizei] = sizestr[sizei];

				}

				System.out.println();

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
				pnl_arry[i].setBackground(bakcc);
				pnl_arry2[i].setBackground(bakcc);
				pnl_arry3[i].setBackground(bakcc);
				pnl_arry4[i].setBackground(bakcc);
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

				pnl_arry[i].add(lb_image[i], "North");
				pnl_arry[i].addMouseListener(this);

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
				pnl_main.add(pnl_arry[i]);

			}
			sp_pnl = new JScrollPane(pnl_main);

			int scrollcount = (Table_model.getRowCount() / 4) + 1;
			int scroll = 400;
			pnl_main.setPreferredSize(new Dimension(950, scroll * scrollcount));

			pnl_menu.add(pnl_main, "Center");
			pnl_menu.add(sp_pnl, "Center");
			revalidate();
			repaint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (selnum == 1) {

			for (int i = 0; i < top_model.getRowCount(); i++) {
				if (e.getSource() == pnl_arry[i]) {
					sizenum = i;
					Image image2 = null;
					Image resizeImage2 = null;
					String str;
					new pro_show();

					try {
						image2 = ImageIO.read(new File(top_model.getValueAt(i, 1).toString()));
						resizeImage2 = image2.getScaledInstance(150, 250, Image.SCALE_SMOOTH);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String set_name = this.lb_name_arry[i].getText();
					String set_price = this.lb_price_arry[i].getText() + " 원";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));

					str = top_model.getValueAt(i, 5).toString();
					str = str.replace("<br>", "\n");
					pro_show.ta_under.setText(str);

				}

			}

		}

		if (selnum == 2) {

			for (int i = 0; i < pants_model.getRowCount(); i++) {
				if (e.getSource() == pnl_arry[i]) {
					sizenum = i;
					Image image2 = null;
					Image resizeImage2 = null;
					String str;
					new pro_show();

					try {
						image2 = ImageIO.read(new File(pants_model.getValueAt(i, 1).toString()));
						resizeImage2 = image2.getScaledInstance(150, 250, Image.SCALE_SMOOTH);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String set_name = this.lb_name_arry[i].getText();
					String set_price = this.lb_price_arry[i].getText() + " 원";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));

					str = pants_model.getValueAt(i, 5).toString();
					str = str.replace("<br>", "\n");
					pro_show.ta_under.setText(str);

				}

			}

		}
		if (selnum == 3) {

			for (int i = 0; i < shoes_model.getRowCount(); i++) {
				if (e.getSource() == pnl_arry[i]) {
					sizenum = i;
					Image image2 = null;
					Image resizeImage2 = null;
					String str;
					new pro_show();

					try {
						image2 = ImageIO.read(new File(shoes_model.getValueAt(i, 1).toString()));
						resizeImage2 = image2.getScaledInstance(150, 250, Image.SCALE_SMOOTH);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String set_name = this.lb_name_arry[i].getText();
					String set_price = this.lb_price_arry[i].getText() + " 원";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));

					str = shoes_model.getValueAt(i, 5).toString();
					str = str.replace("<br>", "\n");
					pro_show.ta_under.setText(str);

				}

			}

		}

		if (selnum == 4) {
			for (int i = 0; i < Table_model.getRowCount(); i++) {
				if (e.getSource() == pnl_arry[i]) {
					sizenum = i;
					Image image2 = null;
					Image resizeImage2 = null;
					String str;
					new pro_show();

					try {
						image2 = ImageIO.read(new File(Table_model.getValueAt(i, 1).toString()));
						resizeImage2 = image2.getScaledInstance(150, 250, Image.SCALE_SMOOTH);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String set_name = this.lb_name_arry[i].getText();
					String set_price = this.lb_price_arry[i].getText() + " 원";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));

					str = Table_model.getValueAt(i, 5).toString();
					str = str.replace("<br>", "\n");
					pro_show.ta_under.setText(str);

				}

			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
