package asd;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.table.*;

public class Shop_nomal extends JFrame implements ActionListener, MouseListener, ItemListener {

	JPanel pnl_Basket = new JPanel(); // this에 붙어있는패널
	JPanel pnl_Basket_menu = new JPanel(); // pnl Center에 붙어있는 패널
	JPanel pnl_Basket_main = new JPanel();// menu에 Center로 붙어있는 패널
	JPanel pnl_Basket_buy = new JPanel();
	JPanel[] pnl_Basket_shopbasket = new JPanel[99];
	static String get_price;

	// --------DB 테이블 관련 --------//

	String headermem[] = { "ID", "PW", "이름", "성별", "생년월일", "휴대폰", "주소", "권한" };
	String header[] = { "상품번호", "사진경로", "상품명", "가격", "사이즈", "내용" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model, top_model, pants_model, shoes_model, mem_model;
	JTable tb_Pro, tb_top, tb_pants, tb_shoes, tb_mem;
	// --------DB 테이블 관련 --------//

	Color bakcc = new Color(255, 255, 255, 0);

	// ----------------파일 리드라이트-----------//
	FileReader memfr = null;// 파일객체 읽어오기
	BufferedReader membr = null;// 버퍼를 이용해서 만들어진 파일 읽기도구

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

	static String userid;
	String gr = "product.txt";// 경로저장
	String topgr = "top.txt";// 경로저장
	String pantsgr = "pants.txt";// 경로저장
	String shoesgr = "shoes.txt";// 경로저장
	String memgr = "Members.txt";// 경로저장
	JLabel[] lb_Basket_price = new JLabel[99];
	String img_gr = "noimage.png";
	String size = "";

	String l;// 파일 읽어서 문자열 저장
	String nae;
	String sizevalue;
	static int sizenum;
	static int sizesize = 0;
	static int naesize = 0;
	int selnum = 0;
	Dimension pnlsize = new Dimension();
	JLabel[] lb_Basket_name;
	int py;

	JLabel lb_Basket_gunprice = new JLabel();
	int idx = 0;
	// ----------------파일 리드라이트-----------//
	String[] bas_name;
	ImageIcon background = new ImageIcon("ddd.jpg");
	JPanel pnl = new JPanel();
	JPanel pnl_logoinfo = new JPanel();
	JPanel pnl_menu = new JPanel();
	JPanel pnl_spnl = new JPanel();
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
	JButton buybuy = new JButton("구매하기");
	JButton deldel = new JButton("삭제하기");
	JPanel[] pnl_arry = new JPanel[9];
	JCheckBox[] cb_del = new JCheckBox[9];

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
	JLabel lb_Image2; // 로고 들어갈 상단 라벨
	JLabel lb_WC; // 상단 우측 라벨(WelCome!)

	String[] str_btn_name = { "상의", "하의", "신발", "장바구니" };
	int i;
	int j;
	int k;
	JButton[] btn_menu = new JButton[str_btn_name.length];

	Image image = null;
	Image resizeImage = null;

	ImageIcon Image2 = new ImageIcon("logo2.png");
	Image resizeImage2 = null;
	Image resizeImage3 = null;

	Font WC_Font = new Font("돋움", Font.BOLD, 15);
	Font font = new Font("맑은 고딕",Font.PLAIN,15);
	Font font2 = new Font("맑은 고딕",Font.BOLD,17);
	
	JScrollPane sp_pnl, sp_pnl2;

	Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

	public Shop_nomal() {
		pnl_Basket_main.setLayout(new BoxLayout(pnl_Basket_main, BoxLayout.Y_AXIS));
		// pnl_Basket_main.setLayout(null);
		buybuy.addActionListener(this);
		pnl.setBackground(Color.WHITE);
		Color color = new Color(0,34,102);
		pnl_btn.setBackground(color);
		deldel.addActionListener(this);

		// --------DB 테이블 관련 --------//
		Table_model = new DefaultTableModel(contents, header);
		top_model = new DefaultTableModel(contents, header);
		pants_model = new DefaultTableModel(contents, header);
		shoes_model = new DefaultTableModel(contents, header);
		mem_model = new DefaultTableModel(contents, headermem);

		tb_Pro = new JTable(Table_model);
		tb_top = new JTable(top_model);
		tb_pants = new JTable(pants_model);
		tb_shoes = new JTable(shoes_model);
		tb_mem = new JTable(mem_model);
		// --------DB 테이블 관련 --------//

		try {

			memfr = new FileReader(memgr);
			membr = new BufferedReader(memfr);// 읽어온 파일 버퍼에 객체 담기

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
			while ((l = membr.readLine()) != null) {

				String[] str = l.split("/");

				mem_model.addRow(str);

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
				memfr.close();

				br.close();
				brtop.close();
				brpants.close();
				brshoes.close();
				membr.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		resizeImage2 = Image2.getImage();
		Image Image3 = resizeImage2.getScaledInstance(300, 100, Image.SCALE_SMOOTH);
		ImageIcon Image4 = new ImageIcon(Image3);
		lb_Image2 = new JLabel(Image4);
		lb_WC = new JLabel(Login.name + "님 환영합니다");

		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		pnl.setLayout(new BorderLayout());
		pnl_menu.setLayout(new BorderLayout());
		pnl_btn.setLayout(new GridLayout(0, str_btn_name.length, 15, 0));
		pnl_main.setLayout(new GridLayout(0, 4));
		pnl_logoinfo.setLayout(null);
		pnl_info.setLayout(new GridLayout(2, 0));
		this.add(pnl);
		pnl.add(pnl_logoinfo, "North");
		pnl.add(pnl_menu, "Center");
		pnl_logoinfo.setPreferredSize(new Dimension(1000, 100));
		pnl_logoinfo.setBackground(bakcc);
		lb_Image2.setBounds(340, 0, 300, 100);
		lb_WC.setBounds(850, 70, 200, 25);
		lb_WC.setFont(WC_Font);

		// lb_Image

		pnl_logoinfo.add(lb_Image2);

		pnl_menu.add(pnl_btn, "North");

		pnl_image.add(lb_top_logo);
		pnl_info.add(lb_top_info1);
		pnl_info.add(lb_top_setinfo1);
		pnl_info.add(lb_top_info2);
		pnl_info.add(lb_top_setinfo2);
		pnl_logoinfo.add(lb_WC);

		lb_Image2.setCursor(cursor);
		lb_Image2.addMouseListener(this);

		for (int j = 0; j < btn_menu.length; j++) {
			btn_menu[j] = new JButton(str_btn_name[j]);
			btn_menu[j].addActionListener(this);
			btn_menu[j].setCursor(cursor);
			btn_menu[j].setBackground(color);
			btn_menu[j].setBorderPainted(false);
			btn_menu[j].setForeground(Color.WHITE);
			pnl_btn.add(btn_menu[j]);

		}
		btn_menu[0].setFont(font2);
		btn_menu[1].setFont(font2);
		btn_menu[2].setFont(font2);
		btn_menu[3].setFont(font);

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
			pnl_arry[i].setCursor(cursor);
			pnl_main.add(pnl_arry[i]);

		}

		py = 400 * ((Table_model.getRowCount() / 4) + 1);

		pnl_main.setPreferredSize(new Dimension(900, py));
		sp_pnl = new JScrollPane(pnl_main);
		sp_pnl.getVerticalScrollBar().setUnitIncrement(16);

		pnl_menu.add(sp_pnl, "Center");

		// pnl_menu.add(sp_pnl);
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
			
			btn_menu[0].setFont(font2);
			btn_menu[1].setFont(font);
			btn_menu[2].setFont(font);
			btn_menu[3].setFont(font);

			selnum = 1;
			pnl_menu.removeAll();
			pnl_main.removeAll();
			sp_pnl.removeAll();
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

				// System.out.println();

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
				pnl_arry[i].setCursor(cursor);
				pnl_main.add(pnl_arry[i]);

			}

			py = 400 * ((top_model.getRowCount() / 4) + 1);
			pnl_main.setPreferredSize(new Dimension(900, py));
			sp_pnl = new JScrollPane(pnl_main);
			sp_pnl.getVerticalScrollBar().setUnitIncrement(16);
			pnl_menu.add(sp_pnl, "Center");
			pnl_menu.add(pnl_btn, "North");

			revalidate();
			repaint();
		}

		else if (e.getSource() == btn_menu[1]) {
			
			btn_menu[0].setFont(font);
			btn_menu[1].setFont(font2);
			btn_menu[2].setFont(font);
			btn_menu[3].setFont(font);
			
			selnum = 2;
			pnl_menu.removeAll();
			pnl_main.removeAll();
			sp_pnl.removeAll();
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
				pnl_arry[i].setCursor(cursor);
				pnl_main.add(pnl_arry[i]);

			}

			py = 400 * ((pants_model.getRowCount() / 4) + 1);
			pnl_main.setPreferredSize(new Dimension(900, py));
			sp_pnl = new JScrollPane(pnl_main);
			sp_pnl.getVerticalScrollBar().setUnitIncrement(16);
			pnl_menu.add(sp_pnl, "Center");
			pnl_menu.add(pnl_btn, "North");
			revalidate();
			repaint();
		}

