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
	
	
	
	// --------DB ���̺� ���� --------//
		String header[] = { "��ǰ��ȣ", "�������", "��ǰ��", "����", "������","����" };
		String contents[][] = {};
		String s = "";
		DefaultTableModel Table_model;
		JTable tb_Pro;
		// --------DB ���̺� ���� --------//

		// ----------------���� �������Ʈ-----------//
		BufferedReader br = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
		PrintWriter pw = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��

		FileReader fr = null;// ���ϰ�ü �о����
		FileWriter fw = null;// ���ϰ�ü ����

		String gr = "product.txt";// �������
		String img_gr = "noimage.png";
		String size = "";

		String l;// ���� �о ���ڿ� ����
		int idx = 0;
		// ----------------���� �������Ʈ-----------//
	
	
	
	
	
	
	

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
	JPanel[] pnl_arry3 = new JPanel[8]; //��ǰ����
	JPanel[] pnl_arry4 = new JPanel[8]; //��ǰ���� ����
	
	JLabel[] lb_name = new JLabel[8];
	JLabel[] lb_size = new JLabel[8];
	JLabel[] lb_price = new JLabel[8];
	JLabel[] lb_jumun = new JLabel[8];
	JLabel[] lb_1day = new JLabel[8];
	JLabel[] lb_image = new JLabel[8];
	JLabel[] lb_name_arry = new JLabel[8];
	JLabel[] lb_size_arry = new JLabel[8];
	JLabel[] lb_price_arry = new JLabel[8];
	JLabel lb_top_logo = new JLabel("�̹���"); // ���ΰ� or ���θ� �ΰ�
	JLabel lb_top_info1 = new JLabel("���̵� : "); // �����Ҷ� �Է��� ���̵�
	JLabel lb_top_info2 = new JLabel("��� : "); // admin or user
	JLabel lb_top_setinfo1 = new JLabel(" rozen"); // �ӽ÷� rozen�־�� �۾��Ҷ� ����� �޾ƿ� ���̵� ���� �󺧿� ���̸�� 
	JLabel lb_top_setinfo2 = new JLabel("admin");// �ӽ÷� admin�־�� �۾��Ҷ� ����� �޾ƿ� ���̵� ���� �󺧿� ���̸�� 

	
	String[] str_btn_name = { "����", "����", "�ƿ���", "����", "�Ź�", "�Ǽ�����" };
	int i;
	int j;
	int k;
	JButton[] btn_menu = new JButton[6];
	JButton btn_write = new JButton("�۾���");
	Image image = null;
	Image resizeImage = null;
	
	
	public Shop_admin() {
		
		
		
		// --------DB ���̺� ���� --------//
				Table_model = new DefaultTableModel(contents, header);
				tb_Pro = new JTable(Table_model);
				// --------DB ���̺� ���� --------//

				try {
					fr = new FileReader(gr);
					br = new BufferedReader(fr);// �о�� ���� ���ۿ� ��ü ���

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
			lb_name[i] = new JLabel("�̸� : ");
			lb_size[i] = new JLabel("������ : ");
			lb_price[i] = new JLabel("���� : ");
			lb_jumun[i] = new JLabel("[�ֹ�����]");
			lb_1day[i] = new JLabel("[���Ϲ��]");
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
