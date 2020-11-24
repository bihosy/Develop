package asd;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;

public class Shop_admin extends JFrame implements ActionListener, MouseListener {

	JButton btn_del = new JButton("����");

	// --------DB ���̺� ���� --------//

	String header[] = { "��ǰ��ȣ", "�������", "��ǰ��", "����", "������", "����" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model, top_model, pants_model, shoes_model;
	JTable tb_Pro, tb_top, tb_pants, tb_shoes;
	// --------DB ���̺� ���� --------//

	Color bakcc = new Color(255, 255, 255, 0);

	// ----------------���� �������Ʈ-----------//
	BufferedReader br = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pw = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��
	BufferedReader brtop = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pwtop = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��
	BufferedReader brpants = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pwpants = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��
	BufferedReader brshoes = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pwshoes = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��

	FileReader fr = null;// ���ϰ�ü �о����
	FileWriter fw = null;// ���ϰ�ü ����
	FileReader frtop = null;// ���ϰ�ü �о����
	FileWriter fwtop = null;// ���ϰ�ü ����
	FileReader frpants = null;// ���ϰ�ü �о����
	FileWriter fwpants = null;// ���ϰ�ü ����
	FileReader frshoes = null;// ���ϰ�ü �о����
	FileWriter fwshoes = null;// ���ϰ�ü ����

	String gr = "product.txt";// �������
	String topgr = "top.txt";// �������
	String pantsgr = "pants.txt";// �������
	String shoesgr = "shoes.txt";// �������

	String img_gr = "noimage.png";
	String size = "";

	String l;// ���� �о ���ڿ� ����
	String nae;
	String sizevalue;
	static int sizenum;
	static int sizesize = 0;
	static int naesize = 0;
	static int writechk=0;
	int selnum = 0;
	Dimension pnlsize = new Dimension();

	int py;
	int pronum = 0;
	int seltbnum = 0;

	int idx = 0;
	// ----------------���� �������Ʈ-----------//
	ImageIcon background = new ImageIcon("ddd.jpg");
	JPanel pnl = new JPanel();
	JPanel pnl_logoinfo = new JPanel();
	JPanel pnl_menu = new JPanel();
	JPanel pnl_spnl = new JPanel();
	JPanel pnl_under = new JPanel();
	JPanel pnl_btn = new JPanel();
	JPanel pnl_img = new JPanel(); // ��� �̹��� �г�
	JPanel pnl_main = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(background.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
	JPanel pnl_image = new JPanel();
	JPanel pnl_info = new JPanel();
	JButton buybuy = new JButton("�����ϱ�");
	JPanel[] pnl_arry = new JPanel[99];

	JPanel[] pnl_arry2 = new JPanel[99];
	JPanel[] pnl_arry3 = new JPanel[99]; // ��ǰ����
	JPanel[] pnl_arry4 = new JPanel[99]; // ��ǰ���� ����

	JLabel[] lb_name = new JLabel[99];
	JLabel[] lb_size = new JLabel[99];
	JLabel[] lb_price = new JLabel[99];
	JLabel[] lb_jumun = new JLabel[99];
	JLabel[] lb_1day = new JLabel[99];
	JLabel[] lb_image = new JLabel[99];
	JLabel[] lb_name_arry = new JLabel[99];
	JLabel[] lb_size_arry = new JLabel[99];
	JLabel[] lb_price_arry = new JLabel[99];
	JLabel lb_top_logo = new JLabel("�̹���"); // ���ΰ� or ���θ� �ΰ�
	JLabel lb_top_info1 = new JLabel("���̵� : "); // �����Ҷ� �Է��� ���̵�
	JLabel lb_top_info2 = new JLabel("��� : "); // admin or user
	JLabel lb_top_setinfo1 = new JLabel(" rozen"); // �ӽ÷� rozen�־�� �۾��Ҷ� ����� �޾ƿ�
													// ���̵� ���� �󺧿� ���̸��
	JLabel lb_top_setinfo2 = new JLabel("admin");// �ӽ÷� admin�־�� �۾��Ҷ� ����� �޾ƿ�
													// ���̵� ���� �󺧿� ���̸��
	JButton btn_write = new JButton("�۾���");
	JLabel lb_Image2; // �ΰ� �� ��� ��
	JLabel lb_WC; // ��� ���� ��(WelCome!)

	String[] str_btn_name = { "����", "����", "�Ź�", "�� ����" };
	int i;
	int j;
	int k;
	JButton[] btn_menu = new JButton[str_btn_name.length];

	Image image = null;
	Image resizeImage = null;

	ImageIcon Image2 = new ImageIcon("logo2.png");
	Image resizeImage2 = null;
	Image resizeImage3 = null;

	Font WC_Font = new Font("����", Font.BOLD, 15);
	JScrollPane sp_pnl, sp_pnl2;

	Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

	Font font = new Font("���� ���", Font.PLAIN, 15);
	Font font2 = new Font("���� ���", Font.BOLD, 17);

	public Shop_admin() {

		pnl.setBackground(Color.WHITE);

		Color color = new Color(0, 34, 102);
		pnl_btn.setBackground(color);

		// --------DB ���̺� ���� --------//
		Table_model = new DefaultTableModel(contents, header);
		top_model = new DefaultTableModel(contents, header);
		pants_model = new DefaultTableModel(contents, header);
		shoes_model = new DefaultTableModel(contents, header);

		tb_Pro = new JTable(Table_model);
		tb_top = new JTable(top_model);
		tb_pants = new JTable(pants_model);
		tb_shoes = new JTable(shoes_model);

		tb_Pro.setRowHeight(25);
		tb_top.setRowHeight(25);
		tb_pants.setRowHeight(25);
		tb_shoes.setRowHeight(25);
		// --------DB ���̺� ���� --------//

		btn_del.addActionListener(this);

		try {
			fr = new FileReader(gr);
			br = new BufferedReader(fr);// �о�� ���� ���ۿ� ��ü ���

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
		resizeImage2 = Image2.getImage();
		Image Image3 = resizeImage2.getScaledInstance(300, 100, Image.SCALE_SMOOTH);
		ImageIcon Image4 = new ImageIcon(Image3);
		lb_Image2 = new JLabel(Image4);
		lb_WC = new JLabel("������� " + Login.name + "�� ������ �����Դϴ�");

		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

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
		lb_WC.setBounds(630, 70, 350, 25);
		lb_WC.setFont(WC_Font);
		lb_WC.setHorizontalAlignment(JLabel.RIGHT);

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

			//System.out.println();

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
			lb_name[i] = new JLabel("�̸� : ");
			lb_size[i] = new JLabel("������ : ");
			lb_price[i] = new JLabel("���� : ");
			lb_jumun[i] = new JLabel("[�ֹ�����]");
			lb_1day[i] = new JLabel("[���Ϲ��]");
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

		tb_pants.setEnabled(false);
		tb_shoes.setEnabled(false);
		tb_top.setEnabled(false);

		py = 400 * ((Table_model.getRowCount() / 4) + 1);

		pnl_main.setPreferredSize(new Dimension(900, py));
		sp_pnl = new JScrollPane(pnl_main);
		sp_pnl.getVerticalScrollBar().setUnitIncrement(16);

		pnl_menu.add(sp_pnl, "Center");
		pnl_under.add(btn_write);
		btn_write.addActionListener(this);
		this.add(pnl_under, "South");
		// pnl_menu.add(sp_pnl);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Shop_admin();

	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		



		if (e.getSource() == btn_write) {
			new Write();
			this.setDefaultCloseOperation(3);
			this.setVisible(false);
		}

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

				//System.out.println();

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
				lb_name[i] = new JLabel("�̸� : ");
				lb_size[i] = new JLabel("������ : ");
				lb_price[i] = new JLabel("���� : ");
				lb_jumun[i] = new JLabel("[�ֹ�����]");
				lb_1day[i] = new JLabel("[���Ϲ��]");
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
			pnl_under.removeAll();
			pnl_under.add(btn_write);

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

				//System.out.println();

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
				lb_name[i] = new JLabel("�̸� : ");
				lb_size[i] = new JLabel("������ : ");
				lb_price[i] = new JLabel("���� : ");
				lb_jumun[i] = new JLabel("[�ֹ�����]");
				lb_1day[i] = new JLabel("[���Ϲ��]");
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
			pnl_under.removeAll();
			pnl_under.add(btn_write);
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

				//System.out.println();

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
				lb_name[i] = new JLabel("�̸� : ");
				lb_size[i] = new JLabel("������ : ");
				lb_price[i] = new JLabel("���� : ");
				lb_jumun[i] = new JLabel("[�ֹ�����]");
				lb_1day[i] = new JLabel("[���Ϲ��]");
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
			pnl_under.removeAll();
			pnl_under.add(btn_write);

			sp_pnl.getVerticalScrollBar().setUnitIncrement(16);
			revalidate();
			repaint();
		}

		else if (e.getSource() == btn_menu[3]) {

			btn_menu[0].setFont(font);
			btn_menu[1].setFont(font);
			btn_menu[2].setFont(font);
			btn_menu[3].setFont(font2);

			pnl_menu.removeAll();
			pnl_main.removeAll();
			sp_pnl.removeAll();
			tb_Pro.addMouseListener(this);
			JPanel pnl_prodb = new JPanel(null);
			pnl_prodb.setBackground(Color.white);
			JLabel[] lb_name = new JLabel[4];

			lb_name[0] = new JLabel("��ü�׸�");
			lb_name[1] = new JLabel("����");
			lb_name[2] = new JLabel("����");
			lb_name[3] = new JLabel("�Ź�");

			Font name_font = new Font("����", Font.BOLD, 18);
			lb_name[0].setFont(name_font);
			lb_name[1].setFont(name_font);
			lb_name[2].setFont(name_font);
			lb_name[3].setFont(name_font);

			JScrollPane sp_prodb = new JScrollPane(tb_Pro);
			JScrollPane sp_topdb = new JScrollPane(tb_top);
			JScrollPane sp_pantsdb = new JScrollPane(tb_pants);
			JScrollPane sp_shoesdb = new JScrollPane(tb_shoes);

			lb_name[0].setBounds(455, 10, 100, 25);
			sp_prodb.setBounds(0, 60, 985, 140);
			sp_prodb.getViewport().setBackground(Color.white);

			lb_name[1].setBounds(470, 210, 100, 25);
			sp_topdb.setBounds(0, 260, 985, 140);
			sp_topdb.getViewport().setBackground(Color.white);

			lb_name[2].setBounds(470, 410, 100, 25);
			sp_pantsdb.setBounds(0, 460, 985, 140);
			sp_pantsdb.getViewport().setBackground(Color.white);

			lb_name[3].setBounds(470, 610, 100, 25);
			sp_shoesdb.setBounds(0, 660, 985, 140);
			sp_shoesdb.getViewport().setBackground(Color.white);

			pnl_prodb.add(lb_name[0]);
			pnl_prodb.add(sp_prodb);
			pnl_prodb.add(lb_name[1]);
			pnl_prodb.add(sp_topdb);
			pnl_prodb.add(lb_name[2]);
			pnl_prodb.add(sp_pantsdb);
			pnl_prodb.add(lb_name[3]);
			pnl_prodb.add(sp_shoesdb);
			pnl_menu.add(pnl_prodb);
			pnl_menu.add(pnl_btn, "North");
			pnl_under.removeAll();
			pnl_under.add(btn_del);
			pnl_under.setBackground(Color.white);
			revalidate();
			repaint();

		}
		if (e.getSource() == buybuy) {
			//System.out.println("3");
		}
		if (e.getSource() == btn_del) {

			if (seltbnum == 0) {
				//System.out.println("Test �׸��� ���� �� �����ϴ�.");
			}

			else {

				Table_model.removeRow(seltbnum);
				for (int i = 0; i < top_model.getRowCount(); i++) {
					if (Integer.parseInt((String) top_model.getValueAt(i, 0)) == pronum) {
						top_model.removeRow(i);
					}
				}
				for (int j = 0; j < pants_model.getRowCount(); j++) {
					if (Integer.parseInt((String) pants_model.getValueAt(j, 0)) == pronum) {
						pants_model.removeRow(j);
					}
				}

				for (int k = 0; k < shoes_model.getRowCount(); k++) {
					if (Integer.parseInt((String) shoes_model.getValueAt(k, 0)) == pronum) {
						shoes_model.removeRow(k);
					}
				}
			}

			try

			{
				fwtop = new FileWriter(topgr);
				pwtop = new PrintWriter(fwtop);

				for (int i = 0; i < tb_top.getRowCount(); i++) {
					for (int j = 0; j < tb_top.getColumnCount(); j++) {

						if (j == tb_top.getColumnCount() - 1) {
							l = top_model.getValueAt(i, j).toString();
							pwtop.print(l);

						} else {
							l = tb_top.getValueAt(i, j).toString();
							pwtop.print(l);
							pwtop.print("/");

						}
					}
					pwtop.println();
				}

			}

			catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} finally {

				try {
					fwtop.close();
					pwtop.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}

			try {
				fwpants = new FileWriter(pantsgr);
				pwpants = new PrintWriter(fwpants);

				for (int i = 0; i < tb_pants.getRowCount(); i++) {
					for (int j = 0; j < tb_pants.getColumnCount(); j++) {

						if (j == tb_pants.getColumnCount() - 1) {
							l = pants_model.getValueAt(i, j).toString();
							pwpants.print(l);

						} else {
							l = tb_pants.getValueAt(i, j).toString();
							pwpants.print(l);
							pwpants.print("/");

						}
					}
					pwpants.println();
				}

			}

			catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} finally {

				try {
					fwpants.close();
					pwpants.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}

			try {
				fwshoes = new FileWriter(shoesgr);
				pwshoes = new PrintWriter(fwshoes);

				for (int i = 0; i < tb_shoes.getRowCount(); i++) {
					for (int j = 0; j < tb_shoes.getColumnCount(); j++) {

						if (j == tb_shoes.getColumnCount() - 1) {
							l = shoes_model.getValueAt(i, j).toString();
							pwshoes.print(l);

						} else {
							l = tb_shoes.getValueAt(i, j).toString();
							pwshoes.print(l);
							pwshoes.print("/");

						}
					}
					pwshoes.println();
				}

			}

			catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} finally {

				try {
					fwshoes.close();
					pwshoes.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}

			try {
				fw = new FileWriter(gr);
				pw = new PrintWriter(fw);

				for (int i = 0; i < tb_Pro.getRowCount(); i++) {
					for (int j = 0; j < tb_Pro.getColumnCount(); j++) {

						if (j == tb_Pro.getColumnCount() - 1) {
							l = Table_model.getValueAt(i, j).toString();
							pw.print(l);

						} else {
							l = Table_model.getValueAt(i, j).toString();
							pw.print(l);
							pw.print("/");

						}
					}
					pw.println();
				}

			}

			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {

				try {
					fw.close();
					pw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == tb_Pro) {
			seltbnum = tb_Pro.getSelectedRow();

			pronum = Integer.parseInt((String) Table_model.getValueAt(seltbnum, 0));

		}

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

				//System.out.println();

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
				lb_name[i] = new JLabel("�̸� : ");
				lb_size[i] = new JLabel("������ : ");
				lb_price[i] = new JLabel("���� : ");
				lb_jumun[i] = new JLabel("[�ֹ�����]");
				lb_1day[i] = new JLabel("[���Ϲ��]");
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
			pnl_under.removeAll();
			pnl_under.add(btn_write);
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
					String set_price = this.lb_price_arry[i].getText() + " ��";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));
					pro_show.Image[pro_show.cc] = resizeImage3;

					String get_price = this.lb_price_arry[i].getText();
					pro_show.price = pro_show.price + Integer.parseInt(get_price);

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
					String set_price = this.lb_price_arry[i].getText() + " ��";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));
					pro_show.Image[pro_show.cc] = resizeImage3;

					String get_price = this.lb_price_arry[i].getText();
					pro_show.price = pro_show.price + Integer.parseInt(get_price);

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
					String set_price = this.lb_price_arry[i].getText() + " ��";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));
					pro_show.Image[pro_show.cc] = resizeImage3;

					String get_price = this.lb_price_arry[i].getText();
					pro_show.price = pro_show.price + Integer.parseInt(get_price);

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
					String set_price = this.lb_price_arry[i].getText() + " ��";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));
					pro_show.Image[pro_show.cc] = resizeImage3;

					String get_price = this.lb_price_arry[i].getText();
					pro_show.price = pro_show.price + Integer.parseInt(get_price);

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
					String set_price = this.lb_price_arry[i].getText() + " ��";

					pro_show.lb_name2.setText(set_name);
					pro_show.lb_price2.setText(set_price);
					pro_show.lb_img.setIcon(new ImageIcon(resizeImage2));
					pro_show.Image[pro_show.cc] = resizeImage3;

					String get_price = this.lb_price_arry[i].getText();

					pro_show.price = pro_show.price + Integer.parseInt(get_price);

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
