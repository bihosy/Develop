package asd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Shop_nomal extends JFrame implements ActionListener, MouseListener {

	

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
	int selnum = 0;
	Dimension pnlsize = new Dimension();

	int py;

	int idx = 0;
	// ----------------���� �������Ʈ-----------//
	ImageIcon background = new ImageIcon("ddd.jpg");
	JPanel pnl = new JPanel();
	JPanel pnl_logoinfo = new JPanel();
	JPanel pnl_menu = new JPanel();
	JPanel pnl_spnl = new JPanel();
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

	JPanel[] pnl_arry = new JPanel[9];

	JPanel[] pnl_arry2 = new JPanel[9];
	JPanel[] pnl_arry3 = new JPanel[9]; // ��ǰ����
	JPanel[] pnl_arry4 = new JPanel[9]; // ��ǰ���� ����

	JLabel[] lb_name = new JLabel[9];
	JLabel[] lb_size = new JLabel[9];
	JLabel[] lb_price = new JLabel[9];
	JLabel[] lb_jumun = new JLabel[9];
	JLabel[] lb_1day = new JLabel[9];
	JLabel[] lb_image = new JLabel[9];
	JLabel[] lb_name_arry = new JLabel[9];
	JLabel[] lb_size_arry = new JLabel[9];
	JLabel[] lb_price_arry = new JLabel[9];
	JLabel lb_top_logo = new JLabel("�̹���"); // ���ΰ� or ���θ� �ΰ�
	JLabel lb_top_info1 = new JLabel("���̵� : "); // �����Ҷ� �Է��� ���̵�
	JLabel lb_top_info2 = new JLabel("��� : "); // admin or user
	JLabel lb_top_setinfo1 = new JLabel(" rozen"); // �ӽ÷� rozen�־�� �۾��Ҷ� ����� �޾ƿ�
													// ���̵� ���� �󺧿� ���̸��
	JLabel lb_top_setinfo2 = new JLabel("admin");// �ӽ÷� admin�־�� �۾��Ҷ� ����� �޾ƿ�
													// ���̵� ���� �󺧿� ���̸��
	JLabel lb_Image2; // �ΰ� �� ��� ��
	JLabel lb_WC; // ��� ���� ��(WelCome!)

	String[] str_btn_name = { "����", "����", "�Ź�", "��ٱ���" };
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

	public Shop_nomal() {

		pnl.setBackground(Color.WHITE);
		pnl_btn.setBackground(Color.WHITE);

		// --------DB ���̺� ���� --------//
		Table_model = new DefaultTableModel(contents, header);
		top_model = new DefaultTableModel(contents, header);
		pants_model = new DefaultTableModel(contents, header);
		shoes_model = new DefaultTableModel(contents, header);

		tb_Pro = new JTable(Table_model);
		tb_top = new JTable(top_model);
		tb_pants = new JTable(pants_model);
		tb_shoes = new JTable(shoes_model);
		// --------DB ���̺� ���� --------//

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
		lb_WC = new JLabel("OOO�� ȯ���մϴ�");

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
		lb_WC.setBounds(800, 70, 200, 25);
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
			pnl_btn.add(btn_menu[j]);

		}

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

			revalidate();
			repaint();
		}

		else if (e.getSource() == btn_menu[1]) {
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
			revalidate();
			repaint();
		}

		else if (e.getSource() == btn_menu[2]) {
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

			sp_pnl.getVerticalScrollBar().setUnitIncrement(16);
			revalidate();
			repaint();
		}

		else if (e.getSource() == btn_menu[3]) {

			// ��ٱ��� ���� //
			int bas_count = pro_show.cc; // bas_count ���ڿ� ���� ��ٱ��Ͽ� ��������� ������ �޶���
											// 2�̸� ��ٱ��Ͽ� �г� 2��
			String[] bas_name = pro_show.bas_name;
			String[] bas_price = pro_show.bas_price;
			String[] bas_size = pro_show.bas_size;
			// ��������� 2��
			// �־��� ��ٱ��Ͽ� ���� ��ǰ�ǰ����� �޾ƿ����
			JPanel pnl_Basket = new JPanel(); // this�� �پ��ִ��г�
			JPanel pnl_Basket_menu = new JPanel(); // pnl Center�� �پ��ִ� �г�
			JPanel pnl_Basket_main = new JPanel();// menu�� Center�� �پ��ִ� �г�

			JScrollPane sp_Basket_pnl;// menu�� Center�� �پ��ִ� �г�

			JPanel[] pnl_Basket_shopbasket = new JPanel[bas_count]; // ��ٱ��� �г�
																	// bas_count��ŭ
																	// ����
			JLabel[] lb_Basket_shopbasket_image = new JLabel[bas_count]; // ��ٱ���
																			// �̹���bas_count��ŭ
																			// ����

			JPanel[] pnl_Basket_product_title = new JPanel[bas_count]; // ��ǰ����
			JPanel[] pnl_Basket_product_description = new JPanel[bas_count]; // ��ǰ
																				// ����
																				// ����

			JLabel[] lb_Basket_name_title = new JLabel[bas_count];
			JLabel[] lb_Basket_size_title = new JLabel[bas_count];
			JLabel[] lb_Basket_price_title = new JLabel[bas_count];

			JLabel[] lb_Basket_name = new JLabel[bas_count];
			JLabel[] lb_Basket_size = new JLabel[bas_count];
			JLabel[] lb_Basket_price = new JLabel[bas_count];

			int Bas_i;

			pnl_Basket_main.setBackground(Color.WHITE);

			// sp_pnl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

			pnl_Basket.setLayout(new BorderLayout());
			pnl_Basket_menu.setLayout(new BorderLayout());

			pnl_Basket_main.setLayout(null); // �г���

			int y = 100; // pnl_Basket_shopbasket[i]�� �г��� y���� ���������ֱ����� �ʱⰪ
			BufferedImage[] img = null;
			// ��ٱ��� ����

			pnl_menu.removeAll();
			pnl_main.removeAll();
			sp_pnl.removeAll();
			

			for (Bas_i = 0; Bas_i < bas_count; Bas_i++) { // bas_count ��ٱ��Ͽ� ����
															// ��ǰ�� ������ŭ �гθ���
				pnl_Basket_shopbasket[Bas_i] = new JPanel();
				lb_Basket_name[Bas_i] = new JLabel(bas_name[Bas_i]);
				lb_Basket_size[Bas_i] = new JLabel(bas_size[Bas_i]);
				lb_Basket_price[Bas_i] = new JLabel(bas_price[Bas_i]);
				lb_Basket_shopbasket_image[Bas_i] = new JLabel();// "�̹����ڸ�"
																		// ��� ����
																		// ������
				// �̹��� �������
				
				lb_Basket_shopbasket_image[Bas_i].setIcon(new ImageIcon(pro_show.Image[Bas_i]));
				pnl_Basket_product_title[Bas_i] = new JPanel();
				pnl_Basket_product_description[Bas_i] = new JPanel();

				pnl_Basket_main.add(pnl_Basket_shopbasket[Bas_i]);
				pnl_Basket_shopbasket[Bas_i].setBounds(100, y, 800, 300); // ���߿�
																			// i����
																			// �����Ҵ븶��
																			// Y����
				// +100���� �ָ鼭
				pnl_Basket_shopbasket[Bas_i].setBackground(Color.WHITE);
				y = y + 300; // �г��� �����ɶ����� ���� pnl_Basket_shopbasket[i]��y����
								// +100��ŭ �ؿ� ����
				pnl_Basket_shopbasket[Bas_i].setLayout(null);

				pnl_Basket_product_title[Bas_i]
						.setLayout(new BoxLayout(pnl_Basket_product_title[Bas_i], BoxLayout.Y_AXIS));
				pnl_Basket_product_description[Bas_i]
						.setLayout(new BoxLayout(pnl_Basket_product_description[Bas_i], BoxLayout.Y_AXIS));

				lb_Basket_name_title[Bas_i] = new JLabel("��ǰ�� : ");
				lb_Basket_size_title[Bas_i] = new JLabel("������ : ");
				lb_Basket_price_title[Bas_i] = new JLabel("���� : ");

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

				pnl_Basket_shopbasket[Bas_i].add(lb_Basket_shopbasket_image[Bas_i]);
				pnl_Basket_shopbasket[Bas_i].add(pnl_Basket_product_title[Bas_i]);
				pnl_Basket_shopbasket[Bas_i].add(pnl_Basket_product_description[Bas_i]);

				lb_Basket_shopbasket_image[Bas_i].setBounds(0,-100, 250, 400);
				pnl_Basket_product_title[Bas_i].setBounds(200, 30, 100, 200);
				pnl_Basket_product_title[Bas_i].setBackground(Color.WHITE);
				pnl_Basket_product_description[Bas_i].setBounds(300, 30, 200, 200);
				pnl_Basket_product_description[Bas_i].setBackground(Color.WHITE);

			}
			int py=0;
			py = 100+(300 * bas_count);

			pnl_Basket.add(pnl_Basket_menu, "Center");
			pnl_Basket_main.setPreferredSize(new Dimension(900, py));

			sp_pnl = new JScrollPane(pnl_Basket_main);

			// this.add(pnl_Basket);

			pnl_menu.add(sp_pnl, "Center");
			pnl_menu.add(pnl_btn, "North");

			sp_pnl.getVerticalScrollBar().setUnitIncrement(16);

			revalidate();
			repaint();

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == lb_Image2) {
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
					pro_show.Image[pro_show.cc]=resizeImage3;

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
					pro_show.Image[pro_show.cc]=resizeImage3;

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
					pro_show.Image[pro_show.cc]=resizeImage3;

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
					pro_show.Image[pro_show.cc]=resizeImage3;

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
					pro_show.Image[pro_show.cc]=resizeImage3;
					

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