		else if (e.getSource() == btn_menu[2]) {
			
			btn_menu[0].setFont(font);
			btn_menu[1].setFont(font);
			btn_menu[2].setFont(font2);
			btn_menu[3].setFont(font);
			
			selnum = 3;
			pnl_menu.removeAll();
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
				pnl_arry[i].setCursor(cursor);
				pnl_main.add(pnl_arry[i]);

			}

			py = 400 * ((shoes_model.getRowCount() / 4) + 1);
			pnl_main.setPreferredSize(new Dimension(900, py));
			sp_pnl = new JScrollPane(pnl_main);

			pnl_menu.add(sp_pnl, "Center");
			pnl_menu.add(pnl_btn, "North");

			sp_pnl.getVerticalScrollBar().setUnitIncrement(16);
			revalidate();
			repaint();
		}

		else if (e.getSource() == btn_menu[3]) {
			
			btn_menu[0].setFont(font);
			btn_menu[1].setFont(font);
			btn_menu[2].setFont(font);
			btn_menu[3].setFont(font2);

			// 장바구니 변수 //
			int bas_count = pro_show.cc; // bas_count 숫자에 따라 장바구니에 만들어지는 갯수가 달라짐
											// 2이면 장바구니에 패널 2개
			bas_name = pro_show.bas_name;
			String[] bas_price = pro_show.bas_price;
			String[] bas_size = pro_show.bas_size;
			// 생성현재는 2로
			// 주었음 장바구니에 넣은 상품의갯수를 받아오면됨

			pnl_Basket_buy.setBackground(Color.gray);
			JScrollPane sp_Basket_pnl;// menu에 Center로 붙어있는 패널

			// 장바구니 패널
			// bas_count만큼
			// 생성
			JLabel[] lb_Basket_shopbasket_image = new JLabel[bas_count]; // 장바구니
																			// 이미지bas_count만큼
																			// 생성

			JPanel[] pnl_Basket_product_title = new JPanel[bas_count]; // 상품정보
			JPanel[] pnl_Basket_product_description = new JPanel[bas_count]; // 상품
																				// 정보
																				// 내용

			JLabel[] lb_Basket_name_title = new JLabel[bas_count];
			JLabel[] lb_Basket_size_title = new JLabel[bas_count];
			JLabel[] lb_Basket_price_title = new JLabel[bas_count];

			JLabel[] lb_Basket_name = new JLabel[bas_count];

			JLabel[] lb_Basket_size = new JLabel[bas_count];

			JButton[] btn_del = new JButton[bas_count];

			int Bas_i;

			pnl_Basket_main.setBackground(Color.WHITE);

			// sp_pnl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

			pnl_Basket.setLayout(new BorderLayout());
			pnl_Basket_menu.setLayout(new BorderLayout());

			// 패널의

			int y = 100; // pnl_Basket_shopbasket[i]의 패널의 y값을 증가시켜주기위한 초기값
			BufferedImage[] img = null;
			// 장바구니 변수

			pnl_menu.removeAll();
			pnl_main.removeAll();
			sp_pnl.removeAll();
			pnl_Basket_buy.removeAll();
			pnl_Basket_main.removeAll();

			for (Bas_i = 0; Bas_i < bas_count; Bas_i++) { // bas_count 장바구니에 넣은
															// 상품의 갯수만큼 패널만듬
				pnl_Basket_shopbasket[Bas_i] = new JPanel();
				lb_Basket_name[Bas_i] = new JLabel(bas_name[Bas_i]);
				lb_Basket_size[Bas_i] = new JLabel(bas_size[Bas_i]);
				lb_Basket_price[Bas_i] = new JLabel(bas_price[Bas_i]);
				btn_del[Bas_i] = new JButton("삭제");
				lb_Basket_shopbasket_image[Bas_i] = new JLabel();// "이미지자리"
																	// 라는 글자
																	// 삭제후
				// 이미지 넣으면됨

				lb_Basket_shopbasket_image[Bas_i].setIcon(new ImageIcon(pro_show.Image[Bas_i]));
				pnl_Basket_product_title[Bas_i] = new JPanel();
				pnl_Basket_product_description[Bas_i] = new JPanel();

				pnl_Basket_main.add(pnl_Basket_shopbasket[Bas_i]);
				// pnl_Basket_shopbasket[Bas_i].setBounds(100, y, 800, 300); //
				// 나중에
				// i값이
				// 증가할대마다
				// Y값을
				// +100정도 주면서
				pnl_Basket_shopbasket[Bas_i].setBackground(Color.WHITE);
				y = y + 300; // 패널이 생성될때마다 이전 pnl_Basket_shopbasket[i]의y값에
								// +100만큼 밑에 생성
				pnl_Basket_shopbasket[Bas_i].setLayout(null);

				pnl_Basket_product_title[Bas_i]
						.setLayout(new BoxLayout(pnl_Basket_product_title[Bas_i], BoxLayout.Y_AXIS));
				pnl_Basket_product_description[Bas_i]
						.setLayout(new BoxLayout(pnl_Basket_product_description[Bas_i], BoxLayout.Y_AXIS));

				lb_Basket_name_title[Bas_i] = new JLabel("상품명 : ");
				lb_Basket_size_title[Bas_i] = new JLabel("사이즈 : ");
				lb_Basket_price_title[Bas_i] = new JLabel("가격 : ");

				pnl_Basket_product_title[Bas_i].add(lb_Basket_name_title[Bas_i]);
				pnl_Basket_product_title[Bas_i].add(lb_Basket_size_title[Bas_i]);
				pnl_Basket_product_title[Bas_i].add(lb_Basket_price_title[Bas_i]);
				Font font = new Font("", Font.BOLD, 20);
				lb_Basket_name_title[Bas_i].setFont(font);
				lb_Basket_size_title[Bas_i].setFont(font);
				lb_Basket_price_title[Bas_i].setFont(font);

				pnl_Basket_product_description[Bas_i].add(lb_Basket_name[Bas_i]);
				pnl_Basket_product_description[Bas_i].add(lb_Basket_size[Bas_i]);
				pnl_Basket_product_description[Bas_i].add(lb_Basket_price[Bas_i]);
				lb_Basket_name[Bas_i].setFont(font);
				lb_Basket_size[Bas_i].setFont(font);
				lb_Basket_price[Bas_i].setFont(font);

				cb_del[Bas_i] = new JCheckBox();
				cb_del[Bas_i].addActionListener(this);

				pnl_Basket_product_description[Bas_i].add(cb_del[Bas_i]);
				pnl_Basket_shopbasket[Bas_i].add(lb_Basket_shopbasket_image[Bas_i]);
				pnl_Basket_shopbasket[Bas_i].add(pnl_Basket_product_title[Bas_i]);
				pnl_Basket_shopbasket[Bas_i].add(pnl_Basket_product_description[Bas_i]);

				lb_Basket_shopbasket_image[Bas_i].setBounds(0, -100, 250, 400);
				pnl_Basket_product_title[Bas_i].setBounds(200, 30, 100, 200);

				pnl_Basket_product_title[Bas_i].setBackground(Color.WHITE);
				pnl_Basket_product_description[Bas_i].setBounds(300, 30, 200, 200);
				pnl_Basket_product_description[Bas_i].setBackground(Color.WHITE);

			}
			int py = 0;
			py = 100 + (300 * bas_count);

			// pnl_Basket.add(pnl_Basket_menu, "Center");
			pnl_Basket_main.setPreferredSize(new Dimension(900, py));

			sp_pnl = new JScrollPane(pnl_Basket_main);

			// this.add(pnl_Basket);

			pnl_menu.add(sp_pnl, "Center");
			pnl_menu.add(pnl_btn, "North");
			lb_Basket_gunprice.setText(Integer.toString(pro_show.price));
			pnl_Basket_buy.add(lb_Basket_gunprice);
			pnl_Basket_buy.add(buybuy);
			pnl_Basket_buy.add(deldel);

			pnl_menu.add(pnl_Basket_buy, "South");

			sp_pnl.getVerticalScrollBar().setUnitIncrement(16);

			revalidate();
			repaint();

		}

		if (e.getSource() == buybuy) {

			new paymentTotal();
			if (pro_show.cc > 1) {
				paymentTotal.lb_Requiredinput.setText(bas_name[0] + " 외 " + (pro_show.cc - 1) + "개");
			}
			if (pro_show.cc == 1) {
				paymentTotal.lb_Requiredinput.setText(bas_name[0]);
			}
			for (int memi = 0; memi < mem_model.getRowCount(); memi++) {

				String sksnrl = mem_model.getValueAt(memi, 6).toString();
				String vhs = mem_model.getValueAt(memi, 5).toString();

				String[] adr = new String[2];
				String[] phnum = new String[3];
				adr = sksnrl.split("=");
				phnum = vhs.split("-");

				if (userid.equals(mem_model.getValueAt(memi, 0).toString())) {
					CusInfo.fd_reciever.setText(mem_model.getValueAt(memi, 2).toString());
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
		int maprice = 0;
		if (e.getSource() == deldel) {

			for (int sel = 0; sel < pro_show.cc; sel++) {
				boolean select = cb_del[sel].isSelected();

				if (select == true) {
					String manumnum = lb_Basket_price[sel].getText().replace(" 원", "");
					maprice = Integer.parseInt(manumnum);
					pro_show.price = pro_show.price - maprice;
					lb_Basket_gunprice.setText(Integer.toString(pro_show.price));

					pnl_Basket_shopbasket[sel].removeAll();

					pro_show.cc--;
				}
			}

			//System.out.println("    " + pro_show.cc);

			// 장바구니 변수 //
			int bas_count = pro_show.cc; // bas_count 숫자에 따라 장바구니에 만들어지는 갯수가 달라짐
											// 2이면 장바구니에 패널 2개
			bas_name = pro_show.bas_name;
			String[] bas_price = pro_show.bas_price;
			String[] bas_size = pro_show.bas_size;
			// 생성현재는 2로
			// 주었음 장바구니에 넣은 상품의갯수를 받아오면됨

			pnl_Basket_buy.setBackground(Color.gray);
			JScrollPane sp_Basket_pnl;// menu에 Center로 붙어있는 패널

			// 장바구니 패널
			// bas_count만큼
			// 생성
			JLabel[] lb_Basket_shopbasket_image = new JLabel[bas_count]; // 장바구니
																			// 이미지bas_count만큼
																			// 생성

			JPanel[] pnl_Basket_product_title = new JPanel[bas_count]; // 상품정보
			JPanel[] pnl_Basket_product_description = new JPanel[bas_count]; // 상품
																				// 정보
																				// 내용

			JLabel[] lb_Basket_name_title = new JLabel[bas_count];
			JLabel[] lb_Basket_size_title = new JLabel[bas_count];
			JLabel[] lb_Basket_price_title = new JLabel[bas_count];

			JLabel[] lb_Basket_name = new JLabel[bas_count];

			JLabel[] lb_Basket_size = new JLabel[bas_count];

			JButton[] btn_del = new JButton[bas_count];

			int Bas_i;

			pnl_Basket_main.setBackground(Color.WHITE);

			// sp_pnl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

			pnl_Basket.setLayout(new BorderLayout());
			pnl_Basket_menu.setLayout(new BorderLayout());

			// 패널의

			int y = 100; // pnl_Basket_shopbasket[i]의 패널의 y값을 증가시켜주기위한 초기값
			BufferedImage[] img = null;
			// 장바구니 변수

			pnl_menu.removeAll();
			pnl_main.removeAll();
			sp_pnl.removeAll();
			pnl_Basket_buy.removeAll();
			pnl_Basket_main.removeAll();

			for (Bas_i = 0; Bas_i < bas_count; Bas_i++) { // bas_count 장바구니에 넣은
															// 상품의 갯수만큼 패널만듬
				pnl_Basket_shopbasket[Bas_i] = new JPanel();
				lb_Basket_name[Bas_i] = new JLabel(bas_name[Bas_i]);
				lb_Basket_size[Bas_i] = new JLabel(bas_size[Bas_i]);
				lb_Basket_price[Bas_i] = new JLabel(bas_price[Bas_i]);
				btn_del[Bas_i] = new JButton("삭제");
				lb_Basket_shopbasket_image[Bas_i] = new JLabel();// "이미지자리"
																	// 라는 글자
																	// 삭제후
				// 이미지 넣으면됨

				lb_Basket_shopbasket_image[Bas_i].setIcon(new ImageIcon(pro_show.Image[Bas_i]));
				pnl_Basket_product_title[Bas_i] = new JPanel();
				pnl_Basket_product_description[Bas_i] = new JPanel();

				pnl_Basket_main.add(pnl_Basket_shopbasket[Bas_i]);
				// pnl_Basket_shopbasket[Bas_i].setBounds(100, y, 800, 300); //
				// 나중에
				// i값이
				// 증가할대마다
				// Y값을
				// +100정도 주면서
				pnl_Basket_shopbasket[Bas_i].setBackground(Color.WHITE);
				y = y + 300; // 패널이 생성될때마다 이전 pnl_Basket_shopbasket[i]의y값에
								// +100만큼 밑에 생성
				pnl_Basket_shopbasket[Bas_i].setLayout(null);

				pnl_Basket_product_title[Bas_i]
						.setLayout(new BoxLayout(pnl_Basket_product_title[Bas_i], BoxLayout.Y_AXIS));
				pnl_Basket_product_description[Bas_i]
						.setLayout(new BoxLayout(pnl_Basket_product_description[Bas_i], BoxLayout.Y_AXIS));

				lb_Basket_name_title[Bas_i] = new JLabel("상품명 : ");
				lb_Basket_size_title[Bas_i] = new JLabel("사이즈 : ");
				lb_Basket_price_title[Bas_i] = new JLabel("가격 : ");

				pnl_Basket_product_title[Bas_i].add(lb_Basket_name_title[Bas_i]);
				pnl_Basket_product_title[Bas_i].add(lb_Basket_size_title[Bas_i]);
				pnl_Basket_product_title[Bas_i].add(lb_Basket_price_title[Bas_i]);
				Font font = new Font("", Font.BOLD, 20);
				lb_Basket_name_title[Bas_i].setFont(font);
				lb_Basket_size_title[Bas_i].setFont(font);
				lb_Basket_price_title[Bas_i].setFont(font);

				pnl_Basket_product_description[Bas_i].add(lb_Basket_name[Bas_i]);
				pnl_Basket_product_description[Bas_i].add(lb_Basket_size[Bas_i]);
				pnl_Basket_product_description[Bas_i].add(lb_Basket_price[Bas_i]);
				lb_Basket_name[Bas_i].setFont(font);
				lb_Basket_size[Bas_i].setFont(font);
				lb_Basket_price[Bas_i].setFont(font);

				cb_del[Bas_i] = new JCheckBox();
				cb_del[Bas_i].addActionListener(this);

				pnl_Basket_product_description[Bas_i].add(cb_del[Bas_i]);
				pnl_Basket_shopbasket[Bas_i].add(lb_Basket_shopbasket_image[Bas_i]);
				pnl_Basket_shopbasket[Bas_i].add(pnl_Basket_product_title[Bas_i]);
				pnl_Basket_shopbasket[Bas_i].add(pnl_Basket_product_description[Bas_i]);

				lb_Basket_shopbasket_image[Bas_i].setBounds(0, -100, 250, 400);
				pnl_Basket_product_title[Bas_i].setBounds(200, 30, 100, 200);

				pnl_Basket_product_title[Bas_i].setBackground(Color.WHITE);
				pnl_Basket_product_description[Bas_i].setBounds(300, 30, 200, 200);
				pnl_Basket_product_description[Bas_i].setBackground(Color.WHITE);

			}
			int py = 0;
			py = 100 + (300 * bas_count);

			// pnl_Basket.add(pnl_Basket_menu, "Center");
			pnl_Basket_main.setPreferredSize(new Dimension(900, py));

			sp_pnl = new JScrollPane(pnl_Basket_main);

			// this.add(pnl_Basket);

			pnl_menu.add(sp_pnl, "Center");
			pnl_menu.add(pnl_btn, "North");
			lb_Basket_gunprice.setText(Integer.toString(pro_show.price));
			pnl_Basket_buy.add(lb_Basket_gunprice);
			pnl_Basket_buy.add(buybuy);
			pnl_Basket_buy.add(deldel);

			pnl_menu.add(pnl_Basket_buy, "South");

			sp_pnl.getVerticalScrollBar().setUnitIncrement(16);

			revalidate();
			repaint();

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == lb_Image2) {
			
			
			btn_menu[0].setFont(font2);
			btn_menu[1].setFont(font2);
			btn_menu[2].setFont(font2);
			btn_menu[3].setFont(font);
			
			selnum = 0;
			pnl_menu.removeAll();
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
				pnl_arry[i].setCursor(cursor);
				pnl_main.add(pnl_arry[i]);

			}

			py = 400 * ((Table_model.getRowCount() / 4) + 1);
			pnl_main.setPreferredSize(new Dimension(900, py));
			sp_pnl2 = new JScrollPane(pnl_main);

			pnl_menu.add(sp_pnl2, "Center");
			sp_pnl2.getVerticalScrollBar().setUnitIncrement(16);
			pnl_menu.add(pnl_btn, "North");
			revalidate();
			repaint();
		}

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
						resizeImage3 = image2.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String set_name = this.lb_name_arry[i].getText();
					String set_price = this.lb_price_arry[i].getText() + " 원";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));
					pro_show.Image[pro_show.cc] = resizeImage3;

					get_price = this.lb_price_arry[i].getText();
				

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
						resizeImage3 = image2.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String set_name = this.lb_name_arry[i].getText();
					String set_price = this.lb_price_arry[i].getText() + " 원";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));
					pro_show.Image[pro_show.cc] = resizeImage3;

					get_price = this.lb_price_arry[i].getText();
					

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
						resizeImage3 = image2.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String set_name = this.lb_name_arry[i].getText();
					String set_price = this.lb_price_arry[i].getText() + " 원";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));
					pro_show.Image[pro_show.cc] = resizeImage3;

					get_price = this.lb_price_arry[i].getText();
					

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
						resizeImage3 = image2.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String set_name = this.lb_name_arry[i].getText();
					String set_price = this.lb_price_arry[i].getText() + " 원";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));
					pro_show.Image[pro_show.cc] = resizeImage3;

					get_price = this.lb_price_arry[i].getText();
					

					str = Table_model.getValueAt(i, 5).toString();
					str = str.replace("<br>", "\n");
					pro_show.ta_under.setText(str);

				}

			}
		}
		if (selnum == 0) {
			for (int i = 0; i < Table_model.getRowCount(); i++) {
				if (e.getSource() == pnl_arry[i]) {
					sizenum = i;
					Image image2 = null;
					resizeImage2 = null;
					String str;
					new pro_show();

					try {
						image2 = ImageIO.read(new File(Table_model.getValueAt(i, 1).toString()));
						resizeImage2 = image2.getScaledInstance(150, 250, Image.SCALE_SMOOTH);
						resizeImage3 = image2.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String set_name = this.lb_name_arry[i].getText();
					String set_price = this.lb_price_arry[i].getText() + " 원";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));
					pro_show.Image[pro_show.cc] = resizeImage3;

					get_price = this.lb_price_arry[i].getText();

					

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

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {

		}

	}

}
